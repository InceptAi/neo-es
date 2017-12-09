/* Copies parameters from a JSON/GSON source to an ontology.
 * Names are matched on lowercase; exceptions (name disagreements)
 * are handled by specialCaseNames */

package com.inceptai.neo.es;

import static java.util.Collections.singleton;

import com.inceptai.neo.es.OntologyUtils.AxiomAdder;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.Iterator;
import org.apache.commons.io.FileUtils;
import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.formats.OWLXMLDocumentFormat;
import org.semanticweb.owlapi.io.StringDocumentSource;
import org.semanticweb.owlapi.model.AxiomType;
import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLAxiom;
import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLDataFactory;
import org.semanticweb.owlapi.model.OWLDataProperty;
import org.semanticweb.owlapi.model.OWLDataPropertyAssertionAxiom;
import org.semanticweb.owlapi.model.OWLDataPropertyDomainAxiom;
import org.semanticweb.owlapi.model.OWLIndividual;
import org.semanticweb.owlapi.model.OWLNamedIndividual;
import org.semanticweb.owlapi.model.OWLObjectProperty;
import org.semanticweb.owlapi.model.OWLObjectPropertyDomainAxiom;
import org.semanticweb.owlapi.model.OWLObjectSomeValuesFrom;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;
import org.semanticweb.owlapi.model.OWLOntologyManager;
import org.semanticweb.owlapi.model.OWLOntologyStorageException;
import org.semanticweb.owlapi.util.OWLOntologyWalker;
import org.semanticweb.owlapi.util.OWLOntologyWalkerVisitorEx;


public class ParamCopier {
  private OWLOntologyManager owlOntologyManager;
  private OWLOntology ontology;

  public ParamCopier(OWLOntologyManager iowlOntologyManager, OWLOntology iontology) {
    owlOntologyManager = iowlOntologyManager;
    ontology = iontology;
  }

  private static boolean isGetter(Method method){
    if(!method.getName().startsWith("get"))      return false;
    if(method.getParameterTypes().length != 0)   return false;  
    if(void.class.equals(method.getReturnType())) return false;
    return true;
  }

  /* Given an object, call all getters and return result
   * as a Map, e.g. {"foo": getFoo(), "bar": getBar() ... } */

  private static Map<String, Object> callGetters(Object o) {
    Map<String, Object> res = new HashMap<String, Object>();
    Class cl = o.getClass();
    Method[] methods = cl.getMethods(); 
    for(Method method : methods){
      if(isGetter(method)) {
        if(method.getName().equals("getClass")) {
          continue;
        }
        String lcName = method.getName().substring(3).toLowerCase();
        Class returnType = method.getReturnType();
        Object val = null;
        try {
          val = method.invoke(o);
        } catch(Exception e){
          val = "EXCEPTION";
        }
        res.put(lcName, val);
      }
    }
    return res;
  }


  /* Given a class like OntologyConstants.Wifi.class
   * return Map<lcName, propertyName> */

  private static Map<String, String> getOntologyNameMap(Class cl) {
    Map<String, String> res = new HashMap<String, String>();
    Method[] methods = cl.getMethods(); 
    for(Method method : methods){
      if(method.getName().endsWith("Property")) {
        String lcName = method.getName().substring(0, method.getName().length() - 8).toLowerCase(); // chop Property
        Class returnType = method.getReturnType();
        try {
          String propName = (String)(method.invoke(null));
          res.put(lcName, propName);
        } catch(Exception e) {
          // fixme
        }
      }
    }
    return res;
  }


  /* Given an array of {key, value, key, value ... }
   * Return a Map */

  private Map<String, String> mapFromArray(String[] a) {
    Map<String, String> res = new HashMap<String, String>();
    for(int i=0; i<a.length; i+=2) {
      res.put(a[i], a[i+1]);
    }
    return res;
  }

  /* Copy all applicable fields from grade into ontology */

  public void copyJsonToOntology(String indivName, Object grade, Class ontClass, String[] specialCaseNamesArray) {
    Map<String, String> specialCaseNames = mapFromArray(specialCaseNamesArray);
    Optional<IRI> iri = ontology.getOntologyID().getOntologyIRI();
    if (!iri.isPresent()) {
      //NL.e("No IRI found for stored ontology. Unable to add individual.");
      return;
    }
    IRI ontologyIRI = iri.get();
    OWLDataFactory factory = owlOntologyManager.getOWLDataFactory();
    OWLIndividual indiv = factory.getOWLNamedIndividual(IRI.create(ontologyIRI + indivName));
    AxiomAdder axiomAdder = new AxiomAdder(factory, indiv, ontologyIRI);

    Map<String, Object> jsonParams = callGetters(grade);
    Map<String, String> ontNames = getOntologyNameMap(ontClass);
    Iterator it = ontNames.entrySet().iterator();
    while (it.hasNext()) {
      Map.Entry pair = (Map.Entry)it.next();
      String k = (String)pair.getKey();

      /* A few names need to be translated */
      if(specialCaseNames.containsKey(k)) {
        k = specialCaseNames.get(k);
      }
      if(jsonParams.containsKey(k)) {
        String ontKey = (String)pair.getValue();
        Object val = jsonParams.get(k);

        if(val instanceof String) {
          axiomAdder.addProperty(ontKey, (String)val);
        }
        else if(val instanceof Integer) {
          axiomAdder.addProperty(ontKey, (Integer)val);
        }
        else if(val instanceof Double) {
          axiomAdder.addProperty(ontKey, (Double)val);
        }
        else if(val == null) {
        }
        else {
          System.out.println("asher4: unmatched type:" + k + ":  " + val.getClass());
        }
      }
    }
    owlOntologyManager.addAxioms(ontology, axiomAdder.asSet().stream());
  }
};

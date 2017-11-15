package com.inceptai.neo.es;

import static java.util.Collections.singleton;

import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.io.StringDocumentSource;
import org.semanticweb.owlapi.model.OWLObjectSomeValuesFrom;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;
import org.semanticweb.owlapi.model.OWLOntologyManager;
import org.semanticweb.owlapi.util.OWLOntologyWalker;
import org.semanticweb.owlapi.util.OWLOntologyWalkerVisitorEx;

public class NeoES {

  private OWLOntologyManager owlOntologyManager;
  private OWLOntology ontology;

  private NeoES(OWLOntologyManager owlOntologyManager, OWLOntology initOntology) {
    this.owlOntologyManager = owlOntologyManager;
    this.ontology = initOntology;
  }

  /**
   *
   * @param owlFile
   * @return Instance of the NeoES object or null on error.
   */
  public static NeoES buildNeoExpertSystem(String owlFile) {
    OWLOntologyManager manager = OWLManager.createOWLOntologyManager();
    OWLOntology owlOntology;

    String readFile="";
    try {
      readFile = FileUtils.readFileToString(new File(owlFile), (String) null);
    } catch (IOException e) {
      // Error logger.
      return null;
    }
    try {
      owlOntology = manager.loadOntologyFromOntologyDocument(new StringDocumentSource(readFile));

    } catch (OWLOntologyCreationException e) {
      return null;
    }
    return new NeoES(manager, owlOntology);
  }

  /**
   * Adds the given OWL file to the basic ontology.
   *
   * @param owlFile
   */
  public void addOntology(String owlFile){

  }

  public void walkOntology() {
    System.out.println(ontology.toString());
    OWLOntologyWalker walker = new OWLOntologyWalker(singleton(ontology));

    OWLOntologyWalkerVisitorEx<Object> visitor = new OWLOntologyWalkerVisitorEx<Object>(walker) {

      public Object visit(OWLObjectSomeValuesFrom ce) {
        // Print out the restriction
        System.out.println("NODE:" + ce.toString());
        // Print out the axiom where the restriction is used
        System.out.println("   has axiom:  " + getCurrentAxiom());
        // We don't need to return anything here.
        return "";
      }
    };
    // Now ask the walker to walk over the ontology structure using our
    // visitor instance.
    walker.walkStructure(visitor);
  }
}

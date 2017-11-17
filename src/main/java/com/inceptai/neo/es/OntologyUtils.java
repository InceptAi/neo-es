package com.inceptai.neo.es;

import java.util.HashSet;
import java.util.Set;
import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLAxiom;
import org.semanticweb.owlapi.model.OWLDataFactory;
import org.semanticweb.owlapi.model.OWLDataProperty;
import org.semanticweb.owlapi.model.OWLDataPropertyAssertionAxiom;
import org.semanticweb.owlapi.model.OWLIndividual;

public class OntologyUtils {
  private static final Logger NL = Logger.get(OntologyUtils.class);

  public static class AxiomAdder {
    private OWLDataFactory owlDataFactory;
    private OWLIndividual owlIndividual;
    private IRI iri;

    private Set<OWLAxiom> axiomSet;

    public AxiomAdder(OWLDataFactory factory, OWLIndividual owlIndividual,
        IRI iri) {
      this.owlDataFactory = factory;
      this.owlIndividual = owlIndividual;
      this.iri = iri;
      axiomSet = new HashSet<>();
    }

    public void addProperty(String property, String value) {
      if (value == null) {
        NL.w("Skipping null value for property: " + property);
        return;
      }
      NL.i("Adding property: " + property + " : " + value);
      axiomSet.add(dataProperty(owlDataFactory, owlIndividual, iri, property, value));
    }

    public void addProperty(String property, int value) {
      OWLDataProperty owlDataProperty = owlDataFactory.getOWLDataProperty(IRI.create(iri + property));
      OWLDataPropertyAssertionAxiom dataPropertyAssertion = owlDataFactory.getOWLDataPropertyAssertionAxiom(owlDataProperty,
          owlIndividual, value);
      axiomSet.add(dataPropertyAssertion);
    }

    public void addProperty(String property, double value) {
      OWLDataProperty owlDataProperty = owlDataFactory.getOWLDataProperty(IRI.create(iri + property));
      OWLDataPropertyAssertionAxiom dataPropertyAssertion = owlDataFactory.getOWLDataPropertyAssertionAxiom(owlDataProperty,
          owlIndividual, value);
      axiomSet.add(dataPropertyAssertion);
    }

    public Set<OWLAxiom> asSet() {
      return axiomSet;
    }
  }

  public static OWLDataPropertyAssertionAxiom dataProperty(OWLDataFactory factory, OWLIndividual owlIndividual,
      IRI iri, String propertyName, String value) {
    OWLDataProperty owlDataProperty = factory.getOWLDataProperty(IRI.create(iri + propertyName));
    OWLDataPropertyAssertionAxiom dataPropertyAssertion = factory.getOWLDataPropertyAssertionAxiom(owlDataProperty,
        owlIndividual, value);
    return dataPropertyAssertion;
  }

}

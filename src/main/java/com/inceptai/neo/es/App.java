package com.inceptai.neo.es;

import java.io.File;
import java.io.IOException;
import javax.annotation.Nonnull;
import org.apache.commons.io.FileUtils;
import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.io.StringDocumentSource;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;
import org.semanticweb.owlapi.model.OWLOntologyManager;

/**
 * Hello world!
 */
public class App {

  public static void main(String[] args) {
    try {
      OWLOntologyManager manager = OWLManager.createOWLOntologyManager();
      OWLOntology ontology = load(manager, "");
    } catch (OWLOntologyCreationException e) {
      System.out.println(e.toString());
    }
    System.out.println("Hello World!");
  }

  @Nonnull
  private static OWLOntology load(@Nonnull OWLOntologyManager manager, String sourceFile)
      throws OWLOntologyCreationException {
    String readFile="";
    try {
      readFile = FileUtils.readFileToString(new File(sourceFile), (String) null);
    } catch (IOException e) {

    }
    // in this test, the ontology is loaded from a string
    return manager.loadOntologyFromOntologyDocument(new StringDocumentSource(readFile));
  }
}

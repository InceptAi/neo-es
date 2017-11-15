package com.inceptai.neo.es;

/**
 * Hello world!
 */
public class App {
  private static final String ONTOLOGY_SRC = "owl/android-user-101.owl";

  public static void main(String[] args) {
    NeoES neoES = NeoES.buildNeoExpertSystem(ONTOLOGY_SRC);

    if (neoES != null) {
      System.out.println("Successfully created a NeoES instance !");
    } else {
      System.out.println("NeoES creation failed.");
    }
    neoES.walkOntology();
  }
}

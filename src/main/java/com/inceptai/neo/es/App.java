package com.inceptai.neo.es;

import com.inceptai.neo.es.data.InferenceBundle;
import com.inceptai.neo.es.data.JsonParser;
import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;

/**
 * CommandLine interface to the NeoES.
 */
public class App {
  private static final String ONTOLOGY_SRC = "owl/android-user-101.owl";
  private static final String INFERENCE_RECORD_JSON = "json-data/dobbybackend--KjP4FnLvj-SdF0zGXnN-export.json";
  private static final String ENRICHED_ONTOLOGY_FN = "enriched.owl";
  private static final String UNENRICHED_ONTOLOGY_FN = "unenriched.owl";
  private static final Logger NL = Logger.get(App.class);

  public static void main(String[] args) {
    NeoES neoES = NeoES.buildNeoExpertSystem(ONTOLOGY_SRC);
    boolean rc = neoES.saveOntology(UNENRICHED_ONTOLOGY_FN);
    System.out.println(String.format("Wrote unenriched ontology to %s; rc=%b", UNENRICHED_ONTOLOGY_FN, rc));
    neoES.printOntology();
    if (neoES != null) {
      System.out.println("Successfully created a NeoES instance !");
    } else {
      System.out.println("NeoES creation failed.");
    }
    neoES.walkOntology();
    try {
      InferenceBundle bundle = JsonParser.parseInferenceResult(
          FileUtils.readFileToString(new File(INFERENCE_RECORD_JSON), (String) null));
      neoES.addInference(bundle);
    } catch (IOException e) {
      NL.e("Exception parsing json file: " + e);
    }
    rc = neoES.saveOntology(ENRICHED_ONTOLOGY_FN);
    System.out.println(String.format("Wrote enriched ontology to %s; rc=%b", ENRICHED_ONTOLOGY_FN, rc));
  }
}

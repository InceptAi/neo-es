package com.inceptai.neo.es.data;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.inceptai.neo.es.Logger;

public class JsonParser {
  private static Gson GSON;
  private static Logger NL = Logger.get(JsonParser.class);

  private JsonParser() {}

  public static void parseInferenceResult(String jsonString) {
    InferenceRecord record = getGson().fromJson(jsonString, InferenceRecord.class);
    NL.i("InferenceRecord: " + record);
  }

  /**
   * Lazy create Gson builder.
   * @return
   */
  private static Gson getGson() {
    if (GSON == null) {
      GSON = new GsonBuilder().setPrettyPrinting().create();
    }
    return GSON;
  }
}

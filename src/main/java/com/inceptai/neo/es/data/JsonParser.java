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

    BandwidthGrade bandwidthGrade = getGson().fromJson(record.getBandwidthGradeJson(), BandwidthGrade.class);
    NL.i("BandwidthGrade: " + bandwidthGrade);

    PingGrade pingGrade = getGson().fromJson(record.getPingGradeJson(), PingGrade.class);
    NL.i("PingGrade: " + pingGrade);

    HttpGrade httpGrade = getGson().fromJson(record.getHttpGradeJson(), HttpGrade.class);
    NL.i("HttpGrade: " + httpGrade);

    WifiGrade wifiGrade = getGson().fromJson(record.getWifiGradeJson(), WifiGrade.class);
    NL.i("WifiGrade: " + wifiGrade);
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

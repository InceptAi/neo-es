package com.inceptai.neo.es.data;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This class is a POJO representation of how the Inference data is stored in firebase.
 */
public class InferenceRecord {
  public String uid;
  public String phoneInfo;
  public String appVersion;
  public String titleMessage;
  public long timestamp;
  public String bandwidthGradeJson;
  public String wifiGradeJson;
  public String pingGradeJson;
  public String httpGradeJson;
  public List<String> detailedMessageList;
  public String conditionsUsedForInference;
  public double lat;
  public double lon;
  public double accuracy;

  public InferenceRecord() {}

  public Map<String, Object> toMap() {
    HashMap<String, Object> result = new HashMap<String, Object>();
    result.put("uid", uid);
    result.put("phoneInfo", phoneInfo);
    result.put("titleMessage", titleMessage);
    result.put("appVersion", appVersion);
    result.put("detailedMessageList", detailedMessageList);
    result.put("timestamp", timestamp);
    result.put("bandwidthGradeJson", bandwidthGradeJson);
    result.put("wifiGradeJson", wifiGradeJson);
    result.put("pingGradeJson", pingGradeJson);
    result.put("httpGradeJson", httpGradeJson);
    result.put("conditionsUsedForInference", conditionsUsedForInference);
    result.put("lat", lat);
    result.put("lon", lon);
    result.put("accuracy", accuracy);
    return result;
  }

  public String getUid() {
    return uid;
  }

  public String getPhoneInfo() {
    return phoneInfo;
  }

  public String getTitleMessage() {
    return titleMessage;
  }

  public List<String> getDetailedMessageList() {
    return detailedMessageList;
  }

  public long getTimestamp() {
    return timestamp;
  }

  public String getBandwidthGradeJson() {
    return bandwidthGradeJson;
  }

  public String getWifiGradeJson() {
    return wifiGradeJson;
  }

  public String getPingGradeJson() {
    return pingGradeJson;
  }

  public String getHttpGradeJson() {
    return httpGradeJson;
  }

  public String getConditionsUsedForInference() {
    return conditionsUsedForInference;
  }

  public String getAppVersion() {
    return appVersion;
  }

  public double getLat() {
    return lat;
  }

  public double getLon() {
    return lon;
  }

  public double getAccuracy() {
    return accuracy;
  }

  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder();
    builder.append("\nuid = " + uid);
    builder.append("\n phoneInfo = " + phoneInfo);
    builder.append("\n titleMessage = " + titleMessage);
    builder.append("\n appVersion = " + appVersion);
    builder.append("\n detailedMessageList = " + detailedMessageList);
    builder.append("\n timestamp = " + timestamp);
    builder.append("\n bandwidthGradeJson = " + bandwidthGradeJson);
    builder.append("\n wifiGradeJson = " + wifiGradeJson);
    builder.append("\n pingGradeJson = " + pingGradeJson);
    builder.append("\n httpGradeJson = " + httpGradeJson);
    builder.append("\n conditionUsedForInference = " + conditionsUsedForInference);
    builder.append("\n lat = " + lat);
    builder.append("\n lng = " + lon);
    builder.append("\n accuracy = " + accuracy);
    return builder.toString();
  }
}

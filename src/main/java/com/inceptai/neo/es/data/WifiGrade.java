package com.inceptai.neo.es.data;

import com.inceptai.neo.es.Utils;
import com.inceptai.neo.es.data.WifiAnnotations.ErrorCodes;
import com.inceptai.neo.es.data.WifiAnnotations.MetricType;
import com.inceptai.neo.es.data.WifiAnnotations.WifiConnectivityMode;
import com.inceptai.neo.es.data.WifiAnnotations.WifiLinkMode;
import java.util.HashMap;

public class WifiGrade {
  HashMap<Integer, Integer> wifiChannelOccupancyMetric;  /* based on congestion metric */
  @MetricType
  int primaryApSignalMetric = MetricType.UNKNOWN;
  @MetricType
  int primaryApLinkSpeedMetric = MetricType.UNKNOWN;
  @MetricType
  int primaryLinkChannelOccupancyMetric = MetricType.UNKNOWN;
  @WifiConnectivityMode int wifiConnectivityMode = WifiConnectivityMode.UNKNOWN;
  @WifiLinkMode int wifiLinkMode = WifiLinkMode.UNKNOWN;
  String primaryApBSSID;
  String primaryApSsid;
  int primaryApChannel;
  int leastOccupiedChannel;
  int primaryApChannelInterferingAps;
  int leastOccupiedChannelAps;
  int primaryApSignal;
  int linkSpeed;
  @ErrorCodes
  int errorCode = ErrorCodes.ERROR_UNINITIAlIZED;
  // HashMap<String, Utils.PercentileStats> detailedNetworkStateStats;
  HashMap<Long, String> networkStateTransitions;
  private long updatedAtMs;
  private String connectivityModeString = Utils.EMPTY_STRING;
  private String linkModeString = Utils.EMPTY_STRING;
  private String errorCodeString = Utils.EMPTY_STRING;
  private String primaryApSignalString = Utils.EMPTY_STRING;
  private String primaryChannelOccupancyString = Utils.EMPTY_STRING;
  private String linkSpeedString = Utils.EMPTY_STRING;



  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder();
    builder.append("\n\nWifiGrade: Primary AP signalMetric: " +
        WifiAnnotations.metricTypeToString(primaryApSignalMetric) +
        ", SpeedMetric: " + WifiAnnotations.metricTypeToString(primaryApLinkSpeedMetric) +
        ", channelOccupancy: " +
        WifiAnnotations.metricTypeToString(primaryLinkChannelOccupancyMetric));
    builder.append("\nWifi connectivity mode: " + WifiAnnotations.connectivityModeToString(wifiConnectivityMode));
    builder.append("\nWifi link mode: " + WifiAnnotations.wifiLinkModeToString(wifiLinkMode));
    builder.append("\nChannel map:" + wifiChannelOccupancyMetric.toString());
    builder.append("\n primaryApChannel:" + primaryApChannel);
    builder.append("\n primaryApSignal:" + primaryApSignal);
    builder.append("\n leastOccupiedChannel:" + leastOccupiedChannel);
    builder.append("\n primaryApChannelAps:" + primaryApChannelInterferingAps);
    builder.append("\n leastOccupiedChannelAps:" + leastOccupiedChannelAps);
    builder.append("\n linkSpeed:" + linkSpeed);
    return builder.toString();
  }

  public String getPrimaryApSignalMetric() {
    return WifiAnnotations.metricTypeToString(primaryApSignalMetric);
  }

  public int getPrimaryApLinkSpeedMetric() {
    return primaryApLinkSpeedMetric;
  }

  public int getPrimaryLinkChannelOccupancyMetric() {
    return primaryLinkChannelOccupancyMetric;
  }

  public String getWifiConnectivityMode() {
    return WifiAnnotations.connectivityModeToString(wifiConnectivityMode);
  }

  public String getWifiLinkMode() {
    return WifiAnnotations.wifiLinkModeToString(wifiLinkMode);
  }

  public String getPrimaryApBSSID() {
    return primaryApBSSID;
  }

  public String getPrimaryApSsid() {
    return primaryApSsid;
  }

  public int getPrimaryApChannel() {
    return primaryApChannel;
  }

  public int getLinkSpeed() {
    return linkSpeed;
  }
  public String getErrorCode() {
    return WifiAnnotations.errorCodesToStrings(errorCode);
  }
}

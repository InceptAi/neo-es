package com.inceptai.neo.es.data;

import com.inceptai.neo.es.Utils;
import com.inceptai.neo.es.data.WifiAnnotations.ErrorCodes;
import com.inceptai.neo.es.data.WifiAnnotations.MetricType;

public class BandwidthGrade {
  private @MetricType int uploadBandwidthMetric = MetricType.UNKNOWN;
  private @MetricType int downloadBandwidthMetric = MetricType.UNKNOWN;
  private long downloadUpdatedAtMs;
  private long uploadUpdatedAtMs;
  private double uploadMbps;
  private double downloadMbps;
  String isp = Utils.EMPTY_STRING;;
  String externalIP = Utils.EMPTY_STRING;;
  String bestServerName = Utils.EMPTY_STRING;
  String bestServerCountry = Utils.EMPTY_STRING;
  double bestServerLatencyMs;
  double lat;
  double lon;
  @ErrorCodes int errorCode = ErrorCodes.ERROR_UNINITIAlIZED;
  private String errorCodeString = Utils.EMPTY_STRING;
  private String downloadMetricString = Utils.EMPTY_STRING;
  private String uploadMetricString  = Utils.EMPTY_STRING;


  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder();
    builder.append("\n\nBandwidth Grade: Upload: " + WifiAnnotations.metricTypeToString(uploadBandwidthMetric));
    builder.append("\n Download: " + WifiAnnotations.metricTypeToString(downloadBandwidthMetric));
    builder.append("\n Download BW: " + downloadMbps);
    builder.append("\n Upload BW: " + uploadMbps);
    builder.append("\n Download Updated: " + downloadUpdatedAtMs);
    builder.append("\n Upload Updated: " + downloadUpdatedAtMs);
    builder.append("\n isp : " + isp);
    builder.append("\n external IP: " + externalIP);
    builder.append("\n lat: " + lat);
    builder.append("\n lon: " + lon);
    builder.append("\n bestServerName: " + bestServerName);
    builder.append("\n bestServerCountry: " + bestServerCountry);
    builder.append("\n bestServerLatencyMs: " + bestServerLatencyMs);
    builder.append("\n error code: " + errorCode);

    return builder.toString();
  }

}

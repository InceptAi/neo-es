package com.inceptai.neo.es.data;

import com.inceptai.neo.es.Utils;
import com.inceptai.neo.es.data.WifiAnnotations.ErrorCodes;
import com.inceptai.neo.es.data.WifiAnnotations.MetricType;

public class PingGrade {
  @MetricType int externalServerLatencyMetric = MetricType.UNKNOWN;
  @MetricType int dnsServerLatencyMetric = MetricType.UNKNOWN;
  @MetricType int routerLatencyMetric = MetricType.UNKNOWN;
  @MetricType int alternativeDnsMetric = MetricType.UNKNOWN;
  String primaryDns;
  String alternativeDns;
  String routerIp;
  String ownIp;
  String netmask;
  int leaseDuration;
  double routerLatencyMs;
  double dnsServerLatencyMs;
  double externalServerLatencyMs;
  double alternativeDnsLatencyMs;
  long updatedAtMs;
  @ErrorCodes int errorCode = ErrorCodes.ERROR_UNINITIAlIZED;
  private String errorCodeString = Utils.EMPTY_STRING;
  private String externalServerMetricString = Utils.EMPTY_STRING;
  private String dnsServerMetricString = Utils.EMPTY_STRING;
  private String routerMetricString = Utils.EMPTY_STRING;
  private String alternativeDnsMetricString = Utils.EMPTY_STRING;


  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder();
    builder.append("\n\nPingGrade: ExternalServer: " +
        WifiAnnotations.metricTypeToString(externalServerLatencyMetric) + "\n DnsServer: " +
        WifiAnnotations.metricTypeToString(dnsServerLatencyMetric) + "\n Router: " +
        WifiAnnotations.metricTypeToString(routerLatencyMetric) + "\n Alternative Dns: " +
        WifiAnnotations.metricTypeToString(alternativeDnsMetric));
    return builder.toString();
  }

  public String getExternalServerLatencyMetric() {
    return WifiAnnotations.metricTypeToString(externalServerLatencyMetric);
  }

  public String getDnsServerLatencyMetric() {
    return WifiAnnotations.metricTypeToString(dnsServerLatencyMetric);
  }

  public String getRouterLatencyMetric() {
    return WifiAnnotations.metricTypeToString(routerLatencyMetric);
  }

  public String getAlternativeDnsMetric() {
    return WifiAnnotations.metricTypeToString(alternativeDnsMetric);
  }

  /*
  public String getPrimaryDns() {
    return primaryDns;
  }

  public String getAlternativeDns() {
    return alternativeDns;
  }

  public String getRouterIp() {
    return routerIp;
  }

  public String getOwnIp() {
    return ownIp;
  }

  public String getNetmask() {
    return netmask;
  }

  public int getLeaseDuration() {
    return leaseDuration;
  }
  */

  public double getRouterLatencyMs() {
    return routerLatencyMs;
  }

  public double getDnsServerLatencyMs() {
    return dnsServerLatencyMs;
  }

  public double getExternalServerLatencyMs() {
    return externalServerLatencyMs;
  }

  public double getAlternativeDnsLatencyMs() {
    return alternativeDnsLatencyMs;
  }

}

package com.inceptai.neo.es.data;

import com.inceptai.neo.es.Utils;
import com.inceptai.neo.es.data.WifiAnnotations.ErrorCodes;
import com.inceptai.neo.es.data.WifiAnnotations.MetricType;

public class HttpGrade {
  @MetricType int httpDownloadLatencyMetric = MetricType.UNKNOWN;
  private long updatedAtMs;
  @ErrorCodes int errorCode = ErrorCodes.ERROR_UNINITIAlIZED;
  private String errorCodeString = Utils.EMPTY_STRING;
  private String httpDownloadMetricString = Utils.EMPTY_STRING;
  private double httpDownloadLatencyMs;



  @Override
  public String toString() {
    return "\n\nHttpGrade: " + WifiAnnotations.metricTypeToString(httpDownloadLatencyMetric);
  }

}

package com.inceptai.neo.es.data;

/**
 * Just a wrapper class to hold references to *Grade and InferenceRecord objects.
 */
public class InferenceBundle {

  private InferenceRecord inferenceRecord;
  private WifiGrade wifiGrade;
  private HttpGrade httpGrade;
  private BandwidthGrade bandwidthGrade;
  private PingGrade pingGrade;

  public InferenceBundle(InferenceRecord inferenceRecord, WifiGrade wifiGrade, HttpGrade httpGrade,
      BandwidthGrade bandwidthGrade, PingGrade pingGrade) {
    this.inferenceRecord = inferenceRecord;
    this.wifiGrade = wifiGrade;
    this.httpGrade = httpGrade;
    this.bandwidthGrade = bandwidthGrade;
    this.pingGrade = pingGrade;
  }


  public InferenceRecord getInferenceRecord() {
    return inferenceRecord;
  }

  public WifiGrade getWifiGrade() {
    return wifiGrade;
  }

  public HttpGrade getHttpGrade() {
    return httpGrade;
  }

  public BandwidthGrade getBandwidthGrade() {
    return bandwidthGrade;
  }

  public PingGrade getPingGrade() {
    return pingGrade;
  }
}

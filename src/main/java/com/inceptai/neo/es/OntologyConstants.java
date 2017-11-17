package com.inceptai.neo.es;

public class OntologyConstants {

  private static final String WIFI_NETWORK_INDIVIDUAL = "#RealWifiNetwork";
  private static final String INTERNET_CONNECTION_INDIVIDUAL = "#RealInternet";


  public static String wifiNetworkIndividual() {
    return WIFI_NETWORK_INDIVIDUAL;
  }

  public static String internetConnectionIndividual() {
    return INTERNET_CONNECTION_INDIVIDUAL;
  }

  public static final class Wifi {
    private static final String PRIMARY_AP_SIGNAL_METRIC = "PrimaryAPSignalMetric";
    private static final String PRIMARY_AP_LINK_SPEED_METRIC = "PrimaryAPLinkSpeedMetric";
    private static final String PRIMARY_AP_LINK_SPEED = "PrimaryAPLinkSpeed";
    private static final String WIFI_LINK_MODE = "WifiLinkMode";
    private static final String WIFI_ERROR_CODE = "WifiErrorCode";
    private static final String WIFI_DATA_RATE = "WifiDataRate";
    private static final String WIFI_CONNECTIVITY_MODE = "WifiConnectivityMode";
    private static final String PRIMARY_LINK_CHANNEL_OCCUPANCY_METRIC = "PrimaryLinkChannelOccupancyMetric";
    private static final String PRIMARY_AP_SSID = "PrimaryAPSSID";
    private static final String PRIMARY_AP_BSSID = "PrimaryAPBSSID";
    private static final String PRIMARY_AP_CHANNEL = "PrimaryAPChannel";


    public static String primaryApSignalMetricProperty() {
      return PRIMARY_AP_SIGNAL_METRIC;
    }

    public static String primaryApLinkSpeedProperty(){
      return PRIMARY_AP_LINK_SPEED;
    }

    public static String primaryApLinkSpeedMetricProperty(){
      return PRIMARY_AP_LINK_SPEED_METRIC;
    }

    public static String wifiLinkModeProperty() {
      return WIFI_LINK_MODE;
    }

    public static String wifiErrorCodeProperty() {
      return WIFI_ERROR_CODE;
    }

    public static String wifiDataRateProperty() {
      return WIFI_DATA_RATE;
    }

    public static String wifiConnectivityModeProperty() {
      return WIFI_CONNECTIVITY_MODE;
    }

    public static String primaryLinkChannelOccupancyMetricProperty() {
      return PRIMARY_LINK_CHANNEL_OCCUPANCY_METRIC;
    }

    public static String primaryApSsidProperty() {
      return PRIMARY_AP_SSID;
    }

    public static String primaryApBssidProperty() {
      return PRIMARY_AP_BSSID;
    }

    public static String primaryApChannelProperty() {
      return PRIMARY_AP_CHANNEL;
    }
  }

  public static class Http {

    private static final String HTTP_ERROR_CODE = "HttpErrorCode";
    private static final String HTTP_DOWNLOAD_LATENCY_MS = "HttpDownloadLatencyMs";
    private static final String DOWNLOAD_LATENCY_METRIC = "DownloadLatencyMetric";

    public static String httpErrorCodeProperty() {
      return HTTP_ERROR_CODE;
    }

    public static String httpDownloadLatencyMsProperty() {
      return HTTP_DOWNLOAD_LATENCY_MS;
    }

    public static String downloadLatencyMetricProperty() {
      return DOWNLOAD_LATENCY_METRIC;
    }
  }

  public static class Ping {

    private static final String ROUTER_LATENCY_MS = "RouterLatencyMs";
    private static final String ROUTER_LATENCY_METRIC = "RouterLatencyMetric";

    private static final String EXTERNAL_SERVER_LATENCY_MS = "ExternalServerLatencyMs";
    private static final String EXTERNAL_SERVER_LATENCY_METRIC = "ExternalServerLatencyMetric";

    private static final String DNS_LATENCY_MS = "DnsLatencyMs";
    private static final String DNS_LATENCY_METRIC = "DnsLatencyMetric";


    private static final String ALTERNATIVE_DNS_LATENCY_MS = "AlternativeDnsLatencyMs";
    private static final String ALTERNATIVE_DNS_LATENCY_METRIC = "AlternativeDnsLatencyMetric";

    public static String routerLatencyMsProperty() {
      return ROUTER_LATENCY_MS;
    }

    public static String routerLatencyMetricProperty() {
      return ROUTER_LATENCY_METRIC;
    }

    public static String externalServerLatencyMsProperty() {
      return EXTERNAL_SERVER_LATENCY_MS;
    }

    public static String externalServerLatencyMetricProperty() {
      return EXTERNAL_SERVER_LATENCY_METRIC;
    }

    public static String dnsLatencyMsProperty() {
      return DNS_LATENCY_MS;
    }

    public static String dnsLatencyMetricProperty() {
      return DNS_LATENCY_METRIC;
    }

    public static String alternativeDnsLatencyMsProperty() {
      return ALTERNATIVE_DNS_LATENCY_MS;
    }

    public static String alternativeDnsLatencyMetricProperty() {
      return ALTERNATIVE_DNS_LATENCY_METRIC;
    }
  }

  public static class Bandwidth {

    private static final String UPLOAD_BANDWIDTH_METRIC = "UploadBandwidthMetric";
    private static final String UPLOAD_MBPS = "UploadMbps";

    private static final String DOWNLOAD_BANDWIDTH_METRIC = "DownloadBandwidthMetric";
    private static final String DOWNLOAD_MBPS = "DownloadMbps";

    private static final String BEST_SERVER_LATENCY_MS = "BestServerLatencyMs";
    private static final String BANDWIDTH_ERROR_CODE = "BandwidthErrorCode";

    public static String uploadBandwidthMetricProperty() {
      return UPLOAD_BANDWIDTH_METRIC;
    }

    public static String uploadMbpsProperty() {
      return UPLOAD_MBPS;
    }

    public static String downloadBandwidthMetricProperty() {
      return DOWNLOAD_BANDWIDTH_METRIC;
    }

    public static String downloadMbpsProperty() {
      return DOWNLOAD_MBPS;
    }

    public static String bestServerLatencyMsProperty() {
      return BEST_SERVER_LATENCY_MS;
    }

    public static String bandwidthErrorCodeProperty() {
      return BANDWIDTH_ERROR_CODE;
    }
  }
}

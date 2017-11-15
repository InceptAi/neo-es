package com.inceptai.neo.es.data;

import static java.lang.annotation.RetentionPolicy.CLASS;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

public class WifiAnnotations {

  @IntDef({MetricType.EXCELLENT, MetricType.GOOD, MetricType.AVERAGE, MetricType.POOR,
      MetricType.ABYSMAL, MetricType.NONFUNCTIONAL, MetricType.UNKNOWN})
  @Retention(RetentionPolicy.SOURCE)
  public @interface MetricType {

    int EXCELLENT = 0;
    int GOOD = 1;
    int AVERAGE = 2;
    int POOR = 3;
    int ABYSMAL = 4;
    int NONFUNCTIONAL = 5;  /* all valid values that are POOR or worse */
    int UNKNOWN = 6;  /* no valid result available */
  }

  @Retention(CLASS)
  @IntDef({WifiConnectivityMode.CONNECTED_AND_ONLINE, WifiConnectivityMode.CONNECTED_AND_CAPTIVE_PORTAL,
      WifiConnectivityMode.CONNECTED_AND_OFFLINE, WifiConnectivityMode.ON_AND_DISCONNECTED,
      WifiConnectivityMode.OFF, WifiConnectivityMode.UNKNOWN, WifiConnectivityMode.CONNECTED_AND_UNKNOWN,
      WifiConnectivityMode.MAX_MODES})
  public @interface WifiConnectivityMode {
    int CONNECTED_AND_ONLINE = 0;
    int CONNECTED_AND_CAPTIVE_PORTAL = 1;
    int CONNECTED_AND_OFFLINE = 2;
    int CONNECTED_AND_UNKNOWN = 3;
    int ON_AND_DISCONNECTED = 4;
    int OFF = 5;
    int UNKNOWN = 6;
    int MAX_MODES = 7;
  }


  @Retention(RetentionPolicy.SOURCE)
  @IntDef({SignalStrengthZones.HIGH, SignalStrengthZones.MEDIUM,
      SignalStrengthZones.LOW, SignalStrengthZones.FRINGE})
  public @interface SignalStrengthZones {
    int HIGH  = -60;
    int MEDIUM = -80;
    int LOW = -100;
    int FRINGE = -140;
  }

  @Retention(RetentionPolicy.SOURCE)
  @IntDef({WifiLinkMode.NO_PROBLEM_DEFAULT_STATE, WifiLinkMode.HANGING_ON_DHCP,
      WifiLinkMode.HANGING_ON_AUTHENTICATING, WifiLinkMode.HANGING_ON_SCANNING,
      WifiLinkMode.FREQUENT_DISCONNECTIONS, WifiLinkMode.UNKNOWN,
      WifiLinkMode.DISCONNECTED, WifiLinkMode.CONNECTING, WifiLinkMode.MAX_MODES})
  public @interface WifiLinkMode {
    int NO_PROBLEM_DEFAULT_STATE = 0;  // Connected and working normally.
    int HANGING_ON_DHCP = 1;
    int HANGING_ON_AUTHENTICATING = 2;
    int HANGING_ON_SCANNING = 3;
    int FREQUENT_DISCONNECTIONS = 4;
    int CONNECTING = 5;
    int DISCONNECTED = 6;
    int UNKNOWN = 7;
    int MAX_MODES = 8;
  }


  @Retention(RetentionPolicy.SOURCE)
  @IntDef({TestMode.DOWNLOAD_AND_UPLOAD, TestMode.DOWNLOAD,
      TestMode.UPLOAD, TestMode.CONFIG_FETCH,
      TestMode.SERVER_FETCH, TestMode.STARTING, TestMode.IDLE})
  public @interface TestMode {
    int DOWNLOAD_AND_UPLOAD = 0;
    int DOWNLOAD = 1;
    int UPLOAD = 2;
    int CONFIG_FETCH = 3;
    int SERVER_FETCH = 4;
    int STARTING = 5;
    int IDLE = 6;
  }

  @Retention(RetentionPolicy.SOURCE)
  @IntDef({ErrorCodes.ERROR_UNKNOWN, ErrorCodes.ERROR_FETCHING_CONFIG,
      ErrorCodes.ERROR_PARSING_CONFIG, ErrorCodes.ERROR_FETCHING_SERVER_INFO,
      ErrorCodes.ERROR_PARSING_SERVER_INFO, ErrorCodes.ERROR_SELECTING_BEST_SERVER,
      ErrorCodes.ERROR_INVALID_HTTP_RESPONSE, ErrorCodes.ERROR_SOCKET_ERROR,
      ErrorCodes.ERROR_SOCKET_TIMEOUT, ErrorCodes.ERROR_CONNECTION_ERROR,
      ErrorCodes.ERROR_TEST_INTERRUPTED, ErrorCodes.ERROR_TEST_ALREADY_RUNNING,
      ErrorCodes.ERROR_WIFI_OFFLINE, ErrorCodes.NO_ERROR, ErrorCodes.ERROR_DHCP_INFO_UNAVAILABLE,
      ErrorCodes.ERROR_PERFORMING_PING, ErrorCodes.ERROR_WIFI_IN_CAPTIVE_PORTAL,
      ErrorCodes.ERROR_WIFI_CONNECTED_AND_OFFLINE, ErrorCodes.ERROR_WIFI_CONNECTED_AND_UNKNOWN,
      ErrorCodes.ERROR_WIFI_ON_AND_DISCONNECTED, ErrorCodes.ERROR_WIFI_OFF,
      ErrorCodes.ERROR_WIFI_UNKNOWN_STATE, ErrorCodes.ERROR_UNINITIAlIZED})
  public @interface ErrorCodes {
    int ERROR_UNINITIAlIZED = -1;
    int NO_ERROR = 0;
    int ERROR_UNKNOWN = 1;
    int ERROR_FETCHING_CONFIG = 2;
    int ERROR_PARSING_CONFIG = 3;
    int ERROR_FETCHING_SERVER_INFO = 4;
    int ERROR_PARSING_SERVER_INFO = 5;
    int ERROR_SELECTING_BEST_SERVER = 6;
    int ERROR_INVALID_HTTP_RESPONSE = 7;
    int ERROR_SOCKET_ERROR = 8;
    int ERROR_SOCKET_TIMEOUT = 9;
    int ERROR_CONNECTION_ERROR = 10;
    int ERROR_TEST_INTERRUPTED = 11;
    int ERROR_TEST_ALREADY_RUNNING = 12;
    int ERROR_WIFI_OFFLINE = 13;
    int ERROR_DHCP_INFO_UNAVAILABLE = 14;
    int ERROR_PERFORMING_PING = 15;
    int ERROR_WIFI_IN_CAPTIVE_PORTAL = 16;
    int ERROR_WIFI_CONNECTED_AND_OFFLINE = 17;
    int ERROR_WIFI_CONNECTED_AND_UNKNOWN = 18;
    int ERROR_WIFI_ON_AND_DISCONNECTED = 19;
    int ERROR_WIFI_OFF = 20;
    int ERROR_WIFI_UNKNOWN_STATE = 21;
  }

  public static String wifiLinkModeToString(@WifiLinkMode int mode) {
    switch (mode) {
      case WifiLinkMode.NO_PROBLEM_DEFAULT_STATE:
        return "NO_PROBLEM_DEFAULT_STATE";
      case WifiLinkMode.HANGING_ON_DHCP:
        return "HANGING_ON_DHCP";
      case WifiLinkMode.HANGING_ON_AUTHENTICATING:
        return "HANGING_ON_AUTHENTICATING";
      case WifiLinkMode.HANGING_ON_SCANNING:
        return "HANGING_ON_SCANNING";
      case WifiLinkMode.FREQUENT_DISCONNECTIONS:
        return "FREQUENT_DISCONNECTIONS";
      case WifiLinkMode.DISCONNECTED:
        return "DISCONNECTED";
      case WifiLinkMode.CONNECTING:
        return "CONNECTING";
      case WifiLinkMode.MAX_MODES:
        return "MAX_MODES";
      case WifiLinkMode.UNKNOWN:
      default:
        return "UNKNOWN";
    }
  }


  public static String connectivityModeToString(@WifiConnectivityMode int mode) {
    switch (mode) {
      case WifiConnectivityMode.CONNECTED_AND_ONLINE:
        return "CONNECTED_AND_ONLINE";
      case WifiConnectivityMode.CONNECTED_AND_CAPTIVE_PORTAL:
        return "CONNECTED_AND_CAPTIVE_PORTAL";
      case WifiConnectivityMode.CONNECTED_AND_OFFLINE:
        return "CONNECTED_AND_OFFLINE";
      case WifiConnectivityMode.CONNECTED_AND_UNKNOWN:
        return "CONNECTED_AND_UNKNOWN";
      case WifiConnectivityMode.ON_AND_DISCONNECTED:
        return "ON_AND_DISCONNECTED";
      case WifiConnectivityMode.OFF:
        return "OFF";
      default:
        return "Unknown";
    }
  }

  public static String bandwidthTestErrorCodesToStrings(@ErrorCodes int errorCode) {
    switch (errorCode) {
      case ErrorCodes.NO_ERROR:
        return "NO_ERROR";
      case ErrorCodes.ERROR_FETCHING_CONFIG:
        return "ERROR_FETCHING_CONFIG";
      case ErrorCodes.ERROR_PARSING_CONFIG:
        return "ERROR_PARSING_CONFIG";
      case ErrorCodes.ERROR_FETCHING_SERVER_INFO:
        return "ERROR_FETCHING_SERVER_INFO";
      case ErrorCodes.ERROR_PARSING_SERVER_INFO:
        return "ERROR_PARSING_SERVER_INFO";
      case ErrorCodes.ERROR_SELECTING_BEST_SERVER:
        return "ERROR_SELECTING_BEST_SERVER";
      case ErrorCodes.ERROR_INVALID_HTTP_RESPONSE:
        return "ERROR_INVALID_HTTP_RESPONSE";
      case ErrorCodes.ERROR_SOCKET_ERROR:
        return "ERROR_SOCKET_ERROR";
      case ErrorCodes.ERROR_SOCKET_TIMEOUT:
        return "ERROR_SOCKET_TIMEOUT";
      case ErrorCodes.ERROR_CONNECTION_ERROR:
        return "ERROR_CONNECTION_ERROR";
      case ErrorCodes.ERROR_TEST_INTERRUPTED:
        return "ERROR_TEST_INTERRUPTED";
      case ErrorCodes.ERROR_TEST_ALREADY_RUNNING:
        return "ERROR_TEST_ALREADY_RUNNING";
      case ErrorCodes.ERROR_WIFI_OFFLINE:
        return "ERROR_WIFI_OFFLINE";
      case ErrorCodes.ERROR_UNINITIAlIZED:
        return "ERROR_UNINITIAlIZED";
      case ErrorCodes.ERROR_PERFORMING_PING:
        return "ERROR_PERFORMING_PING";
      case ErrorCodes.ERROR_WIFI_IN_CAPTIVE_PORTAL:
        return "ERROR_WIFI_IN_CAPTIVE_PORTAL";
      case ErrorCodes.ERROR_WIFI_CONNECTED_AND_OFFLINE:
        return "ERROR_WIFI_CONNECTED_AND_OFFLINE";
      case ErrorCodes.ERROR_WIFI_CONNECTED_AND_UNKNOWN:
        return "ERROR_WIFI_CONNECTED_AND_UNKNOWN";
      case ErrorCodes.ERROR_WIFI_ON_AND_DISCONNECTED:
        return "ERROR_WIFI_ON_AND_DISCONNECTED";
      case ErrorCodes.ERROR_WIFI_OFF:
        return "ERROR_WIFI_OFF";
      case ErrorCodes.ERROR_WIFI_UNKNOWN_STATE:
        return "ERROR_WIFI_UNKNOWN_STATE";
      case ErrorCodes.ERROR_UNKNOWN:
      default:
        return "ERROR_UNKNOWN";
    }
  }

  public static String metricTypeToString(@MetricType int metricType) {
    switch(metricType) {
      case MetricType.EXCELLENT:
        return "EXCELLENT";
      case MetricType.AVERAGE:
        return "AVERAGE";
      case MetricType.GOOD:
        return "GOOD";
      case MetricType.POOR:
        return "POOR";
      case MetricType.ABYSMAL:
        return "ABYSMAL";
      case MetricType.NONFUNCTIONAL:
        return "NONFUNCTIONAL";
    }
    return "UNKNOWN";
  }

}

package ru.panmin.gtspro.utils;

public class Constants {

    public static final String DOMAIN = "217.151.77.93:58081";
    public static final String API_VERSION = "0";
    public static final String URL_REST = String.format("http://%s/api/v%s/", DOMAIN, API_VERSION);
    public static final String URL_WEB_SOCKET = String.format("ws://%s/ws/", DOMAIN);

    public static final String HEADER_AUTHORIZATION = "Authorization";
    public static final String TOKEN_TYPE_BEARER = "Bearer";

    public static final String DATE_TIME_FORMAT = "hh:MM:ss";

    public static final String WS_TYPE_USER_INFO = "user_info";
    public static final String WS_TYPE_ADDRESS_PROGRAM = "address_program";
    public static final String WS_TYPE_FORM = "form";
    public static final String WS_TYPE_FORM_FILLING_TIME = "form_filling_time";
    public static final String WS_TYPE_HOT_LINE = "hot_line";
    public static final String WS_TYPE_CLAIM = "claim";
    public static final String WS_TYPE_CLAIM_ANSWERED = "claim_answered";
    public static final String WS_TYPE_CLAIM_REDIRECT = "claim_redirect";
    public static final String WS_TYPE_START_VISIT = "start_visit";
    public static final String WS_TYPE_VISIT_ACCEPTED = "visit_accepted";
    public static final String WS_TYPE_END_VISIT = "end_visit";
    public static final String WS_TYPE_MERCHANDISER_VISITS = "merchandiser_visits";
    public static final String WS_TYPE_ERROR = "error";

    /* константы времени */
    public static final int SECOND = 1000;
    public static final int MINUTE = 60 * SECOND;
    public static final int HOUR = 60 * MINUTE;
    public static final int DAY = 24 * HOUR;
    public static final int WEEK = 7 * DAY;

    public static final String ROLE_MERCHANDISER = "me";
    public static final String ROLE_SUPERVISOR = "sv";

    public static final String LANGUAGE_RUSSIAN = "ru";
    public static final String LANGUAGE_ENGLISH = "en";

    public static final String SORT_TYPE_TIME = "sort.type.time";
    public static final String SORT_TYPE_DISTANCE = "sort.type.distance";
    public static final String SORT_TYPE_ALPHABET = "sort.type.alphabet";

    private Constants() {
    }

}
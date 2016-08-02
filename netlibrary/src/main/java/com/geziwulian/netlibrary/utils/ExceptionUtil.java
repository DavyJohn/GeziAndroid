package com.geziwulian.netlibrary.utils;

/**
 * Created by yyx on 16/3/2.
 */
public class ExceptionUtil {


        public static final int REQUEST_SUCCESS = 200;
        public static final int OBJECT_CREATE_SUCCESS = 201;
        public static final int ACCEPT_REQUEST = 202;
        public static final int REQUEST_SUCCESS_WITHOUT_CONTENT = 204;
        public static final int RESOURCES_MOVE_TO_NEW_LOCAL = 301;
        public static final int GET_RESOURCES_FORM_OTHER_URL_TEMPORARY = 302;
        public static final int SEE_OTHER_URL_BUT_CLIENT_USE_GET = 303;
        public static final int SEE_OTHER_URL_CLIENT_KEEP = 307;
        public static final int NOT_MODIFIED = 304;
        public static final int REQUEST_AND_RESOURCES_CONFLICT = 409;
        public static final int PRECONDITION_FAILED = 412;
        public static final int SYNTAX_ERROR = 400;
        public static final int NEED_VERIFICATION = 401;
        public static final int REFUSE_TO_EXECUTE = 403;
        public static final int NOT_FOUND = 404;
        public static final int DO_NOT_PERFORM_METHOD = 405;
        public static final int DO_NOT_SUPPORT_CONTENT_FORMAT = 406;
        public static final int RESOURCES_DELETE = 410;
        public static final int THE_REQUEST_IS_TOO_LARGE = 413;
        public static final int DO_NOT_SUPPORT_REQUESTING_DATA_FORMAT = 415;
        public static final int REQUEST_SUCCESS_WITH_MISTAKES = 422;
        public static final int NEED_PRECONDITION_REQUIRED = 428;
        public static final int SERVER_ERROR = 500;
        public static final int THE_SERVER_DOES_NOT_SUPPORT_THE_CURRENT_REQUEST = 501;
        public static final int AS_GATEWAY_THE_UPSTREAM_SERVER_RECEIVE_INVALID_RESPONSE = 502;
        public static final int SERVER_MAINTENANCE_AND_CON_NOT_HANDLE_THE_REQUEST = 503;

    public static final String NEED_VERIFICATION_MSG="请重新登录";
    public static final String SYNTAX_ERROR_MSG = "请求出错";

    /**
     * 返回状态码对应的中文翻译
     * @param code
     * @return msg
     */
    public static String throwExceptionMessage(int code){
        String msg = "";
        switch (code){
            case NEED_VERIFICATION:
                msg = NEED_VERIFICATION_MSG;
                break;
            case SYNTAX_ERROR:
                msg = SYNTAX_ERROR_MSG;
                break;
        }
        return msg;
    }
}

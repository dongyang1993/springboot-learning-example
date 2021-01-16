package org.springboot.security.common.api;


/**
 * @Author DongYang
 * @Description
 * @Date 2021/1/16 19:43
 **/
public enum RsCode {
    OK(200, "OK"),
    BAD_REQUEST(400, "Bad Request"),
    UNAUTHORIZED(401, "Unauthorized"),
    FORBIDDEN(403, "Forbidden"),
    NOT_FOUND(404, "Not Found"),
    METHOD_NOT_ALLOWED(405, "Method Not Allowed"),
    FAILED(500, "Failed");

    private final long code;
    private final String message;

    private RsCode(long code, String message) {
        this.code = code;
        this.message = message;
    }

    public long getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}

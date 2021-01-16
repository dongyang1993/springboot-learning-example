package org.springboot.security.common.api;

/**
 * @Author DongYang
 * @Description
 * @Date 2021/1/16 19:52
 **/
public class Rs<T> {
    private long code;
    private String message;
    private T data;

    protected Rs() {
    }

    protected Rs(long code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    /**
     * 成功返回结果
     *
     * @param data 获取的数据
     */
    public static <T> Rs<T> ok(T data) {
        return new Rs<T>(RsCode.OK.getCode(), RsCode.OK.getMessage(), data);
    }

    /**
     * 成功返回结果
     *
     * @param data    获取的数据
     * @param message 提示信息
     */
    public static <T> Rs<T> ok(T data, String message) {
        return new Rs<T>(RsCode.OK.getCode(), message, data);
    }

    /**
     * 失败返回结果
     */
    public static <T> Rs<T> failed(RsCode rsCode) {
        return new Rs<T>(rsCode.getCode(), rsCode.getMessage(), null);
    }

    /**
     * 失败返回结果
     *
     * @param message 提示信息
     */
    public static <T> Rs<T> failed(String message) {
        return new Rs<T>(RsCode.FAILED.getCode(), message, null);
    }

    /**
     * 失败返回结果
     */
    public static <T> Rs<T> failed() {
        return failed(RsCode.FAILED);
    }

    /**
     * 参数验证失败返回结果
     */
    public static <T> Rs<T> badRequest() {
        return failed(RsCode.BAD_REQUEST);
    }

    /**
     * 参数验证失败返回结果
     *
     * @param message 提示信息
     */
    public static <T> Rs<T> badRequest(String message) {
        return new Rs<T>(RsCode.BAD_REQUEST.getCode(), message, null);
    }

    /**
     * 未登录返回结果
     */
    public static <T> Rs<T> unauthorized(T data) {
        return new Rs<T>(RsCode.UNAUTHORIZED.getCode(), RsCode.UNAUTHORIZED.getMessage(), data);
    }

    /**
     * 未授权返回结果
     */
    public static <T> Rs<T> forbidden() {
        return new Rs<T>(RsCode.FORBIDDEN.getCode(), RsCode.FORBIDDEN.getMessage(), null);
    }

    public long getCode() {
        return code;
    }

    public void setCode(long code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}

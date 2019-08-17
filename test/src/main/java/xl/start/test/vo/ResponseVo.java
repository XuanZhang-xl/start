package xl.start.test.vo;

import xl.start.test.enums.ErrorCode;

/**
 * created by XUAN on 2019/05/18
 */
public class ResponseVo<T> {

    private String msg;

    private Integer code;

    private boolean success;

    private T data;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public static <T> ResponseVo buildSuccess(T data) {
        return new ResponseVo().success(data);
    }

    public static ResponseVo buildSuccess() {
        return new ResponseVo().success();
    }

    public static ResponseVo buildFail() {
        return new ResponseVo().fail();
    }

    public static ResponseVo buildFail(ErrorCode errorCode) {
        return new ResponseVo().fail(errorCode);
    }

    public ResponseVo success(T data) {
        this.setData(data);
        this.success = true;
        this.code = ErrorCode.SUCCESS.getCode();
        this.msg = ErrorCode.SUCCESS.getMsg();
        return this;
    }

    public ResponseVo success() {
        this.success = true;
        this.code = ErrorCode.SUCCESS.getCode();
        this.msg = ErrorCode.SUCCESS.getMsg();
        return this;
    }

    public ResponseVo fail() {
        this.success = false;
        this.code = ErrorCode.FAIL.getCode();
        this.msg = ErrorCode.FAIL.getMsg();
        return this;
    }

    public ResponseVo fail(ErrorCode errorCode) {
        this.success = false;
        this.code = errorCode.getCode();
        this.msg = errorCode.getMsg();
        return this;
    }
}

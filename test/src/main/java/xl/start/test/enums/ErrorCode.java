package xl.start.test.enums;

/**
 * created by XUAN on 2019/8/7
 */
public enum ErrorCode {

    SUCCESS(100000, "OK", "成功"),
    FAIL(999999, "ERROR", "失败"),

    ;

    Integer code;

    String msg;

    String info;

    ErrorCode(Integer code, String msg, String info) {
        this.code = code;
        this.msg = msg;
        this.info = info;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public String getInfo() {
        return info;
    }
}

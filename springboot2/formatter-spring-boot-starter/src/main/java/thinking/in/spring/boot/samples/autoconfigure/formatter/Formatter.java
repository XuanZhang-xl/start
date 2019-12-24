package thinking.in.spring.boot.samples.autoconfigure.formatter;

/**
 * created by XUAN on 2019/12/24
 */
public interface Formatter {

    /**
     * 格式化操作
     * @param object
     * @return
     */
    String format(Object object);
}

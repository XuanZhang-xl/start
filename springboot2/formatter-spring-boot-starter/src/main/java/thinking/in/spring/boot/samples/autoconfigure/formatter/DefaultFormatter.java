package thinking.in.spring.boot.samples.autoconfigure.formatter;

/**
 * created by XUAN on 2019/12/24
 */
public class DefaultFormatter implements Formatter {

    @Override
    public String format(Object object) {
        return String.valueOf(object);
    }
}

package thinking.in.spring.boot.samples.autoconfigure.formatter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * created by XUAN on 2019/12/24
 */
public class FastJsonFormatter implements Formatter {

    @Override
    public String format(Object object) {
        return JSON.toJSONString(object);
    }
}

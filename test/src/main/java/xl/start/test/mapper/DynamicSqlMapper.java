package xl.start.test.mapper;

import xl.start.test.dto.ElasticSync;

import java.util.List;
import java.util.Map;

/**
 * created by XUAN on 2019/9/25
 */
public interface DynamicSqlMapper {

    /**
     * 测试动态sql
     */
    List<Map<String, Object>> dynamicSqlConcat(ElasticSync sync);



}

package xl.start.test.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xl.start.test.dto.ElasticSync;
import xl.start.test.dto.FormatPairDate;
import xl.start.test.dto.PairDate;
import xl.start.test.mapper.DynamicSqlMapper;
import xl.start.test.vo.ResponseVo;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 动态sql测试
 * created by XUAN on 2019/9/25
 */
@RestController
@RequestMapping("/dynamicSql")
public class DynamicSqlController {

    @Resource
    DynamicSqlMapper dynamicSqlMapper;

    @RequestMapping("/concat")
    public ResponseVo dynamicSql () {
        try {
            ElasticSync sync = new ElasticSync();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
            Date begin = sdf.parse("20190608");
            Date end1 = sdf.parse("20190614");
            Date end2 = sdf.parse("20190617");
            Date end3 = sdf.parse("20190619");
            List<PairDate> pairDates = new ArrayList<>();
            pairDates.add(new PairDate(begin, end1));
            pairDates.add(new PairDate(begin, end2));
            pairDates.add(new PairDate(begin, end3));
            sync.setPairDate(pairDates);

            List<FormatPairDate> formatPairDates = new ArrayList<>();
            //formatPairDates.add(new FormatPairDate("20190608", "20190614"));
            formatPairDates.add(new FormatPairDate("20190608", "20190617"));
            formatPairDates.add(new FormatPairDate("20190608", "20190619"));
            sync.setFormatPairDate(formatPairDates);

            List<Map<String, Object>> dynamicSqlConcat = dynamicSqlMapper.dynamicSqlConcat(sync);
            return ResponseVo.buildSuccess(dynamicSqlConcat);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseVo.buildFail(e.getMessage());
        }
    }
}

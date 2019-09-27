package xl.start.test.dto;

import java.util.Date;

/**
 * 自定义日期对
 * created by XUAN on 2019/9/23
 */
public class FormatPairDate {

    // 起始日期
    private String beginDate;

    // 结束日期
    private String endDate;

    public FormatPairDate(String beginDate, String endDate) {
        this.beginDate = beginDate;
        this.endDate = endDate;
    }

    public String getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(String beginDate) {
        this.beginDate = beginDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
}

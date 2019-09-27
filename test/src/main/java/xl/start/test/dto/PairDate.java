package xl.start.test.dto;

import java.util.Date;

/**
 * 自定义日期对
 * created by XUAN on 2019/9/23
 */
public class PairDate {

    // 起始日期
    private Date beginDate;

    // 结束日期
    private Date endDate;

    public PairDate(Date beginDate, Date endDate) {
        this.beginDate = beginDate;
        this.endDate = endDate;
    }

    public Date getBeginDate() {
        return beginDate;
    }

    public Date getEndDate() {
        return endDate;
    }
}

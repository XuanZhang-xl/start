package xl.start.test.dto;

import java.util.Date;
import java.util.List;


/**
 * elasticSync
 * @author gsh11043
 */
public class ElasticSync {

    private static final long serialVersionUID = 7956403206158898480L;

    private Long shopId; //店铺id
    
    private List<PairDate> pairDate;

    private List<FormatPairDate> formatPairDate;


    public Long getShopId() {
        return shopId;
    }

    
    public void setShopId(Long shopId){
        this.shopId = shopId;
    }


    public List<PairDate> getPairDate() {
        return pairDate;
    }

    public void setPairDate(List<PairDate> pairDate) {
        this.pairDate = pairDate;
    }

    public List<FormatPairDate> getFormatPairDate() {
        return formatPairDate;
    }

    public void setFormatPairDate(List<FormatPairDate> formatPairDate) {
        this.formatPairDate = formatPairDate;
    }
}
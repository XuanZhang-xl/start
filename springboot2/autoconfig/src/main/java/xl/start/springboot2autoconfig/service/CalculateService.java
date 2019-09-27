package xl.start.springboot2autoconfig.service;

/**
 * 计算服务
 *
 * @author XUAN
 * @since 2019/09/22
 */
public interface CalculateService {

    /**
     * 累加
     *
     * @param sum 输入的整数
     * @return 输入整数的和
     */
    Integer sum(Integer... sum);
}

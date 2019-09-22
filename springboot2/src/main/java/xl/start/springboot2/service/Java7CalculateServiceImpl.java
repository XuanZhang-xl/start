package xl.start.springboot2.service;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

/**
 * java7实现 {@link CalculateService}
 * @author XUAN
 * @since 2019/09/22
 */
@Service
@Profile("Java7")
public class Java7CalculateServiceImpl implements CalculateService {
    @Override
    public Integer sum(Integer... sum) {
        int result = 0;
        for (int i = 0; i < sum.length; i++) {
            result += sum[i];
        }
        return result;
    }
}

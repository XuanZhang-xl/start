package xl.start.springboot2autoconfig.service;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.stream.Stream;

/**
 * java7实现 {@link CalculateService}
 *
 * @author XUAN
 * @since 2019/09/22
 */
@Service
@Profile("Java8")
public class Java8CalculateServiceImpl implements CalculateService {
    @Override
    public Integer sum(Integer... sum) {
        return Stream.of(sum).reduce(0, Integer::sum);
    }
}

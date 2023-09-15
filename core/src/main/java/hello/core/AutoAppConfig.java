package hello.core;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(
        // 컴포넌트 스캔을 사용하면 @Configuration이 붙은 설정 정보도 자동 등록이 되기 때문에,
        // 필터를 이용하여 해당 설정 정보는 컴포넌트 스캔 대상에서 제외했다.
        // 보통 설정 정보를 컴포넌트 스캔 대상에서 제외 하지는 않지만, 기존 예제 코드를 최대한 유지하기 위해 이 방법을 선택했다.
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
)
public class AutoAppConfig {

}

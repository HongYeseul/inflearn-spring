package hello.core.lifecycle;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class BeanLifeCycleTest {

    // 싱글톤으로 이뤄진 빈들의 생명주기
    // 스프링 컨테이너 생성 -> 스프링 빈 생성 -> 의존관계 주입 -> 초기화 콜백 -> 사용 -> 소멸전 콜백 -> 스프링 종료
    // 초기화 콜백: 빈이 생성되고, 빈의 의존관계 주입이 완료된 후 호출
    // 소멸전 콜백: 빈이 소멸되기 직전에 호출
    // 스프링은 다양한 방식으로 생명주기 콜백을 지원한다.

    @Test
    public void lifeCycleTest(){
        ConfigurableApplicationContext ac = new AnnotationConfigApplicationContext(LifeCycleConfig.class);
        NetworkClient client = ac.getBean(NetworkClient.class);
        ac.close();
    }

    @Configuration
    static class LifeCycleConfig{

        // destroyMethod에는 특별한 기능이 있다.
        // 라이브러리는 대부분 'close'나 'shutdown' 이라는 이름의 종료 메서드를 사용한다.
        // @Bean의 `destroyMethod` 는 기본값이 `(inferred)` (추론)으로 등록되어 있다.
        // 이 추론 기능은 `close` , `shutdown` 라는 이름의 메서드를 자동으로 호출해준다. 이름 그대로 종료 메서드를 추 론해서 호출해준다.
        // 따라서 직접 스프링 빈으로 등록하면 종료 메서드는 따로 적어주지 않아도 잘 동작한다.
        // 추론 기능을 사용하기 싫으면 `destroyMethod=""` 처럼 빈 공백을 지정하면 된다.
        @Bean(initMethod = "init", destroyMethod = "close")
        public NetworkClient networkClient(){
            NetworkClient networkClient = new NetworkClient();
            networkClient.setUrl("http://hello-spring.dev");
            return networkClient;
        }
    }
}

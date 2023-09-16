package hello.core;

import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import org.springframework.context.annotation.Bean;
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

    // 원래 수동 빈 등록과 자동 빈 등록에서 빈 이름이 충돌 되면 오류가 나는데,
    // 해당 경우에는 수동 빈 등록이 우선권을 가진다(수동빈이 자동빈을 오버라이딩 한다)
    // 하지만 개발자가 의도적으로 설정해서 이런 결과가 만들어지기 보다는 여러 설정들이 꼬여서 이런 결과가 만들어지는 경우가 대부분이다.
    // 때문에 최근 스프링부트에서는 수동 빈 등록과 자동 빈 등록이 충돌나면 오류가 발생하도록 기본 값을 바꾸었다.(-> CoreApplication 실행 시 오류)
//    @Bean(name = "memoryMemberRepository")
//    public MemberRepository memberRepository(){
//        return new MemoryMemberRepository();
//    }

}

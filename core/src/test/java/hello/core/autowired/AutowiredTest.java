package hello.core.autowired;

import hello.core.member.Member;
import jakarta.annotation.Nullable;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Optional;

public class AutowiredTest {
    @Test
    void AutowiredOption(){
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestBean.class); //스프링빈 등록
    }

    static class TestBean{

        @Autowired(required = false) //required false는 의존관계가 없다면 아예 해당 메소드 자체가 호출이 안됨.
        public void setNoBean1(Member noBean1){ // member는 스프링빈이 관리하는 빈이 아님.(스프링컨테이너가 관리하지않음)
            System.out.println("noBean1 = " + noBean1);
        }

        @Autowired
        public void setNoBean2(@Nullable Member noBean2){ // 호출은 되나 null로 들어감
            System.out.println("noBean2 = " + noBean2);
        }

        @Autowired
        public void setNoBean3(Optional<Member> noBean3){ // 호출은 되나 Optional.empty
            System.out.println("noBean3 = " + noBean3);
        }
    }
}

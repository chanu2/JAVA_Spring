package hello.hellospring;

import hello.hellospring.aop.TimeTraceAop;
import hello.hellospring.repository.JpaMemberRepository;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {
/*
    private EntityManager em;    // jpa 사용할 때

    @Autowired
    public SpringConfig(EntityManager em){
        this.em=em;
    }
*/
    private final MemberRepository memberRepository;  //spring data jpa 사용법
    @Autowired
    public SpringConfig(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /*    private DataSource dataSource;

        @Autowired
        public SpringConfig(DataSource dataSource){
            this.dataSource=dataSource;
        }
    */
    @Bean  // 스피링빈에 직접등록
    public MemberService memberService(){

        return new MemberService(memberRepository);
    }

//    @Bean
//    public MemberRepository memberRepository(){
        //return new MemoryMemberRepository();
        //return new JdbcMemberRepository(dataSource);
        //return new JdbcTemplateMemberRepository(dataSource);
//        return new JpaMemberRepository(em);
//    }

/*    @Bean
    public TimeTraceAop timeTraceAop(){
        return new TimeTraceAop();
    }    이런식으로 따로 bean해서 사용하면 알기쉽다
*/
}

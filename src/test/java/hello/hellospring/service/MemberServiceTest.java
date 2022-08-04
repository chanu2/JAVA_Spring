package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.assertj.core.api.Assertions.assertThat; // 추가해주기
import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {

    MemberService memberService;
    //MemoryMemberRepository memberRepository = new MemoryMemberRepository();  // 또 다시 객체를 생성했기 떄문에 MemoryMemberRepository에서 stroe가 static으로 선언되었기 때문에 상관없지만 만약 아니라면 서로다른 객체에이다. 그렇기 때문에 같은 리퍼지토리를 사용해야한다
    MemoryMemberRepository memberRepository;

    @BeforeEach
    public void beforeEach(){
        memberRepository = new MemoryMemberRepository();     //DI라고 한다 디펜던트 인잭션
        memberService = new MemberService(memberRepository);


    }

    @AfterEach   // 각각의 테스트 실행전에 실행
    public void afterEach(){
        memberRepository.clearStore();
    }

    @Test    //테스트는 독립적으로 실행되야한다
    void 회원가입() {
        //given 주어진거
        Member member = new Member();
        member.setName("hello");
        //when 실행 했을떄
        Long saveId = memberService.join(member);

        //then  나올 값
        Member findMember = memberService.findOne(saveId).get(); // 겟하면 옵셔널 사라진다
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }
    @Test
    public void 중복회원예외(){
        //given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");
        //when
        memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원");
/*
        try{
            memberService.join(member1);
            fail();
        }catch (IllegalStateException e){
            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원");
        }
*/



        //then
    }

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}
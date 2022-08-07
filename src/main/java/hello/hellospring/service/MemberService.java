package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
//@Service 를 해주면 스프링 컨테이너에 memberService가 등록됨 그리고  MemberRepository도 memberService에 등록이 필요하기 떄문에 컨테이너에 등록을 해야하고
@Transactional  // jpa 쓰려면 항상 있어야 한다 데이터를 저장하거나 변경할때 꼭 있어야한다
public class MemberService {  // 단지 순수한 자바코드이기 때문에 @Service

    private final MemberRepository memberRepository;

    //@Autowired 를 통해서 memberService와 MemberRepository를 연결한다.
    public MemberService(MemberRepository memberRepository) {  // 외부에서 넣어주도록 선언한다
        this.memberRepository = memberRepository;
    }

    //회원 가입
    public Long join(Member member){

        // 같은이름 중복회원 불가
        validateDuplicateMember(member);  // 중복 회원 검증
        memberRepository.save(member);
        return member.getId();

    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                .ifPresent(m -> {throw new IllegalStateException("이미 존재하는 회원"); });
    }

    //전체 회원 조회
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId){
        return memberRepository.findById(memberId);
    }
}

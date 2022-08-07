package hello.hellospring.controller;

import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller  // 스프링이 컨테이너에 등록한다.
public class MemberController {
    private final MemberService memberService;  // new로 생성해서 사용할 수도 있지만 memberService를  ordercontroller등 다른 컨트롤러도 가져다 사용해야 하기 때문에

    @Autowired   // 멤버 서비스를 스트링이 스프링 컨테이너에 있는 멤버서비스를 가져다가 연결해준다  그렇지만 memberService는 단지 spring이 접근하지 못한 순수한 자바 코드이기 때문에 스프링 컨테이너에 등록헤 줘야한다 memberService로 이동
    public MemberController(MemberService memberService){
        this.memberService=memberService;
    }

    @GetMapping("/members/new")
    public String createFrom(){
        return "members/createMemberFrom";
    }

    @PostMapping("/members/new")
    public String create(MemberForm form){
        Member member = new Member();
        member.setName(form.getName());

        memberService.join(member);
        return "redirect:/"; //홈화면으로 돌리기
    }

    @GetMapping("/members")
    public String list(Model model){
        List<Member> members = memberService.findMembers();
        model.addAttribute("members",members);
        return "members/memberList";


    }
}

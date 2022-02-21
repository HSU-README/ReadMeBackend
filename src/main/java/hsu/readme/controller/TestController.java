package hsu.readme.controller;

import hsu.readme.Repository.MemberRepository;
import hsu.readme.domain.Member;
import hsu.readme.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TestController {

    private final MemberService memberService;
    private final MemberRepository memberRepository;

    @GetMapping("/test")
    public String test(){
        Member member = new Member();
        member.setName("Jgeun");
        Long savedId = memberService.join(member);
        Member savedMember = memberRepository.findOne(savedId);

        return "memberService: " + savedId + " name: " + member.getName() + "\n " +
                "memberRepository:  " + savedMember.getId() + " name: " +savedMember.getName();
    }
}

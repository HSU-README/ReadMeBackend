package hsu.readme.api.member;

import hsu.readme.api.Response;
import hsu.readme.api.ResponseMessage;
import hsu.readme.domain.Member;
import hsu.readme.service.MemberService;
import lombok.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.util.List;

import static hsu.readme.api.ResponseMessage.*;

@RestController
@RequiredArgsConstructor
public class MemberApiController {

    private final MemberService memberService;

    @PostMapping("/api/v1/members/new")
    @ResponseBody
    public Response saveMemberV1(@RequestBody @Valid CreateMemberRequest request){
        Member member = new Member();
        member.setName(request.getName());
        member.setEmail(request.getEmail());
        member.setPassword(request.getPassword());
        member.setUniversity(request.getUniversity());
        member.setMajor(request.getMajor());

        Long savedId = memberService.join(member);
        return Response.response("S200", CREATED_USER, new CreateMemberResult(savedId));
    }

    @PostMapping("/api/v1/members/login")
    @ResponseBody
    public Response loginMemberV1(@RequestBody @Valid LoginMemberRequest request){
        Long savedId = memberService.login(request.getEmail(), request.getPassword());
        return Response.response("S200", LOGIN_SUCCESS, new CreateMemberResult(savedId));
    }

    @GetMapping("/api/v1/members")
    public Response memberInfoV1(@RequestParam Long id){
        Member member = memberService.findOne(id);
        return Response.response("S200", MEMBER_INFO_SUCCESS,
                new GetMemberResult(member.getName(), member.getProfileUrl(), member.getUniversity(), member.getMajor(), member.getInterest()));
    }
}

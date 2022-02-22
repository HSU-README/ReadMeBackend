package hsu.readme.api.member;

import hsu.readme.api.Response;
import hsu.readme.api.ResponseMessage;
import hsu.readme.domain.Member;
import hsu.readme.service.MemberService;
import lombok.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static hsu.readme.api.ResponseMessage.*;

@RestController
@RequiredArgsConstructor
public class MemberApiController {

    private final MemberService memberService;

    @PostMapping("/api/v1/members")
    @ResponseBody
    public Response saveMemberV1(@RequestBody @Valid CreateMemberRequest request){
        Member member = new Member();
        member.setName(request.getName());
        member.setEmail(request.getEmail());
        member.setPassword(request.getPassword());

        Long savedId = memberService.join(member);
        return Response.response("S200", CREATED_USER, new CreateMemberResult(savedId));
    }
}

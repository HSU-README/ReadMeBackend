package hsu.readme.api.member;

import hsu.readme.api.Response;
import hsu.readme.api.component.DocInfoDto;
import hsu.readme.api.document.DocPreviewInfoDto;
import hsu.readme.domain.Document;
import hsu.readme.domain.Member;
import hsu.readme.service.DocumentService;
import hsu.readme.service.MemberService;
import lombok.*;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.util.ArrayList;
import java.util.List;

import static hsu.readme.api.ResponseMessage.*;

@RestController
@RequiredArgsConstructor
public class MemberApiController {

    private final MemberService memberService;
    private final DocumentService documentService;

    @PostMapping("/api/v1/members/new")
    @ResponseBody
    public Response saveMemberV1(@RequestBody @Valid CreateMemberRequest request){
        Member member = new Member();
        member.setName(request.getName());
        member.setEmail(request.getEmail());
        member.setPassword(request.getPassword());
        member.setUniversity(request.getUniversity());
        member.setMajor(request.getMajor());
        member.setProfileUrl("");
        member.setInterests("");

        Long savedId = memberService.join(member);
        return Response.response("S200", CREATED_USER, new MemberResult(savedId));
    }

    @PostMapping("/api/v1/members/login")
    @ResponseBody
    public Response loginMemberV1(@RequestBody @Valid LoginMemberRequest request){
        Long savedId = memberService.login(request.getEmail(), request.getPassword());
        Member member = memberService.findOne(savedId);
        return Response.response("S200", LOGIN_SUCCESS, new LoginMemberResult(savedId, member.getName(), member.getMajor()));
    }

    @GetMapping("/api/v1/members/{id}")
    public Response memberInfoV1(@PathVariable Long id){
        Member member = memberService.findOne(id);
        return Response.response("S200", MEMBER_INFO_SUCCESS,
                new GetMemberResult(member.getId(), member.getName(), member.getProfileUrl(), member.getUniversity(), member.getMajor(), member.getInterests()));
    }

    @PutMapping("/api/v1/members/{id}")
    public Response putMemberInfoV1(@PathVariable Long id, @RequestBody @Valid PutMemberRequest request){
        memberService.update(id, request.getName(), request.getProfileUrl(), request.getUniversity(), request.getMajor(), request.getInterests());
        Member member = memberService.findOne(id);
        return Response.response("S200", PUT_MEMBER_INFO_SUCCESS,
                new GetMemberResult(member.getId(), member.getName(), member.getProfileUrl(), member.getUniversity(), member.getMajor(), member.getInterests()));
    }

    @GetMapping("/api/v1/member/{id}/docs/like")
    public Response getMemberLikeDocsV1(@PathVariable Long id) {
        List<Document> docs = documentService.findDocsWithMemberLikes(id);
        List<DocPreviewInfoDto> docInfoDtos = new ArrayList<>();
        for(Document doc : docs) {
            DocPreviewInfoDto previewInfoDto = new DocPreviewInfoDto(doc);
            docInfoDtos.add(previewInfoDto);
        }
        return Response.response("S200", DOC_INFO_SUCCESS, docInfoDtos);
    }
}
package hsu.readme.api.home;

import hsu.readme.api.Response;
import hsu.readme.api.document.DocPreviewInfoDto;
import hsu.readme.domain.*;
import hsu.readme.service.AdService;
import hsu.readme.service.DocumentService;
import hsu.readme.service.MemberService;
import hsu.readme.service.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static hsu.readme.api.ResponseMessage.DOC_INFO_SUCCESS;
import static hsu.readme.api.ResponseMessage.HOME_INFO_SUCCESS;

@RestController
@RequiredArgsConstructor
public class HomeApiController {

    private final MemberService memberService;
    private final DocumentService documentService;
    private final TagService tagService;
    private final AdService adService;

    //홈 전체 게시글
    @GetMapping("/api/v1/home/docs/all")
    public Response homeDocsV1(@RequestParam(value = "start", defaultValue = "0") int start,
                               @RequestParam(value = "limit", defaultValue = "8") int limit) {
        List<Document> documentsSortedByDocDate = documentService.findDocumentsSortedByDocDate(start, limit);
        List<DocPreviewInfoDto> docs = documentsSortedByDocDate.stream().map(DocPreviewInfoDto::new)
                .collect(Collectors.toList());
        return Response.response("S200", DOC_INFO_SUCCESS, docs);
    }

    //홈 인기 게시글
    @GetMapping("/api/v1/home/docs/mostLike")
    public Response homeDocsMostLikeV1(@RequestParam(value = "start", defaultValue = "0") int start,
                                       @RequestParam(value = "limit", defaultValue = "8") int limit) {
        List<Document> documentsByLikeDesc = documentService.findDocumentsByLikeDesc(start, limit);
        List<DocPreviewInfoDto> docs = documentsByLikeDesc.stream().map(DocPreviewInfoDto::new)
                .sorted(Comparator.comparingInt(DocPreviewInfoDto::getLikeCnt)
                .reversed())
                .collect(Collectors.toList());
        return Response.response("S200", DOC_INFO_SUCCESS, docs);
    }

    //홈 유저 전공 관련 게시글
    @GetMapping("/api/v1/home/docs/memberMajor")
    public Response homeDocsMemberMajorV1(@RequestParam @Valid HomeMajorRequest request,
                                          @RequestParam(value = "start", defaultValue = "0") int start,
                                          @RequestParam(value = "limit", defaultValue = "8") int limit) {
        Member member = memberService.findOne(request.getMemberId());
        List<Document> documentsByLikeDesc = documentService.findDocsWithMemberMajor(member.getMajor(), start, limit);
        List<DocPreviewInfoDto> docs = documentsByLikeDesc.stream().map(DocPreviewInfoDto::new)
                .collect(Collectors.toList());
        return Response.response("S200", DOC_INFO_SUCCESS, docs);
    }

    //홈 유저 전공 = 게시글 전공 호출
    @GetMapping("/api/v1/home/docs/major")
    public Response homeDocsMajorV1(@RequestParam(value="memberId") long memberId,
                                    @RequestParam(value = "start", defaultValue = "0") int start,
                                    @RequestParam(value = "limit", defaultValue = "8") int limit) {
        Member member = memberService.findOne(memberId);
        List<Document> documentsByLikeDesc = documentService.findDocsWithMajor(member.getMajor(), start, limit);
        List<DocPreviewInfoDto> docs = documentsByLikeDesc.stream().map(DocPreviewInfoDto::new)
                .collect(Collectors.toList());
        return Response.response("S200", DOC_INFO_SUCCESS, docs);
    }

/*
    private void makeDocuments() {
        Member member = new Member();
        member.setName("designer");
        member.setMajor("major");
        member.setEmail("user@user.com");
        member.setPassword("user");
        member.setUniversity("user");

        memberService.join(member);

        Member findMember = memberService.findOne(member.getId());

        for(int i=0; i<10; i++) {
            Document document = new Document();
            Tag tag = new Tag();
            tag.setName("IT"+i);
            tagService.join(tag);

            Tag tag1 = new Tag();
            tag1.setName("IT_add_"+i);
            tagService.join(tag1);

            Tag findTag = tagService.findOne(tag.getId());
            Tag findTag1 = tagService.findOne(tag1.getId());

            document.setTitle("test"+i);
            document.setLikeCnt(i);
            document.getTags().add(findTag);
            document.setMember(findMember);

            if (i % 2 == 0) {
                document.setStatus(DocumentStatus.WRITE);
            } else {
                document.setStatus(DocumentStatus.FINISH);
            }
            findTag.setDocument(document);
            findTag1.setDocument(document);
            documentService.join(document);
        }
    }

    private void makeAds() {
        for(int i=0; i<4; i++) {
            Adv ad = new Adv();
            ad.setImgUrl("test"+i);
            adService.join(ad);
        }
    }*/
}

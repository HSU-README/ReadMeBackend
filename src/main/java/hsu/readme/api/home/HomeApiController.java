package hsu.readme.api.home;

import hsu.readme.api.Response;
import hsu.readme.domain.*;
import hsu.readme.service.AdService;
import hsu.readme.service.DocumentService;
import hsu.readme.service.MemberService;
import hsu.readme.service.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

import static hsu.readme.api.ResponseMessage.HOME_INFO_SUCCESS;

@RestController
@RequiredArgsConstructor
public class HomeApiController {

    private final MemberService memberService;
    private final DocumentService documentService;
    private final TagService tagService;
    private final AdService adService;

    @GetMapping("/api/v1/home")
    @ResponseBody
    public Response homeInfoV1(){
        makeAds();
        makeDocuments();

        List<Adv> ads = adService.findAds();
        List<Document> findDocuments = documentService.findDocumentsByLikeDesc(5);

        List<HomeInfoDocDto> topDocuments = findDocuments.stream()
                .map(HomeInfoDocDto::new)
                .collect(Collectors.toList());
        return Response.response("S200", HOME_INFO_SUCCESS, new HomeInfoResult(ads, topDocuments));
    }

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
    }
}

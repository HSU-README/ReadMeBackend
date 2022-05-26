package hsu.readme.api.test;

import hsu.readme.Repository.ComponentRepository;
import hsu.readme.Repository.DocComponentRepository;
import hsu.readme.api.Response;
import hsu.readme.domain.DocComponent;
import hsu.readme.domain.Document;
import hsu.readme.domain.Member;
import hsu.readme.domain.component.Component;
import hsu.readme.service.ComponentService;
import hsu.readme.service.DocComponentService;
import hsu.readme.service.DocumentService;
import hsu.readme.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static hsu.readme.api.ResponseMessage.DOC_INFO_SUCCESS;

@RestController
@RequiredArgsConstructor
public class TestApiController {

    private final DocumentService documentService;
    private final ComponentService componentService;
    private final MemberService memberService;
    private final DocComponentService docComponentService;

    @GetMapping("/important/docs/remove")
    public Response test(){
        List<DocComponent> dcs = docComponentService.findAll();
        for(DocComponent dc : dcs) {
            docComponentService.delete(dc);
        }

        List<Component> coms = componentService.findComponents();
        for (Component com : coms) {
            componentService.remove(com);
        }

        List<Document> docs = documentService.findDocuments();
        for(Document d : docs) {
            documentService.deleteDocument(d);
        }

        List<Member> members = memberService.findAll();
        for(Member m : members) {
            memberService.remove(m);
        }

        return Response.response("S200", "다 삭제됨 ㅋㅋ", 1);
    }

}

package hsu.readme.api.document;

import hsu.readme.api.Response;
import hsu.readme.api.home.HomeInfoResult;
import hsu.readme.api.member.CreateMemberRequest;
import hsu.readme.domain.Document;
import hsu.readme.service.ComponentService;
import hsu.readme.service.DocumentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.print.Doc;
import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

import static hsu.readme.api.ResponseMessage.DOC_CREATE_SUCCESS;
import static hsu.readme.api.ResponseMessage.DOC_INFO_SUCCESS;

@RestController
@RequiredArgsConstructor
public class DocApiController {

    private final DocumentService documentService;
    private final ComponentService componentService;

    @GetMapping("/api/v1/doc/{id}/preview")
    public Response docPreviewV1(@PathVariable Long id) { //doc id 보내줌
        Document document = documentService.findDocumentWithMember(id);
        return Response.response("S200", DOC_INFO_SUCCESS, new DocInfoDto(document));
    }

    @GetMapping("/api/v1/doc/{id}")
    public Response docInfoV1(@PathVariable Long id) {
        Document document = documentService.findOne(id);
        return Response.response("S200", DOC_INFO_SUCCESS, new DocInfoDto(document));
    }

    @GetMapping("/api/v1/docall")
    public Response docInfoV1() {
        List<Document> findDocuments = documentService.findDocuments();

        List<DocInfoDto> docs = findDocuments.stream()
                .map(DocInfoDto::new)
                .collect(Collectors.toList());
        return Response.response("S200", DOC_INFO_SUCCESS, docs);
    }

    @GetMapping("/api/v1/doc/new")
    public Response makeDocV1() {
        Document document = new Document();
        documentService.join(document);

        Document findDoc = documentService.findOne(document.getId());
        return Response.response("S200", DOC_CREATE_SUCCESS, new CreateDocResponse(findDoc.getId()));
    }

    //여기까지 진행 하고 테이블 수정들어가야함.
//    @PostMapping("/api/v1/doc/edit/{id}")
//    public Response storeDocComponent() {
//
//    }
}

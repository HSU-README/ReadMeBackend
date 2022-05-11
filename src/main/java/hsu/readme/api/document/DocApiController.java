package hsu.readme.api.document;

import hsu.readme.api.Response;
import hsu.readme.api.component.DocInfoDto;
import hsu.readme.domain.Document;
import hsu.readme.service.DocumentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static hsu.readme.api.ResponseMessage.*;

@RestController
@RequiredArgsConstructor
public class DocApiController {

    private final DocumentService documentService;

    @GetMapping("/api/v1/doc/{id}/preview")
    public Response docPreviewV1(@PathVariable Long id) {
        Document document = documentService.findDocumentWithMember(id);
        return Response.response("S200", DOC_INFO_SUCCESS, new DocInfoDto(document));
    }

    @GetMapping("/api/v1/user/{memberId}/docs")
    public Response userDocsV1(@PathVariable Long memberId) {
        List<Document> memberDocs = documentService.findMemberDocs(memberId);

        List<MemberDocsDto> docs = memberDocs.stream()
                .map(MemberDocsDto::new)
                .collect(Collectors.toList());
        return Response.response("S200", DOC_INFO_SUCCESS, docs);
    }

    @GetMapping("/api/v1/docall")
    public Response docInfoV1() {
        List<Document> findDocuments = documentService.findDocuments();

        List<DocInfoDto_legacy> docs = findDocuments.stream()
                .map(DocInfoDto_legacy::new)
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

    @PostMapping("/api/v1/doc/delete/{docId}")
    public Response deleteDoc(@PathVariable Long docId) {
        Document document = documentService.findOne(docId);
        documentService.deleteDocument(document);
        return Response.response("S200", DOC_DELETE_SUCCESS, docId);
    }
}

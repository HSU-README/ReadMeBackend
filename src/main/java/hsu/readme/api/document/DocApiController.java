package hsu.readme.api.document;

import hsu.readme.api.Response;
import hsu.readme.api.home.HomeInfoResult;
import hsu.readme.domain.Document;
import hsu.readme.service.DocumentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

import static hsu.readme.api.ResponseMessage.DOC_INFO_SUCCESS;
import static hsu.readme.api.ResponseMessage.HOME_INFO_SUCCESS;

@RestController
@RequiredArgsConstructor
public class DocApiController {

    private final DocumentService documentService;

    @GetMapping("/api/v1/doc/{id}/preview")
    public Response docPreviewV1(@PathVariable Long id){ //doc id 보내줌
        List<Document> documents = documentService.findDocumentWithMember(id);
        List<DocInfoDto> doc = documents.stream()
                .map(DocInfoDto::new)
                .collect(Collectors.toList());
        return Response.response("S200", DOC_INFO_SUCCESS, doc);
    }

    @GetMapping("/api/v1/doc/{id}")
    public Response docInfoV1(@PathVariable Long id){
        Document document = documentService.findOne(id);
        return Response.response("S200", DOC_INFO_SUCCESS, new DocInfoDto(document));
    }

    @GetMapping("/api/v1/docall")
    public Response docInfoV1(){
        List<Document> findDocuments = documentService.findDocuments();

        List<DocInfoDto> docs = findDocuments.stream()
                .map(DocInfoDto::new)
                .collect(Collectors.toList());
        return Response.response("S200", DOC_INFO_SUCCESS, docs);
    }
}

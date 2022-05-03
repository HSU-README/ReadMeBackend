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

import static hsu.readme.api.ResponseMessage.HOME_INFO_SUCCESS;

@RestController
@RequiredArgsConstructor
public class DocApiController {

    private final DocumentService documentService;

    @GetMapping("/api/v1/{id}/preview")
    public Response docPreviewV1(@PathVariable Long id){ //doc id 보내줌
        List<Document> documents = documentService.findDocumentWithMember(id);
        return Response.response("S200", HOME_INFO_SUCCESS, documents);
    }


}

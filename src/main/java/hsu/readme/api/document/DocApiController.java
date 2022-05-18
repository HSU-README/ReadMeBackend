package hsu.readme.api.document;

import hsu.readme.api.Response;
import hsu.readme.api.component.DocInfoDto;
import hsu.readme.domain.Document;
import hsu.readme.domain.Like;
import hsu.readme.domain.Member;
import hsu.readme.service.DocumentService;
import hsu.readme.service.LikeService;
import hsu.readme.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

import static hsu.readme.api.ResponseMessage.*;

@RestController
@RequiredArgsConstructor
public class DocApiController {

    private final MemberService memberService;
    private final DocumentService documentService;
    private final LikeService likeService;

    @GetMapping("/api/v1/doc/{id}/preview")
    public Response docPreviewV1(@PathVariable Long id) {
        Document document = documentService.findDocumentWithMember(id);
        List<Like> likes = likeService.findWithDoc(document);
        DocInfoDto docInfoDto = new DocInfoDto(document, likes);
        return Response.response("S200", DOC_INFO_SUCCESS, docInfoDto);
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

    @PostMapping("/api/v1/doc/like")
    public Response likeDoc(@RequestBody @Valid LikeRequestDto likeRequestDto) {
        Member findMember = memberService.findOne(likeRequestDto.getMemberId());
        Document findDoc = documentService.findOne(likeRequestDto.getDocId());
        Long createdLike = likeService.createLike(findMember, findDoc);
        return Response.response("S200", DOC_LIKE_SUCCESS, findMember.getId());
    }

    @PostMapping("/api/v1/doc/unlike")
    public Response unlikeDoc(@RequestBody @Valid LikeRequestDto likeRequestDto) {
        Member findMember = memberService.findOne(likeRequestDto.getMemberId());
        Document findDoc = documentService.findOne(likeRequestDto.getDocId());
        Like findLike = likeService.findWithDocAndMember(findMember, findDoc);
        if(findLike == null)
            return Response.response("E200", DOC_LIKE_NOT_EXIST, likeRequestDto);
        else {
            likeService.delete(findLike);
            return Response.response("S200", DOC_UNLIKE_SUCCESS, likeRequestDto);

        }
    }
}

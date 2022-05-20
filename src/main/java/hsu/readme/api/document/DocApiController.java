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

    //문서 미리보기 API
    @GetMapping("/api/v1/doc/{id}/preview")
    public Response docPreviewV1(@PathVariable Long id) {
        Document document = documentService.findDocumentWithMember(id);
        DocPreviewInfoDto previewInfoDto = new DocPreviewInfoDto(document);
        return Response.response("S200", DOC_INFO_SUCCESS, previewInfoDto);
    }

    //문서 저장 API
    @GetMapping("/api/v1/doc/new")
    public Response makeDocV1() {
        Document document = new Document();
        documentService.join(document);

        Document findDoc = documentService.findOne(document.getId());
        return Response.response("S200", DOC_CREATE_SUCCESS, new CreateDocResponse(findDoc.getId()));
    }

    //문서 삭제
    @PostMapping("/api/v1/doc/delete/{docId}")
    public Response deleteDoc(@PathVariable Long docId) {
        Document document = documentService.findOne(docId);
        documentService.deleteDocument(document);
        return Response.response("S200", DOC_DELETE_SUCCESS, docId);
    }

    //문서 좋아요
    @PostMapping("/api/v1/doc/{id}/like")
    public Response likeDoc(@PathVariable Long id, @RequestBody @Valid LikeRequestDto likeRequestDto) {
        Member findMember = memberService.findOne(likeRequestDto.getMemberId());
        Document findDoc = documentService.findOne(id);
        Long createdLike = likeService.createLike(findMember, findDoc);
        return Response.response("S200", DOC_LIKE_SUCCESS, new LikeRequestDto(findMember.getId()));
    }

    //문서 좋아요 취소
    @PostMapping("/api/v1/doc/{id}/unlike")
    public Response unlikeDoc(@PathVariable Long id, @RequestBody @Valid LikeRequestDto likeRequestDto) {
        Member findMember = memberService.findOne(likeRequestDto.getMemberId());
        Document findDoc = documentService.findOne(id);
        Like findLike = likeService.findWithDocAndMember(findMember, findDoc);
        if(findLike == null)
            return Response.response("E200", DOC_LIKE_NOT_EXIST, likeRequestDto);
        else {
            likeService.delete(findLike);
            return Response.response("S200", DOC_UNLIKE_SUCCESS, new LikeRequestDto(findMember.getId()));
        }
    }


    @GetMapping("/api/v1/docall")
    public Response docInfoV1() {
        List<Document> findDocuments = documentService.findDocuments();

        List<DocInfoDto_legacy> docs = findDocuments.stream()
                .map(DocInfoDto_legacy::new)
                .collect(Collectors.toList());
        return Response.response("S200", DOC_INFO_SUCCESS, docs);
    }
}

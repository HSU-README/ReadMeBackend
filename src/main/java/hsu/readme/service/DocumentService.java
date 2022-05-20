package hsu.readme.service;

import hsu.readme.Repository.ComponentRepository;
import hsu.readme.Repository.DocumentRepository;
import hsu.readme.Repository.MemberRepository;
import hsu.readme.Repository.TagRepository;
import hsu.readme.api.component.DocInfoDto;
import hsu.readme.domain.DocComponent;
import hsu.readme.domain.Document;
import hsu.readme.domain.Member;
import hsu.readme.domain.Tag;
import hsu.readme.domain.component.Component;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class DocumentService {

    private final MemberRepository memberRepository;
    private final DocumentRepository documentRepository;
    private final ComponentRepository componentRepository;
    private final TagRepository tagRepository;

    //문서 작성
    @Transactional
    public Long join(Document document){
        validateDocument(document);
        documentRepository.save(document);
        return document.getId();
    }

    private void validateDocument(Document document){}

    //문서 전체 조회
    public List<Document> findDocuments(){
        return documentRepository.findAll();
    }

    public Document findOne(Long documentId){
        return documentRepository.findOne(documentId);
    }

    public List<Document> findDocumentsByLikeDesc(int cnt){
        return documentRepository.findTopDocumentsOrderByLikeCnt(cnt);
    }

    public List<Document> findDocumentsWithTag(){
        List<Document> find = documentRepository.findAllWithTags();
        System.out.println("service: " + find.size());
        return find;
    }

    public Document findDocumentWithMember(Long docId){
        return documentRepository.findWithMember(docId);
    }

    public List<Document> findMemberDocs(Long memberId) {
        return documentRepository.findMemberDocs(memberId);
    }

    public Document findDocumentInfo(Long docId) {
        Document documentInfo = documentRepository.findDocumentInfo(docId);

        return documentInfo;
    }

    public List<Document> findDocsWithMemberLikes(Long memberId) {
        return documentRepository.findMemberLikeDocs(memberId);
    }

    /*
    * 문서 생성
     */
    @Transactional
    public Long makeDocument(Long docId, Long memberId, String title, String docUrl, String visibility, List<Long> tagIds, List<Long> componentIds) {

        Member member = memberRepository.findOne(memberId);

        List<DocComponent> docComponents = new ArrayList<>();
        for(Long componentId : componentIds) {
            if(componentId == -1) continue;
            Component component = componentRepository.findOne(componentId);
            DocComponent docComponent = DocComponent.createDocComponent(component);
            docComponents.add(docComponent);
        }

        List<Tag> tags = new ArrayList<>();
        for(Long tagId : tagIds) {
            Tag tag = tagRepository.findOne(tagId);
            tags.add(tag);
        }

        Document document = Document.createDocument(docId, member, title, docUrl, visibility, tags, docComponents);

        documentRepository.save(document);

        return document.getId();
    }

    /*
    * 문서 삭제
    */
    @Transactional
    public Long deleteDocument(Document document) {
        documentRepository.deleteDocument(document);
        return document.getId();
    }
}
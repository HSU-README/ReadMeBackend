package hsu.readme.service;

import hsu.readme.Repository.*;
import hsu.readme.domain.*;
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
    private final DocComponentRepository docComponentRepository;
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

    //문서 전체 조회
    public List<Document> findDocumentsSortedByDocDate(){
        return documentRepository.findAllSortedByDocDate();
    }

    public List<Document> findDocumentsSortedByDocDate(int offset, int limit){
        return documentRepository.findAllSortedByDocDate(offset, limit);
    }

    public Document findOne(Long documentId){
        return documentRepository.findOne(documentId);
    }

    public List<Document> findDocumentsByLikeDesc(int start, int limit){
        return documentRepository.findTopDocumentsOrderByLikeCnt(start, limit);
    }

    public Document findDocumentWithMember(Long docId){
        return documentRepository.findWithMember(docId);
    }

    public List<Document> findMemberDocs(Long memberId) {
        return documentRepository.findMemberDocs(memberId);
    }

    public Document findDocumentInfo(Long docId) {
        return documentRepository.findDocumentInfo(docId);
    }

    public List<Document> findDocsWithMemberLikes(Long memberId) {
        return documentRepository.findMemberLikeDocs(memberId);
    }

    public List<Document> findDocsWithMajor(String major) {
        return documentRepository.findAllWithMajor(major);
    }

    /*public List<Document> findDocsWithMemberMajor(String major) {
        return documentRepository.findAllWithMemberMajor(major);
    }*/

    public List<Document> findDocsWithMemberMajor(String major, int start, int limit) {
        return documentRepository.findAllWithMemberMajor(major, start, limit);
    }

    public List<Document> findDocsWithSearch(String search) {
        return documentRepository.searchDocuments(search);
    }

    /*
    * 문서 생성
     */
    @Transactional
    public Long makeDocument(Long memberId, String title, String docUrl, String visibility, String major, List<Long> tagIds, List<Long> componentIds) {

        Member member = memberRepository.findOne(memberId);

        List<DocComponent> docComponents = createDocComponents(componentIds);

        List<Tag> tags = createTags(tagIds);

        Document document = Document.createDocument(member, title, docUrl, visibility, major, tags, docComponents);

        documentRepository.save(document);

        return document.getId();
    }

    @Transactional
    public Long editDocument(Long docId, String title, String docUrl, String visibility, String major, List<Long> tagIds, List<Long> componentIds) {
        Document document = documentRepository.findOne(docId);
        document.getDocComponents().clear();
        document.getTags().clear();
        List<DocComponent> docComponents = createDocComponents(componentIds);

        List<Tag> tags = createTags(tagIds);

        for(DocComponent docComponent : docComponents) {
            document.getDocComponents().add(docComponent);
            docComponent.setDocument(document);
        }

        for(Tag tag : tags) {
            document.getTags().add(tag);
            tag.setDocument(document);
        }

        document.setTitle(title);
        document.setDocUrl(docUrl);
        if(visibility.equals("public")) {
            document.setVisibility(DocumentVisibility.PUBLIC);
        } else {
            document.setVisibility(DocumentVisibility.PRIVATE);
        }
        document.setDocMajor(major);

        return document.getId();
    }

    private List<Tag> createTags(List<Long> tagIds) {
        List<Tag> tags = new ArrayList<>();
        for(Long tagId : tagIds) {
            Tag tag = tagRepository.findOne(tagId);
            tags.add(tag);
        }
        return tags;
    }

    private List<DocComponent> createDocComponents(List<Long> componentIds) {
        List<DocComponent> docComponents = new ArrayList<>();
        for(Long componentId : componentIds) {
            if(componentId == -1) continue;
            Component component = componentRepository.findOne(componentId);
            DocComponent docComponent = DocComponent.createDocComponent(component);
            docComponents.add(docComponent);
        }
        return docComponents;
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
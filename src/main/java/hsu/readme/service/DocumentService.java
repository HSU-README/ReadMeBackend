package hsu.readme.service;

import hsu.readme.Repository.DocumentRepository;
import hsu.readme.domain.Document;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class DocumentService {

    private final DocumentRepository documentRepository;

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

    public List<Document> findDocumentWithMember(Long id){
        return documentRepository.findWithMember(id);
    }
}
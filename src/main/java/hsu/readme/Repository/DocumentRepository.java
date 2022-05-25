package hsu.readme.Repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import hsu.readme.domain.Document;
import hsu.readme.domain.Member;
import hsu.readme.domain.QDocument;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.print.Doc;
import java.util.List;

import static hsu.readme.domain.QTag.*;
import static hsu.readme.domain.QMember.*;
import static hsu.readme.domain.QDocument.*;

@Repository
public class DocumentRepository {


    @PersistenceContext
    private EntityManager em;
    private JPAQueryFactory query;

    public DocumentRepository(EntityManager em) {
        this.em = em;
        this.query = new JPAQueryFactory(em);
    }

    public void save(Document document){
        em.persist(document);
    }

    public Document findOne(Long id){
        return em.find(Document.class, id);
    }

    public List<Document> findAll(){
        return em.createQuery("select d from Document d", Document.class)
                .getResultList();
    }

    public List<Document> findAllSortedByDocDate(){
        return em.createQuery("select d from Document d " +
                " order by d.documentDate desc", Document.class)
                .getResultList();
    }

    public List<Document> findAllSortedByDocDate(int offset, int limit){
        return em.createQuery("select d from Document d " +
                " order by d.documentDate desc", Document.class)
                .setFirstResult(offset)
                .setMaxResults(limit)
                .getResultList();
    }

    public List<Document> findAllWithMajor(String major, int start, int limit){
            return em.createQuery("select d from Document d " +
                    " where d.docMajor like :major" +
                    " order by d.documentDate desc", Document.class)
                    .setParameter("major", major)
                    .setFirstResult(start)
                    .setMaxResults(limit)
                .getResultList();
    }

    /*public List<Document> findAllWithMemberMajor(String major){
        return em.createQuery("select d from Document d " +
                " join fetch d.member m " +
                " where m.major like :major" +
                " order by d.documentDate desc", Document.class)
                .setParameter("major", major)
                .getResultList();
    }*/

    public List<Document> findAllWithMemberMajor(String major, int start, int limit){
        return em.createQuery("select d from Document d " +
                " join fetch d.member m " +
                " where m.major like :major" +
                " order by d.documentDate desc", Document.class)
                .setParameter("major", major)
                .setFirstResult(start)
                .setMaxResults(limit)
                .getResultList();
    }

    public List<Document> findAllWithTags(){
        return em.createQuery("select d from Document d " +
                "left join fetch d.tags t", Document.class)
                .getResultList();
    }

    public List<Document> findAllWithTags(int offset, int limit){
        return em.createQuery("select d from Document d " +
                " join fetch d.tags t", Document.class)
                .setFirstResult(offset)
                .setMaxResults(limit)
                .getResultList();
    }

    public Document findWithMember(Long id){
        return em.createQuery("select d from Document d " +
                " join fetch d.member m " +
                " where d.id = :id", Document.class)
                .setParameter("id", id)
                .getSingleResult();
    }

    public void deleteDocument(Document document) {
        em.remove(document);
    }

    public Document findDocumentInfo(Long docId) {
        return em.createQuery("select d from Document d " +
                " join fetch d.member m " +
//                " left join fetch d.tags t " +
                " join fetch d.docComponents dc " +
                " join fetch dc.component c " +
                " where d.id = :id", Document.class)
                .setParameter("id", docId)
                .getSingleResult();
    }

    public List<Document> findMemberDocs(Long memberId) {
        return em.createQuery("select d from Document d " +
                " join fetch d.member m " +
                " where m.id = :id", Document.class)
                .setParameter("id", memberId)
                .getResultList();
    }

    public List<Document> findMemberLikeDocs(Long memberId) {
        return em.createQuery("select d from Document d " +
                " join fetch d.likes l " +
                " join fetch d.member m" +
                " where m.id = :memberId", Document.class)
                .setParameter("memberId", memberId)
                .getResultList();
    }

    public List<Document> findTopDocumentsOrderByLikeCnt(int start, int limit) {
        return em.createQuery(
                "select d from Document d " +
                        " join fetch d.member m " +
                        " order by d.documentDate desc", Document.class)
                .setFirstResult(start)
                .setMaxResults(limit)
                .getResultList();
    }

    public List<Document> searchDocs(String search) {
        return em.createQuery("select d from Document d " +
                " join fetch d.tags t " +
                " where d.title like %" + search +"% ", Document.class
                ).getResultList();
    }

    public List<Document> searchDocuments(String search) {
        return query.select(document)
                .from(document)
                .distinct()
                .join(document.tags, tag)
                .join(document.member, member)
                .where(titleLike(search).or(tagLike(search)))
                .limit(1000)
                .fetch();
    }

    private BooleanExpression titleLike(String search) {
        if(!StringUtils.hasText(search)) return null;
        return document.title.contains(search);
    }

    private BooleanExpression tagLike(String search) {
        if(!StringUtils.hasText(search)) {
            return null;
        }
        return tag.name.contains(search);
    }
}

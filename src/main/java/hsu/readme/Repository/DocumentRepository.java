package hsu.readme.Repository;

import hsu.readme.domain.Document;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class DocumentRepository {


    @PersistenceContext
    private EntityManager em;

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

    public List<Document> findByTitle(String title){
        return em.createQuery(
                "select d from Document d " +
                        " where d.title = :title", Document.class)
                .setParameter("title", title)
                .getResultList();
    }

    public List<Document> findTopDocumentsOrderByLikeCnt(int cnt) {
        return em.createQuery(
                "select d from Document d " +
                        "left join fetch d.tags t " +
                        " order by d.likeCnt desc ", Document.class)
                .setMaxResults(cnt)
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
}

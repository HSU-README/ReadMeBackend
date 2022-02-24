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
                        " order by d.likeCnt desc ", Document.class)
                .setMaxResults(cnt)
                .getResultList();
    }
}

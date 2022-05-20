package hsu.readme.Repository;

import hsu.readme.domain.DocComponent;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class DocComponentRepository {

    @PersistenceContext
    private EntityManager em;


    public void save(DocComponent docComponent) {
        em.persist(docComponent);
    }

    public DocComponent findOne(Long id) {
        return em.find(DocComponent.class, id);
    }

    public Long remove(DocComponent docComponent) {
        em.remove(docComponent);
        return docComponent.getId();
    }
}

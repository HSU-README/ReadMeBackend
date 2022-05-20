package hsu.readme.Repository;

import hsu.readme.domain.Tag;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class TagRepository {

    @PersistenceContext
    private EntityManager em;

    public void save(Tag tag){
        em.persist(tag);
    }

    public Tag findOne(Long id){
        return em.find(Tag.class, id);
    }

    public void remove(Tag tag) {
        em.remove(tag);
    }
}

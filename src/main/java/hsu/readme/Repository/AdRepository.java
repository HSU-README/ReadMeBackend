package hsu.readme.Repository;

import hsu.readme.domain.Adv;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class AdRepository {

    @PersistenceContext
    private EntityManager em;

    public Adv findOne(Long id){
        return em.find(Adv.class, id);
    }

    public List<Adv> findAll(){
        return em.createQuery("select a from Adv a", Adv.class
        ).getResultList();
    }
}

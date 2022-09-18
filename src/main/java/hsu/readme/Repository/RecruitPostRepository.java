package hsu.readme.Repository;

import hsu.readme.domain.Document;
import hsu.readme.domain.RecruitPost;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class RecruitPostRepository {

    @PersistenceContext
    private final EntityManager em;

    public RecruitPostRepository(EntityManager em) {
        this.em = em;
    }

    public void save(RecruitPost recruitPost) {
        em.persist(recruitPost);
    }

    public RecruitPost findOne(Long id) { return em.find(RecruitPost.class, id); }

    public List<RecruitPost> findAll() {
        return em.createQuery("select rp from RecruitPost rp", RecruitPost.class)
                .getResultList();
    }

    public List<RecruitPost> findAllSortedByPostDate(){
        return em.createQuery("select rp from RecruitPost rp " +
                " order by rp.id desc", RecruitPost.class)
                .getResultList();
    }

    public List<RecruitPost> findAllSortedByJob(String job) {
        return em.createQuery("select rp from RecruitPost  rp " +
                " where rp.jobOpening = :job " +
                 " order by rp.id desc", RecruitPost.class)
                .setParameter("job", job)
                .getResultList();
    }
}

package hsu.readme.Repository;

import hsu.readme.domain.Like;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class LikeRepository {

    @PersistenceContext
    private EntityManager em;

    public void save(Like like) {
        em.persist(like);
    }

    public void remove(Like like) {
        em.remove(like);
    }

    public Like findOne(Long id) {
        return em.find(Like.class, id);
    }

    public List<Like> findAll() {
        return em.createQuery("select l from Like l", Like.class)
                .getResultList();
    }

    public List<Like> findLikeWithDoc(Long docId) {
        return em.createQuery("select l from Like l " +
                " join fetch l.document d " +
                " where d.id = :docId", Like.class)
                .setParameter("docId", docId)
                .getResultList();
    }

    public Like findLikeInDocWithMember(Long memberId, Long docId) {
        return em.createQuery("select l from Like l " +
                " join fetch l.member m " +
                " join fetch l.document d " +
                " where m.id = :memberId and " +
                " d.id = :docId", Like.class)
                .setParameter("memberId", memberId)
                .setParameter("docId", docId)
                .getSingleResult();
    }
}

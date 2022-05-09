package hsu.readme.Repository;

import hsu.readme.domain.component.Component;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ComponentRepository {

    @PersistenceContext
    private final EntityManager em;

    public void save(Component component) {
        if (component.getId() == null) {
            em.persist(component);
        } else {
            em.merge(component);
        }
    }

    public Component findOne(Long id) { return em.find(Component.class, id); }

    public List<Component> findAll() {
        return em.createQuery("select c from Component c", Component.class)
                .getResultList();
    }
}

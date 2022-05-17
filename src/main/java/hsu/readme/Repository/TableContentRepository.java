package hsu.readme.Repository;

import hsu.readme.domain.component.Component;
import hsu.readme.domain.component.table.TableContent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class TableContentRepository {

    @PersistenceContext
    private final EntityManager em;

    public void save(TableContent tableContent) {
        em.persist(tableContent);
    }

    public TableContent findOne(Long id) { return em.find(TableContent.class, id); }

    public List<TableContent> findAll() {
        return em.createQuery("select t from TableContent t", TableContent.class)
                .getResultList();
    }
}

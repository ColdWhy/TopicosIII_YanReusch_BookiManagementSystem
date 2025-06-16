package booki.bookimanagementsystem.facade;

import booki.bookimanagementsystem.entity.BookEntity;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class BookFacade extends AbstractFacade<BookEntity> {

    @PersistenceContext(unitName = "BookiManagementSystem")
    private EntityManager em;

    public BookFacade() {
        super(BookEntity.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    private List<BookEntity> entityList;

    public List<BookEntity> fetchAll() {
        entityList = new ArrayList<>();
        try {
            Query query = getEntityManager().createQuery("SELECT b FROM BookEntity b ORDER BY b.title");
            if (!query.getResultList().isEmpty()) {
                entityList = (List<BookEntity>) query.getResultList();
            }
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
        return entityList;
    }

}

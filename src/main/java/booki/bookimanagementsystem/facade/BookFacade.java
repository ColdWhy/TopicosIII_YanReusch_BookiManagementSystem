package booki.bookimanagementsystem.facade;

import booki.bookimanagementsystem.entity.BookEntity;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

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
}

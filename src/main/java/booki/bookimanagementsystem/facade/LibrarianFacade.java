package booki.bookimanagementsystem.facade;

import booki.bookimanagementsystem.entity.LibrarianEntity;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Stateless
public class LibrarianFacade extends AbstractFacade<LibrarianEntity> {

    @PersistenceContext(unitName = "BookiManagementSystem")
    private EntityManager em;

    public LibrarianFacade() {
        super(LibrarianEntity.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
}

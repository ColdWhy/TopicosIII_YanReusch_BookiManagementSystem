package booki.bookimanagementsystem.facade;

import booki.bookimanagementsystem.entity.LibrarianEntity;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

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

    public LibrarianEntity findByNameAndPassword(String name, String password) {
        try {
            Query query = em.createQuery("SELECT l FROM LibrarianEntity l WHERE l.name = :name AND l.password = :password");
            query.setParameter("name", name);
            query.setParameter("password", password);
            return (LibrarianEntity) query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
}

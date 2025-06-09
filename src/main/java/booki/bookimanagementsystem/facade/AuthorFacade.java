package booki.bookimanagementsystem.facade;

import booki.bookimanagementsystem.entity.AuthorEntity;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Stateless
public class AuthorFacade extends AbstractFacade<AuthorEntity> {

    @PersistenceContext(unitName = "BookiManagementSystem")
    private EntityManager em;

    public AuthorFacade() {
        super(AuthorEntity.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
}

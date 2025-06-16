package booki.bookimanagementsystem.facade;

import booki.bookimanagementsystem.entity.AuthorEntity;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import java.util.ArrayList;
import java.util.List;

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

    private List<AuthorEntity> entityList;

    public List<AuthorEntity> fetchAll() {
        entityList = new ArrayList<>();
        try {
            Query query = getEntityManager().createQuery("SELECT a FROM AuthorEntity a ORDER BY a.name");
            if (!query.getResultList().isEmpty()) {
                entityList = (List<AuthorEntity>) query.getResultList();
            }
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
        return entityList;
    }
}

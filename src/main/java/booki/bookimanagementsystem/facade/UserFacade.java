package booki.bookimanagementsystem.facade;

import booki.bookimanagementsystem.entity.UserEntity;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

@Stateless
public class UserFacade extends AbstractFacade<UserEntity> {

    @PersistenceContext(unitName = "BookiManagementSystem")
    private EntityManager em;

    public UserFacade() {
        super(UserEntity.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UserEntity findByEmailAndPassword(String email, String password) {
        try {
            Query query = em.createQuery("SELECT u FROM UserEntity u WHERE u.email = :email AND u.password = :password");
            query.setParameter("email", email);
            query.setParameter("password", password);
            return (UserEntity) query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
}

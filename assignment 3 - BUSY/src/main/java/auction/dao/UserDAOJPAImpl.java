package auction.dao;

import auction.domain.User;
import java.util.List;
import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class UserDAOJPAImpl implements UserDAO {

    EntityManagerFactory ef = Persistence.createEntityManagerFactory("db");
    EntityManager users = ef.createEntityManager();

    public UserDAOJPAImpl() {
    }

    @Override
    public int count() {
        int ret = (Integer) users.createNamedQuery("SELECT count(*) FROM User")
                .getSingleResult();
        return ret;
    }

    /**
     * Transaction moet verplaatst worden naar een 'hoger niveau' zodat meerdere
     * persists/merges tegelijk uitgevoerd kunnen worden.
     *
     * @param user
     */
    @Override
    public void create(User user) {
        if (findByEmail(user.getEmail()) != null) {
            throw new EntityExistsException();
        }
        users.getTransaction().begin();
        users.persist(user);
        users.getTransaction().commit();
    }

    /**
     * Transaction moet verplaatst worden naar een 'hoger niveau' zodat meerdere
     * persists/merges tegelijk uitgevoerd kunnen worden.
     *
     * @param user
     */
    @Override
    public void edit(User user) {
        if (findByEmail(user.getEmail()) == null) {
            throw new IllegalArgumentException();
        }
        users.getTransaction().begin();
        users.merge(user);
        users.getTransaction().commit();
    }

    @Override
    public List<User> findAll() {
        return users.createQuery("SELECT k FROM User k").getResultList();
    }

    @Override
    public User findByEmail(String email) {
        return users.find(User.class, email);
    }

    /**
     * Wanneer gebruik je een Transaction? Navragen!
     *
     * @param user
     */
    @Override
    public void remove(User user) {
        users.remove(user.getEmail());
    }
}

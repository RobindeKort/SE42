package auction.dao;

import auction.domain.User;
import java.util.List;
import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

public class UserDAOJPAImpl implements UserDAO {

    private EntityManager users;

    public UserDAOJPAImpl(EntityManager users) {
        this.users = users;
    }

    @Override
    public int count() {
        Query q = users.createNamedQuery("User.countUsers", User.class);
        return ((Long) q.getSingleResult()).intValue();
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
        return users.createNamedQuery("User.getAllUsers", User.class).getResultList();
    }

    @Override
    public User findByEmail(String email) {
        Query q = users.createNamedQuery("User.findUserByEmail", User.class);
        q.setParameter("email", email);
        User user = null;

        try {
            user = (User) q.getSingleResult();
        } catch (NoResultException e) {
            user = null;
        }
        return user;
    }

    /**
     *
     * @param user
     */
    @Override
    public void remove(User user) {
        users.remove(users.merge(user));
    }
}

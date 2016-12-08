package auction.dao;

import auction.domain.Item;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class ItemDAOJPAImpl implements ItemDAO {

    private EntityManager items;

    public ItemDAOJPAImpl() {
        this.items = Persistence.createEntityManagerFactory("db").createEntityManager();
    }

    @Override
    public int count() {
        Query q = items.createNamedQuery("Item.count", Item.class);
        return ((Long) q.getSingleResult()).intValue();
    }

    @Override
    public void create(Item item) {
        items.getTransaction().begin();
        items.persist(item);
        items.getTransaction().commit();
    }

    @Override
    public void edit(Item item) {
        items.merge(item);
    }

    @Override
    public List<Item> findAll() {
        return items.createNamedQuery("Item.getAllItems", Item.class).getResultList();
    }

    @Override
    public void remove(Item item) {
        items.getTransaction().begin();
        items.remove(this.find(item.getId()));
        items.getTransaction().commit();
    }

    @Override
    public Item find(Long id) {
        Item item;
        Query query = items.createNamedQuery("Item.findByID", Item.class);
        query.setParameter("id", id);
        try {
            item = (Item) query.getSingleResult();
        } catch (NoResultException e) {
            item = null;
        }
        return item;
    }

    @Override
    public List<Item> findByDescription(String description) {
        Query q = items.createNamedQuery("Item.findByDescription", Item.class);
        return q.setParameter("description", description).getResultList();
    }
}

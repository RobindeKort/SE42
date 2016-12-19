package auction.dao;

import auction.domain.Item;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

public class ItemDAOJPAImpl implements ItemDAO {

    private EntityManager em;

    public ItemDAOJPAImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public int count() {
        Query q = em.createNamedQuery("Item.count", Item.class);
        return ((Long) q.getSingleResult()).intValue();
    }

    @Override
    public void create(Item item) {
        em.persist(item);
    }

    @Override
    public void edit(Item item) {
        em.merge(item);
    }

    @Override
    public List<Item> findAll() {
        return em.createNamedQuery("Item.getAllItems", Item.class).getResultList();
    }

    @Override
    public void remove(Item item) {        
        em.remove(item);
        
    }

    @Override
    public Item find(Long id) {
        Item item;
        Query query = em.createNamedQuery("Item.findByID", Item.class);
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
        Query q = em.createNamedQuery("Item.findByDescription", Item.class);
        return q.setParameter("description", description).getResultList();
    }
}

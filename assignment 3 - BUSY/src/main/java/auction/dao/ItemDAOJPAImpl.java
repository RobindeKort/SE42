package auction.dao;

import auction.domain.Item;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class ItemDAOJPAImpl implements ItemDAO {

    EntityManagerFactory ef = Persistence.createEntityManagerFactory("db");
    EntityManager items = ef.createEntityManager();

    public ItemDAOJPAImpl() {
    }

    @Override
    public int count() {
        return (Integer) items.createNativeQuery("SELECT count(*) FROM item")
                .getSingleResult();
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
        return items.createQuery("SELECT * FROM Item").getResultList();
    }

    @Override
    public void remove(Item item) {
        items.getTransaction().begin();
        items.remove(this.find(item.getId()));
        items.getTransaction().commit();
    }

    @Override
    public Item find(Long id) {
        return items.find(Item.class, id);
    }

    @Override
    public List<Item> findByDescription(String description) {
        Query q = items.createQuery("SELECT i FROM Item i WHERE i.description = :description", Item.class); //HMMMM??? effe navragen aan ROBINIO
        return q.setParameter("description", description).getResultList();
    }
}

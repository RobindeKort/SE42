package auction.service;

import auction.dao.ItemDAO;
import auction.dao.ItemDAOJPAImpl;
import nl.fontys.util.Money;
import auction.domain.Bid;
import auction.domain.Item;
import auction.domain.User;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class AuctionMgr {

     private final EntityManagerFactory f = Persistence.createEntityManagerFactory("db");

    /**
     * @param id
     * @return het item met deze id; als dit item niet bekend is wordt er null
     * geretourneerd
     */
    public Item getItem(Long id) {
        EntityManager em = f.createEntityManager();
        ItemDAO itemDAO = new ItemDAOJPAImpl(em);
        Item item = null;
        em.getTransaction().begin();
        try {
            item = itemDAO.find(id);
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        }
        em.close();
        return item;
    }

    /**
     * @param description
     * @return een lijst met items met @desciption. Eventueel lege lijst.
     */
         public List<Item> findItemByDescription(String description) {
        EntityManager em = f.createEntityManager();
        ItemDAO itemDAO = new ItemDAOJPAImpl(em);
        List<Item> items = null;
        em.getTransaction().begin();
        try {
            items = itemDAO.findByDescription(description);
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        }
        return items;
    }

    /**
     * @param item
     * @param buyer
     * @param amount
     * @return het nieuwe bod ter hoogte van amount op item door buyer, tenzij
     * amount niet hoger was dan het laatste bod, dan null
     */
    public Bid newBid(Item item, User buyer, Money amount) {
        EntityManager em = f.createEntityManager();
        ItemDAO itemDAO = new ItemDAOJPAImpl(em);
        Bid bid = new Bid(buyer, amount);
        if (item.getHighestBid() != null && item.getHighestBid().getAmount().getCents() >= amount.getCents()) {
            return null;
        }
        if (item.getHighestBid() == null || item.getHighestBid().getAmount().getCents() < amount.getCents()) {
            em.getTransaction().begin();
            item.newBid(buyer, amount);
            try {
                itemDAO.edit(item);
                em.getTransaction().commit();
            } catch (Exception e) {
                e.printStackTrace();
                em.getTransaction().rollback();
            }
        }
        return bid;
    }
}

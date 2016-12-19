package auction.service;

import auction.dao.ItemDAO;
import auction.dao.ItemDAOJPAImpl;
import auction.domain.Category;
import auction.domain.Item;
import auction.domain.User;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class SellerMgr {

    private final EntityManagerFactory f = Persistence.createEntityManagerFactory("db");

    /**
     * @param seller
     * @param cat
     * @param description
     * @return het item aangeboden door seller, behorende tot de categorie cat
     * en met de beschrijving description
     */
    public Item offerItem(User seller, Category cat, String description) {
        EntityManager em = f.createEntityManager();
        ItemDAO itemDAO = new ItemDAOJPAImpl(em);
        Item item = new Item(seller, cat, description);
        em.getTransaction().begin();
        try {
            itemDAO.create(item);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
        }
        return item;
    }

    /**
     * @param item
     * @return true als er nog niet geboden is op het item. Het item word
     * verwijderd. false als er al geboden was op het item.
     */
    public boolean revokeItem(Item item) {
        EntityManager em = f.createEntityManager();
        ItemDAOJPAImpl itemDAO = new ItemDAOJPAImpl(em);
        em.getTransaction().begin();
        try {
            Item databaseItem = itemDAO.find(item.getId());
            if (databaseItem.getHighestBid() == null) {
                itemDAO.remove(databaseItem);
                em.getTransaction().commit();
                return true;
            }
        } catch (Exception e) {
            em.getTransaction().rollback();
        }
        return false;
    }
}

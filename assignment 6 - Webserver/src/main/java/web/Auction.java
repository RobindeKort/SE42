package web;

import auction.domain.Bid;
import auction.domain.Category;
import auction.domain.Item;
import auction.domain.User;
import auction.service.AuctionMgr;
import auction.service.SellerMgr;
import java.sql.SQLException;
import java.util.List;
import javax.jws.*;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import nl.fontys.util.Money;
import util.DatabaseCleaner;

@WebService
public class Auction {
    private final AuctionMgr auctionMgr = new AuctionMgr();
    private final SellerMgr sellerMgr = new SellerMgr();
    private final EntityManagerFactory f = Persistence.createEntityManagerFactory("db");
    private EntityManager em;
    
    public Auction() {
    }
    
    @WebMethod
    public Item getItem(Long id){
        return auctionMgr.getItem(id);
    }
    
    @WebMethod
    public List<Item> findItemByDescription(String description){
        return auctionMgr.findItemByDescription(description);
    }
    
    @WebMethod
    public Bid newBid(Item item, User buyer, Money amount){
        return auctionMgr.newBid(item, buyer, amount);
    }
    
    @WebMethod
    public Item offerItem(User seller, Category cat, String description){
        return sellerMgr.offerItem(seller, cat, description);
    }
    
    @WebMethod
    public boolean revokeItem(Item item){
        return sellerMgr.revokeItem(item);
    }
    
    @WebMethod
    public void DatabaseClean(){
        em = f.createEntityManager();
        DatabaseCleaner dc = new DatabaseCleaner(em);
        try {
            dc.clean();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}

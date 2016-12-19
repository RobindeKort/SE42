package auction.service;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import java.util.List;
import org.junit.After;
import auctionclient.*;

//webseervices
import web.Bid;
import web.Category;
import web.Item;
import web.Money;
import web.User;

public class AuctionMgrTest {
    
    private Auction auctionMgr;
    private Registration registrationMgr;

    @Before
    public void setUp() throws Exception {
        registrationMgr = new Registration();
        auctionMgr = new Auction();
    }

    @After
    public void tearDown() throws Exception {
        auctionMgr.databaseClean();
    }

    @Test
    public void getItem() {

        String email = "xx2@nl";
        String omsch = "omsch";

        User seller1 = registrationMgr.registerUser(email);
        Category cat = new Category();
        cat.setDescription("cat2");
        
        Item item1 = auctionMgr.offerItem(seller1, cat, omsch);
        Item item2 = auctionMgr.getItem(item1.getId());
        assertEquals(omsch, item2.getDescription());
        assertEquals(email, item2.getSeller().getEmail());
    }

    @Test
    public void findItemByDescription() {
        String email3 = "xx3@nl";
        String omsch = "omsch";
        String email4 = "xx4@nl";
        String omsch2 = "omsch2";

        User seller3 = registrationMgr.registerUser(email3);
        User seller4 = registrationMgr.registerUser(email4);
        Category cat = new Category();
        cat.setDescription("cat3");
        Item item1 = auctionMgr.offerItem(seller3, cat, omsch);
        Item item2 = auctionMgr.offerItem(seller4, cat, omsch);

        List<Item> res = auctionMgr.findItemByDescription(omsch2);
        assertEquals(0, res.size());

        res = auctionMgr.findItemByDescription(omsch);
        assertEquals(2, res.size());

    }

    @Test
    public void newBid() {

        String email = "ss2@nl";
        String emailb = "bb@nl";
        String emailb2 = "bb2@nl";
        String omsch = "omsch_bb";

        User seller = registrationMgr.registerUser(email);
        User buyer = registrationMgr.registerUser(emailb);
        User buyer2 = registrationMgr.registerUser(emailb2);
        // eerste bod
        Category cat = new Category();
        cat.setDescription("cat9");
        Item item1 = auctionMgr.offerItem(seller, cat, omsch);

        Money money = new Money();
        money.setCents(10);
        money.setCurrency("eur");
        Bid new1 = auctionMgr.newBid(item1, buyer, money);
        assertEquals(emailb, new1.getBuyer().getEmail());

        // lager bod
        money.setCents(9);
        Bid new2 = auctionMgr.newBid(item1, buyer2, money);
        assertNotNull(new2);

        // hoger bod
        money.setCents(11);
        Bid new3 = auctionMgr.newBid(item1, buyer2, money);
        assertEquals(emailb2, new3.getBuyer().getEmail());
    }
}

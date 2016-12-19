package auction.service;

import auctionclient.*;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import java.util.List;
import org.junit.After;

//Webservices
import web.Category;
import web.Item;
import web.Money;
import web.User;

public class SellerMgrTest {
    
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

    /**
     * Test of offerItem method, of class SellerMgr.
     */
    @Test
    public void testOfferItem() {
        String omsch = "omsch";

        User user1 = registrationMgr.registerUser("xx@nl");
        Category cat = new Category();
        cat.setDescription("cat1");
        Item item1 = auctionMgr.offerItem(user1, cat, omsch);
        assertEquals(omsch, item1.getDescription());
        assertNotNull(item1.getId());
    }

    /**
     * Test of revokeItem method, of class SellerMgr.
     */
    @Test
    public void testRevokeItem() {
        String omsch = "omsch";
        String omsch2 = "omsch2";

        User seller = registrationMgr.registerUser("sel@nl");
        User buyer = registrationMgr.registerUser("buy@nl");
        Category cat = new Category();
        cat.setDescription("cat1");

        // revoke before bidding
        Item item1 = auctionMgr.offerItem(seller, cat, omsch);
        boolean res = auctionMgr.revokeItem(item1);
        assertTrue(res);

        List<Item> itemsFromDatabase = auctionMgr.findItemByDescription(omsch);
        int count = itemsFromDatabase.size();
        assertEquals(0, count);

        // revoke after bid has been made
        Item item2 = auctionMgr.offerItem(seller, cat, omsch2);
        Money money = new Money();
        money.setCents(100);
        money.setCurrency("Euro");
        auctionMgr.newBid(item2, buyer, money);
        boolean res2 = auctionMgr.revokeItem(item2);
        assertFalse(res2);
        int count2 = auctionMgr.findItemByDescription(omsch2).size();
        assertEquals(1, count2);

    }

}

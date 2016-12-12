package auction.service;

import static org.junit.Assert.*;

import nl.fontys.util.Money;

import org.junit.Before;
import org.junit.Test;

import auction.domain.Category;
import auction.domain.Item;
import auction.domain.User;
import java.sql.SQLException;
import java.util.List;
import org.junit.After;
import org.junit.Ignore;
import util.DatabaseCleaner;

public class SellerMgrTest {

    private AuctionMgr auctionMgr;
    private RegistrationMgr registrationMgr;
    private SellerMgr sellerMgr;

    @Before
    public void setUp() throws Exception {
        registrationMgr = new RegistrationMgr();
        auctionMgr = new AuctionMgr();
        sellerMgr = new SellerMgr();
    }

    @After
    public void tearDown() throws Exception {
       DatabaseCleaner cleaner = new DatabaseCleaner(registrationMgr.getEntityManager());
        try {
            cleaner.clean();
        }
        catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Test of offerItem method, of class SellerMgr.
     */
    @Ignore
    @Test
    public void testOfferItem() {
        String omsch = "omsch";

        User user1 = registrationMgr.registerUser("xx@nl");
        Category cat = new Category("cat1");
        Item item1 = sellerMgr.offerItem(user1, cat, omsch);
        assertEquals(omsch, item1.getDescription());
        assertNotNull(item1.getId());
        try {
            tearDown();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Test of revokeItem method, of class SellerMgr.
     */
    @Ignore
    @Test
    public void testRevokeItem() {
        String omsch = "omsch";
        String omsch2 = "omsch2";

        User seller = registrationMgr.registerUser("sel@nl");
        User buyer = registrationMgr.registerUser("buy@nl");
        Category cat = new Category("cat1");

        // revoke before bidding
        Item item1 = sellerMgr.offerItem(seller, cat, omsch);
        boolean res = sellerMgr.revokeItem(item1);
        assertTrue(res);

        List<Item> itemsFromDatabase = auctionMgr.findItemByDescription(omsch);
        int count = itemsFromDatabase.size();
        assertEquals(0, count);

        // revoke after bid has been made
        Item item2 = sellerMgr.offerItem(seller, cat, omsch2);
        auctionMgr.newBid(item2, buyer, new Money(100, "Euro"));
        boolean res2 = sellerMgr.revokeItem(item2);
        assertFalse(res2);
        int count2 = auctionMgr.findItemByDescription(omsch2).size();
        assertEquals(1, count2);

    }

}

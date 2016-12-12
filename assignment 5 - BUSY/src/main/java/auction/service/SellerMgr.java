package auction.service;

import auction.dao.ItemDAOJPAImpl;
import auction.domain.Category;
import auction.domain.Furniture;
import auction.domain.Item;
import auction.domain.Painting;
import auction.domain.User;

public class SellerMgr {

    private ItemDAOJPAImpl itemDAOJPA;

    public SellerMgr() {
        itemDAOJPA = new ItemDAOJPAImpl();
    }

    /**
     * @param seller
     * @param cat
     * @param description
     * @return het item aangeboden door seller, behorende tot de categorie cat
     * en met de beschrijving description
     */
    public Item offerItem(User seller, Category cat, String description) {
//        Item item = new Item(seller, cat, description);
//        itemDAOJPA.create(item);
//        seller.addItemToUser(item);
//        return item;
        return null;
    }

    /**
     * @param item
     * @return true als er nog niet geboden is op het item. Het item word
     * verwijderd. false als er al geboden was op het item.
     */
    public boolean revokeItem(Item item) {
        Item databaseItem = itemDAOJPA.find(item.getId());
        if (databaseItem.getHighestBid() == null) {
            itemDAOJPA.remove(databaseItem);
            return true;
        }
        return false;
    }

    Item offerPainting(User seller, Category cat, String description, String title, String painter) {
        Painting painting = new Painting(seller, cat, description, title, painter);
        itemDAOJPA.create(painting);
        seller.addItemToUser(painting);
        return painting;
    }

    Item offerFurniture(User seller, Category cat, String description, String madeFrom) {
        Furniture furniture = new Furniture(seller, cat, description, madeFrom);
        itemDAOJPA.create(furniture);
        seller.addItemToUser(furniture);
        return furniture;
    }
}

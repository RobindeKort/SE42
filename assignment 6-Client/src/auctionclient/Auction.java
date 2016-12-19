package auctionclient;

import web.Bid;
import web.Item;

public class Auction {

    public java.util.List<web.Item> findItemByDescription(java.lang.String arg0) {
        web.AuctionService service = new web.AuctionService();
        web.Auction port = service.getAuctionPort();
        return port.findItemByDescription(arg0);
    }

    public Item getItem(java.lang.Long arg0) {
        web.AuctionService service = new web.AuctionService();
        web.Auction port = service.getAuctionPort();
        return port.getItem(arg0);
    }

    public Bid newBid(web.Item arg0, web.User arg1, web.Money arg2) {
        web.AuctionService service = new web.AuctionService();
        web.Auction port = service.getAuctionPort();
        return port.newBid(arg0, arg1, arg2);
    }

    public Item offerItem(web.User arg0, web.Category arg1, java.lang.String arg2) {
        web.AuctionService service = new web.AuctionService();
        web.Auction port = service.getAuctionPort();
        return port.offerItem(arg0, arg1, arg2);
    }

    public boolean revokeItem(web.Item arg0) {
        web.AuctionService service = new web.AuctionService();
        web.Auction port = service.getAuctionPort();
        return port.revokeItem(arg0);
    }

    public void databaseClean() {
        web.AuctionService service = new web.AuctionService();
        web.Auction port = service.getAuctionPort();
        port.databaseClean();
    }
}

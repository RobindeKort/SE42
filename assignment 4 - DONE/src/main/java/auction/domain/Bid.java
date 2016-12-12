package auction.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import nl.fontys.util.FontysTime;
import nl.fontys.util.Money;

@Entity
public class Bid {

    @Id
    @GeneratedValue
    private Long id;

    @OneToOne
    private Item bettedOnItem;
    
    @Column //niet helemaal zeker
    private FontysTime time;

    @OneToOne
    private User buyer;
    private Money amount;

    public Bid() {
    }

    public Bid(User buyer, Money amount, Item bettedOnItem) {
        this.buyer = buyer;
        this.amount = amount;
        this.bettedOnItem = bettedOnItem;
    }

    public void setBettedOnItem(Item bettedOnItem) {
        this.bettedOnItem = bettedOnItem;
    }

    public Item getBettedOnItem() {
        return bettedOnItem;
    }

    public FontysTime getTime() {
        return time;
    }

    public User getBuyer() {
        return buyer;
    }

    public Money getAmount() {
        return amount;
    }
}

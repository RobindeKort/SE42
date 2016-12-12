package auction.domain;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.CascadeType;
import nl.fontys.util.Money;
import javax.persistence.Id;
import javax.persistence.Entity;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.GeneratedValue;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;

/**
 * item entity
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "Item.count", query = "select count(i) from Item as i"),
    @NamedQuery(name = "Item.findByID", query = "select i from Item as i where i.id = :id"),
    @NamedQuery(name = "Item.findByDescription", query = "select i from Item as i where i.description = :description"),
    @NamedQuery(name = "Item.getAllItems", query = "select i from Item as i")
})
public class Item implements Comparable {

    /**
     * The id of the item
     */
    @Id
    @GeneratedValue
    private Long id;

    /**
     * The seller of the selected item
     */
    @ManyToOne
    private User seller;

    public void setSeller(User seller) {
        this.seller = seller;
    }
    /**
     * The category the item is in
     */
    @Embedded
    @AttributeOverrides({
        @AttributeOverride(name = "description", column = @Column(name = "c_description"))})
    @OneToOne(cascade = CascadeType.PERSIST)
    private Category category;

    /**
     * The description of the item
     */
    private String description;

    /**
     * The highest bid on the selected item
     */
    @OneToOne(cascade = CascadeType.PERSIST, mappedBy = "bettedOnItem")
    private Bid highest;

    /**
     * Item constructor
     *
     * @param seller The seller
     * @param category The category
     * @param description The description
     */
    public Item(User seller, Category category, String description) {
        this.seller = seller;
        this.category = category;
        this.description = description;
    }

    public Item() {
    }

    /**
     * Returns id
     *
     * @return The id
     */
    public Long getId() {
        return id;
    }

    /**
     * Returns the user that gets the item
     *
     * @return The user
     */
    public User getSeller() {
        return seller;
    }

    /**
     * Returns the category this item belongs to
     *
     * @return The category
     */
    public Category getCategory() {
        return category;
    }

    /**
     * Returns the description of this item
     *
     * @return The description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns the highest bid that was placed on this item
     *
     * @return The highest bid
     */
    public Bid getHighestBid() {
        return highest;
    }

    /**
     * Sets a new bid for this item
     *
     * @param buyer The buyer
     * @param amount The amount of the bid
     * @return The created bid
     */
    public Bid newBid(User buyer, Money amount) {
        if (highest != null && highest.getAmount().compareTo(amount) >= 0) {
            return null;
        }
        highest = new Bid(buyer, amount, this);
        return highest;
    }

    /**
     * Compares to an object
     *
     * @param arg0
     * @return
     */
    public int compareTo(Object other) {
        if (other instanceof Item) {
            if (this.id == ((Item) other).getId()) {
                return 0;
            } else {
                return 1;
            }
        } else {
            return -1;
        }
    }

    public boolean equals(Object o) {
        if (o instanceof Item) {
            return this == o;
        } else {
            return false;
        }
    }

    //https://en.wikipedia.org/wiki/Java_hashCode() ik heb een random getal 10 gepakt :P
    public int hashCode() {
        int result = id.hashCode();
        result = 10 * result + seller.hashCode();
        result = 10 * result + category.hashCode();
        result = 10 * result + description.hashCode();
        if (highest != null) {
            result = 10 * result + highest.hashCode();
        }
        return result;
    }
}

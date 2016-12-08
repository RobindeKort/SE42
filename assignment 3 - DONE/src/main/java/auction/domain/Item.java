package auction.domain;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import nl.fontys.util.Money;
import javax.persistence.Id;
import javax.persistence.Entity;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.GeneratedValue;
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
    @OneToOne
    private User seller;

    /**
     * The category the item is in
     */
    @Embedded
    @AttributeOverrides({
        @AttributeOverride(name = "description", column = @Column(name = "c_description"))})
    private Category category;

    /**
     * The description of the item
     */
    private String description;

    /**
     * The highest bid on the selected item
     */
    @OneToOne
    private Bid highest;

    public Item() {
    }

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
        highest = new Bid(buyer, amount);
        return highest;
    }

    /**
     * Compares to an object
     *
     * @param arg0
     * @return
     */
    public int compareTo(Object arg0) {
        //TODO
        return -1;
    }

    public boolean equals(Object o) {
        //TODO
        return false;
    }

    public int hashCode() {
        //TODO
        return 0;
    }
}

package auction.domain;

import java.io.Serializable;
import java.util.Iterator;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Contains a user entity
 */
@Entity
@Table(name = "user")
@NamedQueries({
    @NamedQuery(name = "User.countUsers", query = "select count(user) from User as user"),
    @NamedQuery(name = "User.getAllUsers", query = "select user from User as user"),
    @NamedQuery(name = "User.findUserByEmail", query = "select user from User as user where user.email = :email"),
    @NamedQuery(name = "User.getUserOfferings", query = "select i from Item as i where i.seller = :user ")
})
public class User implements Serializable {

    /**
     * The email address from the user
     */
    @Id
    @GeneratedValue
    private long Id;

    @Column(unique = true)
    private String email;

    @OneToMany(mappedBy = "seller")
    private Set<Item> offeredItems;

    public User(String email) {
        this.email = email;
    }

    /**
     * Empty con
     */
    public User() {
    }

    /**
     * Returns the id selected user
     *
     * @return id
     */
    public long getId() {
        return Id;
    }

    /**
     * Set id
     */
    public void setId(long id) {
        this.Id = id;
    }

    /**
     * Returns the email address from the selected user
     *
     * @return The email address
     */
    public String getEmail() {
        return email;
    }

    /**
     * Set the email address from the selected user
     */
    public void setEmail(String email) {
        this.email = email;
    }

    public Iterator<Item> getOfferedItems() {
        return offeredItems.iterator();
    }

    public int numberOfOfferdItems() {
        return offeredItems.size();
    }

    private void addItem(Item item) {
		offeredItems.add(item);
		item.setSeller(this);
	}

	public void addItemToUser(Item item){
		if (item != null && !item.getDescription().isEmpty()){
			addItem(item);
		}
	}
}

package auction.domain;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 * Contains a user entity
 */
@Entity
public class User {

    /**
     * The email address from the user
     */
    @Id
    private String email;

    @OneToMany(mappedBy = "seller", cascade = CascadeType.PERSIST)
    private List<Item> items = new ArrayList<>();

    @OneToMany(mappedBy = "buyer", cascade = CascadeType.PERSIST)
    private List<Bid> bids = new ArrayList<>();

    /**
     * Empty con
     */
    public User() {
    }

    /**
     * Constructs a user using the email address
     *
     * @param email
     */
    public User(String email) {
        this.email = email;

    }

    /**
     * Returns the email address from the selected user
     *
     * @return The email address
     */
    public String getEmail() {
        return email;
    }
}

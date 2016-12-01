package auction.domain;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class User {

    /**
     * The email address from the user
     */
    @Id
    private String email;

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

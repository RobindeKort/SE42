package auction.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Contains a user entity
 */
@Entity
@Table(name = "user")
@NamedQueries({
    @NamedQuery(name = "User.countUsers", query = "select count(user) from User as user"),
    @NamedQuery(name = "User.getAllUsers", query = "select user from User as user"),
    @NamedQuery(name = "User.findUserByEmail", query = "select user from User as user where user.email = :email")
})
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class User {

    /**
     * The email address from the user
     */
    @Id
    @GeneratedValue
    private long Id;
    @Column(unique = true)
    private String email;

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
}

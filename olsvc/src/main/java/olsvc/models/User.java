package olsvc.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "users")
public class User {

    //id
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long user_id;

    //email
    @NotNull
    @Column(unique = true)
    private String email;

    //login
    @NotNull
    @Column(unique = true)
    private String login;


    public User() { }

    public User(long id) {
        this.user_id = id;
    }

    public User(String email, String login) {
        this.email = email;
        this.login = login;
    }

    // Getter and setter methods

    public long getId() {
        return user_id;
    }

    public void setId(long value) {
        this.user_id = value;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String value) {
        this.email = value;
    }

    public String getName() {
        return login;
    }

    public void setName(String value) {
        this.login = value;
    }
}

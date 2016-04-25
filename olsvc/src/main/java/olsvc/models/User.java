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

    //password
    @NotNull
    private String password;

    public User() { }

    public User(long id) {
        this.user_id = id;
    }

    public User(String email, String login, String password) {
        this.email = email;
        this.login = login;
        this.password = password;
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

    public String getLogin() {
        return login;
    }

    public void setLogin(String value) {
        this.login = value;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

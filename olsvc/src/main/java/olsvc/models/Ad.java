package olsvc.models;


import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "ads")
public class Ad {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long ad_id;

    @JoinColumn(name = "user_id")
    private long user;

    private String title;

    private String category;

    private String description;

    private String city;

    public Ad() { }

    public Ad(long id) {
        this.ad_id = id;
    }

    public Ad(String title, String category, String description) {
        this.title = title;
        this.category = category;
        this.description = description;
    }

    public long getAd_id() {
        return ad_id;
    }

    public void setAd_id(long ad_id) {
        this.ad_id = ad_id;
    }

    public long getUser() {
        return user;
    }

    public void setUser(long user) {
        this.user = user;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }


    //@Temporal(TemporalType.DATE)
    //@Column(name = "data_dodania")
    //private Date publishedDate;

    //private int period;
}

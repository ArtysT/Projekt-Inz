package olsvc.models;


import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "ads")
public class Ad {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long ad_id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;

    private String title;

    private String description;

    @Temporal(TemporalType.DATE)
    @Column(name = "data_dorania")
    private Date publishedDate;

    private int period;
}

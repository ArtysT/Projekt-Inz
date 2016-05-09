package olsvc.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "companies")
public class Company {

    //id
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "company_id")
    private long company_id;

    //Nazwa Przedsiembiorstwa
    private String nazwa_przed;

    //Adres
    private String adres;

    //kod poccztowy
    private String kod;

    //Miejscowosc
    private String city;

    //wojewodztwo
    private String wojewodztwo;

    //kraj
    private String country;

    //tel
    private int phone;

    //krs
    private int krs;

    public String getNazwa_przed() {
        return nazwa_przed;
    }

    public void setNazwa_przed(String nazwa_przed) {
        this.nazwa_przed = nazwa_przed;
    }

    public String getAdres() {
        return adres;
    }

    public void setAdres(String adres) {
        this.adres = adres;
    }

    public String getKod() {
        return kod;
    }

    public void setKod(String kod) {
        this.kod = kod;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getWojewodztwo() {
        return wojewodztwo;
    }

    public void setWojewodztwo(String wojewodztwo) {
        this.wojewodztwo = wojewodztwo;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public int getKrs() {
        return krs;
    }

    public void setKrs(int krs) {
        this.krs = krs;
    }

    public Company() { }

    public Company(long id) {
        this.company_id = id;
    }

    public Company(String nazwa_przed, int phone, int krs) {
        this.nazwa_przed = nazwa_przed;
        this.phone = phone;
        this.krs = krs;
    }

    // Getter and setter methods

    public long getId() {
        return company_id;
    }

    public void setId(long value) {
        this.company_id = value;
    }
}

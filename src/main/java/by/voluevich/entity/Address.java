package by.voluevich.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private boolean isPrimary;
    private String city;
    private String street;
    private int numHome;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    public Address(int id, boolean isPrimary, String city, String street, int numHome, User user) {
        this.id = id;
        this.isPrimary = isPrimary;
        this.city = city;
        this.street = street;
        this.numHome = numHome;
        this.user = user;
    }

    public Address(boolean isPrimary, String city, String street, int numHome, User user) {
        this.isPrimary = isPrimary;
        this.city = city;
        this.street = street;
        this.numHome = numHome;
        this.user = user;
    }

    public Address(int id, String city, String street, int numHome, User user) {
        this.id = id;
        this.city = city;
        this.street = street;
        this.numHome = numHome;
        this.user = user;
    }

    public Address() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isPrimary() {
        return isPrimary;
    }

    public void setPrimary(boolean primary) {
        isPrimary = primary;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public int getNumHome() {
        return numHome;
    }

    public void setNumHome(int numHome) {
        this.numHome = numHome;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return getId() == address.getId() && isPrimary() == address.isPrimary() && Objects.equals(getUser(), address.getUser());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), isPrimary(), getUser());
    }

    @Override
    public String toString() {
        return "Address{" +
                "id=" + id +
                ", city='" + city + '\'' +
                ", street='" + street + '\'' +
                ", numHome=" + numHome +
                ", user=" + user +
                '}';
    }
}

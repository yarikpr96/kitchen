package Class.Entity;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int id_p;
    @Column
    private String name;
    @Lob
    @Column
    private byte[] image = new byte[1];
    @Temporal(TemporalType.DATE)
    private Date date;






    @OneToMany(fetch = FetchType.EAGER, mappedBy = "product")
    private List<Kitchen> kitchenList;

    @ManyToOne (fetch = FetchType.EAGER)
    private Customer customer;

    public Product() {
        this.date = Calendar.getInstance().getTime();
    }

    public int getId_p() {
        return id_p;
    }

    public void setId_p(int id_p) {
        this.id_p = id_p;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", date=" + date +
                '}';
    }


}

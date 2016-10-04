package Class.Entity;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Ярослав on 16.08.2016.
 */
@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int id;
    @Column
    private String name;
    @Column
    private String surname;
    @Column(nullable = false, unique = true)
    private String phone;
    @Column
    private String password;
    @Transient
    private String passwordConfirm;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "customer")
    private List<Kitchen> kitchenList;


    @OneToMany(fetch = FetchType.EAGER, mappedBy = "customer")
    private List<Product>productList;



    public Customer() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordConfirm() {
        return passwordConfirm;
    }

    public void setPasswordConfirm(String passwordConfirm) {
        this.passwordConfirm = passwordConfirm;
    }

    @Override
    public String toString() {
        return "Customer{" +
                " name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}

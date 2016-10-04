package Class.Entity;

import javax.persistence.*;
import java.util.List;

@Entity
public class Kitchen {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int id_k;


    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "kitchen_recipe", joinColumns = @JoinColumn(name = "id_k"), inverseJoinColumns = @JoinColumn(name = "id_r"))
    private List<Recipe> recipeList;

    @ManyToOne(fetch = FetchType.EAGER)
    private Customer customer;

    @ManyToOne(fetch = FetchType.EAGER)
    private Product product;


    public Kitchen() {
    }

    public int getId_k() {
        return id_k;
    }

    public void setId_k(int id_k) {
        this.id_k = id_k;
    }

    public List<Recipe> getRecipeList() {
        return recipeList;
    }

    public void setRecipeList(List<Recipe> recipeList) {
        this.recipeList = recipeList;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Override
    public String toString() {
        return "Kitchen{" +
                "id_k=" + id_k +
                '}';
    }
}

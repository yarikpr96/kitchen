package Class.Entity;

import javax.persistence.*;
import java.util.List;

@Entity
public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int id_r;
    @Column
    private String name;
    @Column
    private String category;
    @Column
    private String description;
    @Lob
    @Column
    private byte[] image = new byte[1];
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "recipe_product",joinColumns = @JoinColumn(name = "id_r"),inverseJoinColumns = @JoinColumn(name = "id_ap"))
    private List<AProduct>productList;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "kitchen_recipe",joinColumns = @JoinColumn(name = "id_r"),inverseJoinColumns = @JoinColumn(name = "id_k"))
    private List<Kitchen>kitchenList;

    public Recipe() {
    }


    public int getId_r() {
        return id_r;
    }

    public void setId_r(int id_r) {
        this.id_r = id_r;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
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

    public List<AProduct> getProductList() {
        return productList;
    }

    public void setProductList(List<AProduct> productList) {
        this.productList = productList;
    }

    public List<Kitchen> getKitchenList() {
        return kitchenList;
    }

    public void setKitchenList(List<Kitchen> kitchenList) {
        this.kitchenList = kitchenList;
    }

    @Override
    public String toString() {
        return "Recipe{" +
                "id_r=" + id_r +
                ", name='" + name + '\'' +
                ", productList=" + productList +
                '}';
    }
}

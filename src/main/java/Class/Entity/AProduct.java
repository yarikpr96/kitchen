package Class.Entity;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by Ярослав on 02.10.2016.
 */
@Entity
public class AProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int id_ap;
    @Column
    private String name;
    @Lob
    @Column
    private byte[] image = new byte[1];
    @Temporal(TemporalType.DATE)
    private Date date;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "recipe_product", joinColumns = @JoinColumn(name = "id_ap"), inverseJoinColumns = @JoinColumn(name = "id_r"))
    private List<Recipe> recipeList;

    public AProduct() {
        this.date = Calendar.getInstance().getTime();
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

    public int getId_ap() {
        return id_ap;
    }

    public void setId_ap(int id_ap) {
        this.id_ap = id_ap;
    }

    public List<Recipe> getRecipeList() {
        return recipeList;
    }

    public void setRecipeList(List<Recipe> recipeList) {
        this.recipeList = recipeList;
    }

    @Override
    public String toString() {
        return "AProduct{" +
                "name='" + name + '\'' +
                ", date=" + date +
                '}';
    }


}


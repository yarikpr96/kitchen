package Class.DTO;

import java.util.Date;

/**
 * Created by Ярослав on 16.09.2016.
 */
public class ProductDTO {
    private int id_p;
    private String name;
    private String image;
    private Date date;

    public ProductDTO() {
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}

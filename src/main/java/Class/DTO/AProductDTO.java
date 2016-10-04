package Class.DTO;

import java.util.Date;

/**
 * Created by Ярослав on 02.10.2016.
 */
public class AProductDTO {


    private int id_ap;

    private String name;

    private String image;

    private Date date;

    public AProductDTO() {
    }

    public int getId_ap() {
        return id_ap;
    }

    public void setId_ap(int id_ap) {
        this.id_ap = id_ap;
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

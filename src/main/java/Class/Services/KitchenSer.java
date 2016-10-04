package Class.Services;

import Class.Entity.Kitchen;

import java.util.List;

/**
 * Created by Ярослав on 16.09.2016.
 */
public interface KitchenSer {
    void addOrEdit(Kitchen kitchen);
    void delete(int id_k);
    Kitchen findOne(int id_k);
    List<Kitchen> findAll();
}

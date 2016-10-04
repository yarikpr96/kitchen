package Class.Services.Implementation;

import Class.Entity.Kitchen;
import Class.Repository.KitchenRepo;
import Class.Services.KitchenSer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Ярослав on 16.09.2016.
 */
@Service
public class KitchenSerImpl implements KitchenSer {
    @Autowired
    private KitchenRepo kitchenRepo;

    @Override
    public void addOrEdit(Kitchen kitchen) {
        kitchenRepo.save(kitchen);
    }

    @Override
    public void delete(int id_k) {
        kitchenRepo.delete(id_k);

    }

    @Override
    public Kitchen findOne(int id_k) {
        return kitchenRepo.findOne(id_k);
    }

    @Override
    public List<Kitchen> findAll() {
        return kitchenRepo.findAll();
    }
}

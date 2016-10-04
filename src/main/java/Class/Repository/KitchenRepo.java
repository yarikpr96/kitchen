package Class.Repository;

import Class.Entity.Kitchen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by Ярослав on 16.09.2016.
 */
public interface KitchenRepo extends JpaRepository<Kitchen,Integer> {
    @Query("FROM Kitchen ")
    List<Kitchen> findAll ();


}

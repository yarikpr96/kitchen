package Class.Repository;

import Class.Entity.AProduct;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by Ярослав on 02.10.2016.
 */
public interface AProductRepo extends JpaRepository<AProduct,Integer> {
    @Query("from AProduct  p where p.name LIKE :name")
    List<AProduct> findByName (@Param("name")String name);

    @Query("FROM AProduct ")
    List<AProduct> findAll ();

}

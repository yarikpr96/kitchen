package Class.Repository;

import Class.Entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by Ярослав on 16.09.2016.
 */
public interface ProductRepo extends JpaRepository<Product,Integer> {

    @Query("from Product p where p.name LIKE :name")
    List<Product> findByName (@Param("name")String name);

    @Query("FROM Product ")
    List<Product> findAll ();

}

package Class.Repository;

import Class.Entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by Ярослав on 16.09.2016.
 */
public interface CustomerRepo extends JpaRepository<Customer,Integer> {
    @Query( " FROM Customer u WHERE  u.phone LIKE :login" )
    Customer findByLogin(@Param("login") String login);
    @Query("FROM Customer ")
    List<Customer> findAll ();
}

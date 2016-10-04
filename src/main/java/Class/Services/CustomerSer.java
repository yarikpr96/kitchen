package Class.Services;

import Class.Entity.Customer;

import java.util.List;

/**
 * Created by Ярослав on 16.09.2016.
 */
public interface CustomerSer {
    void addOrEdit(Customer ordering);
    void delete(int id);
    Customer findOne(int id);
    List<Customer> findAll();
}

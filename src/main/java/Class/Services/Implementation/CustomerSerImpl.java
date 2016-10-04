package Class.Services.Implementation;

import Class.Entity.Customer;
import Class.Repository.CustomerRepo;
import Class.Services.CustomerSer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.persistence.NoResultException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by Ярослав on 16.08.2016.
 */
@Service
public class CustomerSerImpl implements CustomerSer, UserDetailsService {
    @Autowired
    private CustomerRepo customerRepo ;

    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        Customer customer;

        try {
            customer = (Customer) customerRepo.findByLogin(login);
        } catch (NoResultException e) {
            return null;
        }
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<SimpleGrantedAuthority>();
        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        return new org.springframework.security.core.userdetails.User(String.valueOf(customer.getId()), customer.getPassword(), authorities);
    }
    @Override
    public void addOrEdit(Customer ordering) {
        customerRepo.save( ordering );
    }

    @Override
    public void delete(int id) {
        customerRepo.delete( id );
    }

    @Override
    public Customer findOne(int id) {
        return customerRepo.findOne( id);
    }

    @Override
    public List<Customer> findAll() {
        return customerRepo.findAll();
    }
}

package Class.Validations;

import Class.Entity.Customer;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Ярослав on 04.08.2016.
 */
@Component
public class CustomersVal implements Validator {
    Pattern pattern = Pattern.compile("(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])");
    Pattern pattern2 = Pattern.compile("^[+]{0,1}[- (),0-9]{7,}$");
    Pattern pattern3 = Pattern.compile("^[A-Za-z_-]{3,15}$");
    Pattern pattern4 = Pattern.compile("^[A-Za-z_-]{2,15}$");

    public boolean supports(Class<?> aClass) {
        return Customer.class.equals(aClass);
    }

    public void validate(Object o, Errors errors) {
        Customer customer = (Customer) o;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "name.empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "surname", "surname.empty");

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "phone", "phone.empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "password.empty");


        boolean passwordSwitcher = true;

        if (customer.getPassword().length() < 8) {
            errors.rejectValue("password", "password.too.short");
            passwordSwitcher = false;
        }
        if (passwordSwitcher) {
            if (!customer.getPassword().equals(customer.getPasswordConfirm())) {
                errors.rejectValue("password", "bad.password");
            }
        }

        Matcher matcher;


        if (!(matcher = pattern2.matcher(customer.getPhone())).matches()) {
            errors.rejectValue("phone", "phone.incorrect");
        }

        if (!(matcher = pattern3.matcher(customer.getName())).matches()) {
            errors.rejectValue("name", "bad.name");
        }
        if (!(matcher = pattern4.matcher(customer.getSurname())).matches()) {
            errors.rejectValue("surname", "bad.surname");
        }
    }
}


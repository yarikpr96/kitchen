package Class.Services;

import Class.DTO.ProductDTO;
import Class.Entity.Product;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;

/**
 * Created by Ярослав on 16.09.2016.
 */
public interface ProductSer {
    void addOrEdit(Product product);

    void delete(int id_p);

    void edit(int id_p, String name, Date date, MultipartFile multipartFile);

    ProductDTO findOne(int id_p);

    Product findOneById(int id_p);

    List<ProductDTO> findAll();

    void editt(int id_p, Date date);
    List<Product> findAllND();
}

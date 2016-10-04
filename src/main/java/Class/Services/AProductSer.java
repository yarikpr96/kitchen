package Class.Services;

import Class.DTO.AProductDTO;
import Class.DTO.ProductDTO;
import Class.Entity.AProduct;
import Class.Entity.Product;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;

/**
 * Created by Ярослав on 16.09.2016.
 */
public interface AProductSer {
    void addOrEdit(AProduct aProduct);

    void delete(int id_ap);

    void edit(int id_ap, String name, Date date, MultipartFile multipartFile);

    AProductDTO findOne(int id_ap);

    AProduct findOneById(int id_ap);

    List<AProductDTO> findAll();

    void editt(int id_ap, Date date);
}

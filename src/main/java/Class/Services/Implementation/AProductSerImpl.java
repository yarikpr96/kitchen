package Class.Services.Implementation;

import Class.DTO.AProductDTO;
import Class.DTO.ProductDTO;
import Class.Entity.AProduct;
import Class.Entity.Product;
import Class.Repository.AProductRepo;
import Class.Services.AProductSer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.List;

/**
 * Created by Ярослав on 02.10.2016.
 */
@Service
public class AProductSerImpl implements AProductSer {
    @Autowired
    private AProductRepo aProductRepo;

    @Override
    public void addOrEdit(AProduct aProduct) {
        aProductRepo.save(aProduct);
    }

    @Override
    public void delete(int id_ap) {
        aProductRepo.delete(id_ap);
    }

    @Override
    public void edit(int id_ap, String name, Date date, MultipartFile multipartFile) {
        AProduct aProduct = aProductRepo.findOne(id_ap);
        aProduct.setName(name);
        aProduct.setDate(date);
        try {
            aProduct.setImage(multipartFile.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
        aProductRepo.save(aProduct);
    }

    @Override
    public AProductDTO findOne(int id_ap) {
        AProduct aProduct = aProductRepo.findOne(id_ap);
        AProductDTO aProductDTO = new AProductDTO();
        aProductDTO.setId_ap(aProduct.getId_ap());
        aProductDTO.setName(aProduct.getName());
        aProductDTO.setDate(aProduct.getDate());
        String image = Base64.getEncoder().encodeToString(aProduct.getImage());
        aProductDTO.setImage(image);
        return aProductDTO;
    }

    @Override
    public AProduct findOneById(int id_ap) {
        return aProductRepo.findOne(id_ap);
    }

    @Override
    public List<AProductDTO> findAll() {
        List<AProductDTO> aProductDTOs = new ArrayList<>();
        List<AProduct> productList = aProductRepo.findAll();

        for (AProduct product : productList) {
            AProductDTO productDTO = new AProductDTO();
            productDTO.setId_ap(product.getId_ap());
            productDTO.setName(product.getName());
            productDTO.setDate(product.getDate());
            String image = Base64.getEncoder().encodeToString(product.getImage());
            productDTO.setImage(image);
            aProductDTOs.add(productDTO);
        }
        return aProductDTOs;
    }

    @Override
    public void editt(int id_ap, Date date) {
        AProduct product = aProductRepo.findOne(id_ap);
        product.setDate(date);
        aProductRepo.save(product);
    }
}

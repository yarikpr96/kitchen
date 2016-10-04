package Class.Services.Implementation;

import Class.DTO.ProductDTO;
import Class.Entity.Product;
import Class.Repository.ProductRepo;
import Class.Services.ProductSer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.List;


@Service
public class ProductSerImpl implements ProductSer {
    @Autowired
    private ProductRepo productRepo;

    @Override
    public void addOrEdit(Product product) {
        productRepo.save(product);

    }

    @Override
    public void delete(int id_p) {
        productRepo.delete(id_p);

    }

    @Override
    public Product findOneById(int id_p) {
        return productRepo.findOne(id_p);
    }


    public void edit(int id_p, String name, Date date, MultipartFile multipartFile) {
        Product product = productRepo.findOne(id_p);
        product.setName(name);
        product.setDate(date);
        try {
            product.setImage(multipartFile.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
        productRepo.save(product);
    }


    public ProductDTO findOne(int id_p) {
        Product product = productRepo.findOne(id_p);
        ProductDTO productDTO = new ProductDTO();
        productDTO.setId_p(product.getId_p());
        productDTO.setName(product.getName());
        productDTO.setDate(product.getDate());
        String image = Base64.getEncoder().encodeToString(product.getImage());
        productDTO.setImage(image);
        return productDTO;
    }

    public List<ProductDTO> findAll() {
        List<ProductDTO> productDTOs = new ArrayList<>();
        List<Product> productList = productRepo.findAll();

        for (Product product : productList) {
            ProductDTO productDTO = new ProductDTO();
            productDTO.setId_p(product.getId_p());
            productDTO.setName(product.getName());
            productDTO.setDate(product.getDate());
            String image = Base64.getEncoder().encodeToString(product.getImage());
            productDTO.setImage(image);
            productDTOs.add(productDTO);
        }
        return productDTOs;
    }


    public List<ProductDTO> findByName(String name) {
        List<ProductDTO> productDTOs = new ArrayList<>();
        List<Product> productList = productRepo.findByName(name);
        for (Product product : productList) {
            ProductDTO productDTO = new ProductDTO();
            productDTO.setId_p(product.getId_p());
            productDTO.setDate(product.getDate());
            productDTO.setName(product.getName());
            String image = Base64.getEncoder().encodeToString(product.getImage());
            productDTO.setImage(image);
            productDTOs.add(productDTO);
        }
        return productDTOs;
    }

    public void editt(int id_p, Date date) {
        Product product = productRepo.findOne(id_p);
        product.setDate(date);
        productRepo.save(product);
    }

    @Override
    public List<Product> findAllND() {
        return productRepo.findAll();
    }

}

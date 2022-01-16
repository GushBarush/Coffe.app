package com.example.coffeapp.service;

import com.example.coffeapp.dto.product.ProductDTO;
import com.example.coffeapp.dto.product.ProductPriceDTO;
import com.example.coffeapp.dto.product.ProductSizeDTO;
import com.example.coffeapp.entity.product.Product;
import com.example.coffeapp.entity.product.ProductPrice;
import com.example.coffeapp.repository.ProductPriceRepo;
import com.example.coffeapp.repository.ProductRepo;
import com.example.coffeapp.repository.ProductSizeRepo;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class ProductService {

    private final ProductRepo productRepo;
    private final ProductPriceRepo productPriceRepo;
    private final ProductSizeRepo productSizeRepo;

    public List<ProductDTO> allProduct(){
        List<Product> productsEntity = productRepo.findAll();
        List<ProductDTO> productDTOS = new ArrayList<>();
        ModelMapper mapper = new ModelMapper();

        for (Product product : productsEntity) {
            productDTOS.add(mapper.map(product, ProductDTO.class));
        }
        return productDTOS;
    }

    public void saveNewProduct(ProductPriceDTO productPriceDTO,
                               ProductDTO productDTO) {
        ModelMapper mapper = new ModelMapper();
        
        Product productEntity = mapper.map(productDTO, Product.class);
        ProductPrice productPriceEntity = mapper.map(productPriceDTO, ProductPrice.class);

        productPriceEntity.setProduct(productRepo.save(productEntity));

        productPriceRepo.save(productPriceEntity);
    }

    public List<Object> getNewInfo(boolean dop) {
        List<Object> productInfo = new ArrayList<>();
        
        ProductPriceDTO productPriceDTO = new ProductPriceDTO();
        ProductDTO productDTO = new ProductDTO();
        ModelMapper mapper = new ModelMapper();

        productDTO.setDop(dop);
        if (dop) {
            productDTO.setCategory("Dop");
            productPriceDTO.setProductSize(mapper.map(productSizeRepo.findBySizeName("SMALL"), ProductSizeDTO.class));
        }

        productInfo.add(productDTO);
        productInfo.add(productPriceDTO);
        
        return productInfo;
    }
}

package com.example.coffeapp.service;

import com.example.coffeapp.dto.product.ProductDTO;
import com.example.coffeapp.dto.product.ProductPriceDTO;
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

    public ProductDTO getProductDTO(Long productId) {
        ModelMapper mapper = new ModelMapper();
        Product product = productRepo.findById(productId).orElse(new Product());

        return mapper.map(product, ProductDTO.class);
    }

    public ProductPriceDTO getProductPriceDTO(Long productId) {
        ModelMapper mapper = new ModelMapper();
        Product product = productRepo.findById(productId).orElse(new Product());

        return mapper.map(productPriceRepo.findByProduct(product), ProductPriceDTO.class);
    }

    public void updateProduct(ProductDTO productDTO, ProductPriceDTO productPriceDTO){
        ModelMapper mapper = new ModelMapper();
        Product product = mapper.map(productDTO, Product.class);
        ProductPrice productPrice = mapper.map(productPriceDTO, ProductPrice.class);

        productPriceRepo.save(productPrice);
        productRepo.save(product);
    }

    public void saveNewDopProduct(ProductDTO productDTO, Double price) {
        ModelMapper mapper = new ModelMapper();
        Product product = mapper.map(productDTO, Product.class);
        ProductPrice productPrice = new ProductPrice();

        productPrice.setProduct(productRepo.save(product));
        productPrice.setProductSize(productSizeRepo.findBySizeName("SMALL"));
        productPrice.setPrice(price);

        productPriceRepo.save(productPrice);
    }

    public void saveNewProduct(ProductDTO productDTO, Double priceSmall, Double priceMiddle, Double priceBig) {
        ModelMapper mapper = new ModelMapper();
        Product product = productRepo.save(mapper.map(productDTO, Product.class));
        ProductPrice productPriceSmall = new ProductPrice();
        ProductPrice productPriceMiddle = new ProductPrice();
        ProductPrice productPriceBig = new ProductPrice();

        productPriceSmall.setProduct(product);
        productPriceMiddle.setProduct(product);
        productPriceBig.setProduct(product);

        productPriceSmall.setPrice(priceSmall);
        productPriceMiddle.setPrice(priceMiddle);
        productPriceBig.setPrice(priceBig);

        productPriceSmall.setProductSize(productSizeRepo.findBySizeName("SMALL"));
        productPriceMiddle.setProductSize(productSizeRepo.findBySizeName("MEDIUM"));
        productPriceBig.setProductSize(productSizeRepo.findBySizeName("BIG"));

        productPriceRepo.save(productPriceSmall);
        productPriceRepo.save(productPriceMiddle);
        productPriceRepo.save(productPriceBig);
    }

    public void productDelete(Long productId) {
        Product product = productRepo.findById(productId).orElse(new Product());
        List<ProductPrice> productPrices = productPriceRepo.findAllByProduct(product);

        productPriceRepo.deleteAll(productPrices);
        productRepo.delete(product);
    }
}

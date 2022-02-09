package com.example.coffeapp.service;

import com.example.coffeapp.dto.product.ProductDTO;
import com.example.coffeapp.dto.product.ProductView;
import com.example.coffeapp.entity.product.Product;
import com.example.coffeapp.entity.product.ProductImage;
import com.example.coffeapp.entity.product.ProductPrice;
import com.example.coffeapp.repository.ProductImageRepo;
import com.example.coffeapp.repository.ProductPriceRepo;
import com.example.coffeapp.repository.ProductRepo;
import com.example.coffeapp.repository.ProductSizeRepo;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
public class ProductService {

    final ProductRepo productRepo;
    final ProductPriceRepo productPriceRepo;
    final ProductSizeRepo productSizeRepo;
    final ProductImageRepo productImageRepo;

    public List<ProductDTO> allProduct(){
        List<Product> productsEntity = productRepo.findAllByActive(true);
        List<ProductDTO> productDTOS = new ArrayList<>();
        ModelMapper mapper = new ModelMapper();

        for (Product product : productsEntity) {
            productDTOS.add(mapper.map(product, ProductDTO.class));
        }
        return productDTOS;
    }

    public List<ProductDTO> allProduct(Boolean dop){
        List<Product> productsEntity;
        if (dop) {
            productsEntity = productRepo.findAllByActiveAndByCategory(true, "dop");
        } else {
            productsEntity = productRepo.findAllByActiveAndCategoryIsNot(true, "dop");
        }

        List<ProductDTO> productDTOS = new ArrayList<>();
        ModelMapper mapper = new ModelMapper();

        for (Product product : productsEntity) {
            productDTOS.add(mapper.map(product, ProductDTO.class));
        }
        return productDTOS;
    }

    public List<ProductView> getProductsView(String category) {
        List<ProductView> productViews = new ArrayList<>();
        List<Product> products = productRepo.findAllByActiveAndByCategory(true, category);

        for (Product product : products) {
            ProductView productView = getProductView(product.getId());
            productViews.add(productView);
        }

        return productViews;
    }

    public ProductView getProductView(Long productId) {
        Product product = productRepo.getById(productId);

        ProductView productView = new ProductView();

        productView.setId(product.getId());
        productView.setProductName(product.getProductName());
        productView.setDescription(product.getDescription());
        productView.setCategory(product.getCategory());
        if (product.getProductImage() != null) {
            productView.setImageId(product.getProductImage().getId());
        }

        if(product.getCategory().equals("dop")) {
            productView.setPriceSmall(productPriceRepo
                    .findByActiveAndProductAndProductSize(true, product, productSizeRepo.findBySizeName("SMALL"))
                    .getPrice());
        } else {
            productView.setPriceSmall(productPriceRepo
                    .findByActiveAndProductAndProductSize(true, product, productSizeRepo.findBySizeName("SMALL"))
                    .getPrice());
            productView.setPriceMedium(productPriceRepo
                    .findByActiveAndProductAndProductSize(true, product, productSizeRepo.findBySizeName("MEDIUM"))
                    .getPrice());
            productView.setPriceBig(productPriceRepo
                    .findByActiveAndProductAndProductSize(true, product, productSizeRepo.findBySizeName("BIG"))
                    .getPrice());
        }

        return productView;
    }

    public void saveNewDopProduct(String productName, String description, String category,
                                  Double priceSmall, MultipartFile file) throws IOException {

        Product product = new Product();
        ProductImage productImage;

        if(file.getSize() != 0) {
            productImage = toImageEntity(file);
            product.setProductImage(productImage);
        }

        product.setProductName(productName);
        product.setDescription(description);
        product.setCategory(category);
        product.setActive(true);
        product.setDop(true);

        addNewProductPrice(productRepo.save(product), "SMALL", priceSmall);
    }

    public void saveNewProduct(String productName, String description, String category,
                               Double priceSmall, Double priceMedium, Double priceBig, MultipartFile file) throws IOException {

        Product product = new Product();
        ProductImage productImage;

        if(file.getSize() != 0) {
            productImage = toImageEntity(file);
            product.setProductImage(productImage);
        }

        product.setProductName(productName);
        product.setDescription(description);
        product.setCategory(category);
        product.setActive(true);
        product.setDop(false);

        Product productSaved = productRepo.save(product);

        addNewProductPrice(productSaved, "SMALL", priceSmall);
        addNewProductPrice(productSaved, "MEDIUM", priceMedium);
        addNewProductPrice(productSaved, "BIG", priceBig);
    }

    public void productDelete(Long productId) {
        Product product = productRepo.getById(productId);
        List<ProductPrice> productPrices = productPriceRepo.findAllByActiveAndProduct(true, product);

        for (ProductPrice productPrice : productPrices) {
            productPrice.setActive(false);
            productPriceRepo.save(productPrice);
        }

        product.setActive(false);

        productRepo.save(product);
    }

    public void upgradeProduct(Long productId, String category, String description, String productName,
                                  Double priceSmall, Double priceMedium, Double priceBig, MultipartFile file) throws IOException {

        Product product = productRepo.getById(productId);

        product.setCategory(category);
        product.setDescription(description);
        product.setProductName(productName);

        if(file.getSize() != 0) {
            ProductImage productImage = toImageEntity(file);
            product.setProductImage(productImage);
        }

        Product productSaved = productRepo.save(product);

        List<ProductPrice> productPriceList = productPriceRepo.findAllByActiveAndProduct(true, product);

        for (ProductPrice productPrice : productPriceList) {
            switch (productPrice.getProductSize().getSizeName()) {
                case "SMALL":
                    if(!Objects.equals(productPrice.getPrice(), priceSmall)) {
                        productPrice.setActive(false);
                        productPriceRepo.save(productPrice);
                        addNewProductPrice(productSaved, "SMALL", priceSmall);
                    }
                case "MEDIUM":
                    if(!Objects.equals(productPrice.getPrice(), priceMedium)) {
                        productPrice.setActive(false);
                        productPriceRepo.save(productPrice);
                        addNewProductPrice(productSaved, "MEDIUM", priceMedium);
                    }
                case "BIG":
                    if(!Objects.equals(productPrice.getPrice(), priceBig)) {
                        productPrice.setActive(false);
                        productPriceRepo.save(productPrice);
                        addNewProductPrice(productSaved, "BIG", priceBig);
                    }
            }
        }
    }

    private ProductImage toImageEntity(MultipartFile file) throws IOException {
        ProductImage productImage = new ProductImage();

        productImage.setName(file.getName());
        productImage.setOriginalFileName(file.getOriginalFilename());
        productImage.setContentType(file.getContentType());
        productImage.setSize(file.getSize());
        productImage.setBytes(file.getBytes());

        return productImage;
    }

    private void addNewProductPrice(Product product, String size, Double price) {
        ProductPrice productPrice = new ProductPrice();

        productPrice.setProduct(product);
        productPrice.setPrice(price);
        productPrice.setProductSize(productSizeRepo.findBySizeName(size));
        productPrice.setActive(true);

        productPriceRepo.save(productPrice);
    }
}

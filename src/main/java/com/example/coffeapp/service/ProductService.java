package com.example.coffeapp.service;

import com.example.coffeapp.dto.product.ProductDTO;
import com.example.coffeapp.dto.product.ProductPriceDTO;
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

@Service
@AllArgsConstructor
public class ProductService {

    private final ProductRepo productRepo;
    private final ProductPriceRepo productPriceRepo;
    private final ProductSizeRepo productSizeRepo;
    private final ProductImageRepo productImageRepo;

    public List<ProductDTO> allProduct(){
        List<Product> productsEntity = productRepo.findAll();
        List<ProductDTO> productDTOS = new ArrayList<>();
        ModelMapper mapper = new ModelMapper();

        for (Product product : productsEntity) {
            productDTOS.add(mapper.map(product, ProductDTO.class));
        }
        return productDTOS;
    }

    public List<ProductView> getProductsView(String category) {
        List<ProductView> productViews = new ArrayList<>();
        List<Product> products = productRepo.findAllByCategory(category);

        for (Product product : products) {
            ProductView productView = new ProductView();

            productView.setId(product.getId());
            productView.setProductName(product.getProductName());
            productView.setDescription(product.getDescription());
            productView.setCategory(product.getCategory());
            productView.setImageId(product.getProductImage().getId());

            if(product.getCategory().equals("dop")) {
                productView.setPriceSmall(productPriceRepo.findAllByProductAndProductSize(product, productSizeRepo.findBySizeName("SMALL")).getPrice());
            } else {
                productView.setPriceSmall(productPriceRepo.findAllByProductAndProductSize(product, productSizeRepo.findBySizeName("SMALL")).getPrice());
                productView.setPriceMedium(productPriceRepo.findAllByProductAndProductSize(product, productSizeRepo.findBySizeName("MEDIUM")).getPrice());
                productView.setPriceBig(productPriceRepo.findAllByProductAndProductSize(product, productSizeRepo.findBySizeName("BIG")).getPrice());
            }
            productViews.add(productView);
        }

        return productViews;
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

    public void updateDopProduct(Long productId, Long productPriceId, String productName,
                              Double price, String description, MultipartFile file) throws IOException {

        Product product = productRepo.getById(productId);
        ProductPrice productPrice = productPriceRepo.getById(productPriceId);
        ProductImage productImage;

        if (file.getSize() != 0) {
            productImageRepo.delete(product.getProductImage());
            productImage = toImageEntity(file);
            product.setProductImage(productImage);
        }

        product.setProductName(productName);
        product.setDescription(description);

        productPrice.setPrice(price);
        productPrice.setProduct(productRepo.save(product));

        productPriceRepo.save(productPrice);
    }

    public void updateProduct(Long productId, String productName,
                              Double smallPrice, Double mediumPrice,
                              Double bigPrice, String category,
                              String description, MultipartFile file) throws IOException {

        Product product = productRepo.getById(productId);
        ProductPrice productPriceSmall = productPriceRepo.findAllByProductAndProductSize(product, productSizeRepo.findBySizeName("SMALL"));
        ProductPrice productPriceMedium = productPriceRepo.findAllByProductAndProductSize(product, productSizeRepo.findBySizeName("MEDIUM"));
        ProductPrice productPriceBig = productPriceRepo.findAllByProductAndProductSize(product, productSizeRepo.findBySizeName("BIG"));
        ProductImage productImage;

        if (file.getSize() != 0) {
            productImageRepo.delete(product.getProductImage());
            productImage = toImageEntity(file);
            product.setProductImage(productImage);
        }

        product.setProductName(productName);
        product.setDescription(description);
        product.setCategory(category);

        productPriceSmall.setPrice(smallPrice);
        productPriceMedium.setPrice(mediumPrice);
        productPriceBig.setPrice(bigPrice);

        Product productSaved = productRepo.save(product);

        productPriceSmall.setProduct(productSaved);
        productPriceMedium.setProduct(productSaved);
        productPriceBig.setProduct(productSaved);

        productPriceRepo.save(productPriceSmall);
        productPriceRepo.save(productPriceMedium);
        productPriceRepo.save(productPriceBig);
    }

    public void saveNewDopProduct(ProductDTO productDTO, Double price, MultipartFile file) throws IOException {
        ModelMapper mapper = new ModelMapper();
        Product product = mapper.map(productDTO, Product.class);
        ProductPrice productPrice = new ProductPrice();
        ProductImage productImage;

        if(file.getSize() != 0) {
            productImage = toImageEntity(file);
            product.setProductImage(productImage);
        }

        productPrice.setProduct(productRepo.save(product));
        productPrice.setProductSize(productSizeRepo.findBySizeName("SMALL"));
        productPrice.setPrice(price);

        productPriceRepo.save(productPrice);
    }

    public void saveNewProduct(ProductDTO productDTO, Double priceSmall,
                               Double priceMiddle, Double priceBig,
                               MultipartFile file) throws IOException {

        ModelMapper mapper = new ModelMapper();
        Product product = productRepo.save(mapper.map(productDTO, Product.class));
        ProductPrice productPriceSmall = new ProductPrice();
        ProductPrice productPriceMiddle = new ProductPrice();
        ProductPrice productPriceBig = new ProductPrice();
        ProductImage productImage;

        productPriceSmall.setProduct(product);
        productPriceMiddle.setProduct(product);
        productPriceBig.setProduct(product);

        productPriceSmall.setPrice(priceSmall);
        productPriceMiddle.setPrice(priceMiddle);
        productPriceBig.setPrice(priceBig);

        productPriceSmall.setProductSize(productSizeRepo.findBySizeName("SMALL"));
        productPriceMiddle.setProductSize(productSizeRepo.findBySizeName("MEDIUM"));
        productPriceBig.setProductSize(productSizeRepo.findBySizeName("BIG"));

        if(file.getSize() != 0) {
            productImage = toImageEntity(file);
            product.setProductImage(productImage);
        }

        productPriceRepo.save(productPriceSmall);
        productPriceRepo.save(productPriceMiddle);
        productPriceRepo.save(productPriceBig);
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

    public void productDelete(Long productId) {
        Product product = productRepo.findById(productId).orElse(null);
        List<ProductPrice> productPrices = productPriceRepo.findAllByProduct(product);

        productPriceRepo.deleteAll(productPrices);
        productRepo.delete(product);
    }

    public ProductView getProductPriceView(Long productId) {
        Product product = productRepo.findById(productId).orElse(null);
        ProductView productView = new ProductView();

        productView.setProductName(product.getProductName());
        productView.setDescription(product.getDescription());
        productView.setCategory(product.getCategory());
        productView.setImageId(product.getProductImage().getId());

        productView.setPriceSmall(productPriceRepo.findAllByProductAndProductSize(product, productSizeRepo.findBySizeName("SMALL")).getPrice());
        productView.setPriceMedium(productPriceRepo.findAllByProductAndProductSize(product, productSizeRepo.findBySizeName("MEDIUM")).getPrice());
        productView.setPriceBig(productPriceRepo.findAllByProductAndProductSize(product, productSizeRepo.findBySizeName("BIG")).getPrice());

        return productView;
    }
}

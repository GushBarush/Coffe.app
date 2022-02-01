package com.example.coffeapp.service;

import com.example.coffeapp.dto.order.OrderDTO;
import com.example.coffeapp.entity.order.Order;
import com.example.coffeapp.entity.product.Product;
import com.example.coffeapp.entity.product.ProductPrice;
import com.example.coffeapp.repository.*;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;

@Service
@AllArgsConstructor
public class OrderService {

    final UserService userService;
    final OrderRepo orderRepo;
    final PayDayRepo payDayRepo;
    final ProductRepo productRepo;
    final ProductPriceRepo productPriceRepo;
    final ProductSizeRepo productSizeRepo;

    public OrderDTO newOrder(Long userId, Long payDayId) {
        Order orderEntity = new Order();
        ModelMapper mapper = new ModelMapper();
        LocalDateTime localDateTime = LocalDateTime.now(ZoneId.of("Europe/Moscow"));
        OrderDTO orderDTO;

        orderEntity.setUser(userService.getUserById(userId));
        orderEntity.setPayDay(payDayRepo.getById(payDayId));
        orderEntity.setTime(localDateTime);
        orderEntity.setSum(0.0);

        orderDTO = mapper.map(orderRepo.save(orderEntity), OrderDTO.class);

        return orderDTO;
    }

    public OrderDTO updateOrder(Long orderId, Long productId, String size) {
        Order orderEntity = orderRepo.getById(orderId);
        Product product = productRepo.getById(productId);
        ProductPrice productPrice = productPriceRepo.findAllByProductAndProductSize(product, productSizeRepo.findBySizeName(size));
        ModelMapper mapper = new ModelMapper();
        OrderDTO orderDTO;

        orderEntity.setPrice(productPrice);

        orderDTO = mapper.map(orderRepo.save(orderEntity), OrderDTO.class);

        return orderDTO;
    }
}

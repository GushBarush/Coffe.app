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
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class OrderService {

    final UserService userService;
    final OrderRepo orderRepo;
    final PayDayRepo payDayRepo;
    final ProductRepo productRepo;
    final ProductPriceRepo productPriceRepo;
    final ProductSizeRepo productSizeRepo;

    public List<OrderDTO> allOrder() {
        List<Order> orderList = orderRepo.findAll();
        List<OrderDTO> orderDTOS = new ArrayList<>();
        ModelMapper mapper = new ModelMapper();

        for (Order order : orderList) {
            orderDTOS.add(mapper.map(order, OrderDTO.class));
        }

        return orderDTOS;
    }

    public OrderDTO getOrder(Long id) {
        Order orderEntity = orderRepo.getById(id);
        ModelMapper mapper = new ModelMapper();

        return mapper.map(orderEntity, OrderDTO.class);
    }

    public OrderDTO newOrder(Long userId, Long payDayId) {
        Order orderEntity = new Order();
        ModelMapper mapper = new ModelMapper();
        LocalDateTime localDateTime = LocalDateTime.now(ZoneId.of("Europe/Moscow"));
        OrderDTO orderDTO;

        orderEntity.setUser(userService.getUserById(userId));
        orderEntity.setPayDay(payDayRepo.getById(payDayId));
        orderEntity.setTime(localDateTime);
        orderEntity.setSum(0.0);
        orderEntity.setActive(false);

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
        orderEntity.setSum(orderEntity.getSum() + productPrice.getPrice());

        orderDTO = mapper.map(orderRepo.save(orderEntity), OrderDTO.class);

        return orderDTO;
    }
}

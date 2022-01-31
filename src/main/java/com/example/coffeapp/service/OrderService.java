package com.example.coffeapp.service;

import com.example.coffeapp.dto.order.OrderDTO;
import com.example.coffeapp.entity.order.Order;
import com.example.coffeapp.repository.OrderRepo;
import com.example.coffeapp.repository.PayDayRepo;
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

    public OrderDTO newOrder(Long userId, Long payDayId) {
        Order orderEntity = new Order();
        ModelMapper mapper = new ModelMapper();
        LocalDateTime localDateTime = LocalDateTime.now(ZoneId.of("Europe/Moscow"));
        OrderDTO orderDTO;

        orderEntity.setUser(userService.getUserById(userId));
        orderEntity.setPayDay(payDayRepo.getById(payDayId));
        orderEntity.setTime(localDateTime);

        orderRepo.save(orderEntity);

        orderDTO = mapper.map(orderEntity, OrderDTO.class);

        return orderDTO;
    }
}

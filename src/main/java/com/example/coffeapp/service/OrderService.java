package com.example.coffeapp.service;

import com.example.coffeapp.dto.order.OrderDTO;
import com.example.coffeapp.entity.order.Order;
import com.example.coffeapp.entity.user.User;
import com.example.coffeapp.repository.OrderRepo;
import com.example.coffeapp.repository.PayDayRepo;
import com.example.coffeapp.repository.UserRepo;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;

@Service
@AllArgsConstructor
public class OrderService {

    @Autowired
    final UserRepo userRepo;

    final OrderRepo orderRepo;
    final PayDayRepo payDayRepo;

    public OrderDTO newOrder(User user, Long payDayId) {
        Order orderEntity = new Order();
        ModelMapper mapper = new ModelMapper();
        LocalDateTime localDateTime = LocalDateTime.now(ZoneId.of("Europe/Moscow"));
        OrderDTO orderDTO;

        orderEntity.setUser(userRepo.getById(user.getId()));
        orderEntity.setPayDay(payDayRepo.getById(payDayId));
        orderEntity.setTime(localDateTime);

        orderRepo.save(orderEntity);

        orderDTO = mapper.map(orderEntity, OrderDTO.class);

        return orderDTO;
    }
}

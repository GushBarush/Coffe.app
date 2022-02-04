package com.example.coffeapp.service;

import com.example.coffeapp.dto.order.ExpenseDTO;
import com.example.coffeapp.dto.order.OrderDTO;
import com.example.coffeapp.entity.order.Expense;
import com.example.coffeapp.entity.order.Order;
import com.example.coffeapp.entity.payday.PayDay;
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
    final ExpenseRepo expenseRepo;

    public List<OrderDTO> allOrderByPayDay(Long payDayId) {
        List<Order> orderList = orderRepo.findAllByPayDay(payDayRepo.getById(payDayId));
        List<OrderDTO> orderDTOS = new ArrayList<>();
        ModelMapper mapper = new ModelMapper();

        for (Order order : orderList) {
            orderDTOS.add(mapper.map(order, OrderDTO.class));
        }

        return orderDTOS;
    }

    public List<ExpenseDTO> allExpenses(Long payDayId) {
        List<Expense> expenseList = expenseRepo.findAllByPayDay(payDayRepo.getById(payDayId));
        List<ExpenseDTO> expenseDTOS = new ArrayList<>();
        ModelMapper mapper = new ModelMapper();

        for (Expense expense : expenseList) {
            expenseDTOS.add(mapper.map(expense, ExpenseDTO.class));
        }

        return expenseDTOS;
    }

    public OrderDTO getOrder(Long id) {
        Order orderEntity = orderRepo.getById(id);
        ModelMapper mapper = new ModelMapper();

        return mapper.map(orderEntity, OrderDTO.class);
    }

    public OrderDTO newOrder(Long userId, Long payDayId, Boolean isFree) {
        Order orderEntity = new Order();
        ModelMapper mapper = new ModelMapper();
        LocalDateTime localDateTime = LocalDateTime.now(ZoneId.of("Europe/Moscow"));
        OrderDTO orderDTO;

        orderEntity.setUser(userService.getUserById(userId));
        orderEntity.setPayDay(payDayRepo.getById(payDayId));
        orderEntity.setTime(localDateTime);
        orderEntity.setSum(0.0);
        orderEntity.setActive(true);
        orderEntity.setFree(isFree);

        orderDTO = mapper.map(orderRepo.save(orderEntity), OrderDTO.class);

        return orderDTO;
    }

    public OrderDTO cleanOrder(Long orderId, Long productPriceId) {
        Order order = orderRepo.getById(orderId);
        ModelMapper mapper = new ModelMapper();
        ProductPrice productPrice = productPriceRepo.getById(productPriceId);

        checkOrder(order);
        order.getProductPriceList().remove(productPrice);
        order.setSum(order.getSum() - productPrice.getPrice());

        orderRepo.save(order);

        return mapper.map(order, OrderDTO.class);
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

    public void saveOrder(Long id, Boolean isCash) {
        Order order = orderRepo.getById(id);
        PayDay payDay = order.getPayDay();

        checkOrder(order);

        if (order.isFree()) {
            payDay.setSumFree(payDay.getSumFree() + order.getSum());
        } else {
            if (isCash) {
                payDay.setSumCash(payDay.getSumCash() + order.getSum());
            } else {
                payDay.setSumNotCash(payDay.getSumNotCash() + order.getSum());
            }

            payDay.setSumAll(payDay.getSumAll() + order.getSum());
        }

        updateUser(order);
        order.setCash(isCash);
        order.setActive(false);

        payDayRepo.save(payDay);
        orderRepo.save(order);
    }

    public void deleteOrder(Long id) {
        Order order = orderRepo.getById(id);

        checkOrder(order);

        orderRepo.delete(order);
    }

    private void checkOrder(Order order) {
        PayDay payDay = payDayRepo.getById(order.getPayDay().getId());

        if(order.isFree()) {
            if (!order.isActive()) {
                payDay.setSumFree(payDay.getSumFree() - order.getSum());
            }
        } else {
            if(!order.isActive()) {
                if(order.isCash()) {
                    payDay.setSumCash(payDay.getSumCash() - order.getSum());
                } else {
                    payDay.setSumNotCash(payDay.getSumNotCash() - order.getSum());
                }
                payDay.setSumAll(payDay.getSumAll() - order.getSum());
            }
        }

        payDayRepo.save(payDay);
    }

    private void updateUser(Order order) {
        List<ProductPrice> productPriceList = order.getProductPriceList();

        for (ProductPrice productPrice : productPriceList) {
            if (!productPrice.getProduct().isDop()) {
                if (order.isFree()) {
                    userService.delHappyCoffe(order.getUser());
                } else {
                    userService.addCoffe(order.getUser());
                }
            }
        }
    }

    public void saveExpense(Long payDayId, Double sum, String commit) {
        Expense expense = new Expense();
        PayDay payDay = payDayRepo.getById(payDayId);

        expense.setCommit(commit);
        expense.setSum(sum);
        expense.setPayDay(payDay);

        payDay.setSumExpense(payDay.getSumExpense() + expense.getSum());
        payDay.setSumAll(payDay.getSumAll() - expense.getSum());

        payDayRepo.save(payDay);
        expenseRepo.save(expense);
    }

    public void delExpense(Long expenseId) {
        Expense expense = expenseRepo.getById(expenseId);
        PayDay payDay = payDayRepo.getById(expense.getPayDay().getId());

        payDay.setSumAll(payDay.getSumAll() + expense.getSum());
        payDay.setSumExpense(payDay.getSumExpense() - expense.getSum());

        payDayRepo.save(payDay);
        expenseRepo.save(expense);
    }
}

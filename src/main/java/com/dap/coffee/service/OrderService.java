package com.dap.coffee.service;

import com.dap.coffee.common.ApiResponse;
import com.dap.coffee.common.MessageResponse;
import com.dap.coffee.common.QueueNames;
import com.dap.coffee.entities.Order;
import com.dap.coffee.entities.OrderItem;
import com.dap.coffee.model.request.OrderRequest;
import com.dap.coffee.model.response.OrderResponse;
import com.dap.coffee.repository.OrderRepository;
import com.dap.coffee.utils.ModelMapperUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class OrderService {
    private final JmsTemplate jmsTemplate;

    private final OrderRepository orderRepository;

    public ApiResponse<OrderResponse> saveOrder(OrderRequest orderRequest) {

        Order order = new Order();
        order.setShopId(orderRequest.getShopId());
        order.setUserId(orderRequest.getUserId());
        List<OrderItem> orderItems = orderRequest.getItems()
                .stream()
                .map(i -> OrderItem.builder()
                         .productId(i.getProductId())
                        .quantity(i.getQuantity())
                        .order(order)
                         .build()).collect(Collectors.toList());
        order.setOrderItems(orderItems);

        Order entity = orderRepository.saveAndFlush(order);

        // call core-service to notify the queue
        // TODO: using event-based or async request
        jmsTemplate.convertAndSend(QueueNames.QUEUE_NEW_ORDER, new Order(entity.getId()));
        return ApiResponse.ok(MessageResponse.SUCCESS, ModelMapperUtils.toObject(orderRepository.save(order), OrderResponse.class));
    }

    public ApiResponse<List<OrderResponse>> getAllOrders() {
        List<Order> orders = orderRepository.findAll();
        return ApiResponse.ok(MessageResponse.SUCCESS, orders.stream().map(o -> ModelMapperUtils.toObject(o, OrderResponse.class)).collect(Collectors.toList()));
    }
}

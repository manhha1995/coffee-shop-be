package com.dap.coffee.controller;

import com.dap.coffee.common.ApiResponse;
import com.dap.coffee.model.request.OrderRequest;
import com.dap.coffee.model.response.OrderResponse;
import com.dap.coffee.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/order-creation")
    public ApiResponse<OrderResponse> save(@RequestBody OrderRequest request) {
        return orderService.saveOrder(request);
    }

    @GetMapping("/findAll")
    public ApiResponse<List<OrderResponse>> getAll() {
        return orderService.getAllOrders();
    }
}

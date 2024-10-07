package com.dap.coffee.service;

import com.dap.coffee.common.QueueStatus;
import com.dap.coffee.entities.Queue;
import com.dap.coffee.model.response.CancelOrderResponse;
import com.dap.coffee.repository.QueueRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class CustomerService {

    private final QueueRepository queueRepository;

    public CancelOrderResponse cancelOrder(String id, String orderId) {
        queueRepository.deleteById(orderId);
        log.info("Order with id {} canceled", orderId);
        List<Queue> queuesReady = getReadyQueue(id, QueueStatus.READY);
        log.info("Ready queues: {}", queuesReady);
        queuesReady.stream().filter(q -> q.getOrder().getId().equals(orderId)).forEach(q -> q.setStatus(QueueStatus.CANCELED));

        return new CancelOrderResponse("Order with id " + orderId + " canceled");
    }

    public List<Queue> getReadyQueue(String id, String status) {
        return queueRepository.findQueuesByIdAndStatus(id, status);
    }
}

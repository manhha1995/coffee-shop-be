package com.dap.coffee.repository;

import com.dap.coffee.entities.Queue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QueueRepository extends JpaRepository<Queue, String> {
    List<Queue> findQueuesByIdAndStatus(String id, String status);
}

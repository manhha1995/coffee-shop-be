package com.dap.coffee.repository;

import com.dap.coffee.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface OrderRepository extends JpaRepository<Order, String> {
    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(
            value = "UPDATE orders  SET status = ?2 WHERE id = ?1",
            nativeQuery = true
    )
    int updateStatus(String orderId, String status);
}

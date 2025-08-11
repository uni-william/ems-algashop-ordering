package com.algaworks.algashop.ordering.infrastructure.persistence.repository;


import com.algaworks.algashop.ordering.infrastructure.persistence.entity.OrderPersistenceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public interface OrderPersistenceEntityRepository  extends JpaRepository<OrderPersistenceEntity, Long> {

    @Query("""
        SELECT o
        FROM OrderPersistenceEntity o
        WHERE o.customer.id = :customerId
          AND YEAR(o.placedAt) = :year
    """)
    List<OrderPersistenceEntity> placedByCustomerInYear(@Param("customerId") UUID customerId, @Param("year") Integer year);

    @Query("""
      SELECT COUNT(o)
      FROM OrderPersistenceEntity o
      WHERE o.customer.id = :customerId
        AND YEAR(o.placedAt) = :year
        AND o.paidAt IS NOT NULL
        AND o.canceledAt IS NULL
    """)
    long salesQuantityByCustomerInYear(@Param("customerId") UUID customerId, @Param("year") Integer year);

    @Query("""
      SELECT COALESCE(SUM(o.totalAmount), 0)
      FROM OrderPersistenceEntity o
      WHERE o.customer.id = :customerId
        AND o.canceledAt IS NULL
        AND o.paidAt IS NOT NULL
    """)
    BigDecimal totalSoldForCustomer(@Param("customerId")UUID customerId);

}

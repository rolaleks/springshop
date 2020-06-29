package ru.geekbrains.persist.repo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.geekbrains.persist.enity.Product;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    Page<Product> findByCostBetween(BigDecimal min, BigDecimal max, Pageable pageable);

    Page<Product> findByCostGreaterThanEqual(BigDecimal cost, Pageable pageable);

    Page<Product> findByCostLessThanEqual(BigDecimal cost, Pageable pageable);

    List<Product> findByCostAndTitleAndIdNot(BigDecimal cost, String title, Long id);

    @Query("SELECT p FROM Product p WHERE (:min is null or p.cost >= :min) " +
            "and (:max is null or p.cost <= :max)" +
            "and (:title is null or p.title like CONCAT('%',:title,'%'))")
    Page<Product> findProductByParams(@Param("min") BigDecimal min, @Param("max") BigDecimal max, @Param("title") String title, Pageable pageable);
}

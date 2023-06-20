package com.api.repo;

import com.api.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;

public interface ProductRepo extends JpaRepository<Product,Integer> {

//    @Query("SELECT COUNT(s) FROM Sales s WHERE s.department = :department " +
//            "AND FUNCTION('quarter', s.saleDate) = 3 " +
//            "AND FUNCTION('year', s.saleDate) = FUNCTION('year', :endDate) " +
//            "AND s.saleDate >= :startDate AND s.saleDate <= :endDate")
//     int getTotalItemsSoldInQ3(@Param("startDate") Date startDate,
//                              @Param("endDate") Date endDate,
//                              @Param("department") String department);
}

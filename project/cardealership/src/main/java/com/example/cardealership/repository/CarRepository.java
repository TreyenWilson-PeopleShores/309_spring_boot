package com.example.cardealership.repository;

import com.example.cardealership.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CarRepository extends JpaRepository<Car, Long> {
    List<Car> findByMake(String make);
    List<Car> findByColor(String color);
    List<Car> findByYear(int year);
    List<Car> findByPriceBetween(double min, double max);
    List<Car> findByMakeContainingIgnoreCase(String keyword);
    List<Car> findByOwnerIsNull();
    List<Car> findByOwnerIsNotNull();
    @Query("SELECT c FROM Car c WHERE LOWER(c.make) LIKE LOWER(CONCAT('%', :keyword, '%')) " +
            "OR LOWER(c.model) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<Car> searchByKeyword(@Param("keyword") String keyword);
}
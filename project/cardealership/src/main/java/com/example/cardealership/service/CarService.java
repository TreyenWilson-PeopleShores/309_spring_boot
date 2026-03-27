
package com.example.cardealership.service;
import com.example.cardealership.dto.CarRequest;
import com.example.cardealership.dto.CarResponse;
import java.util.List;
import com.example.cardealership.repository.CarRepository.*;
import com.example.cardealership.entity.Car;
import com.example.cardealership.entity.Owner;
public interface CarService {
    List<CarResponse> getAllCars();
    CarResponse getCarById(Long id);
    CarResponse createCar(CarRequest request);
    CarResponse updateCar(Long id, CarRequest request);
    CarResponse assignOwner(Long carId, Long ownerId);
    void deleteCar(Long id);

}


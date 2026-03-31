package com.example.cardealership.service;

import com.example.cardealership.dto.CarRequest;
import com.example.cardealership.dto.CarResponse;
import com.example.cardealership.entity.Car;
import com.example.cardealership.exception.ResourceNotFoundException;
import com.example.cardealership.mapper.CarMapper;
import com.example.cardealership.repository.CarRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.example.cardealership.entity.Car.*;

import java.util.List;

@Service
public class CarServiceImpl implements CarService {

    private final CarRepository carRepository;
    private final CarMapper carMapper;

    public CarServiceImpl(CarRepository carRepository, CarMapper carMapper) {
        this.carRepository = carRepository;
        this.carMapper = carMapper;
    }


    /*public List<CarResponse> getAllCars() {
        return carRepository.findAll()
                .stream()
                .map(carMapper::toResponse)
                .toList();
    }*/
    @Override
    public Page<CarResponse> getAllCars(int page, int size, String sortBy, String direction) {
        Sort sort = direction.equalsIgnoreCase("desc")
                ? Sort.by(sortBy).descending()
                : Sort.by(sortBy).ascending();

        Pageable pageable = PageRequest.of(page, size, sort);

        return carRepository.findAll(pageable)
                .map(carMapper::toResponse);
    }


    @Override
    public CarResponse createCar(CarRequest request) {
        Car car = carMapper.toEntity(request);
        Car saved = carRepository.save(car);
        return carMapper.toResponse(saved);
    }



    @Override // MAY BE WRONG EDITOR ADDED
    public CarResponse assignOwner(Long carId, Long ownerId) {
        return null;
    } // MAY BE WRONG EDITOR ADDED

    @Override
    public CarResponse getCarById(Long id) {
        Car car = carRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Car", id));
        return carMapper.toResponse(car);
    }

    @Override
    public CarResponse updateCar(Long id, CarRequest request) {
        Car car = carRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Car", id));
        carMapper.updateEntity(car, request);
        return carMapper.toResponse(carRepository.save(car));
    }

    @Override
    public void deleteCar(Long id) {
        Car car = carRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Car", id));
        carRepository.delete(car);
    }
    @Override
    public List<CarResponse> searchCars(String keyword) {
        //System.out.println("In Implementation");
        return carRepository.searchByKeyword(keyword)
                .stream()
                .map(carMapper::toResponse)
                .toList();
    }

}
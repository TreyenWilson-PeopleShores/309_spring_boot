package com.example.cardealership.service;
import com.example.cardealership.mapper.CarMapper;
import  com.example.cardealership.mapper.CarMapper.*;
import com.example.cardealership.dto.CarResponse;
import com.example.cardealership.dto.OwnerRequest;
import com.example.cardealership.dto.OwnerResponse;
import com.example.cardealership.entity.Car;
import com.example.cardealership.entity.Owner;
import com.example.cardealership.mapper.OwnerMapper;
import com.example.cardealership.repository.CarRepository;
import com.example.cardealership.repository.OwnerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OwnerServiceImpl implements OwnerService {

    private final OwnerRepository ownerRepository;
    private final OwnerMapper ownerMapper;
    private final CarRepository carRepository;
    private final CarMapper carMapper;
    public OwnerServiceImpl(OwnerRepository ownerRepository, OwnerMapper ownerMapper, OwnerRepository ownerrepository, OwnerRepository ownerepository, OwnerRepository owneRepository, CarRepository carRepository, CarMapper carMapper) {
        this.ownerRepository = ownerRepository;
        this.ownerMapper = ownerMapper;
        this.carRepository = carRepository;
        this.carMapper = carMapper;
    }

    @Override
    public List<OwnerResponse> getAllOwners() {
        return ownerRepository.findAll().stream()
                .map(ownerMapper::toResponse)
                .toList();
    }

    @Override
    public OwnerResponse getOwnerById(Long id) {
        Owner owner = ownerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Owner not found with id: " + id));
        return ownerMapper.toResponse(owner);
    }

    @Override
    public OwnerResponse createOwner(OwnerRequest request) {
        Owner owner = ownerMapper.toEntity(request);
        Owner saved = ownerRepository.save(owner);
        return ownerMapper.toResponse(saved);
    }
    @Override
    public CarResponse assignOwner(Long carId, Long ownerId) {
        Car car = carRepository.findById(carId)
                .orElseThrow(() -> new RuntimeException("Car not found"));
        Owner owner = ownerRepository.findById(ownerId)
                .orElseThrow(() -> new RuntimeException("Owner not found"));
        car.setOwner(owner);
        Car saved = carRepository.save(car);
        return carMapper.toResponse(saved);
    }
}
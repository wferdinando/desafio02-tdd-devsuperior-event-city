package com.devsuperior.bds02.services;

import javax.persistence.EntityNotFoundException;

import org.springframework.stereotype.Service;

import com.devsuperior.bds02.dto.EventDTO;
import com.devsuperior.bds02.entities.City;
import com.devsuperior.bds02.repositories.EventRepository;
import com.devsuperior.bds02.services.exceptions.ResourceNotFoundException;

@Service
public class EventService {

    private final EventRepository repository;

    public EventService(EventRepository repository) {
        this.repository = repository;
    }

    public EventDTO update(Long id, EventDTO dto) {
        try {
            var entity = repository.getOne(id);
            entity.setUrl(dto.getUrl());
            entity.setCity(new City(dto.getCityId(), dto.getName()));
            entity.setDate(dto.getDate());
            entity.setName(dto.getName());
            repository.save(entity);
            return new EventDTO(entity);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Id not found: " + id);
        }
    }

}

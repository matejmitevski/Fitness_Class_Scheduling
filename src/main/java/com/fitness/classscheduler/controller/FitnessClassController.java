package com.fitness.classscheduler.controller;

import com.fitness.classscheduler.dto.FitnessClassDto;
import com.fitness.classscheduler.model.FitnessClass;
import com.fitness.classscheduler.model.User;
import com.fitness.classscheduler.service.FitnessClassService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/classes")
public class FitnessClassController {

    @Autowired
    private FitnessClassService service;

    @PostMapping
    public ResponseEntity<FitnessClassDto> create(@Valid @RequestBody FitnessClass fc) {
        FitnessClass created = service.create(fc);
        return ResponseEntity.ok(service.toDto(created));
    }

    @GetMapping
    public List<FitnessClassDto> getAll() {
        return service.getAll().stream()
                .map(service::toDto)
                .toList();
    }

    @GetMapping("/{id}")
    public FitnessClassDto getById(@PathVariable Long id) {
        return service.toDto(service.getById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{classId}/attendees")
    public ResponseEntity<FitnessClassDto> enroll(@PathVariable Long classId, @RequestBody User user) {
        FitnessClass fc = service.enrollUser(classId, user);
        return ResponseEntity.ok(service.toDto(fc));
    }

    @GetMapping("/{classId}/attendees")
    public List<User> getAttendees(@PathVariable Long classId) {
        return service.getAttendees(classId);
    }

    @DeleteMapping("/{classId}/attendees/{userId}")
    public ResponseEntity<?> removeAttendee(@PathVariable Long classId, @PathVariable Long userId) {
        service.removeAttendee(classId, userId);
        return ResponseEntity.noContent().build();
    }
}

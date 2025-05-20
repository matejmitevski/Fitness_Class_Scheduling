package com.fitness.classscheduler.service;

import com.fitness.classscheduler.dto.FitnessClassDto;
import com.fitness.classscheduler.model.FitnessClass;
import com.fitness.classscheduler.model.User;
import com.fitness.classscheduler.repository.FitnessClassRepository;
import com.fitness.classscheduler.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FitnessClassService {

    @Autowired
    private FitnessClassRepository repository;

    @Autowired
    private UserRepository userRepository;

    public FitnessClass create(FitnessClass fitnessClass) {
        return repository.save(fitnessClass);
    }

    public List<FitnessClass> getAll() {
        return repository.findAll();
    }

    public FitnessClass getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Class not found with id: " + id));
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public List<User> getAttendees(Long classId) {
        return getById(classId).getAttendees();
    }

    public void removeAttendee(Long classId, Long userId) {
        FitnessClass fc = getById(classId);
        fc.getAttendees().removeIf(user -> user.getId().equals(userId));
        repository.save(fc);
    }

    public FitnessClass enrollUser(Long classId, User user) {
        FitnessClass fc = getById(classId);
        User savedUser = userRepository.save(user);
        fc.getAttendees().add(savedUser);
        return repository.save(fc);
    }

    public FitnessClassDto toDto(FitnessClass fc) {
        FitnessClassDto dto = new FitnessClassDto();
        dto.setId(fc.getId());
        dto.setTitle(fc.getTitle());
        dto.setStartTime(fc.getStartTime());
        dto.setEndTime(fc.getEndTime());
        dto.setInstructorName(fc.getInstructorName());
        dto.setCapacity(fc.getCapacity());
        dto.setStatus(fc.getStatus());
        return dto;
    }

}

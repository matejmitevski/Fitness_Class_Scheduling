package com.fitness.classscheduler.service;

import com.fitness.classscheduler.dto.FitnessClassDto;
import com.fitness.classscheduler.model.FitnessClass;
import com.fitness.classscheduler.model.Instructor;
import com.fitness.classscheduler.model.User;
import com.fitness.classscheduler.repository.FitnessClassRepository;
import com.fitness.classscheduler.repository.InstructorRepository;
import com.fitness.classscheduler.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FitnessClassService {

    @Autowired
    private FitnessClassRepository repository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private InstructorRepository instructorRepository;

    public FitnessClass createFromDto(@Valid FitnessClassDto dto) {
        Instructor instructor = instructorRepository.findById(dto.getInstructorId())
                .orElseThrow(() -> new RuntimeException("Instructor not found"));

        FitnessClass fc = new FitnessClass();
        fc.setTitle(dto.getTitle());
        fc.setStartTime(dto.getStartTime());
        fc.setEndTime(dto.getEndTime());
        fc.setCapacity(dto.getCapacity());
        fc.setInstructor(instructor);
        return repository.save(fc);
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
        FitnessClass fc = getById(classId);
        return fc.getAttendees(); // Now fc is used properly
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
        dto.setInstructorId(fc.getInstructor().getId()); // Updated
        dto.setCapacity(fc.getCapacity());
        dto.setStatus(fc.getStatus());

        List<Long> attendeeIds = fc.getAttendees().stream()
                .map(User::getId)
                .toList();
        dto.setAttendeeIds(attendeeIds);

        return dto;
    }

}

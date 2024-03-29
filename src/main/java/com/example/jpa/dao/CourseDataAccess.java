package com.example.jpa.dao;

import com.example.jpa.model.Course;
import com.example.jpa.repository.CourseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class CourseDataAccess {
  private final CourseRepository repository;

  public Optional<Course> findById(Long id) {
    return repository.findById(id);
  }

  public Course save(Course entity) {
    return repository.save(entity);
  }

  public Page<Course> findAll(Pageable pageable) {
    return repository.findAll(pageable);
  }

  public void delete(Long id) {
    repository.deleteById(id);
  }
}

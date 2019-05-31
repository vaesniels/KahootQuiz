package be.pxl.springboot.student;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface StudentService {
    Student getById(long Id);

    List<Student> getAll();

    void addOrUpdate(Student student);

    void deleteById(Long id);
}

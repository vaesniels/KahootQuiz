package be.pxl.springboot.teacher;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TeacherService {
    Teacher getById(long Id);

    List<Teacher> getAll();

    void addOrUpdate(Teacher teacher);

    void deleteById(Long id);
}

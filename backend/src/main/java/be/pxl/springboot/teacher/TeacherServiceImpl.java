package be.pxl.springboot.teacher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherServiceImpl implements TeacherService {
    private final TeacherRepository teacherRepository;

    @Autowired
    public TeacherServiceImpl(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }

    @Override
    public Teacher getById(long Id) {
        return teacherRepository.findById(Id).get();
    }

    @Override
    public List<Teacher> getAll() {
        return teacherRepository.findAll();
    }

    @Override
    public void addOrUpdate(Teacher teacher) {
        teacherRepository.save(teacher);
    }

    @Override
    public void deleteById(Long id) {
        teacherRepository.deleteById(id);
    }
}

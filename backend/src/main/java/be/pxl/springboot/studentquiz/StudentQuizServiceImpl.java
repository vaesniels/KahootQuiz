package be.pxl.springboot.studentquiz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentQuizServiceImpl implements StudentQuizService {
    private final StudentQuizRepository studentQuizRepository;

    @Autowired
    public StudentQuizServiceImpl(StudentQuizRepository studentQuizRepository) {
        this.studentQuizRepository = studentQuizRepository;
    }

    @Override
    public StudentQuiz getById(long Id) {
        return studentQuizRepository.findById(Id).get();
    }

    @Override
    public List<StudentQuiz> getAll() {
        return studentQuizRepository.findAll();
    }

    @Override
    public void addOrUpdate(StudentQuiz studentQuiz) {
        studentQuizRepository.save(studentQuiz);
    }

    @Override
    public void deleteById(Long id) {
        studentQuizRepository.deleteById(id);
    }
}

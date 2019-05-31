package be.pxl.springboot.studentquiz;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface StudentQuizService {
    StudentQuiz getById(long Id);

    List<StudentQuiz> getAll();

    void addOrUpdate(StudentQuiz studentQuiz);

    void deleteById(Long id);
}

package be.pxl.springboot.quiz;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface QuizService {
    Quiz getById(Long Id);

    List<Quiz> getAll();

    void addOrUpdate(Quiz quiz);

    void deleteById(Long id);
}

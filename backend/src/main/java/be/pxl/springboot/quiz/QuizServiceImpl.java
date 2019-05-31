package be.pxl.springboot.quiz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuizServiceImpl implements QuizService {
    private final QuizRepository quizRepository;

    @Autowired
    public QuizServiceImpl(QuizRepository quizRepository) {
        this.quizRepository = quizRepository;
    }

    @Override
    public Quiz getById(Long Id) {
        return quizRepository.findById(Id).get();
    }

    @Override
    public List<Quiz> getAll() {
        return quizRepository.findAll();
    }

    @Override
    public void addOrUpdate(Quiz quiz) {
        quizRepository.save(quiz);
    }

    @Override
    public void deleteById(Long id) {
        quizRepository.deleteById(id);
    }
}

package be.pxl.springboot.question;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionServiceImpl implements QuestionService {
    private final QuestionRepository questionRepository;

    @Autowired
    public QuestionServiceImpl(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    @Override
    public Question getById(Long Id) {
        return questionRepository.findById(Id).get();
    }

    @Override
    public List<Question> getAll() {
        return questionRepository.findAll();
    }

    @Override
    public void addOrUpdate(Question question) {
        questionRepository.save(question);
    }

    @Override
    public void deleteById(Long id) {
        questionRepository.deleteById(id);
    }
}

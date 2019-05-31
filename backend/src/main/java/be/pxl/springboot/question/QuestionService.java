package be.pxl.springboot.question;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface QuestionService {
    Question getById(Long id);

    List<Question> getAll();

    void addOrUpdate(Question question);

    void deleteById(Long id);
}
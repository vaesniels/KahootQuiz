package be.pxl.springboot.quiztemplate;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface QuizTemplateService {
    QuizTemplate getById(Long Id);

    List<QuizTemplate> getAll();

    void addOrUpdate(QuizTemplate quizTemplate);

    void deleteById(Long id);
}

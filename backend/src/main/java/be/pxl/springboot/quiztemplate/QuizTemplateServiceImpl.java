package be.pxl.springboot.quiztemplate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuizTemplateServiceImpl implements QuizTemplateService {
    private final QuizTemplateRepository quizTemplateRepository;

    @Autowired
    public QuizTemplateServiceImpl(QuizTemplateRepository quizTemplateRepository) {
        this.quizTemplateRepository = quizTemplateRepository;
    }

    @Override
    public QuizTemplate getById(Long Id) {
        return quizTemplateRepository.findById(Id).get();
    }

    @Override
    public List<QuizTemplate> getAll() {
        return quizTemplateRepository.findAll();
    }

    @Override
    public void addOrUpdate(QuizTemplate quizTemplate) {
        quizTemplateRepository.save(quizTemplate);
    }

    @Override
    public void deleteById(Long id) {
        quizTemplateRepository.deleteById(id);
    }
}
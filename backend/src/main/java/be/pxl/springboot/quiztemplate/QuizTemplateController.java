package be.pxl.springboot.quiztemplate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Secured({"ROLE_TEACHER"})
public class QuizTemplateController {
    private final QuizTemplateService quizTemplateService;

    @Autowired
    public QuizTemplateController(QuizTemplateService quizTemplateService) {
        this.quizTemplateService = quizTemplateService;
    }

    @GetMapping("/quizTemplate")
    public ResponseEntity<List<QuizTemplate>> getQuizTemplates() {
        List<QuizTemplate> quizTemplates = quizTemplateService.getAll();
        if (quizTemplates.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(quizTemplates, HttpStatus.OK);
        }
    }

    @GetMapping("/quizTemplate/{id}")
    public ResponseEntity getQuizTemplateById(@PathVariable(value = "id") Long id) {
        try {
            QuizTemplate quizTemplate = quizTemplateService.getById(id);
            return new ResponseEntity<>(quizTemplate, HttpStatus.OK);
        } catch (QuizTemplateNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }


    @PostMapping("/quizTemplate")
    public ResponseEntity addQuizTemplate(@RequestBody QuizTemplate newQuizTemplate) {
        quizTemplateService.addOrUpdate(newQuizTemplate);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PutMapping("/quizTemplate/{id}")
    public ResponseEntity updateQuizTemplate(@PathVariable(value = "id") Long id,
                                             @RequestBody QuizTemplate updatedQuizTemplate) {
        if (id == updatedQuizTemplate.getId()) {
            quizTemplateService.addOrUpdate(updatedQuizTemplate);
            return new ResponseEntity(HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/quizTemplate/{id}")
    public ResponseEntity deleteQuizTemplate(@PathVariable(value = "id") Long id) {
        quizTemplateService.deleteById(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}

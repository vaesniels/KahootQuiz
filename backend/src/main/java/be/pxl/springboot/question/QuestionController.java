package be.pxl.springboot.question;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class QuestionController {
    private final QuestionService questionService;

    @Autowired
    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @GetMapping("/question")
    @Secured({"ROLE_STUDENT", "ROLE_TEACHER"})
    public ResponseEntity<List<Question>> getQuestions() {
        List<Question> questions = questionService.getAll();
        if (questions.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(questions, HttpStatus.OK);
        }
    }

    @GetMapping("/question/{id}")
    @Secured({"ROLE_STUDENT", "ROLE_TEACHER"})
    public ResponseEntity getQuestionById(@PathVariable(value = "id") Long id) {
        try {
            Question question = questionService.getById(id);
            return new ResponseEntity<>(question, HttpStatus.OK);
        } catch (QuestionNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }


    @PostMapping("/question")
    @Secured({"ROLE_TEACHER"})
    public ResponseEntity addQuestion(@RequestBody Question newQuestion) {
        questionService.addOrUpdate(newQuestion);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PutMapping("/question/{id}")
    @Secured({"ROLE_TEACHER"})
    public ResponseEntity updateQuestion(@PathVariable(value = "id") Long id,
                                         @RequestBody Question updatedQuestion) {
        if (id == updatedQuestion.getId()) {
            questionService.addOrUpdate(updatedQuestion);
            return new ResponseEntity(HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/question/{id}")
    @Secured({"ROLE_TEACHER"})
    public ResponseEntity deleteQuestion(@PathVariable(value = "id") Long id) {
        questionService.deleteById(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}

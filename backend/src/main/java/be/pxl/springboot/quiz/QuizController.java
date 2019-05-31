package be.pxl.springboot.quiz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Secured({"ROLE_TEACHER"})
public class QuizController {
    private final QuizService quizService;

    @Autowired
    public QuizController(QuizService quizService) {
        this.quizService = quizService;
    }


    @GetMapping("/quiz")
    @Secured({"ROLE_STUDENT", "ROLE_TEACHER"})
    public ResponseEntity<List<Quiz>> getQuizzes() {
        List<Quiz> quizzes = quizService.getAll();
        if (quizzes.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(quizzes, HttpStatus.OK);
        }
    }

    @GetMapping("/quiz/{id}")
    @Secured({"ROLE_STUDENT", "ROLE_TEACHER"})
    public ResponseEntity getQuizById(@PathVariable(value = "id") Long id) {
        try {
            Quiz quiz = quizService.getById(id);
            return new ResponseEntity<>(quiz, HttpStatus.OK);
        } catch (QuizNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }


    @PostMapping("/quiz")
    public ResponseEntity addQuiz(@RequestBody Quiz newQuiz) {
        quizService.addOrUpdate(newQuiz);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PutMapping("/quiz/{id}")
    public ResponseEntity updateQuiz(@PathVariable(value = "id") Long id,
                                     @RequestBody Quiz updatedQuiz) {
        if (id == updatedQuiz.getId()) {
            quizService.addOrUpdate(updatedQuiz);
            return new ResponseEntity(HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/quiz/{id}")
    public ResponseEntity deleteQuiz(@PathVariable(value = "id") Long id) {
        quizService.deleteById(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}
package be.pxl.springboot.studentquiz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Secured({"ROLE_STUDENT", "ROLE_TEACHER"})
public class StudentQuizController {
    private final StudentQuizService studentQuizService;

    @Autowired
    public StudentQuizController(StudentQuizService studentQuizService) {
        this.studentQuizService = studentQuizService;
    }

    @GetMapping("/studentQuiz")
    public ResponseEntity<List<StudentQuiz>> getStudentQuizzes() {
        List<StudentQuiz> quizTemplates = studentQuizService.getAll();
        if (quizTemplates.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(quizTemplates, HttpStatus.OK);
        }
    }

    @GetMapping("/studentQuiz/{id}")
    public ResponseEntity getStudentQuizById(@PathVariable(value = "id") Long id) {
        try {
            StudentQuiz studentQuiz = studentQuizService.getById(id);
            return new ResponseEntity<>(studentQuiz, HttpStatus.OK);
        } catch (StudentQuizNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }


    @PostMapping("/studentQuiz")
    public ResponseEntity addStudentQuiz(@RequestBody StudentQuiz newStudentQuiz) {
        studentQuizService.addOrUpdate(newStudentQuiz);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PutMapping("/studentQuiz/{id}")
    public ResponseEntity updateStudentQuiz(@PathVariable(value = "id") Long id,
                                            @RequestBody StudentQuiz updatedStudentQuiz) {
        if (id == updatedStudentQuiz.getId()) {
            studentQuizService.addOrUpdate(updatedStudentQuiz);
            return new ResponseEntity(HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/studentQuiz/{id}")
    public ResponseEntity deleteStudentQuiz(@PathVariable(value = "id") Long id) {
        studentQuizService.deleteById(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}

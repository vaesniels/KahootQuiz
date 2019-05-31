package be.pxl.springboot.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//TODO: name methods properly
@RestController
public class StudentController {
    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/student")
    @Secured({"ROLE_STUDENT", "ROLE_TEACHER"})
    public ResponseEntity<List<Student>> getStudents() {
        List<Student> questions = studentService.getAll();
        if (questions.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(questions, HttpStatus.OK);
        }
    }

    @GetMapping("/student/{id}")
    @Secured({"ROLE_STUDENT", "ROLE_TEACHER"})
    public ResponseEntity getStudentById(@PathVariable(value = "id") Long id) {
        try {
            Student student = studentService.getById(id);
            return new ResponseEntity<>(student, HttpStatus.OK);
        } catch (StudentNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/student")
    public ResponseEntity addStudent(@RequestBody Student newStudent) {
        studentService.addOrUpdate(newStudent);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PutMapping("/student/{id}")
    @Secured({"ROLE_STUDENT", "ROLE_TEACHER"})
    public ResponseEntity updateStudent(@PathVariable(value = "id") Long id,
                                        @RequestBody Student updatedStudent) {
        if (id == updatedStudent.getId()) {
            studentService.addOrUpdate(updatedStudent);
            return new ResponseEntity(HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/student/{id}")
    @Secured({"ROLE_STUDENT", "ROLE_TEACHER"})
    public ResponseEntity deleteStudent(@PathVariable(value = "id") Long id) {
        studentService.deleteById(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}

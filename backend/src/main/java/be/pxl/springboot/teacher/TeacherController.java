package be.pxl.springboot.teacher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TeacherController {
    private final TeacherService teacherService;

    @Autowired
    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @GetMapping("/teacher")
    @Secured({"ROLE_TEACHER"})
    public ResponseEntity<List<Teacher>> getTeachers() {
        List<Teacher> teachers = teacherService.getAll();
        if (teachers.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(teachers, HttpStatus.OK);
        }
    }

    @GetMapping("/teacher/{id}")
    @Secured({"ROLE_TEACHER"})
    public ResponseEntity getTeacherById(@PathVariable(value = "id") Long id) {
        try {
            Teacher teacher = teacherService.getById(id);
            return new ResponseEntity<>(teacher, HttpStatus.OK);
        } catch (TeacherNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }


    @PostMapping("/teacher")
    public ResponseEntity addTeacher(@RequestBody Teacher newTeacher) {
        teacherService.addOrUpdate(newTeacher);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PutMapping("/teacher/{id}")
    @Secured({"ROLE_TEACHER"})
    public ResponseEntity updateTeacher(@PathVariable(value = "id") Long id,
                                        @RequestBody Teacher updatedTeacher) {
        if (id == updatedTeacher.getId()) {
            teacherService.addOrUpdate(updatedTeacher);
            return new ResponseEntity(HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/teacher/{id}")
    @Secured({"ROLE_TEACHER"})
    public ResponseEntity deleteTeacher(@PathVariable(value = "id") Long id) {
        teacherService.deleteById(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}
package be.pxl.springboot.quiz;

import be.pxl.springboot.studentquiz.StudentQuiz;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "quiz")
public class Quiz implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;

    @Column(name = "quiztemplateid")
    private long quizTemplateId;

    @Column(name = "name")
    private String name;

    @OneToMany
    @JoinColumn(name = "quizid")
    private List<StudentQuiz> studentQuizzes;

    @Column(name = "teacherid")
    private long teacherId;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getQuizTemplateId() {
        return quizTemplateId;
    }

    public void setQuizTemplateId(long quizTemplateId) {
        this.quizTemplateId = quizTemplateId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<StudentQuiz> getStudentQuizzes() {
        return studentQuizzes;
    }

    public void setStudentQuizzes(List<StudentQuiz> studentQuizzes) {
        this.studentQuizzes = studentQuizzes;
    }

    public long getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(long teacherId) {
        this.teacherId = teacherId;
    }
}

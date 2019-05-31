package be.pxl.springboot.quiztemplate;

import be.pxl.springboot.question.Question;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "quiztemplate")
public class QuizTemplate implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;

    @OneToMany(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "quiztemplateid")
    private List<Question> questions;

    @Column(name = "teacherid")
    private long teacherId;

    @Column(name = "name")
    private String name;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public long getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(long teacherId) {
        this.teacherId = teacherId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

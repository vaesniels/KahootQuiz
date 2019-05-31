package be.pxl.springboot.studentquiz;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "studentquiz")
public class StudentQuiz implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;

    @Column(name = "studentid")
    private long studentId;

    @Column(name = "quizid")
    private long quizId;

    @Column(name = "score")
    private int score;

    @Column(name = "rank")
    private int rank;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getStudentId() {
        return studentId;
    }

    public void setStudentId(long studentId) {
        this.studentId = studentId;
    }

    public long getQuizId() {
        return quizId;
    }

    public void setQuizId(long quizId) {
        this.quizId = quizId;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}

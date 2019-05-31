package be.pxl.springboot.studentquiz;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentQuizRepository extends JpaRepository<StudentQuiz, Long> {
}

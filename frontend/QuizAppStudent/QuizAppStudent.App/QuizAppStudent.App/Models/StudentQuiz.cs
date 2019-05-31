using System;
using System.Collections.Generic;
using System.Text;

namespace QuizAppStudent.App.Models
{
    public class StudentQuiz
    {

        public long Id { get; set; }
        public long StudentId { get; set; }
        public long QuizId { get; set; }
        public int Score { get; set; }
        public int Rank { get; set; }
        public int rank { get; internal set; }
    }
}

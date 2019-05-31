namespace QuizAppStudent.App.Models
{
    public class Student
    {
        public int Id { get; set; }
        public string Name { get; set; }
        public int Score { get; set; }
        public int Rank { get; set; }

        public string RankAndName => $"{Rank}. {Name}";
    }
}
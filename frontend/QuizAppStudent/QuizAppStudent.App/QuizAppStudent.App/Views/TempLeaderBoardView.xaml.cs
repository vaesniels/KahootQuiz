using System.Collections.ObjectModel;
using System.Linq;
using QuizAppStudent.App.Models;
using Xamarin.Forms;
using Xamarin.Forms.Xaml;

namespace QuizAppStudent.App
{
    [XamlCompilation(XamlCompilationOptions.Compile)]
    public partial class TempLeaderBoardPage : ContentPage
    {
        public TempLeaderBoardPage()
        {
            InitializeComponent();

            // Dummy data testing on TempLeaderBoardListView
            var studentDummyData = new ObservableCollection<Student>
            {
                new Student {Name = "Cedric", Score = 5478},
                new Student {Name = "Chantal", Score = 5555},
                new Student {Name = "Axel", Score = 1278},
                new Student {Name = "Robin", Score = 7896},
                new Student {Name = "Niels", Score = 4789},
                // Lowest Score and over 6 students in the leader board only 5 should be shown
                new Student {Name = "TestIfHeShowsUpInTempLeaderBoard", Score = 5}
            };
            var orderedStudentDummyData = studentDummyData.OrderByDescending(s => s.Score).Take(5).ToList();
            for (var i = 0; i < 5; i++) orderedStudentDummyData.ElementAt(i).Rank = i + 1;
            TempLeaderBoardListView.ItemsSource = orderedStudentDummyData;
        }
    }
}
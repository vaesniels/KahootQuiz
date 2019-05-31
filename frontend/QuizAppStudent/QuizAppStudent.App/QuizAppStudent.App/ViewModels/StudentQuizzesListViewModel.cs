using System;
using System.Collections.Generic;
using System.Net.Http;
using System.Text;
using QuizAppStudent.App.Models;
using QuizAppStudent.App.Services;
using QuizAppStudent.App.Views;
using Xamarin.Forms;

namespace QuizAppStudent.App.ViewModels
{
    public class StudentQuizzesListViewModel
    {
        public StudentQuiz CurrentStudentQuiz { get; set; }
        public IList<StudentQuiz> StudentQuiz { get; set; }
        public INavigationService _navigationService;

        public Command StudentQuizSelectedcommand => new Command<StudentQuiz>(BarCodeScanner);

        public StudentQuizzesListViewModel(INavigationService NavService)
        {
            _navigationService = NavService;
            List<StudentQuiz> studentQuizzes = new List<StudentQuiz>();
            studentQuizzes.Add(new StudentQuiz { Id = 1 , QuizId = 1 , Rank = 10, Score = 10 , StudentId = 1});
            studentQuizzes.Add(new StudentQuiz { Id = 2, QuizId = 2, Rank = 10, Score = 10, StudentId = 2 });
            studentQuizzes.Add(new StudentQuiz { Id = 3, QuizId = 3, Rank = 10, Score = 10, StudentId = 3 });
            studentQuizzes.Add(new StudentQuiz { Id = 2, QuizId = 2, Rank = 10, Score = 10, StudentId = 2 });
            studentQuizzes.Add(new StudentQuiz { Id = 3, QuizId = 3, Rank = 10, Score = 10, StudentId = 3 });

            /* var httpclient = new HttpClient();
             string jsonResult = string.Empty;

             var responseMessage = httpclient.GetAsync("localhost:8080/studentquiz");
            */
            StudentQuiz = studentQuizzes;
           
        }
       

        private void BarCodeScanner(StudentQuiz SQ)
        {
            System.Diagnostics.Debug.WriteLine(SQ.Id);
           
       /*     var mainView = (MainView)Application.Current.MainPage;
            var loginPage = (NavigationPage)mainView.Detail;

            var LoginView = loginPage.CurrentPage as
           LoginView;
            if (LoginView == null)
            {
                LoginView = new LoginView();

                await loginPage.PushAsync(LoginView);
            }*/
        }
    }
    
}

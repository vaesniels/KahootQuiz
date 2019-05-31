using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using GalaSoft.MvvmLight;
using GalaSoft.MvvmLight.Command;
using GalaSoft.MvvmLight.Views;
using QuizAppLector.Models;

namespace QuizAppLector.ViewModels
{
    public class QuizOverviewPageViewModel : ViewModelBase
    {
        private readonly INavigationService _navigationService;
        public RelayCommand NavigateCommand { get; private set; }
        public List<QuizTemplate> Quizzes { get; private set; }

        public QuizOverviewPageViewModel(INavigationService navigationService)
        {
            _navigationService = navigationService;
            Quizzes = new List<QuizTemplate>()
            {
                new QuizTemplate("Quiz 1: Variables"),
                new QuizTemplate("Quiz 2: Methods"),
                new QuizTemplate("Quiz 3: Classes"),
                new QuizTemplate("Quiz 4: Debugging"),
                new QuizTemplate("Quiz 5: Exceptions"),
                new QuizTemplate("Quiz 6: Abstract Classes")
            };

            NavigateCommand = new RelayCommand(NavigateCommandAction);
        }

        private void NavigateCommandAction()
        {
            _navigationService.NavigateTo("StartPage");
        }
    }
}

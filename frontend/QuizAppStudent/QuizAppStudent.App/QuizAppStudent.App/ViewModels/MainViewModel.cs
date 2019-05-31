using System;
using System.Collections.Generic;
using System.Text;
using Autofac;
using QuizAppStudent.App.Models;
using QuizAppStudent.App.Util;
using Xamarin.Forms;

namespace QuizAppStudent.App.ViewModels
{
    public class MainViewModel : ViewModelBase
    {
        public StudentQuizzesListViewModel StudentQuizzesListViewModel { get; set; }
        public NavigationListViewModel NavigationListViewModel { get; set; }

        public MainViewModel()
        {
            StudentQuizzesListViewModel = AppContainer.Resolve<StudentQuizzesListViewModel>();
            NavigationListViewModel = AppContainer.Resolve<NavigationListViewModel>();
        }
    }

}

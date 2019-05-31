using System;
using System.Collections.Generic;
using System.Text;
using QuizAppStudent.App.Models;
using QuizAppStudent.App.Services;
using QuizAppStudent.App.Util;
using QuizAppStudent.App.Views;
using Xamarin.Forms;

namespace QuizAppStudent.App.ViewModels
{
    public class LoginViewModel : ViewModelBase
    {
        public INavigationService _navigationService;
        public Command LoginCommand => new Command(Login);
        public String Password { get; set; }
        public String Username { get; set; }
        public String ErrorMessage { get; set; }

        public LoginViewModel(INavigationService NavService)
        {
            _navigationService = NavService; 
        }

        private void Login()
        {
            System.Diagnostics.Debug.WriteLine(Username,Password);
            //API call check Check login credentials.

            //_navigationService.NavigateToNewView<MainViewModel>();
        }

        
    }
}

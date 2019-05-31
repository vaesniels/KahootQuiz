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
    public class NavigationListViewModel
    {
        public INavigationService _navigationService;
        public Command LogoutCommand => new Command(Logout);
        public Command NavigateToBarcodeScannerCommand => new Command(NavigateToBarCodeScanner);
       
   
        public NavigationListViewModel(INavigationService NavService)
        {
            _navigationService = NavService;
        }

        private void NavigateToBarCodeScanner()
        {
            MessagingCenter.Instance.Send(this, "StartQRScanner");
        }

        private void Logout()
        {
            //App.Current.MainPage = new LoginView{BindingContext = new LoginViewModel()};
            _navigationService.NavigateToNewView<LoginViewModel>();
        }

    }
}

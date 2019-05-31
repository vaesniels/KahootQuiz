using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using GalaSoft.MvvmLight;
using GalaSoft.MvvmLight.Command;
using GalaSoft.MvvmLight.Views;

namespace QuizAppLector.ViewModels
{
    public class RegisterPageViewModel : ViewModelBase
    {
        private readonly INavigationService _navigationService;
        public RelayCommand NavigateToLoginPage { get; private set; }
       
        public RegisterPageViewModel(INavigationService navigationService)
        {
            _navigationService = navigationService;
            NavigateToLoginPage = new RelayCommand(NavigateToLoginAction);
        }

        private void NavigateToLoginAction()
        {
            _navigationService.NavigateTo("StartPage");
        }
    }
}

using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using GalaSoft.MvvmLight;
using GalaSoft.MvvmLight.Command;
using GalaSoft.MvvmLight.Views;
using QuizAppLector.Views;

namespace QuizAppLector.ViewModels
{
    public class StartPageViewModel : ViewModelBase
    {
        private readonly INavigationService _navigationService;
        public RelayCommand NavigateToRegisterPage { get; private set; }
        public RelayCommand NavigateToOverviewPage { get; private set; }
        
        public StartPageViewModel(INavigationService navigationService)
        {
            _navigationService = navigationService;
            NavigateToRegisterPage = new RelayCommand(NavigateToRegisterAction);
            NavigateToOverviewPage = new RelayCommand(NavigateToQuizOverviewAction);
        }

        private void NavigateToRegisterAction()
        {
            _navigationService.NavigateTo("RegisterPage");
        }

        private void NavigateToQuizOverviewAction()
        {
            _navigationService.NavigateTo("QuizOverviewPage");
        }
    }
}

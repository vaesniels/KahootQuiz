using GalaSoft.MvvmLight;
using GalaSoft.MvvmLight.Views;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Input;

namespace QuizAppLector.Commands
{
    class NavigationCommand : ICommand
    {
        public NavigationService NavigationService;
        public event EventHandler CanExecuteChanged;

        public NavigationCommand(NavigationService navigationService)
        {
            NavigationService = navigationService ;
        }

        public bool CanExecute(object parameter)
        {
            return true;
        }

        public void Execute(object parameter)
        {
            NavigationService.NavigateTo(parameter.GetType.ToString);
        }
    }
}

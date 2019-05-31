using System.Threading.Tasks;
using QuizAppStudent.App.ViewModels;

namespace QuizAppStudent.App.Services
{
    public interface INavigationService
    {
        Task NavigateToAsync<TViewModel>() where TViewModel : ViewModelBase;
        void NavigateToNewView<TViewModel>() where TViewModel : ViewModelBase;

    }
}
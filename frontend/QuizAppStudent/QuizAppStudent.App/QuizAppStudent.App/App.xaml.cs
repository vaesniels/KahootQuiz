using QuizAppStudent.App.Util;
using QuizAppStudent.App.ViewModels;
using QuizAppStudent.App.Views;
using Xamarin.Forms;
using Xamarin.Forms.Xaml;

[assembly: XamlCompilation(XamlCompilationOptions.Compile)]

namespace QuizAppStudent.App
{
    public partial class App : Application
    {
        public App()
        {
           
            InitializeComponent();
            AppContainer.RegisterDependencies();
            var main = new LoginView
            {
                BindingContext = AppContainer.Resolve<LoginViewModel>()
            };
            MainPage =  main;

            //MainPage = new NavigationPage(new LoginPage {Title = "Quiz Time"});
        }

        protected override void OnStart()
        {
            // Handle when your app starts
        }

        protected override void OnSleep()
        {
            // Handle when your app sleeps
        }

        protected override void OnResume()
        {
            // Handle when your app resumes
        }
    }
}
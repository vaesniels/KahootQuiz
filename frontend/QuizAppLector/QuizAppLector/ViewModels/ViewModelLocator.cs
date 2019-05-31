using CommonServiceLocator;
using GalaSoft.MvvmLight;
using GalaSoft.MvvmLight.Ioc;
using GalaSoft.MvvmLight.Views;
using QuizAppLector.Views;

namespace QuizAppLector.ViewModels
{
    public class ViewModelLocator
    {
        public const string StartPageKey = "StartPage";
        public const string RegisterPageKey = "RegisterPage";
        public const string QuizOverviewPageKey = "QuizOverviewPage";

        /// <summary>
        ///     Initializes a new instance of the ViewModelLocator class.
        /// </summary>
        public ViewModelLocator()
        {
            ServiceLocator.SetLocatorProvider(() => SimpleIoc.Default);
            var nav = new NavigationService();
            nav.Configure(StartPageKey, typeof(StartPage));
            nav.Configure(RegisterPageKey, typeof(RegisterPage));
            nav.Configure(QuizOverviewPageKey, typeof(QuizOverviewPage));
            if (ViewModelBase.IsInDesignModeStatic)
            {
                // Create design time view services and models
            }

            //Register your services used here
            SimpleIoc.Default.Register<INavigationService>(() => nav) ;
            SimpleIoc.Default.Register<StartPageViewModel>();
            SimpleIoc.Default.Register<RegisterPageViewModel>();
            SimpleIoc.Default.Register<QuizOverviewPageViewModel>();
        }


        // <summary>
        // Gets the StartPage view model.
        // </summary>
        // <value>
        // The StartPage view model.
        // </value>
        public StartPageViewModel StartPageInstance => ServiceLocator.Current.GetInstance<StartPageViewModel>();

        public RegisterPageViewModel RegisterPageInstance => ServiceLocator.Current.GetInstance<RegisterPageViewModel>();

        public QuizOverviewPageViewModel QuizOverviewPageInstance => ServiceLocator.Current.GetInstance<QuizOverviewPageViewModel>();

        // <summary>
        // The cleanup.
        // </summary>
        public static void Cleanup()
        {
            // TODO Clear the ViewModels
        }
    }
}
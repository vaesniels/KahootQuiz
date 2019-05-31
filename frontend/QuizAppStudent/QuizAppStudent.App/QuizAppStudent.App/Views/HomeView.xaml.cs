using System;
using System.Collections.ObjectModel;
using Xamarin.Forms;
using Xamarin.Forms.Xaml;

namespace QuizAppStudent.App
{
    [XamlCompilation(XamlCompilationOptions.Compile)]
    public partial class HomeView : ContentPage
    {
        public HomeView()
        {
            InitializeComponent();

            // TODO HomePage API call to retrieve quizzes from the database
            // Dummy data testing on CreatedQuizzesListView
        }

       
    }
}
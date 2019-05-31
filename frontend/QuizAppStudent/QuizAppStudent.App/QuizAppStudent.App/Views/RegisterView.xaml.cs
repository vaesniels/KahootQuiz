using System;
using Xamarin.Forms;
using Xamarin.Forms.Xaml;

namespace QuizAppStudent.App
{
    [XamlCompilation(XamlCompilationOptions.Compile)]
    public partial class RegisterPage : ContentPage
    {
        public RegisterPage()
        {
            InitializeComponent();

            // Add event handler to Label -> Navigate to LoginPage
            HaveAccountLabel.GestureRecognizers.Add(new TapGestureRecognizer
            {
                Command = new Command(() => { Navigation.PushAsync(new LoginView()); })
            });
        }

        private void RegisterButton_OnClicked(object sender, EventArgs e)
        {
            if (UsernameEntry.Text != "" && PasswordEntry.Text != "")
                // TODO RegisterPage check if username doesn't already exist
                Navigation.PushAsync(new HomeView());

            // TODO RegisterPage add warning to tell the user the username has already been taken.
        }
    }
}
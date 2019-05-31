using System;
using Xamarin.Forms;

namespace QuizAppStudent.App
{
    public partial class LoginView : ContentPage
    {
        public LoginView()
        {
            InitializeComponent();

            // Add event handler to Label -> Navigate to RegisterPage
            NoAccountLabel.GestureRecognizers.Add(new TapGestureRecognizer
            {
                Command = new Command(() => { Navigation.PushAsync(new RegisterPage()); })
            });
        }

        private void LoginButton_OnClicked(object sender, EventArgs e)
        {
            if (UsernameEntry.Text != "" && PasswordEntry.Text != "")
                // TODO LoginPage check if username exists already exists and if password for that user is correct.
                Navigation.PushAsync(new HomeView());

            // TODO LoginPage add validation warning if user doesn't exist or if password is incorrect.
        }
    }
}
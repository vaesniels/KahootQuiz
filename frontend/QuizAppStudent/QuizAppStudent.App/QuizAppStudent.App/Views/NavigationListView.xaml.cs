using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using QuizAppStudent.App.Models;
using QuizAppStudent.App.ViewModels;
using Xamarin.Forms;
using Xamarin.Forms.Xaml;
using ZXing.Net.Mobile.Forms;

namespace QuizAppStudent.App
{
	[XamlCompilation(XamlCompilationOptions.Compile)]
	public partial class NavigationListView : ContentPage
	{
        private ZXingScannerPage scanPage;

        public NavigationListView ()
		{
			InitializeComponent ();
            MessagingCenter.Instance.Subscribe<NavigationListViewModel>(this, "StartQRScanner", (sender) => { ScanQR(); });
        }
        private void ScanQR()
        {
            scanPage = new ZXingScannerPage();
            //scanPage.Options.PossibleFormats.Add(BarcodeFormat.QR_CODE);
            scanPage.OnScanResult += (result) =>
            {
                if (scanPage.IsScanning)
                {
                    scanPage.AutoFocus();

                }

                Device.BeginInvokeOnMainThread(async () =>
                {
                    await Navigation.PopModalAsync();
                    if (result != null)
                    {
                        await DisplayAlert("Scanned QR code", result.Text, "OK");
                        MessagingCenter.Instance.Send(this, "MessageModel", new MessageModel { Data = result.Text });
                    }
                });
            };
            Navigation.PushModalAsync(scanPage);
        }
    }
}
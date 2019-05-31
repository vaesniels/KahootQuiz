using System.Net.Http;
using QuizAppStudent.App.Models;
using Newtonsoft.Json;
using System.Text;
using System;

namespace QuizAppStudent.App.Services
{
    public class ApiService
    {
        public static async System.Threading.Tasks.Task<Object> GetAsync(string url)
        {
            using (var client = new HttpClient())
            {
                HttpResponseMessage response = await client.GetAsync(url);
                if (response.IsSuccessStatusCode)
                {
                    var GetResult = await response.Content.ReadAsStringAsync();
                    Object obj = JsonConvert.DeserializeObject(GetResult);
                    return null;
                }
                return null;
            }
        }
        public static async System.Threading.Tasks.Task<Boolean> DeleteAsync(string url)
        {
            using (var client = new HttpClient())
            {
                HttpResponseMessage response = await client.DeleteAsync(url);
                if (response.IsSuccessStatusCode)
                {
                   return true;
                }
                return false;
            }
        }
        public static async System.Threading.Tasks.Task<Boolean> PostAsync(string url, Object o)
        {
            using (var client = new HttpClient())
            {
                var Content = new StringContent(JsonConvert.SerializeObject(o), Encoding.UTF8, "application/json");
                var Response = await client.PostAsync(url, Content);
                var ResponseString = await Response.Content.ReadAsStringAsync();
                if (Response.IsSuccessStatusCode)
                {
                    return true;
                }
                return false;
            }
        }
        public static async System.Threading.Tasks.Task<Boolean> PutAsync(string url, Object o)
        {
            using (var client = new HttpClient())
            {
                var Content = new StringContent(JsonConvert.SerializeObject(o), Encoding.UTF8, "application/json");
                var Response = await client.PutAsync(url,Content);
                var ResponseString = await Response.Content.ReadAsStringAsync();
                if (Response.IsSuccessStatusCode)
                {
                    return true;
                }
                return false;
            }
        }
    }
}

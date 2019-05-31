using System;
using System.Collections.Generic;
using System.Text;
using Autofac;
using QuizAppStudent.App.Services;
using QuizAppStudent.App.ViewModels;

namespace QuizAppStudent.App.Util
{
    public static class AppContainer
    {
        public static IContainer _container;
        public static void RegisterDependencies()
        {
            var builder = new ContainerBuilder();
            //ViewModels
            /*
            builder.RegisterType<BarCodeScannerViewModel>().SingleInstance();
            builder.RegisterType<LoginViewModel>().SingleInstance();
            builder.RegisterType<NavigationListViewModel>().SingleInstance();
            builder.RegisterType<StudentQuizzesListViewModel>().SingleInstance();
            builder.RegisterType<MainViewModel>().SingleInstance();
            */
            //Services
            builder.RegisterType<NavigationService>().As<INavigationService>();
            builder.RegisterType<NavigationListViewModel>().As<NavigationListViewModel>();
            builder.RegisterType<LoginViewModel>().As<LoginViewModel>();
            builder.RegisterType<StudentQuizzesListViewModel>().As<StudentQuizzesListViewModel>();
            builder.RegisterType<MainViewModel>().As<MainViewModel>();
            //General

            _container = builder.Build();
        }
        public static object Resolve(Type typeName)
        {
            return _container.Resolve(typeName);
        }
        public static T Resolve<T>()
        {
            return _container.Resolve<T>();
        }
    }

}

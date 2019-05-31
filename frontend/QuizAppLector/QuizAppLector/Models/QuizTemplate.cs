using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace QuizAppLector.Models
{
    public class QuizTemplate
    {
        public long Id { get; set; }
        public string Name { get; set; }

        public QuizTemplate(String name)
        {
            this.Name = name;
        }
    }
}

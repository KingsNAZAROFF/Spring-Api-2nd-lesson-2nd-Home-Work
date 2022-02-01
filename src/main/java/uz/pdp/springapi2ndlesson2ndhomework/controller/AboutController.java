package uz.pdp.springapi2ndlesson2ndhomework.controller;

import org.springframework.web.bind.annotation.*;

@RestController
public class AboutController {

   @RequestMapping(value = "/about",method = RequestMethod.GET)
    public String about(){
        return "About CodingBat\n" +
                "CodingBat is a free site of live coding problems to build coding skill in Java and Python (example problem). CodingBat is a project by Nick Parlante, " +
                "a computer science lecturer at Stanford.\n" +
                "Going through many practice problem is a great way to solidify your understanding of how the code should work. " +
                "CodingBat problems work great as homework, or for self-study practice, or in a lab, or as live lecture examples. " +
                "The CodingBat problems are designed to have low overhead: short problem statements (like an exam), nothing to install, " +
                "immediate feedback in the browser, and there's lots of them to build up those skills. The idea for CodingBat came from my experience teaching CS at " +
                "Stanford combined with seeing how student's used unit-tests in more advanced courses, and crystalized when I saw an Owen " +
                "Astrachan demo of a unit-testing thing he uses with his Duke students.";
    }

    @RequestMapping(value = "/help",method = RequestMethod.GET)
    public  String help(){
       return "CodingBat is a free site of live Java and Python coding problems to build coding skill. " +
               "Each problem has a problem description and a table showing some sample output for that problem. " +
               "Type your Java code into the large text area and click the \"Go\" button to save your code, compile and run. " +
               "Each time you click Go, the results are shown in the right side of the page. On each problem page, " +
               "the \"prev\" and \"next\" links lead through the sequence of problems in that section. The \"chance\" " +
               "link goes to a random unfinished problem in the section.\n" +
               "The problems in each section get harder towards the end of the section. " +
               "Some of the problems (marked with an \"H\") have hint text and/or the solution code available. " +
               "In particular, all of the problems in the \"Warmup\" sections have solution code available, so that's an easy way to get started.";
    }

}

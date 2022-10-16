package com.example.mvcdemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AppController {
    @Autowired
    private SessionData sessionData;

/*
    @GetMapping("/greet")
    public ModelAndView welcome(){
        String msg="hello everyone";
        ModelAndView mv=new ModelAndView("welcomepage","msg",msg);
        return mv;
    }
    */

    @GetMapping("/greet")
    public String welcome(Model model) {
        String msg = "hello everyone";
        model.addAttribute("msg", msg);
        return "welcomepage";
    }

    @GetMapping("/increment")
    public String incrementCounter(Model model) {
        int counter = sessionData.getCounter();
        counter++;
        sessionData.setCounter(counter);
        model.addAttribute("counter", counter);
        return "counter";
    }


}

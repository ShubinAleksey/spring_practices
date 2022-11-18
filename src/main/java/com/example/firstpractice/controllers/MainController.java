package com.example.firstpractice.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {
    @GetMapping("/")
    public String index() {
        return "CalculatorPace";
    }

    @GetMapping("/calculator")
    public String calculatorGet(@RequestParam(name = "firstnumber", required = false, defaultValue = "0") double firstnumber,
                             @RequestParam(name = "secondnumber", required = false, defaultValue = "0") double secondnumber,
                             @RequestParam(name = "operation", required = false) String operation, Model model) {
        double result = switch (operation) {
            case "+" -> firstnumber + secondnumber;
            case "-" -> firstnumber - secondnumber;
            case "*" -> firstnumber * secondnumber;
            case "/" -> firstnumber / secondnumber;
            default -> 0;
        };
        model.addAttribute("temp",result);
        return "CalculatorPace";
    }
    @PostMapping("/calculator")
    public String calculatorPost(@RequestParam(name = "firstnumber") double firstnumber,
                             @RequestParam(name = "secondnumber") double secondnumber,
                             @RequestParam(name = "operation") String operation, Model model) {
        double result = switch (operation) {
            case "+" -> firstnumber + secondnumber;
            case "-" -> firstnumber - secondnumber;
            case "*" -> firstnumber * secondnumber;
            case "/" -> firstnumber / secondnumber;
            default -> 0;
        };
        model.addAttribute("temp",result);
        return "CalculatorPace";
    }
}

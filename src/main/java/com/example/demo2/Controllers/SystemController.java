package com.example.demo2.Controllers;

import com.example.demo2.Models.System;
import com.example.demo2.Repository.SystemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/system")
public class SystemController {
    @Autowired
    SystemRepository systemRepository;

    @GetMapping("/")
    public String index(Model model) {
        Iterable<System> systemIterable = systemRepository.findAll();
        model.addAttribute("system_list",systemIterable);
        return "/system/system";
    }

    @GetMapping("/add")
    public String AddView() {
        return "system/system-add";
    }

    @PostMapping("/add")
    public String AddSystem(@RequestParam(name = "name") String name,
                          @RequestParam(name = "system_mass") Long system_mass,
                          @RequestParam(name = "total_stars") int total_stars,
                          @RequestParam(name = "total_known_planets") int total_known_planets,
                          @RequestParam(name = "total_moons") int total_moons) {
        System new_system = new System(name, system_mass, total_stars, total_known_planets, total_moons);
        systemRepository.save(new_system);
        return "redirect:/system/";
    }
}

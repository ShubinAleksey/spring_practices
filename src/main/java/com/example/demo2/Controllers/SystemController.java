package com.example.demo2.Controllers;

import com.example.demo2.Models.Galaxy;
import com.example.demo2.Models.Star;
import com.example.demo2.Models.System;
import com.example.demo2.Repository.GalaxyRepository;
import com.example.demo2.Repository.SystemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/system")
public class SystemController {
    @Autowired
    SystemRepository systemRepository;

    @Autowired
    GalaxyRepository galaxyRepository;

    @GetMapping("/")
    public String index(Model model) {
        Iterable<System> systemIterable = systemRepository.findAll();
        model.addAttribute("system_list",systemIterable);
        return "/system/system";
    }

    @GetMapping("/add")
    public String AddView(System system, Model model) {
        Iterable<Galaxy> galaxies = galaxyRepository.findAll();
        model.addAttribute("galaxies", galaxies);
        return "system/system-add";
    }

    @PostMapping("/add")
    public String AddSystem(@ModelAttribute("system") @Valid System system, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            Iterable<Galaxy> galaxies = galaxyRepository.findAll();
            model.addAttribute("galaxies", galaxies);
            return "system/system-add";
        }
        systemRepository.save(system);
        return "redirect:/system/";
    }

    @GetMapping("/filter/")
    public String filter(
            @RequestParam(name = "name") String name_system,
            Model model) {
        List<System> systemList = systemRepository.findByName(name_system);
        model.addAttribute("system_list", systemList);
        return "system/system";
    }

    @GetMapping("/filtercontains/")
    public String filterContains(
            @RequestParam(name = "name") String name_system,
            Model model) {
        List<System> systemList = systemRepository.findByNameContains(name_system);
        model.addAttribute("system_list", systemList);
        return "system/system";
    }

    @GetMapping("/detail/{id}")
    public String detailSystem(
            @PathVariable Long id,
            Model model
    ) {
        System system_obj = systemRepository.findById(id).orElseThrow();
        model.addAttribute("one_system", system_obj);
        return "/system/info";
    }

    @GetMapping("/detail/{id}/del")
    public String delSystem(@PathVariable Long id) {
        System system_obj = systemRepository.findById(id).orElseThrow();
        systemRepository.delete(system_obj);
        return "redirect:/system/";
    }

    @GetMapping("/detail/{id}/upd")
    public String updateView(@PathVariable Long id, Model model, System system) {
        Iterable<Galaxy> galaxies = galaxyRepository.findAll();
        model.addAttribute("galaxies", galaxies);
        model.addAttribute("system",systemRepository.findById(id).orElseThrow());
        return "system/update";
    }

    @PostMapping("/detail/{id}/upd")
    public String updateSystem(@ModelAttribute("system") @Valid System system, BindingResult bindingResult, Model model) {
        if(bindingResult.hasErrors()) {
            Iterable<Galaxy> galaxies = galaxyRepository.findAll();
            model.addAttribute("galaxies", galaxies);
            return "system/update";
        }
        systemRepository.save(system);
        return "redirect:/system/detail/" + system.getUID();
    }
}

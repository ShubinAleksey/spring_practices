package com.example.demo2.Controllers;

import com.example.demo2.Models.Galaxy;
import com.example.demo2.Models.System;
import com.example.demo2.Repository.SystemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
        systemRepository.deleteById(id);
        return "redirect:/system/";
    }

    @GetMapping("/detail/{id}/upd")
    public String updateView(@PathVariable Long id, Model model) {
        model.addAttribute("object",systemRepository.findById(id).orElseThrow());
        return "system/update";
    }

    @PostMapping("/detail/{id}/upd")
    public String updateSystem(@PathVariable Long id,
                             @RequestParam String name,
                             @RequestParam(name = "system_mass") Long system_mass,
                             @RequestParam Integer total_stars,
                             @RequestParam Integer total_known_planets,
                             @RequestParam Integer total_moons
    ) {
        System system = systemRepository.findById(id).orElseThrow();
        system.setName(name);
        system.setSystem_mass(system_mass);
        system.setTotal_stars(total_stars);
        system.setTotal_known_planets(total_known_planets);
        system.setTotal_moons(total_moons);
        systemRepository.save(system);
        return "redirect:/system/detail/" + system.getUID();
    }
}

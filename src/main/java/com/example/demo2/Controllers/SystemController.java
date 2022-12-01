package com.example.demo2.Controllers;

import com.example.demo2.Models.Galaxy;
import com.example.demo2.Models.Star;
import com.example.demo2.Models.System;
import com.example.demo2.Repository.GalaxyRepository;
import com.example.demo2.Repository.SystemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    @PreAuthorize("hasAnyAuthority('ADMIN','NASA')")
    @GetMapping("/add")
    public String AddView(System system, Model model) {
        Iterable<Galaxy> galaxies = galaxyRepository.findAll();
        model.addAttribute("galaxies", galaxies);
        return "system/system-add";
    }

    @PreAuthorize("hasAnyAuthority('ADMIN','NASA')")
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

    @PreAuthorize("hasAnyAuthority('ADMIN','NASA')")
    @GetMapping("/detail/{id}/del")
    public String delSystem(@PathVariable Long id) {
        System system_obj = systemRepository.findById(id).orElseThrow();
        systemRepository.delete(system_obj);
        return "redirect:/system/";
    }

    @PreAuthorize("hasAnyAuthority('ADMIN','NASA')")
    @GetMapping("/detail/{id}/upd")
    public String updateView(@PathVariable Long id, Model model) {
        Optional<System> system = systemRepository.findById(id);
        ArrayList<System> systemArrayList = new ArrayList<>();
        system.ifPresent(systemArrayList::add);
        Iterable<Galaxy> galaxies = galaxyRepository.findAll();
        model.addAttribute("galaxies", galaxies);
        model.addAttribute("system",systemArrayList.get(0));
        return "system/update";
    }

    @PreAuthorize("hasAnyAuthority('ADMIN','NASA')")
    @PostMapping("/detail/{id}/upd")
    public String updateSystem(@PathVariable Long id, @ModelAttribute("system") @Valid System system, BindingResult bindingResult, Model model) {
        if(!systemRepository.existsById(id)) {
            return "redirect:/system/";
        }
        if(bindingResult.hasErrors()) {
            Iterable<Galaxy> galaxies = galaxyRepository.findAll();
            model.addAttribute("galaxies", galaxies);
            system.setUID(id);
            return "system/update";
        }
        Iterable<Galaxy> galaxies = galaxyRepository.findAll();
        model.addAttribute("galaxies", galaxies);
        system.setUID(id);
        systemRepository.save(system);
        return "redirect:/system/detail/" + system.getUID();
    }
}

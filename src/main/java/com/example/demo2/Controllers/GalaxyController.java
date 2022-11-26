package com.example.demo2.Controllers;

import com.example.demo2.Models.Galaxy;
import com.example.demo2.Repository.GalaxyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/galaxy")
public class GalaxyController {
    @Autowired
    GalaxyRepository galaxyRepository;

    @GetMapping("/")
    public String index(Model model) {
        Iterable<Galaxy> galaxyIterable = galaxyRepository.findAll();
        model.addAttribute("galaxy_list",galaxyIterable);
        return "/galaxy/galaxy";
    }

    @GetMapping("/add")
    public String AddView(Galaxy galaxy) {
        return "galaxy/galaxy-add";
    }

    @PostMapping("/add")
    public String AddStar(@ModelAttribute("galaxy") @Valid Galaxy galaxy, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "galaxy/galaxy-add";
        }
        galaxyRepository.save(galaxy);
        return "redirect:/galaxy/";
    }

    @GetMapping("/filter/")
    public String filter(
            @RequestParam(name = "name") String name_galaxy,
            Model model) {
        List<Galaxy> galaxyList = galaxyRepository.findByName(name_galaxy);
        model.addAttribute("galaxy_list", galaxyList);
        return "galaxy/galaxy";
    }

    @GetMapping("/filtercontains/")
    public String filterContains(
            @RequestParam(name = "name") String name_galaxy,
            Model model) {
        List<Galaxy> galaxyList = galaxyRepository.findByNameContains(name_galaxy);
        model.addAttribute("galaxy_list", galaxyList);
        return "galaxy/galaxy";
    }

    @GetMapping("/detail/{id}")
    public String detailGalaxy(
            @PathVariable Long id,
            Model model
    ) {
        Galaxy galaxy_obj = galaxyRepository.findById(id).orElseThrow();
        model.addAttribute("one_galaxy", galaxy_obj);
        return "/galaxy/info";
    }

    @GetMapping("/detail/{id}/del")
    public String delGalaxy(@PathVariable Long id) {
        Galaxy galaxy_obj = galaxyRepository.findById(id).orElseThrow();
        galaxyRepository.delete(galaxy_obj);
        return "redirect:/galaxy/";
    }

    @GetMapping("/detail/{id}/upd")
    public String updateView(@PathVariable Long id, Model model, Galaxy galaxy) {
        model.addAttribute("galaxy",galaxyRepository.findById(id).orElseThrow());
        return "galaxy/update";
    }

    @PostMapping("/detail/{id}/upd")
    public String updateStar(@ModelAttribute("galaxy") @Valid Galaxy galaxy, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            return "galaxy/update";
        }
        galaxyRepository.save(galaxy);
        return "redirect:/galaxy/detail/" + galaxy.getUID();
    }
}

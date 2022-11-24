package com.example.demo2.Controllers;

import com.example.demo2.Models.Galaxy;
import com.example.demo2.Repository.GalaxyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
    public String AddView() {
        return "galaxy/galaxy-add";
    }

    @PostMapping("/add")
    public String AddStar(@RequestParam(name = "name") String name,
                          @RequestParam(name = "galaxy_mass") Long galaxy_mass,
                          @RequestParam(name = "galaxy_radius") String galaxy_radius,
                          @RequestParam(name = "absolute_star") String absolute_star,
                          @RequestParam(name = "total_stars") int total_stars) {
        Galaxy new_galaxy = new Galaxy(name, galaxy_mass, galaxy_radius, absolute_star, total_stars);
        galaxyRepository.save(new_galaxy);
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
        galaxyRepository.deleteById(id);
        return "redirect:/galaxy/";
    }

    @GetMapping("/detail/{id}/upd")
    public String updateView(@PathVariable Long id, Model model) {
        model.addAttribute("object",galaxyRepository.findById(id).orElseThrow());
        return "galaxy/update";
    }

    @PostMapping("/detail/{id}/upd")
    public String updateStar(@PathVariable Long id,
                             @RequestParam String name,
                             @RequestParam(name = "galaxy_mass") Long galaxy_mass,
                             @RequestParam String galaxy_radius,
                             @RequestParam String absolute_star,
                             @RequestParam Integer total_stars
                             ) {
        Galaxy galaxy = galaxyRepository.findById(id).orElseThrow();
        galaxy.setName(name);
        galaxy.setGalaxy_mass(galaxy_mass);
        galaxy.setGalaxy_radius(galaxy_radius);
        galaxy.setAbsolute_star(absolute_star);
        galaxy.setTotal_stars(total_stars);
        galaxyRepository.save(galaxy);
        return "redirect:/galaxy/detail/" + galaxy.getUID();
    }
}

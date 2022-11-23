package com.example.demo2.Controllers;

import com.example.demo2.Models.Galaxy;
import com.example.demo2.Repository.GalaxyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
}

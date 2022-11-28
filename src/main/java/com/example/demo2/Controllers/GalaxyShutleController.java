package com.example.demo2.Controllers;
import com.example.demo2.Models.GalaxyShutle;
import com.example.demo2.Models.System;
import com.example.demo2.Repository.GalaxyShutleRepository;
import com.example.demo2.Repository.SystemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/galaxyshutle")
public class GalaxyShutleController {

    @Autowired
    private GalaxyShutleRepository galaxyShutleRepository;

    @Autowired
    private SystemRepository systemRepository;

    @GetMapping("/")
    public String index(Model model) {
        Iterable<GalaxyShutle> galaxyShutleIterable = galaxyShutleRepository.findAll();
        model.addAttribute("galaxyShutle_list",galaxyShutleIterable);
        return "/galaxyshutle/galaxyshutle";
    }

    @GetMapping("/add")
    public String AddView(GalaxyShutle galaxyShutle, Model model) {
        Iterable<System> systems = systemRepository.findAll();
        model.addAttribute("systems", systems);
        return "galaxyshutle/galaxyshutle-add";
    }

    @PostMapping("/add")
    public String AddStar(@ModelAttribute("galaxyShutle") @Valid GalaxyShutle galaxyShutle, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            Iterable<System> systems = systemRepository.findAll();
            model.addAttribute("systems", systems);
            return "galaxyshutle/galaxyshutle-add";
        }
        galaxyShutleRepository.save(galaxyShutle);
        return "redirect:/galaxyshutle/";
    }

    @GetMapping("/filter/")
    public String filter(
            @RequestParam(name = "name") String name_galaxyshutle,
            Model model) {
        List<GalaxyShutle> galaxyShutleList = galaxyShutleRepository.findByName(name_galaxyshutle);
        model.addAttribute("galaxyShutle_list", galaxyShutleList);
        return "galaxyshutle/galaxyshutle";
    }

    @GetMapping("/filtercontains/")
    public String filterContains(
            @RequestParam(name = "name") String name_galaxyshutle,
            Model model) {
        List<GalaxyShutle> galaxyShutleList = galaxyShutleRepository.findByNameContains(name_galaxyshutle);
        model.addAttribute("galaxyShutle_list", galaxyShutleList);
        return "galaxyshutle/galaxyshutle";
    }

    @GetMapping("/detail/{id}")
    public String detailStar(
            @PathVariable Long id,
            Model model
    ) {
        GalaxyShutle galaxyShutle_obj = galaxyShutleRepository.findById(id).orElseThrow();
        model.addAttribute("one_galaxyShutle", galaxyShutle_obj);
        return "/galaxyshutle/info";
    }

    @GetMapping("/detail/{id}/del")
    public String delStars(@PathVariable Long id) {
        GalaxyShutle galaxyShutle_obj = galaxyShutleRepository.findById(id).orElseThrow();
        galaxyShutleRepository.delete(galaxyShutle_obj);
        return "redirect:/galaxyshutle/";
    }

    @GetMapping("/detail/{id}/upd")
    public String updateView(@PathVariable Long id, Model model, GalaxyShutle galaxyShutle) {
        Iterable<System> systems = systemRepository.findAll();
        model.addAttribute("systems", systems);
        model.addAttribute("galaxyShutle",galaxyShutleRepository.findById(id).orElseThrow());
        return "galaxyshutle/update";
    }

    @PostMapping("/detail/{id}/upd")
    public String updateStar(@ModelAttribute("galaxyShutle") @Valid GalaxyShutle galaxyShutle, BindingResult bindingResult, Model model) {
        if(bindingResult.hasErrors()) {
            Iterable<System> systems = systemRepository.findAll();
            model.addAttribute("systems", systems);
            return "galaxyshutle/update";
        }
        galaxyShutleRepository.save(galaxyShutle);
        return "redirect:/galaxyshutle/detail/" + galaxyShutle.getUID();
    }
}
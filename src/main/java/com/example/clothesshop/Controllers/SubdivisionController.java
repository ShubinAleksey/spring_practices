package com.example.clothesshop.Controllers;

import com.example.clothesshop.Models.ColorProduct;
import com.example.clothesshop.Models.Subdivision;
import com.example.clothesshop.Repository.SubdivisionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/subdivision")
public class SubdivisionController {
    @Autowired
    SubdivisionRepository subdivisionRepository;

    @GetMapping("/")
    public String index(Model model) {
        Iterable<Subdivision> subdivisionIterable = subdivisionRepository.findAll();
        model.addAttribute("subdivision_list",subdivisionIterable);
        return "/subdivision/subdivision";
    }

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @GetMapping("/add")
    public String AddView(Subdivision subdivision) {
        return "subdivision/subdivision-add";
    }

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @PostMapping("/add")
    public String AddStar(@ModelAttribute("subdivision") @Valid Subdivision subdivision, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "subdivision/subdivision-add";
        }
        subdivisionRepository.save(subdivision);
        return "redirect:/subdivision/";
    }

    @GetMapping("/filter/")
    public String filter(
            @RequestParam(name = "name") String subdivision_name,
            Model model) {
        List<Subdivision> subdivisionList = subdivisionRepository.findBySubdivisionName(subdivision_name);
        model.addAttribute("subdivision_list", subdivisionList);
        return "subdivision/subdivision";
    }

    @GetMapping("/filtercontains/")
    public String filterContains(
            @RequestParam(name = "name") String subdivision_name,
            Model model) {
        List<Subdivision> subdivisionList = subdivisionRepository.findBySubdivisionNameContains(subdivision_name);
        model.addAttribute("subdivision_list", subdivisionList);
        return "subdivision/subdivision";
    }

    @GetMapping("/detail/{id}")
    public String detailGalaxy(
            @PathVariable Long id,
            Model model) {
        Subdivision subdivision_obj = subdivisionRepository.findById(id).orElseThrow();
        model.addAttribute("one_subdivision", subdivision_obj);
        return "/subdivision/info";
    }

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @GetMapping("/detail/{id}/del")
    public String delGalaxy(@PathVariable Long id) {
        Subdivision subdivision_obj = subdivisionRepository.findById(id).orElseThrow();
        subdivisionRepository.delete(subdivision_obj);
        return "redirect:/subdivision/";
    }

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @GetMapping("/detail/{id}/upd")
    public String updateView(@PathVariable Long id, Model model, Subdivision subdivision) {
        model.addAttribute("subdivision",subdivisionRepository.findById(id).orElseThrow());
        return "subdivision/update";
    }

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @PostMapping("/detail/{id}/upd")
    public String updateStar(@PathVariable Long id, @ModelAttribute("subdivision") @Valid Subdivision subdivision, BindingResult bindingResult) {
        if(!subdivisionRepository.existsById(id)) {
            return "redirect:/subdivision/";
        }
        if(bindingResult.hasErrors()) {
            subdivision.setUID(id);
            return "subdivision/update";
        }
        subdivision.setUID(id);
        subdivisionRepository.save(subdivision);
        return "redirect:/subdivision/detail/" + subdivision.getUID();
    }
}

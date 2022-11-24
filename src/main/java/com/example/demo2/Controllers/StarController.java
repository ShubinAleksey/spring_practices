package com.example.demo2.Controllers;

import com.example.demo2.Models.Star;
import com.example.demo2.Repository.StarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/star")
public class StarController {

    @Autowired
    StarRepository starRepository;

    @GetMapping("/")
    public String index(Model model) {
        Iterable<Star> starIterable = starRepository.findAll();
        model.addAttribute("star_list",starIterable);
        return "/star/star";
    }

    @GetMapping("/add")
    public String AddView() {
        return "star/star-add";
    }

    @PostMapping("/add")
    public String AddStar(@RequestParam(name = "name") String name,
                          @RequestParam(name = "class") String class_star,
                          @RequestParam(name = "lumen") int lumen) {
        Star new_star = new Star(name, class_star, lumen);
        starRepository.save(new_star);
        return "redirect:/star/";
    }

    @GetMapping("/filter/")
    public String filter(
            @RequestParam(name = "name") String name_star,
            Model model) {
        List<Star> starList = starRepository.findByName(name_star);
        model.addAttribute("star_list", starList);
        return "star/star";
    }

    @GetMapping("/filtercontains/")
    public String filterContains(
            @RequestParam(name = "name") String name_star,
            Model model) {
        List<Star> starList = starRepository.findByNameContains(name_star);
        model.addAttribute("star_list", starList);
        return "star/star";
    }

    @GetMapping("/detail/{id}")
    public String detailStar(
            @PathVariable Long id,
            Model model
    ) {
        Star star_obj = starRepository.findById(id).orElseThrow();
        model.addAttribute("one_star", star_obj);
        return "/star/info";
    }

    @GetMapping("/detail/{id}/del")
    public String delStars(@PathVariable Long id) {
        Star star_obj = starRepository.findById(id).orElseThrow();
        starRepository.delete(star_obj);
        return "redirect:/star/";
    }

    @GetMapping("/detail/{id}/upd")
    public String updateView(@PathVariable Long id, Model model) {
        model.addAttribute("object",starRepository.findById(id).orElseThrow());
        return "star/update";
    }

    @PostMapping("/detail/{id}/upd")
    public String updateStar(@PathVariable Long id,
                             @RequestParam String name,
                             @RequestParam(name = "class") String class_star,
                             @RequestParam Integer lumen) {
        Star star = starRepository.findById(id).orElseThrow();
        star.setName(name);
        star.setClass_star(class_star);
        star.setLumen(lumen);
        starRepository.save(star);
        return "redirect:/star/detail/" + star.getUID();
    }
}

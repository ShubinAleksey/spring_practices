package com.example.clothesshop.Controllers;

import com.example.clothesshop.Models.ColorProduct;
import com.example.clothesshop.Repository.ColorProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/colorproduct")
public class ColorProductController {
    @Autowired
    ColorProductRepository colorProductRepository;

    @PreAuthorize("hasAnyAuthority('ADMIN','CASHIER','MERCHANDISER')")
    @GetMapping("/")
    public String index(Model model) {
        Iterable<ColorProduct> colorProductIterable = colorProductRepository.findAll();
        model.addAttribute("colorproduct_list",colorProductIterable);
        return "/colorproduct/colorproduct";
    }

    @PreAuthorize("hasAnyAuthority('ADMIN','CASHIER')")
    @GetMapping("/add")
    public String AddView(ColorProduct colorProduct) {
        return "colorproduct/colorproduct-add";
    }

    @PreAuthorize("hasAnyAuthority('ADMIN','CASHIER')")
    @PostMapping("/add")
    public String AddStar(@ModelAttribute("colorProduct") @Valid ColorProduct colorProduct, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "colorproduct/colorproduct-add";
        }
        colorProductRepository.save(colorProduct);
        return "redirect:/colorproduct/";
    }

    @PreAuthorize("hasAnyAuthority('ADMIN','CASHIER','MERCHANDISER')")
    @GetMapping("/filter/")
    public String filter(
            @RequestParam(name = "name") String color_name,
            Model model) {
        List<ColorProduct> colorProductList = colorProductRepository.findByColorName(color_name);
        model.addAttribute("colorproduct_list", colorProductList);
        return "colorproduct/colorproduct";
    }

    @PreAuthorize("hasAnyAuthority('ADMIN','CASHIER','MERCHANDISER')")
    @GetMapping("/filtercontains/")
    public String filterContains(
            @RequestParam(name = "name") String color_name,
            Model model) {
        List<ColorProduct> colorProductList = colorProductRepository.findByColorNameContains(color_name);
        model.addAttribute("colorproduct_list", colorProductList);
        return "colorproduct/colorproduct";
    }

    @PreAuthorize("hasAnyAuthority('ADMIN','CASHIER','MERCHANDISER')")
    @GetMapping("/detail/{id}")
    public String detailGalaxy(
            @PathVariable Long id,
            Model model) {
        ColorProduct colorproduct_obj = colorProductRepository.findById(id).orElseThrow();
        model.addAttribute("one_colorproduct", colorproduct_obj);
        return "/colorproduct/info";
    }

    @PreAuthorize("hasAnyAuthority('ADMIN','CASHIER')")
    @GetMapping("/detail/{id}/del")
    public String delGalaxy(@PathVariable Long id) {
        ColorProduct colorproduct_obj = colorProductRepository.findById(id).orElseThrow();
        colorProductRepository.delete(colorproduct_obj);
        return "redirect:/colorproduct/";
    }

    @PreAuthorize("hasAnyAuthority('ADMIN','CASHIER')")
    @GetMapping("/detail/{id}/upd")
    public String updateView(@PathVariable Long id, Model model, ColorProduct colorProduct) {
        model.addAttribute("colorproduct",colorProductRepository.findById(id).orElseThrow());
        return "colorproduct/update";
    }

    @PreAuthorize("hasAnyAuthority('ADMIN','CASHIER')")
    @PostMapping("/detail/{id}/upd")
    public String updateStar(@PathVariable Long id, @ModelAttribute("colorProduct") @Valid ColorProduct colorProduct, BindingResult bindingResult) {
        if(!colorProductRepository.existsById(id)) {
            return "redirect:/colorproduct/";
        }
        if(bindingResult.hasErrors()) {
            colorProduct.setUID(id);
            return "colorproduct/update";
        }
        colorProduct.setUID(id);
        colorProductRepository.save(colorProduct);
        return "redirect:/colorproduct/detail/" + colorProduct.getUID();
    }
}

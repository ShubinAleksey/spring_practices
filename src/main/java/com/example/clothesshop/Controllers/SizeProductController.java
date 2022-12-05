package com.example.clothesshop.Controllers;

import com.example.clothesshop.Models.SizeProduct;
import com.example.clothesshop.Repository.SizeProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/sizeproduct")
public class SizeProductController {
    @Autowired
    SizeProductRepository sizeProductRepository;

    @GetMapping("/")
    public String index(Model model) {
        Iterable<SizeProduct> sizeProductIterable = sizeProductRepository.findAll();
        model.addAttribute("sizeproduct_list",sizeProductIterable);
        return "/sizeproduct/sizeproduct";
    }

    @PreAuthorize("hasAnyAuthority('ADMIN','CASHIER')")
    @GetMapping("/add")
    public String AddView(SizeProduct sizeProduct) {
        return "sizeproduct/sizeproduct-add";
    }

    @PreAuthorize("hasAnyAuthority('ADMIN','CASHIER')")
    @PostMapping("/add")
    public String AddStar(@ModelAttribute("sizeProduct") @Valid SizeProduct sizeProduct, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "sizeproduct/sizeproduct-add";
        }
        sizeProductRepository.save(sizeProduct);
        return "redirect:/sizeproduct/";
    }

    @GetMapping("/filter/")
    public String filter(
            @RequestParam(name = "name") String size_name,
            Model model) {
        List<SizeProduct> sizeProductList = sizeProductRepository.findBySizeName(size_name);
        model.addAttribute("sizeproduct_list", sizeProductList);
        return "sizeproduct/sizeproduct";
    }

    @GetMapping("/filtercontains/")
    public String filterContains(
            @RequestParam(name = "name") String size_name,
            Model model) {
        List<SizeProduct> sizeProductList = sizeProductRepository.findBySizeNameContains(size_name);
        model.addAttribute("sizeproduct_list", sizeProductList);
        return "sizeproduct/sizeproduct";
    }

    @GetMapping("/detail/{id}")
    public String detailGalaxy(
            @PathVariable Long id,
            Model model) {
        SizeProduct sizeProduct_obj = sizeProductRepository.findById(id).orElseThrow();
        model.addAttribute("one_sizeproduct", sizeProduct_obj);
        return "/sizeproduct/info";
    }

    @PreAuthorize("hasAnyAuthority('ADMIN','CASHIER')")
    @GetMapping("/detail/{id}/del")
    public String delGalaxy(@PathVariable Long id) {
        SizeProduct sizeProduct_obj = sizeProductRepository.findById(id).orElseThrow();
        sizeProductRepository.delete(sizeProduct_obj);
        return "redirect:/sizeproduct/";
    }

    @PreAuthorize("hasAnyAuthority('ADMIN','CASHIER')")
    @GetMapping("/detail/{id}/upd")
    public String updateView(@PathVariable Long id, Model model, SizeProduct sizeProduct) {
        model.addAttribute("sizeproduct",sizeProductRepository.findById(id).orElseThrow());
        return "sizeproduct/update";
    }

    @PreAuthorize("hasAnyAuthority('ADMIN','CASHIER')")
    @PostMapping("/detail/{id}/upd")
    public String updateStar(@PathVariable Long id, @ModelAttribute("sizeProduct") @Valid SizeProduct sizeProduct, BindingResult bindingResult) {
        if(!sizeProductRepository.existsById(id)) {
            return "redirect:/sizeproduct/";
        }
        if(bindingResult.hasErrors()) {
            sizeProduct.setUID(id);
            return "sizeproduct/update";
        }
        sizeProduct.setUID(id);
        sizeProductRepository.save(sizeProduct);
        return "redirect:/sizeproduct/detail/" + sizeProduct.getUID();
    }
}

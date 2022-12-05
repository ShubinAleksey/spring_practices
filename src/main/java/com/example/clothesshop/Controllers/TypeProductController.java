package com.example.clothesshop.Controllers;

import com.example.clothesshop.Models.TypeProduct;
import com.example.clothesshop.Repository.TypeProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/typeproduct")
public class TypeProductController {
    @Autowired
    TypeProductRepository typeProductRepository;

    @GetMapping("/")
    public String index(Model model) {
        Iterable<TypeProduct> typeProductIterable = typeProductRepository.findAll();
        model.addAttribute("typeproduct_list",typeProductIterable);
        return "/typeproduct/typeproduct";
    }

    @PreAuthorize("hasAnyAuthority('ADMIN','CASHIER')")
    @GetMapping("/add")
    public String AddView(TypeProduct typeProduct) {
        return "typeproduct/typeproduct-add";
    }

    @PreAuthorize("hasAnyAuthority('ADMIN','CASHIER')")
    @PostMapping("/add")
    public String AddStar(@ModelAttribute("typeProduct") @Valid TypeProduct typeProduct, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "typeproduct/typeproduct-add";
        }
        typeProductRepository.save(typeProduct);
        return "redirect:/typeproduct/";
    }

    @GetMapping("/filter/")
    public String filter(
            @RequestParam(name = "name") String type_name,
            Model model) {
        List<TypeProduct> typeProductList = typeProductRepository.findByTypeName(type_name);
        model.addAttribute("typeproduct_list", typeProductList);
        return "typeproduct/typeproduct";
    }

    @GetMapping("/filtercontains/")
    public String filterContains(
            @RequestParam(name = "name") String type_name,
            Model model) {
        List<TypeProduct> typeProductList = typeProductRepository.findByTypeNameContains(type_name);
        model.addAttribute("typeproduct_list", typeProductList);
        return "typeproduct/typeproduct";
    }

    @GetMapping("/detail/{id}")
    public String detailGalaxy(
            @PathVariable Long id,
            Model model) {
        TypeProduct typeProduct_obj = typeProductRepository.findById(id).orElseThrow();
        model.addAttribute("one_typeproduct", typeProduct_obj);
        return "/typeproduct/info";
    }

    @PreAuthorize("hasAnyAuthority('ADMIN','CASHIER')")
    @GetMapping("/detail/{id}/del")
    public String delGalaxy(@PathVariable Long id) {
        TypeProduct typeProduct_obj = typeProductRepository.findById(id).orElseThrow();
        typeProductRepository.delete(typeProduct_obj);
        return "redirect:/typeproduct/";
    }

    @PreAuthorize("hasAnyAuthority('ADMIN','CASHIER')")
    @GetMapping("/detail/{id}/upd")
    public String updateView(@PathVariable Long id, Model model, TypeProduct typeProduct) {
        model.addAttribute("typeproduct",typeProductRepository.findById(id).orElseThrow());
        return "typeproduct/update";
    }

    @PreAuthorize("hasAnyAuthority('ADMIN','CASHIER')")
    @PostMapping("/detail/{id}/upd")
    public String updateStar(@PathVariable Long id, @ModelAttribute("typeProduct") @Valid TypeProduct typeProduct, BindingResult bindingResult) {
        if(!typeProductRepository.existsById(id)) {
            return "redirect:/typeproduct/";
        }
        if(bindingResult.hasErrors()) {
            typeProduct.setUID(id);
            return "typeproduct/update";
        }
        typeProduct.setUID(id);
        typeProductRepository.save(typeProduct);
        return "redirect:/typeproduct/detail/" + typeProduct.getUID();
    }
}

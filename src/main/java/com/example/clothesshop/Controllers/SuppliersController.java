package com.example.clothesshop.Controllers;

import com.example.clothesshop.Models.Suppliers;
import com.example.clothesshop.Repository.SuppliersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/suppliers")
@PreAuthorize("hasAnyAuthority('ADMIN','MERCHANDISER')")
public class SuppliersController {
    @Autowired
    SuppliersRepository suppliersRepository;

    @GetMapping("/")
    public String index(Model model) {
        Iterable<Suppliers> suppliersIterable = suppliersRepository.findAll();
        model.addAttribute("suppliers_list",suppliersIterable);
        return "/suppliers/suppliers";
    }

    @GetMapping("/add")
    public String AddView(Suppliers suppliers) {
        return "suppliers/suppliers-add";
    }

    @PostMapping("/add")
    public String AddStar(@ModelAttribute("suppliers") @Valid Suppliers suppliers, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "suppliers/suppliers-add";
        }
        suppliersRepository.save(suppliers);
        return "redirect:/suppliers/";
    }

    @GetMapping("/filter/")
    public String filter(
            @RequestParam(name = "name") String suppliers_name,
            Model model) {
        List<Suppliers> suppliersList = suppliersRepository.findBySuppliersName(suppliers_name);
        model.addAttribute("suppliers_list", suppliersList);
        return "suppliers/suppliers";
    }

    @GetMapping("/filtercontains/")
    public String filterContains(
            @RequestParam(name = "name") String suppliers_name,
            Model model) {
        List<Suppliers> suppliersList = suppliersRepository.findBySuppliersNameContains(suppliers_name);
        model.addAttribute("suppliers_list", suppliersList);
        return "suppliers/suppliers";
    }

    @GetMapping("/detail/{id}")
    public String detailGalaxy(
            @PathVariable Long id,
            Model model) {
        Suppliers suppliers_obj = suppliersRepository.findById(id).orElseThrow();
        model.addAttribute("one_suppliers", suppliers_obj);
        return "/suppliers/info";
    }

    @GetMapping("/detail/{id}/del")
    public String delGalaxy(@PathVariable Long id) {
        Suppliers suppliers_obj = suppliersRepository.findById(id).orElseThrow();
        suppliersRepository.delete(suppliers_obj);
        return "redirect:/suppliers/";
    }

    @GetMapping("/detail/{id}/upd")
    public String updateView(@PathVariable Long id, Model model, Suppliers suppliers) {
        model.addAttribute("suppliers",suppliersRepository.findById(id).orElseThrow());
        return "suppliers/update";
    }

    @PostMapping("/detail/{id}/upd")
    public String updateStar(@PathVariable Long id, @ModelAttribute("suppliers") @Valid Suppliers suppliers, BindingResult bindingResult) {
        if(!suppliersRepository.existsById(id)) {
            return "redirect:/suppliers/";
        }
        if(bindingResult.hasErrors()) {
            suppliers.setUID(id);
            return "suppliers/update";
        }
        suppliers.setUID(id);
        suppliersRepository.save(suppliers);
        return "redirect:/suppliers/detail/" + suppliers.getUID();
    }
}

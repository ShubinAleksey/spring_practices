package com.example.clothesshop.Controllers;

import com.example.clothesshop.Models.*;
import com.example.clothesshop.Repository.DeliveryRepository;
import com.example.clothesshop.Repository.ProductRepository;
import com.example.clothesshop.Repository.SuppliersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/delivery")
@PreAuthorize("hasAnyAuthority('ADMIN','MERCHANDISER')")
public class DeliveryController {
    @Autowired
    DeliveryRepository deliveryRepository;
    @Autowired
    SuppliersRepository suppliersRepository;
    @Autowired
    ProductRepository productRepository;

    @GetMapping("/")
    public String index(Model model) {
        Iterable<Delivery> deliveryIterable = deliveryRepository.findAll();
        model.addAttribute("delivery_list",deliveryIterable);
        return "/delivery/delivery";
    }

    @GetMapping("/add")
    public String AddView(Delivery delivery, Model model) {
        Iterable<Suppliers> suppliers = suppliersRepository.findAll();
        Iterable<Product> products = productRepository.findAll();
        model.addAttribute("suppliers", suppliers);
        model.addAttribute("products", products);
        return "delivery/delivery-add";
    }

    @PostMapping("/add")
    public String AddStar(@ModelAttribute("delivery") @Valid Delivery delivery, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            Iterable<Suppliers> suppliers = suppliersRepository.findAll();
            Iterable<Product> products = productRepository.findAll();
            model.addAttribute("suppliers", suppliers);
            model.addAttribute("products", products);
            return "delivery/delivery-add";
        }
        deliveryRepository.save(delivery);
        return "redirect:/delivery/";
    }

    @GetMapping("/detail/{id}")
    public String detailStar(
            @PathVariable Long id,
            Model model) {
        Delivery delivery_obj = deliveryRepository.findById(id).orElseThrow();
        model.addAttribute("one_delivery", delivery_obj);
        return "/delivery/info";
    }

    @GetMapping("/detail/{id}/del")
    public String delStars(@PathVariable Long id) {
        Delivery delivery_obj = deliveryRepository.findById(id).orElseThrow();
        deliveryRepository.delete(delivery_obj);
        return "redirect:/delivery/";
    }

    @GetMapping("/detail/{id}/upd")
    public String updateView(@PathVariable Long id, Model model) {
        Optional<Delivery> delivery = deliveryRepository.findById(id);
        ArrayList<Delivery> deliveryArrayList = new ArrayList<>();
        delivery.ifPresent(deliveryArrayList::add);
        Iterable<Suppliers> suppliers = suppliersRepository.findAll();
        Iterable<Product> products = productRepository.findAll();
        model.addAttribute("suppliers", suppliers);
        model.addAttribute("products", products);
        model.addAttribute("delivery",deliveryArrayList.get(0));
        return "delivery/update";
    }

    @PostMapping("/detail/{id}/upd")
    public String updateStar(@PathVariable Long id, @ModelAttribute("delivery") @Valid Delivery delivery,
                             BindingResult bindingResult,
                             Model model) {
        if(!deliveryRepository.existsById(id)) {
            return "redirect:/delivery/";
        }
        if(bindingResult.hasErrors()) {
            Iterable<Suppliers> suppliers = suppliersRepository.findAll();
            Iterable<Product> products = productRepository.findAll();
            model.addAttribute("suppliers", suppliers);
            model.addAttribute("products", products);
            delivery.setUID(id);
            return "delivery/update";
        }
        Iterable<Suppliers> suppliers = suppliersRepository.findAll();
        Iterable<Product> products = productRepository.findAll();
        model.addAttribute("suppliers", suppliers);
        model.addAttribute("products", products);
        delivery.setUID(id);
        deliveryRepository.save(delivery);
        return "redirect:/delivery/detail/" + delivery.getUID();
    }
}

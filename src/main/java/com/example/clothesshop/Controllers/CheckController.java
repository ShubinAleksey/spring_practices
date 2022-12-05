package com.example.clothesshop.Controllers;

import com.example.clothesshop.Models.Check;
import com.example.clothesshop.Models.Order;
import com.example.clothesshop.Models.User;
import com.example.clothesshop.Repository.CheckRepository;
import com.example.clothesshop.Repository.OrderRepository;
import com.example.clothesshop.Repository.UserRepository;
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
@RequestMapping("/check")
public class CheckController {

    @Autowired
    CheckRepository checkRepository;

    @Autowired
    OrderRepository orderRepository;
    @Autowired
    UserRepository userRepository;

    @PreAuthorize("hasAnyAuthority('ADMIN','USER','CASHIER','PURCHASER')")
    @GetMapping("/")
    public String index(Model model) {
        Iterable<Check> checkIterable = checkRepository.findAll();
        model.addAttribute("check_list",checkIterable);
        return "/check/check";
    }

    @PreAuthorize("hasAnyAuthority('ADMIN','CASHIER')")
    @GetMapping("/add")
    public String AddView(Check check, Model model) {
        Iterable<Order> orders = orderRepository.findAll();
        Iterable<User> users = userRepository.findAll();
        model.addAttribute("orders", orders);
        model.addAttribute("users", users);
        return "check/check-add";
    }

    @PreAuthorize("hasAnyAuthority('ADMIN','CASHIER')")
    @PostMapping("/add")
    public String AddStar(@ModelAttribute("check") @Valid Check check, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            Iterable<Order> orders = orderRepository.findAll();
            Iterable<User> users = userRepository.findAll();
            model.addAttribute("orders", orders);
            model.addAttribute("users", users);
            return "check/check-add";
        }
        checkRepository.save(check);
        return "redirect:/check/";
    }

    @PreAuthorize("hasAnyAuthority('ADMIN','USER','CASHIER','PURCHASER')")
    @GetMapping("/detail/{id}")
    public String detailStar(
            @PathVariable Long id,
            Model model) {
        Check check_obj = checkRepository.findById(id).orElseThrow();
        model.addAttribute("one_check", check_obj);
        return "/check/info";
    }

    @PreAuthorize("hasAnyAuthority('ADMIN','CASHIER')")
    @GetMapping("/detail/{id}/del")
    public String delStars(@PathVariable Long id) {
        Check check_obj = checkRepository.findById(id).orElseThrow();
        checkRepository.delete(check_obj);
        return "redirect:/check/";
    }

    @PreAuthorize("hasAnyAuthority('ADMIN','CASHIER')")
    @GetMapping("/detail/{id}/upd")
    public String updateView(@PathVariable Long id, Model model) {
        Optional<Check> check = checkRepository.findById(id);
        ArrayList<Check> checkArrayList = new ArrayList<>();
        check.ifPresent(checkArrayList::add);
        Iterable<Order> orders = orderRepository.findAll();
        Iterable<User> users = userRepository.findAll();
        model.addAttribute("orders", orders);
        model.addAttribute("users", users);
        model.addAttribute("check",checkArrayList.get(0));
        return "check/update";
    }

    @PreAuthorize("hasAnyAuthority('ADMIN','CASHIER')")
    @PostMapping("/detail/{id}/upd")
    public String updateStar(@PathVariable Long id, @ModelAttribute("check") @Valid Check check,
                             BindingResult bindingResult,
                             Model model) {
        if(!checkRepository.existsById(id)) {
            return "redirect:/check/";
        }
        if(bindingResult.hasErrors()) {
            Iterable<Order> orders = orderRepository.findAll();
            Iterable<User> users = userRepository.findAll();
            model.addAttribute("orders", orders);
            model.addAttribute("users", users);
            check.setUID(id);
            return "check/update";
        }
        Iterable<Order> orders = orderRepository.findAll();
        Iterable<User> users = userRepository.findAll();
        model.addAttribute("orders", orders);
        model.addAttribute("users", users);
        check.setUID(id);
        checkRepository.save(check);
        return "redirect:/check/detail/" + check.getUID();
    }
}

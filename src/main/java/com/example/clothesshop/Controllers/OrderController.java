package com.example.clothesshop.Controllers;

import com.example.clothesshop.Models.Order;
import com.example.clothesshop.Models.Product;
import com.example.clothesshop.Models.User;
import com.example.clothesshop.Repository.OrderRepository;
import com.example.clothesshop.Repository.ProductRepository;
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
@RequestMapping("/order")
public class OrderController {
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    UserRepository userRepository;

    @PreAuthorize("hasAnyAuthority('ADMIN','USER','CASHIER','PURCHASER')")
    @GetMapping("/")
    public String index(Model model) {
        Iterable<Order> orderIterable = orderRepository.findAll();
        model.addAttribute("order_list",orderIterable);
        return "/order/order";
    }

    @PreAuthorize("hasAnyAuthority('ADMIN','USER','CASHIER')")
    @GetMapping("/add")
    public String AddView(Order order, Model model) {
        Iterable<Product> products = productRepository.findAll();
        Iterable<User> users = userRepository.findAll();
        model.addAttribute("products", products);
        model.addAttribute("users", users);
        return "order/order-add";
    }

    @PreAuthorize("hasAnyAuthority('ADMIN','USER','CASHIER')")
    @PostMapping("/add")
    public String AddStar(@ModelAttribute("order") @Valid Order order, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            Iterable<Product> products = productRepository.findAll();
            Iterable<User> users = userRepository.findAll();
            model.addAttribute("products", products);
            model.addAttribute("users", users);
            return "order/order-add";
        }
        orderRepository.save(order);
        return "redirect:/order/";
    }

    @PreAuthorize("hasAnyAuthority('ADMIN','USER','CASHIER','PURCHASER')")
    @GetMapping("/filter/")
    public String filter(
            @RequestParam(name = "name") String order_name,
            Model model) {
        List<Order> orderList = orderRepository.findByOrderName(order_name);
        model.addAttribute("order_list", orderList);
        return "order/order";
    }

    @PreAuthorize("hasAnyAuthority('ADMIN','USER','CASHIER','PURCHASER')")
    @GetMapping("/filtercontains/")
    public String filterContains(
            @RequestParam(name = "name") String order_name,
            Model model) {
        List<Order> orderList = orderRepository.findByOrderNameContains(order_name);
        model.addAttribute("order_list", orderList);
        return "order/order";
    }

    @PreAuthorize("hasAnyAuthority('ADMIN','USER','CASHIER','PURCHASER')")
    @GetMapping("/detail/{id}")
    public String detailStar(
            @PathVariable Long id,
            Model model) {
        Order order_obj = orderRepository.findById(id).orElseThrow();
        model.addAttribute("one_order", order_obj);
        return "/order/info";
    }

    @PreAuthorize("hasAnyAuthority('ADMIN','USER','CASHIER')")
    @GetMapping("/detail/{id}/del")
    public String delStars(@PathVariable Long id) {
        Order order_obj = orderRepository.findById(id).orElseThrow();
        orderRepository.delete(order_obj);
        return "redirect:/order/";
    }

    @PreAuthorize("hasAnyAuthority('ADMIN','USER','CASHIER')")
    @GetMapping("/detail/{id}/upd")
    public String updateView(@PathVariable Long id, Model model) {
        Optional<Order> order = orderRepository.findById(id);
        ArrayList<Order> orderArrayList = new ArrayList<>();
        order.ifPresent(orderArrayList::add);
        Iterable<Product> products = productRepository.findAll();
        Iterable<User> users = userRepository.findAll();
        model.addAttribute("products", products);
        model.addAttribute("users", users);
        model.addAttribute("order",orderArrayList.get(0));
        return "order/update";
    }

    @PreAuthorize("hasAnyAuthority('ADMIN','USER','CASHIER')")
    @PostMapping("/detail/{id}/upd")
    public String updateStar(@PathVariable Long id, @ModelAttribute("order") @Valid Order order,
                             BindingResult bindingResult,
                             Model model) {
        if(!orderRepository.existsById(id)) {
            return "redirect:/order/";
        }
        if(bindingResult.hasErrors()) {
            Iterable<Product> products = productRepository.findAll();
            Iterable<User> users = userRepository.findAll();
            model.addAttribute("products", products);
            model.addAttribute("users", users);
            order.setUID(id);
            return "order/update";
        }
        Iterable<Product> products = productRepository.findAll();
        Iterable<User> users = userRepository.findAll();
        model.addAttribute("products", products);
        model.addAttribute("users", users);
        order.setUID(id);
        orderRepository.save(order);
        return "redirect:/order/detail/" + order.getUID();
    }
}

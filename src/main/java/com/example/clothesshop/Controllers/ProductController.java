package com.example.clothesshop.Controllers;

import com.example.clothesshop.Models.*;
import com.example.clothesshop.Repository.*;
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
@RequestMapping("/product")
public class ProductController {
    @Autowired
    ProductRepository productRepository;
    @Autowired
    TypeProductRepository typeProductRepository;
    @Autowired
    SizeProductRepository sizeProductRepository;
    @Autowired
    ColorProductRepository colorProductRepository;

    @GetMapping("/")
    public String index(Model model) {
        Iterable<Product> productIterable = productRepository.findAll();
        model.addAttribute("product_list",productIterable);
        return "/product/product";
    }

    @PreAuthorize("hasAnyAuthority('ADMIN','CASHIER')")
    @GetMapping("/add")
    public String AddView(Product product, Model model) {
        Iterable<TypeProduct> typeProducts = typeProductRepository.findAll();
        Iterable<SizeProduct> sizeProducts = sizeProductRepository.findAll();
        Iterable<ColorProduct> colorProducts = colorProductRepository.findAll();
        model.addAttribute("typeproducts", typeProducts);
        model.addAttribute("sizeproducts", sizeProducts);
        model.addAttribute("colorproducts", colorProducts);
        return "product/product-add";
    }

    @PreAuthorize("hasAnyAuthority('ADMIN','CASHIER')")
    @PostMapping("/add")
    public String AddStar(@ModelAttribute("product") @Valid Product product, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            Iterable<TypeProduct> typeProducts = typeProductRepository.findAll();
            Iterable<SizeProduct> sizeProducts = sizeProductRepository.findAll();
            Iterable<ColorProduct> colorProducts = colorProductRepository.findAll();
            model.addAttribute("typeproducts", typeProducts);
            model.addAttribute("sizeproducts", sizeProducts);
            model.addAttribute("colorproducts", colorProducts);
            return "product/product-add";
        }
        productRepository.save(product);
        return "redirect:/product/";
    }

    @GetMapping("/filter/")
    public String filter(
            @RequestParam(name = "name") String product_name,
            Model model) {
        List<Product> productList = productRepository.findByProductName(product_name);
        model.addAttribute("product_list", productList);
        return "product/product";
    }

    @GetMapping("/filtercontains/")
    public String filterContains(
            @RequestParam(name = "name") String product_name,
            Model model) {
        List<Product> productList = productRepository.findByProductNameContains(product_name);
        model.addAttribute("product_list", productList);
        return "product/product";
    }

    @GetMapping("/detail/{id}")
    public String detailStar(
            @PathVariable Long id,
            Model model) {
        Product product_obj = productRepository.findById(id).orElseThrow();
        model.addAttribute("one_product", product_obj);
        return "/product/info";
    }

    @PreAuthorize("hasAnyAuthority('ADMIN','CASHIER')")
    @GetMapping("/detail/{id}/del")
    public String delStars(@PathVariable Long id) {
        Product product_obj = productRepository.findById(id).orElseThrow();
        productRepository.delete(product_obj);
        return "redirect:/product/";
    }

    @PreAuthorize("hasAnyAuthority('ADMIN','CASHIER')")
    @GetMapping("/detail/{id}/upd")
    public String updateView(@PathVariable Long id, Model model) {
        Optional<Product> product = productRepository.findById(id);
        ArrayList<Product> productArrayList = new ArrayList<>();
        product.ifPresent(productArrayList::add);
        Iterable<TypeProduct> typeProducts = typeProductRepository.findAll();
        Iterable<SizeProduct> sizeProducts = sizeProductRepository.findAll();
        Iterable<ColorProduct> colorProducts = colorProductRepository.findAll();
        model.addAttribute("typeproducts", typeProducts);
        model.addAttribute("sizeproducts", sizeProducts);
        model.addAttribute("colorproducts", colorProducts);
        model.addAttribute("product",productArrayList.get(0));
        return "product/update";
    }

    @PreAuthorize("hasAnyAuthority('ADMIN','CASHIER')")
    @PostMapping("/detail/{id}/upd")
    public String updateStar(@PathVariable Long id, @ModelAttribute("product") @Valid Product product,
                             BindingResult bindingResult,
                             Model model) {
        if(!productRepository.existsById(id)) {
            return "redirect:/product/";
        }
        if(bindingResult.hasErrors()) {
            Iterable<TypeProduct> typeProducts = typeProductRepository.findAll();
            Iterable<SizeProduct> sizeProducts = sizeProductRepository.findAll();
            Iterable<ColorProduct> colorProducts = colorProductRepository.findAll();
            model.addAttribute("typeproducts", typeProducts);
            model.addAttribute("sizeproducts", sizeProducts);
            model.addAttribute("colorproducts", colorProducts);
            product.setUID(id);
            return "product/update";
        }
        Iterable<TypeProduct> typeProducts = typeProductRepository.findAll();
        Iterable<SizeProduct> sizeProducts = sizeProductRepository.findAll();
        Iterable<ColorProduct> colorProducts = colorProductRepository.findAll();
        model.addAttribute("typeproducts", typeProducts);
        model.addAttribute("sizeproducts", sizeProducts);
        model.addAttribute("colorproducts", colorProducts);
        product.setUID(id);
        productRepository.save(product);
        return "redirect:/product/detail/" + product.getUID();
    }
}

package com.example.clothesshop.Controllers;

import com.example.clothesshop.Models.*;
import com.example.clothesshop.Repository.DeliveryRepository;
import com.example.clothesshop.Repository.InventoryControlRepository;
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
@RequestMapping("/inventorycontrol")
@PreAuthorize("hasAnyAuthority('ADMIN','MERCHANDISER')")
public class InventoryControlController {
    @Autowired
    InventoryControlRepository inventoryControlRepository;
    @Autowired
    DeliveryRepository deliveryRepository;

    @GetMapping("/")
    public String index(Model model) {
        Iterable<InventoryControl> inventoryControlIterable = inventoryControlRepository.findAll();
        model.addAttribute("inventorycontrol_list",inventoryControlIterable);
        return "/inventorycontrol/inventorycontrol";
    }

    @GetMapping("/add")
    public String AddView(InventoryControl inventoryControl, Model model) {
        Iterable<Delivery> deliveries = deliveryRepository.findAll();
        model.addAttribute("deliveries", deliveries);
        return "inventorycontrol/inventorycontrol-add";
    }

    @PostMapping("/add")
    public String AddStar(@ModelAttribute("inventoryControl") @Valid InventoryControl inventoryControl, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            Iterable<Delivery> deliveries = deliveryRepository.findAll();
            model.addAttribute("deliveries", deliveries);
            return "inventorycontrol/inventorycontrol-add";
        }
        inventoryControlRepository.save(inventoryControl);
        return "redirect:/inventorycontrol/";
    }

    @GetMapping("/filter/")
    public String filter(
            @RequestParam(name = "name") String document_name,
            Model model) {
        List<InventoryControl> inventoryControlList = inventoryControlRepository.findByDocumentName(document_name);
        model.addAttribute("inventorycontrol_list", inventoryControlList);
        return "inventorycontrol/inventorycontrol";
    }

    @GetMapping("/filtercontains/")
    public String filterContains(
            @RequestParam(name = "name") String document_name,
            Model model) {
        List<InventoryControl> inventoryControlList = inventoryControlRepository.findByDocumentNameContains(document_name);
        model.addAttribute("inventorycontrol_list", inventoryControlList);
        return "inventorycontrol/inventorycontrol";
    }

    @GetMapping("/detail/{id}")
    public String detailStar(
            @PathVariable Long id,
            Model model) {
        InventoryControl inventorycontrol_obj = inventoryControlRepository.findById(id).orElseThrow();
        model.addAttribute("one_inventorycontrol", inventorycontrol_obj);
        return "/inventorycontrol/info";
    }

    @GetMapping("/detail/{id}/del")
    public String delStars(@PathVariable Long id) {
        InventoryControl inventorycontrol_obj = inventoryControlRepository.findById(id).orElseThrow();
        inventoryControlRepository.delete(inventorycontrol_obj);
        return "redirect:/inventorycontrol/";
    }

    @GetMapping("/detail/{id}/upd")
    public String updateView(@PathVariable Long id, Model model) {
        Optional<InventoryControl> inventoryControl = inventoryControlRepository.findById(id);
        ArrayList<InventoryControl> inventoryControlArrayList = new ArrayList<>();
        inventoryControl.ifPresent(inventoryControlArrayList::add);
        Iterable<Delivery> deliveries = deliveryRepository.findAll();
        model.addAttribute("deliveries", deliveries);
        model.addAttribute("inventorycontrol",inventoryControlArrayList.get(0));
        return "inventorycontrol/update";
    }

    @PostMapping("/detail/{id}/upd")
    public String updateStar(@PathVariable Long id, @ModelAttribute("inventoryControl") @Valid InventoryControl inventoryControl,
                             BindingResult bindingResult,
                             Model model) {
        if(!inventoryControlRepository.existsById(id)) {
            return "redirect:/inventorycontrol/";
        }
        if(bindingResult.hasErrors()) {
            Iterable<Delivery> deliveries = deliveryRepository.findAll();
            model.addAttribute("deliveries", deliveries);
            inventoryControl.setUID(id);
            return "inventorycontrol/update";
        }
        Iterable<Delivery> deliveries = deliveryRepository.findAll();
        model.addAttribute("deliveries", deliveries);
        inventoryControl.setUID(id);
        inventoryControlRepository.save(inventoryControl);
        return "redirect:/inventorycontrol/detail/" + inventoryControl.getUID();
    }

}

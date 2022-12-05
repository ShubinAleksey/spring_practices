package com.example.clothesshop.Controllers;

import com.example.clothesshop.Models.Subdivision;
import com.example.clothesshop.Models.SubdivisionReporting;
import com.example.clothesshop.Repository.SubdivisionReportingRepository;
import com.example.clothesshop.Repository.SubdivisionRepository;
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
@RequestMapping("/subdivisionreporting")
@PreAuthorize("hasAnyAuthority('ADMIN','PURCHASER')")
public class SubdivisionReportingController {
    @Autowired
    SubdivisionReportingRepository subdivisionReportingRepository;
    @Autowired
    SubdivisionRepository subdivisionRepository;

    @GetMapping("/")
    public String index(Model model) {
        Iterable<SubdivisionReporting> subdivisionReportingIterable = subdivisionReportingRepository.findAll();
        model.addAttribute("subdivisionreporting_list",subdivisionReportingIterable);
        return "/subdivisionreporting/subdivisionreporting";
    }

    @GetMapping("/add")
    public String AddView(SubdivisionReporting subdivisionReporting, Model model) {
        Iterable<Subdivision> subdivisions = subdivisionRepository.findAll();
        model.addAttribute("subdivisions", subdivisions);
        return "subdivisionreporting/subdivisionreporting-add";
    }

    @PostMapping("/add")
    public String AddStar(@ModelAttribute("subdivisionReporting") @Valid SubdivisionReporting subdivisionReporting, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            Iterable<Subdivision> subdivisions = subdivisionRepository.findAll();
            model.addAttribute("subdivisions", subdivisions);
            return "subdivisionreporting/subdivisionreporting-add";
        }
        subdivisionReportingRepository.save(subdivisionReporting);
        return "redirect:/subdivisionreporting/";
    }

    @GetMapping("/filter/")
    public String filter(
            @RequestParam(name = "name") String document_name,
            Model model) {
        List<SubdivisionReporting> subdivisionReportingList = subdivisionReportingRepository.findByDocumentName(document_name);
        model.addAttribute("subdivisionreporting_list", subdivisionReportingList);
        return "subdivisionreporting/subdivisionreporting";
    }

    @GetMapping("/filtercontains/")
    public String filterContains(
            @RequestParam(name = "name") String document_name,
            Model model) {
        List<SubdivisionReporting> subdivisionReportingList = subdivisionReportingRepository.findByDocumentNameContains(document_name);
        model.addAttribute("subdivisionreporting_list", subdivisionReportingList);
        return "subdivisionreporting/subdivisionreporting";
    }

    @GetMapping("/detail/{id}")
    public String detailStar(
            @PathVariable Long id,
            Model model) {
        SubdivisionReporting subdivisionReporting_obj = subdivisionReportingRepository.findById(id).orElseThrow();
        model.addAttribute("one_subdivisionreporting", subdivisionReporting_obj);
        return "/subdivisionreporting/info";
    }

    @GetMapping("/detail/{id}/del")
    public String delStars(@PathVariable Long id) {
        SubdivisionReporting subdivisionReporting_obj = subdivisionReportingRepository.findById(id).orElseThrow();
        subdivisionReportingRepository.delete(subdivisionReporting_obj);
        return "redirect:/subdivisionreporting/";
    }

    @GetMapping("/detail/{id}/upd")
    public String updateView(@PathVariable Long id, Model model) {
        Optional<SubdivisionReporting> subdivisionReporting = subdivisionReportingRepository.findById(id);
        ArrayList<SubdivisionReporting> subdivisionReportingArrayList = new ArrayList<>();
        subdivisionReporting.ifPresent(subdivisionReportingArrayList::add);
        Iterable<Subdivision> subdivisions = subdivisionRepository.findAll();
        model.addAttribute("subdivisions", subdivisions);
        model.addAttribute("subdivisionreporing",subdivisionReportingArrayList.get(0));
        return "subdivisionreporting/update";
    }

    @PostMapping("/detail/{id}/upd")
    public String updateStar(@PathVariable Long id, @ModelAttribute("subdivisionReporting") @Valid SubdivisionReporting subdivisionReporting,
                             BindingResult bindingResult,
                             Model model) {
        if(!subdivisionReportingRepository.existsById(id)) {
            return "redirect:/subdivisionreporting/";
        }
        if(bindingResult.hasErrors()) {
            Iterable<Subdivision> subdivisions = subdivisionRepository.findAll();
            model.addAttribute("subdivisions", subdivisions);
            subdivisionReporting.setUID(id);
            return "subdivisionreporting/update";
        }
        Iterable<Subdivision> subdivisions = subdivisionRepository.findAll();
        model.addAttribute("subdivisions", subdivisions);
        subdivisionReporting.setUID(id);
        subdivisionReportingRepository.save(subdivisionReporting);
        return "redirect:/subdivisionreporting/detail/" + subdivisionReporting.getUID();
    }
}

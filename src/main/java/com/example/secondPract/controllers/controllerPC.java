package com.example.secondPract.controllers;

import com.example.secondPract.models.ModelPC;
import com.example.secondPract.models.ModelUser;
import com.example.secondPract.repo.RepoPC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.util.List;

@Controller
public class controllerPC {


    @Autowired
    private RepoPC repoPC;


    @GetMapping("/page_addPC")
    public String blogPC(Model model){return "page_addPC";}

    @PostMapping("/page_addPC")
    public String pAddPC(@RequestParam String name,
                         @RequestParam String cpu,
                         @RequestParam String ram,
                         @RequestParam Boolean there_is,
                         @RequestParam BigDecimal price,
                         Model model
    ) {
        ModelPC modelPC = new ModelPC(name, cpu, ram, there_is, price);
        repoPC.save(modelPC);
        return "redirect:/pc/";
    }

    @GetMapping("/page_FilterPC")
    public String filterPC(Model model){return  "page_FilterPC";}

    @PostMapping("/page_FilterPC")
    public String pfilterPC(@RequestParam String name,
                            @RequestParam(value = "cbFilter", required = false, defaultValue = "1")
                            String cbFilter, Model model)
    {

        if(cbFilter.equals("1")) {
            List<ModelPC> modelPCS = repoPC.findByNameContains(name);
            model.addAttribute("pcs", modelPCS);
        }
        else {
            List<ModelPC> tmodelPCS = repoPC.findByName(name);
            model.addAttribute("pcs", tmodelPCS);
        }
        return "page_FilterPC";
    }

}

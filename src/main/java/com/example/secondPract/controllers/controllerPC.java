package com.example.secondPract.controllers;

import com.example.secondPract.models.ModelPC;
import com.example.secondPract.repo.RepoPC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
                         @RequestParam Boolean has,
                         @RequestParam int price,
                         Model model
    ) {
        ModelPC modelPC = new ModelPC(name, cpu, ram, has, price);
        repoPC.save(modelPC);
        return "redirect:/";
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

    @GetMapping("/PC/{id_pc}")
    public String blogDetailsPC(@PathVariable(value = "id_pc") long id_pc, Model model)
    {
        Optional<ModelPC> pcs = repoPC.findById(id_pc);
        if( pcs.isPresent() )
        {
            List<ModelPC> res = new ArrayList<>();
            res.add(pcs.get());
            model.addAttribute("pcs", res);
            return "page_detailsPC";
        }
        return "redirect:/";

//        if(!repoPC.existsById(id_pc))
//        {
//        }
    }

    @GetMapping("/PC/{id_pc}/edit")
    public String blogEditPC(@PathVariable("id_pc")long id_pc,
                           Model model)
    {
        if(!repoPC.existsById(id_pc)){
            return "redirect:/";
        }
        Optional<ModelPC> pcs = repoPC.findById(id_pc);
        ArrayList<ModelPC> res = new ArrayList<>();
        pcs.ifPresent(res::add);
        model.addAttribute("pcs",res);
        return "page_editPC";
    }

    @PostMapping("/PC/{id_pc}/edit")
    //public ModelPC(String name, String cpu, String ram, Boolean there_is, int price) {
    public String pageUpdatePC(@PathVariable("id_pc")long id_pc,
                                 @RequestParam String name,
                                 @RequestParam String cpu,
                                 @RequestParam String ram,
                                 @RequestParam(required=false) Boolean has,
                                 @RequestParam int price,
                                 Model model)
    {
        ModelPC pcs = repoPC.findById(id_pc).orElseThrow();
        pcs.setName(name);
        pcs.setCpu(cpu);
        pcs.setRam(ram);
        pcs.setHas(has == null ? Boolean.FALSE : has);
        pcs.setPrice(price);
        repoPC.save(pcs);
        return "redirect:/";
    }

    @PostMapping("/PC/{id_pc}/delete")
    public String deletePC(@PathVariable("id_pc") long id_pc, Model model){
        ModelPC pcs = repoPC.findById(id_pc).orElseThrow();
        repoPC.delete(pcs);
        return "redirect:/";
    }

}

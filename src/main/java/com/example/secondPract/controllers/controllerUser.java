package com.example.secondPract.controllers;

import com.example.secondPract.models.ModelPC;
import com.example.secondPract.models.ModelUser;
import com.example.secondPract.repo.RepoUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.*;

@Controller
public class controllerUser {
    @Autowired
    private RepoUser repoUser;

    @GetMapping("/page_addUser")
    public String addUser(Model model){return "page_addUser";}


    @PostMapping("/page_addUser")
    public String pAddUser(@RequestParam String fname,
                           @RequestParam String lname,
                           @RequestParam BigDecimal oklad,
                           @RequestParam String dateU,
                           @RequestParam String login,
                           ModelUser model
    ) {

        ModelUser modelUser = new ModelUser(fname, lname, login, oklad, dateU );
        repoUser.save(modelUser);
        return "redirect:/";
    }

    @GetMapping("/page_FilterUser")
    public String filterUser(Model model){return  "page_FilterUser";}

    @PostMapping("/page_FilterUser")
    public String pfilterUser(@RequestParam String fname,
                            @RequestParam(value = "cbFilter", required = false, defaultValue = "1")
                            String cbFilter, Model model)
    {
        if(cbFilter.equals("1")) {
            List<ModelUser> modelUserS = repoUser.findByFnameContains(fname);
            model.addAttribute("users", modelUserS);
        }
        else {
        List<ModelUser> tmodelUserS = repoUser.findByFname(fname);
        model.addAttribute("users", tmodelUserS);
        }
        return "page_FilterUser";
    }

    @GetMapping("/User/{id_user}")
    public String blogDetails(@PathVariable(value = "id_user") long id_user, Model model)
    {
        Optional<ModelUser> users = repoUser.findById(id_user);
        ArrayList<ModelUser> res = new ArrayList<>();
        users.ifPresent(res::add);
        model.addAttribute("users", res);
        if(!repoUser.existsById(id_user))
        {
            return "redirect:/";
        }
        return "page_detailsUser";
    }

}

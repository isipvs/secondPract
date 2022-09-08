package com.example.secondPract.controllers;

import com.example.secondPract.models.ModelUser;
import com.example.secondPract.repo.RepoUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
                           @RequestParam int oklad,
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
    public String blogDetailsUser(@PathVariable(value = "id_user") long id_user, Model model)
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
    
    @GetMapping("/User/{id_user}/edit")
    public String blogEditUser(@PathVariable("id_user")long id_user,
                             Model model)
    {
        if(!repoUser.existsById(id_user)){
            return "redirect:/";
        }
        Optional<ModelUser> users = repoUser.findById(id_user);
        ArrayList<ModelUser> res = new ArrayList<>();
        users.ifPresent(res::add);
        model.addAttribute("users",res);
        return "page_editUser";
    }

    @PostMapping("/User/{id_user}/edit")
    public String pageUpdateUser(@PathVariable("id_user")long id_user,
                               @RequestParam String fname,
                               @RequestParam String lname,
                               @RequestParam String login,
                               @RequestParam int oklad,
                               @RequestParam String dateU,
                               Model model)
    {
        //public ModelUser(String fname, String lname, String  login, int okald, String dateU) {
        ModelUser users = repoUser.findById(id_user).orElseThrow();
        users.setFname(fname);
        users.setLname(lname);
        users.setLogin(login);
        users.setDateU(dateU);
        users.setOkald(oklad);
        repoUser.save(users);
        return "redirect:/";
    }

    @PostMapping("/User/{id_user}/remove")
    public String deleteUser(@PathVariable("id_user") long id_user, Model model){
        ModelUser users = repoUser.findById(id_user).orElseThrow();
        repoUser.delete(users);
        return "redirect:/";
    }
}

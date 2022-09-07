package com.example.secondPract.controllers;

import com.example.secondPract.models.ModelPC;
import com.example.secondPract.models.ModelUser;
import com.example.secondPract.repo.RepoPC;
import com.example.secondPract.repo.RepoUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class controllerMain {

    @Autowired
    private RepoPC repoPC;

    @Autowired
    private RepoUser repoUser;

    @GetMapping("/")
    public String blogMain(Model model ){

        Iterable<ModelUser> userList = repoUser.findAll();
        model.addAttribute("users",userList);

        Iterable<ModelPC> pcsList = repoPC.findAll();
        model.addAttribute("pcs",pcsList);

        return "page_first";
    }
}

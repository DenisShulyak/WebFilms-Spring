package com.example.controllers;


import com.example.models.Film;
import com.example.models.User;
import com.example.repo.FilmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Controller
public class HomeController {


    @Autowired
    private FilmRepository filmRepository;

    @GetMapping("/")
    public String greeting(  Map<String, Object> model) {
        return "home";
    }

    @GetMapping("/main")
    public String main(Map<String, Object> model){
        /*Iterable<Message> messages = messageRepository.findAll();
        model.put("message", messages);*/
        Iterable<Film> allFilms = filmRepository.findAll();
        model.put("films", allFilms);
        return "main";
    }





    /*@PostMapping("/find")
    public String find_film(@RequestParam String name, Map<String, Object> model){

       Iterable<Film> allFilms = filmRepository.findAll();
        ArrayList<Film> films = new ArrayList<>();
        for (Film f:allFilms
             ) {
            if(f.getName().equalsIgnoreCase(name)){
                films.add(f);
            }
        }
        model.put("films", films);

        return "main";
    }*/
    @GetMapping("/find")
    public String get_find_film(@RequestParam String name, Map<String, Object> model){

        Iterable<Film> allFilms = filmRepository.findAll();
        ArrayList<Film> films = new ArrayList<>();
        for (Film f:allFilms
        ) {
            if(f.getName().equalsIgnoreCase(name)){
                films.add(f);
            }
        }
        model.put("films", films);

        return "main";
    }


}

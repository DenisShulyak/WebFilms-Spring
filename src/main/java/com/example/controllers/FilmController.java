package com.example.controllers;


import com.example.models.Comment;
import com.example.models.Film;
import com.example.models.User;
import com.example.repo.CommentRepository;
import com.example.repo.FilmRepository;
import com.example.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;


@Controller
public class FilmController {

    @Autowired
    private FilmRepository filmRepository;
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private UserRepo userRepo;

    @PostMapping("/film/comment/send")
    public String post_send_comment(@RequestParam String message, @RequestParam Long film_id , Map<String, Object> model){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();
        if(name.equalsIgnoreCase("guest")){
            model.put("error", "Guest cannot post a comment");
            return "redirect:/film/comment?id="+film_id.toString();
        }
        User user = userRepo.findByUsername(name);
        Comment comment = new Comment(message, film_id, user.getId(), name);
        commentRepository.save(comment);
        return "redirect:/film/comment?id="+film_id.toString();


    }
    @GetMapping("/film/comment")
    public String get_film_comments(@RequestParam Long id, Map<String, Object> model){

        Optional<Film> film = filmRepository.findById(id);
        Iterable<Comment> allComments = commentRepository.findAll();
        ArrayList<Comment> comments = new ArrayList<>();
        for (Comment c:allComments
             ) {
            if(c.getFilm_id() == film.get().getId()){
                comments.add(c);
            }
        }
        model.put("film", film);
        model.put("comments", comments);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();
        if(name.equalsIgnoreCase("guest")){
            model.put("error", "Guest cannot post a comment");
        }

        return "comment";
    }


}

package com.example.controllers;


import com.example.models.*;
import com.example.repo.CommentRepository;
import com.example.repo.FilmRepository;
import com.example.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


import java.util.*;

@Controller
public class AdminController {

    @Autowired
    private UserRepo userRepo;
    @Autowired
    private FilmRepository filmRepository;
    @Autowired
    private CommentRepository commentRepository;
    //@GetMapping("/")
    //public String greeting(  Map<String, Object> model) {
    //    return "home";
    //}

    public boolean isAuthAdmin(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();
        User user = userRepo.findByUsername(name);
        if(user.getRoles().toArray()[0].equals(Role.ADMIN)){
            return true;
        }
        return false;
    }
    @GetMapping("/admin")
    public String admin_panel(Map<String, Object> model){
        if(!isAuthAdmin()){
            return "main";
        }
        model.put("admin_text", "Admin panel");
        return "admin";
    }
    @GetMapping("/admin/users")
    public String admin_users(Map<String, Object> model){
        if(!isAuthAdmin()){
            return "main";
        }
        Iterable<User> users = userRepo.findAll();
        model.put("users", users);
        model.put("admin_text", "Admin users");
        return "admin-users";
    }
    @GetMapping("/admin/films")
    public String admin_films(Map<String, Object> model){
        if(!isAuthAdmin()){
            return "main";
        }
        Iterable<Film> films = filmRepository.findAll();
        model.put("films", films);
        model.put("admin_text", "Admin users");
        return "admin-films";
    }
    @PostMapping("/admin/user/{id}")
    public  String admin_user(@RequestParam long postId, Map<String, Object> model){
        if(!isAuthAdmin()){
            return "main";
        }
        //System.out.println(postId);
        if(!userRepo.existsById(postId)){
            return "redirect:/admin/users";
        }
        Optional<User> post = userRepo.findById((postId));
       /* ArrayList<User> users = new ArrayList<>();
        post.ifPresent(users::add);*/
        model.put("user", post);

        return "user-info";
    }
    @PostMapping("/admin/film/{id}")
    public  String admin_film(@PathVariable(value = "id")long postId, Map<String, Object> model){
        if(!isAuthAdmin()){
            return "main";
        }
        //System.out.println(postId);
        if(!userRepo.existsById(postId)){
            return "redirect:/admin/films";
        }
        Optional<Film> post = filmRepository.findById((postId));
        ArrayList<Film> films = new ArrayList<>();
        post.ifPresent(films::add);
        model.put("films", films);
        return "film-info";
    }
    @GetMapping("/admin/film-add")
    public String getAdminAddFilm(Map<String, Object> model)
    {
        if(!isAuthAdmin()){
            return "main";
        }
        return "film-add";
    }
    @PostMapping("/admin/film-add")
    public String postAdminAddFilm( @RequestParam String name, @RequestParam String url, Map<String, Object> model){
        if(!isAuthAdmin()){
            return "main";
        }
        Film film = new Film(name,url);
        filmRepository.save(film);
        return "redirect:/admin/films";
    }
    @GetMapping("/admin/user-add")
    public String getAdminAddUser(Map<String, Object> model)
    {
        if(!isAuthAdmin()){
            return "main";
        }
        return "user-add";
    }
    @PostMapping("/admin/user-add")
    public String postAdminAddUser( @RequestParam String username, @RequestParam String password, Map<String, Object> model){
        if(!isAuthAdmin()){
            return "main";
        }
        User user = new User(username, password);
        User u = userRepo.findByUsername(username);
        if (u != null){
            model.put("message", "User exists");
            return "user-add";
        }
        user.setActive(true);
        user.setRoles(Collections.singleton(Role.USER));// этот метод принимает коллекцию сэтов, поэтому мы возвращаем одно значение
        userRepo.save(user);
        return "redirect:/admin/users";
    }
    @PostMapping("/admin/film/delete")
    public String postAdminDeleteFilm(@RequestParam Long id, Map<String, Object> model){
        if(!isAuthAdmin()){
            return "main";
        }
      filmRepository.deleteById(id);
        return "redirect:/admin/films";
    }
    @PostMapping("/admin/user/delete")
    public String postAdminDeleteUser(@RequestParam Long id, Map<String, Object> model){
        if(!isAuthAdmin()){
            return "main";
        }
      userRepo.deleteById(id);
        return "redirect:/admin/users";
    }
    @PostMapping("/admin/comment/delete")
    public String postAdminDeleteComment(@RequestParam Long id, Map<String, Object> model){
        if(!isAuthAdmin()){
            return "main";
        }
      commentRepository.deleteById(id);
        return "redirect:/admin/films";
    }

    @GetMapping("/admin/film/edit")
    public String getAdminEditFilm(@RequestParam Long id, Map<String, Object> model){
        if(!isAuthAdmin()){
            return "main";
        }
        Optional<Film> get = filmRepository.findById((id));
        Film film = get.get();
        model.put("film", film);
        return "film-edit";
    }
    @PostMapping("/admin/film/edit")
    public String postAdminEditFilm(@RequestParam String name, @RequestParam String url,@RequestParam Long id, Map<String, Object> model){
        if(!isAuthAdmin()){
            return "main";
        }
      filmRepository.deleteById(id);
      filmRepository.save(new Film(id, name, url));
        return "redirect:/admin/films";
    }
    @PostMapping("/admin/film/comments")
    public String postAdminComments(@RequestParam Long id, Map<String, Object> model){
        if(!isAuthAdmin()){
            return "main";
        }
        Iterable<Comment> comments = commentRepository.findAll();
        ArrayList<Comment> commentArrayList = new ArrayList<>();
        for (Comment c:comments
             ) {
            if(c.getFilm_id()==id){
                commentArrayList.add(c);
            }
        }
        model.put("comments", commentArrayList);
        model.put("admin_text", "Admin comments");
        return "admin-comments";
    }


    /*@PostMapping("/main")
    public String addMessage(@AuthenticationPrincipal User user,
                             @RequestParam String text,
                             @RequestParam String tag,
                             Map<String, Object> model){
        Message message = new Message(text, tag, user);
        messageRepository.save(message);
        Iterable<Message> messages = messageRepository.findAll();
        model.put("message", messages);
        return "main";
    }

    @PostMapping("/filter")
    public String filter(@RequestParam String filter, Map<String, Object> model){
        Iterable<Message> messages;
        if (filter!=null && !filter.isEmpty()){
            messages = messageRepository.findByTag(filter);
        }else {
            messages = messageRepository.findAll();
        }
        model.put("message", messages);

        return "main";
    }*/
}

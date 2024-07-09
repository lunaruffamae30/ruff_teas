package com.lunacompany.ruff_teas.Controllers;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lunacompany.ruff_teas.Model.User;
import com.lunacompany.ruff_teas.NotFoundException.UserNotFoundException;
import com.lunacompany.ruff_teas.Repository.UserRepository;


@RestController
@RequestMapping("/api/v1/")
public class UserController {

    final UserRepository repo;
    
    public UserController (UserRepository repo){
        this.repo = repo;
    }
//http://127.0.0.1/User
    //getall User
    @GetMapping("/all")
    public List<User>getUser(){
        return repo.findAll();
    }
    //http://127.0.0.1:8080/User/1
    @GetMapping("/{id}")
    public User  getUserById(@PathVariable Long id){
        return repo.findById(id)
        .orElseThrow (()-> new UserNotFoundException(id));
    }  

    //http//:127.0.0.1:8080/User/new
    @PostMapping("/new")
    public String addUser(@RequestBody User newUser){
        repo.save(newUser);
        return "A new User is added!";

    }
   //UPDATE ENDPOINTS
   //http:127.0.0.1:8080/User/edit/1
   @PutMapping ("/edit/{id}")
   public User updateUser(@PathVariable Long id, 
   @RequestBody User newUser){
        return repo.findById(id)
        .map(User ->{
            User.setName(newUser.getName());
            User.setYear(newUser.getYear());
            User.setContact(newUser.getContact());
            User.setEmail(newUser.getEmail());
            User.setPassword(newUser.getPassword());
            return repo.save(User);
    }).orElseGet(()->{
        return repo.save(newUser);
    });
   }
   

   //DELETE ENDPOINTS
   //http://127.0.0.1:8080/User/delete/1
   @DeleteMapping ("/delete/{id}")
   public String deleteUser(@PathVariable Long id){
     repo.deleteById(id);
     return "A user is deleted!";
   }
}



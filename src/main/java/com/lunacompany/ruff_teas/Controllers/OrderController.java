package com.lunacompany.ruff_teas.Controllers;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lunacompany.ruff_teas.Model.Order;
import com.lunacompany.ruff_teas.NotFoundException.OrderNotFoundException;
import com.lunacompany.ruff_teas.Repository.OrderRepository;


@RestController
@RequestMapping("/api/v1/order")
public class OrderController {

    final OrderRepository repo;
    
    public OrderController (OrderRepository repo){
        this.repo = repo;
    }
//http://127.0.0.1/Order
    //getall Order
    @GetMapping("/all")
    public List<Order>getOrder(){
        return repo.findAll();
    }
    //http://127.0.0.1:8080/Order/1
    @GetMapping("/{id}")
    public Order  getOrderById(@PathVariable Long id){
        return repo.findById(id)
        .orElseThrow (()-> new OrderNotFoundException(id));
    }  

    //http//:127.0.0.1:8080/Order/new
    @PostMapping("/new")
    public String addOrder(@RequestBody Order newOrder){
        repo.save(newOrder);
        return "A new Order is added!";

    }

   //DELETE ENDPOINTS
   //http://127.0.0.1:8080/Order/delete/1
   @DeleteMapping ("delete/{id}")
   public String deleteOrder(@PathVariable Long id){
     repo.deleteById(id);
     return " Your Order is been deleted!";
   }
}



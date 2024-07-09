package com.lunacompany.ruff_teas.Controllers;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lunacompany.ruff_teas.Model.OrderItem;
import com.lunacompany.ruff_teas.NotFoundException.OrderItemNotFoundException;
import com.lunacompany.ruff_teas.Repository.OrderItemRepository;


@RestController
@RequestMapping("/api/v1/orderitem")
public class OrderItemController {

    final OrderItemRepository repo;
    
    public OrderItemController (OrderItemRepository repo){
        this.repo = repo;
    }
//http://127.0.0.1P:8080/OrderItem
    //getall OrderItem
    @GetMapping("/all")
    public List<OrderItem>getOrderItems(){
        return repo.findAll();
    }
    //http://127.0.0.1:8080/OrderItem/1
    @GetMapping("/{id}")
    public OrderItem  getOrderItemById(@PathVariable Long id){
        return repo.findById(id)
        .orElseThrow (()-> new OrderItemNotFoundException(id));
    }  

    //http//:127.0.0.1:8080/OrderItem/new
    @PostMapping("/new")
    public String addOrderItem(@RequestBody OrderItem newOrderItem){
        repo.save(newOrderItem);
        return "A new Order Item is added!";

    }

   //DELETE ENDPOINTS
   //http://127.0.0.1:8080/OrderItem/delete/1
   @DeleteMapping ("/delete/{id}")
   public String deleteOrderItem(@PathVariable Long id){
     repo.deleteById(id);
     return "An Order Item is deleted!";
   }
}


package it.exercise.spring_la_mia_pizzeria_security.controller.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import it.exercise.spring_la_mia_pizzeria_security.model.Pizza;
import it.exercise.spring_la_mia_pizzeria_security.service.PizzaService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/pizzas")
public class PizzaRestController {

    @Autowired
    private PizzaService pizzaService; 

    @GetMapping
    public List<Pizza> index(@RequestParam(name = "keyword", required = false) String name) {

        return pizzaService.findPizza(name);
    }
    
    @PostMapping
    public Pizza create(@Valid @RequestBody Pizza pizza) {

        return pizzaService.save(pizza);
    }

    @PutMapping("/{id}")
    public Pizza edit(@PathVariable Integer id, @RequestBody Pizza pizza) { 

        return pizzaService.save(pizza);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {

        pizzaService.delete(id);
    }

}

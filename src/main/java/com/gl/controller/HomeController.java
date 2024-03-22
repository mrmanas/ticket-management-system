package com.gl.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.gl.bean.Ticket;
import com.gl.bean.User;
import com.gl.dao.UserDAO;
import com.gl.service.TicketService;

@Controller
public class HomeController {
	@Autowired
    PasswordEncoder passwordencoder;
	  @Autowired
	    UserDAO udao;
    @Autowired
    TicketService s;
    
    @GetMapping("/home")
    public String getHome(Model model){ 
        model.addAttribute("ticketList", s.getAllTicket());
        return "home";
    }
                                
    @GetMapping("/showForm")
    public String showForm(Model model) {
        model.addAttribute("ticket", new Ticket());
        return "showForm";
    }

    @PostMapping("/addTicket")
    public String addTicket(@ModelAttribute("ticket") Ticket ticket) {
        s.addTicket(ticket);
        return "redirect:/home";
    }
    
    @GetMapping("/search")
    public String searchTicket(@RequestParam("searchTitle") String searchTitle, Model model) {
        model.addAttribute("ticketList", s.searchTicketByTitle(searchTitle));
        return "home";
    }
    
    @GetMapping("/del/{id}")
    public String delete(@PathVariable("id") long id) {
        s.deleteById(id);
        return "redirect:/home";
    }
   
    @GetMapping("/edit/{id}")
    public String editTicket(@PathVariable("id") long id , Model model) {
        Ticket ticket = s.getTicketById(id);
        model.addAttribute("ticket", ticket);
        return "showForm";
    }
    
    @PostMapping("/update/{id}")
    public String updateTicket(@PathVariable("id") long id, @ModelAttribute("ticket") Ticket ticket) {
        s.updateTicketById(ticket, id);
        return "redirect:/home";
    }
    
    @GetMapping("/viewTicket")
    public String viewTicket(@RequestParam("id") long id, Model model) {
        Ticket ticket = s.getTicketById(id);
        model.addAttribute("ticket", ticket);
        return "views"; // This will resolve to viewTicket.html Thymeleaf template
    }
    @GetMapping("/showUsePasswordPage")
    public String showUserForm(Model model) {
        model.addAttribute("user", new User());
        return "newuserform";
    }
    

    @PostMapping("/saveUser")
    public String addTeacher(@ModelAttribute("user") User user) {
    	System.out.println(user);
        user.setPassword(passwordencoder.encode(user.getPassword()));
        udao.save(user);
        return "redirect:/home";
    }
    
    @GetMapping("/showSortForm") 
    public String showSortForm() { 
        return "home"; 
    }

    @GetMapping("/home/sort")
    public String sortedTickets(@RequestParam("title") String title,
                                @RequestParam("order") String order,
                                Model model) {
        model.addAttribute("ticketList", s.getSortedList(title, order)); // Corrected closing parenthesis
        return "home";
    }


}

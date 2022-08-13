package com.bhargav.contacts.controller;

import com.bhargav.contacts.model.Contact;
import com.bhargav.contacts.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class ContactController {

    @Autowired
    private ContactService service;

    @GetMapping("/")
    public String viewHomepage(Model model) {
        model.addAttribute("contacts", service.listAllContacts());
        return "index";
    }

    @GetMapping("/addContact")
    public String addContact(Model model) {
        model.addAttribute("contact", new Contact());
        return "add_contact";
    }

    @PostMapping("/saveContact")
    public String saveContact(@ModelAttribute("contact") Contact contact) {
        service.addContact(contact);
        return "redirect:/";
    }

    @GetMapping("/deleteContact/{id}")
    public String deleteContact(@PathVariable("id") long id) {
        service.deleteContact(id);
        return "redirect:/";
    }

    @GetMapping("/showUpdateForm/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model) {
        Contact contact = service.findContactById(id);
        model.addAttribute("contact", contact);
        return "update_contact";
    }
}

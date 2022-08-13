package com.bhargav.contacts.service;

import com.bhargav.contacts.model.Contact;
import com.bhargav.contacts.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ContactService {
    @Autowired
    private ContactRepository repository;

    public List<Contact> listAllContacts() {
        return repository.findAll();
    }

    public void addContact(Contact contact) {
        repository.save(contact);
    }

    public Contact findContactById(long id) {
        Optional<Contact> optionalContact = repository.findById(id);
        if (optionalContact.isEmpty()) throw new IllegalStateException("no contact with id=%d found".formatted(id));
        return optionalContact.get();
    }

    public void deleteContact(long id) {
        findContactById(id);
        repository.deleteById(id);
    }
}

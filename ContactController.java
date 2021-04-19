  
package com.example.sharingapp;

public class ContactController {

    private Contact contact;


    public ContactController(Contact contact) {
        this.contact = contact;
    }

    public String getID() {
        return this.contact.getId();
    }

    public void setID() {
        contact.setId();
    }

    public void setUsername(String username) {
        contact.setUsername(username);
    }

    public String getUserName() {
        return contact.getUsername();
    }

    public void setEmail(String email) {
        contact.setEmail(email);
    }
    public String getEmail() {
        return contact.getEmail();
    }

    public Contact getContact() {
      return this.contact;
    }

    public void addObserver(Observer observer) {
      contacat.addObserver(observer);
    }

    public void removeObserver(Observer observer) {
      contact.addObserver(observer);
    }

}
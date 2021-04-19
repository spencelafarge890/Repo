package com.example.sharingapp;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.util.Observable;
import java.util.Observer;

public class EditContactActivity extends AppCompatActivity implements Observer {

    private ContactList contact_list = new ContactList();
    private ContactListController contactListController = new ContactListController(contactList);

    private Contact contact;
    private ContactController contactController;

    private EditText email;
    private EditText username;
    private Context context;
    private int pos;
    private boolean oncreateUpdate = true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_contact);

        context = getApplicationContext();
        contactListController.addObserver(this);
        contactListController.loadContacts(context);

        Intent intent = getIntent();
        int pos = intent.getIntExtra("position", 0);
        oncreateUpdate = false;


    }

    public void saveContact(View view) {

        String email_str = email.getText().toString();

        if (email_str.equals("")) {
            email.setError("Empty field!");
            return;
        }

        if (!email_str.contains("@")){
            email.setError("Must be an email address!");
            return;
        }

        String username_str = username.getText().toString();
        String id = contactController.getId(); 

        if (!contactListController.isUsernameAvailable(username_str) && !(contact.getUsername().equals(username_str))) {
            username.setError("Username already taken!");
            return;
        }

        String id_str = contactController.getId();
        Contact updated_contact = new Contact(username_str, email_str, id);

        boolean success = contactListController.editContact(contact, updatedContact, context);
        if(!success) {
          return ;
        }
      
        finish();
    }

    public void deleteContact(View view) {

      boolean success = contactListController.deleteContact(contact, context);
      if(!success) {
        return;
      }

        finish();
    }

    public void update(){

        if (on_create_update) {

            contact = contact_list_controller.getContact(pos);
            contact_controller = new ContactController(contact);

            username = (EditText) findViewById(R.id.username);
            email = (EditText) findViewById(R.id.email);

            
            username.setText(contact_controller.getUsername());
            email.setText(contact_controller.getEmail());
        }

      }

      protected void onDestroy() {
       super.onDestroy();
       contact_list_controller.removeObserver(this);
   }



}
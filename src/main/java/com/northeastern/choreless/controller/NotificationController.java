package com.northeastern.choreless.controller;

import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.Message;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.io.FileInputStream;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.messaging.Notification;
import com.northeastern.choreless.service.*;

@RestController
public class NotificationController {
	
	@Autowired
	NotificationService service;

    @PostConstruct
    public void initializeFirebase() throws Exception {
        FileInputStream serviceAccount = new FileInputStream("src/main/resources/serviceAccountKey.json");

        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .build();

        FirebaseApp.initializeApp(options);
    }

    @GetMapping("/sendNotification")
    public String sendNotification() {
        try {
        	service.sendNotification("Hi!", "How are you?", "all", null, null);
        	
//            Message message = Message.builder()
//                    .setNotification(com.google.firebase.messaging.Notification.builder()
//                            .setTitle("Hi!")
//                            .setBody("How are you?")
//                            .build())
//                    .putData("key1", "value1") // Additional data if needed
//                    .setTopic("all")
//                    .build();
//
//            System.out.println("It works!");
//            FirebaseMessaging.getInstance().send(message);
            return "Notification sent successfully!";
        } catch (Exception e) {
            e.printStackTrace();
            return "Error sending notification: " + e.getMessage();
        }
    }
}

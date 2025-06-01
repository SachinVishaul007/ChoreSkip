package com.northeastern.choreless.service;

import java.io.FileInputStream;

import org.springframework.stereotype.Service;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;

@Service
public class NotificationService {

    public void initializeFirebase() throws Exception {
        FileInputStream serviceAccount = new FileInputStream("src/main/resources/serviceAccountKey.json");

        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .build();

        FirebaseApp.initializeApp(options);
    }

    public void sendNotification(String title, String body, String topic, String dataKey, String dataValue) throws Exception {
        Message.Builder messageBuilder = Message.builder()
                .setNotification(Notification.builder()
                        .setTitle(title)
                        .setBody(body)
                        .build())
                .setTopic(topic);

        // Add additional data if provided
        if (dataKey != null && dataValue != null) {
            messageBuilder.putData(dataKey, dataValue);
        }

        Message message = messageBuilder.build();
        FirebaseMessaging.getInstance().send(message);
        System.out.println("Message sent from service!");
    }
}

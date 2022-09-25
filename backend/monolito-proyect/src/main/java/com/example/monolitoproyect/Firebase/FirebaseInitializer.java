package com.example.monolitoproyect.Firebase;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;

@Service
public class FirebaseInitializer {
    @PostConstruct
    private void initFirestore() throws IOException{

        FileInputStream serviceAccount =new FileInputStream("/home/victor/Escritorio/arq-monolito-proyect/backend/monolito-proyect/src/main/resources/hospital-firebase.json");

        FirebaseOptions options = new FirebaseOptions.Builder()
        .setCredentials(GoogleCredentials.fromStream(serviceAccount))
        .setDatabaseUrl("https://hospital.firebaseio.com/")
        .build();
        FirebaseApp.initializeApp(options);

        // if(FirebaseApp.getApps().isEmpty()){
        // }

    }
    public Firestore getFirestore(){
        return FirestoreClient.getFirestore();
    }
}

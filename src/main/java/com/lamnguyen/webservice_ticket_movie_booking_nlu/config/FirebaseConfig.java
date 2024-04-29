package com.lamnguyen.webservice_ticket_movie_booking_nlu.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.messaging.FirebaseMessaging;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ResourceLoader;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

@Configuration
public class FirebaseConfig {
    private final String FIREBASE_CONFIG_PATH = "classpath:firebase_config.json";
    private final String FIREBASE_DATABASE_URL = "https://ticket-movie-booking-nlu-2196d-default-rtdb.firebaseio.com";

    @Bean
    public FirebaseApp firebaseAppConfig() throws IOException {
        if (!FirebaseApp.getApps().isEmpty()) return FirebaseApp.getApps().get(0);
        File file = ResourceUtils.getFile(FIREBASE_CONFIG_PATH);
        FileInputStream serviceAccount = new FileInputStream(file);

        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .setDatabaseUrl(FIREBASE_DATABASE_URL)
                .build();

        return FirebaseApp.initializeApp(options);
    }


    @Bean
    public FirebaseMessaging firebaseMessaging(FirebaseApp firebaseApp) {
        return FirebaseMessaging.getInstance(firebaseApp);
    }

    @Bean
    public FirebaseDatabase firebaseDatabase(FirebaseApp firebaseApp) {
        return FirebaseDatabase.getInstance(firebaseApp);
    }
}

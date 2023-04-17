package io.github.haappi.productivityApp;

import com.google.auth.oauth2.ClientId;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import com.google.firebase.auth.FirebaseAuth;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class HelloController {
    @FXML
    private Button googleSignInButton;

    public void initialize() throws IOException {
        FileInputStream serviceAccount =
                new FileInputStream("java-final-project-b9dca-firebase-adminsdk-w3ec0-0f750f95f2.json");

        FirebaseOptions options = FirebaseOptions.builder()
  .setCredentials(GoogleCredentials.fromStream(serviceAccount))
  .setDatabaseUrl("https://java-final-project-b9dca-default-rtdb.firebaseio.com")
  .build();
        //https://stackoverflow.com/questions/40824660/firebase-user-authentication-for-java-application-not-android
//        FirebaseAuth.getInstance().signInWithCredential(credential);
FirebaseApp.initializeApp(options);
        // Set the on-click event handler for the Google Sign-In button
//        googleSignInButton.setOnAction(event -> {
//            // Authenticate the user using Google Sign-In
//            FirebaseAuth.getInstance()
//                .signInWithPopup(new GoogleAuthProvider())
//                .addOnCompleteListener(task -> {
//                    if (task.isSuccessful()) {
//                        // User is signed in
//                        FirebaseUser user = task.getResult().getUser();
//                        System.out.println("User signed in with Google: " + user.getDisplayName());
//                        // Redirect the user to the home screen
//                    } else {
//                        // Authentication failed
//                        System.out.println("Authentication failed: " + task.getException().getMessage());
//                    }
//                });
//        });
    }

    // See: https://developer.android.com/training/basics/intents/result
}

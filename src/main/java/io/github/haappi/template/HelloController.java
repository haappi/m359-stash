package io.github.haappi.template;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import com.google.firebase.auth.FirebaseAuth;

public class HelloController {
    @FXML
    private Button googleSignInButton;

    public void initialize() {
        // Set the on-click event handler for the Google Sign-In button
        googleSignInButton.setOnAction(event -> {
            // Authenticate the user using Google Sign-In
            FirebaseAuth.getInstance()
                .signInWithPopup(new GoogleAuthProvider())
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        // User is signed in
                        FirebaseUser user = task.getResult().getUser();
                        System.out.println("User signed in with Google: " + user.getDisplayName());
                        // Redirect the user to the home screen
                    } else {
                        // Authentication failed
                        System.out.println("Authentication failed: " + task.getException().getMessage());
                    }
                });
        });
    }
}

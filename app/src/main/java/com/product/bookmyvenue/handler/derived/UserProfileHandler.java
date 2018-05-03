package com.product.bookmyvenue.handler.derived;

import android.app.Activity;
import android.support.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.product.bookmyvenue.handler.base.BaseHandler;

/**
 * Handler class to handle the profile add, update, delete and query data.
 */
public class UserProfileHandler extends BaseHandler {

    private static final String TAG = "UserProfileHandler";

    /**
     * Method to fetch user profile for a given mobile number.
     *
     * @param mobile String.
     */
    public void fetchUser(Activity activity, String mobile) {

        FirebaseFirestore db = FirebaseFirestore.getInstance();

        db.collection("activeUsers")
                .document(mobile)
                .get()
                .addOnCompleteListener(activity, new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {

                        DocumentSnapshot documentSnapshot = task.getResult();

                        if (documentSnapshot.exists()) {

                        }

                    }
                });

    }

}

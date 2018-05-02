package com.product.bookmyvenue;

import android.app.Activity;
import android.support.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

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

//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//
//                if (task.getResult().exists()) {
//
//                    Map<String, Object> map = task.getResult().getData();
//
//                    Toast.makeText(context, "Data fetched", Toast.LENGTH_SHORT).show();
//                } else {
//
//                    Toast.makeText(context, "No data found", Toast.LENGTH_SHORT).show();
//                }
//            }
//        }, 3000);


    }

}

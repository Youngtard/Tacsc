package com.youngtard.tacsc;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.ErrorCodes;
import com.firebase.ui.auth.IdpResponse;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.youngtard.tacsc.model.EndUser;
import com.youngtard.tacsc.model.Technician;

import java.util.Arrays;

public class SignUpEndUser extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    Spinner spinner;
    Button verifyPhoneNumber;
    EditText enteredFullName;
    ArrayAdapter<CharSequence> adapter;

    String selectedProfile;

    FirebaseAuth auth;
    DatabaseReference databaseReference;

    String fullName;

    private static final int REQUEST_CODE_SIGN_IN = 123;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        auth = FirebaseAuth.getInstance();
        //Check if user already signed in
        if (auth.getCurrentUser() != null) {
            Intent intent = new Intent(this, TimelineActivity.class);
            startActivity(intent);
            //already signed in

        } else {
            //not signed in
            //execute all the code below?
            setContentView(R.layout.activity_sign_up_end_user);


            //Initialize widgets
            enteredFullName = findViewById(R.id.et_full_name);
            spinner = findViewById(R.id.spn_profiles);
            verifyPhoneNumber = findViewById(R.id.btn_verify_phone_number);


            //Set up spinner
            adapter = ArrayAdapter.createFromResource(this, R.array.profiles, android.R.layout.simple_spinner_item);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner.setAdapter(adapter);
            spinner.setOnItemSelectedListener(this);
            getSupportActionBar().setTitle("Create account");


            verifyPhoneNumber.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    boolean validInput = validateInput(enteredFullName.getText().toString(), "c");


//                String tt = enteredFullName.getText().toString() + " " + selectedProfile;
//                Toast.makeText(SignUpEndUser.this, tt, Toast.LENGTH_SHORT).show();
                    if (validInput) {
                        AuthUI authUI = AuthUI.getInstance();
                        AuthUI.SignInIntentBuilder authUISignInIntentBuilder = authUI.createSignInIntentBuilder();
                        AuthUI.IdpConfig phoneAuthProvider = new AuthUI.IdpConfig.PhoneBuilder()
                                .setDefaultNumber("+234")
                                .build();
                        startActivityForResult(authUISignInIntentBuilder.setAvailableProviders(Arrays.asList(phoneAuthProvider)).build(), REQUEST_CODE_SIGN_IN);

                    }


                }
            });


        }


    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        //There can be many startActivityForResult? that's why there needs to be a request code for a particular request for result?
        if (requestCode == REQUEST_CODE_SIGN_IN) {
            IdpResponse response = IdpResponse.fromResultIntent(data);
            if (resultCode == RESULT_OK) {
                FirebaseUser user = auth.getCurrentUser();

                addUserToDatabase(selectedProfile, user);

                Intent intent = new Intent(this, TimelineActivity.class);
                startActivity(intent);


            } else {
                //Sign in failed
                if (response == null) {
//                    User pressed back button
                    return;
                }

                if (response.getError().getErrorCode() == ErrorCodes.NO_NETWORK) {
                    Toast.makeText(this, "Internet Connnection Error", Toast.LENGTH_SHORT).show();
                    return;
                }

                Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
            }


        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        selectedProfile = parent.getItemAtPosition(position).toString();

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    private boolean validateInput(String enteredFullName, String profile) {

        if (enteredFullName.isEmpty()) {
            Toast.makeText(this, "Name field should be filled", Toast.LENGTH_SHORT).show();
            return false;
        }

        fullName = enteredFullName;
        return true;

    }

    private void addUserToDatabase(String selectedProfile, FirebaseUser user) {
        databaseReference = FirebaseDatabase.getInstance().getReference();

        if (selectedProfile.equals("Technician")) {
            Technician technician = new Technician(fullName, user.getPhoneNumber());
            databaseReference.child("technicians").child(user.getUid()).setValue(technician);
        } else {
            EndUser endUser = new EndUser(fullName, user.getPhoneNumber());
            databaseReference.child("endusers").child(user.getUid()).setValue(endUser);
        }
    }


}

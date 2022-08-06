package edu.ewubd.mybloodbank;

import androidx.appcompat.app.AppCompatActivity;
import android.database.sqlite.SQLiteDatabase;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


public class SignUpActivity extends AppCompatActivity {
    private EditText useridTF,userNameTF,addressTF, phoneTF,divitionTF,bloodGroupTF, lastDonationTF, emailTF, password1TF, password2TF;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        useridTF = findViewById(R.id.etUserId);
        phoneTF = findViewById(R.id.etPhone);
        divitionTF = findViewById(R.id.etArea);
        bloodGroupTF = findViewById(R.id.etBloodGroup);
        emailTF = findViewById(R.id.etEmail);
        lastDonationTF = findViewById(R.id.etLastDay);
        userNameTF = findViewById(R.id.etName);
        addressTF = findViewById(R.id.etAddress);
        password1TF = findViewById(R.id.etPassword1);
        password2TF = findViewById(R.id.etPassword2);

        findViewById(R.id.buttonSignup).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setProfileData();
            }
        });

    }

    private void showDialog(String message, String title, String btnLabel, boolean isSuccess) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        //Uncomment the below code to Set the message and title from the strings.xml file
        builder.setMessage(message).setTitle(title);

        //Setting message manually and performing action on button click
        builder.setMessage(message)
                .setCancelable(false)
                .setNegativeButton(btnLabel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        if (isSuccess) {
                            dialog.cancel();
                        } else {
                            //dialog.cancel();
                            Intent i = new Intent(SignUpActivity.this,LoginActivity.class);
                            startActivity(i);
                            finish();
                        }
                    }
                });
        //Creating dialog box
        AlertDialog alert = builder.create();
        //Setting the title manually
//        alert.setTitle("Error Dialog");
        alert.show();
    }

    private void setProfileData(){
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        //String datePattern = "[0-9._-]+@[a-z]+\\.+[a-z]+";
        String userID = useridTF.getText().toString();
        String email = emailTF.getText().toString();
        String name = userNameTF.getText().toString();
        String address = addressTF.getText().toString();
        String phone = phoneTF.getText().toString();
        String divition = divitionTF.getText().toString();
        String bloodGroup = bloodGroupTF.getText().toString();
        String lastDonation = lastDonationTF.getText().toString();



        String password1 = password1TF.getText().toString();
        String password2 = password2TF.getText().toString();
        String errormsg = "";

        Database g = new Database(this);
        SQLiteDatabase db = g.getReadableDatabase();
        Boolean user = g.check_user(userID);
        Boolean c_email = g.check_email(email);

        if(userID.isEmpty()){
            //errormsg += "Please enter the user id";
            Toast.makeText(SignUpActivity.this,"Enter the user id",Toast.LENGTH_SHORT).show();
        }
        if(userID.isEmpty()){
            //errormsg += "Please enter the user id";
            Toast.makeText(SignUpActivity.this,"Enter the user id",Toast.LENGTH_SHORT).show();
        }
        else if(userID.length() < 4){
            Toast.makeText(SignUpActivity.this,"User id is too short",Toast.LENGTH_SHORT).show();
        }
        else if(user==true){
            Toast.makeText(SignUpActivity.this,"user already exists", Toast.LENGTH_SHORT).show();
        }

        else if(email.isEmpty() || !email.matches(emailPattern)){
            //errormsg += "Please enter the email address";
            Toast.makeText(SignUpActivity.this,"Enter the valid Email Address",Toast.LENGTH_SHORT).show();
        }
        else if(c_email==true){
            Toast.makeText(SignUpActivity.this,"Email Adress already exists", Toast.LENGTH_SHORT).show();
        }
        else if(phone.isEmpty() || phone.length() < 4){
            //errormsg += "Please enter the email address";
            Toast.makeText(SignUpActivity.this,"Enter the valid Email Address",Toast.LENGTH_SHORT).show();
        }
        else if(divition.isEmpty()){
            //errormsg += "Please enter the password";
            Toast.makeText(SignUpActivity.this,"Enter the divition",Toast.LENGTH_SHORT).show();
        }
        else if(bloodGroup.isEmpty()|| bloodGroup.length() > 3|| bloodGroup.length() <2){
            //errormsg += "Please enter the password";
            Toast.makeText(SignUpActivity.this,"Enter the blood group(example: A+/B-/AB+)",Toast.LENGTH_SHORT).show();
        }
        else if(name.isEmpty()|| name.length() <2){
            //errormsg += "Please enter the password";
            Toast.makeText(SignUpActivity.this,"Enter the proper name",Toast.LENGTH_SHORT).show();
        }
        else if(address.isEmpty()||address.length() <2){
            //errormsg += "Please enter the password";
            Toast.makeText(SignUpActivity.this,"Enter valid Address",Toast.LENGTH_SHORT).show();
        }
        else if(lastDonation.isEmpty()|| lastDonation.length() > 10|| lastDonation.length() <9){
            //errormsg += "Please enter the password";
            Toast.makeText(SignUpActivity.this,"Enter the last blood donation date in yyyy/mm/dd format",Toast.LENGTH_SHORT).show();
        }

        else if(password1.isEmpty()){
            //errormsg += "Please enter the password";
            Toast.makeText(SignUpActivity.this,"Enter the Password",Toast.LENGTH_SHORT).show();
        }
        else if(password1.length() <6){
            //errormsg += "Please enter the password";
            Toast.makeText(SignUpActivity.this,"Password is too short",Toast.LENGTH_SHORT).show();
        }
        else if(password2.isEmpty()){
            //errormsg += "Please enter the confirm password";
            Toast.makeText(SignUpActivity.this,"Enter the Confirm Password",Toast.LENGTH_SHORT).show();
        }

        else if(!password1.equals(password2)){
            //errormsg += "Please enter the confirm password";
            Toast.makeText(SignUpActivity.this,"Password doesn't matched",Toast.LENGTH_SHORT).show();
        }
        else {
            Boolean flag = g.user_insert(userID,name,email,phone,divition,address,bloodGroup,lastDonation,password1);
            if(flag==true){
                showDialog("Account Created Successfully","Info","Okay",false);
            }else{
                Toast.makeText(SignUpActivity.this,"SignUp Unsuccessful",Toast.LENGTH_SHORT).show();
            }

        }

    }
}
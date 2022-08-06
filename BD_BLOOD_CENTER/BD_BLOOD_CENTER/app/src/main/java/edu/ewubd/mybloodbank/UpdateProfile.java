package edu.ewubd.mybloodbank;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class UpdateProfile extends AppCompatActivity {
    private EditText useridTF,userNameTF,addressTF, phoneTF,divitionTF,bloodGroupTF, lastDonationTF, emailTF, password1TF, password2TF;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_profile);
        bloodGroupTF = findViewById(R.id.etUBloodGroup);
        phoneTF = findViewById(R.id.etUPhone);
        divitionTF = findViewById(R.id.etUArea);
        emailTF = findViewById(R.id.etUEmail);
        lastDonationTF = findViewById(R.id.etULastDay);
        userNameTF = findViewById(R.id.etUName);
        addressTF = findViewById(R.id.etUAddress);
        password1TF = findViewById(R.id.etUPassword1);
        password2TF = findViewById(R.id.etUPassword2);
        String userID;
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                userID= null;
            } else {
                userID= extras.getString("userid");
            }
        } else {
            userID= (String) savedInstanceState.getSerializable("userid");
        }




        Database db = new Database(this);
        TextView textView = findViewById(R.id.searchResult1);
        StringBuilder sb = new StringBuilder();
        Cursor cursor = db.userInfo(userID);
        while (cursor.moveToNext()){
            userNameTF.setText(cursor.getString(2));
            emailTF.setText(cursor.getString(3));
            phoneTF.setText(cursor.getString(4));
            divitionTF.setText(cursor.getString(5));
            addressTF.setText(cursor.getString(6));
            bloodGroupTF.setText(cursor.getString(7));
            lastDonationTF.setText(cursor.getString(8));
            password1TF.setText(cursor.getString(9));
            password2TF.setText(cursor.getString(9));
        }





        findViewById(R.id.buttonUpdateUser).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setProfileData(userID);
            }
        });

        findViewById(R.id.buttonDeleteUser).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                deleteProfileData(userID);
                showDeleteDialog("Account Has been deleted","Info","Okay",false);

            }
        });

    }

    private void showDeleteDialog(String message, String title, String btnLabel, boolean isSuccess) {
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
                            Intent i = new Intent(UpdateProfile.this,MainActivity.class);
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
                            Intent i = new Intent(UpdateProfile.this,MainActivity.class);
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


    private void deleteProfileData(String user) {





        Database g = new Database(this);
        SQLiteDatabase db = g.getReadableDatabase();
        g.delete_profile(user);
    }

    private void setProfileData(String user){
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        //String datePattern = "[0-9._-]+@[a-z]+\\.+[a-z]+";
        String userID = user;
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
        //Boolean user = g.check_user(userID);
        Boolean c_email = g.check_email(email);


        if(email.isEmpty() || !email.matches(emailPattern)){
            //errormsg += "Please enter the email address";
            Toast.makeText(UpdateProfile.this,"Enter the valid Email Address",Toast.LENGTH_SHORT).show();
        }
        else if(c_email==true){
            Toast.makeText(UpdateProfile.this,"Email Adress already exists", Toast.LENGTH_SHORT).show();
        }
        else if(phone.isEmpty() || phone.length() < 4){
            //errormsg += "Please enter the email address";
            Toast.makeText(UpdateProfile.this,"Enter the valid Email Address",Toast.LENGTH_SHORT).show();
        }
        else if(bloodGroup.isEmpty() || bloodGroup.length() > 3){
            //errormsg += "Please enter the email address";
            Toast.makeText(UpdateProfile.this,"Enter the valid Blood Group",Toast.LENGTH_SHORT).show();
        }
        else if(divition.isEmpty()){
            //errormsg += "Please enter the password";
            Toast.makeText(UpdateProfile.this,"Enter the divition",Toast.LENGTH_SHORT).show();
        }

        else if(name.isEmpty()|| name.length() <2){
            //errormsg += "Please enter the password";
            Toast.makeText(UpdateProfile.this,"Enter the proper name",Toast.LENGTH_SHORT).show();
        }
        else if(address.isEmpty()||address.length() <2){
            //errormsg += "Please enter the password";
            Toast.makeText(UpdateProfile.this,"Enter valid Address",Toast.LENGTH_SHORT).show();
        }
        else if(lastDonation.isEmpty()|| lastDonation.length() > 10|| lastDonation.length() <9){
            //errormsg += "Please enter the password";
            Toast.makeText(UpdateProfile.this,"Enter the last blood donation date in yyyy/mm/dd format",Toast.LENGTH_SHORT).show();
        }

        else if(password1.isEmpty()){
            //errormsg += "Please enter the password";
            Toast.makeText(UpdateProfile.this,"Enter the Password",Toast.LENGTH_SHORT).show();
        }
        else if(password1.length() <6){
            //errormsg += "Please enter the password";
            Toast.makeText(UpdateProfile.this,"Password is too short",Toast.LENGTH_SHORT).show();
        }
        else if(password2.isEmpty()){
            //errormsg += "Please enter the confirm password";
            Toast.makeText(UpdateProfile.this,"Enter the Confirm Password",Toast.LENGTH_SHORT).show();
        }

        else if(!password1.equals(password2)){
            //errormsg += "Please enter the confirm password";
            Toast.makeText(UpdateProfile.this,"Password doesn't matched",Toast.LENGTH_SHORT).show();
        }
        else {

           boolean flag = g.update_info(userID,name,email,phone,divition,address,bloodGroup,lastDonation,password1);
            if(flag==true){
                showDialog("Account Updated Successfully","Info","Okay",false);
            }else{
                Toast.makeText(UpdateProfile.this,"SignUp Unsuccessful",Toast.LENGTH_SHORT).show();
            }




        }

    }
}
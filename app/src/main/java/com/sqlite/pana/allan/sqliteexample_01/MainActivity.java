package com.sqlite.pana.allan.sqliteexample_01;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    EditText et_User,et_Password,et_name;
    MyDataBaseAdapter dbHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHelper = new MyDataBaseAdapter(this);
        et_User = (EditText) findViewById(R.id.et_username);
        et_Password = (EditText) findViewById(R.id.et_password);
        et_name = (EditText) findViewById(R.id.et_getDetail);
    }

    /**
     * Insert into database
     * @param view
     */
   public void addUser(View view){
       String user = String.valueOf(et_User.getText());
       String pass = String.valueOf(et_Password.getText());
       long id = dbHelper.insertData(user,pass);
       if(id<0){
           Toast.makeText(this,"Unsuccessful.........",Toast.LENGTH_LONG).show();
       }else {
           Toast.makeText(this, "Data successfully inserted..........", Toast.LENGTH_LONG).show();
       }
   }

    public void viewData(View view){
        //String allData = dbHelper.viewallData();
        Toast.makeText(this,"look at me",Toast.LENGTH_LONG).show();
        startActivity(new Intent(this, AllData.class));
    }

    public void getData(View view){
        String data = dbHelper.getUserDetails(et_name.getText().toString());
        Toast.makeText(this,data,Toast.LENGTH_LONG).show();
    }

    public void updateData(View view){
        long update = dbHelper.updateData(et_User.getText()+"",et_Password.getText()+"");

        if(update == 0){
            Toast.makeText(this,"Insertion Failed" ,Toast.LENGTH_LONG).show();
        }else {
            Toast.makeText(this,"Inserted Successfully, Row affected = "+ update, Toast.LENGTH_LONG).show();
        }
    }

    public void deleteData(View view){
        long delete = dbHelper.deleteData(et_name.getText()+"");
        if(delete == 0){
            Toast.makeText(this,"Deletion Failed" ,Toast.LENGTH_LONG).show();
        }else {
            Toast.makeText(this,"Deletion Successfully, Row affected = "+ delete, Toast.LENGTH_LONG).show();
        }
    }

}

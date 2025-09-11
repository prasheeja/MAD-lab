package com.example.optionmenu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item){
        int id=item.getItemId();
        if(id==R.id.action_activity_one){
         Intent intent=new Intent(MainActivity.this,activityOne.class);
         startActivity(intent);
            Toast.makeText(this, "Navigating to Activity One", Toast.LENGTH_SHORT).show();
           return true;
        }
        else if(id==R.id.action_activity_two){
            Intent intent=new Intent(MainActivity.this, activityTwo.class);
           startActivity(intent);
           Toast.makeText(this,"Navigating to Activity Two",Toast.LENGTH_SHORT).show();
           return true;
        }
        return super.onOptionsItemSelected(item);
    }
}

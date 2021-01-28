package com.example.sharedprefrences;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences sharedPreferences = this.getSharedPreferences("com.example.sharedprefrences", Context.MODE_PRIVATE);

        //Put info

        //String
        sharedPreferences.edit().putString("username","Niks").apply();

        //ArrayList
        ArrayList<String> friends=new ArrayList<>();
        friends.add("Chandler");
        friends.add("Ross");
        friends.add("Joey");
        friends.add("Monica");
        friends.add("Rachel");
        friends.add("Phoebe");

        try {
            String serialize = ObjectSerializer.serialize(friends);
            sharedPreferences.edit().putString("list",serialize).apply();
            Log.i("Friends",""+serialize);
        } catch (Exception e) {
            e.printStackTrace();
        }

        //Get Info

        //String
        String username=sharedPreferences.getString("username","Default string");
        Log.i("This is a username",""+username);

        //ArrayList
        ArrayList<String> newFriends=new ArrayList<>();
        try {
            newFriends= (ArrayList<String>) ObjectSerializer.deserialize(sharedPreferences.getString("list",ObjectSerializer.serialize(new ArrayList<String>())));
            Log.i("New Friends",""+newFriends.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
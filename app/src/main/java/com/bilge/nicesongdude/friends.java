package com.bilge.nicesongdude;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Map;

public class friends extends AppCompatActivity {
    ArrayList<String> name;
    ArrayList<String> number;
    ArrayList<String> city;
    ArrayList<String> music;

    private FirebaseFirestore db;
    private FirebaseAuth auth;
    private FirebaseUser user;

    String cityWhat;
    String userMail;
    String musicWhat;

    String mirror;


    adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friends);

        name = new ArrayList<>();
        number = new ArrayList<>();
        city = new ArrayList<>();
        music = new ArrayList<>();

        auth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();
        user = auth.getCurrentUser();
        userMail = user.getEmail();

        getData();




        RecyclerView rv = findViewById(R.id.friendrec);

        rv.setLayoutManager(new LinearLayoutManager(this));

        adapter = new adapter(name,number,music,city);

        rv.setAdapter(adapter);
    }
    public void getData(){


        CollectionReference dataReferance = db.collection("user");
        dataReferance.whereEqualTo("mail",userMail).addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                if(error != null){
                    Toast.makeText(friends.this,error.getLocalizedMessage().toString(),Toast.LENGTH_SHORT).show();
                }
                if(value != null){
                    for(DocumentSnapshot snap : value.getDocuments()){
                        Map<String,Object> datalar = snap.getData();
                        cityWhat = (String) datalar.get("City");
                        musicWhat = (String) datalar.get("Music");
                        mirror = (String) datalar.get("Number");
                        search();
                    }
                }
            }
        });

    }
    public void search(){


        CollectionReference dataReferance = db.collection("user");
        dataReferance.whereEqualTo("City",cityWhat).whereEqualTo("Music",musicWhat).addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                if(error != null){
                    Toast.makeText(friends.this,error.getLocalizedMessage().toString(),Toast.LENGTH_SHORT).show();
                }
                if(value != null){
                    for(DocumentSnapshot snap : value.getDocuments()){
                        Map<String,Object> s = snap.getData();
                        String musicWho = (String) s.get("Music");
                        String namewho = (String) s.get("Name");
                        String numberwho = (String) s.get("Number");
                        String citywho = (String) s.get("City");

                        if(mirror.matches(numberwho)){

                        }else{
                            name.add(namewho);
                            number.add(numberwho);
                            music.add(musicWho);
                            city.add(citywho);
                            adapter.notifyDataSetChanged();
                            System.out.println(namewho);
                        }



                    }
                }
            }
        });

    }


}
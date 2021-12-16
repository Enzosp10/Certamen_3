package com.example.certamen_3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private List<Usuario> ListUsuario = new ArrayList<Usuario>();
    ArrayAdapter<Usuario> arrayAdapterUsuario;

    FirebaseDatabase firebaseDataBase;
    DatabaseReference databaseReference;


    private Button btn_ingresar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_ingresar = findViewById(R.id.btn_ingresar);
        EditText etUsuario= findViewById(R.id.etUsuario);
        EditText etPassword= findViewById(R.id.etPassword);

        inicializarFirebase();



        btn_ingresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                databaseReference.child("usuario").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        ListUsuario.clear();
                        for (DataSnapshot objS : snapshot.getChildren()){
                            Usuario usuario1=objS.getValue(Usuario.class);
                            ListUsuario.add(usuario1);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {


                    }
                });
                if (ListUsuario.isEmpty()){

                    Snackbar mySnackbar = Snackbar.make(view, "Ingrese Usuario y Contraseña",Snackbar.LENGTH_LONG);
                    mySnackbar.show();

                }
                else{
                    if (ListUsuario.get(0).getNombre().equals(etUsuario.getText().toString()) && ListUsuario.get(0).getContraseña().equals(etPassword.getText().toString())){

                        Intent btn_ingresar = new Intent(MainActivity.this,MainActivity2.class);
                        startActivity(btn_ingresar);
                        // pasa login
                    }
                    else {
                        Snackbar mySnackbar = Snackbar.make(view, "Usuario o contraseña incorrecta",Snackbar.LENGTH_LONG);
                        mySnackbar.show();
                    }

                }
            }
        });
    }

    private void inicializarFirebase() {

        FirebaseApp.initializeApp(this);
        firebaseDataBase=FirebaseDatabase.getInstance();
        databaseReference= firebaseDataBase.getReference();
    }




    }

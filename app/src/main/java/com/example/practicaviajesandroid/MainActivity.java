package com.example.practicaviajesandroid;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.practicaviajesandroid.fragments.Ajustes;
import com.example.practicaviajesandroid.fragments.ListadoLugares;
import com.example.practicaviajesandroid.fragments.ListadoViajeros;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        enlazarComponentes();
        remplazarFragmentos(new ListadoLugares());
        menuListeners();
    }

    private void enlazarComponentes(){
        bottomNavigationView = findViewById(R.id.bottomNavigationView);
    }

    private void remplazarFragmentos(Fragment f) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.fragLayout, f);
        ft.commit();
    }

    private void menuListeners(){
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.menu_listado_lugares:
                        remplazarFragmentos(new ListadoLugares());
                        break;
                    case R.id.menu_listado_viajeros:
                        remplazarFragmentos(new ListadoViajeros());
                        break;
                    case R.id.menu_ajustes:
                        remplazarFragmentos(new Ajustes());
                }
                return true;
            }
        });
    }


}
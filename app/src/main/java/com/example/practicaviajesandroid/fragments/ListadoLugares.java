package com.example.practicaviajesandroid.fragments;

import android.app.Dialog;
import android.os.Bundle;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.practicaviajesandroid.R;
import com.example.practicaviajesandroid.adapters.LugaresAdapter;
import com.example.practicaviajesandroid.data.DataRoomDB;
import com.example.practicaviajesandroid.models.LugarEntity;

import java.util.ArrayList;
import java.util.List;

public class ListadoLugares extends Fragment {

    Toolbar toolbarListadoLugares;
    DataRoomDB database;

    RecyclerView recyclerLugares;
    List<LugarEntity> lugarEntities = new ArrayList<>();
    LugaresAdapter lugaresAdapter;
    LinearLayoutManager llm;

    String opcionSpinner;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        database = DataRoomDB.getInstance(getContext());

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_listado_lugares, container, false);

        lugarEntities = database.lugarDao().getAll();

        // ELEMENTOS RECYCLER
        recyclerLugares = v.findViewById(R.id.recyclerLugares);
        llm = new LinearLayoutManager(getContext());
        recyclerLugares.setLayoutManager(llm);
        lugaresAdapter = new LugaresAdapter(lugarEntities, getActivity());
        recyclerLugares.setAdapter(lugaresAdapter);

        // TOOLBAR + LISTENER
        toolbarListadoLugares = v.findViewById(R.id.toolbarListadoLugares);
        // El menu puede ser inflado desde el editor de layout

        // String [] rellenado para los spinners
        String [] regiones = new String[]{"Europa", "América", "Asia", "África"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_dropdown_item, regiones);

        toolbarListadoLugares.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.menu_add_lugar:
                        // CREACION DIALOGO
                        Dialog dialog = new Dialog(getContext());
                        dialog.setContentView(R.layout.dialog_new_lugar);

                        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
                        lp.copyFrom(dialog.getWindow().getAttributes());
                        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
                        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;

                        dialog.show();
                        dialog.getWindow().setAttributes(lp);

                        // ELEMENTOS DIALOGO
                        EditText editTextNombreLugar = dialog.findViewById(R.id.editTextNombreLugar);
                        Spinner spinnerRegionDialog = dialog.findViewById(R.id.spinnerRegionDialog);
                        Button btnAddLugar = dialog.findViewById(R.id.btnAddLugar);

                        // Preparar & Rellenar Spinner
                        // En este caso, los elementos del spinner se encuentra en la creacion de la vista, ya que voy a usar...
                        // ...dos iguales

                        spinnerRegionDialog.setAdapter(adapter);
                        spinnerRegionDialog.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                opcionSpinner = adapterView.getItemAtPosition(i).toString();
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> adapterView) {
                            }
                        });

                        btnAddLugar.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                LugarEntity lugar = new LugarEntity();

                                lugar.setFoto(R.drawable.lugar);
                                lugar.setNombre(editTextNombreLugar.getText().toString());
                                lugar.setRegion(opcionSpinner);

                                dialog.dismiss();

                                long resultado = database.lugarDao().insert(lugar);
                                Log.i("insert() = ", "" + resultado); // Comprobacion

                                // LIMPIAMOS LISTA Y ACTUALIZAMOS
                                lugarEntities.clear();
                                lugarEntities = database.lugarDao().getAll();
                                lugaresAdapter = new LugaresAdapter(lugarEntities, getActivity());
                                recyclerLugares.setAdapter(lugaresAdapter);

                                Toast.makeText(getContext(), "Lugar Agregado", Toast.LENGTH_SHORT).show();
                            }
                        });
                        break;
                    case R.id.menu_filtrado_lugar:
                        // CREACION DIALOGO
                        Dialog dialog2 = new Dialog(getContext());
                        dialog2.setContentView(R.layout.dialog_filter_region);

                        WindowManager.LayoutParams lp2 = new WindowManager.LayoutParams();
                        lp2.copyFrom(dialog2.getWindow().getAttributes());
                        lp2.width = WindowManager.LayoutParams.MATCH_PARENT;
                        lp2.height = WindowManager.LayoutParams.WRAP_CONTENT;

                        dialog2.show();
                        dialog2.getWindow().setAttributes(lp2);

                        // ELEMENTOS DIALOGO
                        Spinner spinnerFilterRegion = dialog2.findViewById(R.id.spinnerFilterRegion);
                        Button btnFilterRegion = dialog2.findViewById(R.id.btnFilterRegion);

                        // PREPARAR SPINNER
                        spinnerFilterRegion.setAdapter(adapter);
                        spinnerFilterRegion.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                opcionSpinner = adapterView.getItemAtPosition(i).toString();
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> adapterView) {
                            }
                        });

                        btnFilterRegion.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                lugarEntities.clear();
                                lugarEntities = database.lugarDao().filterRegion(opcionSpinner);

                                dialog2.dismiss();

                                lugaresAdapter = new LugaresAdapter(lugarEntities, getActivity());
                                recyclerLugares.setAdapter(lugaresAdapter);

                                Toast.makeText(getContext(), "Filtro Aplicado", Toast.LENGTH_SHORT).show();
                            }
                        });

                }
                return false;
            }
        });


        return v;
    }
}
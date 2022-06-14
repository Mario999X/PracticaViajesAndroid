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
import com.example.practicaviajesandroid.adapters.ViajeroAdapter;
import com.example.practicaviajesandroid.data.DataRoomDB;
import com.example.practicaviajesandroid.models.ViajeroEntity;

import java.util.ArrayList;
import java.util.List;

public class ListadoViajeros extends Fragment {

    Toolbar toolbarListadoViajeros;
    DataRoomDB database;

    RecyclerView recyclerViajeros;
    List<ViajeroEntity> viajeroEntities = new ArrayList<>();
    ViajeroAdapter viajeroAdapter;
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
        View v = inflater.inflate(R.layout.fragment_listado_viajeros, container, false);

        viajeroEntities = database.viajeroDao().getAll();

        // ELEMENTOS RECYCLER
        recyclerViajeros = v.findViewById(R.id.recyclerViajeros);
        llm = new LinearLayoutManager(getContext());
        recyclerViajeros.setLayoutManager(llm);
        viajeroAdapter = new ViajeroAdapter(viajeroEntities, getActivity());
        recyclerViajeros.setAdapter(viajeroAdapter);

        // TOOLBAR + LISTENER
        toolbarListadoViajeros = v.findViewById(R.id.toolbarViajerosListado);
        // Menu inflado desde el editor

        // String [] rellenado para los spinners
        String [] destinos = database.lugarDao().getNombreLugares();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_dropdown_item, destinos);

        toolbarListadoViajeros.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.menu_add_viajero:
                        // CREACION DIALOGO
                        Dialog dialog = new Dialog(getContext());
                        dialog.setContentView(R.layout.dialog_new_viajero);

                        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
                        lp.copyFrom(dialog.getWindow().getAttributes());
                        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
                        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;

                        dialog.show();
                        dialog.getWindow().setAttributes(lp);

                        // ELEMENTOS DIALOGO
                        EditText editViajeroAddNombre = dialog.findViewById(R.id.editViajeroAddNombre);
                        Spinner spinnerDestinosNewViajero = dialog.findViewById(R.id.spinnerDestinosNewViajero);
                        Button btnAddViajero = dialog.findViewById(R.id.btnAddViajero);

                        // PREPARAR SPINNER
                        spinnerDestinosNewViajero.setAdapter(adapter);
                        spinnerDestinosNewViajero.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                opcionSpinner = adapterView.getItemAtPosition(i).toString();
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> adapterView) {
                            }
                        });

                        btnAddViajero.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {

                                if (opcionSpinner == null) {

                                    Toast.makeText(getContext(), "Sin Destinos Posibles", Toast.LENGTH_SHORT).show();

                                } else {
                                    ViajeroEntity viajero = new ViajeroEntity();

                                    viajero.setFoto(R.drawable.viajero);
                                    viajero.setNombre(editViajeroAddNombre.getText().toString());
                                    viajero.setLugarDestino(opcionSpinner);

                                    dialog.dismiss();

                                    long resultado = database.viajeroDao().insert(viajero);
                                    Log.i("insert() = ", "" + resultado); // Comprobacion

                                    // LIMPIAMOS LISTA Y ACTUALIZAMOS
                                    viajeroEntities.clear();
                                    viajeroEntities = database.viajeroDao().getAll();
                                    viajeroAdapter = new ViajeroAdapter(viajeroEntities, getActivity());
                                    recyclerViajeros.setAdapter(viajeroAdapter);

                                    Toast.makeText(getContext(), "Viajero Agregado", Toast.LENGTH_SHORT).show();
                                }

                            }
                        });
                        break;
                    case R.id.menu_busqueda_viajero:
                        // CREACION DIALOGO2
                        Dialog dialog2 = new Dialog(getContext());
                        dialog2.setContentView(R.layout.dialog_search_viajero);

                        WindowManager.LayoutParams lp2 = new WindowManager.LayoutParams();
                        lp2.copyFrom(dialog2.getWindow().getAttributes());
                        lp2.width = WindowManager.LayoutParams.MATCH_PARENT;
                        lp2.height = WindowManager.LayoutParams.WRAP_CONTENT;

                        dialog2.show();
                        dialog2.getWindow().setAttributes(lp2);

                        // ELEMENTOS DIALOGO
                        EditText editTextIdViajero = dialog2.findViewById(R.id.editTextIdViajero);
                        Button btnSearchViajero = dialog2.findViewById(R.id.btnSearchViajero);

                        btnSearchViajero.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                dialog2.dismiss();

                                viajeroEntities.clear();
                                viajeroEntities = database.viajeroDao().selectViajero(Integer.parseInt(editTextIdViajero.getText().toString()));
                                viajeroAdapter = new ViajeroAdapter(viajeroEntities, getActivity());
                                recyclerViajeros.setAdapter(viajeroAdapter);

                                Toast.makeText(getContext(), "Búsqueda Realizada", Toast.LENGTH_SHORT).show();
                            }
                        });
                        break;
                    case R.id.menu_filtro_destino:
                        // CREACION DIALOGO3
                        Dialog dialog3 = new Dialog(getContext());
                        dialog3.setContentView(R.layout.dialog_filtro_destino);

                        WindowManager.LayoutParams lp3 = new WindowManager.LayoutParams();
                        lp3.copyFrom(dialog3.getWindow().getAttributes());
                        lp3.width = WindowManager.LayoutParams.MATCH_PARENT;
                        lp3.height = WindowManager.LayoutParams.WRAP_CONTENT;

                        dialog3.show();
                        dialog3.getWindow().setAttributes(lp3);

                        // ELEMENTOS DIALOGO
                        Spinner spinnerFilterDestino = dialog3.findViewById(R.id.spinnerFilterDestino);
                        Button btnFilterDestino = dialog3.findViewById(R.id.btnFilterDestino);

                        // PREPARAR SPINNER
                        spinnerFilterDestino.setAdapter(adapter);
                        spinnerFilterDestino.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                opcionSpinner = adapterView.getItemAtPosition(i).toString();
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> adapterView) {
                            }
                        });

                        btnFilterDestino.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                if (opcionSpinner == null) {

                                    dialog3.dismiss();
                                    Toast.makeText(getContext(), "Sin Destinos ", Toast.LENGTH_SHORT).show();

                                } else {
                                    dialog3.dismiss();

                                    viajeroEntities.clear();
                                    viajeroEntities = database.viajeroDao().filterDestino(opcionSpinner);
                                    viajeroAdapter = new ViajeroAdapter(viajeroEntities, getActivity());
                                    recyclerViajeros.setAdapter(viajeroAdapter);

                                    Toast.makeText(getContext(), "Filtro Aplicado", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                }
                return false;
            }
        });

        return v;
    }
}
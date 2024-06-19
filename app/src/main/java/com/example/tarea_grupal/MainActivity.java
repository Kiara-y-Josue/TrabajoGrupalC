package com.example.tarea_grupal;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import WebServices.Asynchtask;
import WebServices.WebService;

public class MainActivity extends AppCompatActivity implements Asynchtask {
    ListView lstOpciones;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        lstOpciones = findViewById(R.id.lstOpciones);
        Map<String, String> datos = new HashMap<String, String>();
        WebService ws = new WebService("https://reqres.in/api/users",
                datos, MainActivity.this, MainActivity.this);
        ws.execute("GET");

    }

    @Override
    public void processFinish(String result) {
        try {
            JSONObject JSONlista = new JSONObject(result);
            JSONArray JSONlistaUsuarios = JSONlista.getJSONArray("data");
            ArrayList<Usuario> lstUsuarios = Usuario.JsonObjectsBuild(JSONlistaUsuarios);
            AdaptadorAlumnos adaptador = new AdaptadorAlumnos(this, lstUsuarios);
            lstOpciones.setAdapter(adaptador);
        } catch (JSONException e) {
            e.printStackTrace();
            Toast.makeText(this, "Error al procesar los datos JSON de usuarios", Toast.LENGTH_SHORT).show();
        }
    }
}
package com.example.tarea_grupal;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Usuario {
    private String nombres;
    private String email;
    private String website;
    private String urlavatar;

    public String getNombres() {
        return nombres;
    }

    public String getEmail() {
        return email;
    }

    public String getWebsite() {
        return website;
    }

    public String getUrlavatar() {
        return urlavatar;
    }
    public Usuario(JSONObject a) throws JSONException {
        nombres = a.getString("first_name").toString() + " " + a.getString("last_name").toString();
        email = a.getString("email").toString() ;
        website = a.getString("avatar").toString() ;
        urlavatar = a.getString("avatar").toString() ;
    }
    public static ArrayList<Usuario> JsonObjectsBuild(JSONArray datos) throws JSONException {
        ArrayList<Usuario> usuarios = new ArrayList<>();
        for (int i = 0; i < datos.length() && i<20; i++) {
            usuarios.add(new Usuario(datos.getJSONObject(i)));
        }
        return usuarios;
    }
}
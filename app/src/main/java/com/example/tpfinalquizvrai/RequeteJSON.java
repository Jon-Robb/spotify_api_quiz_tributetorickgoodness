package com.example.tpfinalquizvrai;


import android.content.Context;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


public class RequeteJSON {

//    classe personnelle pour une qui a comme fonction de faire une requete JSON

    public RequeteJSON(){}

    public void faireRequete(Context context, int method, String url, Response.Listener<JSONObject> listener){

        RequestsSingleton instance = RequestsSingleton.getInstance(context);

        JsonObjectRequest requete = new JsonObjectRequest(method, url, null, listener,
                error -> error.printStackTrace()) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = new HashMap<>();
                String token = instance.getToken();
                String auth = "Bearer " + token;
                headers.put("Authorization", auth);
                headers.put("Content-Type", "application/json; charset=utf-8");
                return headers;
            }
        };
        instance.addToRequestQueue(requete);
    }

}

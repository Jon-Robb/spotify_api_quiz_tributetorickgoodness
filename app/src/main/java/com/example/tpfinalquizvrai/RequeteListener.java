package com.example.tpfinalquizvrai;

import com.android.volley.Response;

import org.json.JSONObject;

public class RequeteListener implements Response.Listener<JSONObject>{

//     Classe servant a l implementation de l interface listener pour les requetes JSON

    private RequeteTermineeListener listener;

    public RequeteListener(RequeteTermineeListener listener){
        this.listener = listener;
    }

    @Override
    public void onResponse(JSONObject response) {
        listener.requeteTerminee(response);
    }
}

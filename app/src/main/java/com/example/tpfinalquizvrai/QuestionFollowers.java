package com.example.tpfinalquizvrai;

import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.android.volley.Request;
import com.android.volley.toolbox.NetworkImageView;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;

public class QuestionFollowers extends Fragment {

//    Fragment pour les questions en lien avec le nombre de followers.

    private TextView score;
    private TextView result;
    private ArrayList<Artiste> a;
    private Artistes artistes;
    private Artiste bonArtiste;
    private Utils utils;
    private QuestionHelper questionHelper;
    private boolean peutRepondre = true;
    private Score s;

    public QuestionFollowers() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
//        On transtype le inflater en ViewGroup, pour avoir acces aux views de ce fragment particulier
        ViewGroup parent = (ViewGroup )inflater.inflate(R.layout.fragment_question1, container, false);
        TextView q1 = parent.findViewById(R.id.q1);
        result = parent.findViewById(R.id.result3);
        TextView rep11 = parent.findViewById(R.id.reponse31);
        TextView rep12 = parent.findViewById(R.id.reponse32);
        score = parent.findViewById(R.id.scoreQ1);
        NetworkImageView img11 = parent.findViewById(R.id.img31);
        NetworkImageView img12 = parent.findViewById(R.id.img32);

//        Animations
        LinearLayout conteneurRep1 = parent.findViewById(R.id.conteneurRep31);
        LinearLayout conteneurRep2 = parent.findViewById(R.id.conteneurRep32);
        ObjectAnimator oa1 = ObjectAnimator.ofFloat(conteneurRep1, View.TRANSLATION_X, 0);
        ObjectAnimator oa2 = ObjectAnimator.ofFloat(conteneurRep2, View.TRANSLATION_X, 0);
        oa1.setDuration(2000);
        oa2.setDuration(2000);
        oa1.start();
        oa2.start();

//        On va chercher le score et on l affiche
        Context context = getContext();
        assert context != null;
        s = ((ConteneurFragmentsActivity) context).getS();
        score.setText(String.valueOf(s.getScore()));

        utils = new Utils(getContext());
        questionHelper = new QuestionHelper();

//        On cree un objet requeteTermineListener dans lequel on code les particularites de cette quetion
        RequeteTermineeListener requeteTermineeListener = response -> {
            Gson gson = new GsonBuilder().create();
//            On remplit un objet Artistes avec la reponse a la requete
            artistes = gson.fromJson(String.valueOf(response), Artistes.class);
//            On utilise nos fonctions specifiques pour les questions de followers
            a = artistes.getNArtists(2);
            bonArtiste = questionHelper.generateFollowersAnswer(a);
//            On remplit la vue avec les informations sur les deux artistes de la question
            utils.viewsFiller(q1, "Quel artiste a le plus de followers ?", img11, a.get(0), rep11, img12, a.get(1), rep12);
        };

//        Etapes d Eric
        EcouteurQ1 ec = new EcouteurQ1();
        conteneurRep1.setOnClickListener(ec);
        conteneurRep2.setOnClickListener(ec);

//        On cree les objets necessaires a la requete et au listener
        RequeteListener requeteListener = new RequeteListener(requeteTermineeListener);
        RequeteJSON requete = new RequeteJSON();

        UrlGenerator urlGenerator = new UrlGenerator();

        String url = urlGenerator.generateTwoArtistsUrl();
//        On fait la requete grace a notre objet RequeteJSON et notre listener
        requete.faireRequete(getContext(), Request.Method.GET, url, requeteListener);

        return parent;
    }

    public class EcouteurQ1 implements View.OnClickListener{

        @SuppressLint("SetTextI18n")
        @Override
        public void onClick(View view) {
//            Si l usager n a pas encore repondu, on agit selon la reponse qu il donne
            if (peutRepondre){
                String nom = utils.returnSecondChildString(view);
                Artiste artRep = artistes.getArtiste(nom);
                if (artRep == bonArtiste){
                    view.setBackgroundColor(getResources().getColor(R.color.green));
                    s.setScore(10);
                    score.setText(String.valueOf(s.getScore()));
                    result.setTextColor(getResources().getColor(R.color.green));
                    result.setText("Bonne reponse!!!\n" + artRep.followers.total + " followers");

                }
                else{
                    view.setBackgroundColor(getResources().getColor(R.color.orange));
                    result.setTextColor(getResources().getColor(R.color.orange));
                    result.setText("Mauvaises reponse\nBonne reponse : " + bonArtiste.name + ", " + bonArtiste.followers.total + " followers");
                }
                peutRepondre = false;
            }
        }
    }
}
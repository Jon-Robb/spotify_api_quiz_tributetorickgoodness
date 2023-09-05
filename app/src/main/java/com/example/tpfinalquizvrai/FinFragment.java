package com.example.tpfinalquizvrai;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Objects;


public class FinFragment extends Fragment {


    private Button btnSave, btnQuitter;
    private TextView scoreFinal;
    private ImageView etoile1, etoile2, etoile3, etoile4;
    private Score s;
    private ObjectAnimator o1, o2, o3, o4;
    private EcouteurFin ec;
    private Utils utils;

    public FinFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup parent = (ViewGroup )inflater.inflate(R.layout.fragment_fin, container, false);
        btnSave = parent.findViewById(R.id.btnSave);
        btnQuitter = parent.findViewById(R.id.btnRetour);
        scoreFinal = parent.findViewById(R.id.scoreFin);
        etoile1 = parent.findViewById(R.id.imgEtoile1);
        etoile2 = parent.findViewById(R.id.imgEtoile2);
        etoile3 = parent.findViewById(R.id.imgEtoile3);
        etoile4 = parent.findViewById(R.id.imgEtoile4);
        utils = new Utils(getContext());

        Context context = getContext();
        assert context != null;
        s = ((ConteneurFragmentsActivity)context).getS();
        scoreFinal.setText(String.valueOf(s.getScore()));


//        Beaucoup de code pour l animation des etoiles, dans un animatorSet ca ne faisait pas ce que je voulais.
        o1 = ObjectAnimator.ofFloat(etoile1, View.ROTATION, 3600);
        o2 = ObjectAnimator.ofFloat(etoile2, View.ROTATION, 3600);
        o3 = ObjectAnimator.ofFloat(etoile3, View.ROTATION, 3600);
        o4 = ObjectAnimator.ofFloat(etoile4, View.ROTATION, 3600);
        o1.setDuration(10000);
        o2.setDuration(10000);
        o3.setDuration(10000);
        o4.setDuration(10000);
        o1.setInterpolator(new AccelerateDecelerateInterpolator());
        o2.setInterpolator(new AccelerateDecelerateInterpolator());
        o3.setInterpolator(new AccelerateDecelerateInterpolator());
        o4.setInterpolator(new AccelerateDecelerateInterpolator());
        o1.setRepeatCount(ValueAnimator.INFINITE);
        o2.setRepeatCount(ValueAnimator.INFINITE);
        o3.setRepeatCount(ValueAnimator.INFINITE);
        o4.setRepeatCount(ValueAnimator.INFINITE);
        o1.setRepeatMode(ValueAnimator.REVERSE);
        o2.setRepeatMode(ValueAnimator.REVERSE);
        o3.setRepeatMode(ValueAnimator.REVERSE);
        o4.setRepeatMode(ValueAnimator.REVERSE);

        o1.start();
        o2.start();
        o3.start();
        o4.start();

        ec = new EcouteurFin();
        btnSave.setOnClickListener(ec);
        btnQuitter.setOnClickListener(ec);

        return parent;
    }

    public class EcouteurFin implements View.OnClickListener{

        @Override
        public void onClick(View source) {
//            Si l usager veut sauvegarder, on va voir s il y a un fichier de serialization, s il y
//             en a un, on prend l objet, qui est un objet Scores, on l update avec le nouveau Score.
            if (source == btnSave){
                if (s.getScore() > 0){
                    try{
                        Scores scores = (Scores) utils.getSerialized(requireActivity(), "scores.ser");
                        scores.updateArrayScore(s);
                        utils.serialiser(requireActivity(), scores, "scores.ser");
                    }
                    catch(IOException | ClassNotFoundException exception){
//                        S il n y a pas de fichier de serialization on va en creer un et y ajouter un scores
                        try {
                            Scores scores = new Scores();
                            scores.updateArrayScore(s);
                            utils.serialiser(requireActivity(), scores, "scores.ser");
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
                else{
                    Toast.makeText(getContext(), "Vous n'avez pas fait de point", Toast.LENGTH_LONG).show();
                }
            }
            requireActivity().finish();
        }
    }
}
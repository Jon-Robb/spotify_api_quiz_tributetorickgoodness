package com.example.tpfinalquizvrai;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.toolbox.NetworkImageView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Utils {

//    Classe qui renferme les fonctions qui m ont ete utiles au cours du projet

    private RequestsSingleton instance;

    public Utils(Context context){

        instance = RequestsSingleton.getInstance(context);

    }

    public void viewsFiller(TextView q, String question, NetworkImageView img1, Artiste a1, TextView rep1, NetworkImageView img2, Artiste a2, TextView rep2){

//        Fonction tres specifique pour remplir la Vue d un fragment de type question.
        q.setText(question);
        int index1 = a1.randomFromVecImg();
        int index2 = a2.randomFromVecImg();
        String image1 = a1.images.get(index1).getUrl();
        String image2 = a2.images.get(index2).getUrl();
        img1.setImageUrl(image1, instance.getImageLoader());
        rep1.setText(a1.name);
        img2.setImageUrl(image2, instance.getImageLoader());
        rep2.setText(a2.name);

    }

    public String returnSecondChildString(View view){
//        Fonction specifique qui renvoie le deuxieme enfant d une view et renvoie le text de celui ci

        LinearLayout parent = (LinearLayout) view;
        TextView enfant = (TextView) parent.getChildAt(1);
        return enfant.getText().toString();

    }

    public void serialiser(Activity activity, Object s, String fichier) throws IOException {
//       FOnction generique qui va serialiser un objet dans un fichier
            FileOutputStream fos = activity.openFileOutput(fichier, Context.MODE_PRIVATE);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(s);
            oos.close();
    }

    public Object getSerialized(Activity activity, String fichier) throws IOException, ClassNotFoundException {
//        Fonction generique qui renvoie un objet d un fichier de serialization
        FileInputStream fis = activity.openFileInput(fichier);
        ObjectInputStream ois = new ObjectInputStream(fis);
        Object o = ois.readObject();
        ois.close();
        return o;
    }

    public void fillBestScore(Activity activity, TextView bestScore) throws IOException, ClassNotFoundException {
//        Fonction specifique qui remplit le textview bestScore dans les fragments Question
        Scores s = (Scores) this.getSerialized(activity, "scores.ser");
        Score best = new Score();

        if (s.getArrayScores() != null){
            for(Score score : s.getArrayScores()){
                if (score.getScore() > best.getScore()){
                    best = score;
                }
            }
            bestScore.setText(String.valueOf(best.getScore()));
        }
        else{
            bestScore.setText(String.valueOf(0));
        }
    }

}


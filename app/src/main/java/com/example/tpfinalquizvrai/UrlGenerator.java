package com.example.tpfinalquizvrai;

import java.util.ArrayList;
import java.util.Collections;

public class UrlGenerator{

//    Classe qui garde en stock les urls souhaites

    private String https = "https://api.spotify.com/v1/artists?ids=";
    private ArrayList<String> artisteIDs;

    public UrlGenerator(){
//        On remplit une liste d url d artistes a la creation du generateur
        this.artisteIDs = new ArrayList<>();
        this.artisteIDs.add("4Z8W4fKeB5YxbusRsdQVPb");
        this.artisteIDs.add("0C0XlULifJtAgn6ZNCW2eu");
        this.artisteIDs.add("12Chz98pHFMPJEknJQMWvI");
        this.artisteIDs.add("0k17h0D3J5VfsdmQ1iZtE9");
        this.artisteIDs.add("0L8ExT028jH3ddEcZwqJJ5");
        this.artisteIDs.add("3k0HFz1mMtmsaWYsX75MiW");
        this.artisteIDs.add("2cdnxq3gRcJudFLmWhDTQp");
        this.artisteIDs.add("7dGJo4pcD2V6oG8kP0tJRR");
        this.artisteIDs.add("2o5jDhtHVPhrJdv3cEQ99Z");
        this.artisteIDs.add("7sk1rfSVKopRQf1fxSJLTq");
        this.artisteIDs.add("2ye2Wgw4gimLv2eAKyk1NB");
        this.artisteIDs.add("3RNrq3jvMZxD9ZyoOZbQOD");
        this.artisteIDs.add("6zvul52xwTWzilBZl6BUbT");
        this.artisteIDs.add("0epOFNiUfyON9EYx7Tpr6V");
        this.artisteIDs.add("2Xoi1HPP0Wa6nyNSYyHxgI");
        this.artisteIDs.add("54BZLczVMsW9sPSIcUst1a");
        this.artisteIDs.add("7ys7ta4FOM1pKxJwpqvazG");
        this.artisteIDs.add("1ms7U6RHK5ox13A37q5eDG");
        this.artisteIDs.add("4wxSKb9ur3AbGRCv81xFAd");
        this.artisteIDs.add("0SfsnGyD8FpIN4U4WCkBZ5");
        this.artisteIDs.add("1XpDYCrUJnvCo9Ez6yeMWh");
        this.artisteIDs.add("5Qi4Bb7a8C0a00NZcA77L0");
        this.artisteIDs.add("6evKD5JWJON3qPBJtUEmtY");
        this.artisteIDs.add("6DPYiyq5kWVQS4RGwxzPC7");
        this.artisteIDs.add("1ZwdS5xdxEREPySFridCfh");
        this.artisteIDs.add("5me0Irg2ANcsgc93uaYrpb");
        this.artisteIDs.add("5pKCCKE2ajJHZ9KAiaK11H");
        this.artisteIDs.add("1HY2Jd0NmPuamShAr6KMms");
        this.artisteIDs.add("5YGY8feqx7naU7z4HrwZM6");
        this.artisteIDs.add("06HL4z0CvFAxyc27GXpf02");
        this.artisteIDs.add("6jJ0s89eD6GaHleKKya26X");
        this.artisteIDs.add("6eUKZXaKkcviH0Ku9w2n3V");
    }

    public String generateTwoArtistsUrl(){
//        Fonction qui renvoie un string monte pour une requete a deux artistes

        Collections.shuffle(this.artisteIDs);

        return this.https + this.artisteIDs.get(0) + "%2C" + this.artisteIDs.get(1);

    }

}

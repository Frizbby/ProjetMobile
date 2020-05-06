package com.esiea.projetmobile;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;



public class Giphy {
    private String title;
    private String url;
    private String embed_url;
    private String images;
   /* @SerializedName("url")
    @Expose*/
    //private String url2;

    public String getImages(){   return images; }
    public String getDownsizedLarge() { return url;}
    public String getUrl() { return url; }

        //public String getUrl(){

        //}




    public String getTitle() { return title; }

    /*

*/
    public String getEmbedUrl() { return embed_url; }

}

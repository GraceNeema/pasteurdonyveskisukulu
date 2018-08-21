package Model;

/**
 * Created by Lenovo on 8/14/2018.
 */

public class Meditation {

    String titre, soustitre, message, date, imageReSource;



    public String getSoustitre() {
        return soustitre;
    }

    public void setSoustitre(String soustitre) {
        this.soustitre = soustitre;
    }


    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getImageResource() {
        return imageReSource;
    }

    public void setImageReSource(String imageReSource) {
        this.imageReSource = imageReSource;
    }
    public String getMessage(){
        return message;
    }
    public void  setMessage(String message){
        this.message=message;
    }
    public String getDate(){
        return date;
    }
    public void  setDate(String date){
        this.date=date;
    }

}

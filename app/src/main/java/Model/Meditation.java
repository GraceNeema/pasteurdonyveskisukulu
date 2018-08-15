package Model;

/**
 * Created by Lenovo on 8/14/2018.
 */

public class Meditation {

    String titre, message, date;
    int imageReSourceId;
   // int isfav;
    int isturned;

    public int getIsturned() {
        return isturned;
    }

    public void setIsturned(int isturned) {
        this.isturned = isturned;
    }


    public String getCardName() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public int getImageResourceId() {
        return imageReSourceId;
    }

    public void setImageRef(int imageReSourceId) {
        this.imageReSourceId = imageReSourceId;
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

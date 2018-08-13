package Model;

/**
 * Created by YvonFlourAlvin on 27/06/2018.
 */

public class Actualité {

    private String titre;
    private String message;
    private String date;
    private String imageRef;

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getImageRef() {
        return imageRef;
    }

    public void setImageRef(String imageRef) {
        this.imageRef = imageRef;
    }

    public Actualité() {

    }

    public Actualité(String titre, String message, String date, String imageRef) {

        this.titre = titre;
        this.message = message;
        this.date = date;
        this.imageRef = imageRef;
    }
}

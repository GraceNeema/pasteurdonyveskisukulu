package Model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by YvonFlourAlvin on 27/06/2018.
 */

public class Predication {

    private int idpredication;
    private String titre;
    private String orateur;
    private String reference_biblique;
    private String message;
    private String date;
    private String imageRef;


    public String getOrateur() {
        return orateur;
    }

    public void setOrateur(String orateur) {
        this.orateur = orateur;
    }

    public String getReference_biblique() {
        return reference_biblique;
    }

    public void setReference_biblique(String reference_biblique) {
        this.reference_biblique = reference_biblique;
    }

    public int getIdpredication() {
        return idpredication;
    }

    public void setIdpredication(int idpredication) {
        this.idpredication = idpredication;
    }

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

    public Predication() {

    }

    public Predication(String titre, String message, String date, String imageRef) {

        this.titre = titre;
        this.message = message;
        this.date = date;
        this.imageRef = imageRef;
    }

    public Predication jsonTopredication(String json){
        Predication predication = null;
        try{
            JSONObject json_ob= new JSONObject(json);
            predication = new Predication();
            predication.setIdpredication(json_ob.getInt("idpredication"));
            predication.setTitre(json_ob.getString("titre"));
            predication.setOrateur(json_ob.getString("orateur"));
            predication.setReference_biblique(json_ob.getString("reference_biblique"));
            predication.setMessage(json_ob.getString("message"));
            predication.setImageRef(json_ob.getString("image_ref"));
            predication.setDate(json_ob.getString("date"));
            return predication;
        }catch (Exception e){
            return null;
        }
    }

    // todo : interaction avec la base de données

    public static final String table_name = "predication";
    public static final String col_idpredication = "idpredication";
    public  static final String col_titre  = "titre";
    public  static final String col_orateur= "orateur";
    public  static final String col_reference_biblique= "reference_biblique";
    public  static final String col_message = "message";
    public  static final String col_date = "date";
    public  static final String col_image_ref = "image_ref";
    public  static final String create_table = "create table "+table_name+"(" +
            col_idpredication+" integer primary key," +
            col_titre+" varchar(45)," +
            col_orateur+" varchar(200),"+
            col_reference_biblique+" text,"+
            col_message+" text," +
            col_image_ref +" text," +
            col_date+" varchar(45));";

    public static final String tab_cols[] = new String[]{
        col_idpredication,
        col_titre,
        col_orateur,
        col_reference_biblique,
        col_message,
        col_image_ref,
        col_date
    };

    private Predication cursorTopredication(Cursor c){
        Predication predication = null;
        try{
            predication = new Predication();
            predication.setIdpredication(c.getInt(0));
            predication.setTitre(c.getString(1));
            predication.setOrateur(c.getString(2));
            predication.setReference_biblique(c.getString(3));
            predication.setMessage(c.getString(4));
            predication.setImageRef(c.getString(5));
            predication.setDate(c.getString(6));
            return predication;
        }catch (Exception e){
            return null;
        }
    }


    public long save(Context context){
        ContentValues values = new ContentValues();
        values.put(col_idpredication, this.idpredication);
        values.put(col_titre, this.titre);
        values.put(col_orateur, this.orateur);
        values.put(col_reference_biblique, this.reference_biblique);
        values.put(col_message, this.message);
        values.put(col_date, this.date);
        values.put(col_image_ref, this.imageRef);
        try{
            if(this.get(context, this.idpredication) != null){ // update

            }else {  // insert
                Database db = new Database(context);
                SQLiteDatabase mdb = db.getWritableDatabase();
                long l = mdb.insert(table_name, null, values );
                mdb.close();
                db.close();
            }
        }catch (Exception e){
            Log.e("predication_info", e.getMessage());
        }
        return 0;
    }

    public Predication get(Context context, int _idpredication){
        Predication predication = null;
        try{
            Database db = new Database(context);
            SQLiteDatabase mdb = db.getReadableDatabase();
            Cursor c = mdb.query(table_name, tab_cols, "idpredication="+_idpredication, null, null, null, null);
            predication = cursorTopredication(c);
            mdb.close();
            db.close();
            return predication;
        }catch (Exception e){

        }
        return predication;
    }

    public ArrayList<Predication> getAll(Context context){
        ArrayList<Predication> predications = null;
        try{
            predications = new ArrayList<>();
            Database db= new Database(context);
            SQLiteDatabase mdb = db.getReadableDatabase();
            Cursor c = mdb.query(table_name, tab_cols, null,null,null,null,null);
            if(c != null && c.getCount() != 0 && c.moveToFirst()){
                do{
                    predications.add(cursorTopredication(c));
                }while (c.moveToNext());
            }
            mdb.close();
            db.close();
        }catch (Exception e){

        }
        return predications;
    }


}

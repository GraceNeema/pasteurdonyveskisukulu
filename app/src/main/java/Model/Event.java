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

public class Event {

    private int idactualite;
    private String titre;
    private String message;
    private String date;
    private String imageRef;


    public int getIdactualite() {
        return idactualite;
    }

    public void setIdactualite(int idactualite) {
        this.idactualite = idactualite;
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

    public Event() {

    }

    public Event(String titre, String message, String date, String imageRef) {

        this.titre = titre;
        this.message = message;
        this.date = date;
        this.imageRef = imageRef;
    }

    public Event jsonToActualite(String json){
        Event actualite = null;
        try{
            JSONObject json_ob= new JSONObject(json);
            actualite = new Event();
            actualite.setIdactualite(json_ob.getInt("idactualite"));
            actualite.setTitre(json_ob.getString("titre"));
            actualite.setMessage(json_ob.getString("message"));
            actualite.setImageRef(json_ob.getString("image_ref"));
            actualite.setDate(json_ob.getString("date"));
            return actualite;
        }catch (Exception e){
            return null;
        }
    }

    // todo : interaction avec la base de données

    public static final String table_name = "actualite";
    public static final String col_idactualite = "idactualite";
    public  static final String col_titre  = "titre";
    public  static final String col_message = "message";
    public  static final String col_date = "date";
    public  static final String col_image_ref = "image_ref";
    public  static final String create_table = "create table "+table_name+"(" +
            col_idactualite+" integer primary key," +
            col_titre+" varchar(45)," +
            col_message+" text," +
            col_image_ref +" text," +
            col_date+" varchar(45));";

    public static final String tab_cols[] = new String[]{
        col_idactualite,
        col_titre,
        col_message,
        col_image_ref,
        col_date
    };

    private Event cursorToActualite(Cursor c){
        Event actualite = null;
        try{
            actualite = new Event();
            actualite.setIdactualite(c.getInt(0));
            actualite.setTitre(c.getString(1));
            actualite.setMessage(c.getString(2));
            actualite.setImageRef(c.getString(3));
            actualite.setDate(c.getString(4));
            return actualite;
        }catch (Exception e){
            return null;
        }
    }


    public long save(Context context){
        ContentValues values = new ContentValues();
        values.put(col_idactualite, this.idactualite);
        values.put(col_titre, this.titre);
        values.put(col_message, this.message);
        values.put(col_date, this.date);
        values.put(col_image_ref, this.imageRef);
        try{
            if(this.get(context, this.idactualite) != null){ // update

            }else {  // insert
                Database db = new Database(context);
                SQLiteDatabase mdb = db.getWritableDatabase();
                long l = mdb.insert(table_name, null, values );
                mdb.close();
                db.close();
            }
        }catch (Exception e){
            Log.e("Actualite_info", e.getMessage());
        }
        return 0;
    }

    public Event get(Context context, int _idactualite){
        Event actualite = null;
        try{
            Database db = new Database(context);
            SQLiteDatabase mdb = db.getReadableDatabase();
            Cursor c = mdb.query(table_name, tab_cols, "idactualite="+_idactualite, null, null, null, null);
            actualite = cursorToActualite(c);
            mdb.close();
            db.close();
            return actualite;
        }catch (Exception e){

        }
        return actualite;
    }

    public ArrayList<Event> getAll(Context context){
        ArrayList<Event> actualites = null;
        try{
            actualites = new ArrayList<>();
            Database db= new Database(context);
            SQLiteDatabase mdb = db.getReadableDatabase();
            Cursor c = mdb.query(table_name, tab_cols, null,null,null,null,null);
            if(c != null && c.getCount() != 0 && c.moveToFirst()){
                do{
                    actualites.add(cursorToActualite(c));
                }while (c.moveToNext());
            }
            mdb.close();
            db.close();
        }catch (Exception e){

        }
        return actualites;
    }


}

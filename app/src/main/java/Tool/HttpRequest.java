package Tool;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;


/**
 * Created by Yvon FLour Alvin on 02/04/2018.
 */

/**
*	Cette classe permet d'interagir avec une application web
*	via le protocol HTTP.
*	
*	Elle ne comporte qu'une seul methode, qui est static, donc 
*	pas besoin d'instancier l'objet.
*	
*	Elle permet de faire des requettes GET/POST par HTTP en spécifiant
*	les parametres de la requette et l'adresse de la page ou application
*	vers qui envoyer la requette.
*	
*	
*
**********************************************************************
************** usage *************************************************
**********************************************************************
*
*	Etant donné que la methode submit interagi avec le reseau, elle doit etre 
*	appelé dans une AsyncTask.
*
*	Exemple 1 :
*   
**/
public class HttpRequest {

	/**
	*	@param _url : l'url de l'application web avec lequel on doit interagir #action;
	*	@param request_methode : le type de la methode #methode GET or POST
	*	@param params_key[] : un tableau pour les names des champs du formulaire
	*	@param params_values[] : les  valeurs des champs du formulaire
	*
	*	@return String : retourne une chaine de caractère correspondant à la reponse du serveur (HTML, JSON, TXT, ...)
	**/
    public static String submit(String _url, String request_methode,  String[] params_key, String[] params_value){
        try {
            URL url = new URL(_url);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            if(request_methode.equals("POST"))connection.setDoOutput(true);
            connection.setRequestMethod(request_methode);

            OutputStream out = null;
            BufferedWriter writer = null;
            String data = null;

            if(request_methode.equals("POST")) out = connection.getOutputStream();
            if(request_methode.equals("POST"))writer = new BufferedWriter(new OutputStreamWriter(out, "UTF-8"));

            if(params_key.length!=params_value.length)return "-10";
            data = "";
            for(int i =0; i<params_key.length; i++){
                if(i==0) data += URLEncoder.encode(params_key[i], "UTF-8") + "=" + URLEncoder.encode(params_value[i], "UTF-8");
                else  data += "&" +URLEncoder.encode(params_key[i], "UTF-8") + "=" + URLEncoder.encode(params_value[i], "UTF-8");
            }
            data += "&" +URLEncoder.encode("api_key", "UTF-8") + "=" + URLEncoder.encode("zDrtmdp0fdrqcbuRzo4b425jh97762@fdmAWZkFDDHYtsjhREgfknWdskujSD", "UTF-8");

            if(request_methode.equals("POST"))writer.write(data);
            if(request_methode.equals("POST"))writer.flush();
            if(request_methode.equals("POST"))writer.close();

            Log.i("HttpRequest", "j\'ecris sur "+_url);

            InputStream inputStream = connection.getInputStream();
            String ret = new DataInputStream(inputStream).readLine();
            inputStream.close();
            Log.i("HttpRequest", "je retourne  "+ret);
            return ret;
        }catch(Exception e) {
            Log.e("HttpRequest", _url+" | error : "+e.toString());
            return null;
        }
    }


    public static Bitmap donwload_bitmap(String _url){
        try {
            URL url = new URL(_url);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.setRequestMethod("GET");

            Bitmap bitmap = null;
            Log.i("HttpRequest", "j\'ecris sur "+_url);
            InputStream inputStream = connection.getInputStream();
            bitmap = BitmapFactory.decodeStream(inputStream);
            inputStream.close();

            return bitmap;
        }catch(Exception e) {
            Log.e("HttpRequest", _url+" | error : "+e.toString());
            return null;
        }
    }

}

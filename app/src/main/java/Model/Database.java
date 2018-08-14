package Model;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by YvonFlourAlvin on 14/08/2018.
 */

public class Database extends SQLiteOpenHelper {
    public Database(Context context) {
        super(context, "donyves.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        try{
            sqLiteDatabase.execSQL(Actualite.create_table);
        }catch (Exception e){

        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        try{
            sqLiteDatabase.execSQL("DROP TABLE "+Actualite.table_name);
        }catch (Exception e){

        }

        onCreate(sqLiteDatabase);
    }
}

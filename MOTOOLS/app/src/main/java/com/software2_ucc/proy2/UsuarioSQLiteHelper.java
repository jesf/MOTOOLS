package com.software2_ucc.proy2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static android.provider.BaseColumns._ID;

/**
 * Created by User on 12/11/2016.
 */

public class UsuarioSQLiteHelper extends SQLiteOpenHelper{

    //String sql="CREATE TABLE Alertas("+_ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"+"Latitud REAL, Longitud REAL);";

    public UsuarioSQLiteHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql="CREATE TABLE Alertas(Latitud REAL, Longitud REAL);";
        db.execSQL(sql);

    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS Alertas");
        onCreate(db);
    }
   public void InsertReg(Double LT, Double LN){
        ContentValues datos=new ContentValues();
        datos.put("Latitud",LT);
        datos.put("Longitud",LN);
        this.getWritableDatabase().insert("Alertas",null,datos);
    }
    public void InsertReg2(Double LN){
        ContentValues datos=new ContentValues();
        datos.put("Longitud",LN);
        this.getWritableDatabase().insert("Alertas",null,datos);
    }

    public void abrirBD(){
        this.getWritableDatabase();
    }
    public void cerrarBD(){
        this.close();
    }

    public String Leer(){
        String result="";
        String columnas[]={"Longitud"};
        Cursor c=this.getReadableDatabase().query("Alertas",columnas,null,null,null,null,null);
        int il=c.getColumnIndex("Longitud");
        c.moveToLast();
        result=c.getString(il);
        return result;
    }
}

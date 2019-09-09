package com.widiyanto.kamusbahasa.Database;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import com.widiyanto.kamusbahasa.Model.KamusModelEI;
import com.widiyanto.kamusbahasa.Model.KamusModelIE;

import java.util.ArrayList;

import static android.provider.BaseColumns._ID;
import static com.widiyanto.kamusbahasa.Database.DatabaseContractEI.KamusColumns.DETAIL_EI;
import static com.widiyanto.kamusbahasa.Database.DatabaseContractEI.KamusColumns.TEXT_EI;
import static com.widiyanto.kamusbahasa.Database.DatabaseContractEI.TABLE_EI;
import static com.widiyanto.kamusbahasa.Database.DatabaseContractIE.KamusColumns.DETAIL_IE;
import static com.widiyanto.kamusbahasa.Database.DatabaseContractIE.KamusColumns.TEXT_IE;
import static com.widiyanto.kamusbahasa.Database.DatabaseContractIE.TABLE_IE;

public class KamusHelper {
    private Context context;
    private DatabaseHelper dataBaseHelper;
    private SQLiteDatabase database;

    public KamusHelper(Context context){
        this.context = context;
    }

    public KamusHelper open() throws SQLException {

        dataBaseHelper = new DatabaseHelper(context);
        database = dataBaseHelper.getReadableDatabase();
        return this;
    }

    public void close(){
        dataBaseHelper.close();
    }

    public ArrayList<KamusModelEI> getDataEIByText(String text){
        String result = "";
        Cursor cursor = database.query(TABLE_EI,
                null,
                TEXT_EI +" LIKE '%"+text+"%'" ,
                null,
                null,
                null,
                _ID + " ASC",
                null);
        cursor.moveToFirst();
        ArrayList<KamusModelEI> arrayList = new ArrayList<>();
        if (cursor.getCount()>0){
            do {
                KamusModelEI kamusModelEI;
                kamusModelEI = new KamusModelEI();
                kamusModelEI.setId(cursor.getInt(cursor.getColumnIndexOrThrow(_ID)));
                kamusModelEI.setText(cursor.getString(cursor.getColumnIndexOrThrow(TEXT_EI)));
                kamusModelEI.setDetail(cursor.getString(cursor.getColumnIndexOrThrow(DETAIL_EI)));

                arrayList.add(kamusModelEI);
                cursor.moveToNext();
            } while (!cursor.isAfterLast());
        }
        cursor.close();
        return arrayList;
    }

    public ArrayList<KamusModelEI> getAllDataEI(){
        Cursor cursor = database.query(TABLE_EI,
                null,
                null,
                null,
                null,
                null,
                _ID + " ASC",
                null);
        cursor.moveToFirst();
        ArrayList<KamusModelEI> arrayList = new ArrayList<>();
        KamusModelEI kamusModelEI;
        if (cursor.getCount()>0){
            do {
                kamusModelEI = new KamusModelEI();
                kamusModelEI.setId(cursor.getInt(cursor.getColumnIndexOrThrow(_ID)));
                kamusModelEI.setText(cursor.getString(cursor.getColumnIndexOrThrow(TEXT_EI)));
                kamusModelEI.setDetail(cursor.getString(cursor.getColumnIndexOrThrow(DETAIL_EI)));

                arrayList.add(kamusModelEI);
                cursor.moveToNext();
            } while (!cursor.isAfterLast());
        }
        cursor.close();
        return arrayList;
    }

    public ArrayList<KamusModelIE> getDataIEByText(String text){
        String result = "";
        Cursor cursor = database.query(TABLE_IE,
                null,
                TEXT_IE +" LIKE '%"+text+"%'" ,
                null,
                null,
                null,
                _ID + " ASC",
                null);
        cursor.moveToFirst();
        ArrayList<KamusModelIE> arrayList = new ArrayList<>();
        KamusModelIE kamusModelIE;
        if (cursor.getCount()>0){
            do {
                kamusModelIE = new KamusModelIE();
                kamusModelIE.setId(cursor.getInt(cursor.getColumnIndexOrThrow(_ID)));
                kamusModelIE.setText(cursor.getString(cursor.getColumnIndexOrThrow(TEXT_IE)));
                kamusModelIE.setDetail(cursor.getString(cursor.getColumnIndexOrThrow(DETAIL_IE)));

                arrayList.add(kamusModelIE);
                cursor.moveToNext();
            } while (!cursor.isAfterLast());
        }
        cursor.close();
        return arrayList;
    }

    public ArrayList<KamusModelIE> getAllDataIE(){
        Cursor cursor = database.query(TABLE_IE,
                null,
                null,
                null,
                null,
                null,
                _ID + " ASC",
                null);
        cursor.moveToFirst();
        ArrayList<KamusModelIE> arrayList = new ArrayList<>();
        KamusModelIE kamusModelIE;
        if (cursor.getCount()>0){
            do {
                kamusModelIE = new KamusModelIE();
                kamusModelIE.setId(cursor.getInt(cursor.getColumnIndexOrThrow(_ID)));
                kamusModelIE.setText(cursor.getString(cursor.getColumnIndexOrThrow(TEXT_IE)));
                kamusModelIE.setDetail(cursor.getString(cursor.getColumnIndexOrThrow(DETAIL_IE)));

                arrayList.add(kamusModelIE);
                cursor.moveToNext();
            } while (!cursor.isAfterLast());
        }
        cursor.close();
        return arrayList;
    }

    public void beginTransaction(){
        database.beginTransaction();
    }

    public void setTransactionSuccess(){
        database.setTransactionSuccessful();
    }

    public void endTransaction(){
        database.endTransaction();
    }

    public void insertTransactionEI(KamusModelEI kamusModelEI){
        String sqlEI = "INSERT INTO "+ TABLE_EI +" ("+ TEXT_EI +", "+ DETAIL_EI
                +") VALUES (?, ?)";
        SQLiteStatement stmtEI = database.compileStatement(sqlEI);
        stmtEI.bindString(1, kamusModelEI.getText());
        stmtEI.bindString(2, kamusModelEI.getDetail());
        stmtEI.execute();
        stmtEI.clearBindings();
    }

    public void insertTransactionIE(KamusModelIE kamusModelIE){
        String sqlIE = "INSERT INTO "+ TABLE_IE +" ("+ TEXT_IE +", "+ DETAIL_IE
                +") VALUES (?, ?)";
        SQLiteStatement stmtIE = database.compileStatement(sqlIE);
        stmtIE.bindString(1, kamusModelIE.getText());
        stmtIE.bindString(2, kamusModelIE.getDetail());
        stmtIE.execute();
        stmtIE.clearBindings();
    }
}

package com.widiyanto.kamusbahasa.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static android.provider.BaseColumns._ID;
import static com.widiyanto.kamusbahasa.Database.DatabaseContractEI.KamusColumns.DETAIL_EI;
import static com.widiyanto.kamusbahasa.Database.DatabaseContractEI.KamusColumns.TEXT_EI;
import static com.widiyanto.kamusbahasa.Database.DatabaseContractEI.TABLE_EI;
import static com.widiyanto.kamusbahasa.Database.DatabaseContractIE.KamusColumns.DETAIL_IE;
import static com.widiyanto.kamusbahasa.Database.DatabaseContractIE.KamusColumns.TEXT_IE;
import static com.widiyanto.kamusbahasa.Database.DatabaseContractIE.TABLE_IE;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static String DATABASE_NAME = "dbkamus";

    private  static final int DATABASE_VERSION = 1;

    public static String CREATE_TABLE_EI = "create table "+ TABLE_EI
            +" ("+_ID+" integer primary key autoincrement, "
            + TEXT_EI +" text not null, "
            + DETAIL_EI +" text not null);";

    public static String CREATE_TABLE_IE = "create table "+ TABLE_IE
            +" ("+_ID+" integer primary key autoincrement, "
            + TEXT_IE +" text not null, "
            + DETAIL_IE +" text not null);";

    public DatabaseHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_EI);
        db.execSQL(CREATE_TABLE_IE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_EI);
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_IE);
        onCreate(db);
    }
}

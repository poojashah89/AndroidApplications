package assignments.sjsu.poojashah.datastorage;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by poojashah on 4/1/17.
 */

public class DataController {
    public static final String TABLE_NAME="items";
    public static final String DATABASE_NAME="datastorage.db";
    public static final int DATABASE_VERSION=4;
    public static final String TABLE_CREATE="create table items (item_name text not null, item_Description text, item_price text, item_review text);";

    DataBaseHelper dbHelper;
    Context context;
    SQLiteDatabase db;

    public DataController(Context context)
    {
        this.context=context;
        dbHelper=new DataBaseHelper(context);
    }

    public DataController open()
    {
        db=dbHelper.getWritableDatabase();
        return this;
    }

    public void close()
    {
        dbHelper.close();
    }

    public long insert(String item_name, String item_Description, String item_price, String item_review)
    {
        ContentValues content=new ContentValues();
        content.put("item_name", item_name);
        content.put("item_Description", item_Description);
        content.put("item_price", item_price);
        content.put("item_review", item_review);
        return db.insertOrThrow(TABLE_NAME, null, content);
    }

    public Cursor retrieveAll()
    {
        return db.query(TABLE_NAME, new String[]{"item_name", "item_Description", "item_price", "item_review" }, null, null, null, null, null);
    }

    public Cursor retrieve(String searchstring)
    {
        return db.query(TABLE_NAME, new String[]{"item_name", "item_Description", "item_price", "item_review" }, "item_name like " + "'%"+ searchstring+"%'", null, null, null, null);
    }

    private static class DataBaseHelper extends SQLiteOpenHelper
    {

        public DataBaseHelper(Context context)
        {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            // TODO Auto-generated method stub
            try
            {
                db.execSQL(TABLE_CREATE);
            }
            catch(SQLiteException e)
            {
                e.printStackTrace();
            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            // TODO Auto-generated method stub
            db.execSQL("DROP TABLE IF EXISTS Msg_Table");
            onCreate(db);
        }

    }
}

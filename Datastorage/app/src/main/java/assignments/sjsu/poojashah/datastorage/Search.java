package assignments.sjsu.poojashah.datastorage;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class Search extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
    }

    public void searchItem(View view) {
        DataController dataController=new DataController(getBaseContext());
        dataController.open();

        EditText searchitem = (EditText) findViewById(R.id.search);
        TextView res =  (TextView)findViewById(R.id.result);
        Cursor mCursor = dataController.retrieve(searchitem.getText().toString());
        res.append("Found items " + mCursor.getCount() + "\n--------------------------------------------\n");
            
        if (mCursor.moveToFirst()) {
            do {
                String name = mCursor.getString(mCursor.getColumnIndexOrThrow("item_name"));
                String desc = mCursor.getString(mCursor.getColumnIndexOrThrow("item_Description"));
                String price= mCursor.getString(mCursor.getColumnIndexOrThrow("item_price"));
                String review = mCursor.getString(mCursor.getColumnIndexOrThrow("item_review"));

                res.append("Item : " + name +  " \n" + desc + " \nPrice: " + price + "$ Review: " + review+ "*\n--------------------------------------------\n");
            } while (mCursor.moveToNext());
        }
        if (mCursor != null && !mCursor.isClosed()) {
            mCursor.close();
        }
    }

    public void cancel(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}

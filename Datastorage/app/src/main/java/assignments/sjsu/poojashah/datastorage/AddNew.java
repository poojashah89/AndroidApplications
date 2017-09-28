package assignments.sjsu.poojashah.datastorage;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class AddNew extends AppCompatActivity {

    private EditText name, desc, amt, review = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new);
        name=(EditText)findViewById(R.id.itemname);
        desc=(EditText)findViewById(R.id.itemdescription);
        amt=(EditText)findViewById(R.id.amt);
        review=(EditText)findViewById(R.id.review);


    }

    public void addItems(View view){
        String itemname=name.getText().toString();
        String description=desc.getText().toString();
        String price=amt.getText().toString();
        String reviewVal=review.getText().toString();

        DataController dataController=new DataController(getBaseContext());
        dataController.open();

        long retValue= dataController.insert(itemname, description, price, reviewVal);

        dataController.close();
        if(retValue!=-1)
        {
            Context context = getApplicationContext();
            int duration= Toast.LENGTH_LONG;
            Toast.makeText(context, "Record saved successfully!", duration).show();
        }

    }

    public void cancel(View view){
        name.setText("");
        desc.setText("");
        amt.setText("");
        review.setText("");

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}

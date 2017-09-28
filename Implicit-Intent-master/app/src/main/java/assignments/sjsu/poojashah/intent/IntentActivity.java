package assignments.sjsu.poojashah.intent;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.view.View;
import android.content.Intent;
import android.content.Context;

public class IntentActivity extends AppCompatActivity {

    private EditText textURL;
    private EditText textPhone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intent);
        textURL = (EditText)findViewById(R.id.enterURL);
        textPhone = (EditText) findViewById(R.id.enterPhone);
    }

    /**
     * Launch URL in web browser
     * @param view
     */
    public void launchUrl(View view) {
        String parseUrl = textURL.getText().toString();

        if(!parseUrl.startsWith("http://") && !parseUrl.startsWith("https://") ){
            parseUrl = "http://"+ parseUrl;
        }

        Uri Url=Uri.parse(parseUrl);
        Intent intent = new Intent(Intent.ACTION_VIEW,Url);

        String title = "Select a browser";
        Intent chooser = Intent.createChooser(intent, title);

        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(chooser);
        }
    }

    /**
     * Ring Phone Call
     * @param view
     */
    public void phoneCall(View view) {
        Uri number=Uri.parse("tel:"+ textPhone.getText().toString());
        Intent intent = new Intent(Intent.ACTION_DIAL, number);
        String title = "Select an application to call";
        Intent chooser = Intent.createChooser(intent, title);

        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(chooser);
        }
    }

    /**
     * Close application
     * @param view
     */
    public void closeApp(View view) {
        IntentActivity.this.finish();
    }
}

package proyect.my.of.example.miyuki.xmlpull;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    Button button;
    XmlPullParserFactory parserFactory;
    XmlPullParser parser;
    InputStream istream;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);
        button = findViewById(R.id.button);

        try {
            istream = getAssets().open("data.xml");
        } catch (IOException e) {
            e.printStackTrace();
        }


        try {
            callparser();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        }


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    ProcessParser();
                } catch (XmlPullParserException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void callparser() throws XmlPullParserException {

        parserFactory = XmlPullParserFactory.newInstance();
        parser = parserFactory.newPullParser();
        parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
        parser.setInput(istream, null);



    }

  public void ProcessParser() throws XmlPullParserException, IOException {
        String tag = "", text = "";


        int event = parser.getEventType();
      //  while (event != XmlPullParser.END_DOCUMENT) {
            tag = parser.getName();
            switch (event) {
                case XmlPullParser.START_TAG:
                    if (tag.equals("user"))

                        break;
                case XmlPullParser.TEXT:
                    text = parser.getText();
                    break;
                case XmlPullParser.END_TAG:
                    switch (tag) {
                        case "name":
                            break;
                        case "designation":
                            break;
                        case "location":
                            break;
                    }
                    break;
            }

        String resul = tag + "\n " + text;
            textView.setText(resul);

            event = parser.next();
        //}
    }
}

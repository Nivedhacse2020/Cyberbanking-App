package com.example.encrypt;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

class Decoder extends AppCompatActivity {

    EditText etdec;
    TextView dectv;
    ClipboardManager cplboard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_decoder);

        // link the edittext and textview with its id
        etdec = findViewById(R.id.etVar1);
        dectv = findViewById(R.id.tvVar2);

        // create a clipboard manager variable to copy text
        cplboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
    }

    // onClick function of encrypt button
    public void dec(View view) {
        // get code from edittext
        String temp = etdec.getText().toString();
        Log.e("dec", "text - " + temp);

        // pass the string to the decryption algorithm
        // and get the decrypted text
        String rv = Decoder.decode(temp);

        // set the text to the edit text for display
        dectv.setText(rv);
        Log.e("dec", "text - " + rv);
    }

    public static int decode(String temp) {
        String binaryString = dectv.getText().toString();

        int baseVal = 2;
        int expVal = 0;
        int powVal = 1;
        int decVal = 0;
        int i = binaryString.length() - 1;
        if (binaryString.charAt(i) == '1') {
            for (int j = 0; j < expVal; j++) {
                powVal *= baseVal;
            }
            decVal += powVal;
            powVal = 1;
        }
        i--;
        expVal++;
        return decVal;
    }



    // onClick function of copy text button
    public void cpl(View view) {

        // get the string from the textview and trim all spaces
        String data = dectv.getText().toString().trim();

        // check if the textview is not empty
        if (!data.isEmpty()) {

            // copy the text in the clip board
            ClipData temp = ClipData.newPlainText("text", data);

            // display message that the text has been copied
            Toast.makeText(this, "Copied", Toast.LENGTH_SHORT).show();
        }
    }
}
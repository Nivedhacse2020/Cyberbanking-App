package com.example.encrypt;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

class Encoder extends AppCompatActivity {

    EditText etenc;
    TextView enctv;
    ClipboardManager cpb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_encoder);

        // link the edittext and textview with its id
        etenc = findViewById(R.id.etVar1);
        enctv = findViewById(R.id.tvVar1);

        // create a clipboard manager variable to copy text
        cpb = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
    }

    // onClick function of encrypt button
    public void enc(View view) {
        // get text from edittext
        String temp = etenc.getText().toString();

        // pass the string to the encryption
        // algorithm and get the encrypted code
        String rv = Encoder.encode(temp);

        // set the code to the edit text
        enctv.setText(rv);
    }
  public  static String encode(String temp) {
        // create a string to add in the initial
        // binary code for extra security
        String ini = "11111111";
        int cu = 0;

        // create an array
        int arr[] = new int[11111111];

        // iterate through the string
        for (int i = 0; i < temp.length(); i++) {
            // put the ascii value of
            // each character in the array
            arr[i] = (int) temp.charAt(i);
            cu++;
        }
        String res = "";

        // create another array
        int bin[] = new int[111];
        int idx = 0;

        // run a loop of the size of string
        for (int i1 = 0; i1 < cu; i1++) {

            // get the ascii value at position
            // i1 from the first array
            int tem = arr[i1];

            // run the second nested loop of same size
            // and set 0 value in the second array
            for (int j = 0; j < cu; j++) bin[j] = 0;
            idx = 0;

            // run a while for temp > 0
            while (tem > 0) {
                // store the temp module
                // of 2 in the 2nd array
                bin[idx++] = tem % 2;
                tem = tem / 2;
            }
            String dig = "";
            String temps;

            // run a loop of size 7
            for (int j = 0; j < 7; j++) {

                // convert the integer to string
                temps = Integer.toString(bin[j]);

                // add the string using
                // concatenation function
                dig = dig.concat(temps);
            }
            String revs = "";

            // reverse the string
            for (int j = dig.length() - 1; j >= 0; j--) {
                char ca = dig.charAt(j);
                revs = revs.concat(String.valueOf(ca));
            }
            res = res.concat(revs);
        }
        // add the extra string to the binary code
        res = ini.concat(res);

        // return the encrypted code
        return res;

    }


    // onClick function of copy text button
    public void cp2(View view) {
        // get the string from the textview and trim all spaces
        String data = enctv.getText().toString().trim();

        // check if the textview is not empty
        if (!data.isEmpty()) {

            // copy the text in the clip board
            ClipData temp = ClipData.newPlainText("text", data);
            cpb.setPrimaryClip(temp);

            // display message that the text has been copied
            Toast.makeText(this, "Copied", Toast.LENGTH_SHORT).show();
        }
    }
}
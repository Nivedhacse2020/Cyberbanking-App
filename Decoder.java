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

    public static String decode(String temp) {
        String invalid = "Invalid Code";

        // create the same initial
        // string as in encode class
        String ini = "11111111";
        Boolean flag = true;

        // run a loop of size 8
        for (int i = 0; i < 8; i++) {
            // check if the initial value is same
            if (ini.charAt(i) != temp.charAt(i)) {
                flag = false;
                break;
            }
        }
        String val = "";

        // reverse the encrypted code
        for (int i = 8; i < temp.length(); i++) {
            char ch = temp.charAt(i);
            val = val.concat(String.valueOf(ch));
        }

        // create a 2 dimensional array
        int arr[][] = new int[11101][8];
        int ind1 = -1;
        int ind2 = 0;

        // run a loop of size of the encrypted code
        for (int i = 0; i < val.length(); i++) {

            // check if the position of the
            // string if divisible by 7
            if (i % 7 == 0) {
                // start the value in other
                // column of the 2D array
                ind1++;
                ind2 = 0;
                char ch = val.charAt(i);
                arr[ind1][ind2] = ch - '0';
                ind2++;
            } else {
                // otherwise store the value
                // in the same column
                char ch = val.charAt(i);
                arr[ind1][ind2] = ch - '0';
                ind2++;
            }
        }
        // create an array
        int num[] = new int[11111];
        int nind = 0;
        int tem = 0;
        int cu = 0;

        // run a loop of size of the column
        for (int i = 0; i <= ind1; i++) {
            cu = 0;
            tem = 0;
            // convert binary to decimal and add them
            // from each column and store in the array
            for (int j = 6; j >= 0; j--) {
                int tem1 = (int) Math.pow(2, cu);
                tem += (arr[i][j] * tem1);
                cu++;
            }
            num[nind++] = tem;
        }
        String ret = "";
        char ch;
        // convert the decimal ascii number to its
        // char value and add them to form a decrypted
        // string using conception function
        for (int i = 0; i < nind; i++) {
            ch = (char) num[i];
            ret = ret.concat(String.valueOf(ch));
        }
        Log.e("dec", "text 11 - " + ret);

        // check if the encrypted code was
        // generated for this algorithm
        if (val.length() % 7 == 0 && flag == true) {
            // return the decrypted code
            return ret;
        } else {
            // otherwise return an invalid message
            return invalid;
        }
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
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
        int temp ;

        // pass the string to the encryption
        // algorithm and get the encrypted code
        int rv = Encoder.encode(temp);

        // set the code to the edit text
        enctv.setText(rv);
    }
  public  static int encode(int temp) {
      int dec = temp;
      int quo, rem, i;
      int bin[] = new int[100];
      quo = dec;
      i = 0;
      while (quo != 0) {
          rem = quo % 2;
          quo = quo / 2;
          bin[i] = rem;
          i++;
      }
      for (int j = i - 1; j >= 0; j--) {
          return bin[j];
      }
  }
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
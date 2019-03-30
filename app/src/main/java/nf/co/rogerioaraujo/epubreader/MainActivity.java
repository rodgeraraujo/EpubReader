package nf.co.rogerioaraujo.epubreader;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Button button;
    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                editText = findViewById(R.id.editText);
                String title = editText.getText().toString();

                Log.d("TITLE", title);

                Intent intent = new Intent(MainActivity.this, EpubReaderActivity.class);
                intent.putExtra("title", title);
                startActivity(intent);
            }
        });
    }
}

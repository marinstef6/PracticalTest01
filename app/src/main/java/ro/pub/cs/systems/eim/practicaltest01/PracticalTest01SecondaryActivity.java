package ro.pub.cs.systems.eim.practicaltest01;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class PracticalTest01SecondaryActivity extends AppCompatActivity {

    private EditText numberofClick;
    private Button ok, cancel;
    private ButtonClickListener buttonClickListener = new ButtonClickListener();

    private class ButtonClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            if (view.getId() == R.id.ok_button)
                setResult(RESULT_OK, null);
            else if (view.getId() == R.id.cancel_button)
                setResult(RESULT_CANCELED, null);
            finish();
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //EdgeToEdge.enable(this);
        setContentView(R.layout.activity_practical_test01_secondary);
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
//            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
//            return insets;
//        });
        numberofClick = (EditText) findViewById(R.id.editTextText2);
        Intent intent = getIntent();
        if (intent != null && intent.getExtras().containsKey(Constants.NUMBER_OF_CLICKS)) {
            int numberOfClicks = intent.getIntExtra(Constants.NUMBER_OF_CLICKS, -1);
            numberofClick.setText(String.valueOf(numberOfClicks));
        }

        ok = (Button) findViewById(R.id.ok_button);
        ok.setOnClickListener(buttonClickListener);
        cancel = (Button) findViewById(R.id.cancel_button);
        ok.setOnClickListener(v -> {
            Intent resultIntent = new Intent();
            resultIntent.putExtra("pressedButton", "result");
            setResult(RESULT_OK, resultIntent);
            finish();
        });

        cancel.setOnClickListener(v -> {
            Intent resultIntent = new Intent();
            resultIntent.putExtra("pressedButton", "Cancel");
            setResult(RESULT_CANCELED, resultIntent);
            finish();
        });
    }
}
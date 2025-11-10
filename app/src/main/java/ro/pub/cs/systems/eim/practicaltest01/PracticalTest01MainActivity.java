package ro.pub.cs.systems.eim.practicaltest01;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class PracticalTest01MainActivity extends AppCompatActivity {

    private EditText text_left;
    private EditText text_right;
    private Button press_me_too;
    private Button press_me;
    private Button navigate_to_secondary_activity;
   // private ButtonClickListener buttonClickListener = new ButtonClickListener();
    private Button NavigateToSecondaryActivity;
    int left=0;
    int right=0;
    int textsum=0;
    private class ButtonClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {

            if (view.getId() == R.id.navigate_to_secondary_activity_button) {
                textsum = left+right;
                Intent intent = new Intent(getApplicationContext(), PracticalTest01SecondaryActivity.class);
                //pentru ca mai sus suma mea este int altfel fac conversie ( intent.putExtra("NUMBER_OF_CLICKS", Integer.parseInt(text1.getText().toString()));)
                intent.putExtra(Constants.NUMBER_OF_CLICKS, textsum);
                //cere un int aceasta metoda de aia trebuie sa am secondary cu int
                startActivityForResult(intent, Constants.SECONDARY_ACTIVITY_REQUEST_CODE);
            }
            if (view.getId() == R.id.left_button) {
                left++;
                text_left.setText(String.valueOf(left));
            } else if(view.getId() == R.id.right_button) {
                right++;
                text_right.setText(String.valueOf(right));
            }
            }

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // EdgeToEdge.enable(this);
        setContentView(R.layout.activity_practical_test01_activity_main);
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
//            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
//            return insets;
//        });
        text_left = findViewById(R.id.left_edit_text);
        text_right = findViewById(R.id.right_edit_text);
        press_me = findViewById(R.id.left_button);
        press_me_too = findViewById(R.id.right_button);
        navigate_to_secondary_activity = findViewById(R.id.navigate_to_secondary_activity_button);
        press_me.setOnClickListener(new ButtonClickListener());
        press_me_too.setOnClickListener(new ButtonClickListener());
        navigate_to_secondary_activity.setOnClickListener((new ButtonClickListener()));
        text_left.setText("0");
        text_right.setText("0");
        if (savedInstanceState != null) {
            if (savedInstanceState.containsKey(Constants.right_VALUE)) {
                text_right.setText(savedInstanceState.getString(Constants.right_VALUE));
            } else {
                text_right.setText(String.valueOf("0"));
            }
            if (savedInstanceState.containsKey(Constants.Left_VALUE)) {
                text_left.setText(savedInstanceState.getString(Constants.Left_VALUE));
            } else {
                text_left.setText(String.valueOf("0"));
            }
        }
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        if (savedInstanceState.containsKey(Constants.right_VALUE)) {
            text_right.setText(savedInstanceState.getString(Constants.right_VALUE));
        }
        if (savedInstanceState.containsKey(Constants.Left_VALUE)) {
            text_left.setText(savedInstanceState.getString(Constants.Left_VALUE));
        }
        if (savedInstanceState.containsKey(Constants.CLICK_COUNT_1)) {
            right = savedInstanceState.getInt(Constants.CLICK_COUNT_1);
        }
        if (savedInstanceState.containsKey(Constants.CLICK_COUNT)) {
            left = savedInstanceState.getInt(Constants.CLICK_COUNT);
        }

    }
    //pentru restaurare
    protected void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);

        savedInstanceState.putString(Constants.right_VALUE, text_right.getText().toString());
        savedInstanceState.putString(Constants.Left_VALUE, text_left.getText().toString());
        savedInstanceState.putInt(Constants.CLICK_COUNT, left);
        savedInstanceState.putInt(Constants.CLICK_COUNT_1, right);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == Constants.SECONDARY_ACTIVITY_REQUEST_CODE) {

            if (data != null && data.hasExtra("pressedButton")) {
                String buttonPressed = data.getStringExtra("pressedButton");

                Toast.makeText(this,
                        "În SecondActivity ai apăsat: " + buttonPressed,
                        Toast.LENGTH_LONG).show();
            }
        }
    }
}

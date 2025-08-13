package com.elasados.steaks.activities;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.elasados.steaks.R;
import android.widget.ImageView;
import android.os.Handler;

public class TrackingActivity extends AppCompatActivity {
    private Handler handler = new Handler();
    private int step = 0;
    private ImageView img;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tracking);
        img = findViewById(R.id.imgMapPlaceholder);
        // Simple animation simulation could be added here
        handler.postDelayed(() -> img.setAlpha(1f), 500);
    }

    @Override protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacksAndMessages(null);
    }
}

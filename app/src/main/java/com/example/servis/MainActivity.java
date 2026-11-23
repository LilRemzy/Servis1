package com.example.servis;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.core.view.WindowInsetsControllerCompat;

public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_DEVICE = "com.example.servis.EXTRA_DEVICE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        WindowInsetsControllerCompat insetsController = WindowCompat.getInsetsController(getWindow(), getWindow().getDecorView());
        if (insetsController != null) {
            insetsController.setAppearanceLightStatusBars(false);
        }

        View headerBar = findViewById(R.id.header_bar);
        View scrollView = findViewById(R.id.scroll_view);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());

            if (headerBar != null) {
                headerBar.setPadding(
                        headerBar.getPaddingLeft(),
                        systemBars.top,
                        headerBar.getPaddingRight(),
                        headerBar.getPaddingBottom()
                );
            }

            if (scrollView != null) {
                scrollView.setPadding(
                        scrollView.getPaddingLeft(),
                        scrollView.getPaddingTop(),
                        scrollView.getPaddingRight(),
                        systemBars.bottom
                );
            }

            return insets;
        });

        LinearLayout cardComputer = findViewById(R.id.card_computer);
        LinearLayout cardPhone = findViewById(R.id.card_phone);
        LinearLayout cardPrinter = findViewById(R.id.card_printer);

        cardComputer.setOnClickListener(v -> openReservationActivity(getString(R.string.device_computer_title)));
        cardPhone.setOnClickListener(v -> openReservationActivity(getString(R.string.device_phone_title)));
        cardPrinter.setOnClickListener(v -> openReservationActivity(getString(R.string.device_printer_title)));
    }

    private void openReservationActivity(String deviceName) {
        Intent intent = new Intent(MainActivity.this, RezervacijaActivity.class);
        intent.putExtra(EXTRA_DEVICE, deviceName);
        startActivity(intent);
    }
}
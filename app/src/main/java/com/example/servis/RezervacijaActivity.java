package com.example.servis;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.core.view.WindowInsetsControllerCompat;

import com.google.android.material.button.MaterialButton;

import java.util.Calendar;
import java.util.Locale;

public class RezervacijaActivity extends AppCompatActivity {

    private String selectedDevice = "";
    private String selectedServiceType = "";
    private String selectedContactMethod = "";

    private EditText etName;
    private EditText etEmail;
    private EditText etPhone;
    private Spinner spinnerServiceType;

    private TextView tvHeaderTitle;
    private TextView tvSelectedDate;
    private TextView tvSelectedTime;

    private RadioGroup rgContactMethod;

    private Calendar selectedCalendar;
    private boolean isDateSelected = false;
    private boolean isTimeSelected = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_rezervacija);

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

        if (getIntent() != null && getIntent().hasExtra(MainActivity.EXTRA_DEVICE)) {
            selectedDevice = getIntent().getStringExtra(MainActivity.EXTRA_DEVICE);
        }
        if (TextUtils.isEmpty(selectedDevice)) {
            selectedDevice = getString(R.string.device_computer_title);
        }

        initViews();
        setupHeader();
        setupSpinner();
        setupDateTimePickers();
        setupRadioGroup();
        setupButtons();
    }

    private void initViews() {
        tvHeaderTitle = findViewById(R.id.tv_header_title);
        etName = findViewById(R.id.et_name);
        etEmail = findViewById(R.id.et_email);
        etPhone = findViewById(R.id.et_phone);
        spinnerServiceType = findViewById(R.id.spinner_service_type);

        tvSelectedDate = findViewById(R.id.tv_selected_date);
        tvSelectedTime = findViewById(R.id.tv_selected_time);

        rgContactMethod = findViewById(R.id.rg_contact_method);

        selectedCalendar = Calendar.getInstance();
    }

    private void setupHeader() {
        ImageView btnBack = findViewById(R.id.btn_back);
        btnBack.setOnClickListener(v -> finish());

        String headerText = getString(R.string.reservation_title_prefix) + selectedDevice;
        tvHeaderTitle.setText(headerText);
    }

    private void setupSpinner() {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this,
                R.array.service_types,
                android.R.layout.simple_spinner_item
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerServiceType.setAdapter(adapter);

        spinnerServiceType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedServiceType = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                selectedServiceType = "";
            }
        });
    }

    private void setupDateTimePickers() {
        LinearLayout btnSelectDate = findViewById(R.id.btn_select_date);
        LinearLayout btnSelectTime = findViewById(R.id.btn_select_time);

        btnSelectDate.setOnClickListener(v -> showDatePickerDialog());
        btnSelectTime.setOnClickListener(v -> showTimePickerDialog());
    }

    private void showDatePickerDialog() {
        Calendar now = Calendar.getInstance();
        int year = isDateSelected ? selectedCalendar.get(Calendar.YEAR) : now.get(Calendar.YEAR);
        int month = isDateSelected ? selectedCalendar.get(Calendar.MONTH) : now.get(Calendar.MONTH);
        int day = isDateSelected ? selectedCalendar.get(Calendar.DAY_OF_MONTH) : now.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this,
                (view, year1, monthOfYear, dayOfMonth) -> {
                    selectedCalendar.set(Calendar.YEAR, year1);
                    selectedCalendar.set(Calendar.MONTH, monthOfYear);
                    selectedCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                    isDateSelected = true;

                    String formattedDate = String.format(Locale.getDefault(), "%02d. %02d. %d", dayOfMonth, monthOfYear + 1, year1);
                    tvSelectedDate.setText(formattedDate);
                },
                year, month, day
        );

        datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
        datePickerDialog.show();
    }

    private void showTimePickerDialog() {
        Calendar now = Calendar.getInstance();
        int hour = isTimeSelected ? selectedCalendar.get(Calendar.HOUR_OF_DAY) : now.get(Calendar.HOUR_OF_DAY);
        int minute = isTimeSelected ? selectedCalendar.get(Calendar.MINUTE) : now.get(Calendar.MINUTE);

        TimePickerDialog timePickerDialog = new TimePickerDialog(
                this,
                (view, hourOfDay, minuteOfHour) -> {
                    selectedCalendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
                    selectedCalendar.set(Calendar.MINUTE, minuteOfHour);
                    isTimeSelected = true;

                    String formattedTime = String.format(Locale.getDefault(), "%02d:%02d", hourOfDay, minuteOfHour);
                    tvSelectedTime.setText(formattedTime);
                },
                hour, minute, true
        );
        timePickerDialog.show();
    }

    private void setupRadioGroup() {
        rgContactMethod.setOnCheckedChangeListener((group, checkedId) -> {
            if (checkedId == R.id.rb_email) {
                selectedContactMethod = getString(R.string.radio_email);
            } else if (checkedId == R.id.rb_phone) {
                selectedContactMethod = getString(R.string.radio_phone);
            } else if (checkedId == R.id.rb_sms) {
                selectedContactMethod = getString(R.string.radio_sms);
            }

            if (!TextUtils.isEmpty(selectedContactMethod)) {
                String toastMessage = String.format(getString(R.string.toast_contact_method), selectedContactMethod);
                Toast.makeText(RezervacijaActivity.this, toastMessage, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setupButtons() {
        MaterialButton btnDialService = findViewById(R.id.btn_dial_service);
        MaterialButton btnSubmit = findViewById(R.id.btn_submit);

        btnDialService.setOnClickListener(v -> {
            Intent dialIntent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:080123456"));
            startActivity(dialIntent);
        });

        btnSubmit.setOnClickListener(v -> validateAndSubmitForm());
    }

    private void validateAndSubmitForm() {
        String name = etName.getText().toString().trim();
        String email = etEmail.getText().toString().trim();
        String phone = etPhone.getText().toString().trim();

        if (TextUtils.isEmpty(name)) {
            etName.setError(getString(R.string.error_name_required));
            etName.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(email)) {
            etEmail.setError(getString(R.string.error_email_required));
            etEmail.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            etEmail.setError(getString(R.string.error_email_invalid));
            etEmail.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(phone)) {
            etPhone.setError(getString(R.string.error_phone_required));
            etPhone.requestFocus();
            return;
        }

        if (!isDateSelected) {
            Toast.makeText(this, getString(R.string.error_date_required), Toast.LENGTH_SHORT).show();
            return;
        }

        Calendar todayStart = Calendar.getInstance();
        todayStart.set(Calendar.HOUR_OF_DAY, 0);
        todayStart.set(Calendar.MINUTE, 0);
        todayStart.set(Calendar.SECOND, 0);
        todayStart.set(Calendar.MILLISECOND, 0);

        if (selectedCalendar.before(todayStart)) {
            Toast.makeText(this, getString(R.string.error_date_past), Toast.LENGTH_SHORT).show();
            return;
        }

        if (!isTimeSelected) {
            Toast.makeText(this, getString(R.string.error_time_required), Toast.LENGTH_SHORT).show();
            return;
        }

        if (rgContactMethod.getCheckedRadioButtonId() == -1) {
            Toast.makeText(this, getString(R.string.error_contact_method_required), Toast.LENGTH_SHORT).show();
            return;
        }

        showConfirmationDialog(name);
    }

    private void showConfirmationDialog(String name) {
        String dateStr = tvSelectedDate.getText().toString();
        String timeStr = tvSelectedTime.getText().toString();

        String dialogMessage = String.format(
                getString(R.string.dialog_message_format),
                selectedDevice,
                selectedServiceType,
                name,
                dateStr,
                timeStr,
                selectedContactMethod
        );

        new AlertDialog.Builder(this)
                .setTitle(getString(R.string.dialog_title))
                .setMessage(dialogMessage)
                .setPositiveButton(getString(R.string.dialog_btn_confirm), (dialog, which) -> {
                    String toastText = String.format(getString(R.string.toast_reservation_success), selectedDevice);
                    Toast.makeText(RezervacijaActivity.this, toastText, Toast.LENGTH_LONG).show();
                })
                .setNegativeButton(getString(R.string.dialog_btn_cancel), (dialog, which) -> {
                    Toast.makeText(RezervacijaActivity.this, getString(R.string.toast_reservation_cancelled), Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                })
                .show();
    }
}
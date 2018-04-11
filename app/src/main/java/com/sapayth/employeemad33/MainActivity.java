package com.sapayth.employeemad33;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private android.widget.LinearLayout mBodyLayout;
    private EditText nameET, ageEt, phoneET;
    private TextView mOfficeStartTV;
    private TextView mOfficeEndTV;
    private ImageView mCalenderIV;
    private EditText mDobDateET;
    private EditText mDobMonthET;
    private EditText mDobYearET;
    private List<String> skills = new ArrayList<String>();
    RadioGroup genderRG;
    RadioGroup employeeTypeRG;
    private String gender = "Male";
    private String mDateOfBirth = "";
    private String mOfficeStartTime = "";
    private String mOfficeEndTime = "";
    private String employeeType = "Base Salaried Employee";

    AlertDialog.Builder dialog = null;

    private boolean isLoggedIn = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mBodyLayout = findViewById(R.id.bodyLayout);
        nameET = findViewById(R.id.empNameInput);
        ageEt = findViewById(R.id.empAgeInput);
        phoneET = findViewById(R.id.empPhoneInput);
        genderRG  = findViewById(R.id.genderRG);
        employeeTypeRG = findViewById(R.id.employeeTypeRG);
        mCalenderIV = findViewById(R.id.calenderImageView);
        mDobDateET = findViewById(R.id.dobDateTextView);
        mDobMonthET = findViewById(R.id.dobMonthTextView);
        mDobYearET = findViewById(R.id.dobYearTextView);
        mOfficeStartTV = findViewById(R.id.officeFromTextView);
        mOfficeEndTV = findViewById(R.id.officeToTextView);

        genderRG.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton rb = findViewById(i);
                gender = rb.getText().toString();
            }
        });

        employeeTypeRG.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton rb = findViewById(i);
                employeeType = rb.getText().toString();
            }
        });

        mCalenderIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(MainActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                                mDobDateET.setText(dayOfMonth + "");
                                mDobMonthET.setText((month + 1) + "");
                                mDobYearET.setText(year + "");

                                mDateOfBirth = dayOfMonth + "/" + month + "/" + year;
                            }
                        },
                        1990, 1, 1);

                datePickerDialog.show();
            }
        });

        mOfficeStartTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog timePickerDialog = new TimePickerDialog(MainActivity.this,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                mOfficeStartTime = getAmPmTime(hourOfDay, minute);
                                mOfficeStartTV.setText("From: " + mOfficeStartTime);
                            }
                        }
                        ,
                        9, 00, false);
                timePickerDialog.show();
            }
        });

        mOfficeEndTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog timePickerDialog = new TimePickerDialog(MainActivity.this,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                mOfficeEndTime = getAmPmTime(hourOfDay, minute);
                                mOfficeEndTV.setText("To: " + mOfficeEndTime);
                            }
                        }
                        ,
                        9, 0, false);
                timePickerDialog.show();
            }
        });
    }

    private String getAmPmTime(int hourOfDay, int minute) {
        String minutes = "";
        if (minute == 0) {
            minutes = "00";
        } else {
            minutes = minute + "";
        }

        if (hourOfDay >= 12) {
            hourOfDay -= 12;
            return hourOfDay + ":" + minutes + "PM";
        } else {
            return hourOfDay + ":" + minutes + "AM";
        }
    }

    public void registerEmployee(View view) {
        if (isLoggedIn) {
            String name = nameET.getText().toString();
            String age = ageEt.getText().toString();
            String phone = phoneET.getText().toString();

            Employee anEmployee = new Employee(name, age, phone, gender, employeeType, skills);
            anEmployee.setDateOfBirth(mDateOfBirth);
            anEmployee.setOfficeStartTime(mOfficeStartTime);
            anEmployee.setOfficeEndTime(mOfficeEndTime);

            Employee.addEmployeeToList(anEmployee);
            // Explicit Intent
            Intent intent = new Intent(this, EmployeeListActivity.class);
            startActivity(intent);
        } else {
            showLoginDialog();
        }
    }

    public void selectSkill(View view) {
        CheckBox cb = (CheckBox) view;

        boolean status = cb.isChecked();

        if(status) {
            skills.add(cb.getText().toString());
        } else {
            skills.remove(cb.getText().toString());
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int menuId = item.getItemId();

        switch (menuId) {
            case R.id.item_login:
                showLoginDialog();
                break;
            case R.id.item_logout:
                isLoggedIn = false;
                Toast.makeText(this, "Logged Out", Toast.LENGTH_SHORT).show();
                break;
            case R.id.item_red:
                mBodyLayout.setBackgroundResource(R.color.red);
                break;
            case R.id.item_green:
                mBodyLayout.setBackgroundResource(R.color.green);
                break;
            case R.id.item_blue:
                mBodyLayout.setBackgroundResource(R.color.blue);
                break;
            case R.id.item_default:
                mBodyLayout.setBackgroundResource(0);
                break;
        }
        return true;
    }

    private void showLoginDialog() {
        LayoutInflater inflater = LayoutInflater.from(this);
        dialog = new AlertDialog.Builder(this);
        dialog.setCancelable(false);
        dialog.setTitle("Login");
        dialog.setIcon(R.mipmap.ic_launcher);

        View v = inflater.inflate(R.layout.login_dialog_layout, null);
        dialog.setView(v);

        final EditText emailET = v.findViewById(R.id.emailEditText);
        final EditText passwordET = v.findViewById(R.id.passwordEditText);
        Button loginBtn = v.findViewById(R.id.loginButton);
        final AlertDialog alertDialog = dialog.create();

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailET.getText().toString();
                String password = passwordET.getText().toString();

                if (email.equals(EmployeeUtils.Authentication.EMAIL) &&
                        password.equals(EmployeeUtils.Authentication.PASSWORD)) {

                    alertDialog.dismiss();
                    isLoggedIn = true;
                    Toast.makeText(MainActivity.this, "Logged In", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Wrong id/pass", Toast.LENGTH_SHORT).show();
                    Toast.makeText(MainActivity.this, "mail: test@testmail.com pass: 123456", Toast.LENGTH_SHORT).show();
                }
            }
        });

        alertDialog.show();
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        MenuItem login = menu.findItem(R.id.item_login);
        MenuItem logout = menu.findItem(R.id.item_logout);
        if (isLoggedIn) {
            login.setVisible(false);
            logout.setVisible(true);
        } else {
            logout.setVisible(false);
            login.setVisible(true);
        }

        return true;
    }
}

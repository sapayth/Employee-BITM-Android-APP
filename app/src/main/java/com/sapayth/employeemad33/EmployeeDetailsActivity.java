package com.sapayth.employeemad33;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class EmployeeDetailsActivity extends AppCompatActivity {
    Employee mAnEmployee;
    TextView mNameTV, mDobTV, mAgeTV, mPhoneTV, mSkillsTV, mGenderTV, mEmployeeTypeTV, mOfficeTimeTV;
    LinearLayout mShowPhoneLL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_details);

        mNameTV = findViewById(R.id.showName);
        mDobTV = findViewById(R.id.showDob);
        mAgeTV = findViewById(R.id.showAge);
        mPhoneTV = findViewById(R.id.showPhone);
        mSkillsTV = findViewById(R.id.showSkills);
        mGenderTV = findViewById(R.id.showGender);
        mEmployeeTypeTV = findViewById(R.id.showEmployeeType);
        mShowPhoneLL = findViewById(R.id.showPhoneLayout);
        mOfficeTimeTV = findViewById(R.id.showOfficeTime);

        Intent i = getIntent();
        mAnEmployee = (Employee) i.getSerializableExtra("emp");

        mNameTV.setText(mAnEmployee.getEmpName());
        mDobTV.setText(mAnEmployee.getDateOfBirth());
        mAgeTV.setText(mAnEmployee.getEmpAge());
        mPhoneTV.setText(mAnEmployee.getEmpPhone());
        String skills = TextUtils.join(", ", mAnEmployee.getSkills());
        mSkillsTV.setText(skills);
        mGenderTV.setText(mAnEmployee.getGender());
        mEmployeeTypeTV.setText(mAnEmployee.getEmployeeType());
        mOfficeTimeTV.setText("From " + mAnEmployee.getOfficeStartTime() + " to "
                + mAnEmployee.getOfficeEndTime());

        mShowPhoneLL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_CALL);
                intent.setData(Uri.parse("tel:" + mAnEmployee.getEmpPhone()));
                if (intent.resolveActivity(getPackageManager()) != null) {
                    if (ActivityCompat.checkSelfPermission(EmployeeDetailsActivity.this,
                            Manifest.permission.CALL_PHONE)
                            != PackageManager.PERMISSION_GRANTED) {
                        ActivityCompat.requestPermissions(EmployeeDetailsActivity.this,
                                new String[] {Manifest.permission.CALL_PHONE}, 100);
                        return;
                    }
                    startActivity(intent);
                } else {
                    Toast.makeText(EmployeeDetailsActivity.this,
                            "No component found", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}

package com.sapayth.employeemad33;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mobile App Develop on 4/11/2018.
 */

public class EmployeeAdapter extends ArrayAdapter<Employee> {
    private Context context;
    private List<Employee> employeeList;

    public EmployeeAdapter(@NonNull Context context, List<Employee> employeeList) {
        super(context, R.layout.employee_row, employeeList);
        this.context = context;
        this.employeeList = employeeList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.employee_row, parent, false);

        TextView nameTV = convertView.findViewById(R.id.employeeNameTextView);
        TextView ageTV = convertView.findViewById(R.id.employeeAgeTextView);
        TextView phoneTV = convertView.findViewById(R.id.employeePhoneTextView);

        Employee currentEmployee = getItem(position);
        nameTV.setText(currentEmployee.getEmpName());
        ageTV.setText(currentEmployee.getEmpAge());
        phoneTV.setText(currentEmployee.getEmpPhone());

        return convertView;
    }
}

package com.sapayth.employeemad33;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class EmployeeListActivity extends AppCompatActivity {

    private ListView mEmployeeLV;
    private TextView mEmptyTV;
    private Button mAddEmployeeBtn;
    private List<Employee> mEmployeeList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_list);

        mEmployeeLV = findViewById(R.id.employeeListView);
        mEmptyTV = findViewById(R.id.emptyListMsgTextView);
        mAddEmployeeBtn = findViewById(R.id.addEmployeeButton);

        mEmployeeList = Employee.getEmployeeList();

        if (mEmployeeList.size() == 0) {
            mEmptyTV.setVisibility(View.VISIBLE);
        }

        registerForContextMenu(mEmployeeLV);

        mEmployeeLV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Employee currentEmployee = mEmployeeList.get(i);
                Intent intent = new Intent(EmployeeListActivity.this, EmployeeDetailsActivity.class);
                intent.putExtra("emp", currentEmployee);
                startActivity(intent);
            }
        });

        mEmployeeLV.setAdapter(new EmployeeAdapter(this, mEmployeeList));
    }

    public void addNewEmployee(View view) {
        startActivity(new Intent(EmployeeListActivity.this, MainActivity.class));
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        getMenuInflater().inflate(R.menu.row_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item_edit:
                Toast.makeText(this, "Edit Clicked", Toast.LENGTH_SHORT).show();
                break;
            case R.id.item_delete:
                Toast.makeText(this, "Delete Clicked", Toast.LENGTH_SHORT).show();
                break;
        }

        return true;
    }
}

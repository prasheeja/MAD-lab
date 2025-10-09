package com.example.sqlite;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    DatabaseHelper myDb;
    EditText editTextId, editTextName, editTextEmail;
    Button btnAdd, btnView, btnUpdate, btnDelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myDb = new DatabaseHelper(this);

        editTextId = findViewById(R.id.editTextId);
        editTextName = findViewById(R.id.editTextName);
        editTextEmail = findViewById(R.id.editTextEmail);

        btnAdd = findViewById(R.id.btnAdd);
        btnView = findViewById(R.id.btnView);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnDelete = findViewById(R.id.btnDelete);

        addData();
        viewAll();
        updateData();
        deleteData();
    }

    public void addData() {
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isInserted = myDb.insertData(
                        editTextName.getText().toString(),
                        editTextEmail.getText().toString()
                );
                if (isInserted) {
                    Toast.makeText(MainActivity.this, "Data Inserted Successfully!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Data Insertion Failed", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void viewAll() {
        btnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor res = myDb.getAllData();
                if (res.getCount() == 0) {
                    showMessage("Error", "Nothing found");
                    return;
                }
                StringBuilder buffer = new StringBuilder();
                while (res.moveToNext()) {
                    buffer.append("ID: ").append(res.getString(0)).append("\n");
                    buffer.append("Name: ").append(res.getString(1)).append("\n");
                    buffer.append("Email: ").append(res.getString(2)).append("\n\n");
                }
                showMessage("Data", buffer.toString());
            }
        });
    }

    public void updateData() {
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = editTextId.getText().toString();
                if (id.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Please enter an ID", Toast.LENGTH_SHORT).show();
                    return;
                }
                boolean isUpdated = myDb.updateData(
                        id,
                        editTextName.getText().toString(),
                        editTextEmail.getText().toString()
                );
                if (isUpdated) {
                    Toast.makeText(MainActivity.this, "Data Updated Successfully!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Data Update Failed", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void deleteData() {
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = editTextId.getText().toString();
                if (id.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Please enter an ID", Toast.LENGTH_SHORT).show();
                    return;
                }
                Integer deletedRows = myDb.deleteData(id);
                if (deletedRows > 0) {
                    Toast.makeText(MainActivity.this, "Data Deleted Successfully!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Data Deletion Failed", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void showMessage(String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }
}

package com.example.latihanstorage;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {
    public static final String FILENAME = "fileku.txt";
    Button btnBuat,btnUbah, btnBaca, btnHapus;
    TextView txtBaca;
    File file;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnBuat = findViewById(R.id.btn_buat);
        btnUbah = findViewById(R.id.btn_ubah);
        btnBaca = findViewById(R.id.btn_baca);
        btnHapus = findViewById(R.id.btn_hapus);
        txtBaca = findViewById(R.id.txtHasil);
        file = new File(getFilesDir(), FILENAME);

        btnBuat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buatFile("M. Wahyu Asharul Falah");
            }
        });
    }
    public void buatFile(String isiFile){
        FileOutputStream outputStream = null;
        try {
            file.createNewFile();
            outputStream = new FileOutputStream(file, true);
            outputStream.write(isiFile.getBytes());
            outputStream.flush();
            outputStream.close();
            Toast.makeText(this, "File sudah dibuat", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            Toast.makeText(this, "Gagal membuat file", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }
    public void ubahFile(View view){
        if (file.exists()) {
            hapusFile(view);
            buatFile("File Diubah.");
        }else {
            Toast.makeText(this, "File tidak ada", Toast.LENGTH_SHORT).show();
        }
    }
    public void bacaFile(View view){
        if (file.exists()) {
            FileInputStream fis = null;
            try {
                fis = openFileInput(FILENAME);
                InputStreamReader isr = new InputStreamReader(fis);
                BufferedReader bufferedReader = new BufferedReader(isr);
                StringBuilder sb = new StringBuilder();
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    sb.append(line);
                }
                txtBaca.setText(sb);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else {
            Toast.makeText(this, "File tidak ada", Toast.LENGTH_SHORT).show();
        }
    }
    public void hapusFile(View view){
        if (file.exists()){
            file.delete();
            Toast.makeText(this, "File telah dihapus", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(this, "File tidak ada", Toast.LENGTH_SHORT).show();
        }
    }
}
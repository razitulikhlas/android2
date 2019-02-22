package com.example.razitulikhlas.projectdicoding;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class Update extends AppCompatActivity {
    private DBpelajaran database;
    EditText nenama,nematakuliah,newaktu,neruangan;
    Button savedata;
    private String getNAma,getMatakuliah,getRuangan,getWaktu,getHari;
    private Spinner spNamen;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        database = new DBpelajaran(getBaseContext());
        savedata=(Button)findViewById(R.id.save);
        nenama=(EditText)findViewById(R.id.new_inputnama);
        nematakuliah=(EditText)findViewById(R.id.new_inputmatakuliah);
        neruangan=(EditText)findViewById(R.id.new_inputruangan);
        newaktu=(EditText)findViewById(R.id.new_inputjadwalkuliah);
        spNamen = (Spinner) findViewById(R.id.sp_name);




        savedata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setUpdateData();
                Intent update=new Intent(Update.this,Senin.class);
                startActivity(update);
                finish();

            }
        });
    }

    private void setUpdateData() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        getNAma=nenama.getText().toString();
        getMatakuliah=nematakuliah.getText().toString();
        getRuangan=neruangan.getText().toString();
        getWaktu=newaktu.getText().toString();
        getHari=spNamen.getSelectedItem().toString();

        String GetNama = getIntent().getExtras().getString("sendNama");

        SQLiteDatabase database1 = database.getReadableDatabase();

        //Memasukan Data baru pada 3 kolom (NIM, Nama dan Jurusan)
        ContentValues values = new ContentValues();
        values.put(DBpelajaran.MyColumns.Nama, getNAma);
        values.put(DBpelajaran.MyColumns.Matakuliah, getMatakuliah);
        values.put(DBpelajaran.MyColumns.Ruangan, getRuangan);
        values.put(DBpelajaran.MyColumns.Waktu, getWaktu);
        values.put(DBpelajaran.MyColumns.Hari, getHari);

        //Untuk Menentukan Data/Item yang ingin diubah, berdasarkan NIM
        String selection = DBpelajaran.MyColumns.Nama + " LIKE ?";
        String[] selectionArgs = {GetNama};
        database1.update(DBpelajaran.MyColumns.NamaTabel, values, selection, selectionArgs);
        Toast.makeText(getApplicationContext(), "Berhasil Diubah", Toast.LENGTH_SHORT).show();

    }


}

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

public class Input extends AppCompatActivity {
    EditText enama,ematakuliah,ewaktu,eruangan;
    Button inputdata;
    private String setNAma,setMatakuliah,setRuangan,setWaktu,setHari;
    private Spinner spNamen;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);

        enama=(EditText)findViewById(R.id.inputnama);
        ematakuliah=(EditText)findViewById(R.id.inputmatakuliah);
        ewaktu=(EditText)findViewById(R.id.inputjadwalkuliah);
        eruangan=(EditText)findViewById(R.id.inputruangan);
        inputdata=(Button)findViewById(R.id.input);
        spNamen = (Spinner) findViewById(R.id.sp_name);


        
        inputdata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setData();
                saveData();
                setResult(RESULT_OK);
                finish();
                clearData();
            }
        });
    }

    private void clearData() {

        eruangan.setText("");
        ewaktu.setText("");
        ematakuliah.setText("");
        enama.setText("");
    }

    private void saveData() {
        DBpelajaran database=new DBpelajaran(this);
        SQLiteDatabase create = database.getWritableDatabase();

        //Membuat Map Baru, Yang Berisi Nama Kolom dan Data Yang Ingin Dimasukan
        ContentValues values = new ContentValues();
        values.put(DBpelajaran.MyColumns.Nama, setNAma);
        values.put(DBpelajaran.MyColumns.Matakuliah, setMatakuliah);
        values.put(DBpelajaran.MyColumns.Waktu, setWaktu);
        values.put(DBpelajaran.MyColumns.Ruangan, setRuangan);
        values.put(DBpelajaran.MyColumns.Hari, setHari);


        //Menambahkan Baris Baru, Berupa Data Yang Sudah Diinputkan pada Kolom didalam Database
        create.insert(DBpelajaran.MyColumns.NamaTabel, null, values);
    }

    private void setData() {
        setNAma=enama.getText().toString();
        setMatakuliah=ematakuliah.getText().toString();
        setWaktu=ewaktu.getText().toString();
        setRuangan=eruangan.getText().toString();
        setHari=spNamen.getSelectedItem().toString();

    }

    @Override
    public void onBackPressed() {
        setResult(RESULT_CANCELED);
        super.onBackPressed();
    }
}

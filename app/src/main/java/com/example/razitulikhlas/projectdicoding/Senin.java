package com.example.razitulikhlas.projectdicoding;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import java.util.ArrayList;

public class Senin extends AppCompatActivity {
    private DBpelajaran dBpelajaran;
    private RecylerVIewAdapter recylerVIewAdapter;
    private ArrayList<Data> datalist;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_senin);
        datalist=new ArrayList<>();
        dBpelajaran=new DBpelajaran(getBaseContext());
        RecyclerView recyclerView = findViewById(R.id.recycler);
        Intent intent = getIntent();
        getData(intent.getStringExtra("hari"));
        //Menggunakan Layout Manager, Dan Membuat List Secara Vertical
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recylerVIewAdapter = new RecylerVIewAdapter(datalist);
        //Memasang Adapter pada RecyclerView
        recyclerView.setAdapter(recylerVIewAdapter);
        //Membuat Underline pada Setiap Item Didalam List
        DividerItemDecoration itemDecoration = new DividerItemDecoration(getApplicationContext(), DividerItemDecoration.VERTICAL);
        itemDecoration.setDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.line));
        recyclerView.addItemDecoration(itemDecoration);

    }
    //Berisi Statement-Statement Untuk Mengambi Data dari Database
    @SuppressLint("Recycle")
    protected void getData(String hari){
        //Mengambil Repository dengan Mode Membaca
        SQLiteDatabase ReadData = dBpelajaran.getReadableDatabase();
        Cursor cursor = ReadData.rawQuery("SELECT * FROM "+ DBpelajaran.MyColumns.NamaTabel
                +" WHERE " + DBpelajaran.MyColumns.Hari + " = '"+hari+"' ",null);

        cursor.moveToFirst();//Memulai Cursor pada Posisi Awal

        //Melooping Sesuai Dengan Jumlan Data (Count) pada cursor
        for(int count=0; count < cursor.getCount(); count++){

            cursor.moveToPosition(count);//Berpindah Posisi dari no index 0 hingga no index terakhir

            //Memasukan semua data dari variable NIM, Nama dan Jurusan ke parameter Class DataFiter
            datalist.add(new Data(cursor.getString(0),cursor.getString(1),cursor.getString(2),cursor.getString(4),
                    cursor.getString(3)));
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //Memanggil/Memasang menu item pada toolbar dari layout menu_bar.xml
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.search_bar, menu);
        MenuItem searchIem = menu.findItem(R.id.search);
        final SearchView searchView = (SearchView) searchIem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String nextText) {
                //Data akan berubah saat user menginputkan text/kata kunci pada SearchView
                nextText = nextText.toLowerCase();
                ArrayList<Data> dataFilter = new ArrayList<>();
                for(Data data : datalist){
                    String nama = data.getNama().toLowerCase();
                    if(nama.contains(nextText)){
                        dataFilter.add(data);
                    }
                }
                recylerVIewAdapter.setFilter(dataFilter);
                return true;
            }
        });
        return true;
    }

}

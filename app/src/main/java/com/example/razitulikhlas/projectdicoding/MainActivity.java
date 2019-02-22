
package com.example.razitulikhlas.projectdicoding;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    FloatingActionButton tambah;
    CardView senin1, selasa,rabu,kamis,jumaat;
    public static final int REQUEST_ADD = 101;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tambah=(FloatingActionButton)findViewById(R.id.floatingadd);
        senin1=(CardView)findViewById(R.id.senin);
        selasa = findViewById(R.id.selasa);
        rabu = findViewById(R.id.rabu);
        kamis = findViewById(R.id.kamis);
        jumaat = findViewById(R.id.jumaat);

        senin1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent senin=new Intent(MainActivity.this,Senin.class);
                senin.putExtra("hari", "Senin");
                startActivity(senin);
            }
        });

        selasa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent senin=new Intent(MainActivity.this,Senin.class);
                senin.putExtra("hari", "Selasa");
                startActivity(senin);                
            }
        });
        rabu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent senin=new Intent(MainActivity.this,Senin.class);
                senin.putExtra("hari", "Rabu");
                startActivity(senin);
            }
        });
        kamis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent senin=new Intent(MainActivity.this,Senin.class);
                senin.putExtra("hari", "Kamis");
                startActivity(senin);
            }
        });
        jumaat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent senin=new Intent(MainActivity.this,Senin.class);
                senin.putExtra("hari", "Jumaat");
                startActivity(senin);
            }
        });


        tambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent input=new Intent(MainActivity.this,Input.class);
                startActivityForResult(input, REQUEST_ADD);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case REQUEST_ADD:
                if (resultCode == RESULT_OK){
                    Toast.makeText(this, "Data ditambahkan", Toast.LENGTH_SHORT).show();
                } else if (resultCode == RESULT_CANCELED){
                    Toast.makeText(this, "gagal tambah data", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
}

package com.example.razitulikhlas.projectdicoding;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;


public class RecylerVIewAdapter extends RecyclerView.Adapter<RecylerVIewAdapter.ViewHolder> {
    private ArrayList<Data> datalist;
    private Context context;

    public RecylerVIewAdapter(ArrayList<Data> datalist) {
        this.datalist = datalist;
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvNama,tvMatakuliah,tvWaktu,tvRuangan;
        Spinner sp;
        ImageButton flow;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            context=itemView.getContext();

            flow=(ImageButton)itemView.findViewById(R.id.overflow);
            tvNama=(TextView)itemView.findViewById(R.id.namadosen);
            tvMatakuliah=(TextView)itemView.findViewById(R.id.matakuliah);
            tvRuangan=(TextView)itemView.findViewById(R.id.ruangan);
            tvWaktu=(TextView)itemView.findViewById(R.id.waktu);

        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.hasil,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        final String nama=datalist.get(position).getNama();
        final String matakuliah=datalist.get(position).getMatakuliah();
        final String ruangan=datalist.get(position).getRuangan();
        final String waktu=datalist.get(position).getWaktu();



        holder.tvNama.setText(nama);
        holder.tvMatakuliah.setText(matakuliah);
        holder.tvRuangan.setText(ruangan);
        holder.tvWaktu.setText(waktu);


        holder.flow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                PopupMenu popupMenu=new PopupMenu(v.getContext(),v);
                popupMenu.inflate(R.menu.menudatabase);
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()){
                            case R.id.update:
                                Intent update=new Intent(v.getContext(),Update.class);
                                update.putExtra("sendNIK",nama);
                                context.startActivity(update);
                                ((Activity)context).finish();
                                break;
                            case R.id.delete:
                                DBpelajaran dBpelajaran=new DBpelajaran(v.getContext());
                                SQLiteDatabase DeleteDAta=dBpelajaran.getWritableDatabase();
                                String selection = DBpelajaran.MyColumns.Nama + " LIKE ?";
                                String[] selectionArgs = {nama};
                                DeleteDAta.delete(DBpelajaran.MyColumns.NamaTabel, selection, selectionArgs);

                                String position2 = String.valueOf(nama.indexOf(nama));
                                datalist.remove(position);
                                notifyItemRemoved(position);
                                if (position2 == null) {
                                    notifyItemRangeChanged(Integer.parseInt(position2), datalist.size());
                                }
                                break;
                        }
                        return true;
                    }
                });
                    popupMenu.show();
            }
        });
    }
    @Override
    public int getItemCount() {
        //Menghitung Ukuran/Jumlah Data Yang Akan Ditampilkan Pada RecyclerView
        return datalist.size();
    }

    void setFilter(ArrayList<Data> filterList){
        datalist = new ArrayList<>();
        datalist.addAll(filterList);
        notifyDataSetChanged();
    }
}

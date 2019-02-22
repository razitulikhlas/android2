package com.example.razitulikhlas.projectdicoding;

public class Data {
    private String hari;
    private String nama;
    private String matakuliah;
    private String waktu;
    private String ruangan;

    public Data(String hari, String nama, String matakuliah, String waktu, String ruangan) {
        this.hari = hari;
        this.nama = nama;
        this.matakuliah = matakuliah;
        this.waktu = waktu;
        this.ruangan = ruangan;
    }



    public String getNama() {
        return nama;
    }

    public String getMatakuliah() {
        return matakuliah;
    }

    public String getWaktu() {
        return waktu;
    }

    public String getRuangan() {
        return ruangan;
    }
}

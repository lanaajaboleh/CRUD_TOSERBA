// Mahasiswa.java

package com.example.CRUD_TOSERBA.model;
public class Transaksi {
//! Properties sesuai dengan tabel mahasiswa
private String id_transaksi;
private String tanggal;
private String total_bayar;
private String nama_produk;
private String id_kasir;
private String nama_kasir;

//! Setter dan Getter Properties
public String getId_transaksi() {
return id_transaksi;
}

public void setId_transaksi(String id_transaksi) {
this.id_transaksi = id_transaksi;
}

public String getTanggal() {
return tanggal;
}

public void setTanggal(String tanggal) {
this.tanggal = tanggal;
}

public String getTotal_bayar() {
return total_bayar;
}

public void setTotal_bayar(String total_bayar) {
this.total_bayar = total_bayar;
}

public String getNama_produk() {
return nama_produk;
}

public void setNama_produk (String nama_produk) {
this.nama_produk= nama_produk;
}

public String getId_kasir() {
return id_kasir;
}

public void setId_kasir (String id_kasir) {
this.id_kasir = id_kasir;
}

public String getNama_kasir() {
    return nama_kasir;
    }
    public void setNama_kasir(String nama_kasir) {
    this.nama_kasir = nama_kasir;
    }

}
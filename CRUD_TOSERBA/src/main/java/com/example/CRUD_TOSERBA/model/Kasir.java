// Mahasiswa.java

package com.example.CRUD_TOSERBA.model;
public class Kasir {
//! Properties sesuai dengan tabel mahasiswa
private String id_kasir;
private String nama_kasir;
private String alamat_kasir;
private String no_telp;
private String id_sv;
//! Setter dan Getter Properties
public String getId_kasir() {
return id_kasir;
}
public void setId_kasir(String id_kasir) {
this.id_kasir = id_kasir;
}
public String getNama_kasir() {
return nama_kasir;
}
public void setNama_kasir(String nama_kasir) {
this.nama_kasir = nama_kasir;
}
public String getAlamat_kasir() {
return alamat_kasir;
}
public void setAlamat_kasir(String alamat_kasir) {
this.alamat_kasir= alamat_kasir;
}
public String getNo_telp() {
return no_telp;
}
public void setNo_telp (String no_telp) {
this.no_telp= no_telp;
}

public String getId_sv() {
return id_sv;
}

public void setId_sv (String id_sv) {
this.id_sv= id_sv;
}

}
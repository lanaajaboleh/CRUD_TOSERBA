// Mahasiswa.java

package com.example.CRUD_TOSERBA.model;
public class Supervisor {
//! Properties sesuai dengan tabel mahasiswa
private String id_sv;
private String nama;
private String alamat_sv;
private String no_telp;

//! Setter dan Getter Properties
public String getId_sv() {
return id_sv;
}
public void setId_sv(String id_sv) {
this.id_sv = id_sv;
}
public String getNama() {
return nama;
}
public void setNama(String nama) {
this.nama = nama;
}
public String getAlamat_sv() {
return alamat_sv;
}
public void setAlamat_sv(String alamat_sv) {
this.alamat_sv = alamat_sv;
}
public String getNo_telp() {
return no_telp;
}
public void setNo_telp (String no_telp) {
this.no_telp = no_telp;
}

}
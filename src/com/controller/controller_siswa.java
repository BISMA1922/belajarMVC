/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controller;
import com.view.view;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
public interface controller_siswa {
    public void Simpan(view siswa) throws SQLException;
    public void Ubah(view siswa) throws SQLException;
    public void Hapus(view siswa) throws SQLException;
    public void Tampil(view siswa) throws SQLException;
    public void Baru(view siswa) throws SQLException;
    public void KlikTabel(view siswa) throws SQLException;
      
}

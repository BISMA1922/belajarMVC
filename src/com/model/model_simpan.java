/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.model;

import com.controller.controller_siswa;
import com.koneksi.koneksi;
import com.view.view;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
public class model_simpan implements controller_siswa {
String jk;
    @Override
    public void Simpan(view siswa) throws SQLException {
        if (siswa.rbLaki.isSelected()){
            jk = "Laki-laki";
        } else{
            jk = "Perempuan";
        }
        try{
    Connection con = koneksi.getcon();
    String sql = "Insert Into siswa_sabtu Values(?, ?, ?, ?)";
    PreparedStatement prepare = con.prepareStatement(sql);
    prepare.setString(1, siswa.txtNIS.getText());
    prepare.setString(2, siswa.txtNama.getText());
    prepare.setString(3, jk);
    prepare.setString(4, (String) siswa.cbJurusan.getSelectedItem());
    prepare.executeUpdate();
    JOptionPane.showMessageDialog(null, "Data Berhasil diSimpan");
    prepare.close();
    Baru(siswa);
}catch (Exception e) {
    System.out.println(e);
}finally {
    Tampil(siswa);
    
}

    }
   
    @Override
    public void Ubah(view siswa) throws SQLException {
       if (siswa.rbLaki.isSelected()){
           jk = "Laki-Laki";
       } else {
           jk = "Perempuan";
       }
       try {
           Connection con = koneksi.getcon();
           String sql = "UPDATE siswa SET nama=?, jenis_kelamin=?, "
                   + "jurusan=? WHERE NIS=?";
           PreparedStatement prepare = con.prepareStatement(sql);
           prepare.setString(4, siswa.txtNIS.getText());
           prepare.setString(1, siswa.txtNama.getText());
           prepare.setString(2, jk);
           prepare.setString(3, (String) siswa.cbJurusan.getSelectedItem());
           prepare.executeUpdate();
           JOptionPane.showMessageDialog(null, "Data Berhasil diubah");
           prepare.close();
       } catch (Exception e) {
           System.out.println(e); 
       } finally {
               Tampil(siswa);
//               siswa.setLebarKolom();
               Baru(siswa);
               }
       }
           
    

    @Override
    public void Hapus(view siswa) throws SQLException {
       try {
           Connection con = koneksi.getcon();
           String sql = "DELETE FROM siswa WHERE NIS =?";
           PreparedStatement prepare = con.prepareStatement(sql);
           prepare.setString(1, siswa.txtNIS.getText());
           prepare.executeUpdate();
           JOptionPane.showMessageDialog(null, "Data Berhasil Dihapus");
           prepare.close();
       } catch (Exception e){
           System.out.println(e);
       }finally {
           Tampil(siswa);
//           siswa.setLebarKolom();
           Baru(siswa);
       }
    }

    @Override
    public void Tampil(view siswa) throws SQLException {
       
    }

    @Override
    public void Baru(view siswa) throws SQLException {
       siswa.txtNIS.setText("");
       siswa.txtNama.setText("");
       siswa.rbLaki.setSelected(true);
       siswa.cbJurusan.setSelectedIndex(0);
    }

    @Override
    public void KlikTabel(view siswa) throws SQLException {
        
    }
    
}

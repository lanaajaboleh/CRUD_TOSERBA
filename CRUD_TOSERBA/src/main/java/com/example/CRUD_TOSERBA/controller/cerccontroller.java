// MahasiswaController.java
package com.example.CRUD_TOSERBA.controller;

import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.CRUD_TOSERBA.model.*;;

@Controller
public class cerccontroller {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @GetMapping("/home")
    public String index(Model model) {
        String sql = "SELECT * FROM TRANSAKSI_TOSERBA WHERE DELETED = 'n'";
        List<Transaksi> transaksiList = jdbcTemplate.query(sql,
                BeanPropertyRowMapper.newInstance(Transaksi.class));
         model.addAttribute("transaksiList", transaksiList);
        return "index";
    }

    @GetMapping("/kasir")
    public String kasir(Model model) {
        String sql = "SELECT * FROM KASIR WHERE DELETED = 'n'";
        List<Kasir> kasirList = jdbcTemplate.query(sql,
                BeanPropertyRowMapper.newInstance(Kasir.class));
         model.addAttribute("kasirList", kasirList);
        return "indexkasir";
    }


    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String getUser() {
        return "login";
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public String login(@ModelAttribute(name = "user") user user, Model model) {
        String username = user.getUsername();
        String password = user.getPassword();
        try {
            String sql = "SELECT * FROM LOGIN WHERE USERNAME = ?";
            user asli = jdbcTemplate.queryForObject(sql, BeanPropertyRowMapper.newInstance(user.class), username);
            model.addAttribute("asli", asli);
            String passasli = asli.getPassword();
            if (password.equals(passasli)) {
                return "redirect:/home";
            }
        } catch (EmptyResultDataAccessException e) {
            // TODO: handle exception
            model.addAttribute("invalidCredentials", true);
        }
        model.addAttribute("invalidCredentials", true);
        return "login";
    }

    @GetMapping("/add")
    public String add(Model model) {
        return "add";
    }

    @RequestMapping(value ="/add", method = RequestMethod.POST)
    public String addtransaksi(Transaksi trk, Model model ) {
        
                
                String sql = "INSERT INTO TRANSAKSI (id_transaksi, tanggal, total_bayar, nama_produk, id_kasir, deleted) VALUES (?, ?, ?, ?, ?, 'n')";
                jdbcTemplate.update(sql,
                trk.getId_transaksi(),
                trk.getTanggal(),
                trk.getTotal_bayar(),
                trk.getNama_produk(),
                trk.getId_kasir());
                return "redirect:/home";
                
        
    }

    @GetMapping("/kasir/add")
    public String addkasir(Model model) {
        return "addkasir";
    }

    @RequestMapping(value ="/kasir/add", method = RequestMethod.POST)
    public String addkasir(Kasir ksr, Model model ) {
        
                
                String sql = "INSERT INTO KASIR (id_kasir, nama_kasir, alamat_kasir, no_telp, id_sv, deleted) VALUES (?, ?, ?, ?, ?, 'n')";
                jdbcTemplate.update(sql,
                ksr.getId_kasir(),
                ksr.getNama_kasir(),
                ksr.getAlamat_kasir(),
                ksr.getNo_telp(),
                ksr.getId_sv());
                return "redirect:/kasir";
                
        
    }

    @GetMapping("/edittransaksi/{id_transaksi}")
    public String edit(@PathVariable("id_transaksi") String id_transaksi, Model model) {
        String sql = "SELECT * FROM TRANSAKSI WHERE ID_TRANSAKSI = ?";
        Transaksi transaksi = jdbcTemplate.queryForObject(sql,
                BeanPropertyRowMapper.newInstance(Transaksi.class), id_transaksi);
        model.addAttribute("transaksi", transaksi);
        return "edittransaksi";
    }

    @PostMapping("/edittransaksi")
    public String edit(Transaksi transaksi) {
        String sql = "UPDATE TRANSAKSI SET ID_TRANSAKSI = ?, TANGGAL = ?, TOTAL_BAYAR = ?, NAMA_PRODUK = ? WHERE ID_TRANSAKSI = ?";
        jdbcTemplate.update(sql, transaksi.getId_transaksi(), transaksi.getTanggal(),
                transaksi.getTotal_bayar(),
                transaksi.getNama_produk(), transaksi.getId_kasir());
        return "redirect:/home";
    }
    
    @GetMapping("/kasir/editkasir/{id_kasir}")
    public String editKasir(@PathVariable("id_kasir") String id_kasir, Model model) {
        String sql = "SELECT * FROM KASIR WHERE ID_KASIR = ?";
        Kasir kasir = jdbcTemplate.queryForObject(sql,
                BeanPropertyRowMapper.newInstance(Kasir.class), id_kasir);
        model.addAttribute("kasir", kasir);
        return "editkasir";
    }

    @PostMapping("/kasir/editkasir")
    public String edit(Kasir kasir) {
        String sql = "UPDATE KASIR SET ID_KASIR = ?, NAMA_KASIR = ?, ALAMAT_KASIR = ?, NO_TELP = ? WHERE ID_KASIR = ?";
        jdbcTemplate.update(sql, kasir.getId_kasir(), kasir.getNama_kasir(),
                kasir.getAlamat_kasir(),
                kasir.getNo_telp(), kasir.getId_kasir());
        return "redirect:/kasir";
    }



    @GetMapping("/harddelete/{id_transaksi}")
    public String harddelete(@PathVariable("id_transaksi") String id_transaksi) {
        String sql = "DELETE TRANSAKSI WHERE ID_TRANSAKSI = ?";
        jdbcTemplate.update(sql, id_transaksi);
        return "redirect:/home";
    }

    @GetMapping("/kasir/harddelete/{id_kasir}")
    public String harddeleteKasir(@PathVariable("id_kasir") String id_kasir) {
        String sql = "DELETE KASIR WHERE ID_KASIR = ?";
        jdbcTemplate.update(sql, id_kasir);
        return "redirect:/kasir/trash";
    }
    
    @GetMapping("/trash")
    public String trash(Model model) {
        String sql = "SELECT * FROM TRANSAKSI_TOSERBA WHERE DELETED = 'y'";
        List<Transaksi> deletedList = jdbcTemplate.query(sql,
                BeanPropertyRowMapper.newInstance(Transaksi.class));
        model.addAttribute("deletedList", deletedList);
        return "trash";
    }

    @GetMapping("/kasir/trash")
    public String trashKasir(Model model) {
        String sql = "SELECT * FROM KASIR WHERE DELETED = 'y'";
        List<Kasir> deletedList = jdbcTemplate.query(sql,
                BeanPropertyRowMapper.newInstance(Kasir.class));
        model.addAttribute("deletedList", deletedList);
        return "trash_kasir";
    }

    @GetMapping("/softdelete/{id_transaksi}")
    public String softdelete(@PathVariable("id_transaksi") String id_transaksi) {
        String sql = "UPDATE TRANSAKSI SET DELETED = 'y' WHERE ID_TRANSAKSI = ?";
        jdbcTemplate.update(sql, id_transaksi);
        return "redirect:/home";
    }

    @GetMapping("/restore/{id_transaksi}")
    public String restore(@PathVariable("id_transaksi") String id_transaksi) {
        String sql = "UPDATE TRANSAKSI SET DELETED = 'n' WHERE ID_TRANSAKSI = ?";
        jdbcTemplate.update(sql, id_transaksi);
        return "redirect:/home";
    }

    @GetMapping("/kasir/softdeletekasir/{id_kasir}")
    public String softdeletekasir(@PathVariable("id_kasir") String id_kasir) {
        String sql = "UPDATE KASIR SET DELETED = 'y' WHERE ID_KASIR = ?";
        jdbcTemplate.update(sql, id_kasir);
        return "redirect:/kasir";
    }

    @GetMapping("/kasir/restore/{id_kasir}")
    public String restorekasir(@PathVariable("id_kasir") String id_kasir) {
        String sql = "UPDATE KASIR SET DELETED = 'n' WHERE ID_KASIR = ?";
        jdbcTemplate.update(sql, id_kasir);
        return "redirect:/kasir";
    }



    @RequestMapping("/searchid")
    public String hasilsearch(@PathParam("id_search") String id_search, Model model) {
        String sql = "SELECT * FROM TRANSAKSI WHERE LOWER (NAMA_PRODUK) LIKE CONCAT(CONCAT ('%', ?), '%')";
        List<Transaksi> detailSearch = jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Transaksi.class), id_search);
        model.addAttribute("detailsearch", detailSearch);
        return ("searchid");
    }


}
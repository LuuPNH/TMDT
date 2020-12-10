package com.example.doantmdt.model;

public class sanpham {
    public int id;
    public String ten;
    public int gia;
    public String hinh;
    public String mota;
    public int idLoai;
    public String manhinh;
    public String hdh;
    public int bonho;
    public String camtrc;
    public String camsau;
    public String cpu;
    public int ram;
    public int pin;

    public sanpham(int id, String ten, int gia, String hinh, String mota, int idLoai, String manhinh, String hdh, int bonho, String camtrc, String camsau, String cpu, int ram, int pin) {
        this.id = id;
        this.ten = ten;
        this.gia = gia;
        this.hinh = hinh;
        this.mota = mota;
        this.idLoai = idLoai;
        this.manhinh = manhinh;
        this.hdh = hdh;
        this.bonho = bonho;
        this.camtrc = camtrc;
        this.camsau = camsau;
        this.cpu = cpu;
        this.ram = ram;
        this.pin = pin;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public int getGia() {
        return gia;
    }

    public void setGia(int gia) {
        this.gia = gia;
    }

    public String getHinh() {
        return hinh;
    }

    public void setHinh(String hinh) {
        this.hinh = hinh;
    }

    public String getMota() {
        return mota;
    }

    public void setMota(String mota) {
        this.mota = mota;
    }

    public int getIdLoai() {
        return idLoai;
    }

    public void setIdLoai(int idLoai) {
        this.idLoai = idLoai;
    }

    public String getManhinh() {
        return manhinh;
    }

    public void setManhinh(String manhinh) {
        this.manhinh = manhinh;
    }

    public String getHdh() {
        return hdh;
    }

    public void setHdh(String hdh) {
        this.hdh = hdh;
    }

    public int getBonho() {
        return bonho;
    }

    public void setBonho(int bonho) {
        this.bonho = bonho;
    }

    public String getCamtrc() {
        return camtrc;
    }

    public void setCamtrc(String camtrc) {
        this.camtrc = camtrc;
    }

    public String getCamsau() {
        return camsau;
    }

    public void setCamsau(String camsau) {
        this.camsau = camsau;
    }

    public String getCpu() {
        return cpu;
    }

    public void setCpu(String cpu) {
        this.cpu = cpu;
    }

    public int getRam() {
        return ram;
    }

    public void setRam(int ram) {
        this.ram = ram;
    }

    public int getPin() {
        return pin;
    }

    public void setPin(int pin) {
        this.pin = pin;
    }
}

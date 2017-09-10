package com.pusilkom.demo.dto.view;

import com.pusilkom.demo.model.Institusi;

/**
 * Created by fahri on 1/8/17.
 */
public class InstitusiDetail {
    private Long id;
    private String kodeInstitusi;
    private String nama;
    private String namaSingkat;
    private Boolean flagAktif;

    public InstitusiDetail() {}

    public InstitusiDetail(Institusi institusi) {
        this.id = institusi.getId();
        this.kodeInstitusi = institusi.getKodeInstitusi();
        this.nama = institusi.getNama();
        this.namaSingkat = institusi.getNamaSingkat();
        this.flagAktif = institusi.getFlagAktif();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getKodeInstitusi() {
        return kodeInstitusi;
    }

    public void setKodeInstitusi(String kodeInstitusi) {
        this.kodeInstitusi = kodeInstitusi;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getNamaSingkat() {
        return namaSingkat;
    }

    public void setNamaSingkat(String namaSingkat) {
        this.namaSingkat = namaSingkat;
    }

    public Boolean getFlagAktif() {
        return flagAktif;
    }

    public void setFlagAktif(Boolean flagAktif) {
        this.flagAktif = flagAktif;
    }

}

package com.pusilkom.demo.dto.form.cmd;

import com.pusilkom.demo.model.Institusi;

/**
 * Created by fahri on 1/8/17.
 */
public class InstitusiCmd {

    private Long id;
    private String kodeInstitusi;
    private String nama;
    private String namaSingkat;
    private Boolean flagAktif;

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

    public Institusi toInstitusi() {
        Institusi institusi = new Institusi();
        institusi.setId(this.id);
        institusi.setKodeInstitusi(this.kodeInstitusi);
        institusi.setNamaSingkat(this.namaSingkat);
        institusi.setNama(this.nama);
        institusi.setFlagAktif(this.flagAktif);

        return institusi;
    }

}

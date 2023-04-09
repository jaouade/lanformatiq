package com.example.demo.tst;

import java.util.List;

public class Tomobile {

    private Moteur moteur;
    private List<Rwayda> rwaydat;
    private List<Bola> bolat;

    public void dowrContact(){
        this.moteur.demarer();
    }

    public Tomobile(Moteur moteur, List<Rwayda> rwaydat, List<Bola> bolat) {
        this.moteur = moteur;
        this.rwaydat = rwaydat;
        this.bolat = bolat;
    }

    public Moteur getMoteur() {
        return moteur;
    }

    public void setMoteur(Moteur moteur) {
        this.moteur = moteur;
    }

    public List<Rwayda> getRwaydat() {
        return rwaydat;
    }

    public void setRwaydat(List<Rwayda> rwaydat) {
        this.rwaydat = rwaydat;
    }

    public List<Bola> getBolat() {
        return bolat;
    }

    public void setBolat(List<Bola> bolat) {
        this.bolat = bolat;
    }
}

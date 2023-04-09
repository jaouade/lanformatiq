package com.example.demo.tst;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Rwayda> rwaydat = new ArrayList<>();
        List<Bola> bolat = new ArrayList<>();
        Moteur moteur = new Moteur();
        Tomobile tomobile = new Tomobile(moteur, rwaydat, bolat);
        tomobile.dowrContact();
        AbstraxctClass abstraxctClass = new Heritage();
    }
}

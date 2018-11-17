package com.lab.post.xsd_eqv;

import java.util.ArrayList;

public class Guns {

    // Attributes
    private String _xmlns;
    private String _xmlns_xs;
    private String _xs_schemaLocation;

    // Children
    private ArrayList<Gun> guns;

    // Constructors
    public Guns() {
        guns = new ArrayList<>();
        _xmlns = "LAB.2";
        _xmlns_xs = "http://www.w3.org/2001/XMLSchema";
        _xs_schemaLocation = "LAB.2 XSD/input.xsd";
    }

    public Guns(String _xmlns, String _xmlns_xs, String _xs_schemaLocation) {
        guns = new ArrayList<>();
        this._xmlns = _xmlns;
        this._xmlns_xs = _xmlns_xs;
        this._xs_schemaLocation = _xs_schemaLocation;
    }

    public Guns(ArrayList<Gun> guns, String _xmlns, String _xmlns_xs, String _xs_schemaLocation) {
        this.guns = guns;
        this._xmlns = _xmlns;
        this._xmlns_xs = _xmlns_xs;
        this._xs_schemaLocation = _xs_schemaLocation;
    }

    // Setters
    public final void set_xmlns(String _xmlns) {
        this._xmlns = _xmlns;
    }

    public final void set_xmlns_xs(String _xmlns_xs) {
        this._xmlns_xs = _xmlns_xs;
    }

    public final void set_xs_schemaLocation(String _xs_schemaLocation) {
        this._xs_schemaLocation = _xs_schemaLocation;
    }

    public final void addGun(Gun gun) {
        guns.add(gun);
    }

    // Getters
    public final ArrayList<Gun> getGun() {
        return guns;
    }

    public final String get_xmlns() {
        return _xmlns;
    }

    public final String get_xmlns_xsi() {
        return _xmlns_xs;
    }

    public final String get_xsi_schemaLocation() {
        return _xs_schemaLocation;
    }

    @Override
    public String toString() {
        String tmp = "";
        for (Gun gun : guns) tmp += gun.toString();
        return tmp + "\n}";
    }
}

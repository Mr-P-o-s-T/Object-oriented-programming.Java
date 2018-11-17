package com.lab.post.xsd_eqv;

import com.lab.post.xsd_eqv.gun.Handy;
import com.lab.post.xsd_eqv.gun.Materials;
import com.lab.post.xsd_eqv.gun.TTC;
import com.lab.post.xsd_eqv.gun.ttc.Range;
import com.lab.post.xsd_eqv.gun.ttc.SightningRange;

public class Gun {

    // Attributes
    private int id;

    // Children
    private String model;
    private Handy handy;
    private String origin;
    private TTC ttc;
    private Materials materials;

    // Constructor
    public Gun(int id, String model, Handy handy, String origin, Range range, SightningRange sightningRange) {
        this.id = id;
        this.model = model;
        this.handy = handy;
        this.origin = origin;
        ttc = new TTC(range, sightningRange);
        materials = new Materials();
    }

    public Gun(int id, String model, Handy handy, String origin, TTC ttc, Materials materials) {
        this.id = id;
        this.model = model;
        this.handy = handy;
        this.origin = origin;
        this.ttc = ttc;
        this.materials = materials;
    }

    // Setters
    public void setModel(String model) {
        this.model = model;
    }

    public final void setHandy(Handy handy) {
        this.handy = handy;
    }

    public final void setOrigin(String origin) {
        this.origin = origin;
    }

    public final void setId(int id) {
        this.id = id;
    }

    public final void setTtc(TTC ttc) {
        this.ttc = ttc;
    }

    public final void setMaterials(Materials materials) {
        this.materials = materials;
    }

    // Getters
    public final int getId() {
        return id;
    }

    public final String getModel() {
        return model;
    }

    public final Handy getHandy() {
        return handy;
    }

    public final String getOrigin() {
        return origin;
    }

    public final TTC getTtc() {
        return ttc;
    }

    public final Materials getMaterials() {
        return materials;
    }

    @Override
    public String toString() {
        return "\nGun(" + id +"){\n" +
                "- Model: " + model + '\n' +
                "- Handy: " + handy + '\n' +
                "- Origin: " + origin + '\n' +
                ttc.toString() +
                materials.toString() + '}';
    }
}

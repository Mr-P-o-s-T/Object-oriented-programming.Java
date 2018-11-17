package com.lab.post.xsd_eqv.gun;

import com.lab.post.xsd_eqv.gun.materials.Material;

import java.util.ArrayList;

public class Materials {

    //Chldren
    ArrayList<Material> materials;

    // Constructors
    public Materials() {
        materials = new ArrayList<>();
    }

    @Override
    public String toString() {
        String tmp = "";
        for (Material material : materials) tmp += material.toString();

        return "- Materials:\n" + tmp;
    }

    public Materials(ArrayList<Material> materials) {
        this.materials = materials;
    }

    // Setters
    public final void setMaterials(ArrayList<Material> materials) {
        this.materials = materials;
    }

    public final void addMaterial(Material material) {
        materials.add(material);
    }

    // Getters
    public final ArrayList<Material> getMaterials() {
        return materials;
    }
}

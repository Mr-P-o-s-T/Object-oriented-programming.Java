package com.lab.post.xsd_eqv.gun.materials;

public class Material {

    @Override
    public String toString() {
        return "\t- " + material + '\n';
    }

    // Children
    String material;

    // Constructors
    public Material(String material) {
        this.material = material;
    }

    // Setters
    public final void setMaterial(String material) {
        this.material = material;
    }

    // Getters
    public final String getMaterial() {
        return material;
    }
}

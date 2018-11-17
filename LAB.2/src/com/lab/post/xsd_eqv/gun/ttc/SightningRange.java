package com.lab.post.xsd_eqv.gun.ttc;

public class SightningRange {

    // Children
    private int sightningRange;

    // Constructors
    public SightningRange(int sightningRange) {
        this.sightningRange = sightningRange;
    }

    // Setters
    public final void setSightningRange(int sightningRange) {
        this.sightningRange = sightningRange;
    }

    // Getters
    public final int getSightningRange() {
        return sightningRange;
    }

    @Override
    public String toString() {
        return "\t- Sighting range: " + sightningRange + '\n';
    }
}

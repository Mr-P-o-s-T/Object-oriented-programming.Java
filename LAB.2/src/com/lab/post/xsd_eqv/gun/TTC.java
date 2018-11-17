package com.lab.post.xsd_eqv.gun;

import com.lab.post.xsd_eqv.gun.ttc.Attachments;
import com.lab.post.xsd_eqv.gun.ttc.Range;
import com.lab.post.xsd_eqv.gun.ttc.SightningRange;

public class TTC {

    // Children
    Range range;
    SightningRange sightningRange;
    Attachments attachments;

    // Constructors
    public TTC(Range range, SightningRange sightningRange) {
        this.range = range;
        this.sightningRange = sightningRange;
        attachments = new Attachments();
    }

    public TTC(Range range, SightningRange sightningRange, Attachments attachments) {
        this.range = range;
        this.sightningRange = sightningRange;
        this.attachments = attachments;
    }

    // Setters
    public final void setRange(Range range) {
        this.range = range;
    }

    public final void setSightningRange(SightningRange sightningRange) {
        this.sightningRange = sightningRange;
    }

    public final void setAttachments(Attachments attachments) {
        this.attachments = attachments;
    }

    // Getters
    public final Range getRange() {
        return range;
    }

    public final SightningRange getSightningRange() {
        return sightningRange;
    }

    public final Attachments getAttachments() {
        return attachments;
    }

    @Override
    public String toString() {
        return "- TTC:\n" +
                range.toString() +
                sightningRange.toString() +
                attachments.toString();
    }
}

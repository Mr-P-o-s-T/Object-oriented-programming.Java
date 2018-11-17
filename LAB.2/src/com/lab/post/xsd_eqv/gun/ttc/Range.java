package com.lab.post.xsd_eqv.gun.ttc;

import com.lab.post.xsd_eqv.gun.ttc.range.RangeType;

public class Range {

    // Attributes
    RangeType type;

    // Constructors
    public Range(RangeType type) {
        this.type = type;
    }

    // Setters
    public final void setType(RangeType type) {
        this.type = type;
    }

    // Getters
    public final RangeType getType() {
        return type;
    }

    @Override
    public String toString() {
        return "\t- Range: " + type + '\n';
    }
}

package com.lab.post.xsd_eqv.gun;

import com.lab.post.xsd_eqv.Gun;

import java.util.Comparator;

public class GunCompatrator implements Comparator<Gun> {
    @Override
    public int compare(Gun g1, Gun g2) {
        return g1.getId() - g2.getId();
    }
}

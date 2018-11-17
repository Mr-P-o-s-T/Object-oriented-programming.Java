package com.lab.post.xsd_eqv.gun.ttc.attachments;

import java.util.Comparator;

public class AttachmentComparator implements Comparator<IAttachment> {
    @Override
    public int compare(IAttachment a1, IAttachment a2) {
        return a1.getType().ordinal() - a2.getType().ordinal();
    }
}

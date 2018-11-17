package com.lab.post.xsd_eqv.gun.ttc.attachments;

public class Optics implements IAttachment {
    @Override
    public AttachmentType getType() {
        return AttachmentType.OPTICS;
    }

    @Override
    public String toString() {
        return "\t\t- Optics\n";
    }
}

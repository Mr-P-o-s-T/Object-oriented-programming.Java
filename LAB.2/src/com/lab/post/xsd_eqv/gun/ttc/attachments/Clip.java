package com.lab.post.xsd_eqv.gun.ttc.attachments;

public class Clip implements IAttachment {
    @Override
    public AttachmentType getType() {
        return AttachmentType.CLIP;
    }

    @Override
    public String toString() {
        return "\t\t- Clip\n";
    }
}

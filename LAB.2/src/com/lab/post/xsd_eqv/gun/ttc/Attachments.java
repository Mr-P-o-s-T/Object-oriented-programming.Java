package com.lab.post.xsd_eqv.gun.ttc;

import com.lab.post.xsd_eqv.gun.ttc.attachments.AttachmentComparator;
import com.lab.post.xsd_eqv.gun.ttc.attachments.Clip;
import com.lab.post.xsd_eqv.gun.ttc.attachments.IAttachment;
import com.lab.post.xsd_eqv.gun.ttc.attachments.Optics;

import java.util.ArrayList;

public class Attachments {

    // Children
    ArrayList<IAttachment> attachments;

    // Constructors
    public Attachments() {
        attachments = new ArrayList<>();
    }

    @Override
    public String toString() {
        String tmp = "";
        for (IAttachment attachment : attachments) tmp += attachment.toString();
        if (tmp == "") tmp += "\t\t- None\n";
        return "\t- Attachment:\n" + tmp;
    }

    public Attachments(ArrayList<IAttachment> attachments) {
        this.attachments = attachments;
    }

    // Setters
    public final void setAttachments(ArrayList<IAttachment> attachments) {
        this.attachments = attachments;
    }

    public final void addClip() {
        attachments.add(new Clip());
        attachments.sort(new AttachmentComparator());
    }

    public final void addOptics() {
        attachments.add(new Optics());
        attachments.sort(new AttachmentComparator());
    }

    // Getters
    public final ArrayList<IAttachment> getAttachments() {
        return attachments;
    }
}

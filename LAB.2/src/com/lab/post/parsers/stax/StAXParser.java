package com.lab.post.parsers.stax;

import com.lab.post.xsd_eqv.Gun;
import com.lab.post.xsd_eqv.Guns;
import com.lab.post.xsd_eqv.gun.GunCompatrator;
import com.lab.post.xsd_eqv.gun.Handy;
import com.lab.post.xsd_eqv.gun.Materials;
import com.lab.post.xsd_eqv.gun.TTC;
import com.lab.post.xsd_eqv.gun.materials.Material;
import com.lab.post.xsd_eqv.gun.ttc.Attachments;
import com.lab.post.xsd_eqv.gun.ttc.Range;
import com.lab.post.xsd_eqv.gun.ttc.SightningRange;
import com.lab.post.xsd_eqv.gun.ttc.range.RangeType;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamReader;
import java.io.FileInputStream;

public class StAXParser{

    private enum Stage {
        NONE,
        MODEL,
        ORIGIN,
        SIGHTRANGE,
        MATERIAL
    }

    private Stage curStage = Stage.NONE;

    private Guns guns;

    private int curGunId;

    private String curModel;
    private Handy curHandy;
    private String curOrigin;
    private TTC curTtc;
    private Materials curMaterials;

    private Attachments curAttachments;
    private Range curRange;
    private SightningRange curSightningRange;

    private Material curMaterial;

    public Guns parse(String xmlSource) throws Exception {
        FileInputStream f = new FileInputStream(xmlSource);
        XMLStreamReader reader = XMLInputFactory.newInstance().createXMLStreamReader(f);

        while (reader.hasNext()) {
            int type = reader.next();
            switch (type) {
                case XMLStreamConstants.START_ELEMENT:
                    switch (reader.getLocalName()) {
                        case "guns":
                            guns = new Guns();
                            break;
                        case "gun":
                            curGunId = Integer.parseInt(reader.getAttributeValue(0));
                            break;
                        case "model":
                            curStage = Stage.MODEL;
                            break;
                        case "handy":
                            switch (reader.getAttributeValue(0)) {
                                case "1": curHandy = Handy.ONE;
                                    break;
                                case "2": curHandy = Handy.TWO;
                                    break;
                            }
                            break;
                        case "origin":
                            curStage = Stage.ORIGIN;
                            break;
                        case "range":
                            switch (reader.getAttributeValue(0)) {
                                case "near": curRange = new Range(RangeType.NEAR);
                                    break;
                                case "medium": curRange = new Range(RangeType.MEDIUM);
                                    break;
                                case "far": curRange = new Range(RangeType.FAR);
                                    break;
                            }
                            break;
                        case "sightingRange":
                            curStage = Stage.SIGHTRANGE;
                            break;
                        case "attachments":
                            curAttachments = new Attachments();
                            break;
                        case "clip":
                            curAttachments.addClip();
                            break;
                        case "optics":
                            curAttachments.addOptics();
                            break;
                        case "materials":
                            curMaterials = new Materials();
                            break;
                        case "material":
                            curStage = Stage.MATERIAL;
                            break;
                    }
                    break;
                case  XMLStreamConstants.END_ELEMENT:
                    switch (reader.getLocalName()) {
                        case "gun":
                            Gun curGun = new Gun(curGunId, curModel, curHandy, curOrigin, curTtc, curMaterials);
                            guns.addGun(curGun);
                            break;
                        case "TTC":
                            curTtc = new TTC(curRange, curSightningRange, curAttachments);
                            break;
                        case "material":
                            curMaterials.addMaterial(curMaterial);
                            break;
                    }
                    break;
                case XMLStreamConstants.CHARACTERS:
                    switch (curStage) {
                        case MODEL:
                            curModel = reader.getText();
                            break;
                        case ORIGIN:
                            curOrigin = reader.getText();
                            break;
                        case SIGHTRANGE:
                            curSightningRange = new SightningRange(Integer.parseInt(reader.getText()));
                            break;
                        case MATERIAL:
                            curMaterial = new Material(reader.getText());
                            break;
                    }
                    curStage = Stage.NONE;
                    break;
            }
        }
        guns.getGun().sort(new GunCompatrator());
        f.close();

        Guns res = guns;
        guns = null;
        return res;
    }

}

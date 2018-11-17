package com.lab.post.parsers.sax;

import com.lab.post.xsd_eqv.Gun;
import com.lab.post.xsd_eqv.Guns;
import com.lab.post.xsd_eqv.gun.*;
import com.lab.post.xsd_eqv.gun.materials.*;
import com.lab.post.xsd_eqv.gun.ttc.*;
import com.lab.post.xsd_eqv.gun.ttc.range.*;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.helpers.XMLReaderFactory;

import java.io.FileReader;

public class SAXParser{

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
        XMLReader parser = XMLReaderFactory.createXMLReader();

        DefaultHandler handler = new DefaultHandler(){
            @Override
            public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
                super.startElement(uri, localName, qName, attributes);

                switch (localName) {
                    case "guns":
                        guns = new Guns();
                        break;
                    case "gun":
                        curGunId = Integer.parseInt(attributes.getValue(0));
                        break;
                    case "model":
                        curStage = Stage.MODEL;
                        break;
                    case "handy":
                        switch (attributes.getValue(0)) {
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
                        switch (attributes.getValue(0)) {
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
            }

            @Override
            public void endElement(String uri, String localName, String qName) throws SAXException {
                super.endElement(uri, localName, qName);

                switch (localName) {
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
            }

            @Override
            public void characters(char[] ch, int start, int length) throws SAXException {
                super.characters(ch, start, length);
                switch (curStage) {
                    case MODEL:
                        curModel = new String(ch, start, length);
                        break;
                    case ORIGIN:
                        curOrigin = new String(ch, start, length);
                        break;
                    case SIGHTRANGE:
                        curSightningRange = new SightningRange(Integer.parseInt(new String(ch, start, length)));
                        break;
                    case MATERIAL:
                        curMaterial = new Material(new String(ch, start, length));
                        break;
                }
                curStage = Stage.NONE;
            }
        };

        parser.setContentHandler(handler);
        parser.setErrorHandler(handler);

        FileReader f = new FileReader(xmlSource);
        parser.parse(new InputSource(f));
        guns.getGun().sort(new GunCompatrator());
        f.close();

        Guns res = guns;
        guns = null;
        return res;
    }
}


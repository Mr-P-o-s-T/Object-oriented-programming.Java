package com.lab.post.parsers.dom;

import com.lab.post.xsd_eqv.Gun;
import com.lab.post.xsd_eqv.Guns;
import com.lab.post.xsd_eqv.gun.GunCompatrator;
import com.lab.post.xsd_eqv.gun.Handy;
import com.lab.post.xsd_eqv.gun.materials.Material;
import com.lab.post.xsd_eqv.gun.ttc.Range;
import com.lab.post.xsd_eqv.gun.ttc.SightningRange;
import com.lab.post.xsd_eqv.gun.ttc.range.RangeType;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.FileReader;

public class DOMParser {

    public Guns parse(String xmlSource) throws Exception {
        DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();

        FileReader f = new FileReader(xmlSource);

        Document document = builder.parse(new InputSource(f));

        Element root = document.getDocumentElement();
        Guns guns = new Guns(root.getAttribute("xmlns"), root.getAttribute("xmlns:xsi"), root.getAttribute(
                "xsi:schemaLocation"));

        NodeList gun = root.getElementsByTagName("gun");

        for (int i = 0; i < gun.getLength(); i++) {
            Element curr = (Element)gun.item(i);
            int curGunId = Integer.parseInt(curr.getAttribute("id"));

            String curModel = curr.getElementsByTagName("model").item(0).getFirstChild().getNodeValue();

            Handy curHandy = null;
            switch (((Element)curr.getElementsByTagName("handy").item(0)).getAttribute("num")) {
                case "1":
                    curHandy = Handy.ONE;
                    break;
                case "2":
                    curHandy = Handy.TWO;
                    break;
            }

            String curOrigin = curr.getElementsByTagName("origin").item(0).getFirstChild().getNodeValue();

            Range curRange = null;
            switch (((Element)curr.getElementsByTagName("range").item(0)).getAttribute("type")) {
                case "near": curRange = new Range(RangeType.NEAR);
                    break;
                case "medium": curRange = new Range(RangeType.MEDIUM);
                    break;
                case "far": curRange = new Range(RangeType.FAR);
                    break;
            }

            SightningRange curSightningRange = new SightningRange(Integer.parseInt(curr.getElementsByTagName("sightingRange").item(
                    0).getFirstChild().getNodeValue()));

            guns.addGun(new Gun(curGunId, curModel, curHandy, curOrigin, curRange, curSightningRange));

            if (curr.getElementsByTagName("clip").getLength() != 0) guns.getGun().get(i).getTtc().getAttachments().
                    addClip();
            if (curr.getElementsByTagName("optics").getLength() != 0) guns.getGun().get(i).getTtc().getAttachments().
                    addOptics();

            NodeList maters = curr.getElementsByTagName("material");
            for (int j = 0; j < maters.getLength(); j++) {
                guns.getGun().get(i).getMaterials().addMaterial(new Material(maters.item(j).getFirstChild().
                        getNodeValue()));
            }
        }

        guns.getGun().sort(new GunCompatrator());
        f.close();

        Guns res = guns;
        guns = null;
        return res;
    }
}

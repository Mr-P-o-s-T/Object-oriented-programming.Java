 package com.lab.post;

import com.lab.post.parsers.dom.DOMParser;
import com.lab.post.parsers.sax.SAXParser;
import com.lab.post.parsers.stax.StAXParser;
import com.lab.post.xsd_eqv.Guns;

 class One {

     public One() { System.out.print(1); }

 }

 class Two extends One {

     public Two() { System.out.print(2); }

 }

 class Three extends Two {

     public Three() { System.out.print(3); }

 }

 class Numbers {

     public static void main(String[] argv) {

         new Three();

     } }

public class MainClass {

    private static final String xmlSource = "XML/input.xml";

    public static void main(String[] args) throws Throwable {

        SAXParser parser1 = new SAXParser();
        Guns res1 = parser1.parse(xmlSource);
        System.out.println("SAXParser:" + res1.toString() + '\n');

        DOMParser parser2 = new DOMParser();
        Guns res2 = parser2.parse(xmlSource);
        System.out.println("DOMParser:" + res2.toString() + '\n');

        StAXParser parser3 = new StAXParser();
        Guns res3 = parser3.parse(xmlSource);
        System.out.println("StAXParser:" + res3.toString() + '\n');
    }
}

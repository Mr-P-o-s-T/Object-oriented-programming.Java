package com.lab.post.parsers.stax;

import com.lab.post.parsers.dom.DOMParser;
import com.lab.post.xsd_eqv.Guns;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StAXParserTest {
    private static final String template = "\nGun(1){\n- Model: Model 4 Rifle\n- Handy: TWO\n- Origin: Country 1\n" +
            "- TTC:\n\t- Range: FAR\n\t- Sighting range: 800\n\t- Attachment:\n\t\t- Clip\n\t\t- Optics\n" +
            "- Materials:\n\t- Wood\n\t- Steel\n}\nGun(2){\n- Model: Model 2 SMG\n- Handy: TWO\n- Origin: Country 1\n" +
            "- TTC:\n\t- Range: NEAR\n\t- Sighting range: 150\n\t- Attachment:\n\t\t- None\n- Materials:\n" +
            "\t- Steel\n}\nGun(3){\n- Model: Model 1 Pistol\n- Handy: ONE\n- Origin: Country 1\n- TTC:\n" +
            "\t- Range: NEAR\n\t- Sighting range: 30\n\t- Attachment:\n\t\t- Clip\n- Materials:\n\t- Steel\n" +
            "\t- Wood\n}\nGun(4){\n- Model: Model 3 Automatic Rifle\n- Handy: TWO\n- Origin: Country 1\n" +
            "- TTC:\n\t- Range: MEDIUM\n\t- Sighting range: 450\n\t- Attachment:\n\t\t- None\n- Materials:\n" +
            "\t- Steel\n\t- Wood\n}\n}";

    @Test
    void parseTest() {
        DOMParser parser = new DOMParser();
        Guns guns = null;
        try {
            guns = parser.parse("XML/input.xml");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        assertTrue(guns.toString().equals(template));
    }
}
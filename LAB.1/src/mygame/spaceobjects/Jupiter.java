/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame.spaceobjects;

import com.jme3.app.LegacyApplication;
import com.jme3.font.BitmapFont;
import com.jme3.font.BitmapText;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.shape.Sphere;

/**
 *
 * @author Александр
 */
public class Jupiter extends ASpaceObject {
    
    private static final float maxDT = 25.0f, t = 100.0f;
    private float currT = 0.0f;

    private Node rootNode;
    public BitmapText speedGUI;
    
    public Jupiter(float mass, float radius, Vector3f startPosition, Vector3f 
            startSpeed, Vector3f startRotSpeed, Material mater, BitmapFont font) {
        super(mass, startPosition, startSpeed, startRotSpeed, new Geometry("Jupiter", 
                new Sphere(40, 40, radius)), mater);
        this.speedGUI = new BitmapText(font, false);
        speedGUI.setSize(font.getCharSet().getRenderedSize());
        speedGUI.setColor(ColorRGBA.White);
        speedGUI.setLocalTranslation(600, speedGUI.getHeight(), 0.0f);
    }

    @Override
    public void Translate(float dT, LegacyApplication app) {
        speedGUI.setText("Jupiter speed: " + speed.length());
        if (currT > maxDT) {
            currT -= maxDT;
            Geometry trace = new Geometry("", new Sphere(3, 3, 10.0f));
            trace.setLocalTranslation(geom.getWorldTranslation());
            trace.setMaterial(app.getAssetManager().loadMaterial("Materials/Trace material.j3m"));
            
            rootNode.attachChild(trace);
        }
        else currT += dT;
        super.Translate(dT, app); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void AttachYourselfTo(Node rootNode, Node guiNode) {
        this.rootNode = rootNode;
        super.AttachYourselfTo(rootNode, guiNode);
    }
    
    
}

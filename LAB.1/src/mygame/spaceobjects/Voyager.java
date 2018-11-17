/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame.spaceobjects;

import com.jme3.app.LegacyApplication;
import com.jme3.font.BitmapFont;
import com.jme3.font.BitmapText;
import com.jme3.input.InputManager;
import com.jme3.input.controls.AnalogListener;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.renderer.Camera;
import com.jme3.scene.CameraNode;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.control.CameraControl;
import com.jme3.scene.shape.Cylinder;
import com.jme3.scene.shape.Sphere;

/**
 *
 * @author Александр
 */
public class Voyager extends ASpaceObject implements AnalogListener {

    private static final float maxDT = 25.0f, t = 100.0f;
    private float currT = 0.0f;

    private Node rootNode;
    private Node voyagerNode = new Node();
    private Camera cam;
    CameraNode camNode;
    private InputManager inpMan;
    public BitmapText speedGUI;

    public Voyager(float mass, float radius, float height, Vector3f startPosition, Vector3f startSpeed, Vector3f startRotation,
            Material mater, Camera cam, InputManager inpMan, BitmapFont font) {
        super(mass, Vector3f.ZERO, startSpeed, startRotation, new Vector3f(),
                new Geometry("Voyager", new Cylinder(40, 40, radius, height,
                        true)),
                mater);
        this.cam = cam;
        this.inpMan = inpMan;
        this.voyagerNode.move(startPosition);
        this.speedGUI = new BitmapText(font, false);
        speedGUI.setSize(font.getCharSet().getRenderedSize());
        speedGUI.setColor(ColorRGBA.White);
        speedGUI.setLocalTranslation(300, speedGUI.getHeight(), 0.0f);
    }

    @Override
    public void Translate(float dT, LegacyApplication app) {
        speedGUI.setText("Voyager speed: " + speed.length());
        if (currT > maxDT) {
            currT -= maxDT;
            Geometry trace = new Geometry("", new Sphere(3, 3, 10.0f));
            trace.setLocalTranslation(geom.getWorldTranslation());
            trace.setMaterial(app.getAssetManager().loadMaterial("Materials/Trace material.j3m"));
            
            rootNode.attachChild(trace);
        }
        else currT += dT;
        voyagerNode.move(speed.mult(dT));
    }

    @Override
    public void AttachYourselfTo(Node rootNode, Node guiNode) {
        this.rootNode = rootNode;
        camNode = new CameraNode(null, cam);
        camNode.setControlDir(CameraControl.ControlDirection.SpatialToCamera);
        camNode.move(new Vector3f(-1500.0f, 0.0f, 0.0f));
        camNode.lookAt(geom.getWorldTranslation(), Vector3f.UNIT_Y);
        voyagerNode.attachChild(camNode);
        super.AttachYourselfTo(voyagerNode, guiNode);
        rootNode.attachChild(voyagerNode);
    }

    @Override
    public void onAnalog(String name, float value, float tpf) {
        if (name.equals("rotateUp")) {
            camNode.move(cam.getUp().normalize().negate().mult(t));
            camNode.lookAt(geom.getWorldTranslation(), cam.getUp());
        }
        if (name.equals("rotateDown")) {
            camNode.move(cam.getUp().normalize().mult(t));
            camNode.lookAt(geom.getWorldTranslation(), cam.getUp());
        }
        if (name.equals("rotateLeft")) {
            camNode.move(cam.getLeft().normalize().negate().mult(t));
            camNode.lookAt(geom.getWorldTranslation(), cam.getUp());
        }
        if (name.equals("rotateRight")) {
            camNode.move(cam.getLeft().normalize().mult(t));
            camNode.lookAt(geom.getWorldTranslation(), cam.getUp());
        }
        if (name.equals("zoomIn")) {
            Vector3f d = camNode.getLocalTranslation();
            float zoom = d.length() * 0.8f;
            if (zoom < 100.0f) zoom = 100.0f;
            camNode.setLocalTranslation(d.normalize().mult(zoom));
        }
        if (name.equals("zoomOut")) {
            Vector3f d = camNode.getLocalTranslation();
            float zoom = d.length() * 1.2f;
            camNode.setLocalTranslation(d.normalize().mult(zoom));
        }
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame.spaceobjects;

import com.jme3.app.LegacyApplication;
import com.jme3.material.Material;
import com.jme3.math.FastMath;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;

/**
 *
 * @author Александр
 */
public abstract class ASpaceObject {
    final static float G = 6.67408E-11f;
    
    protected float mass;
    protected Geometry geom;
    protected Material mater;
    protected Vector3f speed, rotSpeed;

    public ASpaceObject(float mass, Vector3f position, Vector3f 
            speed, Vector3f rotation, Vector3f rotSpeed, Geometry geom, 
            Material mater) {
        this.mass = mass;
        this.geom = geom;
        this.mater = mater;
        this.speed = speed;
        this.rotSpeed = rotSpeed;
        
        this.geom.move(position);
        this.geom.rotate(rotation.x, rotation.y, rotation.z);
        geom.setMaterial(mater);
    }

    public ASpaceObject(float mass, Vector3f position, Vector3f speed, Vector3f 
            rotSpeed, Geometry geom, Material mater) {
        this(mass, position, speed, new Vector3f(FastMath.DEG_TO_RAD * 90, 0, 0), rotSpeed, geom, mater);
    }
    
    public float getDistance(ASpaceObject b) {
        return b.geom.getWorldTranslation().subtract(
                geom.getWorldTranslation()).length();
    }
    
    public float getSquaredDistance(ASpaceObject b) {
        return b.geom.getWorldTranslation().subtract(
                geom.getWorldTranslation()).lengthSquared();
    }
    
    public Vector3f getOrth(ASpaceObject b) {
        return new Vector3f(b.geom.getWorldTranslation().subtract(
                geom.getWorldTranslation()).normalizeLocal());
    }
    
    public void Translate(float dT, LegacyApplication app) {
        geom.move(speed.mult(dT));
    }
    
    public void Rotate(float dT) {
        Vector3f tmp = rotSpeed.mult(dT);
        geom.rotate(tmp.x, tmp.y, tmp.z);
    }
    
    public void ChangeSpeed(float dT, ASpaceObject []gravBodies) {
        Vector3f gravity = new Vector3f();
        for (ASpaceObject gravBodie : gravBodies) {
            if (this != gravBodie) {
                gravity.addLocal(getOrth(gravBodie).mult(G * gravBodie.mass / 
                        getSquaredDistance(gravBodie)));
            }
        }
        
        speed.addLocal(gravity.mult(dT));
    }
    
    public void ChangeRotSpeed(Vector3f rotSpeed) {
        this.rotSpeed = rotSpeed;
    }
    
    public void AttachYourselfTo(Node rootNode, Node guiNode) {
        rootNode.attachChild(geom);
    }
}

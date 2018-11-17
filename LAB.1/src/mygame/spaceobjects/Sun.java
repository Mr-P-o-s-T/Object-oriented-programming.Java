/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame.spaceobjects;

import com.jme3.light.PointLight;
import com.jme3.material.Material;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.shape.Sphere;

/**
 *
 * @author Александр
 */
public class Sun extends ASpaceObject{
    private final PointLight sunLight;

    public Sun(float mass, float radius, Vector3f startRotSpeed, float brightness, Material 
            mater) {
        super(mass,  new Vector3f(), new Vector3f(), startRotSpeed, new 
        Geometry("Sun", new Sphere(40, 40, radius)), mater);
        sunLight = new PointLight(new Vector3f(), brightness);
    }

    @Override
    public void AttachYourselfTo(Node rootNode, Node guiNode) {
        super.AttachYourselfTo(rootNode, guiNode);
        rootNode.addLight(sunLight);
        
    }
    
}

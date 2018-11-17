package mygame;

import com.jme3.app.SimpleApplication;
import com.jme3.input.MouseInput;
import com.jme3.input.controls.*;
import com.jme3.math.FastMath;
import com.jme3.math.Vector3f;
import com.jme3.post.FilterPostProcessor;
import com.jme3.post.filters.BloomFilter;
import com.jme3.renderer.RenderManager;

import mygame.spaceobjects.*;

/**
 * This is the Main Class of your Game. You should only do initialization here.
 * Move your Logic into AppStates or Controls
 *
 * @author normenhansen
 */
public class Main extends SimpleApplication {

    private static final float timeSpeed = 100f;
    ASpaceObject[] spaceObjects;

    public static void main(String[] args) {
        Main app = new Main();
        app.start();
    }

    @Override
    public void simpleInitApp() {       
        cam.setFrustumFar(20E30f);

        spaceObjects = new ASpaceObject[3];

        spaceObjects[0] = new Sun(2E15f, 140.0f, new Vector3f(0.0f, 0.0f, 0.0f),
                10E30f, getAssetManager().loadMaterial(
                        "Materials/SunMaterial.j3m"));
        
        float angle = FastMath.DEG_TO_RAD * 77;
        spaceObjects[1] = new Jupiter(1.9E12f, 70.0f, new Vector3f(7.8E3f * FastMath.cos(angle), 0.0f,
                7.8E3f * FastMath.sin(angle)), new Vector3f(4.1f * FastMath.sin(angle), 0.0f, -4.1f * FastMath.cos(angle)), new Vector3f(0.0f, 0.0f, 0.0f),
                getAssetManager().loadMaterial(
                        "Materials/JupiterMaterial.j3m"), guiFont);

        spaceObjects[2] = new Voyager(1.0f, 10.0f, 50.0f, new Vector3f(-1000.0f,
                0.0f, 0.0f), new Vector3f(0.0f, 0.0f, 15.53f), new Vector3f(0.0f, 0.0f, 0.0f),
                getAssetManager().loadMaterial(
                        "Materials/VoyagerMaterial.j3m"), cam, inputManager, guiFont);
        
        guiNode.attachChild(((Jupiter)spaceObjects[1]).speedGUI);
        guiNode.attachChild(((Voyager)spaceObjects[2]).speedGUI);
        
        registerInput();
                
        for (ASpaceObject spaceObject : spaceObjects) {
            spaceObject.AttachYourselfTo(rootNode, guiNode);
        }
        
        this.flyCam.setEnabled(false);

        FilterPostProcessor fpp = new FilterPostProcessor(assetManager);
        BloomFilter bf = new BloomFilter(BloomFilter.GlowMode.Objects);
        fpp.addFilter(bf);
        viewPort.addProcessor(fpp);
    }

    @Override
    public void simpleUpdate(float tpf) {
        tpf *= timeSpeed;
        for (int i = 1; i < spaceObjects.length; i++) {
            spaceObjects[i].Translate(tpf, this);
            spaceObjects[i].ChangeSpeed(tpf, spaceObjects);
        }
    }

    public void registerInput() {
        inputManager.addMapping("rotateUp", new KeyTrigger(keyInput.KEY_UP), 
                new KeyTrigger(keyInput.KEY_W), new MouseAxisTrigger(MouseInput.AXIS_Y, false));
        inputManager.addMapping("rotateDown", new KeyTrigger(keyInput.KEY_DOWN),
                new KeyTrigger(keyInput.KEY_S), new MouseAxisTrigger(MouseInput.AXIS_Y, true));
        inputManager.addMapping("rotateRight", new KeyTrigger(
                keyInput.KEY_RIGHT), new KeyTrigger(keyInput.KEY_D), 
                new MouseAxisTrigger(MouseInput.AXIS_X, false));
        inputManager.addMapping("rotateLeft", new KeyTrigger(keyInput.KEY_LEFT),
                new KeyTrigger(keyInput.KEY_A), new MouseAxisTrigger(MouseInput.AXIS_X, true));
        inputManager.addMapping("zoomIn", new MouseAxisTrigger(MouseInput.AXIS_WHEEL, false));
        inputManager.addMapping("zoomOut", new MouseAxisTrigger(MouseInput.AXIS_WHEEL, true));
        inputManager.addListener((Voyager)spaceObjects[2], "rotateUp", "rotateDown", "rotateRight", 
                "rotateLeft", "zoomIn", "zoomOut");
    }
}

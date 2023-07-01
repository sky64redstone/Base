package de.base.game;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class GameHelper {
    private ArrayList<IGameObject>objects;
    // idx = layer num; values = "pointer" in the objects list
    private int[] layers;

    public GameHelper() {
        this.objects = new ArrayList<>();
        this.layers = new int[0];
    }

    public void render(Graphics g) {
        if (layers.length != objects.size())
            throw new IllegalStateException("The Sizes don't matches!");

        for (int i = 0; i < objects.size(); i++)
            objects.get(layers[i]).render(g);
    }

    public void tick(double timeElapsed) {
        objects.forEach(obj -> obj.tick(timeElapsed));
    }

    public void addObject(IGameObject obj) {
        addObject(obj, layers.length);
    }

    public void addObject(IGameObject obj, int layer) {
        int size = layers.length + 1;
        int[] newLayers = new int[size];
        short temp = 0;

        for (int i = 0; i < layers.length + temp; i++) {
            newLayers[i] = layers[i];
            if (i == layer) {
                newLayers[i] = objects.size();
                layer = -1;
                temp = 1;
            }
        }

        layers = newLayers;
        objects.add(objects.size(), obj);
    }

    ActionResult onMouseMove(MouseEvent e) {
        if (layers.length != objects.size())
            throw new IllegalStateException("The Sizes don't matches!");

        ActionResult lastResult = ActionResult.PASS;
        for (int i = 0; i < objects.size(); i++) {
            lastResult = objects.get(layers[i]).onMouseMove(e);

            if (lastResult == ActionResult.CONSUME || lastResult == ActionResult.FAILED)
                break;
        }
        return lastResult;
    }

    ActionResult onMousePress(MouseEvent e) {
        if (layers.length != objects.size())
            throw new IllegalStateException("The Sizes don't matches!");

        ActionResult lastResult = ActionResult.PASS;
        for (int i = 0; i < objects.size(); i++) {
            lastResult = objects.get(layers[i]).onMousePress(e);

            if (lastResult == ActionResult.CONSUME || lastResult == ActionResult.FAILED)
                break;
        }
        return lastResult;
    }

    ActionResult onMouseRelease(MouseEvent e) {
        if (layers.length != objects.size())
            throw new IllegalStateException("The Sizes don't matches!");

        ActionResult lastResult = ActionResult.PASS;
        for (int i = 0; i < objects.size(); i++) {
            lastResult = objects.get(layers[i]).onMouseRelease(e);

            if (lastResult == ActionResult.CONSUME || lastResult == ActionResult.FAILED)
                break;
        }
        return lastResult;
    }

    ActionResult onMouseClick(MouseEvent e) {
        if (layers.length != objects.size())
            throw new IllegalStateException("The Sizes don't matches!");

        ActionResult lastResult = ActionResult.PASS;
        for (int i = 0; i < objects.size(); i++) {
            lastResult = objects.get(layers[i]).onMouseClick(e);

            if (lastResult == ActionResult.CONSUME || lastResult == ActionResult.FAILED)
                break;
        }
        return lastResult;
    }
}

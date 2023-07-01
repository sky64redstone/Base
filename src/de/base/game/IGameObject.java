package de.base.game;

import java.awt.*;
import java.awt.event.MouseEvent;

public interface IGameObject {
    void render(Graphics g);
    void tick(double timeElapsed);

    ActionResult onMouseMove(MouseEvent e);
    ActionResult onMousePress(MouseEvent e);
    ActionResult onMouseRelease(MouseEvent e);
    ActionResult onMouseClick(MouseEvent e);
}

package de;

import de.base.awt.Colors;
import de.base.math.Maths3D;
import de.base.math.Matrix4d;
import de.base.math.Tuple4d;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MainV2 extends Frame {

    public static void main(String[] args) {
        new MainV2();
    }

    Matrix4d matrix;
    Object3d obj;

    public MainV2() {
        super("test");
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        this.setSize(Toolkit.getDefaultToolkit().getScreenSize());
        this.setUndecorated(true);
        this.setLocationRelativeTo(null);
        this.setBackground(Colors.darker_gray);
        this.setVisible(true);

        obj = new Object3d(100, 100, 200, 100, 100);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        matrix = Maths3D.createPerspectiveProjectionMatrix(0, getWidth() * 2, 0, getHeight() * 2, 100, 300, getWidth(), getHeight(), getWidth() * 1.2, getHeight() * 1.2);

        obj.draw(g, matrix);
    }

    class Object3d {
        int x, y, z, w, h;

        public Object3d(int x, int y, int z, int w, int h) {
            this.x = x;
            this.y = y;
            this.z = z;
            this.w = w;
            this.h = h;
        }

        public void draw(Graphics g, Matrix4d matrix) {
            Tuple4d pos = new Tuple4d(x, y, z, 1);
            pos = matrix.transform(pos);

            System.out.println(pos.toString());
            System.out.println(matrix);

            g.setColor(Colors.gray);
            g.drawRect((int) pos.x, (int) pos.y, w, h);
        }
    }
}

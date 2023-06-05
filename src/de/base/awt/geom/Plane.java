package de.base.awt.geom;

import de.base.math.Tuple3d;

/**
 * Plane == Ebene
 * @see de.base.math.Maths3D
 */
public class Plane {
    public Tuple3d v1;
    public Tuple3d v2;
    public Tuple3d nv;
    public Tuple3d p;

    public Plane(Tuple3d tuple1, Tuple3d tuple2, Tuple3d z) {
        p = z;

        v1 = tuple1;

        v2 = tuple2;

        nv = v1.crossProduct(v2);
    }
}

package de.base.math;

import de.base.awt.geom.Plane;

import org.jetbrains.annotations.NotNull;

public class Maths3D {

    public static @NotNull Tuple3d getRotationVector(@NotNull Tuple3d viewFrom, @NotNull Tuple3d viewTo) {
        double distanceX = Math.abs(viewFrom.x - viewTo.x); // the distance between viewFrom and viewTo (only the x coordinate)
        double distanceY = Math.abs(viewFrom.y - viewTo.y); // the distance between viewFrom and viewTo (only the y coordinate)
        double xRotation = distanceY / (distanceX + distanceY);
        double yRotation = distanceX / (distanceX + distanceY);

        if (viewFrom.y > viewTo.y)
            xRotation *= -1;
        if (viewFrom.x < viewTo.x)
            yRotation *= -1;

        return new Tuple3d(xRotation, yRotation, 0);
    }

    public static @NotNull Tuple2d calculatePositionP(Tuple3d pos, Tuple3d viewTo, Tuple3d w1, Tuple3d w2, double x, double y, double z, Plane p) {
        Tuple3d projP = getProj(pos, viewTo, x, y, z, p);
        return getDrawP(projP.x, projP.y, projP.z, w1, w2);
    }

    public static @NotNull Tuple2d getDrawP(double x, double y, double z, @NotNull Tuple3d w1, @NotNull Tuple3d w2) {
        double DrawX = w2.x * x + w2.y * y + w2.z * z;
        double DrawY = w1.x * x + w1.y * y + w1.z * z;
        return new Tuple2d(DrawX, DrawY);
    }

    public static @NotNull Tuple3d getProj(@NotNull Tuple3d pos, Tuple3d viewTo, double x, double y, double z, @NotNull Plane P) {
        Tuple3d ViewToPoint = new Tuple3d(x - pos.x, y - pos.y, z - pos.z);

        double t = (P.nv.x * P.p.x + P.nv.y * P.p.y + P.nv.z * P.p.z - (P.nv.x * pos.x + P.nv.y * pos.y + P.nv.z * pos.z)) / (P.nv.x * ViewToPoint.x + P.nv.y * ViewToPoint.y + P.nv.z * ViewToPoint.z);

        x = pos.x + ViewToPoint.x * t;
        y = pos.y + ViewToPoint.y * t;
        z = pos.z + ViewToPoint.z * t;

        return new Tuple3d(x, y, z);
    }

    public static void getPrederterminedInfo(@NotNull Tuple3d pos, @NotNull Tuple3d viewTo, double zoom) {
        Tuple3d viewVector = new Tuple3d(viewTo.x - pos.x, viewTo.y - pos.y, viewTo.z - pos.z);
        Tuple3d planeVector1 = viewVector.crossProduct(1, 1, 1);
        Tuple3d planeVector2 = viewVector.crossProduct(planeVector1);

        Plane p = new Plane(planeVector1, planeVector2, viewTo);

        Tuple3d rotationVector = getRotationVector(pos, viewTo);
        Tuple3d w1 = viewVector.crossProduct(rotationVector);
        Tuple3d w2 = viewVector.crossProduct(w1);

        Tuple2d calcFocusPos = calculatePositionP(pos, viewTo, w1, w2, viewTo.x, viewTo.y, viewTo.z, p);
        calcFocusPos.mul(zoom, zoom);
    }

    // Perspective Projection Matrix Helpers
    // update
    public static @NotNull Tuple2d getProjection(@NotNull Tuple3d pos, @NotNull Matrix4d projectionMatrix) {
        Tuple4d pos4 = new Tuple4d(pos.x, pos.y, pos.z, 1);

        pos4 = projectionMatrix.transform(pos4);

        return new Tuple2d(pos4.x, pos4.y);
    }

    public static @NotNull Matrix4d createPerspectiveProjectionMatrix(double minX, double maxX, double minY, double maxY, double minZ, double maxZ, double screenX, double screenY, double viewSizeX, double viewSizeY) {
        Matrix4d matrix;

        matrix = createSizeMatrix(screenX, screenY, viewSizeX, viewSizeY);

        matrix.mul(createOrthographicMatrix(minX, maxX, minY, maxY, minZ));

        matrix.mul(createPerspectiveMatrix(minZ, maxZ));

        return matrix;
    }

    public static @NotNull Matrix4d createPerspectiveMatrix(double minZ, double maxZ) {
        return new Matrix4d(
                minZ, 0, 0, 0,
                0, minZ, 0, 0,
                0, 0, maxZ + minZ, -maxZ,
                0, 0, 1, 0
        );
    }

    public static @NotNull Matrix4d createSizeMatrix(double screenX, double screenY, double viewSizeX, double viewSizeY) {
        return new Matrix4d(
                screenX / viewSizeX, 0, 0, 0,
                0, screenY / viewSizeY, 0, 0,
                0, 0, 0, 0,
                0, 0, 0, 1
        );
    }

    public static @NotNull Matrix4d createOrthographicMatrix(double minX, double maxX, double minY, double maxY, double minZ) {
        return new Matrix4d(
                1, 0, 0, -(minX + maxX) / 2d,
                0, 1, 0, -(minY + maxY) / 2d,
                0, 0, 1, -minZ,
                0, 0, 0, 1
        );
    }
}

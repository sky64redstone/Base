package de.base.math;

import java.util.Objects;

/*
World Transform: The world transform (or sometimes referred to as the object transform or model matrix) will transform a models vertices (and normals) from object space (this is the space that the model was created in using a 3D content creation tool like 3D Studio Max or Maya) into world space. World space is the position, orientation (and sometimes scale) that positions the model in the correct place in the world.
View Transform: The world space vertex positions (and normals) need to be transformed into a space that is relative to the view of the camera. This is called “view space” (sometimes referred to “camera space”) and is the transformation that will be studied in this article.
Projection Transform: Vertices that have been transformed into view space need to be transformed by the projection transformation matrix into a space called “clip space”. This is the final space that the graphics programmer needs to worry about. The projection transformation matrix will not be discussed in this article.
 */
/**
 * This class helps with any application of 3 dimensional calculations
 *
 * @author Jonathan
 */
public class Math3D {

    private Math3D() {
    }

    /**
     * fov stands for Field-of-View
     *
     * @param theta best case would be between 0 - 180 degrees
     * @return fov value
     */
    public static double getFOV(double theta) {
        return Math2D.cot(theta / 2.0d);
    }

    /**
     * This is the layout of the clip matrix. aspectRatio is Width/Height.
     * So the FOV for the x component is scaled based on FOV for y.
     * Far and near are coefficients which are the distances for the near and far clipping planes.
     * <pre>
     *
     * [fov * aspectRatio][        0        ][           0           ][  0 ]
     * [        0        ][       fov       ][           0           ][  0 ]
     * [        0        ][        0        ][ (far+near)/(far-near) ][ -1 ]
     * [        0        ][        0        ][(2*near*far)/(near-far)][  0 ]
     * </pre>
     *
     * @param screenWidth  the width of the screen
     * @param screenHeight the height of the screen
     * @param fov          the fov of the camera
     * @param far          the distance to the far clipping plane
     * @param near         the distance to the near clipping plane
     * @return a Matrix4d which can be used to transform a Vector4d
     * @see Math3D#getFOV(double)
     * @see Vector4d
     */
    public static Matrix4d getCameraTransformationMatrix(int screenWidth, int screenHeight, double fov, double far, double near) {
        double aspectRatio = (double) screenHeight / screenWidth;

        return new Matrix4d(
                fov * aspectRatio, 0, 0, 0,
                0, fov, 0, 0,
                0, 0, (far + near) / (far - near), -1,
                0, 0, (2 * near * far) / (near - far), 0
        );
    }

    /**
     * @param alpha the size of the angle at which the x-axis is to be rotated (also known as yaw)
     * @return a basic rotation matrix which rotates the x-axis
     */
    public static Matrix4d getXRotationMatrix(double alpha) {
        return new Matrix4d(
                1, 0, 0, 0,
                0, Math.cos(alpha), -Math.sin(alpha), 0,
                0, Math.sin(alpha), Math.cos(alpha), 0,
                0, 0, 0, 1
        );
    }

    /**
     * @param beta the size of the angle at which the y-axis is to be rotated (also known as pitch)
     * @return a basic rotation matrix which rotates the y-axis
     */
    public static Matrix4d getYRotationMatrix(double beta) {
        return new Matrix4d(
                Math.cos(beta), 0, Math.sin(beta), 0,
                0, 1, 0, 0,
                -Math.sin(beta), 0, Math.cos(beta), 0,
                0, 0, 0, 1
        );
    }

    /**
     * @param gamma the size of the angle at which the z-axis is to be rotated (also known as roll)
     * @return a basic rotation matrix which rotates the z-axis
     */
    public static Matrix4d getZRotationMatrix(double gamma) {
        return new Matrix4d(
                Math.cos(gamma), -Math.sin(gamma), 0, 0,
                Math.sin(gamma), Math.cos(gamma), 0, 0,
                0, 0, 1, 0,
                0, 0, 0, 1
        );
    }

    /**
     * @param alpha the size of the angle at which the x-axis is to be rotated (also known as yaw)
     * @param beta  the size of the angle at which the y-axis is to be rotated (also known as pitch)
     * @param gamma the size of the angle at which the z-axis is to be rotated (also known as roll)
     * @return a rotation matrix which rotates along all three axes
     * @see Math3D#getXRotationMatrix(double)
     * @see Math3D#getYRotationMatrix(double)
     * @see Math3D#getZRotationMatrix(double)
     */
    public static Matrix4d getRotationMatrix(double alpha, double beta, double gamma) {
        return getXRotationMatrix(alpha).mul(getYRotationMatrix(beta)).mul(getZRotationMatrix(gamma));
    }

    /**
     * An Identity Matrix returns the same vector given as parameter in {@link Matrix4d#transform(Vector4d)}
     * @return an Identity Matrix4d
     */
    public static Matrix4d getIdentityMatrix4d() {
        return new Matrix4d(
                1, 0, 0, 0,
                0, 1, 0, 0,
                0, 0, 1, 0,
                0, 0, 0, 1
        );
    }

    /**
     * An Identity Matrix returns the same vector given as parameter in {@link Matrix3d#transform(Vector3d)}
     * @return an Identity Matrix3d
     */
    public static Matrix3d getIdentityMatrix3d() {
        return new Matrix3d(
                1, 0, 0,
                0, 1, 0,
                0, 0, 1
        );
    }

    /**
     * An Identity Matrix returns the same vector given as parameter in {@link Matrix2d#transform(Vector2d)}
     * @return an Identity Matrix2d
     */
    public static Matrix2d getIdentityMatrix2d() {
        return new Matrix2d(
                1, 0,
                0, 1
        );
    }

    /**
     * This returned Matrix helps to move multiple Points with a vector
     *
     * @param vector the translation vector
     * @return a Matrix4d which can be used to move an object to another
     */
    public static Matrix4d getTranslationMatrix(Vector3d vector) {
        Objects.requireNonNull(vector);

        return new Matrix4d(
                1, 0, 0, 0,
                0, 1, 0, 0,
                0, 0, 1, 0,
                vector.x, vector.y, vector.z, 1
        );
    }

    /**
     *
     * @param pos the position of the camera
     * @param view the position of the view of the camera
     * @param up the up-vector of the camera (normally {0, 1, 0, 0})
     * @return a Matrix4d which can be used to create a View Matrix with a quick Inverse
     * @see Matrix4d#quickInverse()
     */
    public static Matrix4d pointMatrix(Vector4d pos, Vector4d view, Vector4d up) {
        Objects.requireNonNull(view);
        Objects.requireNonNull(up);
        // calculate forward direction (like the param view)
        Vector4d forward = view.subtract(pos).norm();
        // calculate new Up direction
        Vector4d a = forward.multiply(up.dotProduct(forward));
        Vector4d newUp = up.subtract(a).norm();
        // calculate right direction
        Vector4d right = newUp.crossProduct(forward);
        // construct dimensioning and translation matrix
        return new Matrix4d(
                right.x, right.y, right.z, 0,
                newUp.x, newUp.y, newUp.z, 0,
                forward.x, forward.y, forward.z, 0,
                pos.x, pos.y, pos.z, 1
        );
    }

    /**
     * This returned Matrix helps to transform Vectors from world space into view space
     * @param pos the position of the camera
     * @param view the position of the view of the camera
     * @param up the up-vector of the camera (normally {0, 1, 0, 0})
     * @return a Matrix4d which can be used to transform world space into view space
     * @see Math3D#pointMatrix(Vector4d, Vector4d, Vector4d)
     */
    public static Matrix4d getViewMatrix(Vector4d pos, Vector4d view, Vector4d up) {
        return pointMatrix(pos, view, up).quickInverse();
    }

    /**
     * Calculates the Position of a point with the latitude, the longitude and the height.
     * The origin is in the middle of the earth and the unit is set to one meter.
     * In addition, in order to make the reference to reality easier,
     * we define that the X axis should intersect the equator on the zero meridian,
     * and the Z axis passes through the poles. In a {@code Right-handed} system,
     * the Y-axis runs through the 90°-East meridian (somewhere in the Indian Ocean).
     *
     * @param earthRadius the radius of the earth in meters (Scale 1:1 -> 6378137 m)
     * @param lat         the latitude of the point
     * @param lon         the longitude of the point
     * @param height      the height above sea level of the point
     * @return a Vector which describes the Position of the point in a normal 3 dimensional Coordinate system
     */
    public static Vector3d getCartesianCoordinates(double earthRadius, double lat, double lon, double height) {
        return new Vector3d(
                (earthRadius + height) * Math.cos(lat) * Math.cos(lon),
                (earthRadius + height) * Math.cos(lat) * Math.sin(lon),
                (earthRadius + height) * Math.sin(lat));
    }
}

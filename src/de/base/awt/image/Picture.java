package de.base.awt.image;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.imageio.ImageIO;
import java.awt.Image;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * This class has many possibilities wich you can do with {@link BufferedImage}
 * @see Graphics2D
 * @see BufferedImage
 */
public class Picture {

    /**
     * @param imagePathToRead that's the {@link BufferedImage} to resize
     * @param resizeWidth shows how big the new width of the {@link BufferedImage} should be
     * @param resizeHeight shows how tall the new height of the {@link BufferedImage} should be
     * @return a new {@link BufferedImage}, wich is resized with the parameters resizeWidth and resizeHeight
     */
    public static @NotNull BufferedImage resizeFile(String imagePathToRead, int resizeWidth, int resizeHeight) throws IOException {
        File fileToRead = new File(imagePathToRead);
        BufferedImage bufferedImageInput = ImageIO.read(fileToRead);

        BufferedImage bufferedImageOutput = new BufferedImage(resizeWidth,
                resizeHeight, bufferedImageInput.getType());

        Graphics2D g2d = bufferedImageOutput.createGraphics();
        g2d.drawImage(bufferedImageInput, 0, 0, resizeWidth, resizeHeight, null);
        g2d.dispose();

        return bufferedImageOutput;
    }

    /**
     * @param imageToRead that's the {@link BufferedImage} to resize
     * @param resizeWidth shows how big the new width of the {@link Image} should be
     * @param resizeHeight shows how tall the new height of the {@link Image} should be
     * @return a new {@link Image}, wich is resized with the parameters resizeWidth and resizeHeight
     */
    public static Image resizeFile(@NotNull BufferedImage imageToRead, int resizeWidth, int resizeHeight) {
        return imageToRead.getScaledInstance(resizeWidth, resizeHeight, Image.SCALE_DEFAULT);
    }

    /**
     * @param imageToRotate that's the {@link BufferedImage} to rotate
     * @param rotation that's how far the {@link BufferedImage} should be rotated
     * @returna new {@link BufferedImage}, which is rotated with the parameter imageToRotate
     */
    public static @NotNull BufferedImage rotateImage(@NotNull BufferedImage imageToRotate, int rotation) {
        int widthOfImage = imageToRotate.getWidth();
        int heightOfImage = imageToRotate.getHeight();
        int typeOfImage = imageToRotate.getType();

        BufferedImage newImageFromBuffer = new BufferedImage(widthOfImage, heightOfImage, typeOfImage);

        Graphics2D graphics2D = newImageFromBuffer.createGraphics();

        graphics2D.rotate(Math.toRadians(rotation), widthOfImage / 2, heightOfImage / 2);
        graphics2D.drawImage(imageToRotate, null, 0, 0);

        return newImageFromBuffer;
    }

    /**
     *
     * @param image the {@link File} which contains a Image
     * @return a {@link BufferedImage}, which is read from the file
     * @throws RuntimeException when normaly a {@link IOException} would be thrown
     */
    public static @Nullable BufferedImage getImage(File image) {
        try {
            return ImageIO.read(image);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static BufferedImage clippXY(BufferedImage image, int width, int height) {
        if (width < height) {
            int imageWidth = height * width / height;
            return image.getSubimage(image.getWidth() - imageWidth, 0, imageWidth, image.getHeight());
        }
        int imageHeight = width * height / width;
        return image.getSubimage(0, image.getHeight() - imageHeight, image.getWidth(), imageHeight);
    }
}

package org.fxmisc.richtext.demo.richtext;

import java.io.File;
import java.util.function.Consumer;

import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


/**
 * A custom object which contains a file path to an image file.
 * When rendered in the rich text editor, the image is loaded from the
 * specified file.
 */
public class RealLinkedImage implements LinkedImage {

    private final String imagePath;
    private ImageView imageView;
    private Consumer<ImageView> clickFun;

    /**
     * Creates a new linked image object.
     *
     * @param imagePath The path to the image file.
     */
    public RealLinkedImage(String imagePath) {

        // if the image is below the current working directory,
        // then store as relative path name.
        String currentDir = System.getProperty("user.dir") + File.separatorChar;
        if (imagePath.startsWith(currentDir)) {
            imagePath = imagePath.substring(currentDir.length());
        }

        this.imagePath = imagePath;
    }

    public RealLinkedImage(String imagePath, Consumer<ImageView> clickFun) {
        this(imagePath);
        this.clickFun = clickFun;
    }

    @Override
    public boolean isReal() {
        return true;
    }

    @Override
    public String getImagePath() {
        return imagePath;
    }

    @Override
    public String toString() {
        return String.format("RealLinkedImage[path=%s]", imagePath);
    }

    @Override
    public Node createNode() {
        Image image = new Image("file:" + imagePath); // XXX: No need to create new Image objects each time -
                                                      // could be cached in the model layer
        imageView = new ImageView(image);
        imageView.setOnMouseClicked(event -> clickFun.accept(imageView));
        imageView.setCursor(Cursor.HAND);
        return imageView;
    }

}

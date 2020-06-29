package renderer;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * class that create an image, we brought it from the moodle
 *
 */
public class ImageWriter {

	private int _imageWidth, _imageHeight;
	private int _Nx, _Ny;

	final String PROJECT_PATH = System.getProperty("user.dir");

	private BufferedImage _image;

	private String _imageName;

	// ***************** Constructors ********************** //

	/**
	 * c-tor of imageWriter
	 */
	public ImageWriter(String imageName, int width, int height, int Nx, int Ny) {
		_imageName = imageName;
		_imageWidth = width;
		_imageHeight = height;
		_Nx = Nx;
		_Ny = Ny;

		_image = new BufferedImage(_imageWidth, _imageHeight, BufferedImage.TYPE_INT_RGB);
	}

	/**
	 * c-tor of imageWriter
	 */
	public ImageWriter(ImageWriter imageWriter) {
		this(imageWriter._imageName, imageWriter._imageWidth, imageWriter._imageHeight, imageWriter._Nx,
				imageWriter._Ny);
	}

	// ***************** Getters/Setters ********************** //

	/**
	 * return the width of the image
	 * 
	 * @return number
	 */
	public int getWidth() {
		return _imageWidth;
	}

	/**
	 * return the height of the image
	 * 
	 * @return number
	 */
	public int getHeight() {
		return _imageHeight;
	}

	/**
	 * returns the number of pixels in axis "y" of the image
	 * 
	 * @return number
	 */
	public int getNy() {
		return _Ny;
	}

	/**
	 * returns the number of pixels in axis "x" of the image
	 * 
	 * @return number
	 */
	public int getNx() {
		return _Nx;
	}

	/**
	 * set the number of pixels in axis "y" of the image
	 */
	public void setNy(int Ny) {
		_Ny = Ny;
	}

	/**
	 * set the number of pixels in axis "x" of the image
	 */
	public void setNx(int Nx) {
		_Nx = Nx;
	}

	// ***************** Operations ******************** //

	/**
	 * writes the image
	 */
	public void writeToimage() {
		File ouFile = new File(PROJECT_PATH + "/src/images/" + _imageName + ".jpg");

		try {
			ImageIO.write(_image, "jpg", ouFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * paint a pixel
	 */
	public void writePixel(int xIndex, int yIndex, int r, int g, int b) {
		int rgb = new Color(r, g, b).getRGB();
		_image.setRGB(xIndex, yIndex, rgb);
	}

	/**
	 * paint a pixel
	 */
	public void writePixel(int xIndex, int yIndex, int[] rgbArray) {
		int rgb = new Color(rgbArray[0], rgbArray[1], rgbArray[2]).getRGB();
		_image.setRGB(xIndex, yIndex, rgb);
	}

	/**
	 * paint a pixel
	 */
	public void writePixel(int xIndex, int yIndex, Color color) {
		_image.setRGB(xIndex, yIndex, color.getRGB());
	}

}
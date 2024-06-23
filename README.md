# Image Editor

# Table of Contents:-

- [Introduction](#introduction)
- [How it works?](#how-it-works)
- [Key Features](#key-features)
  - [Image Operations](#image-operations-this-cli-supports-a-variety-of-image-operations-allowing-you-to-transform-your-images-in-numerous-ways-some-of-the-supported-operations-include)
        - [Safe file Handling](#safe-file-handling-your-original-image-remains-untouched-the-program-ensures-that-the-edited-image-is-saved-with-a-new-filename-preventing-any-accidental-overwrites)
- [Code Explanation](#code-explanation)
- [Examples](#examples)
- [Future Plans](#future-plans)

# Introduction

Welcome to the Image Editor Command Line Interface (CLI)! This Java-based program provides a versatile and user-friendly way to perform various image editing tasks from your command line. It's designed to be easy to use and relies on the built-in Java libraries for image processing.

# How it works?
The Image Editor CLI operates on a simple principle: it takes as input the path to an image file and a command indicating the desired image operation. Once you specify these parameters, the program works its magic, executes the chosen operation on the image, and finally saves the result to a new file.

# Key features

- Here are some of the core features and functionalities that the Image Editor CLI offers:

# Image Operations: This CLI supports a variety of image operations, allowing you to transform your images in numerous ways. Some of the supported operations include:

> Adjusting Brightness
>Flipping (Horizontal and Vertical)
>Rotating (90 degrees anti-clockwise and Clockwise)
>Converting to Gray Scale
>Applying a Blur Effect

# Safe File Handling: Your original image remains untouched. The program ensures that the edited image is saved with a new filename, preventing any accidental overwrites.

# Code Explanation

The import statements include classes and packages necessary for working with images, reading user input from the command line, and handling file operations.

The Utilities class contains two static methods:
readImage(String path): Reads an image from the specified file path and returns it as a BufferedImage.
saveImage(BufferedImage inp, String path, String format): Saves the input BufferedImage to the specified file path with the given image format.

The Editor class contains static methods for various image editing operations like grayscale conversion, flipping, rotation, brightness adjustment, and Gaussian blur.

1) greyScale(BufferedImage inp):
This method takes a BufferedImage named inp as input.
It creates a new BufferedImage named out with the same dimensions as inp, but with a type of BufferedImage.TYPE_BYTE_GRAY. This type represents a grayscale image.
It then iterates through each pixel of the input image (inp) and copies the pixel's color value to the corresponding pixel in the output image (out), effectively converting the input image to grayscale.
Finally, it returns the grayscale image (out).

2) hozInv(BufferedImage inp):
This method takes a BufferedImage named inp as input.
It creates a new BufferedImage named out with the same dimensions and type as inp.
It then performs a horizontal inversion of the input image by copying the pixels from inp to out in reverse order along the horizontal axis.
The result is an image where the leftmost pixel becomes the rightmost pixel, and vice versa.
It returns the horizontally inverted image (out).

3) verInv(BufferedImage inp):
This method is similar to hozInv, but it performs a vertical inversion of the input image.
It creates a new BufferedImage named out with the same dimensions and type as inp.
It copies the pixels from inp to out in reverse order along the vertical axis.
The result is an image where the topmost pixel becomes the bottommost pixel, and vice versa.
It returns the vertically inverted image (out).

4) rotate90ACW(BufferedImage inp):
This method rotates the input image 90 degrees counter-clockwise (ACW).
It creates a new BufferedImage named out with dimensions swapped (width becomes height, and vice versa) compared to inp.
It then iterates through each pixel in inp and copies it to the corresponding location in out, effectively rotating the image 90 degrees counterclockwise.
After the rotation, it performs a vertical inversion using the verInv method to correct the orientation.
It returns the rotated image (out).

5) rotate90CW(BufferedImage inp):
This method rotates the input image 90 degrees clockwise (CW).
It creates a new BufferedImage named out with dimensions swapped compared to inp.
It iterates through each pixel in inp and copies it to the corresponding location in out, effectively rotating the image 90 degrees clockwise.
After the rotation, it performs a horizontal inversion using the hozInv method to correct the orientation.
It returns the rotated image (out).

6) changeBrightness(BufferedImage inp, int factor):
This method takes a BufferedImage named inp and an integer factor as input.
It creates a new BufferedImage named out with the same dimensions and type as inp.
It iterates through each pixel in inp, extracts the red, green, and blue components of the pixel's color, and adjusts their intensities based on the factor.
The factor is used to increase or decrease the brightness of the image. A positive factor brightens the image, while a negative factor darkens it.
The resulting pixel color values are clamped to the range [0, 255] to ensure they remain valid RGB values.
The adjusted color values are used to set the corresponding pixel in the output image (out).
It returns the image with adjusted brightness (out).

7) static BufferedImage gaussianBlur(BufferedImage inpimage, int radius):
This method takes two parameters: a BufferedImage named inpimage and an integer radius that determines the size of the blur kernel.
It creates a new BufferedImage named blurredImage with the same dimensions and type as the input image (inpimage).
The method then iterates through each pixel of the input image and performs Gaussian blur on it. Gaussian blur is a common image processing technique used to reduce noise and smooth images.
For each pixel, it calculates the average color value within a square neighborhood of pixels with a side length of 2 * radius + 1. This neighborhood is centered around the current pixel.
The for loops with dy and dx are used to iterate through the neighborhood. newX and newY are calculated to ensure that the neighborhood stays within the bounds of the image.
It calculates the average red, green, and blue components of the colors within the neighborhood and creates a new Color object with these averaged values.
Finally, it sets the color of the corresponding pixel in the blurredImage with the averaged color.
The result is a new image that has undergone Gaussian blur, effectively smoothing the image.
The blurred image (blurredImage) is returned as the output.

# Examples

1) Input Image

![inputFile](/Editor.jpeg)

2) Gray Scale

![grayScale](/Greyscale.jpeg)

3) Horizontal Flip
![horizontal Invert](/HorFlip.jpeg)

4) Vertical Flip
![Vertical Flip](/VertFlip.jpeg)

5) Anti-clockwise Rotate
![AntiCloseWise Rotate](/AntiCW.jpeg)

6) Clockwise Rotate
![Clockwise Rotate](/90ClockWise.jpeg)

7) Blur
![Blur](/Blurr.jpeg)

8) Brightness
![Brightness](/IncBrightness.jpeg)


# Future Plans

What's to Come?
As we continue to develop and refine our CLI, we have exciting plans for its future. We're committed to expanding its feature set, making it an even more indispensable tool for image enthusiasts. Stay tuned for upcoming updates and enhancements that will take your image editing experience to new heights.

Whether you're a seasoned professional or a curious beginner, the Image Editor Command Line Interface is your passport to a world of creative possibilities. Join us on this journey as we explore its usage, dive into its features, understand its underlying code, and witness real-world examples of its capabilities. The world of image editing is at your command!
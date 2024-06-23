import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

class Utilities{
    static BufferedImage readImage(String path) throws IOException{
        return ImageIO.read(new File(path));
    }

    static void saveImage(BufferedImage inp, String path, String format) throws IOException{
        File outFile = new File(path);
        ImageIO.write(inp, format, outFile);
    }
}

class Editor {
    static BufferedImage greyScale(BufferedImage inp) {
        int height = inp.getHeight();
        int width = inp.getWidth();
        BufferedImage out = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_GRAY);
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                out.setRGB(x, y, inp.getRGB(x, y));
            }
        }
        return out;
    }

    static BufferedImage hozInv(BufferedImage inp) {
        int height = inp.getHeight();
        int width = inp.getWidth();
        BufferedImage out = new BufferedImage(width, height, inp.getType());
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int ogPixel = inp.getRGB(x, y);
                int invX = width - x - 1;
                out.setRGB(invX, y, ogPixel);
            }
        }
        return out;
    }

    static BufferedImage verInv(BufferedImage inp) {
        int height = inp.getHeight();
        int width = inp.getWidth();
        BufferedImage out = new BufferedImage(width, height, inp.getType());
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int ogPixel = inp.getRGB(x, y);
                int invY = height - y - 1;
                out.setRGB(x, invY, ogPixel);
            }
        }
        return out;
    }

    static BufferedImage rotate90ACW(BufferedImage inp) {
        int height = inp.getHeight();
        int width = inp.getWidth();
        BufferedImage out = new BufferedImage(height, width, inp.getType());
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int pixel = inp.getRGB(x, y);
                out.setRGB(y, x, pixel);
            }
        }
        out = verInv(out);
        return out;
    }

    static BufferedImage rotate90CW(BufferedImage inp) {
        int height = inp.getHeight();
        int width = inp.getWidth();
        BufferedImage out = new BufferedImage(height, width, inp.getType());
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int pixel = inp.getRGB(x, y);
                out.setRGB(y, x, pixel);
            }
        }
        out = hozInv((out));
        return out;
    }


    static BufferedImage changeBrightness(BufferedImage inp, int factor) {
        int height = inp.getHeight();
        int width = inp.getWidth();
        BufferedImage out = new BufferedImage(width, height, inp.getType());
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                Color pixel = new Color(inp.getRGB(x, y));

                int red = pixel.getRed();
                int green = pixel.getGreen();
                int blue = pixel.getBlue();

                int nRed = Math.min(255, Math.max(0, (red + (red * factor) / 100)));
                int nBlue = Math.min(255, Math.max(0, (blue + (blue * factor) / 100)));
                int nGreen = Math.min(255, Math.max(0, (green + (green * factor) / 100)));

                Color newPix = new Color(nRed, nGreen, nBlue);

                out.setRGB(x, y, newPix.getRGB());
            }
        }
        return out;
    }

    static BufferedImage gaussianBlur(BufferedImage inpimage, int radius) {
        BufferedImage blurredImage = new BufferedImage(inpimage.getWidth(), inpimage.getHeight(), inpimage.getType());
        int height = inpimage.getHeight();
        int width = inpimage.getWidth();
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int sumRed = 0, sumGreen = 0, sumBlue = 0, pixels = 0;
                for (int dy = -radius; dy <= radius; dy++) {
                    for (int dx = -radius; dx <= radius; dx++) {
                        int newX = Math.min(Math.max(x + dx, 0), width - 1);
                        int newY = Math.min(Math.max(y + dy, 0), height - 1);
                        Color pix = new Color(inpimage.getRGB(newX, newY));
                        sumRed += pix.getRed();
                        sumGreen += pix.getGreen();
                        sumBlue += pix.getBlue();
                        pixels++;
                    }
                }
                int avgRed = sumRed / pixels;
                int avgGreen = sumGreen / pixels;
                int avgBlue = sumBlue / pixels;
                Color blurred = new Color(avgRed, avgGreen, avgBlue);
                blurredImage.setRGB(x, y, blurred.getRGB());
            }
        }
        return blurredImage;
    }
}
    public class JVMP {
        public static void main(String[] args) throws IOException {
            System.out.println("Hello there!! Welcome to my Image Editor");
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Enter the path of your input image.");
            String path = br.readLine();
            BufferedImage inp = Utilities.readImage(path);
            System.out.println("Which operation would you like to perform.");
            System.out.println("1. GreyScale");
            System.out.println("2. Change Brightness");
            System.out.println("3. Rotate 90 degrees ClockWise");
            System.out.println("4. Horizontal Flip");
            System.out.println("5. Vertical Flip");
            System.out.println("6. Gaussian Blur");
            System.out.println("7. Rotate 90 degrees AntiClockwise");
            BufferedImage out = null;
            int option = Integer.parseInt(br.readLine());
            boolean edited = true;
            switch (option) {
                case 1 -> out = Editor.greyScale(inp);
                case 2 -> {
                    System.out.println("Enter the factor by which you want to change the brightness.");
                    System.out.println("Note : For increasing the brightness enter a positive value and for decreasing it enter a negative value.");
                    int factor = Integer.parseInt(br.readLine());
                    out = Editor.changeBrightness(inp, factor);
                }
                case 3 -> out = Editor.rotate90CW(inp);
                case 4 -> out = Editor.hozInv(inp);
                case 5 -> out = Editor.verInv(inp);
                case 6 -> {
                    System.out.println("Enter the strength of your blur.");
                    out = Editor.gaussianBlur(inp, Integer.parseInt(br.readLine()));
                }
                case 7-> out = Editor.rotate90ACW(inp);
                default -> {
                    System.out.println("The option you entered is invalid.");
                    edited = false;
                }
            }

            if (edited) {
                System.out.println("Enter the path for your output image");
                String outpath = br.readLine();
                System.out.println("Enter the format for your image.");
                String format = br.readLine();
                Utilities.saveImage(out, outpath, format);
            }
        }
    }
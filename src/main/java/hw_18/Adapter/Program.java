package hw_18.Adapter;

public class Program {
    public static void main(String[] args) {
        Brush drawing = new Brush();
        ColorHex hex = new ColorHex("234566");
        ColorRGB rgb = new ColorRGB(35, 69, 102);
        ColorHexAdapter rgbAdapt = new ColorHexAdapter(rgb);
        drawing.draw(hex);
        drawing.draw(rgbAdapt);
    }
}

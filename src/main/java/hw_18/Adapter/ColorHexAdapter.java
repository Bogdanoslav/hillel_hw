package hw_18.Adapter;

public class ColorHexAdapter extends ColorHex {
    private String hex;
    private ColorRGB colorRGB;
    public ColorHexAdapter(ColorRGB colorRGB){
        this.colorRGB = colorRGB;
    }
    @Override
    public String getHex(){
        hex = String.format("%02x%02x%02x", colorRGB.getR(), colorRGB.getG(), colorRGB.getB());
        return hex;
    }
}

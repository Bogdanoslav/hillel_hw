package hw_18.Adapter;

public class Brush {
    public void draw(ColorHex c){
        System.out.println("Произошло раскрашивание цветом #" + c.getHex());
    }
}

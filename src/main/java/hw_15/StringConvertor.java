package hw_15;

import java.awt.*;
import java.awt.image.BufferedImage;

public class StringConvertor {
    private int width = 70;
    private int height = 20;
    private int str;
    private BufferedImage bufferedImage;

    public StringConvertor(int width, int height, int[] arr){
        String str = "";
        for(int i = 0; i < arr.length; i++){
            str+=arr[i];
        }
        bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics graphics = bufferedImage.getGraphics();
        Graphics2D graphics2D = (Graphics2D) graphics;
        graphics2D.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
                RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        graphics2D.setColor(Color.YELLOW);
        graphics2D.drawString(str, 1, 12);

    }
    public void Draw(String ch){
        for (int y = 0; y < bufferedImage.getHeight(); y++) {
            StringBuilder stringBuilder = new StringBuilder();

            for (int x = 0; x < bufferedImage.getWidth(); x++) {
                stringBuilder.append(bufferedImage.getRGB(x, y) == -16777216 ? " " : ch);
            }

            if (stringBuilder.toString().trim().isEmpty()) {
                continue;
            }

            System.out.println(stringBuilder);
        }
    }

}


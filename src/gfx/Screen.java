/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gfx;

/**
 *
 * @author AHMADSYAFIQKAMIL
 */
public class Screen {

    public static final int MAP_WIDTH = 64;
    public static final int MAP_WIDTH_MASK = MAP_WIDTH - 1;
    public int[] tiles = new int[MAP_WIDTH * MAP_WIDTH];
    public int[] colours = new int[MAP_WIDTH * MAP_WIDTH * 4];
    public int xOffset = 0;
    public int yOffset = 0;
    public int width = 0;
    public int heigth = 0;
    public SpriteSheet sheet;

    public Screen(int width, int heigth, SpriteSheet sheet) {
        this.width = width;
        this.heigth = heigth;
        this.sheet = sheet;

        for (int i = 0; i < MAP_WIDTH * MAP_WIDTH; i++) {
            colours[i * 4 + 0] = 0xff00ff;
            colours[i * 4 + 1] = 0x00ffff;
            colours[i * 4 + 2] = 0xffff00;
            colours[i * 4 + 3] = 0xffffff;

        }
    }

    public void render(int[] pixels, int offset, int row) {
        for (int yTiles = yOffset << 3; yTiles <= (yOffset + heigth) >> 3; yTiles++) {
            int yMin = yTiles * 8 - yOffset;
            int yMax = yMin + 8;
            if (yMin < 0) {
                yMin = 0;
            }
            if (yMax > heigth) {
                yMax = heigth;
            }
            for (int xTiles = xOffset << 3; xTiles <= (xOffset + width) >> 3; xTiles++) {
                int xMin = xTiles * 8 - yOffset;
                int xMax = xMin + 8;
                if (xMin < 0) {
                    xMin = 0;
                }
                if (xMax > width) {
                    xMax = width;
                }
                
                int tileIndex = (xTiles&(MAP_WIDTH_MASK)+(yTiles&(MAP_WIDTH_MASK))*MAP_WIDTH);
                for (int y = yMin; y < yMax; y++) {
                    int sheetPixel = ((y+yOffset)&7)*sheet.width+((xMin+xOffset)&7);
                    int tilePixel = offset + xMin+y*row;
                    for (int x = xMin; x < xMax; x++) {
                        int colour = tileIndex*4+sheet.pixels[sheetPixel++];
                        pixels[tilePixel++]=colours[colour++];
                    }
                }
            }
            
        }

    }
}

package Graph;

import java.util.Arrays;

/*
Flood Fill

An image is represented by an m x n integer grid image where image[i][j] represents the pixel value of the image.
You are also given three integers sr, sc, and color.
You should perform a flood fill on the image starting from the pixel image[sr][sc].
To perform a flood fill, consider the starting pixel,
plus any pixels connected 4-directionally to the starting pixel of the same color as the starting pixel,
plus any pixels connected 4-directionally to those pixels (also with the same color), and so on.
Replace the color of all the aforementioned pixels with color.
Return the modified image after performing the flood fill.
Example 1:
Input: image = [[1,1,1],[1,1,0],[1,0,1]], sr = 1, sc = 1, color = 2
Output: [[2,2,2],[2,2,0],[2,0,1]]
Explanation: From the center of the image with position (sr, sc) = (1, 1) (i.e., the red pixel), all pixels connected by a path of the same color as the starting pixel (i.e., the blue pixels) are colored with the new color.
Note the bottom corner is not colored 2, because it is not 4-directionally connected to the starting pixel.
Example 2:
Input: image = [[0,0,0],[0,0,0]], sr = 0, sc = 0, color = 0
Output: [[0,0,0],[0,0,0]]
Explanation: The starting pixel is already colored 0, so no changes are made to the image.
*/
public class FloodFill {

    //Time Complexity: O(NxM + NxMx4) ~ O(N x M)
    //For the worst case, all of the pixels will have the same colour,
    //so DFS function will be called for (N x M) nodes and for every node we are traversing for 4 neighbours,
    //so it will take O(N x M x 4) time.
    //Space Complexity: O(N x M) + O(N x M)
    //O(N x M) for copied input array and recursive stack space takes up N x M locations at max.
    public int[][] floodFill(int[][] image, int sr, int sc, int color) {
        int n = image.length;
        int m = image[0].length;
        int[][] resultantImage = new int[n][m];
        //Creating copy of existing image matrix
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                resultantImage[i][j] = image[i][j];
            }
        }
        int initialColor = image[sr][sc];
        //To calculate neighbouring grid positions of the image matrix
        int[] deltaRow = {-1, 0, +1, 0};
        int[] deltaColumn = {0, +1, 0, -1};
        dfs(sr, sc, resultantImage, image, deltaRow, deltaColumn, initialColor, color);
        return resultantImage;
    }

    private void dfs(int row, int column, int[][] resultantImage, int[][] image, int[] deltaRow, int[] deltaColumn, int initialColor, int newColor) {
        int n = image.length;
        int m = image[0].length;
        resultantImage[row][column] = newColor;
        //looking for vertical and horizontal neighbours using deltaRow,deltaColumn which are exactly  neighbours
        for (int i = 0; i < 4; i++) {
            int neighbouringRow = row + deltaRow[i];
            int neighbouringColumn = column + deltaColumn[i];
            //check for valid grid positions coordinates
            //then check for same initial color and unvisited pixel
            if (neighbouringRow >= 0 && neighbouringRow < n && neighbouringColumn >= 0 && neighbouringColumn < m &&
                    image[neighbouringRow][neighbouringColumn] == initialColor && resultantImage[neighbouringRow][neighbouringColumn] != newColor) {
                dfs(neighbouringRow, neighbouringColumn, resultantImage, image, deltaRow, deltaColumn, initialColor, newColor);
            }
        }
    }

    public static void main(String[] args) {
        int[][] image = {{1, 1, 1}, {1, 1, 0}, {1, 0, 1}};
        int sr = 1, sc = 1, color = 2;
        FloodFill ff = new FloodFill();
        int[][] resultantImage = ff.floodFill(image,sr,sc,color);
        System.out.println(Arrays.deepToString(resultantImage));
    }
}

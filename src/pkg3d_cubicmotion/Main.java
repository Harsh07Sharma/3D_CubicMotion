
package pkg3d_cubicmotion;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.*;
import javax.swing.Timer;


public class Main extends JPanel{
    private int X = 45;
    private int Y = 45;
    
    public Main(){
        Timer timer = new Timer(30, (e) -> {
            moveCube();
            repaint();
        });
        timer.start();
    }
    
    // Update the rotation angles of a cube
    public void moveCube(){
        X += 2;
        Y += 2;
    }

    // Paint the cube on Panel
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(Color.WHITE);
        int centerX = getWidth()/2;
        int centerY = getHeight()/2;
        
        // Vertices Coordinates of a cube
        int[][] vertices = {
                            {-50,-50,-50},{50,-50,-50},{50,50,-50},{-50,50,-50},
                            {-50,-50,50},{50,-50,50},{50,50,50},{-50,50,50}
                           };
        
        int[][] edges = {
                            {0,1},{1,2},{2,3},{3,0},
                            {4,5},{5,6},{6,7},{7,4},
                            {0,4},{1,5},{2,6},{3,7}
                           };
        
        Graphics2D g2d = (Graphics2D) g;
        g2d.setStroke(new BasicStroke(5));
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        // Rotating around X axis
        double sinX = Math.sin(Math.toRadians(X));
        double cosX = Math.cos(Math.toRadians(X));
        
        for(int[] vertex : vertices){
            int y = vertex[1];
            int z = vertex[2];
            
            vertex[1] = (int)(y * cosX - z * sinX);
            vertex[2] = (int)(y * sinX - z * cosX);
        }
        
        // Rotate around Y axis
         double sinY = Math.sin(Math.toRadians(Y));
         double cosY = Math.cos(Math.toRadians(Y));
        
        for(int[] vertex : vertices){
            int x = vertex[0];
            int z = vertex[2];
            
            vertex[0] = (int)(x * cosY - z * sinY);
            vertex[2] = (int)(x * sinY - z * cosY);
        }
        
        // Draw edges of a cube
        for(int[] edge : edges){
            int x1 = centerX + vertices[edge[0]][0];
            int y1 = centerY - vertices[edge[0]][1];
            int x2 = centerX + vertices[edge[1]][0];
            int y2 = centerY - vertices[edge[1]][1];
            g2d.drawLine(x1, y1, x2, y2);
        }
        
    }
    
    

    
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setTitle("Animated 3D Cube Motion");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(450, 450);
        frame.add(new Main());
        frame.setLocationRelativeTo(null);
        frame.getContentPane().getComponent(0).setBackground(Color.BLACK);
        frame.setVisible(true);
    }   
}

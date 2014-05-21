import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Color;
//import java.awt.Point;
//import java.awt.event.*;
//import java.util.ArrayList;

class Fractal2 extends JPanel implements Runnable {
    static final int RESOLUTION=500;
    static final int ITERATIONS=50;
    
    
    
    static double[][] lines={{5,5},{50,100},{30,10},{5,60}};
    static double centerx=-.4128;
    static double centery=-.682;
    static double size=2;
    public static void main() {             
        JFrame f = new JFrame("Test1");
        Fractal2 thingy=new Fractal2();//f);
        f.setSize(500, 500);
        f.add(thingy);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //f.setResizable(false);
        f.setVisible(true);
        
        thingy.repaint();
        
        
        for(int i=5;i<10000;i++){
            size=Math.pow(.995,i);
            thingy.repaint();
            try{
                Thread.sleep(10);
            }catch(Exception e){
                
            }
        }
        
        
        //thingy.repaint();
    }
    
    public void paintComponent(Graphics g){
        g.clearRect(0,0,500,500);
        //g.drawRect(10, 20, 30, 40);
        double[] point={0,0};//a+bi
        double[] z0={0,0};
        for(int y=0;y<RESOLUTION;y++){
            for(int x=0;x<RESOLUTION;x++){
                point[0]=(double)x/RESOLUTION*2.0*size+(centerx-size);
                point[1]=(double)y/RESOLUTION*2.0*size+(centery-size);
                int num=getNum(z0,point,ITERATIONS);
                if(num==0){
                    g.setColor(Color.black);
                }else{
                    g.setColor(getHue((double)num/ITERATIONS));
                }
                g.fillRect(x,500-y,(int)(500.0/RESOLUTION),(int)(500.0/RESOLUTION));
                
            }
        }
    }
    
    public Fractal2(){//JFrame parent) {
        
    }
    
    public void run() {
        for(int i = 0; i != -1; i++){
            this.repaint();
            try {
                Thread.sleep(1000);
            }
            catch (InterruptedException e) {
                System.out.println("INTERRUPTED");
            }
        }
    }
    
    public static Color getHue(double hue){//hue should be between 0 and 1
        int Case=(int)(6.0*hue);
        double ratio=(6.0*hue)%1;
        double r=0;
        double g=0;
        double b=0;
        switch(Case){
            case 0:
                r=255;
                g=255*ratio;
                b=0;
                break;
            case 1:
                r=255-255*ratio;
                g=255;
                b=0;
                break;
            case 2:
                r=0;
                g=255;
                b=255*ratio;
                break;
            case 3:
                r=0;
                g=255-255*ratio;
                b=255;
                break;
            case 4:
                r=255*ratio;
                g=0;
                b=255;
                break;
            case 5:
                r=255;
                g=0;
                b=255-255*ratio;
                break;
        }
        return new Color((int) r,(int) g,(int) b);
    }
    
    
    
    
    static int getNum(double[] z, double[] c, int iterations){
        for(int i=0;i<iterations;i++){
            z=squareComplex(z);
            z[0]+=c[0];
            z[1]+=c[1];
            if(dist(z[0],z[1])>2){
                return i;
            }
        }
        return 0;
    }
    static double[] squareComplex(double[] z){
        double[] temp=new double[2];
        temp[0]=z[0]*z[0]-z[1]*z[1];
        temp[1]=2*z[0]*z[1];
        return temp;
    }
    public static double dist(double a, double b){
        return Math.sqrt(a*a+b*b);
    }
    
    
    public void mouseClicked(final MouseEvent evt){
        Point pos=evt.getPoint();
        int x=pos.x;
        int y=pos.y;
        System.out.println(x+"\t"+y);
    }
}




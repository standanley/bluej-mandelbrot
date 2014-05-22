import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.Point;

class Fractal2 extends JPanel implements MouseListener{
    static final int RESOLUTION=500;
    static int ITERATIONS=100;
    
    
    
    static double[][] lines={{5,5},{50,100},{30,10},{5,60}};
    static double centerx=-.5;
    static double centery=0;
    static double size=2;
    public static Fractal2 thingy;
    
    public static void main(){             
        JFrame f = new JFrame("Test1");
        thingy=new Fractal2();//f);
        f.setSize(500, 500);
        f.add(thingy);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //f.setResizable(false);
        f.setVisible(true);
        
        thingy.repaint();

        /**
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
        **/
        
        /**
        for(int i=5;i<10000;i++){
            size=Math.pow(.995,i);
            thingy.repaint();
            try{
                Thread.sleep(10);
            }catch(Exception e){
                
            }
        }
        **/
        
        
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
                    g.setColor(getHue((double)num%50/50.0));
                }
                g.fillRect(x,500-y,(int)(500.0/RESOLUTION),(int)(500.0/RESOLUTION));
                
            }
        }
    }
    
    public Fractal2(){//JFrame parent) {
        
        addMouseListener(this);
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
            if(dist(z[0],z[1])>3){
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
    
        @Override
    public void mouseClicked(MouseEvent e) {
        int x = e.getX();
        int y = 500-e.getY();
        centerx=(x/500.0)*size*2+(centerx-size);
        centery=(y/500.0)*size*2+(centery-size);
        size/=5.0;
        thingy.repaint();
        System.out.println("New Center: "+centerx+" + "+centery+" i\n\tNew zoom level: "+1.0/size);
    }
 
    @Override
    public void mouseEntered(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        //System.out.println("Mouse Entered frame at X: " + x + " - Y: " + y);
    }

    @Override
    public void mouseExited(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        //System.out.println("Mouse Exited frame at X: " + x + " - Y: " + y);
        ITERATIONS+=25;
        System.out.println("New number of iterations: "+ITERATIONS);
        thingy.repaint();
    }

    @Override
    public void mousePressed(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        //System.out.println("Mouse Pressed at X: " + x + " - Y: " + y);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        //System.out.println("Mouse Released at X: " + x + " - Y: " + y);
    }

    static double[] cubeComplex(double[] z){
        double[] temp=new double[2];
        temp[0]=z[0]*z[0]*z[0]-3*z[0]*z[1]*z[1];
        temp[1]=3*z[0]*z[0]*z[1]-z[1]*z[1]*z[1];
        return temp;
    }
    
}




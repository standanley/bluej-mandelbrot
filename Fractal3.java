import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSlider;
imporat javax.swing.JButton;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.Point;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import javax.swing.ButtonModel;

class Fractal3 extends JPanel implements MouseListener{
    static final int RESOLUTION=500;
    static int ITERATIONS=25;
    static  double[] juliaSeed={-0.8,.8444};
    static JSlider slider1;
    static JSlider slider2;
    static JButton button1;
    
    
    
    static double[][] lines={{5,5},{50,100},{30,10},{5,60}};
    static double centerx=0;
    static double centery=0;
    static double size=4;
    public static Fractal3 thingy;
    
    public static void main(){             
        JFrame f = new JFrame("Fractal");
        thingy=new Fractal3();//f);
        f.setSize(500, 500);
        f.add(thingy);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //f.setResizable(false);
        f.setVisible(true);
        
        JFrame f2=new JFrame("Sliders");
        f2.setSize(230,90);
        JPanel panel = new JPanel();
        slider1=new JSlider();
        slider2=new JSlider();
        button1=new JButton("Increase Iterations");
        slider1.addChangeListener(new Listener());
        slider2.addChangeListener(new Listener());
        button1.addChangeListener(new Listener());
        panel.add(slider1);
        panel.add(slider2);
        panel.add(button1);
        f2.add(panel);
        f2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f2.setVisible(true);
        
        
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
        System.out.println("Iterations= "+ITERATIONS);
        //g.drawRect(10, 20, 30, 40);
        double[] point={0,0};//a+bi
        double[] z0=juliaSeed;
        for(int y=0;y<RESOLUTION;y++){
            for(int x=0;x<RESOLUTION;x++){
                point[0]=(double)x/RESOLUTION*2.0*size+(centerx-size);
                point[1]=(double)y/RESOLUTION*2.0*size+(centery-size);
                int num=juliaSet(z0,point,ITERATIONS);
                if(num==0){
                    g.setColor(Color.black);
                }else{
                    g.setColor(getHue((double)num%50/50.0));
                }
                g.fillRect(x,500-y,(int)(500.0/RESOLUTION),(int)(500.0/RESOLUTION));
                
            }
        }
    }
    
    public Fractal3(){//JFrame parent) {
        
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
        //ITERATIONS+=25;
        //System.out.println("New number of iterations: "+ITERATIONS);
        //thingy.repaint();
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
    
    static int juliaSet(double[] seed, double[] point, int iterations){
        for(int i=0;i<iterations;i++){
            point=squareComplex(point);
            point[0]+=seed[0];
            point[1]+=seed[1];
            if(dist(point[0],point[1])>5){
                return i;
            }
        }
        return 0;
    }
}












class Listener implements ChangeListener{
  public void stateChanged(ChangeEvent ce){
      double slider1Value=(Fractal3.slider1.getValue()-50)/50.0;
      double slider2Value=(Fractal3.slider2.getValue()-50)/50.0;
      
          if(Fractal3.button1.getModel().isPressed()){
              System.out.println("button pressed");
              Fractal3.ITERATIONS+=25;
              //Fractal3.thingy.repaint();
            }
          
          Fractal3.juliaSeed[0] = (Fractal3.slider1.getValue()-50)/50.0;
          Fractal3.juliaSeed[1] = (Fractal3.slider2.getValue()-50)/50.0;
          Fractal3.thingy.repaint();
          System.out.println(Fractal3.juliaSeed[0]+"\t"+Fractal3.juliaSeed[1]);
        
  
  //String str = Integer.toString(value);
  //label.setText(str);
      
  }
  }




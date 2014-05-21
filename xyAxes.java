// This is a program that draws two axes. 
// You will rewrite the program to make it more efficient. The current program has you going over the same line. 
// Using a loop place tick marks every ten units on each axis. 
// Use myT2 to draw the tick-marks. 
// You may only use the turtle function below in your program. down, up, 



public class xyAxes
{
    static final int TICS=10;
    
    //static final double centerx=0.00164372;
    //static final double centery =-0.8224676;
    //static final double size=.05;
    static final double centerx=0.015000;
    static final double centery =-0.8300000;
    static final double size=.01;
    
    static final double xmin=centerx-size;
    static final double xmax=centerx+size;
    static final double ymin=centery-size;
    static final double ymax=centery+size;
    
    public static void main()
    {
        
        Turtle myT2 = new Turtle();
        myT2.hide();
        myT2.up();
        myT2.speed(0);
        /**
        Turtle T1 = new Turtle();
        T1.speed(5);
        Turtle myT2 = new Turtle();
        myT2.hide();
        T1.up();
        T1.hide();
        T1.forward(300);
        T1.down();
        T1.backward(600);
        T1.up();
        
        T1.left(90);
        T1.backward(5);
        tic(T1);
        T1.forward(5);
        T1.right(90);
        T1.backward(300);
        T1.left(90);
            
        
        //T1.forward(300); 
        //T1.right(90);
        T1.backward(300);
        T1.down();
        T1.forward(600);
        T1.down();
        T1.right(90);
        T1.backward(5);
        tic(T1);
        //T1.backward(600);
        
        **/
        
        double[] c={-1,0};
        double[] z0={0,0};
        double xscale=(xmax-xmin)/100.0;
        double yscale=(ymax-ymin)/100.0;
        
        for(int y=0;y<100;y++){
            for(int x=0;x<100;x++){
                c[0]=x*xscale+xmin;
                c[1]=y*yscale+ymin;
                int ITERATIONS=25;
                int num=getNum(z0,c,ITERATIONS);
                System.out.println(num+"\t"+c[0]+"\t"+c[1]);
                if(num==0){
                    myT2.penColor("black");
                }else{
                    myT2.penColor(getHue(1.0/ITERATIONS*num));
                }
                myT2.setPosition(x*6-300,y*6-300);
                myT2.down();
                for(int i=0;i<4;i++){
                    myT2.forward(3);
                    myT2.right(90);
                }
                myT2.up();
            }
        }
        
        System.out.println(getNum(z0,c,10));
        
     
    }
    static void tic(Turtle T1){
        for(int i=0;i<600/TICS;i++){
            T1.down();
            T1.forward(10);
            T1.up();
            T1.backward(10);
            T1.right(90);
            T1.forward(TICS);
            T1.left(90);
        }
    }
    static int getNum(double[] z, double[] c, int iterations){
        for(int i=0;i<iterations;i++){
            z=squareComplex(z);
            z[0]+=c[0];
            z[1]+=c[1];
            if(dist(z[0],z[1])>4){
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
    public static String color(int r, int g, int b){
        return ((b)+256*(g)+256*256*(r))+0*256*256*256*255+"";
    }
    public static double dist(double a, double b){
        return Math.sqrt(a*a+b*b);
    }
    
    public static String getHue(double hue){//hue should be between 0 and 1
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
        return color((int) r,(int) g,(int) b);
    }
    
}

//package A2;
import java.util.Properties;
import java.io.*;
import java.util.Random;
import java.net.URL;
import java.util.Objects;
import javax.swing.*;
import java.util.ArrayList;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RollBounce<T> extends JPanel implements ActionListener {

	static class Node <T>{
		T value;
		Node<T> next;
		Node<T> prev;

		Node(T value){
			this.value = value;
		}
	}
	Node<T> head = new Node<T>(null);
	Node<T> tail = head;
	int size;
	
    protected Timer tm;

    protected int x1, y1;
    protected int x2, y2;
    protected int x3, y3;
    protected int x4, y4;
    protected int x5, y5;
    protected int x6, y6;
    


    public RollBounce (String propertyFileName) {
    	Random rand = new Random();
    	 Properties prop = new Properties();
         try {
             // the configuration file name
             String fileName = "a2.prop";
             ClassLoader classLoader = RollBounce.class.getClassLoader();

             // Make sure that the configuration file exists
             URL res = Objects.requireNonNull(classLoader.getResource(fileName),
                 "Can't find configuration file app.config");

             InputStream is = new FileInputStream(res.getFile());

             // load the properties file
             prop.load(is);

         } catch (IOException e) {
             e.printStackTrace();
         }
        tm = new Timer(Integer.parseInt(prop.getProperty("timerDelay")), this); // TODO: Replace the first argument with delay with value from config file.

        // TODO: Consider removing the next two lines (coordinates) for random starting locations.
        x1 = rand.nextInt(Integer.parseInt(prop.getProperty("window_width"))); y1 = rand.nextInt(Integer.parseInt(prop.getProperty("window_height")));
        x2 = rand.nextInt(Integer.parseInt(prop.getProperty("window_width"))); y2 = rand.nextInt(Integer.parseInt(prop.getProperty("window_height")));
        x3 = rand.nextInt(Integer.parseInt(prop.getProperty("window_width"))); y3 = rand.nextInt(Integer.parseInt(prop.getProperty("window_height")));
        x4 = rand.nextInt(Integer.parseInt(prop.getProperty("window_width"))); y4 = rand.nextInt(Integer.parseInt(prop.getProperty("window_height")));
        x5 = rand.nextInt(Integer.parseInt(prop.getProperty("window_width"))); y5 = rand.nextInt(Integer.parseInt(prop.getProperty("window_height")));
        x6 = rand.nextInt(Integer.parseInt(prop.getProperty("window_width"))); y6 = rand.nextInt(Integer.parseInt(prop.getProperty("window_height")));
        
    }


    public void paintComponent(Graphics g) {
        super.paintComponent(g); // Probably best you leave this as is.
        Random rand = new Random();
        Properties prop = new Properties();
        try {
            // the configuration file name
            String fileName = "a2.prop";
            ClassLoader classLoader = RollBounce.class.getClassLoader();

            // Make sure that the configuration file exists
            URL res = Objects.requireNonNull(classLoader.getResource(fileName),
                "Can't find configuration file app.config");

            InputStream is = new FileInputStream(res.getFile());

            // load the properties file
            prop.load(is);

        } catch (IOException e) {
            e.printStackTrace();
        }
        
        
// TODO: Paint each ball. Here's how to paint two balls, one after the other:
        g.setColor(Color.YELLOW);
        g.fillOval(x1, y1, Integer.parseInt(prop.getProperty("ball_radius")), Integer.parseInt(prop.getProperty("ball_radius")));

        g.setColor(Color.RED);
        g.fillOval(x2, y2, Integer.parseInt(prop.getProperty("ball_radius")), Integer.parseInt(prop.getProperty("ball_radius")));
        
        g.setColor(Color.BLUE);
        g.fillOval(x3, y3, Integer.parseInt(prop.getProperty("ball_radius")), Integer.parseInt(prop.getProperty("ball_radius")));

        g.setColor(Color.MAGENTA);
        g.fillOval(x4, y4, Integer.parseInt(prop.getProperty("ball_radius")), Integer.parseInt(prop.getProperty("ball_radius")));
        
        g.setColor(Color.ORANGE);
        g.fillOval(x5, y5, Integer.parseInt(prop.getProperty("ball_radius")), Integer.parseInt(prop.getProperty("ball_radius")));

        g.setColor(Color.GREEN);
        g.fillOval(x6, y6, Integer.parseInt(prop.getProperty("ball_radius")), Integer.parseInt(prop.getProperty("ball_radius")));

        // Recommend you leave the next line as is
        tm.start();
    }


    @Override
    public void actionPerformed(ActionEvent actionEvent) {
    	Properties prop = new Properties();
        try {
            // the configuration file name
            String fileName = "a2.prop";
            ClassLoader classLoader = RollBounce.class.getClassLoader();

            // Make sure that the configuration file exists
            URL res = Objects.requireNonNull(classLoader.getResource(fileName),
                "Can't find configuration file app.config");

            InputStream is = new FileInputStream(res.getFile());

            // load the properties file
            prop.load(is);

        } catch (IOException e) {
            e.printStackTrace();
        }
        x1 += Integer.parseInt(prop.getProperty("minspeed"));
        x2 -= Integer.parseInt(prop.getProperty("maxspeed"));
        x3 += Integer.parseInt(prop.getProperty("minspeed"));
        x4 -= Integer.parseInt(prop.getProperty("maxspeed"));
        x5 += Integer.parseInt(prop.getProperty("minspeed"));
        x6 -= Integer.parseInt(prop.getProperty("maxspeed"));
        // These two "if" statements keep the balls on the screen in case they go off one side.
        if (x1 > 640)
            x1 = 0;
        if (x2 < 0)
            x2 = 640;
        
        if (x3 > 640)
            x3 = 0;
        if (x4 < 0)
            x4 = 640;
        
        if (x5 > 640)
            x5 = 0;
        if (x6 < 0)
            x6 = 640;
        
        

        // Keep this at the end of the function (no matter what you do above):
        repaint();
    }

    public static void main(String[] args) {
        RollBounce rb = new RollBounce(args[0]);
        
        Properties prop = new Properties();
        try {
            // the configuration file name
            String fileName = "a2.prop";
            ClassLoader classLoader = RollBounce.class.getClassLoader();

            // Make sure that the configuration file exists
            URL res = Objects.requireNonNull(classLoader.getResource(fileName),
                "Can't find configuration file app.config");

            InputStream is = new FileInputStream(res.getFile());

            // load the properties file
            prop.load(is);

        } catch (IOException e) {
            e.printStackTrace();
        }
        
        int height = Integer.parseInt(prop.getProperty("window_height"));
        int width = Integer.parseInt(prop.getProperty("window_height"));

        
        JFrame jf = new JFrame();
        jf.setTitle("Roll Bounce");
        jf.setSize(height, width); // TODO: Replace with the size from configuration!
        jf.add(rb);
        jf.setVisible(true);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
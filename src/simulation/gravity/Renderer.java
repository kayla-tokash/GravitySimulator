package simulation.gravity;

// GUI
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

// Other utilities
import java.util.Random;

// OpenGL
import com.jogamp.opengl.*;
import com.jogamp.opengl.glu.GLU;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.util.FPSAnimator;
import static com.jogamp.opengl.GL.*;
import static com.jogamp.opengl.GL2.*;

@SuppressWarnings("serial")
public class Renderer extends GLCanvas implements GLEventListener {
   Universe u = new Universe();
   Random r = new Random();

   private static String TITLE = "Gravity Simulatorw View";
   private static final int CANVAS_WIDTH = 1280;
   private static final int CANVAS_HEIGHT = 720;
   private static final int FPS = 60;

   private final double  camSensitivity = 15; // degrees

   private Vector camOrigin = new Vector(0d,0d,-20d);
   private Vector camRotation = new Vector(0d,0d,0d,camSensitivity);
   private Vector camFocus = new Vector(0d,0d,0d);

   public static void main(String[] args) {
      // Run the GUI codes in the event-dispatching thread for thread safety
      SwingUtilities.invokeLater(new Runnable() {
         @Override
         public void run() {
            // Create the OpenGL rendering canvas
            final Renderer canvas = new Renderer();
            canvas.setPreferredSize(new Dimension(CANVAS_WIDTH, CANVAS_HEIGHT));

            // Create a animator that drives canvas' display() at the specified FPS.
            final FPSAnimator animator = new FPSAnimator(canvas, FPS, true);

            // Create the top-level container
            final JFrame frame = new JFrame(); // Swing's JFrame or AWT's Frame
            frame.getContentPane().add(canvas);
            frame.addWindowListener(new WindowAdapter() {
               @Override
               public void windowClosing(WindowEvent e) {
                  // Use a dedicate thread to run the stop() to ensure that the
                  // animator stops before program exits.
                  new Thread() {
                     @Override
                     public void run() {
                        if (animator.isStarted()) animator.stop();
                        System.exit(0);
                     }
                  }.start();
               }
            });
            frame.addKeyListener(new KeyListener() {
               @Override
               public void keyTyped(KeyEvent e) {
                  System.out.println(e.getKeyCode() + " typed");
               }

               @Override
               public void keyPressed(KeyEvent e) {
                  switch (e.getKeyCode()) {
                     case 37:
                        canvas.camRotation.coor[1]++;
                        break;
                     case 38:
                        canvas.camRotation.coor[0]++;
                        break;
                     case 39:
                        canvas.camRotation.coor[1]--;
                        break;
                     case 40:
                        canvas.camRotation.coor[0]--;
                        break;
                     default:
                        System.out.println(e.getKeyCode() + " pressed");
                        break;
                  }
                  System.out.printf("x=%f, y=%f, z=%f, w=%f",
                          canvas.camRotation.coor[0], canvas.camRotation.coor[1],
                          canvas.camRotation.coor[2], canvas.camRotation.coor[3]);
                  System.out.println(e.getKeyCode() + " pressed");

               }

               @Override
               public void keyReleased(KeyEvent e) {
                  System.out.println(e.getKeyCode() + " released");
               }
            });
            frame.setTitle(TITLE);
            frame.pack();
            frame.setVisible(true);
            animator.start(); // start the animation loop
         }
      });
   }

   // Setup OpenGL Graphics simulation.gravity.Renderer

   private GLU glu;  // for the GL Utility

   /** Constructor to setup the GUI for this Component */
   public Renderer() {
      this.addGLEventListener(this);
   }

   // ------ Implement methods declared in GLEventListener ------

   /**
    * Called back immediately after the OpenGL context is initialized. Can be used
    * to perform one-time initialization. Run only once.
    */
   @Override
   public void init(GLAutoDrawable drawable) {
      GL2 gl = drawable.getGL().getGL2();      // get the OpenGL graphics context
      glu = new GLU();                         // get GL Utilities
      gl.glClearColor(0.0f, 0.0f, 0.0f, 0.0f); // set background (clear) color
      gl.glClearDepth(1.0f);      // set clear depth value to farthest
      gl.glEnable(GL_DEPTH_TEST); // enables depth testing
      gl.glDepthFunc(GL_LEQUAL);  // the type of depth test to do
      gl.glHint(GL_PERSPECTIVE_CORRECTION_HINT, GL_NICEST); // best perspective correction
      gl.glShadeModel(GL_SMOOTH); // blends colors nicely, and smoothes out lighting

      for (int i = 0; i < 1000; ++i) {
         u.addMass((double) r.nextInt(10000) / 10000d,
                 // adding random mass
                 Vector.sum(new Vector((double) r.nextInt(10) - 5,(double)  r.nextInt(10) - 5,(double)  r.nextInt(10) - 5),this.camOrigin),
                 new Vector(0,0,0)
//                  new Vector(r.nextGaussian()*.0001,r.nextGaussian()*.0001,r.nextGaussian()*.0001)
         );
      }
   }

   /**
    * Call-back handler for window re-size event. Also called when the drawable is
    * first set to visible.
    */
   @Override
   public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
      GL2 gl = drawable.getGL().getGL2();  // get the OpenGL 2 graphics context

      if (height == 0) height = 1;   // prevent divide by zero
      double aspect = (double)width / height;

      // Set the view port (display area) to cover the entire window
      gl.glViewport(0, 0, width, height);

      // Setup perspective projection, with aspect ratio matches viewport
      gl.glMatrixMode(GL_PROJECTION);  // choose projection matrix
      gl.glLoadIdentity();             // reset projection matrix
      glu.gluPerspective(45.0, aspect, 0.1, 100.0); // fovy, aspect, zNear, zFar

      // Enable the model-view transform
      gl.glMatrixMode(GL_MODELVIEW);
      gl.glLoadIdentity(); // reset
   }



   /**
    * Called back by the animator to perform rendering.
    */
   @Override
   public void display(GLAutoDrawable drawable) {
      GL2 gl = drawable.getGL().getGL2();  // get the OpenGL 2 graphics context
      gl.glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT); // clear color and depth buffers
      gl.glLoadIdentity();  // reset the model-view matrix

      // ----- Your OpenGL rendering code here (Render a white triangle for testing) -----
      gl.glTranslated(camFocus.coor[0], camFocus.coor[1], camFocus.coor[2]);
      gl.glRotated(camRotation.coor[3], camRotation.coor[0], camRotation.coor[1], camRotation.coor[2]);
      gl.glTranslated(camOrigin.coor[0], camOrigin.coor[1], camOrigin.coor[2]);
//       if (r.nextInt(1000) <= 150)
//           u.addMass(
//                   (double) r.nextInt(10000) / 10000d,
//                   Vector.sum(new Vector((double) r.nextInt(10) - 5,(double)  r.nextInt(10) - 5,(double)  r.nextInt(10) - 5),this.camOrigin),
//                   new Vector(0,0,0)
////                   new Vector(r.nextGaussian()*.0001,r.nextGaussian()*.0001,r.nextGaussian()*.0001)
//           );
      u.draw(gl);
      u.updateMassList();


      if (u.getLargestMass() != null) {

         this.setCamera(u.getLargestMass().getPos(),camRotation,camFocus);
      }
   }

   /**
    * Called back before the OpenGL context is destroyed. Release resource such as buffers.
    */
   @Override
   public void dispose(GLAutoDrawable drawable) { }

   public void setCamera(Vector origin, Vector rotation, Vector focus) {
      this.camOrigin = origin;
      this.camRotation = rotation;
      this.camFocus = focus;
   }
}
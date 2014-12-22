package simulation.gravity;

import java.awt.*;
import java.awt.event.*;
import java.util.Random;

import javax.swing.*;
import javax.media.opengl.GL2;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.awt.GLCanvas;
import javax.media.opengl.glu.GLU;

import com.jogamp.opengl.util.FPSAnimator;

import static javax.media.opengl.GL.*;  // GL constants
import static javax.media.opengl.GL2.*; // GL2 constants

/**
 * simulation.gravity.Renderer.java derived from sample code supplied here
 * http://www3.ntu.edu.sg/home/ehchua/programming/opengl/JOGL2.0.html
 */
@SuppressWarnings("serial")
public class Renderer extends GLCanvas implements GLEventListener {
	Universe u = new Universe();
    Random r = new Random();

   // Define constants for the top-level container
   private static String TITLE = "Gravity Simulatorw View";  // window's title
   private static final int CANVAS_WIDTH = 1280;  // width of the drawable
   private static final int CANVAS_HEIGHT = 720; // height of the drawable
   private static final int FPS = 60; // animator's target frames per second

   private Vector camOrigin = new Vector(0,0,0);
   private Vector camRotation = new Vector(0,0,0,0);

   /** The entry main() method to setup the top-level container and animator */
   public static void main(String[] args) {
      // Run the GUI codes in the event-dispatching thread for thread safety
      SwingUtilities.invokeLater(new Runnable() {
         @Override
         public void run() {
            // Create the OpenGL rendering canvas
            GLCanvas canvas = new Renderer();
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
 
      // ----- Your OpenGL initialization code here -----
      
      //u.addMass(1, new simulation.gravity.Vector(0,0,10), new simulation.gravity.Vector(0,0,0));
//      u.addMass(1, new simulation.gravity.Vector(0,5,10), new simulation.gravity.Vector(0,0,0));
//      u.addMass(1, new simulation.gravity.Vector(0,-5,10), new simulation.gravity.Vector(0,0.00006,0));
//      u.addMass(1, new simulation.gravity.Vector(0,0,7), new simulation.gravity.Vector(0,0,0));
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
   double a = 0;
   @Override
   public void display(GLAutoDrawable drawable) {
      GL2 gl = drawable.getGL().getGL2();  // get the OpenGL 2 graphics context
      gl.glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT); // clear color and depth buffers
      gl.glLoadIdentity();  // reset the model-view matrix
 
      // ----- Your OpenGL rendering code here (Render a white triangle for testing) -----
      gl.glTranslated(this.camOrigin.x, this.camOrigin.y, this.camOrigin.z);
      gl.glRotated(this.camRotation.w, this.camRotation.x, this.camRotation.y, this.camRotation.z);
      gl.glTranslated(0.0, 0.0, -10.0); // translate into the screen
       if (r.nextInt(1000) <= 15)
           u.addMass(
                   (double) r.nextInt(10000) / 10000d,
                   Vector.sum(new Vector((double) r.nextInt(10) - 5,(double)  r.nextInt(10) - 5,(double)  r.nextInt(10) - 5),this.camOrigin),
                   new Vector(r.nextGaussian()*.0001,r.nextGaussian()*.0001,r.nextGaussian()*.0001)
           );
      u.draw(gl);
      u.updateMassList();
      if (u.getLargestMass() != null) {
          this.setCamera(u.getLargestMass().getPos(),Vector.sum(this.camRotation,new Vector(1,0,0.1,0)));
      }
   }
 
   /**
    * Called back before the OpenGL context is destroyed. Release resource such as buffers.
    */
   @Override
   public void dispose(GLAutoDrawable drawable) { }


   public void setCamera(Vector origin, Vector rotation) {
          this.camOrigin = new Vector(origin.coor);
          this.camRotation = new Vector(rotation.coor);
   }
}
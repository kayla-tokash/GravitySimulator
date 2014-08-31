import javax.media.opengl.GL2;
/**
 * 
 * @author Nicholas Tokash
 * Circle.java
 * Handles drawing of a circle in basic OpenGL
 */
public class Circle {
	/**
	 * GLDraw method
	 * @param gl 
	 * @param radius radius of the circle
	 */
	static void draw(GL2 gl, double radius) {
		
		gl.glBegin(GL2.GL_TRIANGLE_FAN);
		
		gl.glVertex3d(0, 0, 0);
		
		for (int i = 0; i < 361; i++) {
			double x = radius * Math.sin(i * Math.PI / 180); 
			double y = radius * Math.cos(i * Math.PI / 180);
			gl.glVertex3d(x, y, 0);
		}
		
		gl.glEnd();
	}
}

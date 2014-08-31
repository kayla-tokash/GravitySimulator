import javax.media.opengl.GL2;

/**
 * @author Nicholas Tokash
 * Mass.java
 * Contains data and methods for handling mass bodies
 */

public class Mass {
	private static long num = 0;
	
	public long id;
	private double mass;
	
	private Vector pos;
	private Vector vel;
	
	/**
	 * Creates a new mass body at origin
	 * @param mass mass of the mass body
	 */
	public Mass(double mass) {
		this(mass, new Vector(3));
	}
	
	/**
	 * Creates a mass body at position
	 * @param mass mass of the mass body 
	 * @param pos position to place the mass body
	 */
	public Mass(double mass, Vector pos) {
		this(mass, pos, new Vector(3));
	}
	
	/**
	 * Creates a mass body at a position with a velocity
	 * @param mass mass of the mass body
	 * @param pos position to place the mass body
	 * @param vel velocity of the mass body 
	 */
	public Mass(double mass, Vector pos, Vector vel) {
		this.pos = pos;
		this.vel = vel;
		this.mass = mass;		
		this.id = num;
		num++;
	}
	
	/**
	 * GlDraw Method
	 * @param gl
	 */
	public void draw(GL2 gl) {
		// Translating to position
		gl.glTranslated(-this.pos.coor[0], -this.pos.coor[1], -this.pos.coor[2]);

		// Setting out color
		gl.glColor3f(1, 1, 1);
		
		// Drawing something
		Circle.draw(gl, this.getRadius() * 0.1f);
		
		// Returning to original position
		gl.glTranslated(this.pos.coor[0], this.pos.coor[1], this.pos.coor[2]);
		
	}
	
	/**
	 * Accelerates the mass body by a certain velocity
	 * @param v velocity to add to the mass body
	 */
	public void accelerateAndMove(Vector v) {
        this.vel = Vector.sum(this.vel, v);
		this.pos = Vector.sum(this.vel, this.pos);
	}
	
	/**
	 * Calculates acceleration from another mass body
	 * @param G gravitational constant
	 * @param m other mass body
	 * @return vector acceleration from the other mass body
	 */
	public Vector getAccFrom(double G, Mass m) {
        // a_from_m = G * m.mass / (distance(this.pos, m.pos) ^ 2)
		Vector mag = Vector.div(G * m.mass, Vector.pow(Vector.diff(this.pos, m.pos), 2));

        Vector diff = Vector.diff(m.pos, this.pos);
        double dist = Vector.distance(this.pos, m.pos);
        double accl = G * m.mass / Math.pow(dist, 2);

        return (dist == 0) ? new Vector(0,0,0) : Vector.div(Vector.prod(diff,accl), dist);
	}
	
	/**
	 * Checks if the current mass body has collided with the checked mass body
	 * @param m the other mass body
	 * @return true if there was a collision, false if not
	 * TODO Implement a function that will check if the mass 
	 * collide while moving as willCollideWith(Mass m) 
	 */
	public boolean hasCollidedWith(Mass m) {
		return (this.getRadius() + m.getRadius() > Math.abs(Vector.distance(this.pos, m.pos)));
	}
	
	/**
	 * Returns the radius based on the mass body's mass
	 * @return the radius of the mass body
	 */
	public double getRadius() {
		return (double)Math.sqrt(this.mass);
	}
	
	/**
	 * Absorbs the mass and momentum of another mass body
	 * @param m the other mass body 
	 */
	public void absorb(Mass m) {
		this.mass += m.mass;
		this.pos = Vector.avg(this.pos, m.pos);
		this.vel = Vector.avg(this.vel, m.vel);
	}
	
	/**
	 * Checks for equality
	 */
	public boolean equals(Object o) {
		return (o instanceof Mass && ((Mass) o).id == this.id); 
			
	}
	
}

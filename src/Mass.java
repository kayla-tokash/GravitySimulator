import javax.media.opengl.GL2;

/**
 * @author Nicholas Tokash
 * Mass.java
 * Contains data and methods for handling mass bodies
 */

public class Mass {
	private static long num = 0;
	
	public long id;
	private float mass;
	
	private Vector pos;
	private Vector vel;
	
	/**
	 * Creates a new mass body at origin
	 * @param mass mass of the mass body
	 */
	public Mass(float mass) {
		this(mass, new Vector(3));
	}
	
	/**
	 * Creates a mass body at position
	 * @param mass mass of the mass body 
	 * @param pos position to place the mass body
	 */
	public Mass(float mass, Vector pos) {
		this(mass, pos, new Vector(3));
	}
	
	/**
	 * Creates a mass body at a position with a velocity
	 * @param mass mass of the mass body
	 * @param pos position to place the mass body
	 * @param vel velocity of the mass body 
	 */
	public Mass(float mass, Vector pos, Vector vel) {
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
		gl.glTranslatef(-this.pos.coor[0], -this.pos.coor[1], -this.pos.coor[2]);
		
		System.out.printf("Coordinate %f, %f, %f\n", this.pos.coor[0], this.pos.coor[1], this.pos.coor[2]);
		
		// Setting out color
		gl.glColor3f(1,1,1);
		
		// Drawing something
		Circle.draw(gl, this.getRadius() * 0.1f);
		
		// Returning to original position
		gl.glTranslatef(this.pos.coor[0], this.pos.coor[1], this.pos.coor[2]);
		
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
	public Vector getAccFrom(float G, Mass m) {
		// a_from_m = G * m.mass / (distance(this.pos, m.pos) ^ 2)
		
		Vector ret = Vector.divVec(G * m.mass, Vector.pow(Vector.diff(this.pos, m.pos), 2));
		return ret;
		// double acc = G * m.mass / Math.pow(Vector.distance(this.pos,m.pos),2);
		
		// double theta_xy = Math.atan((this.pos.y - m.pos.y) / (this.pos.x - m.pos.x)) /
		// 		Vector.distance(
		// 			new Vector(this.pos.x, this.pos.y), 
		// 			new Vector(m.pos.x, m.pos.y));
		
		// double theta_yz = Math.atan((this.pos.z - m.pos.z) / (this.pos.y - m.pos.y)) /
		// 		Vector.distance(
		// 			new Vector(this.pos.y, this.pos.z), 
		// 			new Vector(m.pos.y, m.pos.z));
		
		
		// double x_vector = Math.cos(theta_xy);
		// double y_vector = Math.sin(theta_xy);
		// double z_vector = Math.sin(theta_yz);
		
		// return new Vector(
		// 	x_vector * acc,
		// 	y_vector * acc,
		// 	z_vector * acc);
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
	public float getRadius() {
		return (float)Math.sqrt(this.mass);
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

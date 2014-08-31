import java.util.ArrayList;

import javax.media.opengl.GL2;

/**
 * @author Nicholas Tokash
 * Universe.java
 * Manages the universe features of the simulated world
 */

public class Universe {
	private static double G = 0.05; // Gravitational Constant
	private ArrayList<Mass> massList;
	
	/**
	 * Default Constructor 
	 */
	public Universe() {
		this.massList = new ArrayList<Mass>();
	}
	
	/**
	 * Updates the locations and speeds of the mass bodies 
	 */
	public void updateMassList() {
		
		@SuppressWarnings("unchecked")
		ArrayList<Mass> tmp = (ArrayList<Mass>) this.massList.clone();
		
		for (Mass m1:this.massList) {
			for (Mass m2: tmp) {
				
				if (!m1.equals(m2)) {
					if (m1.hasCollidedWith(m2)) {
						m1.absorb(m2);
						removeMass(m2.id);
					} else {
						m1.accelerateAndMove(m1.getAccFrom(Universe.G, m2));
					}
				}
			}
		}
	}
	
	/**
	 * Removes a mass body from the universe
	 * @param id id number of the massbody
	 */
	public void removeMass(long id) {
		for (int n = 0; n < this.massList.size(); ++n) {
			if (this.massList.get(n).id == id)
				this.massList.remove(n);
		}
	}
	
	/**
	 * Adds a new mass body to the universe
	 * @param mass mass of the body
	 * @param pos position of the body
	 * @param vel velocity of the body
	 */
	public void addMass(double mass, Vector pos, Vector vel) {
		this.massList.add(new Mass(mass, pos, vel));
	}
	
	/** 
	 * GLDraws the body
	 * @param gl
	 */
	public void draw(GL2 gl) {
		for (Mass m:this.massList)
			m.draw(gl);
	}
}

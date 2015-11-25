package simulation.gravity;

import java.util.ArrayList;

import com.jogamp.opengl.*;

/**
 * @author Nicholas Tokash
 * simulation.gravity.Universe.java
 * Manages the universe features of the simulated world
 */

public class Universe {
	private static double G = 0.00000005; // Gravitational Constant
	private ArrayList<Mass> massList;

    private Mass largetMass = null;
	
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
		
		ArrayList<Mass> tmp = (ArrayList<Mass>) this.massList.clone();
		
		for (Mass m1:this.massList) {
			for (Mass m2: tmp) {
				if (!(m1.isDestroyed() || m2.isDestroyed()) && !m1.equals(m2)) {
					if (m1.isTouching(m2)) {
						m1.absorb(m2);
                        m2.destroy();
					} else {
						m1.accelerateAndMove(m1.getAccFrom(Universe.G, m2));
					}
                    if (this.getLargestMass() == null || this.getLargestMass().getMass() < m1.getMass()) {
                        this.largetMass = m1;
                    }
				}
			}
		}

        cleanUp();
	}

    public Mass getLargestMass() {
        return this.largetMass;
    }
	
	/**
	 * Removes a mass body from the universe
	 * @param id id number of the massbody
	 */
    @Deprecated
	public void removeMass(long id) {
//        System.out.println("Removing mass #" + id);
		for (int n = 0; n < this.massList.size(); ++n) {
			if (this.massList.get(n).id == id)
				this.massList.remove(n);
		}
	}

    /**
     * Removes old mass bodies marked "destroyed"
     * FIXME Either the collisions of massbodies is not being detected, or this is not working.
     */
    public void cleanUp() {
        for (int n = 0; n < this.massList.size(); ++n) {
            if (this.massList.get(n).isDestroyed()) {
                System.out.println("Removing mass #" + n);
                this.massList.remove(n);
            }
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
     * Adds a constructed mass body
     * @param mass the mass body
     */
    public void addMass(Mass mass) {
        this.massList.add(mass);
    }

    /**
     * Sets the gravitational constant for the simulated universe
     * @param G the new gravitational constant
     */
    public void setGravitationalConstant(double G) {
        this.G = G;
    }

    /**
     * Getter for the gravitational constant
     * @return the gravitational constant
     */
    public double getGravitationalConstant() {
        return G;
    }

    /**
     * Getter for the list of masses in the universe
     * @return the mass list
     */
    public ArrayList<Mass> getMassList() {
        return massList;
    }

    /**
     * Sets the mass list for the universe
     * @param massList the new mass list
     */
    public void setMassList(ArrayList<Mass> massList) {
        this.massList = massList;
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

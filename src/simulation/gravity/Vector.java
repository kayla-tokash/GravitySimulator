package simulation.gravity;

/**
 * @author Nicholas Tokash
 * simulation.gravity.Vector.java
 * simulation.gravity.Vector Math Methods and simulation.gravity.Vector Class Object
 */

public class Vector {
	public double[] coor;
	public double x, y, z, w;
	
	/**
	 * Constructor
	 * @param size size of the new vector
	 */
	public Vector(int size) {
		this.coor = new double[size];
	}
	
	/**
	 * Constructor
	 * @param args values to form the vector
	 */
	public Vector(double ... args) {
		coor = new double[args.length];
		for (int i = 0; i < args.length; ++i)
			coor[i] = args[i];

		switch (args.length) {
			case 4:
				w = args[3];
			case 3:
				z = args[2];
			case 2:
				y = args[1];
			case 1:
				x = args[0];

		}
	}
	
	/**
	 * Dot product
	 * @param v1 first vector 
	 * @param v2 second vector 
	 * @return result of dot product
	 */
	static public double dot(Vector v1, Vector v2) {
		if (v1.coor.length != v2.coor.length) throw new RuntimeException("Dimmension mismatch. ");
		
		double result = 0;
		for (int i = 0; i < v1.coor.length; ++i)
			result += v1.coor[i] * v2.coor[i];
		
		return result;
	}
	
	/**
	 * Distance between two vectors 
	 * @param v1 first vector
	 * @param v2 second vector
	 * @return distance between the two vector
	 */
	static public double distance(Vector v1, Vector v2) {
		if (v1.coor.length != v2.coor.length) throw new RuntimeException("Dimmension  mismatch. ");
		
		double ret = 0;
		for (int i = 0; i < v1.coor.length; ++i)
			ret += Math.pow(v1.coor[i] - v2.coor[i], 2);
		
		return (double) Math.sqrt(ret);
	}

    static private void distanceTest(Vector v1, Vector v2) {
        System.out.println("v1 = " + v1 + "v2 = " + v2 + "distance = " + distance(v1,v2));
    }
	
	/**
	 * Sum of two vectors
	 * @param v1 first vector
	 * @param v2 second vector
	 * @return vector sum of the two vectors
	 */
	static public Vector sum(Vector v1, Vector v2) {
		if (v1.coor.length != v2.coor.length) throw new RuntimeException("Dimmension mismatch. ");
		
		Vector ret = new Vector(v1.coor.length);
		
		for (int i = 0; i < v1.coor.length; ++i)
			ret.coor[i] = v1.coor[i] + v2.coor[i];
		
		return ret;
	}
	
	/**
	 * Difference of two vectors
	 * @param v1 first vector
	 * @param v2 second vector
	 * @return vector difference of the two vectors
	 */
	static public Vector diff(Vector v1, Vector v2) {
		if (v1.coor.length != v2.coor.length) throw new RuntimeException("Dimmension mismatch. ");
		
		Vector ret = new Vector(v1.coor.length);
		
		for (int i = 0; i < v1.coor.length; ++i)
			ret.coor[i] = v1.coor[i] - v2.coor[i];
		
		return ret;
	}
	
	/**
	 * Product of a vector and a scalar
	 * @param v vector
	 * @param num scalar
	 * @return result of multiplying a vector by a scalar
	 */
	static public Vector prod(Vector v, double num) {
		Vector ret = new Vector(v.coor.length);
		
		for (int i = 0; i < v.coor.length; ++i)
			ret.coor[i] = v.coor[i] * num;
		
		return ret;
	}
	
	/**
	 * Division of a vector by a scalar
	 * @param v vector
	 * @param num scalar
	 * @return result of dividing a vector by a scalar
	 */
	static public Vector div(Vector v, double num) {
		Vector ret = new Vector(v.coor.length);
		
		for (int i = 0; i < v.coor.length; ++i)
			ret.coor[i] = v.coor[i] / num;
		
		return ret;
	}
	
	/**
	 * Division of a scalar by each value of a vector
	 * @param v vector
	 * @param num scalar
	 * @return result of dividing a scalar by a vector
	 */
	static public Vector div(double num, Vector v) {
		Vector ret = new Vector(v.coor.length);
		
		for (int i = 0; i < v.coor.length; ++i)
			ret.coor[i] = (v.coor[i] == 0) ? 0 : num / v.coor[i];
		
		return ret;
	}
	
	/**
	 * Putting each value of a vector to a power
	 * @param v vector 
	 * @param power scalar power
	 * @return result of putting each value of a vector to a power
	 */
	static public Vector pow(Vector v, double power) {
		Vector ret = new Vector(v.coor.length);
		
		for (int i = 0; i < v.coor.length; ++i) 
			ret.coor[i] = (double)Math.pow(v.coor[i], power);
			
		return ret;
	}
	
	/**
	 * Averages two vectors
	 * @param v1 first vector
	 * @param v2 second vector
	 * @return average of two vectors
	 * TODO Re-make for more than two vectors
	 */
	static public Vector avg(Vector v1, Vector v2) {
		if (v1.coor.length != v2.coor.length) throw new RuntimeException("Dimmension mismatch. ");
		
		Vector ret = new Vector(v1.coor.length);
		
		for (int i = 0; i < v1.coor.length; ++i)
			ret.coor[i] = (v1.coor[i] + v2.coor[i]) / 2;
		
		return ret;
	}

    static public Vector cross(Vector v1, Vector v2) {
        if (v1.coor.length != v2.coor.length) throw new RuntimeException("Dimmension mismatch. ");

        Vector ret = new Vector(v1.coor.length);

        for (int i = 0; i < v1.coor.length; ++i)
            ret.coor[i] = v1.coor[i] * v2.coor[i];

        return ret;
    }

    @Override
    public String toString() {
        String ret = ""; int i = 0;
        for(double dim:this.coor) {
            ret += i + " = " + dim + "\n";
            ++i;
        }
        return ret;
    }


    public static void main(String args[]){
        int i = 0;
        while (i < 100) {
            distanceTest(new Vector(0,0,0), new Vector(i,i,i));
            i++;
        }
    }
}



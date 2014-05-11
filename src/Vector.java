
/**
 * @author Nicholas Tokash
 * Vector.java
 * Vector Math Methods and Vector Class Object
 */

public class Vector {
	public float[] coor;
	
	/**
	 * Constructor
	 * @param size size of the new vector
	 */
	public Vector(int size) {
		this.coor = new float[size];
	}
	
	/**
	 * Constructor
	 * @param args values to form the vector
	 */
	public Vector(float ... args) {
		coor = new float[args.length];
		for (int i = 0; i < args.length; ++i)
			coor[i] = args[i];
	}
	
	/**
	 * Dot product
	 * @param v1 first vector 
	 * @param v2 second vector 
	 * @return result of dot product
	 */
	static public float dot(Vector v1, Vector v2) {
		if (v1.coor.length != v2.coor.length) throw new RuntimeException("Dimmension mismatch. ");
		
		float result = 0;
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
	static public float distance(Vector v1, Vector v2) {
		if (v1.coor.length != v2.coor.length) throw new RuntimeException("Dimmension mismatch. ");
		
		float ret = 0;
		for (int i = 0; i < v1.coor.length; ++i)
			ret += Math.pow(v1.coor[i] - v2.coor[i], 2);
		
		return (float) Math.sqrt(ret);
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
	static public Vector prod(Vector v, float num) {
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
	static public Vector div(Vector v, float num) {
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
	static public Vector divVec(float num, Vector v) {
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
	static public Vector pow(Vector v, float power) {
		Vector ret = new Vector(v.coor.length);
		
		for (int i = 0; i < v.coor.length; ++i) 
			ret.coor[i] = (float)Math.pow(v.coor[i], power);
			
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
	
}

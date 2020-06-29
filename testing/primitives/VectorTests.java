package testing.primitives;

import org.junit.jupiter.api.Test;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.*;

class VectorTests {

	/**
	 * testing the "equals" function
	 */
	@Test
	void test_Equalize() {
		Vector v1 = new Vector(1, 0, 1);
		Vector v2 = new Vector(0, 1, 0);
		assertFalse(v1.equals(v2));
	}

	/**
	 * testing the "addVector" function
	 */
	@Test
	void testAddVector() {
		Vector v1 = new Vector(1, 1, 0);
		Vector v2 = new Vector(0, 0, 1);
		Vector v3 = v1.add(v2);
		Vector v4 = new Vector(1, 1, 1);
		assertEquals(v3, v4);
	}

	/**
	 * testing the "subVector" function
	 */
	@Test
	void testSubVector() {
		Vector v1 = new Vector(0, 1, 2);
		Vector v2 = new Vector(2, 0, 1);
		Vector v3 = v1.sub(v2);
		Vector v4 = new Vector(-2, 1, 1);
		assertEquals(v3, v4);
	}

	/**
	 * testing the "multVector" function
	 */
	@Test
	void testMultVector() {
		Vector v1 = new Vector(0, 1, -2);
		Vector v3 = v1.scale(17);
		Vector v4 = new Vector(0, 17, -34);
		assertEquals(v3, v4);
	}

	/**
	 * testing the "length" function
	 */
	@Test
	void testLengthVector() {
		Vector v1 = new Vector(0, 1, -2);
		double result = v1.getLength();
		double myResult = Math.sqrt(5);
		assertEquals(result, myResult);
	}

	/**
	 * testing the "normalize" function
	 */
	@Test
	void testUnitVector() {
		Vector v1 = new Vector(0, 1, -2);
		Vector v2 = v1.normalize();
		Vector v3 = new Vector(0, (1 / Math.sqrt(5)), (-2 / Math.sqrt(5)));
		assertEquals(v2, v3);
	}

	/**
	 * testing the "dotProduct" function
	 */
	@Test
	void testDotProduct() {
		Vector v1 = new Vector(-1, 0, 1);
		Vector v2 = new Vector(5, 1, 6);
		double result = v1.dotProduct(v2);
		assertTrue(result == 1);
	}

	/**
	 * testing the "crossProduct" function
	 */
	@Test
	void testCrossProduct() {
		Vector v1 = new Vector(4, 5, -11);
		Vector v2 = new Vector(2, -6, 7);
		Vector v3 = v1.crossProduct(v2);
		Vector result = new Vector(-31, -50, -34).normalize();
		assertEquals(result, v3);
	}

}

package testing.elements;

import elements.PointLight;
import org.junit.jupiter.api.Test;
import primitives.Color;
import primitives.Point3D;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PointLightTest {

	/**
	 * checks the function "getL"
	 * 
	 * @throws Exception
	 */
	@Test
	void test_getL() throws Exception {

		Point3D p = new Point3D(1, 1, 1);
		Color col = new Color(250, 250, 250);
		PointLight a = new PointLight(p, 1.5, 0.2, 0.3, col);

		Vector l = a.getL(new Point3D(3, 3, 3));
		assertEquals(l, new Vector(2, 2, 2).normalize());

		// another case
		l = a.getL(new Point3D(-5, 0.5, 0));
		assertEquals(l, new Vector(-6, -0.5, -1).normalize());

	}

	/**
	 * checks the function "getIntensity"
	 * 
	 * @throws Exception
	 */
	@Test
	void test_getIntensity() throws Exception {

		Point3D p = new Point3D(1, 1, 1);
		Color col = new Color(250, 250, 250);
		PointLight a = new PointLight(p, 1.5, 0.2, 0.3, col);

		Color intensity = a.getIntensity(new Point3D(2, 2, 2));
		Color tmp = new Color(91, 91, 91);

		assertEquals(intensity, tmp);

	}

}

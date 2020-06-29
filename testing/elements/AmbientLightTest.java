package testing.elements;

import elements.AmbientLight;
import org.junit.jupiter.api.Test;
import primitives.Color;

import static junit.framework.TestCase.assertEquals;

class AmbientLightTest {

	/**
	 * checks that the function work well
	 */
	@Test
	void test_getIntensity() {

		Color a = new Color(250, 250, 250);
		AmbientLight amb = new AmbientLight(a, 0.3);

		Color intensity = amb.getIntensity();
		Color tmp = new Color(75, 75, 75);
		assertEquals(intensity, tmp);

	}

}

package testing.primitives;

import org.junit.jupiter.api.Test;
import primitives.Color;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ColorTest {

	/**
	 * test ctor with overflow & underflow
	 */
	@Test
	void test_ctor() {
		Color a = new Color(400, 1, -400);
		assertEquals(a, new Color(255, 1, 0));
	}

	/**
	 * test scaling with float number it need to round it down
	 */
	@Test
	void test_scale() {
		Color a = new Color(1, 1, 1);
		a.scale(2.5f);
		assertEquals(a, new Color(2, 2, 2));
	}

	/**
	 * test scaling with negative float number it need to be zero
	 */
	@Test
	void test_scale_2() {
		Color a = new Color(1, 1, 1);
		a.scale(-0.5f);
		assertEquals(a, new Color());
	}

	/**
	 * test scaling with negative int number it need to be zero
	 */
	@Test
	void test_scale_3() {
		Color a = new Color(1, 1, 1);
		a.scale(-5);
		assertEquals(a, new Color());
	}

	/**
	 * test scaling with int number
	 */
	@Test
	void test_scale_4() {
		Color a = new Color(1, 1, 1);
		a.scale(5);
		assertEquals(a, new Color(5, 5, 5));
	}

	/**
	 * test scaling overflow with int number
	 */
	@Test
	void test_scale_5() {
		Color a = new Color(1, 1, 1);
		a.scale(500);
		assertEquals(a, new Color(255, 255, 255));
	}

	/**
	 * test adding colors
	 */
	@Test
	void test_add() {
		Color a = new Color(1, 1, 1);
		Color b = new Color(1, 1, 1);
		Color c = new Color(1, 1, 1);

		a.add(b, c);
		assertEquals(a, new Color(3, 3, 3));
	}

	/**
	 * test overflow of adding colors
	 */
	@Test
	void test_add_1() {
		Color a = new Color(1, 1, 1);
		Color b = new Color(255, 1, 255);

		a.add(b);
		assertEquals(a, new Color(255, 2, 255));
	}

	/**
	 * test reduce underflow
	 */
	@Test
	void test_reduce() {
		Color a = new Color(250, 1, 250);

		a.reduce(50);
		assertEquals(a, new Color(200, 0, 200));
	}
}

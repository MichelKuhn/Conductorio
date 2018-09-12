package de.tomgrill.gdxtesting.core;

import com.conductorio.game.Constants;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class UnitTestExample {
	@Test
	public void correctStatsSpacing() {
		int statsElementsTogether = Constants.STATS_ROOM * 4 + Constants.BORDER * 2;
		assertEquals(Constants.DUDE_BOX_SIZE, statsElementsTogether);
	}
}

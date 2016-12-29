package A_programming_Episode.AgileSD;

import junit.framework.TestCase;

public class TestFrame extends TestCase{

	private Game g;
	
	public TestFrame(String name){
		super(name);
	}
	
	public void setUp(){
		g = new Game();
	}
	
	public void testScoreNoThrows(){
		Frame f = new Frame();
		f.add(5);
		assertEquals(5, f.getScore());
	}
	
//	public void testOneThrows(){
//		g.add(5);
//		assertEquals(5, g.getScore());
//		assertEquals(1, g.getCurrentFrame());
//	}
	
	public void testTwoThrowsNoMark(){
		g.add(5);
		g.add(4);
		assertEquals(9, g.getScore());
//		assertEquals(2, g.getCurrentFrame());
	}
	
	public void testFourThrowsNoMark(){
		g.add(5);
		g.add(4);
		g.add(7);
		g.add(2);
		assertEquals(18, g.getScore());
		assertEquals(9, g.scoreForFrame(1));
		assertEquals(18, g.scoreForFrame(2));
//		assertEquals(3, g.getCurrentFrame());
	}
	
	public void testSimpleSpare(){
		g.add(3);
		g.add(7);
		g.add(3);
		assertEquals(13, g.scoreForFrame(1));
		assertEquals(16, g.getScore());
//		assertEquals(2, g.getCurrentFrame());
	}
	
	public void testSimpleFrameAfterSpare(){
		g.add(3);
		g.add(7);
		g.add(3);
		g.add(2);
		assertEquals(13, g.scoreForFrame(1));
		assertEquals(18, g.scoreForFrame(2));
		assertEquals(18, g.getScore());
//		assertEquals(3, g.getCurrentFrame());
	}
	
	public void testPerfectGame(){
		for(int i = 0; i < 12; i++){
			g.add(10);
		}
		assertEquals(300, g.getScore());
//		assertEquals(11, g.getCurrentFrame());
	}
	
	public void testEndOfArray(){
		for(int i = 0; i < 9; i++){
			g.add(0);
			g.add(0);
		}
		g.add(2);
		g.add(8);
		g.add(10);
		assertEquals(20, g.getScore());
	}
	
	public void testSampleGame(){
		g.add(1);
		g.add(4);
		g.add(4);
		g.add(5);
		g.add(6);
		g.add(4);
		g.add(5);
		g.add(5);
		g.add(10);
		g.add(0);
		g.add(1);
		g.add(7);
		g.add(3);
		g.add(6);
		g.add(4);
		g.add(10);
		g.add(2);
		g.add(8);
		g.add(6);
		assertEquals(133, g.getScore());
	}
	
	public void testBreakHeart(){
		for(int i = 0; i < 11; i++){
			g.add(10);
		}
		g.add(9);
		assertEquals(299, g.getScore());
	}
	
	public void testTenthFrameSpare(){
		for(int i = 0; i < 9; i++){
			g.add(10);
		}
		g.add(9);
		g.add(1);
		g.add(1);
		assertEquals(270, g.getScore());
	}
}

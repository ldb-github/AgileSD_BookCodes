package A_programming_Episode.AgileSD;

public class Game {
	// 书籍代码是0， 个人认为这里是1更合适，因为这样就能保证无论是first throw or second throw
	// 对应的itsCurrentFrame都是合适的值。
	private int itsCurrentFrame = 1; 
	private boolean firstThrowInFrame = true;
	private Scorer itsScorer = new Scorer();
	
	public int getScore(){
//		return scoreForFrame(getCurrentFrame() - 1);
//		return scoreForFrame(getCurrentFrame());
		return scoreForFrame(itsCurrentFrame);
	}
	
	public int scoreForFrame(int frame) {
		return itsScorer.scoreForFrame(frame);
	}

	public void add(int pins){
		itsScorer.add(pins);
		adjustCurrentFrame(pins);
	}

	private void adjustCurrentFrame(int pins) {
//		if(firstThrowInFrame){
//			if(pins == 10){
//				itsCurrentFrame++;
//			}else{
//				firstThrowInFrame = false;
//			}
//			
//			
//		}else{
//			firstThrowInFrame = true;
//			itsCurrentFrame++;
//		}
//		itsCurrentFrame = Math.min(11, itsCurrentFrame);
		
//		if (firstThrowInFrame) {
//			if (adjustFrameForStrike(pins) == false) {
//				firstThrowInFrame = false;
//			}
//		} else {
//			firstThrowInFrame = true;
//			advanceFrame();
//		}
		
		if(lastBallInFrame(pins)){
			advanceFrame();
			// 书籍代码没有这行代码,感觉有问题,itsCurrentFrame会不断的增加，
			// 表现出来的值并不是Frame，而是ball
			firstThrowInFrame = true; 
		}else{
			firstThrowInFrame = false;
			
		}
	}

	private boolean lastBallInFrame(int pins) {
		return strike(pins) || !firstThrowInFrame;
	}

	private boolean strike(int pins) {
		return (firstThrowInFrame && pins == 10);
	}

	private boolean adjustFrameForStrike(int pins){
		if(pins == 10){
			advanceFrame();
			return true;
		}
		return false;
	}
	
	private void advanceFrame() {
		itsCurrentFrame = Math.min(10, itsCurrentFrame + 1);;
	}

	public int getCurrentFrame() {
		return itsCurrentFrame;
	}
}

public class Ricochet{
	public static int gridWidth;
	public static int gridHeight;
	public static int xPos = 0;
	public static int yPos = 0;
	public static int numberOfMoves = 0;
	public static boolean ballInCorner = false;
	public static String ballTrajectory = "SE";
	public static String cornerBallEndsUpIn;
	public static int numberOfWallsHit = 0;
	public static int ballVelocity;

	public static void main(String[] args){

		gridHeight = Integer.parseInt(args[0]);
		gridWidth = Integer.parseInt(args[1]);
		ballVelocity = Integer.parseInt(args[2]); 
		
		System.out.println(gridWidth + ", " + gridHeight);

		moveBallAroundGrid();

		cornerBallEndsUpIn = getCornerBallEndsUpIn();
		double timeElapsed = Math.floor(numberOfMoves) / ballVelocity; 


		System.out.println(cornerBallEndsUpIn + " " + numberOfWallsHit + " " + timeElapsed);
	}

	public static void moveBallAroundGrid(){
		do{
			ballMovesOnce();
			numberOfMoves++;
		}while(!ballIsInCorner());
	}

	public static void ballMovesOnce(){
		switch(ballTrajectory){
			case "SE":
				xPos++;
				yPos++;
				break;
			case "SW":
				xPos--;
				yPos++;
				break;
			case "NE":
				xPos++;
				yPos--;
				break;
			case "NW":
				xPos--;
				yPos--;
				break;
		}
	}

	public static boolean ballIsInCorner(){
		System.out.println(xPos + ", " + yPos);
		if(xPos == 0 && yPos == 0){
			setCornerBallEndsUpIn("UL");
			return true;
		}else if(xPos == 0 && yPos == gridHeight){
			setCornerBallEndsUpIn("LL");
			return true;
		}else if(xPos == gridWidth && yPos == 0){
			setCornerBallEndsUpIn("UR");
			return true;
		}else if(xPos == gridWidth && yPos == gridHeight){
			setCornerBallEndsUpIn("LR");
			return true;
		}else if(xPos == gridWidth || yPos == gridHeight || xPos == 0 || yPos == 0){
			registerBallHitsWall();
			ballTrajectory = getNewBallTrajectory();
			System.out.println("new ball trajectory = " + ballTrajectory);
			return false;
		}else{
			System.out.println("none of the above");
			return false;
		}
	}

	private static void registerBallHitsWall(){
		System.out.println("ball hits wall");
		numberOfWallsHit++;
	}

	private static void setCornerBallEndsUpIn(String corner){
		cornerBallEndsUpIn = corner;
	}

	private static String getCornerBallEndsUpIn(){
		return cornerBallEndsUpIn;
	}

	public static String getNewBallTrajectory(){
		//NW
		if(xPos == gridWidth && ballTrajectory == "NE"){
			return "NW";
		}
		if(yPos == gridHeight && ballTrajectory == "SW"){
			return "NW";
		} 
		//NE
		if(xPos == 0 && ballTrajectory == "NW"){
			return "NE";
		}
		if(yPos == gridHeight && ballTrajectory == "SE"){
			return "NE";
		}
		//SW
		if(yPos == 0 && ballTrajectory == "NW"){
			return "SW";
		}
		if(xPos == gridWidth && ballTrajectory == "SE"){
			return "SW";
		}
		//SE
		if(xPos == 0 && ballTrajectory == "SW"){
			return "SE";
		}
		if(yPos == 0 && ballTrajectory == "NE"){
			return "SE";
		}

		return ballTrajectory;
	}

} 
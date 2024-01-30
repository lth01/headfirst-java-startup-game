import java.util.ArrayList;
import java.util.Random;

public class Game {
	private static String[] startupComNames = {
		"awf", "abc", "123", "apple", "google", "nvidea", "tsla"
	};

	private int[] gameMap;

	private GameHelper helper;
	private ArrayList<StartUpCompany> arrStartUp;
	private int mapSize = 0;
	private int comSize = 0;

	private int score = 0;

	public static void main(String[] args) {
		Game game = new Game();
		game.helper = new GameHelper();
		game.arrStartUp = new ArrayList<StartUpCompany>();

		game.setupGameParam();

		game.gameMap = game.helper.createRandomGrid(game.mapSize);
		game.createStartUp();

		game.gameStart();
	}

	public void setupGameParam() {
		this.comSize = 3;
		this.mapSize = 7;
	}

	public void createStartUp() {
		if (this.arrStartUp == null)
			return;

		for (int idx = 0; idx < 3; idx++) {
			Random rand = new Random();
			StartUpCompany com = new StartUpCompany();
			int randIdx = rand.nextInt(startupComNames.length - idx);
			com.setComName(startupComNames[randIdx]);

			// 회사면 랜덤 확정하기 위한 트릭
			startupComNames[randIdx] = startupComNames[startupComNames.length - idx - 1];

			// 회사 좌표 설정
			com.setPosCells(helper.getPlaceStartup(gameMap, mapSize, comSize), mapSize);

			arrStartUp.add(com);
		}
	}

	public void gameStart() {
		boolean tbContinue = true;
		if (helper == null)
			return;

		while (tbContinue) {
			String guessPlace = helper.getGuessPlace();
			getGuessResult(guessPlace);

			score++;

			if (judgeGameOver()) {
				// game end
				System.out.println("All Startups are dead!! your stock is now worthless");
				System.out.format("Took you long enough. %s guesses.", score);
				return;
			}
		}
	}

	public void getGuessResult(String guessPlace) {
		boolean allMiss = true;

		// 추축 결과 확인
		for (StartUpCompany com : arrStartUp) {

			String guessResult = com.guess(guessPlace);

			if (guessResult.equals("miss")) {
				continue;
			}

			if (guessResult.equals("kill")) {
				arrStartUp.remove(com);
			}

			allMiss = false;
			System.out.println(guessResult);
			break;
		}

		if (allMiss) {
			System.out.println("miss");
		}
	}

	public boolean judgeGameOver() {
		return arrStartUp.isEmpty();
	}
}

class StartUpCompany {
	private String comName;
	private ArrayList<String> posCells;
	private int numOfHits = 0;
	private StringBuilder sb;

	StartUpCompany() {
		sb = new StringBuilder();
	}

	public void setComName(String comName) {
		this.comName = comName;
	}

	public void setPosCells(int[] comPos, int mapSize) {
		posCells = new ArrayList<>();
		// 	index array -> Row , Col 형태로 변환하여 저장
		for (int placePosition : comPos) {
			char r = (char)((placePosition / mapSize) + 65);
			int c = placePosition % mapSize;
			sb.append(r);
			sb.append(c);
			posCells.add(sb.toString());

			sb.setLength(0);
		}
	}

	public String guess(String guessPos) {
		boolean guessResult = this.posCells.contains(guessPos);

		if (guessResult) {
			this.numOfHits++;
			this.posCells.remove(guessPos);
		}

		if (guessResult && this.posCells.isEmpty()) {
			System.out.println("Ouch! you sunk " + this.comName);
			return "kill";
		} else if (guessResult) {
			return "hit";
		} else {
			return "miss";
		}
	}
}
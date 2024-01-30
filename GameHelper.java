import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class GameHelper {
	enum mapStatus {
		ABAILABLE,
		NOTABAILABLE
	}

	private Scanner sc;

	private final int MAX_ATTEMPT = 200;

	GameHelper() {
		sc = new Scanner(System.in);
	}

	public int[] createRandomGrid(int mapSize) {
		int[] mapGrid = new int[mapSize * mapSize];
		Arrays.fill(mapGrid, mapStatus.ABAILABLE.ordinal());

		return mapGrid;
	}

	public int[] getPlaceStartup(int[] mapGrid, int mapSize, int comSize) {
		// 	1. 랜덤한 시작 좌표를 가져옵니다.
		// 	2. 시작 좌표로부터 방향을 지정합니다.
		// 	3. 시작 좌표부터, 종료 좌표까지의 인덱스를 갖는 배열을 생성합니다.
		// 	4. 배열이 유효한지 검증합니다. 만약 유효한 경우 반환하고, 유효하지 않을 경우, 다시 루프를 돕니다.
		Random rand = new Random();
		int attempt = 0;
		int[] newPlace = new int[comSize];
		while (attempt++ < MAX_ATTEMPT) {
			int startPos = rand.nextInt(mapSize * mapSize);
			for (int direction = 0; direction < 2; direction++) {
				// 이 과정은 반복시 두번씩 호출되어야해
				int increment = getPlaceDirection(mapSize, direction);

				for (int idx = 0; idx < comSize; idx++) {
					newPlace[idx] = startPos += increment;
				}

				if (coordsAvailable(mapGrid, newPlace))
					return newPlace;
			}
		}

		return null;
	}

	public int getPlaceDirection(int mapSize, int direction) {
		if (direction % 2 == 0) {
			// 로우로 증가시 배열 column 크기만큼 증가
			return mapSize;
		} else {
			return 1;
		}
	}

	public boolean coordsAvailable(int[] mapGrid, int[] newPlace) {
		boolean available = true;
		for (int coords : newPlace) {
			// 이미 선택되었거나, 랜덤한 인덱스가 이미 범위를 초과한 경우
			if ((coords >= mapGrid.length) || (mapGrid[coords] == mapStatus.NOTABAILABLE.ordinal())) {
				available = false;
			} else {
				mapGrid[coords] = mapStatus.NOTABAILABLE.ordinal();
				available = true && available;
			}
		}

		if (!available)
			return false;

		for (int coords : newPlace) {
			mapGrid[coords] = mapStatus.NOTABAILABLE.ordinal();
		}
		return true;
	}

	// 	String 관련
	public String getGuessPlace() {
		System.out.print("Enter a guess: ");
		return sc.next();
	}
}

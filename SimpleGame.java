// public class SimpleGame {
// 	public static void main(String[] args) {
//
// 		// 	StartUpComany 생성
// 		StartUpCompany com = new StartUpCompany();
//
// 		// 	 회사 명 set
// 		com.setComName("안녕");
//
// 		System.out.println(com.getComName());
//
// 		// 포지션 세팅 및 출력
// 		com.setPosCells("A0", "ROW");
//
// 		String[] arrComPos = com.getPosCells();
//
// 		for (String comPos : arrComPos) {
// 			System.out.println(comPos);
// 		}
//
// 		com.setPosCells("A0", "COL");
//
// 		arrComPos = com.getPosCells();
//
// 		System.out.println("col");
// 		for (String comPos : arrComPos) {
// 			System.out.println(comPos);
// 		}
//
// 		// 	스타트업 포지션 질의 테스트
// 		System.out.println(com.guess("A0"));
//
// 		// 틀려보고
// 		System.out.println(com.guess("B0"));
//
// 		System.out.println(com.guess("A1"));
// 		System.out.println(com.guess("A2"));
// 	}
//
// }
//
// class StartUpCompany {
// 	private String comName;
// 	private String[] posCells;
// 	private int numOfHits = 0;
// 	private boolean alive = true;
//
// 	public void setComName(String comName) {
// 		this.comName = comName;
// 	}
//
// 	public String getComName() {
// 		return this.comName;
// 	}
//
// 	public void setPosCells(String startPos, String direction) {
// 		int pivotIdx = 0;
//
// 		this.posCells = new String[3];
// 		switch (direction) {
// 			case "ROW":
// 				pivotIdx = 0;
// 				break;
// 			case "COL":
// 				pivotIdx = 1;
// 				break;
// 		}
// 		this.posCells[0] = startPos;
// 		for (int idx = 1; idx <= 2; idx++) {
// 			int pivotPos = startPos.charAt(pivotIdx) + idx;
// 			String pos = Character.toString(startPos.charAt(1 - pivotIdx)) + "" + Character.toString(pivotPos);
// 			this.posCells[idx] = pos;
// 		}
// 	}
//
// 	public String[] getPosCells() {
// 		return this.posCells;
// 	}
//
// 	public String guess(String guessPos) {
// 		Integer matchIdx = null;
//
// 		for (int idx = 0; idx < this.posCells.length; idx++) {
// 			if (this.posCells[idx] == null)
// 				continue;
//
// 			if (this.posCells[idx].equals(guessPos)) {
// 				matchIdx = idx;
// 				this.numOfHits++;
// 				this.posCells[matchIdx] = null;
// 				break;
// 			}
// 		}
//
// 		if (matchIdx != null && this.numOfHits == 3) {
// 			System.out.println("Ouch! you sunk " + this.comName);
// 			this.alive = false;
// 			return "kill";
// 		} else if (matchIdx != null) {
// 			return "hit";
// 		} else {
// 			return "miss";
// 		}
// 	}
// }

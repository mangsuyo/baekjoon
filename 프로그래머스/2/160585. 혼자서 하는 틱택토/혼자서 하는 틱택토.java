import java.util.*;

class Solution {
	public int solution(String[] board) {
		int answer = -1;

		char[][] graph = new char[board.length][board[0].length()];
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length(); j++) {
				graph[i][j] = board[i].charAt(j);
			}
		}

		int firstCnt = 0;
		int secCnt = 0;

		for (int i = 0; i < graph.length; i++) {
			for (int j = 0; j < graph[i].length; j++) {
				if (graph[i][j] == 'O') {
					firstCnt += 1;
				}
				if (graph[i][j] == 'X') {
					secCnt += 1;
				}
			}
		}

		if (secCnt > firstCnt)
			return 0;

		if (firstCnt > secCnt + 1)
			return 0;

		if (firstCnt == secCnt && isEndGame(graph))
			return 0;

		if(firstCnt > secCnt && isSecondEndGame(graph))
			return 0;
		
		return 1;
	}

	boolean isEndGame(char[][] graph) {
		for (int i = 0; i < graph.length; i++) {
			if (graph[i][0] == 'O' && graph[i][1] == 'O' && graph[i][2] == 'O')
				return true;
		}

		for (int j = 0; j < graph.length; j++) {
			if (graph[0][j] == 'O' && graph[1][j] == 'O' && graph[2][j] == 'O')
				return true;
		}

		if (graph[0][0] == 'O' && graph[1][1] == 'O' && graph[2][2] == 'O')
			return true;

		if (graph[0][2] == 'O' && graph[1][1] == 'O' && graph[2][0] == 'O')
			return true;

		return false;
	}

	boolean isSecondEndGame(char[][] graph) {
		for (int i = 0; i < graph.length; i++) {
			if (graph[i][0] == 'X' && graph[i][1] == 'X' && graph[i][2] == 'X')
				return true;
		}

		for (int j = 0; j < graph.length; j++) {
			if (graph[0][j] == 'X' && graph[1][j] == 'X' && graph[2][j] == 'X')
				return true;
		}

		if (graph[0][0] == 'X' && graph[1][1] == 'X' && graph[2][2] == 'X')
			return true;

		if (graph[0][2] == 'X' && graph[1][1] == 'X' && graph[2][0] == 'X')
			return true;

		return false;
	}
}

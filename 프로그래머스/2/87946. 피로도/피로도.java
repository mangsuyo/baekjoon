import static java.util.Comparator.*;

import java.util.*;

public class Solution {

	public int solution(int k, int[][] dungeons) {
		Arrays.sort(dungeons, Comparator.<int[]>comparingInt((a) -> a[0]).reversed()
			.thenComparingInt(a -> a[1]));

		List<List<Integer>> list = new ArrayList<>();

		List<Integer> numbers = new ArrayList<>();

		for (int i = 0; i < dungeons.length; i++) {
			if (dungeons[i][0] <= k) {
				list.add(new ArrayList<>(Arrays.asList(dungeons[i][0], dungeons[i][1])));
				backtracking(i, k, dungeons, list, numbers, 0);
				break;
			}
		}
		return Collections.max(numbers);
	}

	public void backtracking(int start, int k, int[][] dungeons, List<List<Integer>> list, List<Integer> numbers,
		int count) {

		if (k < list.get(start).get(0)) {
			numbers.add(count);
			return;
		}

		for (int i = start; i < dungeons.length - 1; i++) {
			list.add(Arrays.asList(dungeons[i][0], dungeons[i][1]));
			backtracking(i + 1, k, dungeons, list, numbers, count + 1);
			list.remove(list.size() - 1);
		}

		numbers.add(count);
	}
}

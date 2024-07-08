import java.util.*;

class Solution {

	int row;
	int col;

	String[] keys;

	public int solution(String[][] relation) {
		int answer = 0;

		row = relation.length;
		col = relation[0].length;

		List<List<Integer>> keys = new ArrayList<>();

		backtracking(0, relation, keys, new ArrayList<Integer>());

		List<List<Integer>> candidates = new ArrayList<>();

		Collections.sort(keys, Comparator.comparingInt(item -> item.size()));


		for (int i = 1; i < keys.size(); i++) {
			if(isCandidateKey(keys.get(i), relation, candidates)) {
				answer += 1;
				candidates.add(keys.get(i));
			}
		}


		return answer;
	}

	void backtracking(int start, String[][] relation, List<List<Integer>> keys, List<Integer> list) {

		keys.add(new ArrayList<>(list));

		for (int i = start; i < col; i++) {
			list.add(i);
			backtracking(i + 1, relation, keys, list);
			list.remove(list.size() - 1);
		}
	}

	boolean isCandidateKey(List<Integer> key, String[][] relation, List<List<Integer>> candidates) {
		Set<List<String>> uniqueKeys = new HashSet<>();

		for(List<Integer> candidate: candidates) {
			if(key.containsAll(candidate)){
				return false;
			}
		}

		for (String[] tuple : relation) {
			List<String> temp = new ArrayList<>();
			for (Integer col : key) {
				temp.add(tuple[col]);
			}
			uniqueKeys.add(temp);
		}

		return row == uniqueKeys.size();
	}

}

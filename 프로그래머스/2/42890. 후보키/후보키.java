import java.util.*;

class Solution {

	int n;
	int m;

	List<List<Integer>> uniqueKeys = new ArrayList<>();

	public int solution(String[][] relation) {
		n = relation.length;
		m = relation[0].length;
		int answer = 0;

		List<List<Integer>> candidateKeys = new ArrayList<>();

		backtrack(candidateKeys, new ArrayList<>(), 0);
		
		Collections.sort(candidateKeys, Comparator.comparingInt(item -> item.size()));
		
		for(int i = 0; i < candidateKeys.size(); i++){
			List<Integer> cols = candidateKeys.get(i);
			isCandidateKey(relation, cols);
		}

		return uniqueKeys.size();
	}

	void backtrack(List<List<Integer>> candidateKeys, List<Integer> list, int start) {

		if(!list.isEmpty()){
			candidateKeys.add(new ArrayList<>(list));
		}

		for(int i = start; i < m; i++){
			if(!list.contains(i)){
				list.add(i);
				backtrack(candidateKeys, list, i + 1);
				list.remove(list.size() - 1);
			}
		}
	}

	boolean isCandidateKey(String[][] relation, List<Integer> cols) {

		for(int i = 0 ; i < uniqueKeys.size(); i++){
			if(cols.containsAll(uniqueKeys.get(i))){
				return false;
			}
		}

		Set<List<String>> sets = new HashSet<>();
		for (int i = 0; i < n; i++) {
			List<String> list = new ArrayList<>();
			for (int j = 0; j < cols.size(); j++) {
				int col = cols.get(j);
				list.add(relation[i][col]);
			}
			sets.add(list);
		}

		if(sets.size() == n){
			uniqueKeys.add(new ArrayList<>(cols));
			return true;
		}

		return false;
	}
}

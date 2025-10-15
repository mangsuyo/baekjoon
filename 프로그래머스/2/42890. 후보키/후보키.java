import java.util.*;

class Solution {
    
    int n;
    int m;
    Set<List<Integer>> answers;
    
    public int solution(String[][] relation) {
        int answer = 0;
        n = relation.length;
        m = relation[0].length;
        
        List<List<Integer>> candidates = new ArrayList<>();
        makeCandidates(0, candidates, new ArrayList<>());
        
        candidates.sort((a, b) -> a.size() - b.size());
        
        answers = new HashSet<>();
        for(List<Integer> candidate: candidates){
            if(isCandidateKey(relation, candidate)){
                answer += 1;
                answers.add(candidate);
            }
        }
        return answer;
    }
    
    boolean isCandidateKey(String[][] relation, List<Integer> candidate){
        for(List<Integer> answer: answers){
            if(candidate.containsAll(answer)) return false;
        }
        Set<List<String>> set = new HashSet<>();
        for(int i = 0; i < n; i++){
            List<String> temp = new ArrayList<>();
            for(Integer key: candidate){
                temp.add(relation[i][key]);
            }
            set.add(temp);
        }
        return set.size() == n;
    }
    
    void makeCandidates(int start, List<List<Integer>> candidates, List<Integer> list){
        if(list.size() > 0){
            candidates.add(new ArrayList<>(list));
        }
        
        for(int i = start; i < m; i++){
            list.add(i);
            makeCandidates(i + 1, candidates, list);
            list.remove(list.size() - 1);
        }
    }
}
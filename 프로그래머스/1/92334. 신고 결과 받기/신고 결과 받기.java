import java.util.*;

class Solution {
    public int[] solution(String[] id_list, String[] report, int k) {
        Map<String, Set<String>> map = new HashMap<>();
        Map<String, Integer> reporterMap = new HashMap<>();
        
        for(String r: report){
            String[] cur = r.split(" ");
            map.putIfAbsent(cur[1], new HashSet<>());
            map.get(cur[1]).add(cur[0]);
        }
                
        for(String reported: map.keySet()){
            if(map.get(reported).size() >= k){
                for(String reporter: map.get(reported)){
                    reporterMap.putIfAbsent(reporter, 0);
                    reporterMap.put(reporter, reporterMap.get(reporter) + 1);
                }
            }
        }
        
        int[] answer = new int[id_list.length];
        
        int index = 0;
        for(int i = 0; i < id_list.length; i++){
            String name = id_list[i];
            if(reporterMap.containsKey(name)){
                answer[i] = reporterMap.get(name);
            }
        }
        
        return answer;
    }
}
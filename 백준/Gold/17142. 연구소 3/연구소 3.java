import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import org.w3c.dom.ls.LSOutput;

public class Main {

    static int n;
    static int m;
    static int emptyArea = 0;

    static int[] dr = new int[]{-1, 1, 0, 0};
    static int[] dc = new int[]{0, 0, -1, 1};


    static public class Pair {
        int row;
        int col;
        int count;

        public Pair(int row, int col) {
            this.row = row;
            this.col = col;
        }

        public Pair(int row, int col, int count) {
            this.row = row;
            this.col = col;
            this.count = count;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] size = br.readLine().split(" ");
        n = Integer.parseInt(size[0]);
        m = Integer.parseInt(size[1]);

        int[][] graph = new int[n][n];

        for (int i = 0; i < n; i++) {
            String[] line = br.readLine().split(" ");
            for (int j = 0; j < n; j++) {
                graph[i][j] = Integer.parseInt(line[j]);
            }
        }

        List<Pair> virus = new ArrayList<>();

        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                if(graph[i][j] == 2){
                    virus.add(new Pair(i, j));
                }
                if(graph[i][j] == 0){
                    emptyArea += 1;
                }
            }
        }

        List<List<Pair>> situation = new ArrayList<>();

        backtrack(0, virus, new ArrayList<>(), situation);

        List<Integer> answers = new ArrayList<>();
        for(int i = 0; i < situation.size(); i++){
            answers.add(bfs(situation.get(i), graph));
        }

        int flag = 0;
        for(int i = 0; i < answers.size(); i++){
            if(answers.get(i) != -1) flag = 1;
        }

        if(flag == 1){
            int min = Integer.MAX_VALUE;
            for(int i = 0; i < answers.size(); i++){
                if(answers.get(i) != -1){
                    if(min > answers.get(i)){
                        min = answers.get(i);
                    }
                }
            }

            System.out.println(min);
        }
        else{
            System.out.println(-1);
        }
    }


    public static int bfs(List<Pair> virus, int[][] graph){
        int[][] clone = new int[n][n];
        for(int i = 0; i < n; i++){
            clone[i] = graph[i].clone();
        }

        Queue<Pair> queue= new ArrayDeque<>();

        int depth = 0;
        boolean[][] visited = new boolean[n][n];

        for(Pair v: virus){
            queue.offer(new Pair(v.row, v.col, 0));
            visited[v.row][v.col] = true;
        }

        int virusCount = 0;

        while(!queue.isEmpty()){
            Pair cur = queue.poll();
            for(int i = 0; i < 4; i++){
                int nextR = cur.row + dr[i];
                int nextC = cur.col + dc[i];
                if(0 <= nextR && nextR < n && 0 <= nextC && nextC < n){
                    if(!visited[nextR][nextC] && clone[nextR][nextC] != 1){
                        queue.add(new Pair(nextR, nextC, cur.count + 1));
                        visited[nextR][nextC] = true;
                        if(clone[nextR][nextC] == 0) {
                            virusCount += 1;
                            if(virusCount == emptyArea){
                                return cur.count + 1;
                            }
                        }
                    }
                }
            }
        }

        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                if(clone[i][j] == 0 && !visited[i][j]) return -1;
            }
        }
        
        return depth;
    }
    public static void backtrack(int start, List<Pair> virus, List<Pair> list, List<List<Pair>> situation) {

        if(list.size() == m){
            situation.add(new ArrayList<>(list));
            return;
        }

        for(int i = start; i < virus.size(); i++){
            list.add(virus.get(i));
            backtrack(i + 1, virus, list, situation);
            list.remove(list.size() - 1);
        }
    }
}

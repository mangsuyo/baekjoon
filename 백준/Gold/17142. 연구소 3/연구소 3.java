import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class Main {

    static int N, M;
    static int answer = Integer.MAX_VALUE;
    static int totalEmpty = 0 ;
    static int[] dr = new int[]{-1, 1, 0, 0};
    static int[] dc = new int[]{0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] line = reader.readLine().split(" ");
        N = Integer.parseInt(line[0]);
        M = Integer.parseInt(line[1]);

        char[][] graph = new char[N][N];
        List<Node> virus = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            String[] s = reader.readLine().split(" ");
            for (int j = 0; j < N; j++) {
                graph[i][j] = s[j].charAt(0);
                if (graph[i][j] == '2') virus.add(new Node(i, j, 0));
                else if(graph[i][j] == '0') totalEmpty += 1;
            }
        }
        backtrack(graph, 0, virus, new ArrayList<>());
        System.out.println(answer == Integer.MAX_VALUE ? -1 : answer);
    }


    static void backtrack(char[][] graph, int start, List<Node> virus, List<Node> activeVirus) {
        if (activeVirus.size() == M) {
            answer = Math.min(answer, bfs(graph, activeVirus));
            return;
        }

        for (int i = start; i < virus.size(); i++) {
            activeVirus.add(virus.get(i));
            backtrack(graph, i + 1, virus, activeVirus);
            activeVirus.remove(activeVirus.size() - 1);
        }
    }

    static int bfs(char[][] graph, List<Node> virus) {
        Queue<Node> queue = new ArrayDeque<>();
        boolean[][] visited = new boolean[N][N];
        for (Node n : virus) {
            queue.offer(n);
            visited[n.r][n.c] = true;
        }
        int empty = 0;
        int maxTime = 0;
        while (!queue.isEmpty()) {
            Node cur = queue.poll();
            for (int i = 0; i < 4; i++) {
                int nextR = cur.r + dr[i];
                int nextC = cur.c + dc[i];
                if (0 <= nextR && nextR < N && 0 <= nextC && nextC < N) {
                    if (!visited[nextR][nextC] && graph[nextR][nextC] != '1') {
                        queue.offer(new Node(nextR, nextC, cur.count + 1));
                        visited[nextR][nextC] = true;
                        if(graph[nextR][nextC] == '0'){
                            empty += 1;
                            maxTime = cur.count + 1;
                        }
                    }
                }
            }
        }
        return empty == totalEmpty ? maxTime : Integer.MAX_VALUE;
    }


    static class Node {
        int r;
        int c;
        int count;

        public Node(int r, int c, int count) {
            this.r = r;
            this.c = c;
            this.count = count;
        }
    }
}
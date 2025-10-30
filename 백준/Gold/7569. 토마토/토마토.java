import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N;
    static int M;
    static int H;
    static int k = 0;

    static int[] dr = new int[]{-1, 1, 0, 0, 0, 0};
    static int[] dc = new int[]{0, 0, -1, 1, 0, 0};
    static int[] dz = new int[]{0, 0, 0, 0, -1, 1};

    static class Point {
        int z;
        int r;
        int c;
        int count;

        public Point(int z, int r, int c, int count) {
            this.z = z;
            this.r = r;
            this.c = c;
            this.count = count;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] line = reader.readLine().split(" ");
        M = Integer.parseInt(line[0]);
        N = Integer.parseInt(line[1]);
        H = Integer.parseInt(line[2]);

        List<Point> tomato = new ArrayList<>();

        int answer = 0;

        int[][][] graph = new int[H][N][M];

        for (int z = 0; z < H; z++) {
            for (int r = 0; r < N; r++) {
                String[] s = reader.readLine().split(" ");
                for (int c = 0; c < M; c++) {
                    graph[z][r][c] = Integer.parseInt(s[c]);
                    if (graph[z][r][c] == 1) {
                        tomato.add(new Point(z, r, c, 0));
                    }
                    else if(graph[z][r][c] == 0) {
                        k++;
                    }
                }
            }
        }
        if (k == 0) {
            System.out.println(0);
            return;
        }
        System.out.println(bfs(graph, tomato));
    }

    static int bfs(int[][][] graph, List<Point> tomato){
        boolean[][][] visited = new boolean[H][N][M];
        Queue<Point> queue = new ArrayDeque<>();

        int count = 0;
        for(Point p: tomato){
            queue.offer(p);
            visited[p.z][p.r][p.c] = true;
        }

        while(!queue.isEmpty()){
            Point p = queue.poll();
            for(int i = 0; i < 6; i++){
                int nextZ = p.z + dz[i];
                int nextR = p.r + dr[i];
                int nextC = p.c + dc[i];
                if(0 <= nextZ && nextZ < H && 0 <= nextR && nextR < N && 0 <= nextC && nextC < M){
                    if(!visited[nextZ][nextR][nextC] && graph[nextZ][nextR][nextC] == 0){
                        count += 1;
                        if(count == k){
                            return p.count + 1;
                        }
                        visited[nextZ][nextR][nextC] = true;
                        queue.offer(new Point(nextZ, nextR, nextC, p.count + 1));
                    }
                }
            }
        }

        return -1;
    }
}

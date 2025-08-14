import java.io.*;
import java.util.*;

public class Main {

    static int n;
    static int m;

    static int[] dr = new int[]{-1, 1, 0, 0};
    static int[] dc = new int[]{0, 0, -1, 1};

    static class Pair{
        Ball red;
        Ball blue;
        int count;

        public Pair(Ball red, Ball blue, int count) {
            this.red = red;
            this.blue = blue;
            this.count = count;
        }
    }

    static class Ball{
        int row;
        int col;
        char color;

        public Ball(int row, int col, char color) {
            this.row = row;
            this.col = col;
            this.color = color;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] size = br.readLine().split(" ");
        n = Integer.parseInt(size[0]);
        m = Integer.parseInt(size[1]);

        char[][] graph = new char[n][m];
        for (int i = 0; i < n; i++) {
            String[] line = br.readLine().split("");
            for (int j = 0; j < m; j++) {
                graph[i][j] = line[j].charAt(0);
            }
        }

        int answer = bfs(graph);
        System.out.println(answer);
    }

    private static boolean isRedStartBehind(Ball red, Ball blue, int i){
        switch (i) {
            case 0:
                if (red.row > blue.row)
                    return true;
                break;
            case 1:
                if (red.row < blue.row)
                    return true;
                break;
            case 2:
                if (red.col > blue.col)
                    return true;
                break;
            case 3:
                if (red.col < blue.col)
                    return true;
                break;
        }
        return false;
    }

    private static int bfs(char[][] graph) {
        Queue<Pair> queue = new ArrayDeque<>();
        Ball r = null;
        Ball b = null;
        boolean[][][][] visited = new boolean[n][m][n][m];

        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                if(graph[i][j] == 'R'){
                    r = new Ball(i, j, 'R');
                }
                else if(graph[i][j] == 'B'){
                    b = new Ball(i, j, 'B');
                }
            }
        }

        queue.add(new Pair(r, b, 0));
        visited[r.row][r.col][b.row][b.col] = true;
        boolean isBlueIn = false;
        while(!queue.isEmpty()) {
            Pair cur = queue.poll();
            Ball red = cur.red;
            Ball blue = cur.blue;
            for(int i = 0; i < 4; i++){
                int nextRedR = red.row;
                int nextRedC = red.col;
                int nextBlueR = blue.row;
                int nextBlueC = blue.col;
                isBlueIn = false;
                // BLUE 이동
                while(graph[nextBlueR][nextBlueC] != '#'){
                    // BLUE 이동
                    nextBlueR += dr[i];
                    nextBlueC += dc[i];
                    // BLUE 구멍 검사
                    if(graph[nextBlueR][nextBlueC] == 'O'){
                        isBlueIn = true;
                        break;
                    }
                }
                if(isBlueIn) continue;
                nextBlueR -= dr[i];
                nextBlueC -= dc[i];

                // RED 이동
                while(graph[nextRedR][nextRedC] != '#'){
                    nextRedR += dr[i];
                    nextRedC += dc[i];
                    // RED 구멍 검사
                    if(graph[nextRedR][nextRedC] == 'O' && cur.count < 10){
                        return cur.count + 1;
                    }
                }
                nextRedR -= dr[i];
                nextRedC -= dc[i];

                if(nextRedR == nextBlueR && nextRedC == nextBlueC){
                    boolean isRedBehind = isRedStartBehind(red, blue, i);
                    if(isRedBehind){
                        nextRedR -= dr[i];
                        nextRedC -= dc[i];
                    }
                    else{
                        nextBlueR -= dr[i];
                        nextBlueC -= dc[i];
                    }
                }

                if(!visited[nextRedR][nextRedC][nextBlueR][nextBlueC]){
                    Ball nextRed = new Ball(nextRedR, nextRedC, 'R');
                    Ball nextBlue = new Ball(nextBlueR, nextBlueC, 'B');
                    visited[nextRedR][nextRedC][nextBlueR][nextBlueC] = true;
                    queue.offer(new Pair(nextRed, nextBlue, cur.count + 1));
                }
            }
        }
        return -1;
    }
}

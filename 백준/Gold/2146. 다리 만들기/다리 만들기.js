function solution(input) {
  const N = parseInt(input[0]);
  const graph = [];
  for (let i = 1; i <= N; i++) {
    graph.push(input[i].split(" "));
  }
  const visited = Array.from({ length: N }, () =>
    Array.from({ length: N }).fill(0)
  );
  let cnt = 0;
  let islands = [];
  for (let i = 0; i < N; i++) {
    for (let j = 0; j < N; j++) {
      if (graph[i][j] === "1" && visited[i][j] === 0) {
        islands.push([...bfs(i, j, cnt)]);
        cnt += 1;
      }
    }
  }
  console.log(setBridge());
  function setBridge() {
    const queue = [];
    const dr = [-1, 1, 0, 0];
    const dc = [0, 0, -1, 1];
    const visited = Array.from({ length: N }, () =>
      Array.from({ length: N }).fill(0)
    );
    queue.push(...islands.flat());
    const answer = [];
    while (queue.length) {
      const [curR, curC, island, dist] = queue.shift();
      for (let i = 0; i < 4; i++) {
        const nextR = curR + dr[i];
        const nextC = curC + dc[i];
        if (0 <= nextR && nextR < N && 0 <= nextC && nextC < N) {
          if (graph[nextR][nextC] === "0" && visited[nextR][nextC] === 0) {
            graph[nextR][nextC] = island;
            queue.push([nextR, nextC, island, dist + 1]);
            visited[nextR][nextC] += dist + 1;
          } else if (
            graph[nextR][nextC] != island &&
            graph[nextR][nextC] !== "0"
          ) {
            answer.push(visited[nextR][nextC] + dist);
          }
        }
      }
    }
    return Math.min(...answer);
  }
  function bfs(i, j, cnt) {
    const queue = [[i, j]];
    const dr = [-1, 1, 0, 0];
    const dc = [0, 0, -1, 1];
    visited[i][j] = 1;
    let islands = [];
    while (queue.length) {
      const [curR, curC] = queue.shift();
      islands.push([curR, curC, cnt + 1, 0]);
      graph[curR][curC] = `${cnt + 1}`;
      for (let i = 0; i < 4; i++) {
        const nextR = curR + dr[i];
        const nextC = curC + dc[i];
        if (0 <= nextR && nextR < N && 0 <= nextC && nextC < N) {
          if (visited[nextR][nextC] === 0 && graph[nextR][nextC] === "1") {
            queue.push([nextR, nextC]);
            visited[nextR][nextC] = 1;
          }
        }
      }
    }
    return islands;
  }
}

const fs = require("fs");
const file = process.platform === "linux" ? "/dev/stdin" : "input.txt";
const input = fs.readFileSync(file).toString().trim().split("\n");
solution(input);

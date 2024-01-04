function solution(input) {
  N = input.shift();
  let graph = [];
  for (let i = 0; i < N; i++) {
    row = input.shift().split("");
    graph.push(row);
  }

  let visited = Array.from({ length: N }, () =>
    Array.from({ length: N }, () => false)
  );

  let counts = [0, 0, 0];
  for (let i = 0; i < N; i++) {
    for (let j = 0; j < N; j++) {
      if (!visited[i][j] && graph[i][j] == "R") {
        bfs(i, j, "R");
        counts[0] += 1;
      }
    }
  }
  for (let i = 0; i < N; i++) {
    for (let j = 0; j < N; j++) {
      if (!visited[i][j] && graph[i][j] == "G") {
        bfs(i, j, "G");
        counts[1] += 1;
      }
    }
  }
  for (let i = 0; i < N; i++) {
    for (let j = 0; j < N; j++) {
      if (!visited[i][j] && graph[i][j] == "B") {
        bfs(i, j, "B");
        counts[2] += 1;
      }
    }
  }

  normalPerson = counts[0] + counts[1] + counts[2];

  for (let i = 0; i < N; i++) {
    for (let j = 0; j < N; j++) {
      if (graph[i][j] == "G") {
        graph[i][j] = "R";
      }
    }
  }
  visited = Array.from({ length: N }, () =>
    Array.from({ length: N }, () => false)
  );

  counts = [0, 0, 0];
  for (let i = 0; i < N; i++) {
    for (let j = 0; j < N; j++) {
      if (!visited[i][j] && graph[i][j] == "R") {
        bfs(i, j, "R");
        counts[0] += 1;
      }
    }
  }

  for (let i = 0; i < N; i++) {
    for (let j = 0; j < N; j++) {
      if (!visited[i][j] && graph[i][j] == "B") {
        bfs(i, j, "B");
        counts[2] += 1;
      }
    }
  }
  notNormalPerson = counts[0] + counts[2];

  console.log(normalPerson, notNormalPerson);

  function bfs(row, col, area) {
    dr = [-1, 1, 0, 0];
    dc = [0, 0, -1, 1];
    visited[row][col] = true;
    queue = [[row, col]];

    while (queue.length) {
      const [curRow, curCol] = queue.shift();
      for (let i = 0; i < 4; i++) {
        let nextRow = curRow + dr[i];
        let nextCol = curCol + dc[i];
        if (
          0 <= nextRow &&
          nextRow < N &&
          0 <= nextCol &&
          nextCol < N &&
          !visited[nextRow][nextCol]
        ) {
          if (graph[nextRow][nextCol] == area) {
            queue.push([nextRow, nextCol]);
            visited[nextRow][nextCol] = true;
          }
        }
      }
    }
  }
}

const fs = require("fs");
const file = process.platform === "linux" ? "/dev/stdin" : "input.txt";
const input = fs.readFileSync(file).toString().trim().split("\n");
solution(input);

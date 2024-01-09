class Queue {
  constructor() {
    this.items = {};
    this.front = 0;
    this.rear = 0;
    this.size = 0;
  }
  enqueue(value) {
    this.items[this.rear++] = value;
    this.size++;
  }
  dequeue() {
    const value = this.items[this.front];
    delete this.items[this.front++];
    this.size--;
    return value;
  }
}
function solution(input) {
  const N = parseInt(input[0]);
  let index = 0;
  const answer = [];

  for (let i = 0; i < N; i++) {
    const graph = [];
    index += 1;
    const [width, height] = input[index].split(" ").map(Number);
    for (let j = 1; j <= height; j++) {
      graph.push(input[index + j].split(""));
    }
    index += height;
    answer.push(getEscapeTime(graph, width, height));
  }
  console.log(answer.join("\n"));
}
function getEscapeTime(graph, width, height) {
  const personLocation = [];
  const fireLocation = [];
  for (let i = 0; i < height; i++) {
    for (let j = 0; j < width; j++) {
      if (graph[i][j] === "@") {
        personLocation.push([i, j, 0]);
      } else if (graph[i][j] == "*") {
        fireLocation.push([i, j, 0]);
      }
    }
  }
  return bfs(personLocation, fireLocation);

  function bfs(personLocation, fireLocation) {
    const queue = new Queue();
    queue.enqueue(...personLocation);
    for (let i = 0; i < fireLocation.length; i++) {
      queue.enqueue(fireLocation[i]);
    }
    const dr = [-1, 1, 0, 0];
    const dc = [0, 0, -1, 1];
    let answer;
    while (queue.size > 0) {
      const [curR, curC, count] = queue.dequeue();
      if (graph[curR][curC] === "@") {
        if (isEscape(curR, curC)) return count + 1;
      }
      for (let i = 0; i < 4; i++) {
        let nextR = curR + dr[i];
        let nextC = curC + dc[i];
        if (0 <= nextR && nextR < height && 0 <= nextC && nextC < width) {
          if (graph[curR][curC] == "@") {
            if (graph[nextR][nextC] == ".") {
              queue.enqueue([nextR, nextC, count + 1]);
              graph[nextR][nextC] = graph[curR][curC];
            }
          } else if (graph[curR][curC] == "*") {
            if (graph[nextR][nextC] == "." || graph[nextR][nextC] == "@") {
              queue.enqueue([nextR, nextC, count + 1]);
              graph[nextR][nextC] = graph[curR][curC];
            }
          }
        }
      }
    }
    return "IMPOSSIBLE";

    function isEscape(lastR, lastC) {
      if (lastR == 0 || lastR == height - 1 || lastC == 0 || lastC == width - 1)
        return true;
      return false;
    }
  }
}
const fs = require("fs");
const file = process.platform === "linux" ? "/dev/stdin" : "input.txt";
const input = fs.readFileSync(file).toString().trim().split("\n");
solution(input);

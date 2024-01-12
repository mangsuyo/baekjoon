function solution(input) {
  const N = parseInt(input[0]);
  const dots = [];
  for (let i = 1; i <= N; i++) {
    const dot = input[i].split(" ").map(Number);
    dots.push(dot);
  }

  dots.sort((a, b) => a[0] - b[0]);
  let line = 0;
  let [startPoint, endPoint] = dots[0];
  for (let i = 1; i < dots.length; i++) {
    const [start, end] = dots[i];
    if (start >= endPoint) {
      line = line + (endPoint - startPoint);
      startPoint = start;
      endPoint = end;
    } else if (start < endPoint) {
      if (end >= endPoint) {
        endPoint = end;
      }
    }
  }
  line = line + endPoint - startPoint;
  console.log(line);
}

const fs = require("fs");
const file = process.platform === "linux" ? "/dev/stdin" : "input.txt";
const input = fs.readFileSync(file).toString().trim().split("\n");
solution(input);

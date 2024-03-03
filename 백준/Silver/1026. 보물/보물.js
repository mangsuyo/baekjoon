function solution(input) {
  const N = parseInt(input[0]);
  const A = input[1]
    .split(" ")
    .map(Number)
    .sort((a, b) => a - b);
  const B = input[2]
    .split(" ")
    .map(Number)
    .sort((a, b) => b - a);
  let minValue = 0;
  for (let i = 0; i < N; i++) {
    const value = A[i] * B[i];
    minValue += value;
  }
  console.log(minValue);
}

const fs = require("fs");
const file = process.platform === "linux" ? "/dev/stdin" : "input.txt";
const input = fs.readFileSync(file).toString().trim().split("\n");
solution(input);

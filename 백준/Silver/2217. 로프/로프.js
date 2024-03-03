function solution(input) {
  const N = parseInt(input[0]);
  const lopes = [];
  for (let i = 1; i <= N; i++) {
    lopes.push(parseInt(input[i]));
  }
  lopes.sort((a, b) => a - b);
  const weights = [];
  for (let i = 0; i < lopes.length; i++) {
    const lope = lopes[i];
    weights.push(lope * (lopes.length - i));
  }

  console.log(Math.max(...weights));
}

const fs = require("fs");
const file = process.platform === "linux" ? "/dev/stdin" : "input.txt";
const input = fs.readFileSync(file).toString().trim().split("\n");
solution(input);

function solution(input) {
  const N = parseInt(input);
  for (let i = 1; i <= 9; i++) {
    console.log(`${N} * ${i} = ${N * i}`);
  }
}

const fs = require("fs");
const file = process.platform === "linux" ? "/dev/stdin" : "input.txt";
const input = fs.readFileSync(file).toString().trim();
solution(input);

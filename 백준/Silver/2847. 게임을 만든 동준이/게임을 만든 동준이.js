function solution(input) {
  const list = input.map(Number).reverse();
  const N = list.pop();
  let maxValue = list[0];
  let cnt = 0;
  const nums = [maxValue];
  for (let i = 1; i < N; i++) {
    if (maxValue <= list[i]) {
      cnt = cnt + list[i] - maxValue + 1;
      list[i] = list[i] - (list[i] - maxValue) - 1;
    }
    maxValue = list[i];
    nums.push(list[i]);
  }
  console.log(cnt);
}

const fs = require("fs");
const file = process.platform === "linux" ? "/dev/stdin" : "input.txt";
const input = fs.readFileSync(file).toString().trim().split("\n");
solution(input);

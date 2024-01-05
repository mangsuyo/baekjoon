function solution(input) {
  const [N, M] = input.map(Number);
  const nums = [];
  const answer = [];
  for (let i = 1; i <= N; i++) {
    nums.push(i);
  }
  function backtrack(curr) {
    if (curr.length === M) {
      answer.push([...curr]);
      return;
    }

    for (let num of nums) {
      if (!curr.includes(num)) {
        curr.push(num);
        backtrack(curr);
        curr.pop();
      }
    }
  }
  backtrack([]);
  for (let i = 0; i < answer.length; i++) {
    console.log(answer[i].join(" "));
  }
}

const fs = require("fs");
const file = process.platform === "linux" ? "/dev/stdin" : "input.txt";
const input = fs.readFileSync(file).toString().trim().split(" ");
solution(input);

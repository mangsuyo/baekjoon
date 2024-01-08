function solution(input) {
  const [N, M] = input.map(Number);
  const nums = Array.from({ length: N }, (_, index) => index + 1);
  const answer = [];
  function backtrack(curr) {
    if (curr.length === M) {
      for (let i = 0; i < curr.length - 1; i++) {
        if (curr[i] > curr[i + 1]) return;
      }
      answer.push([...curr]);
      return;
    }
    for (let num of nums) {
      //   if (!curr.includes(num)) {
      curr.push(num);
      backtrack(curr);
      curr.pop();
      //   }
    }
  }
  backtrack([]);
  const result = [];
  for (let i = 0; i < answer.length; i++) {
    result.push(answer[i].join(" "));
  }
  console.log(result.join("\n"));
}

const fs = require("fs");
const file = process.platform === "linux" ? "/dev/stdin" : "input.txt";
const input = fs.readFileSync(file).toString().trim().split(" ");
solution(input);

function solution(input) {
  const T = parseInt(input[0]);
  const answer = [];
  for (let i = 1; i < 2 * T; i += 2) {
    const N = parseInt(input[i]);
    const prices = input[i + 1].split(" ").map(Number).reverse();
    let maxPrice = prices[0];
    let result = 0n;
    // 10 7 6
    // 1 1 3 1 2
    for (let j = 1; j < prices.length; j++) {
      if (maxPrice > prices[j]) {
        result += BigInt(maxPrice - prices[j]);
      } else {
        maxPrice = prices[j];
      }
    }
    answer.push(result);
  }
  console.log(answer.join("\n"));
}

const fs = require("fs");
const file = process.platform === "linux" ? "/dev/stdin" : "input.txt";
const input = fs.readFileSync(file).toString().trim().split("\n");
solution(input);

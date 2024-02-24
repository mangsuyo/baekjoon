function solution(input) {
  const heights = input.map((item) => parseInt(item)).sort((a, b) => a - b);
  const totalHeights = heights.reduce((sum, item) => (sum += item), 0);
  const answer = [];
  let sliceOne = null,
    sliceTwo = null;
  for (let i = 0; i < heights.length; i++) {
    if (sliceOne !== null && sliceTwo !== null) break;
    for (let j = i + 1; j < heights.length; j++) {
      if (totalHeights - heights[i] - heights[j] === 100) {
        sliceOne = i;
        sliceTwo = j;
      }
    }
  }
  for (let i = 0; i < heights.length; i++) {
    if (i != sliceOne && i != sliceTwo) answer.push(heights[i]);
  }
  console.log(answer.join("\n"));
}

const fs = require("fs");
const file = process.platform === "linux" ? "/dev/stdin" : "input.txt";
const input = fs.readFileSync(file).toString().trim().split("\n");
solution(input);

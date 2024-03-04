function solution(input) {
  if (input.includes("0") && input.includes("1")) {
    const oneSpin = input.split("0").filter((item) => item != "").length;
    const zeroSpin = input.split("1").filter((item) => item != "").length;
    console.log(Math.min(oneSpin, zeroSpin));
  } else console.log(0);
}

const fs = require("fs");
const file = process.platform === "linux" ? "/dev/stdin" : "input.txt";
const input = fs.readFileSync(file).toString().trim();
solution(input);

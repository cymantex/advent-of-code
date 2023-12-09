import * as fs from "fs";

if (process.argv.length < 3) {
  console.log("Usage: printSolution.ts <day-number>");
  process.exit(0);
}

const printSolutionForDay = async (day: string) => {
  const { printSolution } = await import(`./${day}/${day}.ts`);
  const daysWithoutSplit = ["day5"];
  const input = readInput(`${day}.txt`);
  printSolution(daysWithoutSplit.includes(day) ? input : input.split("\n"));
};

function readInput(fileName: string): string {
  return fs.readFileSync(`src/inputs/${fileName}`).toString();
}

await printSolutionForDay(`day${parseInt(process.argv[2], 10)}`);

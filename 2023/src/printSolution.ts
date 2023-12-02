import * as fs from "fs";

if (process.argv.length < 3) {
  console.log("Usage: printSolution.ts <day-number>");
  process.exit(0);
}

const printSolutionForDay = async (day: string) => {
  const { printSolution } = await import(`./${day}/${day}.ts`);
  printSolution(readInputLines(`${day}.txt`));
};

function readInputLines(fileName: string): string[] {
  return fs.readFileSync(`src/inputs/${fileName}`).toString().split("\n");
}

await printSolutionForDay(`day${parseInt(process.argv[2], 10)}`);

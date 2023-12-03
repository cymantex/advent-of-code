import { Position } from "./position.ts";

export class SymbolMap {
  private map: Map<string, string> = new Map<string, string>();

  constructor() {
    this.map = new Map<string, string>();
  }

  public get(position: Position): string {
    return this.map.get(JSON.stringify(position))!;
  }

  public set(position: Position, symbol: string): void {
    this.map.set(JSON.stringify(position), symbol);
  }

  public has(position: Position): boolean {
    return this.map.has(JSON.stringify(position));
  }

  public entries(): Array<[Position, string]> {
    return Array.from(this.map.entries()).map(
      ([position, symbol]) => [JSON.parse(position), symbol] as [Position, string]
    );
  }
}

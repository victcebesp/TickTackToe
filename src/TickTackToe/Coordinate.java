package TickTackToe;

public class Coordinate {
    private Game.Position column;
    private Game.Position row;

    public Coordinate(Game.Position row, Game.Position column) {
        this.row = row;
        this.column = column;
    }

    @Override
    public boolean equals(Object obj) {
        return ((Coordinate)obj).column == column && ((Coordinate)obj).row == row;
    }

    @Override
    public int hashCode() {
        return row.hashCode() * column.hashCode();
    }

    public Game.Position xCoordinate() {
        return column;
    }

    public Game.Position yCoordinate() {
        return row;
    }
}

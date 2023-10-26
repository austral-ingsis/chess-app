package common;

import java.util.Objects;

public class Position {
    private int row;
    private int column;

    public Position(int fila, int columna) {
        this.row = fila;
        this.column = columna;
    }


    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return row == position.row && column == position.column;
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, column);
    }


}

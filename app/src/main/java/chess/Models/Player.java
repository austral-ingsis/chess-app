package chess.Models;

import chess.Results.PlayerMove;

import java.util.Scanner;

public class Player {
    private SideColor color;

    public void setColor(SideColor color) {
        this.color = color;
    }

    public SideColor getColor() {
        return color;
    }
}

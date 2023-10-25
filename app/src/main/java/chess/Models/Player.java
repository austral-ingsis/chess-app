package chess.Models;

import chess.Results.PlayerMove;

import java.util.Scanner;

public class Player {
    private SideColor color;

    public void setColor(SideColor color) {
        this.color = color;
    }

    public PlayerMove<String,Integer> definePlay() {
        Scanner scanner = new Scanner(System.in);
            System.out.println("Enter the name of the piece you want to move");
            String pieceName = scanner.nextLine();

            System.out.println("Enter the column of the square you want to move to");
            int column = scanner.nextInt();
            System.out.println("Enter the row of the square you want to move to");
            int row = scanner.nextInt();

        return new PlayerMove<String,Integer>(pieceName,column,row);
    }
    public SideColor getColor() {
        return color;
    }
}

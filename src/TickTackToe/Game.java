package TickTackToe;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Game {

    private Token lastToken;
    private List<Coordinate> coordinatesTaken = new ArrayList<>();
    private Map<Coordinate, Token> map = new HashMap<>();

    public Game() {
        lastToken = Token.O;
    }

    public State play(Token token, Coordinate coordinate){

        map.put(coordinate, token);

        if (aPlayerWin(token, coordinate)) return State.WIN;

        if (theCoordinateIsInvalid(token, coordinate)) return State.INVALID;

        coordinatesTaken.add(coordinate);
        lastToken = token;

        return State.PLAYING;
    }

    private boolean aPlayerWin(Token token, Coordinate coordinate) {
        return aPlayerHasARowOrColumnCompletelySelected(token, coordinate) || aPlayerHasADiagonalCompletelySelected(token);
    }

    private boolean aPlayerHasADiagonalCompletelySelected(Token token) {
        return aPlayerHasCompletelySelectedFirstDiagonal(token) ||
                aPlayerHasCompletelySelectedSecondDiagonal(token);
    }

    private boolean aPlayerHasCompletelySelectedSecondDiagonal(Token token) {
        return (map.get(new Coordinate(Position.THIRD, Position.FIRST)) == token) &&
                (map.get(new Coordinate(Position.SECOND, Position.SECOND)) == token) &&
                (map.get(new Coordinate(Position.FIRST, Position.THIRD)) == token);
    }

    private boolean aPlayerHasCompletelySelectedFirstDiagonal(Token token) {
        return (map.get(new Coordinate(Position.FIRST, Position.FIRST)) == token) &&
                (map.get(new Coordinate(Position.SECOND, Position.SECOND)) == token) &&
                (map.get(new Coordinate(Position.THIRD, Position.THIRD)) == token);
    }

    private boolean aPlayerHasARowOrColumnCompletelySelected(Token token, Coordinate coordinate) {
        return aRowIsCompletelyTakenBy(token, coordinate.xCoordinate()) ||
               aColumnIsCompletelyTakenBy(token, coordinate.yCoordinate());
    }

    private boolean aColumnIsCompletelyTakenBy(Token token, Position row) {
        return (map.get(new Coordinate(row, Position.FIRST)) == token) &&
                (map.get(new Coordinate(row, Position.SECOND)) == token) &&
                (map.get(new Coordinate(row, Position.THIRD)) == token);
    }

    private boolean aRowIsCompletelyTakenBy(Token token, Position column) {
        return (map.get(new Coordinate(Position.FIRST, column)) == token) &&
               (map.get(new Coordinate(Position.SECOND, column)) == token) &&
               (map.get(new Coordinate(Position.THIRD, column)) == token);
    }

    private boolean theCoordinateIsInvalid(Token token, Coordinate coordinate) {
        return coordinatesTaken.contains(coordinate) || token == lastToken;
    }

    public enum Position {
        SECOND, THIRD, FIRST
    }

    public enum Token{
        X, O
    }

    public enum State{
        PLAYING, INVALID, WIN
    }
}
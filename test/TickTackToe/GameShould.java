package TickTackToe;

import org.junit.Before;
import org.junit.Test;

import static TickTackToe.Game.*;
import static TickTackToe.Game.Token.*;
import static TickTackToe.GameShould.Direction.*;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class GameShould {

    private Coordinate coordinate1;
    private Coordinate coordinate2;
    private Coordinate coordinate3;
    private Game game;
    private Coordinate coordinate1_O;
    private Coordinate coordinate2_O;

    @Before
    public void setUp() throws Exception {
        game = new Game();
    }

    @Test
    public void
    start_always_with_player_X() {
        coordinate1 = new Coordinate(Position.FIRST, Position.FIRST);
        assertThat(game.play(X, coordinate1), is(State.PLAYING));
    }

    @Test
    public void
    cannot_start_with_player_O() {
        coordinate1 = new Coordinate(Position.FIRST, Position.FIRST);
        assertThat(game.play(O, coordinate1), is(State.INVALID));
    }

    @Test
    public void
    players_must_not_play_twice() {
        coordinate1 = new Coordinate(Position.FIRST, Position.FIRST);
        game.play(X, coordinate1);
        assertThat(game.play(X, coordinate1), is(State.INVALID));
    }

    @Test
    public void
    players_must_alternate() {
        coordinate1 = new Coordinate(Position.FIRST, Position.FIRST);
        coordinate2 = new Coordinate(Position.FIRST, Position.SECOND);
        game.play(X, coordinate1);
        assertThat(game.play(O, coordinate2), is(State.PLAYING));
    }

    @Test
    public void
    players_must_not_play_twice_in_the_same_position() {
        coordinate1 = new Coordinate(Position.FIRST, Position.FIRST);
        game.play(X, coordinate1);
        assertThat(game.play(O, coordinate1), is(State.INVALID));
    }

    @Test
    public void
    players_must_not_play_twice_in_the_same_position_ever() {
        coordinate1 = new Coordinate(Position.FIRST, Position.FIRST);
        game.play(X, coordinate1);
        assertThat(game.play(O, coordinate1), is(State.INVALID));
    }
    
    @Test public void
    win_X_when_three_position_in_the_first_row_have_been_selected() {
        playForARowOrColumnWin(X, Position.FIRST, ROW);
        assertThat(game.play(X, coordinate3), is(State.WIN));
    }


    @Test public void
    win_O_when_three_position_in_the_first_row_have_been_selected() {
        playForARowOrColumnWin(O, Position.FIRST, ROW);
        assertThat(game.play(O, coordinate3), is(State.WIN));
    }

    @Test public void
    win_X_when_three_position_in_the_second_row_have_been_selected() {
        playForARowOrColumnWin(X, Position.SECOND, ROW);
        assertThat(game.play(X, coordinate3), is(State.WIN));
    }

    @Test public void
    win_O_when_three_position_in_the_second_row_have_been_selected() {
        playForARowOrColumnWin(O, Position.SECOND, ROW);
        assertThat(game.play(O, coordinate3), is(State.WIN));
    }

    @Test public void
    win_X_when_three_position_in_the_third_row_have_been_selected() {
        playForARowOrColumnWin(X, Position.THIRD, ROW);
        assertThat(game.play(X, coordinate3), is(State.WIN));
    }


    @Test public void
    win_O_when_three_position_in_the_third_row_have_been_selected() {
        playForARowOrColumnWin(O, Position.THIRD, ROW);
        assertThat(game.play(O, coordinate3), is(State.WIN));
    }

    @Test public void
    win_X_when_three_position_in_the_first_column_have_been_selected() {
        playForARowOrColumnWin(X, Position.FIRST, COLUMN);
        assertThat(game.play(X, coordinate3), is(State.WIN));
    }

    @Test public void
    win_O_when_three_position_in_the_first_column_have_been_selected() {
        playForARowOrColumnWin(O, Position.FIRST, COLUMN);
        assertThat(game.play(O, coordinate3), is(State.WIN));
    }

    @Test public void
    win_X_when_three_position_in_the_second_column_have_been_selected() {
        playForARowOrColumnWin(X, Position.SECOND, COLUMN);
        assertThat(game.play(X, coordinate3), is(State.WIN));
    }

    @Test public void
    win_X_when_three_position_in_the_first_diagonal_have_been_selected() {
        playForAFirstDiagonalWin(X);
        assertThat(game.play(X, coordinate3), is(State.WIN));
    }

    @Test public void
    win_O_when_three_position_in_the_first_diagonal_have_been_selected() {
        playForAFirstDiagonalWin(O);
        assertThat(game.play(O, coordinate3), is(State.WIN));
    }

    @Test public void
    win_X_when_three_position_in_the_second_diagonal_have_been_selected() {
        playForASecondDiagonalWin(X);
        assertThat(game.play(X, coordinate3), is(State.WIN));
    }

    @Test public void
    win_O_when_three_position_in_the_second_diagonal_have_been_selected() {
        playForASecondDiagonalWin(O);
        assertThat(game.play(O, coordinate3), is(State.WIN));
    }

    private void playForARowOrColumnWin(Token token, Position position, Direction direction) {

        Token otherToken = token == X ? O : X;

        if (direction == ROW) {

            coordinate1 = new Coordinate(Position.FIRST, position);
            game.play(token, coordinate1);
            coordinate1_O = new Coordinate(Position.THIRD, Position.FIRST);
            game.play(otherToken, coordinate1_O);
            coordinate2 = new Coordinate(Position.SECOND, position);
            game.play(token, coordinate2);
            coordinate2_O = new Coordinate(Position.THIRD, Position.THIRD);
            game.play(otherToken, coordinate2_O);
            coordinate3 = new Coordinate(Position.THIRD, position);

        } else if(direction == COLUMN){

            coordinate1 = new Coordinate(position, Position.FIRST);
            game.play(token, coordinate1);
            coordinate1_O = new Coordinate(Position.THIRD, Position.FIRST);
            game.play(otherToken, coordinate1_O);
            coordinate2 = new Coordinate(position, Position.SECOND);
            game.play(token, coordinate2);
            coordinate2_O = new Coordinate(Position.THIRD, Position.THIRD);
            game.play(otherToken, coordinate2_O);
            coordinate3 = new Coordinate(position, Position.THIRD);

        }
    }

    private void playForAFirstDiagonalWin(Token token) {

        Token otherToken = token == X ? O : X;

        coordinate1 = new Coordinate(Position.FIRST, Position.FIRST);
        game.play(token, coordinate1);
        coordinate1_O = new Coordinate(Position.THIRD, Position.FIRST);
        game.play(otherToken, coordinate1_O);
        coordinate2 = new Coordinate(Position.SECOND, Position.SECOND);
        game.play(token, coordinate2);
        coordinate2_O = new Coordinate(Position.THIRD, Position.SECOND);
        game.play(otherToken, coordinate2_O);
        coordinate3 = new Coordinate(Position.THIRD, Position.THIRD);
    }

    private void playForASecondDiagonalWin(Token token) {

        Token otherToken = token == X ? O : X;

        coordinate1 = new Coordinate(Position.THIRD, Position.FIRST);
        game.play(token, coordinate1);
        coordinate1_O = new Coordinate(Position.FIRST, Position.FIRST);
        game.play(otherToken, coordinate1_O);
        coordinate2 = new Coordinate(Position.SECOND, Position.SECOND);
        game.play(token, coordinate2);
        coordinate2_O = new Coordinate(Position.THIRD, Position.SECOND);
        game.play(otherToken, coordinate2_O);
        coordinate3 = new Coordinate(Position.FIRST, Position.THIRD);
    }

    public enum Direction{
        ROW, COLUMN
    }

}

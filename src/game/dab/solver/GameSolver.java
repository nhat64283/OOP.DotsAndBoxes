package game.dab.solver;
import game.dab.objects.Board;
import game.dab.objects.ColorTeam;
import game.dab.objects.Edge;

public abstract class GameSolver {

    protected int referenceColor;
    private final static int cScore = 20;
    private final static int cThree = 15;
    private final static int cTwo = 10;

    protected int heuristic(final Board board, int color) {
        int value;
        if(referenceColor == ColorTeam.RED)
            value = cScore * board.getRedScore() - cScore * board.getBlueScore();
        else
            value = cScore * board.getBlueScore() - cScore * board.getRedScore();
        if(referenceColor == color)
            value += cThree * board.getBoxCount(3) - cTwo * board.getBoxCount(2);
        else
            value -= cThree * board.getBoxCount(3) - cTwo * board.getBoxCount(2);
        return value;
    }

    public abstract Edge getNextMove(final Board board, int color );
}

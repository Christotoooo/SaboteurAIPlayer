package student_player;

import Saboteur.SaboteurBoard;
import Saboteur.SaboteurMove;
import Saboteur.cardClasses.SaboteurBonus;
import Saboteur.cardClasses.SaboteurCard;
import Saboteur.cardClasses.SaboteurTile;
import boardgame.Move;

import Saboteur.SaboteurPlayer;
import Saboteur.SaboteurBoardState;

import java.util.ArrayList;

/** A player file submitted by a student. */
public class Dummy extends SaboteurPlayer {

    /**
     * You must modify this constructor to return your student number. This is
     * important, because this is what the code that runs the competition uses to
     * associate you with your agent. The constructor should do nothing else.
     */
    public Dummy() {
        super("260779480");
    }

    /**
     * This is the primary method that you need to implement. The ``boardState``
     * object contains the current state of the game, which your agent must use to
     * make decisions.
     */
    public Move chooseMove(SaboteurBoardState boardState) {
        // You probably will make separate functions in DummyMyTools.
        // For example, maybe you'll need to load some pre-processed best opening
        // strategies...
        // DummyMyTools.getSomething();
        // Is random the best you can do?
        Move myMove = boardState.getRandomMove();
        int[][] intBoard = boardState.getHiddenIntBoard();
        SaboteurTile[][] board = boardState.getHiddenBoard();
        ArrayList<SaboteurMove> allMoves = boardState.getAllLegalMoves();
        ArrayList<SaboteurCard> cards = boardState.getCurrentPlayerCards();
        for(SaboteurCard sc : cards){
            System.out.println(sc.getName());
        }
        for(SaboteurMove i : allMoves){
            System.out.println(i.toPrettyString());
        }
        int NbMalus = boardState.getNbMalus(boardState.getTurnPlayer());
        SaboteurMove move;
        System.out.println("NBMALUS:" + NbMalus);
        if(NbMalus > 0){
            move = DummyMyTools.useBonus(cards, boardState);
            if(move != null){
                return move;
            }
            move = DummyMyTools.useMap(cards, boardState);
            if(move != null){
                return move;
            }
            move = DummyMyTools.dropTrashCard(cards, boardState);
            return move;
        }else{
            move = DummyMyTools.useMap(cards, boardState);
            if(move != null){
                return move;
            }
            int depth = DummyMyTools.findMaxDepth(allMoves, intBoard);
            if(depth >= 9){
                move = DummyMyTools.useMalus(cards, boardState);
                if(move != null){
                    return move;
                }
            }
            System.out.println("DEPTH: " + depth);
            if(depth > 12){
                depth = 12;
            }
            if(depth == 11){
                move = DummyMyTools.useTail11(depth, allMoves, intBoard);
                if (move != null) {
                    return move;
                }
            }
            else if(depth == 12){
                move = DummyMyTools.useTail12(depth, allMoves, intBoard);
                if (move != null) {
                    return move;
                }
            }else {
                move = DummyMyTools.useTail(depth, allMoves, intBoard);
                if (move != null) {
                    return move;
                }
            }
            move = DummyMyTools.dropTrashCard(cards, boardState);
            if(move != null){
                return move;
            }else {
                return boardState.getRandomMove();
            }
        }

//        for(SaboteurMove i : allMoves){
//
//        }
        // Return your move to be processed by the server.
//        return myMove;
    }
}
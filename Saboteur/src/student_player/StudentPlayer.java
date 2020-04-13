package student_player;

import Saboteur.cardClasses.SaboteurBonus;
import Saboteur.cardClasses.SaboteurCard;
import Saboteur.cardClasses.SaboteurTile;

import Saboteur.SaboteurBoard;
import Saboteur.SaboteurMove;

import Saboteur.SaboteurBoardState;
import boardgame.Move;

import Saboteur.SaboteurPlayer;


import java.util.ArrayList;

public class StudentPlayer extends SaboteurPlayer {

    public StudentPlayer() {
        super("260760794");
    }

    public Move chooseMove(SaboteurBoardState boardState) {
    	
    	// initialization of useful input and resources.
        ArrayList<SaboteurCard> cards = boardState.getCurrentPlayerCards();
        ArrayList<SaboteurMove> all_legal_moves = boardState.getAllLegalMoves();
        SaboteurTile[][] board = boardState.getHiddenBoard();
        int[][] hidden_int_board = boardState.getHiddenIntBoard();

        
        //Saboteurmy_one_move my_one_move;
        
        int current_malus_number = boardState.getNbMalus(boardState.getTurnPlayer());
        
        if(current_malus_number>0){
        	
        	SaboteurMove my_one_move;
            // first of all, we use Bonus if we are cursed by a Malus
        	my_one_move = MyTools.play_a_bonus(cards,boardState);
            if(my_one_move!=null) return my_one_move;
            
            // second, if we have a Map card, then we use it
            my_one_move = MyTools.play_a_map(cards,boardState);
            if(my_one_move!=null) return my_one_move;
            
            // third, if we do not have any Bonus nor Map, we get rid of a useless card
            my_one_move = MyTools.delete_useless_card(cards, boardState);
            return my_one_move;
            
        }else{
        	// in case we are not currently cursed by any Malus cards
        	SaboteurMove my_one_move;
        	
        	// first of all, if we have a Map card, then we use it
        	my_one_move = MyTools.play_a_map(cards, boardState);
            if(my_one_move!=null) return my_one_move;
            
            // use the helper to get all the currently deepest x value
            int current_position_x = MyTools.deepest_position_x(all_legal_moves,hidden_int_board);
            
            // if we are deeper than 8, that means we are in a "dangerous zone" 
            // where each my_one_move should be cautious. 
            // So we play a Malus card with the highest priority if inside this dangerous zone.
            if(9<=current_position_x){
                my_one_move = MyTools.play_a_malus(cards,boardState);
                if(my_one_move!=null) return my_one_move;
            }
            
            // in case we overrun, we try to put our level back to track
            if(12<current_position_x) current_position_x = 12;
            
            
            // get y position (since x is always 12) of the nugget if we know any:)
            int nugget_position = MyTools.nugget_position_y(board);
            
            // x=11 is the most critical region where we should place a tile that can 
            // directly link to the nugget or the middle hidden objective 
            if(11==current_position_x){
                my_one_move = MyTools.play_a_tile_line_11(current_position_x,all_legal_moves,hidden_int_board,nugget_position);
                if(my_one_move!=null) return my_one_move;
            }
            // if we are already in line of the nugget, then we play a tile that can connect
            // hidden objectives horizontally
            else if(12==current_position_x){
                my_one_move = MyTools.play_a_tile_line_12(current_position_x,all_legal_moves,hidden_int_board,nugget_position);
                if(my_one_move!=null) return my_one_move;
            }
            // if we are not in those two dangerous lines
            else{
                my_one_move = MyTools.play_a_tile(current_position_x, all_legal_moves, hidden_int_board);
                if(my_one_move!=null) return my_one_move;
            }
            
            // if we do not have the aforementioned cards, we decide to get rid of a useless card.
            my_one_move = MyTools.delete_useless_card(cards, boardState);
   
            if(my_one_move != null) return my_one_move;
            else return boardState.getRandomMove(); // if I have no cards in hand, then return a random move to avoid Exceptions.
        }
    }
}
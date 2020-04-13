package student_player;

import Saboteur.SaboteurBoardState;
import Saboteur.SaboteurMove;
import Saboteur.cardClasses.*;

import java.util.ArrayList;
import java.util.Arrays;

public class MyTools {
    
	//////////////////////////////
	/// PLAY A BONUS ////////////
	/////////////////////////////
    // play a Bonus card if instructed
    public static SaboteurMove play_a_bonus(ArrayList<SaboteurCard> current_cards, SaboteurBoardState boardState){
        for(SaboteurCard c:current_cards){
            if(c.getName().equals("Bonus")) return new SaboteurMove(c, 0, 0, boardState.getTurnPlayer());
        }
        
        return null;
    }
    
	//////////////////////////////
	/// PLAY A MALUS /////////////
	/////////////////////////////
    // play a Malus card if instructed
    public static SaboteurMove play_a_malus(ArrayList<SaboteurCard> current_cards,SaboteurBoardState boardState){
        for(SaboteurCard c:current_cards){
            if(c.getName().equals("Malus")) return new SaboteurMove(c, 0, 0, boardState.getTurnPlayer());
        }
        
        return null;
    }
    
	    
	//////////////////////////////
	/// PLAY A MAP ///////////////
	/////////////////////////////
    // play a Map card if certain hidden objectives are not yet revealed.
    public static SaboteurMove play_a_map(ArrayList<SaboteurCard> current_cards,SaboteurBoardState boardState){
        SaboteurTile[][] current_board = boardState.getHiddenBoard();
    	for(SaboteurCard c:current_cards){
            if(c.getName().equals("Map")){
                if(current_board[12][3].getIdx().equals("8")) return new SaboteurMove(c,12,3,boardState.getTurnPlayer());
                if(current_board[12][5].getIdx().equals("8")) return new SaboteurMove(c,12,5,boardState.getTurnPlayer());
                if(current_board[12][7].getIdx().equals("8")) return new SaboteurMove(c,12,7,boardState.getTurnPlayer());
            }
        }
        return null;
    }
    

	//////////////////////////////
	/// NUGGET POSITION Y ////////
	/////////////////////////////
    public static int nugget_position_y(SaboteurTile[][] current_board){
        if(current_board[12][3].getIdx().equals("nugget")) return 3;
        if(current_board[12][5].getIdx().equals("nugget")) return 5;
        if(current_board[12][7].getIdx().equals("nugget")) return 7;
        return 0;
    }
    
	
	//////////////////////////////
	/// PLAY A TILE /////////////
	/////////////////////////////
	// play a tile according to current positions and tile types
    public static SaboteurMove play_a_tile(int current_position_x, ArrayList<SaboteurMove> moves, int[][] int_board){
        // use good_tile_moves to store the currently valid and good moves.
    	ArrayList<SaboteurMove> good_tile_moves = new ArrayList<SaboteurMove>();
        /////////////////////
    	// keep tiles only //
    	/////////////////////
        for(SaboteurMove my_one_move:moves){
            String name = my_one_move.getCardPlayed().getName().split(":")[0];
            if(name.equals("Tile") && (my_one_move.getPosPlayed()[0]==current_position_x && can_reach_origin(my_one_move,real_complete_copy(int_board)))) 
            	good_tile_moves.add(my_one_move);
        }

        // We play a tile according to its level of goodness from the best to the worst
        // Tile 8
        for(SaboteurMove my_one_move:good_tile_moves){
            String name = my_one_move.getCardPlayed().getName().split(":")[1];
            if(name.equals("8")) return my_one_move;
        }
        
        // Tile 6, Tile 9 and their flips
        for(SaboteurMove my_one_move:good_tile_moves){
            String name = my_one_move.getCardPlayed().getName().split(":")[1];
            if(name.equals("6")||name.equals("6_flip")||name.equals("9")||name.equals("9_flip")) return my_one_move;
        }
        
        // Tile 0 and Tile 10
        for(SaboteurMove my_one_move:good_tile_moves){
            String name = my_one_move.getCardPlayed().getName().split(":")[1];
            if(name.equals("0")||name.equals("10")) return my_one_move;
        }
        
        // Tile 5, Tile 7 and their flips
        for(SaboteurMove my_one_move:good_tile_moves){
            String name = my_one_move.getCardPlayed().getName().split(":")[1];
            if(name.equals("5")||name.equals("5_flip")||name.equals("7")||name.equals("7_flip")) return my_one_move;
        }
        
        return null;
    
    }
    
    
	//////////////////////////////
	/// PLAY A TILE LINE 12 //////
	////////////////////////////
    // This is a special case of playing a tile when the value of x position = 12
    // i.e. we are in the line of the nugget and hidden objectives
    public static SaboteurMove play_a_tile_line_12(int current_position_x, ArrayList<SaboteurMove> moves, int[][] int_board, int nugget_position_x){
    	
    	// very similar to what we have done for the case of x = 11
        ArrayList<SaboteurMove> good_tile_moves = new ArrayList<SaboteurMove>();
        for(SaboteurMove my_one_move:moves){
            String name = my_one_move.getCardPlayed().getName().split(":")[0];
            if(name.equals("Tile") && (my_one_move.getPosPlayed()[0]==current_position_x && can_reach_origin(my_one_move, real_complete_copy(int_board)))) good_tile_moves.add(my_one_move);
        }
        
        // if we have used map
        if(0!=nugget_position_x) {
        	
        	// Tile 8
            for (SaboteurMove my_one_move:good_tile_moves) {
                String name = my_one_move.getCardPlayed().getName().split(":")[1];
                if ((my_one_move.getPosPlayed()[1]==(nugget_position_x-1)||my_one_move.getPosPlayed()[1]==(nugget_position_x+1)) && name.equals("8")) return my_one_move;
            }
            
            // Tile 9 and its flip
            for (SaboteurMove my_one_move:good_tile_moves) {
                String name = my_one_move.getCardPlayed().getName().split(":")[1];
                if ((my_one_move.getPosPlayed()[1]==(nugget_position_x-1)||my_one_move.getPosPlayed()[1]==(nugget_position_x+1)) && (name.equals("9")||name.equals("9_flip"))) return my_one_move;
            }
            
            // Tile 6's flip and Tile 7
            for (SaboteurMove my_one_move:good_tile_moves) {
                String name = my_one_move.getCardPlayed().getName().split(":")[1];
                if ((my_one_move.getPosPlayed()[1]==(nugget_position_x-1)) && (name.equals("7")||name.equals("6_flip"))) return my_one_move;
            }
            
            // Tile 6 and 5's flip
            for (SaboteurMove my_one_move:good_tile_moves) {
                String name = my_one_move.getCardPlayed().getName().split(":")[1];
                if ((my_one_move.getPosPlayed()[1]==(nugget_position_x+1)) && (name.equals("5_flip")||name.equals("6"))) return my_one_move;
            }
            
            // Tile 10
            for (SaboteurMove my_one_move:good_tile_moves) {
                String name = my_one_move.getCardPlayed().getName().split(":")[1];
                if ((my_one_move.getPosPlayed()[1]==(nugget_position_x-1)||my_one_move.getPosPlayed()[1]==(nugget_position_x+1)) && name.equals("10")) return my_one_move;
            }
            
        }
        
        // nugget not yet revealed. Following the same order as before
        // Tile 8
        for(SaboteurMove my_one_move:good_tile_moves){
            String name = my_one_move.getCardPlayed().getName().split(":")[1];
            if ((my_one_move.getPosPlayed()[1]==4||my_one_move.getPosPlayed()[1]==6) && name.equals("8")) return my_one_move;
        }
        
        // Tile 9 and its flip
        for(SaboteurMove my_one_move:good_tile_moves){
            String name = my_one_move.getCardPlayed().getName().split(":")[1];
            if ((my_one_move.getPosPlayed()[1]==4||my_one_move.getPosPlayed()[1]==6) && (name.equals("9")||name.equals("9_flip"))) return my_one_move;
        }
        
        // Tile 10
        for(SaboteurMove my_one_move:good_tile_moves){
            String name = my_one_move.getCardPlayed().getName().split(":")[1];
            if ((my_one_move.getPosPlayed()[1]==4||my_one_move.getPosPlayed()[1]==6) && name.equals("10")) return my_one_move;
        }
        // Tile 8
        for(SaboteurMove my_one_move:good_tile_moves){
            String name[] = my_one_move.getCardPlayed().getName().split(":");
            if(name[1].equals("8")){
                return my_one_move;
            }
        }
        
        // Tile 9 and its flip
        for(SaboteurMove my_one_move:good_tile_moves){
            String name = my_one_move.getCardPlayed().getName().split(":")[1];
            if(name.equals("9_flip")||name.equals("9")) return my_one_move;
        }
        
        // Tile 10
        for(SaboteurMove my_one_move:good_tile_moves){
            String name = my_one_move.getCardPlayed().getName().split(":")[1];
            if(name.equals("10")) return my_one_move;
        }
        
        
        return null;
    }

    
	//////////////////////////////
	/// PLAY A TILE LINE 11 //////
	/////////////////////////////
    // This is a special case of playing a tile when the value of x position = 11
    // i.e. we are reaching the last line right before the nugget and hidden objectives
    public static SaboteurMove play_a_tile_line_11(int current_position_x, ArrayList<SaboteurMove> moves, int[][] int_board, int nugget_position_x){
        // we are to store valid moves in good_tile_moves
    	ArrayList<SaboteurMove> good_tile_moves = new ArrayList<SaboteurMove>();
        
    	// search for all good tile-moves that are in the right places and can reach the origin.
        for(SaboteurMove my_one_move:moves){
            String name[] = my_one_move.getCardPlayed().getName().split(":");
            if(name[0].equals("Tile") && (my_one_move.getPosPlayed()[0]==current_position_x && can_reach_origin(my_one_move,real_complete_copy(int_board))))
                good_tile_moves.add(my_one_move);
        }

        // we still follow the goodness decreasing rule :)
        if(0!=nugget_position_x) {
        	
        	// Tile 8
            for (SaboteurMove my_one_move:good_tile_moves) {
                String name = my_one_move.getCardPlayed().getName().split(":")[1];
                if(my_one_move.getPosPlayed()[1]==nugget_position_x && name.equals("8")) return my_one_move;
            }
            
            // Tile 6, 9 and 6's flip
            for (SaboteurMove my_one_move:good_tile_moves) {
                String name = my_one_move.getCardPlayed().getName().split(":")[1];
                if(my_one_move.getPosPlayed()[1]==nugget_position_x && (name.equals("6")||name.equals("6_flip")||name.equals("9"))) return my_one_move;
            }
            
            // Tile 0
            for (SaboteurMove my_one_move:good_tile_moves) {
                String name = my_one_move.getCardPlayed().getName().split(":")[1];
                if (my_one_move.getPosPlayed()[1]==nugget_position_x && name.equals("0")) return my_one_move;
            }
            
            // Tile 5 and 7's flip
            for (SaboteurMove my_one_move:good_tile_moves) {
                String name = my_one_move.getCardPlayed().getName().split(":")[1];
                if (my_one_move.getPosPlayed()[1]==nugget_position_x && (name.equals("5") || name.equals("7_flip"))) return my_one_move;
            }
            
        }
        
        
        // in case we are right above the nugget or two hidden objectives, we play specific tiles
        // Tile 8 
        for(SaboteurMove my_one_move:good_tile_moves) {
            String name = my_one_move.getCardPlayed().getName().split(":")[1];
            if ((my_one_move.getPosPlayed()[1]==3||my_one_move.getPosPlayed()[1]==5||my_one_move.getPosPlayed()[1]==7) && name.equals("8")) return my_one_move;
        }
        
        // Tile 9, 6 and its flip
        for(SaboteurMove my_one_move:good_tile_moves){
            String name = my_one_move.getCardPlayed().getName().split(":")[1];
            if ((name.equals("9")||name.equals("6")||name.equals("6_flip")) && (my_one_move.getPosPlayed()[1]==3||my_one_move.getPosPlayed()[1]==5||my_one_move.getPosPlayed()[1]==7))
                return my_one_move;
        }
        
        
        // Tile 0
        for(SaboteurMove my_one_move:good_tile_moves){
            String name = my_one_move.getCardPlayed().getName().split(":")[1];
            if ((my_one_move.getPosPlayed()[1]==3||my_one_move.getPosPlayed()[1]==5||my_one_move.getPosPlayed()[1]==7) && name.equals("0"))
                return my_one_move;
        }
        
        // Tile 5 and Tile 7's flip
        for(SaboteurMove my_one_move:good_tile_moves){
            String name = my_one_move.getCardPlayed().getName().split(":")[1];
            if ((my_one_move.getPosPlayed()[1]==3||my_one_move.getPosPlayed()[1]==5||my_one_move.getPosPlayed()[1]==7) && (name.equals("5")||name.equals("7_flip")))
                return my_one_move;
        }
        
        
        // Tile 8
        for(SaboteurMove my_one_move:good_tile_moves) {
            String name = my_one_move.getCardPlayed().getName().split(":")[1];
            if (name.equals("8")) return my_one_move;
        }
        
        // Tile 6 and its flip and Tile 9
        for(SaboteurMove my_one_move:good_tile_moves){
            String name = my_one_move.getCardPlayed().getName().split(":")[1];
            if(name.equals("6")||name.equals("6_flip")||name.equals("9") ) return my_one_move;
        }
        
        // Tile 0
        for(SaboteurMove my_one_move:good_tile_moves){
            String name = my_one_move.getCardPlayed().getName().split(":")[1];
            if(name.equals("0")) return my_one_move;
        }
        
        // Tile 5 and Tile 7's flip again
        for(SaboteurMove my_one_move : good_tile_moves){
            String name = my_one_move.getCardPlayed().getName().split(":")[1];
            if(name.equals("5")||name.equals("7_flip")) return my_one_move;
        }
        
        
        return null;
    }
    
    
    

    
	//////////////////////////////
	/// REAL COMPLETE COPY ///////
	/////////////////////////////
    // function to solve the "shadow copy" problem of "clone". We implement this to perform a deep-copy
    public static int[][] real_complete_copy(int[][] input) {
        if(input == null) return input;
 
        int[][] to_output = new int[input.length][];
        for(int i=0;i<input.length;i++) to_output[i] = Arrays.copyOf(input[i],input[i].length);
        
        return to_output;
    }

    
    
	/////////////////////////////
	/// DEEPEST POSITION X //////
	/////////////////////////////
    // given an arraylist of moves, return the deepest x val that the valid moves reach in a board
    public static int deepest_position_x(ArrayList<SaboteurMove> moves, int[][] int_board){
    	// init a dummy
        int deepest = -10;
        
        // loop to find the deepest
        for(SaboteurMove my_one_move:moves){
            String name = my_one_move.getCardPlayed().getName().split(":")[0];
            // need to also make sure that the tiles are "good" and valid
            if(name.equals("Tile") && (can_reach_origin(my_one_move, real_complete_copy(int_board)))) 
                if (my_one_move.getPosPlayed()[0] > deepest) deepest = my_one_move.getPosPlayed()[0];
        }
        
        return deepest;
    }

    
	//////////////////////////////
	/// DELETE USELESS CARD /////
	/////////////////////////////
    // get rid of one useless card
    public static SaboteurMove delete_useless_card(ArrayList<SaboteurCard> current_cards,SaboteurBoardState boardState) {
    	// The more USELESS a card, the most likely it is got rid of to begin with.
    	
    	// Tile 13
    	for(int i=0;i<current_cards.size();i++) {
            String cardName[] = current_cards.get(i).getName().split(":");
            if(cardName[0].equals("Tile") && cardName[1].equals("13"))  return new SaboteurMove(new SaboteurDrop(),i,0,boardState.getTurnPlayer());
        }
        
    	// Tile 2 and Tile 13
        for(int i=0;i<current_cards.size();i++) {
            String cardName[] = current_cards.get(i).getName().split(":");
            if (cardName[0].equals("Tile") && (cardName[1].equals("2")||cardName[1].equals("11"))) return new SaboteurMove(new SaboteurDrop(), i, 0, boardState.getTurnPlayer());
        }
        
        // Tile 1, 3, 14 and 15
        for(int i=0;i<current_cards.size();i++) {
            String cardName[] = current_cards.get(i).getName().split(":");
            if (cardName[0].equals("Tile") && (cardName[1].equals("3") || cardName[1].equals("1") || cardName[1].equals("14") || cardName[1].equals("15"))) 
            	return new SaboteurMove(new SaboteurDrop(), i,0,boardState.getTurnPlayer());
        }
        
        
        // Tile 4 and Tile 12
        for(int i=0;i<current_cards.size();i++) {
            String cardName[] = current_cards.get(i).getName().split(":");
            if (cardName[0].equals("Tile") && (cardName[1].equals("4") || cardName[1].equals("12"))) return new SaboteurMove(new SaboteurDrop(), i, 0, boardState.getTurnPlayer());
        }
        
        // Card Destroy
        for(int i=0;i<current_cards.size();i++) {
            String cardName = current_cards.get(i).getName();
            if (cardName.contains("Destroy")) return new SaboteurMove(new SaboteurDrop(), i,0,boardState.getTurnPlayer());
        }
        
        // Tile 5 and Tile 7
        for(int i=0;i<current_cards.size();i++) {
            String cardName[] = current_cards.get(i).getName().split(":");
            if (cardName[0].equals("Tile") && (cardName[1].equals("5") || cardName[1].equals("7"))) return new SaboteurMove(new SaboteurDrop(), i, 0, boardState.getTurnPlayer());
        }
        
        // Tile 0 and Tile 10
        for(int i=0;i<current_cards.size();i++) {
            String cardName[] = current_cards.get(i).getName().split(":");
            if (cardName[0].equals("Tile") && (cardName[1].equals("0") || cardName[1].equals("10"))) return new SaboteurMove(new SaboteurDrop(), i, 0, boardState.getTurnPlayer());
        }
        
        // Dummy move
        for(int i=0;i<current_cards.size();i++) {
            String cardName[] = current_cards.get(i).getName().split(":");
            if (cardName[0].equals("Tile") && (cardName[1].equals("9") || cardName[1].equals("6"))) return new SaboteurMove(new SaboteurDrop(), i, 0, boardState.getTurnPlayer());
        }
        
        return null;
    }
    
    
    
	//////////////////////////////
	/// SEARCH FOR ORIGIN ///////
	/////////////////////////////
    // helper function of the helper function CAN REACH ORIGIN
    // recursion
    public static int search_for_origin(ArrayList<int[]> past_moves,int[] positions,int[][] int_board){
        boolean can_go_leftwards = true;
        boolean can_go_rightwards = true;
    	boolean can_go_upwards = true;
        boolean can_go_downwards = true;

        if(positions[0]==16&&positions[1]==16) return 1;

        for(int[] past_move_pos_x : past_moves){
            if(past_move_pos_x[0]==(positions[0]-1) && past_move_pos_x[1]==positions[1]) can_go_upwards = false;
            if(past_move_pos_x[0]==(positions[0]+1) && past_move_pos_x[1]==positions[1]) can_go_downwards = false;
            if(past_move_pos_x[0]==positions[0] && past_move_pos_x[1]==(positions[1]-1)) can_go_leftwards = false;
            if(past_move_pos_x[0]==positions[0] && past_move_pos_x[1]==(positions[1]+1)) can_go_rightwards = false;           
        }
        
        int indicator = 0;
        
        // recurse left
        if(can_go_leftwards && positions[1]>0 && (1==int_board[positions[0]][positions[1]-1])) {
        	ArrayList<int[]> past_move_position_cnt = (ArrayList<int[]>) past_moves.clone();
            past_move_position_cnt.add(positions);
            indicator = search_for_origin(past_move_position_cnt,new int[]{positions[0],positions[1]-1},int_board) + indicator;
        }
        
        // recurse right
        if(can_go_rightwards && positions[1]<41 && (1==int_board[positions[0]][positions[1]+1])) {
        	ArrayList<int[]> past_move_position_cnt = (ArrayList<int[]>) past_moves.clone();
            past_move_position_cnt.add(positions);
            indicator = search_for_origin(past_move_position_cnt,new int[]{positions[0],positions[1]+1},int_board)+ indicator;
        }
        
        // recurse up
        if(can_go_upwards && positions[0]>0 && (1==int_board[positions[0]-1][positions[1]])) {
            ArrayList<int[]> past_move_position_cnt = (ArrayList<int[]>) past_moves.clone();
            past_move_position_cnt.add(positions);
            indicator =search_for_origin(past_move_position_cnt,new int[]{positions[0]-1,positions[1]},int_board)+ indicator;
        }
        
        // recurse down
        if(can_go_downwards && positions[0]<41 && (1==int_board[positions[0]+1][positions[1]])) {
        	ArrayList<int[]> past_move_position_cnt = (ArrayList<int[]>) past_moves.clone();
            past_move_position_cnt.add(positions);
            indicator = search_for_origin(past_move_position_cnt,new int[]{positions[0]+1,positions[1]},int_board)+ indicator;
        }
       
        
        return indicator;
    }
    
    
	//////////////////////////////
	/// CAN REACH ORIGIN ////////
	/////////////////////////////
    // return if the given current_move can trace back to the origin successfully
    public static boolean can_reach_origin(SaboteurMove current_move, int[][] int_board) {
        SaboteurTile current_card = (SaboteurTile) current_move.getCardPlayed();
        // dummy: update the int board using this given current_move
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++) int_board[i+3*current_move.getPosPlayed()[0]][j+3*current_move.getPosPlayed()[1]] = current_card.getPath()[j][2-i];
        }
        
        // then, start the recursions using the updated int board
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                if(1==int_board[i+3*current_move.getPosPlayed()[0]][j+3*current_move.getPosPlayed()[1]]){
                    int indicator = search_for_origin(new ArrayList<int[]>(), new int[]{i+3*current_move.getPosPlayed()[0], j+3*current_move.getPosPlayed()[1]},int_board);
                    if(indicator!=0) return true;
                    else return false;
                }
            }
        }
        
        return false;
    
    }
    
    
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /// Earlier iterations////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Check if a move uses a Tile.
    public static boolean isTile(SaboteurMove move) {
    	//System.out.println(move.getCardPlayed().getName());
    	if(move.getCardPlayed().getName().startsWith("Tile")) {return true;}
    	else {return false;}
    }
    
    // Check if a move uses a Bonus.
    public static boolean isBonus(SaboteurMove move) {
    	//System.out.println(move.getCardPlayed().getName());
    	if(move.getCardPlayed().getName().startsWith("Bonus")) {return true;}
    	else {return false;}
    }
    
    
    // Check if a move uses a Map.
    public static boolean isMap(SaboteurMove move) {
    	if(move.getCardPlayed().getName().startsWith("Map")) {return true;}
    	else {return false;}
    }
    
    // Check is a move uses a Malus.
    public static boolean isMalus(SaboteurMove move) {
    	//System.out.println(move.getCardPlayed().getName());
    	if(move.getCardPlayed().getName().startsWith("Malus")) {return true;}
    	else {return false;}
    }
    
    // Check is a move uses a Destroy.
    public static boolean isDestroy(SaboteurMove move) {
    	//System.out.println(move.getCardPlayed().getName());
    	if(move.getCardPlayed().getName().startsWith("Destroy")) {return true;}
    	else {return false;}
    }
    
    
    // Return all SaboteurMoves that are Destroy given an array-list of SaboteurMoves.
    public static ArrayList<SaboteurMove> destroyMoves(ArrayList<SaboteurMove> moves){
    	ArrayList<SaboteurMove> destroys = new ArrayList<>();
    	for(SaboteurMove move: moves) {
    		if(isDestroy(move)) {
    			destroys.add(move);
    		}
    	}
    	return destroys;
    }
    
    
    
    // Return the argmin of a list of doubles
    public static int argMin(double[] array){

        // add this
        if (array.length == 0)
            return -1;

        int index = 0;
        double min = array[index];

        for (int i = 1; i < array.length; i++){
            if (array[i] <= min){
            min = array[i];
            index = i;
            }
        }
        return index;
    }
    
    // Return the absolute distance between a Move's position and the goal's position
    public static double absDistance(SaboteurMove move, int[] goal) {
    	int[] pos = move.getPosPlayed();
    	return(Math.abs(goal[0] - pos[0]) + Math.abs(goal[1] - pos[1]));
    }
    
    // Return the Move with the smallest absolute distance towards the goal's position
    public static SaboteurMove moveWithSmallestAbsDistance(ArrayList<SaboteurMove> moves, int[] goal) {
    	if(moves.size()==0) {
    		return null;
    	}
    	double[] distances = new double[moves.size()];
    	for(int i = 0; i < moves.size(); i++) {
    		distances[i] = absDistance(moves.get(i), goal);
    	}
    	return moves.get(argMin(distances));
    }
    
    // Check if a SaboteurMove is considered as an obstacle against my opponent. Input should ideally be Tile.
    // Tile 5 - 10 are considered as non-obstacles, i.e., good tunnels; Tile 1-4, 11-15 are considered obstacles that can be used to impede the opponent.
    // e.g. Name of a card:"Tile:10"
    public static boolean isObstacle(SaboteurMove move) {
    	String moveName = move.getCardPlayed().getName();
    	if(moveName.charAt(5) == '5' || moveName.charAt(5)=='6' || moveName.charAt(5) == '7' || moveName.charAt(5) == '8' || moveName.charAt(5)=='9' || moveName.contains("10")) {
    		return false;
    	}
    	return true;
    }
    
    // Return all SaboteurMoves that are obstacles given an array-list of SaboteurMoves of Tiles.
    public static ArrayList<SaboteurMove> obstacleMoves(ArrayList<SaboteurMove> tileMoves){
    	ArrayList<SaboteurMove> obstacles = new ArrayList<>();
    	for(SaboteurMove move: tileMoves) {
    		if(isObstacle(move) && move.getPosPlayed()[0] <= 13 && move.getPosPlayed()[0]>=6 ) {
    			obstacles.add(move);
    		}
    	}
    	return obstacles;
    }
    
    // Return all SaboteurMoves that are NON obstacles given an array-list of SaboteurMoves of Tiles.
    // i.e. they are useful for ourselves.
    public static ArrayList<SaboteurMove> nonObstacleMoves(ArrayList<SaboteurMove> tileMoves, int[][] intBoard){
    	ArrayList<SaboteurMove> nonObstacles = new ArrayList<>();
    	for(SaboteurMove move: tileMoves) {
    		if((!isObstacle(move)) && move.getPosPlayed()[0] <= 13 && move.getPosPlayed()[0]>=6 && can_reach_origin(move,intBoard)) {
    			nonObstacles.add(move);
    		}
    	}
    	return nonObstacles;
    }
    
    // Return Move w/ the largest x position.
    public static SaboteurMove lowestMove(ArrayList<SaboteurMove> tileMoves) {
    	SaboteurMove lowest = tileMoves.get(0);
    	for(SaboteurMove move : tileMoves) {
    		if(move.getPosPlayed()[0] > lowest.getPosPlayed()[0]) {
    			lowest = move;
    		}
    	}
    	return lowest;
    }
    
    // Return Move w/ the smallest x position.
    public static SaboteurMove highestMove(ArrayList<SaboteurMove> tileMoves) {
    	SaboteurMove highest = tileMoves.get(0);
    	for(SaboteurMove move : tileMoves) {
    		if(move.getPosPlayed()[0] > highest.getPosPlayed()[0]) {
    			highest = move;
    		}
    	}
    	return highest;
    }
    
    // Return Move w/ the smallest y position.
    public static SaboteurMove leftestMove(ArrayList<SaboteurMove> tileMoves) {
    	SaboteurMove leftest = tileMoves.get(0);
    	for(SaboteurMove move : tileMoves) {
    		if(move.getPosPlayed()[1] > leftest.getPosPlayed()[1]) {
    			leftest = move;
    		}
    	}
    	return leftest;
    }
    
    // Return Move w/ the largest y position.
    public static SaboteurMove rightestMove(ArrayList<SaboteurMove> tileMoves) {
    	SaboteurMove rightest = tileMoves.get(0);
    	for(SaboteurMove move : tileMoves) {
    		if(move.getPosPlayed()[1] > rightest.getPosPlayed()[1]) {
    			rightest = move;
    		}
    	}
    	return rightest;
    }
    
    // Return Move w/ the x position closest to the MIDDLE line.
    public static SaboteurMove middlestMove(ArrayList<SaboteurMove> tileMoves) {
    	SaboteurMove middlest = tileMoves.get(0);
    	for(SaboteurMove move : tileMoves) {
    		if(Math.abs(move.getPosPlayed()[0] - 7) > Math.abs(middlest.getPosPlayed()[0] - 7)) {
    			middlest = move;
    		}
    	}
    	return middlest;
    }
    
    // Decide whether a Tile move is safe.
    // Obviously dangerous moves include [10,3] [10,5] [10,7]  [11,4] [11,6] 
    public static boolean isSafe(SaboteurMove move) {
    	// not even a Tile. Always safe
    	if(!move.getCardPlayed().getName().contains("Tile")) {return true;}
    	int x = move.getPosPlayed()[0];
    	int y = move.getPosPlayed()[1];
    	if(x==10) {
    		if(y == 3 || y == 5 || y == 7) {return false;}
    	}else if(x==11) {
    		if(y == 4 || y == 6) {return false;}
    	}
    	return true;
    }
    
    // For line x = 10 - 12 only
    // Return good tile moves in the critical regions
    public static ArrayList<SaboteurMove> selectedGoodMoves(ArrayList<SaboteurMove> tileMoves){
    	ArrayList<SaboteurMove> goodMoves = new ArrayList<>();
    	for(SaboteurMove move: tileMoves) {
    		if(move.getPosPlayed()[0]==10) {
    			if(move.getPosPlayed()[1]==1 || move.getPosPlayed()[1]==2 || move.getPosPlayed()[1]==4 || move.getPosPlayed()[1]==6 || move.getPosPlayed()[1]==8 || move.getPosPlayed()[1]==9) {
    				goodMoves.add(move);
    			}
    		}
    		if(move.getPosPlayed()[0]==11) {
    			if(move.getPosPlayed()[1]==1 || move.getPosPlayed()[1]==3 || move.getPosPlayed()[1]==5 || move.getPosPlayed()[1]==7 || move.getPosPlayed()[1]==9) {
    				goodMoves.add(move);
    			}
    		}
    		if(move.getPosPlayed()[0]==12) {
    			if(move.getPosPlayed()[1]==2 || move.getPosPlayed()[1]==4 || move.getPosPlayed()[1]==6 || move.getPosPlayed()[1]==8) {
    				goodMoves.add(move);
    			}
    		}
    	}
    	return goodMoves;
    }
    
    
    // Return obstacle moves in the critical region
    public static ArrayList<SaboteurMove> selectedObstacleMoves(ArrayList<SaboteurMove> tileMoves){
    	ArrayList<SaboteurMove> obstacleMoves = new ArrayList<>();
    	for(SaboteurMove move: tileMoves) {
    		if(move.getPosPlayed()[0]==10) {
    			if(move.getPosPlayed()[1]==3 || move.getPosPlayed()[1]==5 || move.getPosPlayed()[1]==7) {
    				obstacleMoves.add(move);
    			}
    		}
    		if(move.getPosPlayed()[0]==11) {
    			if(move.getPosPlayed()[1]==2 || move.getPosPlayed()[1]==4 || move.getPosPlayed()[1]==6 || move.getPosPlayed()[1]==8) {
    				obstacleMoves.add(move);
    			}
    		}
    		if(move.getPosPlayed()[0]==12) {
    			if(move.getPosPlayed()[1]==1 || move.getPosPlayed()[1]==9) {
    				obstacleMoves.add(move);
    			}
    		}
    	}
    	return obstacleMoves;
    }
    
}
package student_player;

import Saboteur.SaboteurBoardState;
import Saboteur.SaboteurMove;
import Saboteur.cardClasses.*;

import java.util.ArrayList;
import java.util.Arrays;

public class DummyMyTools {
    public static SaboteurMove useTail(int depth, ArrayList<SaboteurMove> moves, int[][] intBoard){
        ArrayList<SaboteurMove> effectiveMoves = new ArrayList<SaboteurMove>();
        for(SaboteurMove m : moves){
            SaboteurCard c = m.getCardPlayed();
            String name[] = c.getName().split(":");
            if(name[0].equals("Tile")) {
                if (m.getPosPlayed()[0] == depth && getEffective(m, deepCopy(intBoard))) {
                    effectiveMoves.add(m);
                }
            }
        }
        System.out.println("EFFECTIVENUMBER:" + effectiveMoves.size());
        for(SaboteurMove m : effectiveMoves){
            System.out.println("Selected Moves" + m.toPrettyString());
        }
        for(SaboteurMove m : effectiveMoves){
            SaboteurCard c = m.getCardPlayed();
            String name[] = c.getName().split(":");
            if(name[1].equals("8")){
                return m;
            }
        }
        for(SaboteurMove m : effectiveMoves){
            SaboteurCard c = m.getCardPlayed();
            String name[] = c.getName().split(":");
            if(name[1].equals("9") || name[1].equals("6") || name[1].equals("9_flip") || name[1].equals("6_flip")){
                return m;
            }
        }
        for(SaboteurMove m : effectiveMoves){
            SaboteurCard c = m.getCardPlayed();
            String name[] = c.getName().split(":");
            if(name[1].equals("0") || name[1].equals("10")){
                return m;
            }
        }
        for(SaboteurMove m : effectiveMoves){
            SaboteurCard c = m.getCardPlayed();
            String name[] = c.getName().split(":");
            if(name[1].equals("5") || name[1].equals("7") || name[1].equals("5_flip") || name[1].equals("7_flip")){
                return m;
            }
        }
        return null;
    }
    public static SaboteurMove useTail11(int depth, ArrayList<SaboteurMove> moves, int[][] intBoard){
        ArrayList<SaboteurMove> effectiveMoves = new ArrayList<SaboteurMove>();
        for(SaboteurMove m : moves){
            SaboteurCard c = m.getCardPlayed();
            String name[] = c.getName().split(":");
            if(name[0].equals("Tile")) {
                if (m.getPosPlayed()[0] == depth && getEffective(m, deepCopy(intBoard))) {
                    effectiveMoves.add(m);
                }
            }
        }
        System.out.println("EFFECTIVENUMBER:" + effectiveMoves.size());
        for(SaboteurMove m : effectiveMoves){
            System.out.println("Selected Moves" + m.toPrettyString());
        }
        for(SaboteurMove m : effectiveMoves) {
            SaboteurCard c = m.getCardPlayed();
            String name[] = c.getName().split(":");
            if (m.getPosPlayed()[1] == 3 || m.getPosPlayed()[1] == 5 || m.getPosPlayed()[1] == 7) {
                if (name[1].equals("8")) {
                    return m;
                }
            }
        }
        for(SaboteurMove m : effectiveMoves){
            SaboteurCard c = m.getCardPlayed();
            String name[] = c.getName().split(":");
            if (m.getPosPlayed()[1] == 3 || m.getPosPlayed()[1] == 5 || m.getPosPlayed()[1] == 7) {
                if (name[1].equals("9") || name[1].equals("6") || name[1].equals("6_flip")) {
                    return m;
                }
            }
        }
        for(SaboteurMove m : effectiveMoves){
            SaboteurCard c = m.getCardPlayed();
            String name[] = c.getName().split(":");
            if (m.getPosPlayed()[1] == 3 || m.getPosPlayed()[1] == 5 || m.getPosPlayed()[1] == 7) {
                if (name[1].equals("0")) {
                    return m;
                }
            }
        }
        for(SaboteurMove m : effectiveMoves){
            SaboteurCard c = m.getCardPlayed();
            String name[] = c.getName().split(":");
            if (m.getPosPlayed()[1] == 3 || m.getPosPlayed()[1] == 5 || m.getPosPlayed()[1] == 7) {
                if (name[1].equals("5") || name[1].equals("7_flip")) {
                    return m;
                }
            }
        }
        for(SaboteurMove m : effectiveMoves) {
            SaboteurCard c = m.getCardPlayed();
            String name[] = c.getName().split(":");
                if (name[1].equals("8")) {
                    return m;
                }
        }
        for(SaboteurMove m : effectiveMoves){
            SaboteurCard c = m.getCardPlayed();
            String name[] = c.getName().split(":");
            if(name[1].equals("9") || name[1].equals("6") || name[1].equals("6_flip")){
                return m;
            }
        }
        for(SaboteurMove m : effectiveMoves){
            SaboteurCard c = m.getCardPlayed();
            String name[] = c.getName().split(":");
            if(name[1].equals("0")){
                return m;
            }
        }
        for(SaboteurMove m : effectiveMoves){
            SaboteurCard c = m.getCardPlayed();
            String name[] = c.getName().split(":");
            if(name[1].equals("5") || name[1].equals("7_flip")){
                return m;
            }
        }
        return null;
    }
    public static SaboteurMove useTail12(int depth, ArrayList<SaboteurMove> moves, int[][] intBoard){
        ArrayList<SaboteurMove> effectiveMoves = new ArrayList<SaboteurMove>();
        for(SaboteurMove m : moves){
            SaboteurCard c = m.getCardPlayed();
            String name[] = c.getName().split(":");
            if(name[0].equals("Tile")) {
                if (m.getPosPlayed()[0] == depth && getEffective(m, deepCopy(intBoard))) {
                    effectiveMoves.add(m);
                }
            }
        }
        System.out.println("EFFECTIVENUMBER:" + effectiveMoves.size());
        for(SaboteurMove m : effectiveMoves){
            System.out.println("Selected Moves" + m.toPrettyString());
        }
        for(SaboteurMove m : effectiveMoves){
            SaboteurCard c = m.getCardPlayed();
            String name[] = c.getName().split(":");
            if (m.getPosPlayed()[1] == 4 || m.getPosPlayed()[1] == 6) {
                if (name[1].equals("8")) {
                    return m;
                }
            }
        }
        for(SaboteurMove m : effectiveMoves){
            SaboteurCard c = m.getCardPlayed();
            String name[] = c.getName().split(":");
            if (m.getPosPlayed()[1] == 4 || m.getPosPlayed()[1] == 6) {
                if (name[1].equals("9") || name[1].equals("9_flip")) {
                    return m;
                }
            }
        }
        for(SaboteurMove m : effectiveMoves){
            SaboteurCard c = m.getCardPlayed();
            String name[] = c.getName().split(":");
            if (m.getPosPlayed()[1] == 4 || m.getPosPlayed()[1] == 6) {
                if (name[1].equals("10")) {
                    return m;
                }
            }
        }
        for(SaboteurMove m : effectiveMoves){
            SaboteurCard c = m.getCardPlayed();
            String name[] = c.getName().split(":");
            if(name[1].equals("8")){
                return m;
            }
        }
        for(SaboteurMove m : effectiveMoves){
            SaboteurCard c = m.getCardPlayed();
            String name[] = c.getName().split(":");
            if(name[1].equals("9") || name[1].equals("9_flip")){
                return m;
            }
        }
        for(SaboteurMove m : effectiveMoves){
            SaboteurCard c = m.getCardPlayed();
            String name[] = c.getName().split(":");
            if(name[1].equals("10")){
                return m;
            }
        }
        return null;
    }
//    public static int boardDepth(SaboteurTile[][] board){
//        int temp = 0;
//        for(int i = 0; i < 14; i++){
//
//        }
//    }
    public static int findMaxDepth(ArrayList<SaboteurMove> moves, int[][] intBoard){
//        for (int i = 0; i < 14 * 3; i++) {
//            for (int j = 0; j < 14 * 3; j++) {
//                System.out.print(intBoard[i][j]);
//            }
//            System.out.println();
//        }
        int maxDepth = -10;
        for(SaboteurMove m : moves){
            SaboteurCard c = m.getCardPlayed();
            String name[] = c.getName().split(":");
            if(name[0].equals("Tile")) {
                if (getEffective(m, deepCopy(intBoard))) {
                    int[] po = m.getPosPlayed();
                    if (po[0] > maxDepth) {
                        System.out.println("MAXDEPTH UPDATE: " + m.toPrettyString());
//                        for (int i = 0; i < 14 * 3; i++) {
//                            for (int j = 0; j < 14 * 3; j++) {
//                                System.out.print(intBoard[i][j]);
//                            }
//                            System.out.println();
//                        }
                        maxDepth = po[0];
                    }
                }
            }
        }
        return maxDepth;
    }
    public static SaboteurMove useMap(ArrayList<SaboteurCard> cards, SaboteurBoardState boardState){
        for(SaboteurCard i : cards){
            if(i.getName() == "Map"){
                SaboteurTile[][] board = boardState.getHiddenBoard();
                if(board[12][3].getIdx().equals("8")) {
                    return new SaboteurMove(i, 12, 3, boardState.getTurnPlayer());
                }
                if(board[12][7].getIdx().equals("8")) {
                    return new SaboteurMove(i, 12, 7, boardState.getTurnPlayer());
                }
                if(board[12][5].getIdx().equals("8")) {
                    return new SaboteurMove(i, 12, 5, boardState.getTurnPlayer());
                }
            }
        }
        return null;
    }
    public static SaboteurMove useBonus(ArrayList<SaboteurCard> cards, SaboteurBoardState boardState){
        for(SaboteurCard i : cards){
            if(i.getName().equals("Bonus")){
                return new SaboteurMove(i,0,0,boardState.getTurnPlayer());
            }
        }
        return null;
    }
    public static SaboteurMove useMalus(ArrayList<SaboteurCard> cards, SaboteurBoardState boardState){
        for(SaboteurCard i : cards){
            if(i.getName().equals("Malus")){
                return new SaboteurMove(i,0,0,boardState.getTurnPlayer());
            }
        }
        return null;
    }
    public static SaboteurMove dropTrashCard(ArrayList<SaboteurCard> cards, SaboteurBoardState boardState) {
        System.out.println("CARDNUMBER:" + cards.size());
        for (int i = 0; i < cards.size(); i++) {
            SaboteurCard c = cards.get(i);
            String cardName[] = c.getName().split(":");
            if (cardName[0].equals("Tile")) {
                if (cardName[1].equals("13")) {
                    return new SaboteurMove(new SaboteurDrop(), i,0,boardState.getTurnPlayer());
                }
            }
        }
        for (int i = 0; i < cards.size(); i++) {
            SaboteurCard c = cards.get(i);
            String cardName[] = c.getName().split(":");
            if (cardName[0].equals("Tile")) {
                if (cardName[1].equals("11") || cardName[1].equals("2")) {
                    return new SaboteurMove(new SaboteurDrop(), i,0,boardState.getTurnPlayer());
                }
            }
        }
        for (int i = 0; i < cards.size(); i++) {
            SaboteurCard c = cards.get(i);
            String cardName[] = c.getName().split(":");
            if (cardName[0].equals("Tile")) {
                if (cardName[1].equals("3") || cardName[1].equals("1") || cardName[1].equals("14") || cardName[1].equals("15")) {
                    return new SaboteurMove(new SaboteurDrop(), i,0,boardState.getTurnPlayer());
                }
            }
        }
        for (int i = 0; i < cards.size(); i++) {
            SaboteurCard c = cards.get(i);
            String cardName[] = c.getName().split(":");
            if (cardName[0].equals("Tile")) {
                if (cardName[1].equals("4") || cardName[1].equals("12")) {
                    return new SaboteurMove(new SaboteurDrop(), i,0,boardState.getTurnPlayer());
                }
            }
        }
        for (int i = 0; i < cards.size(); i++) {
            SaboteurCard c = cards.get(i);
            String cardName = c.getName();
            if (cardName.equals("Destroy")) {
                return new SaboteurMove(new SaboteurDrop(), i,0,boardState.getTurnPlayer());
            }
        }
        for (int i = 0; i < cards.size(); i++) {
            SaboteurCard c = cards.get(i);
            String cardName[] = c.getName().split(":");
            if (cardName[0].equals("Tile")) {
                if (cardName[1].equals("5") || cardName[1].equals("7")) {
                    return new SaboteurMove(new SaboteurDrop(), i,0,boardState.getTurnPlayer());
                }
            }
        }
        for (int i = 0; i < cards.size(); i++) {
            SaboteurCard c = cards.get(i);
            String cardName[] = c.getName().split(":");
            if (cardName[0].equals("Tile")) {
                if (cardName[1].equals("0") || cardName[1].equals("10")) {
                    return new SaboteurMove(new SaboteurDrop(), i,0,boardState.getTurnPlayer());
                }
            }
        }
        for (int i = 0; i < cards.size(); i++) {
            SaboteurCard c = cards.get(i);
            String cardName[] = c.getName().split(":");
            if (cardName[0].equals("Tile")) {
                if (cardName[1].equals("9") || cardName[1].equals("6")) {
                    return new SaboteurMove(new SaboteurDrop(), i,0,boardState.getTurnPlayer());
                }
            }
        }
        return null;
    }
    public static boolean getEffective(SaboteurMove sm, int[][] intBoard) {
        int[] tilePo = sm.getPosPlayed();
        SaboteurTile sc = (SaboteurTile)sm.getCardPlayed();
        int[][] tilePath = sc.getPath();
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                intBoard[3 * tilePo[0] + i][3 * tilePo[1] + j] = tilePath[j][2 - i];
            }
        }
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                if(intBoard[3 * tilePo[0] + i][3 * tilePo[1] + j] == 1){
                    int sum = recurFindStartPoint(new ArrayList<int[]>(), new int[]{3 * tilePo[0] + i, 3 * tilePo[1] + j}, intBoard);
                    if(sum != 0){
                        return true;
                    }
                    else{
                        return false;
                    }
                }
            }
        }
        return false;
    }
    public static int recurFindStartPoint(ArrayList<int[]> pastMoves, int[] po, int[][] intBoard){
        if(po[0] == 16 && po[1] == 16){
            return 1;
        }
        int sum = 0;
        boolean up = true;
        boolean down = true;
        boolean left = true;
        boolean right = true;
        for(int[] pastM : pastMoves){
            if(pastM[0] == po[0] && pastM[1] == po[1] - 1){
                left = false;
            }
            if(pastM[0] == po[0] && pastM[1] == po[1] + 1){
                right = false;
            }
            if(pastM[0] == po[0] - 1 && pastM[1] == po[1]){
                up = false;
            }
            if(pastM[0] == po[0] + 1 && pastM[1] == po[1]){
                down = false;
            }
        }
        if(left && (po[1] > 0)) {
            if (intBoard[po[0]][po[1] - 1] == 1) {
                ArrayList<int[]> pastMovesC = ((ArrayList<int[]>) pastMoves.clone());
                pastMovesC.add(po);
                sum += recurFindStartPoint(pastMovesC, new int[]{po[0], po[1] - 1}, intBoard);
            }
        }
        if(right && (po[1] < 41)) {
            if (intBoard[po[0]][po[1] + 1] == 1) {
                ArrayList<int[]> pastMovesC = ((ArrayList<int[]>) pastMoves.clone());
                pastMovesC.add(po);
                sum += recurFindStartPoint(pastMovesC, new int[]{po[0], po[1] + 1}, intBoard);
            }
        }
        if(up && (po[0] > 0)) {
            if (intBoard[po[0] - 1][po[1]] == 1) {
                ArrayList<int[]> pastMovesC = ((ArrayList<int[]>) pastMoves.clone());
                pastMovesC.add(po);
                sum += recurFindStartPoint(pastMovesC, new int[]{po[0] - 1, po[1]}, intBoard);
            }
        }
        if(down && (po[0] < 41)) {
            if (intBoard[po[0] + 1][po[1]] == 1) {
                ArrayList<int[]> pastMovesC = ((ArrayList<int[]>) pastMoves.clone());
                pastMovesC.add(po);
                sum += recurFindStartPoint(pastMovesC, new int[]{po[0] + 1, po[1]}, intBoard);
            }
        }
        return sum;
    }
    public static int[][] deepCopy(int[][] original) {
        if (original == null) {
            return null;
        }

        final int[][] result = new int[original.length][];
        for (int i = 0; i < original.length; i++) {
            result[i] = Arrays.copyOf(original[i], original[i].length);
            // For Java versions prior to Java 6 use the next:
            // System.arraycopy(original[i], 0, result[i], 0, original[i].length);
        }
        return result;
    }
}
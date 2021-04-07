package csci2020u.tictactoe;

import java.util.Map;
import java.util.TreeMap;


public class GameLogic {

    /*
    * Function finds out who wins the game
    * based on the current state of the buttonGrid
    *
    * @param choices: map of all positions and which player occupies the positions
    * */
    public Map<String, Boolean> getWinner(Map<String,String> choices){

        // String: Status, Boolean: is the game still going on?
        Map<String, Boolean> result = new TreeMap<>();
        result.put("NO-WINNER",true);

        //TOP ROW
        if((choices.containsKey("topLeft"))
                &&(choices.containsKey("topCenter"))
                &&(choices.containsKey("topRight"))){

            //IF ALL ARE X
            if((choices.get("topLeft").equals("X"))
                    && (choices.get("topCenter").equals("X"))
                    &&(choices.get("topRight").equals("X"))){
                //X WINS
                result.put("X",false);
            }
            //IF ALL ARE O
            else if((choices.get("topLeft").equals("O"))
                    && (choices.get("topCenter").equals("O"))
                    &&(choices.get("topRight").equals("O"))){
                //O WINS
                result.put("O",false);
            }
        }

        //MIDDLE ROW
        else if((choices.containsKey("centerLeft"))
                &&(choices.containsKey("center"))
                &&(choices.containsKey("centerRight"))){

            //IF ALL ARE X
            if((choices.get("centerLeft").equals("X"))
                    && (choices.get("center").equals("X"))
                    &&(choices.get("centerRight").equals("X"))){
                //X WINS
                result.put("X",false);
            }
            //IF ALL ARE O
            else if((choices.get("centerLeft").equals("O"))
                    && (choices.get("center").equals("O"))
                    &&(choices.get("centerRight").equals("O"))){
                //O WINS
                result.put("O",false);
            }
        }

        //BOTTOM ROW
        else if((choices.containsKey("bottomLeft"))
                &&(choices.containsKey("bottomCenter"))
                &&(choices.containsKey("bottomRight"))){

            //IF ALL ARE X
            if((choices.get("bottomLeft").equals("X"))
                    && (choices.get("bottomCenter").equals("X"))
                    &&(choices.get("bottomRight").equals("X"))){
                //X WINS
                result.put("X",false);
            }
            //IF ALL ARE O
            else if((choices.get("bottomLeft").equals("O"))
                    && (choices.get("bottomCenter").equals("O"))
                    &&(choices.get("bottomRight").equals("O"))){
                //O WINS
                result.put("O",false);
            }

        }

        //DIAGONAL LEFT TO RIGHT
        else if((choices.containsKey("topLeft"))
                &&(choices.containsKey("center"))
                &&(choices.containsKey("bottomRight"))){

            //IF ALL ARE X
            if((choices.get("topLeft").equals("X"))
                    && (choices.get("center").equals("X"))
                    &&(choices.get("bottomRight").equals("X"))){
                //X WINS
                result.put("X",false);
            }
            //IF ALL ARE O
            else if((choices.get("topLeft").equals("O"))
                    && (choices.get("center").equals("O"))
                    &&(choices.get("bottomRight").equals("O"))){
                //O WINS
                result.put("O",false);
            }
        }

        //DIAGONAL RIGHT TO LEFT
        else if((choices.containsKey("topRight"))
                &&(choices.containsKey("center"))
                &&(choices.containsKey("bottomLeft"))){

            //IF ALL ARE X
            if((choices.get("topRight").equals("X"))
                    && (choices.get("center").equals("X"))
                    &&(choices.get("bottomLeft").equals("X"))){
                //X WINS
                result.put("X",false);
            }
            //IF ALL ARE O
            else if((choices.get("topRight").equals("O"))
                    && (choices.get("center").equals("O"))
                    &&(choices.get("bottomLeft").equals("O"))){
                //O WINS
                result.put("O",false);
            }
        }

        return result;
    }
}
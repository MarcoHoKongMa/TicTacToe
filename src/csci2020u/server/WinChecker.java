package csci2020u.server;

import java.util.Map;
import java.util.TreeMap;

public class WinChecker {
    private Map<Integer, Boolean> symbolLocation;

    public WinChecker() {
        this.symbolLocation = new TreeMap<>();
        for(int i = 0; i < 9; i++) {
            symbolLocation.put(i, false);
        }
    }

    public boolean checkWin() {
        // Check 5 critical points(Points at (1, j) & (i, 1))
        if(symbolLocation.get(0)) {
            // Horizontal
            if(symbolLocation.get(1)) {
                return symbolLocation.get(2);
            }
            //Vertical
            else if(symbolLocation.get(3)) {
                return symbolLocation.get(6);
            }
            // Diagonal
            else if(symbolLocation.get(4)) {
                return symbolLocation.get(8);
            }
        }
        else if(symbolLocation.get(1)) {
            // Horizontal
            if(symbolLocation.get(0)) {
                return symbolLocation.get(2);
            }
            //Vertical
            else if(symbolLocation.get(4)) {
                return symbolLocation.get(7);
            }
        }
        else if(symbolLocation.get(2)) {
            // Horizontal
            if(symbolLocation.get(1)) {
                return symbolLocation.get(0);
            }
            //Vertical
            else if(symbolLocation.get(5)) {
                return symbolLocation.get(8);
            }
            // Diagonal
            else if(symbolLocation.get(4)) {
                return symbolLocation.get(6);
            }
        }
        else if(symbolLocation.get(3)) {
            // Horizontal
            if(symbolLocation.get(4)) {
                return symbolLocation.get(5);
            }
            //Vertical
            else if(symbolLocation.get(0)) {
                return symbolLocation.get(6);
            }
        }
        else if(symbolLocation.get(6)) {
            // Horizontal
            if(symbolLocation.get(7)) {
                return symbolLocation.get(8);
            }
            //Vertical
            else if(symbolLocation.get(3)) {
                return symbolLocation.get(0);
            }
            // Diagonal
            else if(symbolLocation.get(4)) {
                return symbolLocation.get(2);
            }
        }
        return false;
    }

    public void update(int index) {
        symbolLocation.put(index, true);
    }
}

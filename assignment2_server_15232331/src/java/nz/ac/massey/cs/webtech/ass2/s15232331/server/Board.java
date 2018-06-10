// Error reading included file Templates/Licenses/license-default.txt
package nz.ac.massey.cs.webtech.ass2.s15232331.server;

/**
 *
 * @author 15232331
 */


public class Board {
    
    private String[][] _matrix;
    
    public Board(String starts) {
        if(starts.equals("comp")) {
            this._matrix = new String[][]{
                {"-", "-", "-"},
                {"-", "O", "-"},
                {"-", "-", "-"}
            };           
        } else {
            this._matrix = new String[][]{
                {"-", "-", "-"},
                {"-", "-", "-"},
                {"-", "-", "-"}
            }; 
        }
    }
    
    public String[][] getMatrix() {
        return this._matrix;
    }
    
    public void applyPosition(String move, int x, int y) {        
        this._matrix[y][x] = move;
    }
}

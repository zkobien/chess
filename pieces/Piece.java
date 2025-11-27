package pieces;

public abstract class Piece {
    int x;
    int y;
    String type;

    Piece(int x, int y, String type) {
        this.x = x;
        this.y = y;
        this.type = type;
    }


    //setters
    public void setPos(int x, int y) {
        this.x = x;
        this.y = y;
    }
    //getters
    public int getX() {
       return x;
    }
    public int getY() {
        return y;
    }
}

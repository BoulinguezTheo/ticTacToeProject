public class Cell {
    private final String LeftPart;
    private final String RightPart;
    public String representation;
    public Cell(){
        this.LeftPart = "| ";
        this.representation = " ";
        this.RightPart = " ";
    }
    public String getCell(){
        return this.LeftPart + this.representation + this.RightPart;
    }
}

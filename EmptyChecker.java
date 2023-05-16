package four;

public class EmptyChecker {
    private Button[][] cells;

    public int CheckEmpty(int row, int column) {
        for (int i = 0; i < cells.length; i++) {
            if (cells[i][column].getText().equals(" ")) {
                return i;
            }
        } return cells.length;
    }

    public EmptyChecker(Button[][] cells) {
        this.cells = cells;
    }
}

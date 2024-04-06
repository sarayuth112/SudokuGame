public class SudokuModel {
    //กำหนดค่าคงที่
    private static final int BOARD_SIZE = 9;

    //สร้าง method สนามการเล่น
    private int[][] _board;

    //ตัวที่ใช้สร้าง
    public SudokuModel() {
        _board = new int[BOARD_SIZE][BOARD_SIZE];
    }
    public SudokuModel(String initialBoard) {
        this();
        initializeFromString(initialBoard);
    }

    //เริ่มจาการใช้ตัว string
    public void initializeFromString(final String boardStr) {
        clear();  // ล้างค่าทั้งหมดออกจากกระดาน
        int row = 0;
        int col = 0;

        //วนซ้ำ
        for (int i = 0; i < boardStr.length(); i++) {
            char c = boardStr.charAt(i);
            if (c >= '1' && c <='9') {
                if (row > BOARD_SIZE || col > BOARD_SIZE) {
                    throw new IllegalArgumentException("SudokuModel: "
                            + " Attempt to initialize outside 1-9 "
                            + " at row " + (row+1) + " and col " + (col+1));
                }

                _board[row][col] = c - '0';  // แปลตัวเลขเป็น int
                col++;
            } else if (c == '.') {
                col++;
            } else if (c == '/') {
                row++;
                col = 0;
            } else {
                throw new IllegalArgumentException("SudokuModel: Character '" + c
                        + "' not allowed in board specification");
            }
        }
    }

    //ตั้งค่าการเล่นโดยใส่ตัวเลขตั้งเเต่ 1-9 ให้เป็นทั้งเเถวเเละคอลัมน์
    public boolean isLegalMove(int row, int col, int val) {
        return row>=0 && row<BOARD_SIZE && col>=0 && col<BOARD_SIZE
                && val>0 && val<=9 && _board[row][col]==0;
    }

    //กำหนดค่า value
    public void setVal(int r, int c, int v) {
        _board[r][c] = v;
    }

    //รับค่า value
    public int getVal(int row, int col) {
        return _board[row][col];
    }

    //clear
    public void clear() {
        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int col = 0; col < BOARD_SIZE; col++) {
                setVal(row, col, 0);
            }
        }
    }
}
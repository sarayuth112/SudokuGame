import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.JComponent;

// การแสดงกระดานซูโดกุ
public class SudokuBoardDisplay  extends JComponent {
    //กำหนดค่าคงที่
    private static final int CELL_PIXELS = 50;  // ขนาดของแต่ละ cell
    private static final int PUZZLE_SIZE = 9;   // จำนวนของ rows/cols
    private static final int SUBSQUARE   = 3;   // ขนาดของ subsquare.
    private static final int BOARD_PIXELS = CELL_PIXELS * PUZZLE_SIZE;
    private static final int TEXT_OFFSET = 15;  // การจัดวางข้อความแบบละเอียด
    private static final Font TEXT_FONT  = new Font("Sansserif", Font.BOLD, 24);
    private SudokuModel _model;      //ตั้งอยู่ในคอนสตรัคเตอร์

    //สร้างBackgroundตัวกระดาน
    public SudokuBoardDisplay(SudokuModel model) {
        setPreferredSize(new Dimension(BOARD_PIXELS + 2, BOARD_PIXELS + 2));
        setBackground(Color.yellow);
        _model = model;
    }

    //สีส่วนประกอบ
    @Override public void paintComponent(Graphics g) {
        g.setColor(getBackground());
        g.fillRect(0, 0, getWidth(), getHeight());
        g.setColor(Color.BLACK);

        drawGridLines(g);
        drawCellValues(g);
    }
    //drawGridLines
    // แยกวิธีเพื่อทำให้ paintComponent ง่ายขึ้น
    private void drawGridLines(Graphics g) {
        for (int i = 0; i <= PUZZLE_SIZE; i++) {
            int acrossOrDown = i * CELL_PIXELS;
            g.drawLine(acrossOrDown, 0, acrossOrDown, BOARD_PIXELS);
            g.drawLine(0, acrossOrDown, BOARD_PIXELS, acrossOrDown);

            if (i % SUBSQUARE == 0) {
                acrossOrDown++;
                g.drawLine(acrossOrDown, 0, acrossOrDown, BOARD_PIXELS);
                g.drawLine(0, acrossOrDown, BOARD_PIXELS, acrossOrDown);
            }
        }
    }

    //drawCellValues
    private void drawCellValues(Graphics g) {
        g.setFont(TEXT_FONT);
        for (int i = 0; i < PUZZLE_SIZE; i++) {
            int yDisplacement = (i+1) * CELL_PIXELS - TEXT_OFFSET;
            for (int j = 0; j < PUZZLE_SIZE; j++) {
                if (_model.getVal(i, j) != 0) {
                    int xDisplacement = j * CELL_PIXELS + TEXT_OFFSET;
                    g.drawString("" + _model.getVal(i, j), xDisplacement, yDisplacement);
                }
            }
        }
    }

}
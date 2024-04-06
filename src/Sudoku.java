import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Sudoku extends JFrame {
    ///กำหนดค่าตัวเลขลงในกระดาน
    private static final String INITIAL_BOARD =
            "..615...2/" +
                    "7.328...6/" +
                    ".1..96.4./" +
                    "9..4.5.78/" +
                    "......4../" +
                    "374..8561/" +
                    "5.1.7..8./" +
                    "..983..57/" +
                    ".....96.3";

    //กำหนดหน้าต่างsudoku
    private SudokuModel        _sudokuLogic = new SudokuModel(INITIAL_BOARD);
    private SudokuBoardDisplay _sudokuBoard = new SudokuBoardDisplay(_sudokuLogic);

    private JTextField _rowTF = new JTextField(2);
    private JTextField _colTF = new JTextField(2);
    private JTextField _valTF = new JTextField(2);

    //ตัวสร้างtextเพื่อให้ผู้ใช้สามารถใส่เลขได้
    public Sudoku() {
        JButton moveBtn = new JButton("Move");

        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new FlowLayout());
        controlPanel.add(new JLabel("Row (1-9):"));
        controlPanel.add(_rowTF);
        controlPanel.add(new JLabel("Col (1-9):"));
        controlPanel.add(_colTF);
        controlPanel.add(new JLabel("Val:"));
        controlPanel.add(_valTF);
        controlPanel.add(moveBtn);

        //เพิ่มlistener
        moveBtn.addActionListener(new MoveListener());

        //สร้าง content panel,เพื่อ set layout
        JPanel content = new JPanel();
        content.setLayout(new BorderLayout());

        // เพิ่มส่วนประกอบไปในcontent panel
        content.add(controlPanel, BorderLayout.NORTH);
        content.add(_sudokuBoard, BorderLayout.CENTER);

        // ตั้งค่าคุณสมบัติของหน้าต่าง
        setContentPane(content);
        setTitle("Sudoku 3");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);               // อย่าให้ผู้ใช้ปรับขนาด
        pack();
        setLocationRelativeTo(null);
    }


    //MoveListener
    class MoveListener implements ActionListener {
        public void actionPerformed(ActionEvent ae) {
            try {
                //เเปลงค่าเเถวเเละคอลัมน์
                int row = Integer.parseInt(_rowTF.getText().trim()) - 1;
                int col = Integer.parseInt(_colTF.getText().trim()) - 1;
                int val = Integer.parseInt(_valTF.getText().trim());
                if (_sudokuLogic.isLegalMove(row, col, val)) {
                    _sudokuLogic.setVal(row, col, val);
                    _sudokuBoard.repaint();
                } else {
                    JOptionPane.showMessageDialog(null, "Illegal row, col, or value.");
                }

            } catch (NumberFormatException nfe) {
                JOptionPane.showMessageDialog(null, "Please enter numeric values.");
            }
        }
    }

    //main
    public static void main(String[] args) {
        new Sudoku().setVisible(true);
    }
}
package ee219;

///////Deals with buttons on the board
import javax.swing.JButton;


public class BoardButton extends JButton
{
    private String sign;
    private boolean pressed;
    private int xPos,yPos,value;

    public BoardButton(int xPos, int yPos) {
        this.xPos = xPos;
        this.yPos = yPos;
        value = 0;
    }
    public void setSign(String sign) {
        setText(sign);
    }
    public String getSign() {
       return getText();
    }
    public boolean getState() {
       return pressed;
    }
    public void setState(boolean pressed) {
        this.pressed = pressed;
    }
    public int getValue() {
        return value;
    }
    public void setValue(int value) {
        this.value = value;
    }        
}
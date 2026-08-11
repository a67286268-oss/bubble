package test2;

import lombok.Getter;
import lombok.Setter;

import javax.swing.*;

@Getter
public class Player extends JLabel implements Moveable {

    private int x;
    private int y;

    private ImageIcon playerL;
    private ImageIcon playerR;

    // 플레이어
    private final int SPEED = 4;
    private final int JUMP_SPEED = 2;

    // 플레이이어의 움직임 상태
    @Setter
    private boolean left;

    @Setter
    private boolean right;

    private boolean up = false;
    private boolean down;

    public Player() {
        initdata();
        setInitLayout();
    }

    private void initdata() {
        x = 55;
        y = 535;

        left = false;
        right = false;
        up = false;
        down = false;

        playerR = new ImageIcon("images/playerR.png");
        playerL = new ImageIcon("images/playerL.png");
    }

    private void setInitLayout() {
        setSize(50, 50);
        setLocation(x, y);
        setIcon(playerR);
    }


    @Override
    public void left() {
        if (left) {
            return;
        }
        left = true;
        setIcon(playerL);
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (left) {
                    x -= SPEED;
                    setLocation(x, y);
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }).start();
    }

    @Override
    public void right() {
        if (right) {
            return;
        }
        right = true;
        setIcon(playerR);
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (right) {
                    x += SPEED;
                    setLocation(x, y);
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }).start();
    }

    @Override
    public void up() {
        if (up) {
            return;
        }
        up = true;
        new Thread(new Runnable() {
            @Override
            public void run() {
                int count = 0;
                int maxCount = 70;

                try {
                    while (count < maxCount) {
                        y -= JUMP_SPEED;
                        count++;
                        setLocation(x, y);
                        Thread.sleep(5);
                    }

                    while (count > 0) {
                        y += JUMP_SPEED;
                        count--;
                        setLocation(x, y);
                        Thread.sleep(7);
                    }
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                } finally {
                    up = false;
                }
            }
        }).start();
    }

    @Override
    public void down() {

    }

}

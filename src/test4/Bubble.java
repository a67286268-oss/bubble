package test4;

import lombok.Getter;

import javax.swing.*;

@Getter
public class Bubble extends JLabel implements Moveable {

    private int x;
    private int y;
    private Player player;
    private ImageIcon bubbleIcon;

    private boolean left;
    private boolean right;
    private boolean up;

    private boolean leftWallCrash;
    private boolean rightWallCrash;

    private final int SPEED = 5;
    private final int UP_SPEED = 2;

    public Bubble(Player player) {
        this.player = player;
        initData();
        setInitLayout();
    }

    private void initData() {
        bubbleIcon = new ImageIcon("images/bubble.png");
    }

    private void setInitLayout() {
        x = player.getX();
        y = player.getY();
        setLocation(x, y);
        setSize(50, 50);
        setIcon(bubbleIcon);
    }

    @Override
    public void left() {
        left = true;
        new Thread(() -> {
            for (int i = 0; i < 100 / SPEED; i++) {
                if (leftWallCrash) break;
                x = x - SPEED;
                setLocation(x, y);
                try {
                    Thread.sleep(5);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            left = false;
            up();
        }).start();
    }

    @Override
    public void right() {
        right = true;
        new Thread(() -> {
            for (int i = 0; i < 100 / SPEED; i++) {
                if (rightWallCrash) break;
                x = x + SPEED;
                setLocation(x, y);
                try {
                    Thread.sleep(5);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            right = false;
            up();
        }).start();
    }

    @Override
    public void up() {
        up = true;
        new Thread(() -> {
            while (up) {
                y = y - UP_SPEED;
                setLocation(x, y);

                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }


}
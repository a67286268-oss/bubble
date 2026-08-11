package test4;

public interface Moveable {

    void left();
    void right();
    void up();
    default void down() {};

}

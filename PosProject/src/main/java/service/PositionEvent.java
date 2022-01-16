package service;

/**
 *
 * @author Rori
 */
public class PositionEvent {
    private String id;
    private String msg;

    public PositionEvent(String id, String msg) {
        this.id = id;
        this.msg = msg;
    }

    public String getId() {
        return id;
    }

    public String getMsg() {
        return msg;
    }

    @Override
    public String toString() {
        return "PositionEvent{" + "id=" + id + ", msg=" + msg + '}';
    }
}

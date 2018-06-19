package cn.edu.tj.wenda.async;

import java.util.List;

/**
 * Created by mao on 2017/5/24.
 */
public interface EventHandler {
    void doHandle(EventModel model);
    List<EventType> getSupportEventTypes();
}

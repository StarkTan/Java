package com.stark.design23.memento;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Stark on 2018/3/26.
 * 状态缓存
 */
public class StatusCache {
    private List<Status> statuses = new ArrayList<>();

    public void addStatus(Status status) {
        statuses.add(status);
    }

    public Status getStatus(int index) {
        return statuses.get(index);
    }
}

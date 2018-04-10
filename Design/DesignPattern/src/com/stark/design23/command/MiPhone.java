package com.stark.design23.command;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Stark on 2018/3/27.
 * 手机 具体的命令下发者
 */
public class MiPhone {
    private List<Command> commands;


    public MiPhone() {
        this.commands = new ArrayList<>();
    }

    public void setBtn(Command command) {
        commands.add(command);
    }

    public void pushBtn(int number) {
        if (number % 2 == 0) commands.get(number / 2).excute();
        else commands.get(number / 2).undo();
    }
}

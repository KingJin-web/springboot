package com.king.other.short_link.things;

import java.applet.Applet;
import java.awt.*;

/**
 * @program: springboot
 * @description:
 * @author: King
 * @create: 2021-08-02 20:24
 */
public
class A extends Applet {
    String s;
    @Override
    public void init() {
        s = "H";
    }

    public void paint(Graphics g){
        g.drawString(s,50,50);
    }

    public static void main(String[] args) {

    }
}



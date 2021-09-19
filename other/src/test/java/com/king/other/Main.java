package com.king.other;

import java.util.Scanner;

/**
 * @program: springboot
 * @description:
 * @author: King
 * @create: 2021-09-18 23:33
 */
public class Main {


//attributes
    //public
    // public String stringRepresentation;
    // public double doubleRepresentation;

    /**
     * constructors
     */
    public Main() {
    }

    /*public Main(double doubleRepresentation){
        this.doubleRepresentation = doubleRepresentation;
    }
    public Main(String stringRepresentation){
        this.stringRepresentation = stringRepresentation;
    }*/
    public void paopao(String stringtemp) {
        //ringRepresentation;
        double temp = 0;
        //int i = 0;
        //while((stringtemp.charAt(i) != '.') && (stringtemp.length()-i-1 != 0))
        for (int i = 0; i < stringtemp.length(); i++) {
            temp = temp * 26 + (double) (stringtemp.charAt(i) - '0');
            i++;
        }
        System.out.println(temp);
    }


    public void setUserInput() {
        while (true) {
            Scanner input = new Scanner(System.in);
            char charflag;
            while (true) {

                String ch;
                //char ch=(char)System.in.read();
                System.out.println("Please enter 'h' to convert from Hexiacosadecimal to decimal, or 'd' to convert from decimal to Hexiacosadecimal.");
                //scan.hasNext（）;
                ch = input.next();
                charflag = ch.charAt(0);
                //charflag= ch;
                if ((charflag != 'h') && (charflag != 'H') && (charflag != 'd') && (charflag != 'D') && (charflag != 'q') && (charflag != 'Q')) {
                    System.out.println("INVALID INPUT");
                } else {
                    break;
                }
            }
            if (charflag == 'h' || charflag == 'H') {
                String strtemp;
                //canner input1 = new Scanner(System.in);
                System.out.println("Please enter your hexiacosadecimal number: ");
                strtemp = input.next();
                //this.stringRepresentation = stringRepresentation;
                System.out.println(strtemp);
                paopao(strtemp);
                System.out.println("========");
            } else if (charflag == 'd' || charflag == 'D') {
                double doubleRepresentation;
                //Scanner input2 = new Scanner(System.in);
                System.out.println("Please enter your decimal number: ");
                doubleRepresentation = input.nextDouble();
            } else {
                break;
            }
        }
    }

    public static void main(String[] args) {
        Main myMain = new Main();
        myMain.setUserInput();


    }
}
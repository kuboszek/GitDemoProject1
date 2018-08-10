package pl.wayedu;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Calculator {
    private double result;
    private String operators = "+-*/%^";

    int getPriority(char operator){
        switch (operator) {
            case '+':case '-':
                return 0;
            case '*':case '/': case '%':
                return 1;
            case '^':
                return 2;
            default:
                return -1;
        }
    }

    public double eval(String expr){
        int digitCounter =0;
        Queue<String> wyjscie = new LinkedList<>();
        Stack<String> stos = new Stack<>();
        String number="";
        for(int i=0; i<expr.length(); i++){
            if (Character.isDigit(expr.charAt(i))){
                digitCounter++;
                number+=expr.charAt(i);
            }
            else{
                if (digitCounter!=0){//odczytano liczbe o digitCounter cyfrach
                    //Jeśli symbol jest liczbą dodaj go do kolejki wyjście.
                    wyjscie.add(number);
                    digitCounter = 0;
                }
                if (isOperator(expr.charAt(i))){    //operator o1
                    char op1 = expr.charAt(i);
                    String op2="";
                    while(!stos.empty() && goodOperator(stos.peek().charAt(0), op1)){
                        op2 = stos.pop();
                        wyjscie.add(op2);

                        //jeszcze raz while?

                    }
                    stos.push(""+op1);
                }
            }
        }


        return result;
    }

    private boolean goodOperator(char peek, char op1) {
        return (isOperator(peek) && getPriority(op1)<=getPriority(peek));
    }


    private boolean isOperator(char c) {
        for (int i=0; i<operators.length(); i++){
            if (operators.charAt(i) == c){
                return true;
            }
        }
        return false;


    }

}

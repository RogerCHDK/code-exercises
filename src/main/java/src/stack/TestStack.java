package src.stack;

import java.util.Stack;

public class TestStack {
    public boolean isValid(String s) {
        /*
        * Create one stack for open brackets
        * Iterate through the String
        * If we found a open bracket insert it into the stack
        * If we found a close bracket peek a item from the stack
        * If the item that we peek from the stack it's the corresponding open bracket continue iterating
        * otherwise return false
        * At the end if the stack is empty we return true*/
        Stack<Character> stack = new Stack<>();
        char[] input = s.toCharArray();
        for (char bracket : input){
            if (isOpenBracket(bracket)){
                stack.push(bracket);
            }else{
                if (stack.isEmpty()){
                    return false;
                }
                Character peek = stack.peek();
                if (peek == '[' && bracket == ']'){
                    stack.pop();
                }else if (peek == '{' && bracket == '}'){
                    stack.pop();
                }else if (peek == '(' && bracket == ')'){
                    stack.pop();
                } else {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }

    public boolean isOpenBracket(char input){
        return (input == '{' || input == '[' || input == '(');
    }

    public boolean isValid2(String s) {
        Stack<Character> stack = new Stack<>();
        for (char c: s.toCharArray()) {
            switch(c) {
                case '(':
                    stack.push(')');
                    break;
                case '{':
                    stack.push('}');
                    break;
                case '[':
                    stack.push(']');
                    break;
                default:
                    if (stack.isEmpty() || stack.pop() != c) {
                        return false;
                    }
            }
        }
        return stack.isEmpty();
    }
}

package org.qifei;

import org.junit.Test;

import java.util.ArrayDeque;
import java.util.Deque;

public class LongVaildparentheses {

    public int longestValidParentheses(String s) {
        Deque<Character> que = new ArrayDeque<>();
        int len = s.length();
        if(len <=1){
            return 0;
        }
        int ans = 0;
        int cur = 0;
        for(int i =0;i<len;i++){
            Character t = s.charAt(i);
            if(t=='('){
                if(que.size() == 0){
                    cur = 0;
                }
                que.add(t)cd 
            }else{
                if(que.size() == 0){
                    cur = 0;
                    continue;
                }else{
                    que.removeLast();
                    cur += 2;
                    ans = Math.max(cur,ans);
                }
            }
        }
        return ans;
    }
    @Test
    public void test() {
        longestValidParentheses("()()");

        int k =2;
    }
}

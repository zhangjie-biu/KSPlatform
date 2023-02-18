import java.util.*;

class Solution {
    public static void main(String[] args) {
        int[] pushed = {1,2,3,4,5};
        int[] poped = {4,5,3,2,1};
        Map<Integer,Integer> mp = new HashMap<>();
        Set<Map.Entry<Integer, Integer>> entries = mp.entrySet();
        for(Map.Entry set:entries){
            set.getValue().equals(1);
        }

        List<Integer> res = new ArrayList<>();
        res.add(1);
        res.add(2);
        res.add(4);
        res.remove(0);
        res.add(5);
        res.add(6);
        System.out.println(validateStackSequences(pushed,poped));
    }
    public static boolean validateStackSequences(int[] pushed, int[] popped) {


        Deque<Integer> stack = new ArrayDeque<Integer>();
        for(int i=0,j=0;i<pushed.length;i++){
            stack.push(pushed[i]);
            while(stack.peek()==popped[j]){
                stack.pop();
                j++;
            }
        }

        return stack.isEmpty();


    }
}
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
        System.out.println(BoyerMoore("мама мыла мыла раму мама", "мама"));
    }

    public static Map<Character, Integer> preBmBc(String x){
        Map<Character, Integer> table = new HashMap<>();
        int m = x.length();
        for (int i = m - 2; i >= 0; i-- ){
            char ch = x.charAt(i);
            if(!table.containsKey(ch)){
                table.put(ch, m - i - 1);
            }
        }
        if(!table.containsKey(x.charAt(m-1))){
            table.put(x.charAt(m-1), m);
        }
        return table;
    }

    public static ArrayList<Integer> BoyerMoore(String source, String template){
        ArrayList<Integer> answer = new ArrayList<>();
        int sourceLength = source.length(), templateLength = template.length();
        if(sourceLength == 0){
            answer.add(-1);
            return answer;
        }
        if(templateLength == 0){
            return answer;
        }
        Map<Character, Integer> bmBc = preBmBc(template);

        label:
        for (int i = templateLength-1; i < sourceLength;){
            int j = templateLength - 1;
            while (template.charAt(j) == source.charAt(i)){
                if(j == 0){
                    answer.add(i);
                    i+=templateLength;
                    continue label;
                }
                i--;
                j--;
            }
            i+=bmBc.getOrDefault(source.charAt(i), templateLength);
        }
        if(answer.isEmpty()){
            answer.add(-1);
        }
        return answer;
    }
/**
    public static boolean isPrefix(String x, int p){
        int j = 0;
        int m = x.length();
        for(int i = p; i < m; i++){
            if (x.charAt(i) != x.charAt(j)){
                return false;
            }
            j++;
        }
        return true;
    }

    public  static int suffixLength(String x, int p){
        int len = 0;
        int i = p;
        int j = x.length() - 1;
        while (i>=0 && x.charAt(i)==x.charAt(j)){
            len++;
            i--;
            j--;
        }
        return len;
    }

    public static ArrayList<Integer> preBmGs(String x){
        int m = x.length();
        ArrayList<Integer> table = new ArrayList<>();
        for (int i = 0; i < m; i++){
            table.add(0);
        }
        int lastPrefixPosition = m;
        for (int i = m - 1; i >= 0; i--){
            if (isPrefix(x, i+1)){
                lastPrefixPosition = i+1;
            }
            table.set(m - i - 1, lastPrefixPosition - i + m - 1);
        }
        for (int i = 0; i < m - 1; i++){
            int slen = suffixLength(x, i);
            table.set(slen, m - i - 1 + slen);
        }
        return table;
    }**/
}

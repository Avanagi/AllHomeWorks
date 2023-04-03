import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Class> list = new ArrayList<>();
        for(int i = 0; i < Integer.MAX_VALUE; i++){
            HashMap<Object, Object> hashMap = new HashMap<>();
            list.add(hashMap.getClass());
        }
    }
}
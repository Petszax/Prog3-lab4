import java.util.Comparator;

public class StrengthComparator implements Comparator<Beer> {
    public int compare(Beer b1, Beer b2){
        if(b1.getStrength()<b2.getStrength()){
            return -1;
        }else if(b1.getStrength()==b2.getStrength()){
            return 0;
        }else{
            return 1;
        }
    }
}

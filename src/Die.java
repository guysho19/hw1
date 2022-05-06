import java.util.Random;

public class Die {
    private int minRandomValue;
    private int maxRandomValue;

    public Die(int minRandomValue, int maxRandomValue) {
        this.minRandomValue = minRandomValue;
        this.maxRandomValue = maxRandomValue;
    }

    public Die() {
        this(1,6);
    }
    public int roll(){
    return this.minRandomValue+(Main.rnd=new Random()).nextInt(maxRandomValue-minRandomValue+1);
    }

    public int getMinRandomValue() {
        return minRandomValue;
    }

    public int getMaxRandomValue() {
        return maxRandomValue;
    }
}

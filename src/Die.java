import java.util.Random;

public class Die {
    private int minRandomValue;
    private int maxRandomValue;

    public Die(int maxRandomValue, int minRandomValue) {
        this.minRandomValue = minRandomValue;
        this.maxRandomValue = maxRandomValue;
    }

    public Die() {
        this(1,6);
    }
    public int roll(){
        int randomRoll=this.minRandomValue+(Main.rnd=new Random()).nextInt(maxRandomValue-minRandomValue+1);
        System.out.println(randomRoll);
        return randomRoll;
    }

    public int getMinRandomValue() {
        return minRandomValue;
    }

    public int getMaxRandomValue() {
        return maxRandomValue;
    }
}

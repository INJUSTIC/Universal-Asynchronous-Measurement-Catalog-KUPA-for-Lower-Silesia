package obliczenia;

import java.util.Random;

public class ObliczWilg implements Oblicz{
    @Override
    public double oblicz() {
        Random random = new Random();
        return random.nextInt(0,100);
    }
}

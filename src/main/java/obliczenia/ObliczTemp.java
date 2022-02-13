package obliczenia;

import java.util.Random;

public class ObliczTemp implements Oblicz{
    @Override
    public double oblicz() {
        Random random = new Random();
        return random.nextInt(-5,5);
    }
}

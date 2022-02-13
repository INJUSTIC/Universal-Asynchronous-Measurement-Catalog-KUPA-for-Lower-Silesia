package obliczenia;

import java.util.Random;

public class ObliczCisn implements Oblicz{
    @Override
    public double oblicz() {
        Random random = new Random();
        return random.nextInt(730,770);
    }
}

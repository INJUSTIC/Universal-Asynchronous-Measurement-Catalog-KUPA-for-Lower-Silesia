package obliczenia;

import java.io.Serializable;
import java.util.Random;

public class ObliczTemp implements Oblicz, Serializable {
    @Override
    public double oblicz() {
        Random random = new Random();
        return ((int)(random.nextFloat(-5,5) * 100))/100.0;
    }
}

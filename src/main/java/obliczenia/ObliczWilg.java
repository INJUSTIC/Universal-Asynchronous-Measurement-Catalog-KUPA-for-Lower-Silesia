package obliczenia;

import java.io.Serializable;
import java.util.Random;

public class ObliczWilg implements Oblicz, Serializable {
    @Override
    public double oblicz() {
        Random random = new Random();
        return ((int)(random.nextFloat(0,100) * 100))/100.0;
    }
}

package obliczenia;

import java.io.Serializable;
import java.util.Random;

public class ObliczCisn implements Oblicz, Serializable {
    @Override
    public double oblicz() {
        Random random = new Random();
        return random.nextInt(730,770);
    }
}

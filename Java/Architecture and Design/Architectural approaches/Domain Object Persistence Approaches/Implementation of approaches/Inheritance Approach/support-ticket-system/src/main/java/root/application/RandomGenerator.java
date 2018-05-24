package root.application;

import java.util.UUID;

public class RandomGenerator 
{
    private static final String DASH = "-";
    private static final String EMPTY = "";
    
    public static String generate()
    {
        return UUID.randomUUID().toString().replace(DASH, EMPTY);
    }
}

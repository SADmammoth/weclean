package com.example.weclean.helpers;

public class Helpers {
    public static Object[] unionArrays(Object[]... arrays)
    {
        int maxSize = 0;
        int counter = 0;

        for(Object[] array : arrays) maxSize += array.length;
        Object[] accumulator = new Object[maxSize];

        for(Object[] array : arrays)
            for(Object i : array)
                if(!isDuplicated(accumulator, counter, i))
                    accumulator[counter++] = i;

        Object[] result = new Object[counter];
        for(int i = 0; i < counter; i++) result[i] = accumulator[i];

        return result;
    }

    public static boolean isDuplicated(Object[] array, int counter, Object value)
    {
        for(int i = 0; i < counter; i++) if(array[i].equals(value)) return true;
        return false;
    }
}

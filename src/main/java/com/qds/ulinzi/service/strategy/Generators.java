package com.qds.ulinzi.service.strategy;

import org.apache.commons.lang3.RandomStringUtils;

public class Generators {
    public static String randomGenerateCode(int codeLength, String prefix, String suffix){
        String randomValue = RandomStringUtils.randomAlphanumeric(codeLength);
        if(prefix != null){
            randomValue=prefix+randomValue;
        }
        if(suffix != null){
            randomValue+=suffix;
        }

        return randomValue;
    }
}

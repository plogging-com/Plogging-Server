package com.plogging.global.utill;

public class DateChanger {

    // "2022-08-27 08:47:33.554207" --> "2022.08.27 오전 08:47" 으로 바꿔줌
    public static String changefrom(String localdate){

        String substring = localdate
                .replace('-', '.')// 1. "2022-08-27 08:47:33.554207"
                .substring(0, 16);// 2. "2022.08.27 08:47"

        if(Integer.parseInt(substring.split(" ")[1].split(":")[0]) >= 12)
            return substring.replace(" ", " 오후 ");
        else
            return substring.replace(" ", " 오전 ");
    }
}
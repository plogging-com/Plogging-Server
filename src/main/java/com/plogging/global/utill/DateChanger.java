package com.plogging.global.utill;

public class DateChanger {

    // "2022-08-27T08:47:33.554207" --> "2022.08.27 오전 08:47" 으로 바꿔줌
    public static String changefrom(String localDateTimeStr){
        String substring = localDateTimeStr
                .replace('-', '.')// 1. "2022-08-27 08:47:33.554207"
                .substring(0, 16);// 2. "2022.08.27 08:47"
        if(Integer.parseInt(substring.split("T")[1].split(":")[0]) >= 12)
            return substring.replace("T", " 오후 ");
        else
            return substring.replace("T", " 오전 ");
    }
}

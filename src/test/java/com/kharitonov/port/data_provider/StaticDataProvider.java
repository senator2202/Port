package com.kharitonov.port.data_provider;

import java.util.ArrayList;
import java.util.List;

public class StaticDataProvider {
    public static final String SHIP_LINE =
            "1 20: 1 2.5; 2 3.5; 3 1.5; 4 5.5; 5 3.0";
    public static final List<String> SHIP_LINE_LIST;

    static {
        SHIP_LINE_LIST = new ArrayList<>();
        SHIP_LINE_LIST.add("1 20: 1 2.5; 2 3.5; 3 1.5; 4 5.5; 5 3.0");
        SHIP_LINE_LIST.add("2 15: 6 2.2; 7 3.3; 8 4.4; 9 3.3; 10 4.1; 11 3.7");
        SHIP_LINE_LIST.add("3 12: 12 6.0; 13 5.0; 14 7.4; 15 6.6");
        SHIP_LINE_LIST.add("4 10: 16 3.3; 17 5.5; 18 5.6; 19 5.7; 20 5.8; 21 7.7; 22 6.2");
        SHIP_LINE_LIST.add("5 5: 23 4.1; 24 5.2; 25 3.5");
        SHIP_LINE_LIST.add("6 15: 26 3.3; 27 4.4; 28 5.5; 29 6.6; 30 7.7; 31 7.6; 32 6.5; 33 5.4");
        SHIP_LINE_LIST.add("7 12: 34 2.3; 35 3.4; 36 4.5; 37 5.6; 38 6.7");
        SHIP_LINE_LIST.add("8 20: 39 5.0; 40 4.1; 41 5.9; 42 4.2; 43 5.8; 44 4.3; 45 5.7; 46 4.3; 47 5.6");
        SHIP_LINE_LIST.add("9 10: 48 2.1; 49 2.2; 50 2.3; 51 2.4; 52 2.5; 53 2.6; 54 2.7; 55 2.8; 56 2.9; 57 3.0");
        SHIP_LINE_LIST.add("10 12: 58 4.1; 59 5.0; 60 8.1; 61 2.1; 62 3.0");
    }
}
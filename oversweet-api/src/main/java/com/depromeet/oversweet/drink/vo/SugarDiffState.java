package com.depromeet.oversweet.drink.vo;

public enum SugarDiffState {

    MINUS, PLUS, SAME;


    public static SugarDiffState from(final int mainSugar, final int sugar) {
        if (mainSugar > sugar) {
            return MINUS;
        }

        if (mainSugar < sugar) {
            return PLUS;
        }

        return SAME;
    }
}

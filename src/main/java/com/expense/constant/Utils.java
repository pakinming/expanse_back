package com.expense.constant;

import java.time.format.DateTimeFormatter;
import java.util.Locale;


public  class Utils {

    public final static String PATTERN_RFC3999 = "yyyy-MM-dd'T'HH:mm:ssZ";
    public final static String PATTERN_DATE = "yyyy-MM-dd";
    public final static String GMT_7 = "GMT+7";
    public final static DateTimeFormatter FMT_RFC3339 = DateTimeFormatter.ofPattern(PATTERN_RFC3999).withLocale(Locale.of("th","TH"));
//    public final static SimpleDateFormat FMT_RFC3339 = new SimpleDateFormat(PATTERN_RFC3999);

}

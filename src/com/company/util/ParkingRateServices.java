package com.company.util;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;

public class ParkingRateServices {
    public static BigDecimal calculateParkingCharges(Date entryDate, Date exitDate, BigDecimal rate) {
        final BigDecimal MILLI_TO_HOUR = BigDecimal.valueOf(60 * 60);
        BigDecimal hours = BigDecimal.valueOf(exitDate.getTime() - entryDate.getTime()).divide(MILLI_TO_HOUR, RoundingMode.CEILING);
        return rate.multiply(hours);
    }
}

package com.alg.other;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

// https://leetcode.com/discuss/interview-question/1544410/Doordash-TPS-Senior-Software-Engineer
public class DD_getAvailableDeliveries {
    /**
     * Rules:
     * 1. Deliveries scheduled two days or further should never be available
     * 2. High tier dashers can see all of next day deliveries if current time is 18:00 or later
     * 3. All dashers can see all of next day deliveries if current time is 19:00 or later
     * 4. All dashers can see same day deliveries anytime
     */
    public static List<Delivery> getAvailableDeliveriesForDasher(DDasher dasher, List<Delivery> allDeliveries, LocalDateTime currentDateTime) {
        List<Delivery> eligibleDeliveries = new ArrayList<>();

        for (Delivery delivery : allDeliveries) {
            long daysBetween = Math.abs(currentDateTime.getDayOfYear() - delivery.pickupDateTime.getDayOfYear());

            if (daysBetween >= 2) {
                continue;
            }

            if (daysBetween == 0) {
                eligibleDeliveries.add(delivery);
                continue;
            }

//            if (daysBetween == 1) { // this is all the "else"
                if (dasher.isHighTier) {
                    if (currentDateTime.getHour() >= 18) {
                        eligibleDeliveries.add(delivery);
                        continue;
                    }
                }

                if (currentDateTime.getHour() >= 19) {
                    eligibleDeliveries.add(delivery);
                    continue;
                }
//            }
        }

        return eligibleDeliveries;
    }

    class Delivery {
        LocalDateTime pickupDateTime;
        String storeId;

        public Delivery(LocalDateTime pickupDateTime, String storeId) {
            this.pickupDateTime = pickupDateTime;
            this.storeId = storeId;
        }
    }

    class DDasher {
        String dasherId;
        boolean isHighTier;

        public DDasher(String dasherId, boolean isHighTier) {
            this.dasherId = dasherId;
            this.isHighTier = isHighTier;
        }
    }
}

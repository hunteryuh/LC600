package com.alg.other;

import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

public class DD_GetClosestDriver {
    public class DasherDistance {
        class Location {
            double longitude;
            double latitude;

            Location(double longitude, double latitude) {
                this.longitude = longitude;
                this.latitude = latitude;
            }
        }

        class Dasher {
            long id;
            Location lastLocation;
            int rating;

            Dasher(long id, Location lastLocation, int rating) {
                this.id = id;
                this.lastLocation = lastLocation;
                this.rating = rating;
            }
        }

        class Tuple {
            long id;
            double distance;
            int rating;

            Tuple(long id, double distance, int rating) {
                this.id = id;
                this.distance = distance;
                this.rating = rating;
            }
        }

        long[] getClosestDashers(Location restaurantLocation) {
            // maxHeap
            PriorityQueue<Tuple> pq = new PriorityQueue<>((o1, o2) -> {
                if (o1.distance > o2.distance) {
                    // o1.distance is larger than o2.distance, so swap them and put o1 at the top of the PQ since we want to keep the largest of the 3 distances on top of the PQ
                    return -1; // larger distance, closer to top of queue to be popped
                }
                if (o1.distance == o2.distance) {
                    // the distances are the same, so compare ratings. if o1.rating is larger than or equal to o2.rating, then dont swap them, otherwise, swap and put o2 over o1
                    return o1.rating >= o2.rating ? 1 : -1;
                }
                return 1;
            });

            for (Dasher d : getDashers()) {
                double distance = getDistance(restaurantLocation, d.lastLocation);
                pq.offer(new Tuple(d.id, distance, d.rating));
                if (pq.size() > 3) {
                    pq.poll();
                }
            }

            long[] res = new long[3];
            int i = 3;
            while (i >= 0 && !pq.isEmpty()) {
                res[--i] = pq.poll().id;
            }

            return res;
        }

        private List<Dasher> getDashers() {
            return Collections.emptyList();
        }

        double getDistance(Location restaurantLocation, Location dasherLocation) {
            return Math.sqrt(((dasherLocation.latitude - restaurantLocation.latitude) * (dasherLocation.latitude - restaurantLocation.latitude)) + (
                    (dasherLocation.longitude - restaurantLocation.longitude) * (dasherLocation.longitude - restaurantLocation.longitude)));
        }
    }
}

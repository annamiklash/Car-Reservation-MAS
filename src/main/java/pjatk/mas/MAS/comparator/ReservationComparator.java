package pjatk.mas.MAS.comparator;

import pjatk.mas.MAS.model.dto.Reservation;

import java.time.LocalDate;
import java.util.Comparator;

public class ReservationComparator implements Comparator<Reservation> {
    @Override
    public int compare(Reservation o1, Reservation o2) {
        final LocalDate o1Date = o1.getDateFrom();
        final LocalDate o2Date = o2.getDateFrom();

        return o1Date.compareTo(o2Date);
    }
}

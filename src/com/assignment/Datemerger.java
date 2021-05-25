package com.assignment;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Datemerger {

	public static void main(String args[]) {
		List<DateRange> list = new ArrayList<>();

		DateRange range1 = new DateRange(LocalDate.of(2014, 01, 1), LocalDate.of(2014, 01, 30));
		list.add(range1);

		DateRange range2 = new DateRange(LocalDate.of(2014, 01, 15), LocalDate.of(2014, 02, 15));
		list.add(range2);

		DateRange range3 = new DateRange(LocalDate.of(2014, 03, 10), LocalDate.of(2014, 04, 15));
		list.add(range3);

		DateRange range4 = new DateRange(LocalDate.of(2014, 04, 10), LocalDate.of(2014, 05, 15));
		list.add(range4);

		List<DateRange> finalRangeList = mergeDates(list);
		for (DateRange range : finalRangeList) {
			//System.out.println(range.getStartDate()+"-"+range.getEndDate());
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy");
			System.out.println(range.getStartDate().format(formatter) + "-" + range.getEndDate().format(formatter));

		}

	}

	public static List<DateRange> mergeDates(List<DateRange> dateRanges) {
		List<DateRange> finalRangeList = new ArrayList<>();

		DateRange currentRange = new DateRange(dateRanges.get(0).getStartDate(), dateRanges.get(0).getEndDate());
		for (DateRange range : dateRanges.subList(1, dateRanges.size())) {
			// System.out.println(range);

			if ((range.getStartDate().isAfter(currentRange.getStartDate())
					|| range.getStartDate().isEqual(currentRange.getStartDate()))
					&& (range.getStartDate().isBefore(currentRange.getEndDate())
							&& range.getEndDate().isAfter(currentRange.getEndDate()))) {
				currentRange.setEndDate(range.getEndDate());

			} else if (range.getStartDate().isAfter(currentRange.getEndDate())) {
				DateRange r = new DateRange(currentRange.getStartDate(), currentRange.getEndDate());
				finalRangeList.add(r);
				currentRange.setStartDate(range.getStartDate());
				currentRange.setEndDate(range.getEndDate());
			}

		}

		finalRangeList.add(currentRange);

		return finalRangeList;

	}

}

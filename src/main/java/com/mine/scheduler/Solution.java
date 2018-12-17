package com.mine.scheduler;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;

/**
 * @stefanl
 */
public class Solution {

    public static final String SPACE_STR = " ";
    public static final String NEW_LINE_STR = "\n";
    public static final String DASH_STR = "-";
    public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public int solution(String input) {


        String[] lines = input.split(NEW_LINE_STR);
        Set<Meeting> meetings = collectMeetings(lines);

        return (int) getMax(meetings);
    }

    private Set<Meeting> collectMeetings(String[] lines) {
        Set<Meeting> meetings = new TreeSet<>(compareByStart());

        for (int i = 0; i < lines.length; i++) {
            String[] dayAndTime = lines[i].split(SPACE_STR);
            String[] hours = dayAndTime[1].split(DASH_STR);
            meetings.add(new Meeting(getLocalDateTime(dayAndTime[0], hours[0]),
                    getLocalDateTime(dayAndTime[0], hours[1])));
        }
        return meetings;
    }

    private Comparator<Meeting> compareByStart() {
        return Comparator.comparing(
                Meeting::getStart,
                LocalDateTime::compareTo
        );
    }

    private long getMax(Set<Meeting> meetings) {
        Meeting currentMeeting = null;
        long max = 0;
        for (Meeting nextMeeting : meetings) {
            if (currentMeeting == null) {
                currentMeeting = nextMeeting;
                continue;
            }
            long duration = durationBetween(currentMeeting.end, nextMeeting.start).toMinutes();
            if (nextMeeting.equals(((TreeSet<Meeting>) meetings).last())) {
                duration = durationBetween(nextMeeting.end, LocalDateTime.of(
                        nextMeeting.getEnd().getYear(), nextMeeting.getEnd().getMonth().getValue(),
                        nextMeeting.getEnd().getDayOfMonth(), 23, 59)).toMinutes();
                duration = duration + 1;
            }
            if (duration > max) {
                max = duration;
            }
            currentMeeting = nextMeeting;
        }
        return max;
    }

    private Duration durationBetween(LocalDateTime start, LocalDateTime end) {
        return Duration.between(start, end);
    }

    public LocalDateTime getLocalDateTime(String value, String hour) {
        switch (value.toLowerCase()) {
            case "mon":
                return LocalDateTime.parse("2018-01-01 " + hour, DATE_TIME_FORMATTER);
            case "tue":
                return LocalDateTime.parse("2018-01-02 " + hour, DATE_TIME_FORMATTER);
            case "wed":
                return LocalDateTime.parse("2018-01-03 " + hour, DATE_TIME_FORMATTER);
            case "thu":
                return LocalDateTime.parse("2018-01-04 " + hour, DATE_TIME_FORMATTER);
            case "fri":
                return LocalDateTime.parse("2018-01-05 " + hour, DATE_TIME_FORMATTER);
            case "sat":
                return LocalDateTime.parse("2018-01-06 " + hour, DATE_TIME_FORMATTER);
            case "sun":
                return LocalDateTime.parse("2018-01-07 " + hour, DATE_TIME_FORMATTER);

            default:
                throw new IllegalArgumentException("Day not found");
        }
    }

    public class Meeting {
        LocalDateTime start;
        LocalDateTime end;

        public Meeting(LocalDateTime start,
                       LocalDateTime end) {
            this.start = start;
            this.end = end;
        }

        public LocalDateTime getStart() {
            return start;
        }

        public LocalDateTime getEnd() {
            return end;
        }
    }


}

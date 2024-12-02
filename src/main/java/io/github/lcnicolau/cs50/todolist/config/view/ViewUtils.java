package io.github.lcnicolau.cs50.todolist.config.view;

import org.springframework.stereotype.Component;

import java.time.*;

@Component
class ViewUtils {

    public static String getElapsedTimeSince(Instant date) {
        LocalDate localDate = LocalDate.ofInstant(date, ZoneId.systemDefault());
        Period p = Period.between(localDate, LocalDate.now());
        if (p.getYears() > 1) return p.getYears() + " years ago";
        if (p.getYears() > 0) return p.getYears() + " year ago";
        if (p.getMonths() > 1) return p.getMonths() + " months ago";
        if (p.getMonths() > 0) return p.getMonths() + " month ago";
        if (p.getDays() / 7 > 1) return p.getDays() / 7 + " weeks ago";
        if (p.getDays() / 7 > 0) return p.getDays() / 7 + " week ago";
        if (p.getDays() > 1) return p.getDays() + " days ago";
        if (p.getDays() > 0) return p.getDays() + " day ago";
        Duration d = Duration.between(date, Instant.now());
        if (d.toHoursPart() > 1) return d.toHoursPart() + " hours ago";
        if (d.toHoursPart() > 0) return d.toHoursPart() + " hour ago";
        if (d.toMinutesPart() > 1) return d.toMinutesPart() + " minutes ago";
        return "about a minute ago";
    }

}

package com.ots.trainingapi.global.utils;

import com.google.common.base.Stopwatch;
import org.joda.time.DateTime;
import org.joda.time.Interval;
import org.springframework.util.Assert;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.apache.commons.lang.time.DateUtils.addSeconds;

public class DateUtils {
    
    /** Datetime ISO 8601 format "yyyy-MM-ddThh:mm:ss" */
    public static SimpleDateFormat DATETIME_FORMAT_ISO = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
    
    /** Date ISO 8601 format "yyyy-MM-dd" */
    public static SimpleDateFormat DATE_FORMAT_ISO = new SimpleDateFormat("yyyy-MM-dd");
    
    /**
     * Converts the milliseconds since January 1, 1970, 00:00:00 GMT to <a
     * href="https://en.wikipedia.org/wiki/ISO_8601">ISO 8601</a> Time format,
     * i.e. <code>hh:mm:ss.sss</code>, a more human readable format. For example
     * 4530436 ms is converted to 01:15:30.436 .
     *
     * <p>
     * In order to make the conversion we used this StackOverflow <a
     * href="http://stackoverflow.com/a/625624">question</a>.
     * </p>
     *
     * @param millis
     *            the milliseconds since January 1, 1970, 00:00:00 GMT.
     */
    public static String millisToHumanReadable(long millis) {
        // http://stackoverflow.com/a/625624
        // Don't leave me this way
        
        return String.format(
                "%02d:%02d:%02d.%03d",
                TimeUnit.MILLISECONDS.toHours(millis),
                TimeUnit.MILLISECONDS.toMinutes(millis)
                        - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS
                        .toHours(millis)),
                TimeUnit.MILLISECONDS.toSeconds(millis)
                        - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS
                        .toMinutes(millis)),
                millis
                        - TimeUnit.SECONDS.toMillis(TimeUnit.MILLISECONDS
                        .toSeconds(millis)));
    }
    
    /**
     * Converts the {Stopwatch#elapsed(TimeUnit) elapsed} milliseconds to
     * <a href="https://en.wikipedia.org/wiki/ISO_8601">ISO 8601</a> Time
     * format, i.e. <code>hh:mm:ss.sss</code>, a more human readable format. For
     * example a stopwatch with a value of {Stopwatch#elapsed(TimeUnit)
     * elapsed} 4530436 ms is converted to 01:15:30.436 .
     *
     * @param watch
     *            a {Stopwatch}.
     * @return an ISO 8601 formatted date string (<code>hh:mm:ss.sss</code>) or
     *         <code>null</code> if given parameter is <code>null</code>.
     */
    public static String millisToHumanReadable(Stopwatch watch) {
        if (watch == null) {
            return null;
        }
        
        return millisToHumanReadable(watch.elapsed(TimeUnit.MILLISECONDS));
    }
    
    /**
     * Μετατροπή του δοθέντος αντικειμένου ημερομηνίας σε string μορφής dd/MM/yyyy.
     */
    public static String getDateString(Object dateObject) {
        
        if (dateObject == null) {
            return null;
        }
        
        try {
            return new SimpleDateFormat("dd/MM/yyyy").format((Date) dateObject);
        }
        catch (ClassCastException e) {
            return null;
        }
    }
    
    /**
     * Μετατροπή του δοθέντος αντικειμένου ημερομηνίας/ώρας σε string μορφής dd/MM/yyyy HH:mm.
     */
    public static String getDateTimeString(Object dateTimeObject) {
        
        if (dateTimeObject == null) {
            return null;
        }
        
        try {
            return new SimpleDateFormat("dd/MM/yyyy HH:mm").format((Date) dateTimeObject);
        }
        catch (ClassCastException e) {
            return null;
        }
    }
    
    /**
     * Μετατροπή του δοθέντος αντικειμένου ημερομηνίας/ώρας σε string μορφής dd/MM/yyyy HH:mm:ss.
     */
    public static String getDateTimeWithSecondsString(Object dateTimeObject) {
        
        if (dateTimeObject == null) {
            return null;
        }
        
        try {
            return new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format((Date) dateTimeObject);
        }
        catch (ClassCastException e) {
            return null;
        }
    }
    
    /**
     * Μετατροπή της συγκεκριμένης ημερομηνίας σε string μορφής dd/MM/yyyy 00:00:00.
     */
    public static String getStartOfDate(Date date) {
        
        if (date == null) {
            return null;
        }
        
        try {
            String strDate = new SimpleDateFormat("dd/MM/yyyy").format(date);
            return strDate + " 00:00:00";
        }
        catch (ClassCastException e) {
            return null;
        }
    }
    
    
    /**
     * Μετατροπή της συγκεκριμένης ημερομηνίας σε string μορφής dd/MM/yyyy 23:59:59.
     */
    public static String getEndOfDate(Date date) {
        
        if (date == null) {
            return null;
        }
        
        try {
            String strDate = new SimpleDateFormat("dd/MM/yyyy").format(date);
            return strDate + " 23:59:59";
        }
        catch (ClassCastException e) {
            return null;
        }
    }
    
    /**
     * Εξαγωγή του έτους σε Long από μία ημερομηνία<br/>
     * Σε περίπτωση που η ημερομηνία είναι null επιστρέφεται το έτος της τρέχουσας ημερομηνίας του συστήματος
     * @param date Η ημερομηνία
     * @return Το έτος της ημερομηνίας σε Long
     */
    public static Long getDateYear(Date date) {
        
        Calendar calendar = new GregorianCalendar();
        
        if (date != null) calendar.setTime(date);
        
        return new Long(calendar.get(Calendar.YEAR));
    }
    
    /**
     * Ανάκτηση αρχής συγκεκριμένης ημερομηνίας σε επίπεδο δευτερολέπτου (πχ. 10/10/2016 00:00:01)
     * @param date Ημερομηνία
     * @return
     */
    public static Date getStartOfDay(Date date) {
        if (date == null) {
            return null;
        }
        
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 1);
        
        return calendar.getTime();
    }
    
    /**
     * Ανάκτηση τέλους συγκεκριμένης ημερομηνίας σε επίπεδο δευτερολέπτου (πχ. 10/10/2016 23:59:59)
     * @param date Ημερομηνία
     * @return
     */
    public static Date getEndOfDay(Date date) {
        return addSeconds(org.apache.commons.lang.time.DateUtils.ceiling(date, Calendar.DATE), -1);
    }
    
    /**
     * Εξαγωγή πεδίου (έτος/μήνας/μέρα/ώρα/λεπτά) σε Integer από ημερομηνία
     * @param date
     * @param component Το component από το Calendar (πχ. Calendar.MINUTE ή Calendar.HOUR)
     * @return Το στοιχείο της ημερομηνίας σε Integer και σε περίπτωση λάθος ορίσματος -1
     */
    public static int getDateComponent(Date date, int component) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        try {
            return calendar.get(component);
        }
        catch(ArrayIndexOutOfBoundsException ex) {
            return -1;
        }
    }
    
    /**
     * Μετατροπή συγκεκριμένης ημερομηνίας σε String περιγραφής
     * χρονοσφραγίδας εκτέλεσης λειτουργίας Scheduler
     * Για παράδειγμα η χρονική στιγμή "12/01/2017 23:10" μετατρέπεται σε "0 10 23 12 1 ? 2017"
     * http://www.quartz-scheduler.org/documentation/quartz-2.x/tutorials/crontrigger.html
     * @param date Ημερομηνία
     * @return
     */
    public static String convertDateToCronExpression(Date date) {
        
        Calendar calendar = new GregorianCalendar();
        
        if (date == null) return null;
        
        calendar.setTime(date);
        
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        
        return String.format("0 %d %d %d %d ? %d", minute, hour, day, month, year);
    }
    
    /**
     * Έλεγχος εάν μία ημερομηνία βρίσκεται στο παρελθόν
     * @param date Ημερομηνία
     * @return
     */
    public static boolean dateInPast(Date date) {
        Assert.notNull(date, "Date should not be null");
        return date.before(new Date());
    }
    
    /**
     * Έλεγχος εάν μία ημερομηνία βρίσκεται στο μέλλον
     * @param date Ημερομηνία
     * @return
     */
    public static boolean dateInFuture(Date date) {
        Assert.notNull(date, "Date should not be null");
        return date.after(new Date());
    }
    
    /**
     * Ανάκτηση πρωτοχρονιάς του έτους ημερομηνίας
     * Μηδενίζονται και τα στοιχεία της ώρας (εάν υπάρχουν)
     * @param date Ημερομηνία
     * @return
     */
    public static Date getFirstDateOfYear(Date date) {
        
        Calendar calendar = new GregorianCalendar();
        
        if (date != null) {
            calendar.setTime(date);
        }
        else {
            return null;
        }
        
        calendar.set(Calendar.MONTH, 0);
        calendar.set(Calendar.DAY_OF_YEAR, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        
        return calendar.getTime();
    }
    
    /**
     * Πρόσθεση λεπτών σε ημερομηνία με τα δευτερόλεπτα να ορίζονται σε 0
     * Για παράδειγμα η χρονική στιγμή "12/01/2017 23:10:24" +2 μετατρέπεται σε "12/01/2017 23:12:00"
     * @param date    Ημερομηνία
     * @param minutes Λεπτά
     * @return
     */
    public static Date addMinutesToDate(Date date, int minutes) {
        
        Calendar calendar = new GregorianCalendar();
        
        if (date != null) {
            calendar.setTime(date);
        }
        else {
            return null;
        }
        
        calendar.add(Calendar.MINUTE, minutes);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        
        return calendar.getTime();
    }
    
    /**
     * Πρόσθεση ημερών σε ημερομηνία
     * @param date    Ημερομηνία
     * @param days    Ημέρες
     * @return
     */
    public static Date addDaysToDate(Date date, int days) {
        
        Calendar calendar = new GregorianCalendar();
        
        if (date != null) {
            calendar.setTime(date);
        }
        else {
            return null;
        }
        
        calendar.add(Calendar.DATE, days);
        
        return calendar.getTime();
    }
    
    /**
     * Πρόσθεση μηνών σε ημερομηνία
     * Για παράδειγμα η χρονική στιγμή "12/01/2017 23:10:24" +2 μετατρέπεται σε "12/03/2017 23:10:24"
     * @param date    Ημερομηνία
     * @param months Μήνες
     * @return
     */
    public static Date addMonthsToDate(Date date, int months) {
        
        Calendar calendar = new GregorianCalendar();
        
        if (date != null) {
            calendar.setTime(date);
        }
        else {
            return null;
        }
        
        calendar.add(Calendar.MONTH, months);
        
        return calendar.getTime();
    }
    
    /**
     * Δημιουργία αντικειμένου ημερομηνίας από τις διακριτές τιμές
     * @param year   Έτος
     * @param month  Μήνας (ξεκινά από το 1)
     * @param day    Ημέρα
     * @param hour   Ώρα (οι απογευματινές ώρες πάνε ως εξής 20 για τις 8 το απόγευμα κτλ.)
     * @param minute Λεπτά
     * @param second Δευτερόλεπτα
     * @return
     */
    public static Date getDate(int year, int month, int day, int hour, int minute, int second) {
        
        Calendar calendar = new GregorianCalendar();
        
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month-1);
        calendar.set(Calendar.DAY_OF_MONTH, day);
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, minute);
        calendar.set(Calendar.SECOND, second);
        calendar.set(Calendar.MILLISECOND, 0);
        
        return calendar.getTime();
    }
    
    /**
     * Έλεγχος αν δύο ημερομηνίες είναι όμοιες
     * Λαμβάνονται υπόψη και οι null τιμές. Αν είναι και οι δύο null, τότε θεωρούνται όμοιες.
     * @param d1 Ημερομηνία Α
     * @param d2 Ημερομηνία Β
     */
    public static boolean datesAreEqual(Date d1, Date d2) {
        return d1 == null ? d2 == null : d1.equals(d2);
    }
    
    /**
     * Έλεγχος αν δύο ημερομηνίες είναι όμοιες χωρίς να κοιτάμε την ώρα.
     * Λαμβάνονται υπόψη και οι null τιμές. Αν είναι και οι δύο null, τότε θεωρούνται όμοιες.
     */
    public static boolean datesAreEqualIgnoringTime(Date d1, Date d2) {
        
        if (d1 == null && d2 == null) {
            return true;
        }
        else if (d1 == null && d2 != null) {
            return false;
        }
        else if (d1 != null && d2 == null) {
            return false;
        }
        else {
            return org.apache.commons.lang.time.DateUtils.isSameDay(d1, d2);
        }
    }
    
    /**
     * Έλεγχος αν η πρώτη ημερομηνία είναι μεγαλύτερη από τη δεύτερη χωρίς να κοιτάμε την ώρα.
     * Λαμβάνονται υπόψη και οι null τιμές. Αν είναι και οι δύο null, τότε θεωρούνται όμοιες.
     */
    public static boolean firstDateGreaterIgnoringTime(Date d1, Date d2) {
        
        if (d1 == null && d2 == null) {
            return false;
        }
        else if (d1 == null && d2 != null) {
            return false;
        }
        else if (d1 != null && d2 == null) {
            return true;
        }
        else {
            Calendar cal1 = Calendar.getInstance();
            cal1.setTime(d1);
            Calendar cal2 = Calendar.getInstance();
            cal2.setTime(d2);
            if (cal1.get(Calendar.YEAR) > cal2.get(Calendar.YEAR)) {
                return true;
            }
            else if (cal1.get(Calendar.YEAR) < cal2.get(Calendar.YEAR)) {
                return false;
            }
            else {
                if (cal1.get(Calendar.ERA) > cal2.get(Calendar.ERA)) {
                    return true;
                }
                else if (cal1.get(Calendar.ERA) < cal2.get(Calendar.ERA)) {
                    return false;
                }
                else {
                    return cal1.get(Calendar.DAY_OF_YEAR) > cal2.get(Calendar.DAY_OF_YEAR);
                }
            }
        }
        
    }
    
    /**
     * Έλεγχος αν η πρώτη ημερομηνία είναι μεγαλύτερη από τη δεύτερη. Λαμβάνονται υπόψη και οι null τιμές.
     * Με την παράμετρο ignoreTime αγνοούμε ή όχι την ώρα στη σύγκριση.
     */
    public static boolean firstDateGreater(Date d1, Date d2, boolean ignoreTime) {
        if(ignoreTime) {
            return firstDateGreaterIgnoringTime(d1, d2);
        }
        else {
            if(d1 == null && d2 == null) {
                return false;
            }
            else if(d1 == null && d2 != null) {
                return false;
            }
            else if(d1 != null && d2 == null) {
                return true;
            }
            else {
                return d1.after(d2);
            }
        }
    }
    
    /**
     * Ορισμός της ώρας της ημερομηνίας σε 0
     */
    public static Date setTimeToZero(Date date) {
        
        Calendar calendar = new GregorianCalendar();
        
        if (date != null) {
            calendar.setTime(date);
        }
        else {
            return null;
        }
        
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        
        return calendar.getTime();
    }
    
    /**
     * Ορισμός των milliseconds της ημερομηνίας σε 0
     */
    public static Date setMillisecondsToZero(Date date) {
        
        Calendar calendar = new GregorianCalendar();
        
        if (date != null) {
            calendar.setTime(date);
        }
        else {
            return null;
        }
        
        calendar.set(Calendar.MILLISECOND, 0);
        
        return calendar.getTime();
    }
    
    /**
     * Υπολογισμός διαφοράς ημερομηνιών σε ώρες
     */
    public static long getDatesDifferenceInHours(Date firstDate, Date secondDate) {
        
        if (firstDate == null || secondDate == null) {
            return -1;
        }
        else {
            long diffInMillies = Math.abs(secondDate.getTime() - firstDate.getTime());
            return TimeUnit.HOURS.convert(diffInMillies, TimeUnit.MILLISECONDS);
        }
        
    }
    
    /**
     * Υπολογισμός διαφοράς ημερομηνιών σε ημέρες
     */
    public static long getDatesDifferenceInDays(Date firstDate, Date secondDate) {
        
        if (firstDate == null || secondDate == null) {
            return -1;
        }
        else {
            long diffInMillies = Math.abs(secondDate.getTime() - firstDate.getTime());
            return TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
        }
        
    }
    
    /**
     * Υπολογισμός διαφοράς ημερομηνιών σε μήνες
     */
    public static long getDatesDifferenceInMonths(Date firstDate, Date secondDate) {
        
        if(firstDate == null || secondDate == null) {
            return -1L;
        }
        
        Calendar startDateCal = Calendar.getInstance();
        Calendar endDateCal = Calendar.getInstance();
        
        startDateCal.setTime(firstDate);
        endDateCal.setTime(secondDate);
        
        Long months = 0L;
        
        while (startDateCal.before(endDateCal)) {
            startDateCal.add(Calendar.MONTH, 1);
            if (startDateCal.before(endDateCal) || org.apache.commons.lang.time.DateUtils.isSameDay(startDateCal, endDateCal)) {
                months++;
            }
        }
        return months;
    }
    
    /**
     * Υπολογισμός διαφοράς ημερομηνιών σε έτη
     */
    public static long getDatesDifferenceInYears(Date firstDate, Date secondDate) {
        
        if(firstDate == null || secondDate == null) {
            return -1L;
        }
        
        Calendar startDateCal = Calendar.getInstance();
        Calendar endDateCal = Calendar.getInstance();
        
        startDateCal.setTime(firstDate);
        endDateCal.setTime(secondDate);
        
        Long years = 0L;
        
        while (startDateCal.before(endDateCal)) {
            startDateCal.add(Calendar.YEAR, 1);
            if (startDateCal.before(endDateCal) || org.apache.commons.lang.time.DateUtils.isSameDay(startDateCal, endDateCal)) {
                years++;
            }
        }
        return years;
    }
    
    /**
     * Μετατροπή ενός sql timestamp σε αντικείμενο ημερομηνίας.
     * Αν η ώρα είναι 11 το βράδυ (23), τότε θεωρούμε ότι είναι προβληματική ημερομηνία και προσθέτουμε μία ημέρα.
     */
    public static Date convertTimestampToDate(Timestamp timestamp) {
        if (timestamp == null) {
            return null;
        }
        else {
            Calendar c = Calendar.getInstance();
            c.setTime(timestamp);
            if (c.get(Calendar.HOUR_OF_DAY) == 23) {
                c.add(Calendar.DATE, 1);
                c.set(Calendar.HOUR_OF_DAY, 0);
                c.set(Calendar.MINUTE, 0);
                c.set(Calendar.SECOND, 0);
                c.set(Calendar.MILLISECOND, 0);
                return c.getTime();
            }
            else {
                return c.getTime();
            }
        }
    }
    
    /**
     * Μετατροπή ημερομηνίας Date σε LocalDate με ζώνη ώρας του συστήματος.
     */
    public static LocalDate convertDateToLocalDate(Date date) {
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }
    
    /**
     * Μετατροπή ημερομηνίας Date σε LocalDateTime με ζώνη ώρας του συστήματος.
     */
    public static LocalDateTime convertDateToLocalDateTime(Date date) {
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
    }
    
    /**
     * Μετατροπή ημερομηνίας LocalDateTime σε Date με ζώνη ώρας του συστήματος.
     */
    public static Date convertLocalDateTimeToDate(LocalDateTime localDateTime) {
        return Date.from(localDateTime.atZone(ZoneId.systemDefault())
                .toInstant());
    }
    
    /**
     * Μετατροπή ημερομηνίας LocalDate σε Date με ώρα 00:00 με ζώνη ώρας του συστήματος.
     */
    public static Date convertLocalDateToDate(LocalDate localDate) {
        return Date.from(localDate.atStartOfDay().atZone(ZoneId.systemDefault())
                .toInstant());
    }
    
    /**
     * Ανάκτηση της πρώτης ημέρας του δεδομένου μήνα για το δεδομένο έτος
     * Τα στοιχεία της ώρας είναι 00:00:00.000
     */
    public static Date getFirstDateOfMonthAndYear(Integer year, Integer month) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month - 1, 1, 0, 0, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }
    
    /**
     * Ανάκτηση της τελευταίας ημέρας του δεδομένου μήνα για το δεδομένο έτος
     * Τα στοιχεία της ώρας είναι 00:00:00.000
     */
    public static Date getLastDateOfMonthAndYear(Integer year, Integer month) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month - 1, YearMonth.of(year, month).lengthOfMonth(), 0, 0, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }
    
    /**
     * Μέθοδος που δέχεται ένα string και:
     * Αν μπορεί να μετατραπεί σε ημερομηνία, το επιστρέφει στο κατάλληλο format βάσει του δεδομένου dateType.
     * Αν όχι, το επιστρέφει όπως είναι.
     */
    public static String formatValueIfDate(String fieldValue, String dateType) {
        if(TextUtils.isEmpty(fieldValue) || TextUtils.isEmpty(dateType)) {
            return fieldValue;
        }
        
        String outputDateFormat = null;
        if(dateType.equals("D")) {
            outputDateFormat = "dd/MM/yyyy";
        }
        else if(dateType.equals("DT")) {
            outputDateFormat = "dd/MM/yyyy HH:mm";
        }
        else {
            return fieldValue;
        }
        
        //Μπορούμε να ορίσουμε περισσότερα formats τα οποία μπορούμε να μετατρέψουμε.
        List<SimpleDateFormat> dateFormats = new ArrayList<>();
        dateFormats.add(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.S"));
        
        for(SimpleDateFormat dateFormat : dateFormats) {
            try {
                Date date = dateFormat.parse(fieldValue);
                return new SimpleDateFormat(outputDateFormat).format(date);
            }
            catch(ParseException e) {
            }
        }
        
        return fieldValue;
    }
    
    /**
     * Έλεγχος αν υπάρχει επικάλυψη μεταξύ των δύο δεδομένων ευρών ημερομηνιών
     */
    public static boolean dateRangesOverlap(Date firstRangeStart, Date firstRangeEnd, Date secondRangeStart, Date secondRangeEnd) {
        
        if(firstRangeStart == null || firstRangeEnd == null || secondRangeStart == null || secondRangeEnd == null) {
            //Αν κάποια ημερομηνία είναι null, επιστρέφεται false.
            return false;
        }
        else if(firstRangeStart.after(firstRangeEnd) || secondRangeStart.after(secondRangeEnd)) {
            //Αν σε κάποιο από τα δύο εύρη η ημερομηνία έναρξης είναι μετά την ημερομηνία λήξης, επιστρέφεται false.
            return false;
        }
        else if(firstRangeEnd.equals(secondRangeStart) || secondRangeEnd.equals(firstRangeStart)) {
            //Αν η ημερομηνία λήξης του ενός εύρους είναι η ίδια με την ημερομηνία έναρξης του άλλου, επιστρέφεται true.
            return true;
        }
        else {
            //Αλλιώς, γίνεται σύγκριση των ευρών.
            Interval firstInterval = new Interval(new DateTime(firstRangeStart), new DateTime(firstRangeEnd));
            Interval secondInterval = new Interval(new DateTime(secondRangeStart), new DateTime(secondRangeEnd));
            
            return firstInterval.overlaps(secondInterval);
        }
    }
    
    /**
     * Ανάκτηση του κοινού διαστήματος μεταξύ των δύο δεδομένων ευρών ημερομηνιών
     */
    public static Interval getOverlappingInterval(Date firstRangeStart, Date firstRangeEnd, Date secondRangeStart, Date secondRangeEnd) {
        
        if(firstRangeStart == null || firstRangeEnd == null || secondRangeStart == null || secondRangeEnd == null) {
            //Αν κάποια ημερομηνία είναι null, επιστρέφεται null.
            return null;
        }
        else if(firstRangeStart.after(firstRangeEnd) || secondRangeStart.after(secondRangeEnd)) {
            //Αν σε κάποιο από τα δύο εύρη η ημερομηνία έναρξης είναι μετά την ημερομηνία λήξης, επιστρέφεται null.
            return null;
        }
        else if(firstRangeEnd.equals(secondRangeStart)) {
            //Αν η ημερομηνία λήξης του πρώτου εύρους είναι η ίδια με την ημερομηνία έναρξης του δεύτερου, επιστρέφεται το εύρος από την έναρξη του πρώτου ως τη λήξη του δεύτερου.
            return new Interval(new DateTime(firstRangeStart), new DateTime(secondRangeEnd));
        }
        else if(secondRangeEnd.equals(firstRangeStart)) {
            //Αν η ημερομηνία λήξης του δεύτερου εύρους είναι η ίδια με την ημερομηνία έναρξης του πρώτου, επιστρέφεται το εύρος από την έναρξη του δεύτερου ως τη λήξη του πρώτου.
            return new Interval(new DateTime(secondRangeStart), new DateTime(firstRangeEnd));
        }
        else {
            //Αλλιώς, επιστρέγεται το overlapping εύρος.
            Interval firstInterval = new Interval(new DateTime(firstRangeStart), new DateTime(firstRangeEnd));
            Interval secondInterval = new Interval(new DateTime(secondRangeStart), new DateTime(secondRangeEnd));
            
            return firstInterval.overlap(secondInterval);
        }
    }
}

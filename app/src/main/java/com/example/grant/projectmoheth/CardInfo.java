package com.example.grant.projectmoheth;

import android.content.Context;

import java.util.ArrayList;
import java.util.Calendar;

/*
    in a separate class than the CardAdapter and CardViewHolder because I need to access the hour and
    minute in the AlarmReceiver class
*/
public class CardInfo {
    protected String name;
    protected String description;
    protected int hour;
    protected int minute;
    protected int dateCreated;
    /*
    Meaning of numbers is as follows
    Sunday: 0, Monday: 1, Tuesday: 2, Wednesday: 3, Thursday: 4, Friday: 5, Saturday: 6, Everyday: 7
     */
    protected ArrayList<Integer> selectedDay;

    public CardInfo(String name, String description, int hour, int minute, ArrayList<Integer> selectedDay) {
        Calendar calendar = Calendar.getInstance();
        int dateCreated = calendar.get(Calendar.DAY_OF_YEAR) + calendar.get(Calendar.YEAR);

        this.name = name;
        this.description = description;
        this.hour = hour;
        this.minute = minute;
        this.dateCreated = dateCreated;
        this.selectedDay = selectedDay;
    }

    public String getTruncatedName() {
        if (this.name.length() >= 13)
            return this.name.substring(0, 13) + "...";
        else
            return this.name;
    }

    public String getSelectedDayName(Context context) {
        if (this.selectedDay.size() > 1)
            Utils.insertionSort(this.selectedDay);

        String str = new String();

        if (this.selectedDay.size() > 3) {
            str = context.getString(R.string.custom);
        } else {
            for (int i = 0; i < this.selectedDay.size(); i++) {
                switch (this.selectedDay.get(i)) {
                    case 0:
                        str += context.getString(R.string.day_dialog_sunday_string) + ", ";
                        break;
                    case 1:
                        str += context.getString(R.string.day_dialog_monday_string) + ", ";
                        break;
                    case 2:
                        str += context.getString(R.string.day_dialog_tuesday_string) + ", ";
                        break;
                    case 3:
                        str += context.getString(R.string.day_dialog_wednesday_string) + ", ";
                        break;
                    case 4:
                        str += context.getString(R.string.day_dialog_thursday_string) + ", ";
                        break;
                    case 5:
                        str += context.getString(R.string.day_dialog_friday_string) + ", ";
                        break;
                    case 6:
                        str += context.getString(R.string.day_dialog_saturday_string) + ", ";
                        break;
                    case 7:
                        str += context.getString(R.string.every_day);
                        break;
                }
            }

                // removes the last comma and space
                if (this.selectedDay.get(0) != 7)
                    str = str.substring(0, str.length() - 2);
        }

        return str;
    }
}
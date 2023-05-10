package com.oorja.oorjaTest.Misc;
import org.json.JSONArray;
import org.json.JSONObject;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;


public class CheckWeekend {

    public static boolean isTodaySunday(){
        LocalDate today = LocalDate.now();
        DayOfWeek dayOfWeek = today.getDayOfWeek();

        System.out.println(dayOfWeek);
        if (dayOfWeek == DayOfWeek.SUNDAY) {
            return true;
        }
        return false;
    }

    public static boolean checkIndianPublicHolidayToday() throws Exception {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH);
        LocalDate today = LocalDate.now();
        String formattedDate = today.format(formatter);
        URL url = new URL("https://calendarific.com/api/v2/holidays?api_key=e4dc1198822016c0fd22d6815a25c878bedee37b&country=IN&year=2023" + today.getYear() + "&month=" + today.getMonthValue() + "&day=" + today.getDayOfMonth());
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.connect();
        int responseCode = conn.getResponseCode();
        if (responseCode == 200) {
            String response = new String(conn.getInputStream().readAllBytes());
            JSONObject json = new JSONObject(response);
            if (json.has("response")) {
                JSONObject responseObj = json.getJSONObject("response");
                if (responseObj.has("holidays")) {
                    JSONArray holidaysArr = responseObj.getJSONArray("holidays");
                    for (int i = 0; i < holidaysArr.length(); i++) {
                        JSONObject holiday = ((JSONArray) holidaysArr).getJSONObject(i);
                        String dateStr = holiday.getString("date");
                        LocalDate holidayDate = LocalDate.parse(dateStr, formatter);
                        if (holidayDate.equals(today)) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }
}

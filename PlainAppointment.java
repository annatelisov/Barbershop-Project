package Decorator;

public class PlainAppointment implements Appointment{

    private String day;
    private String time;
    private String currentUser;

    @Override
    public String getType() {
        return "User: " + currentUser + ", Day: " + day + ", Time: " + time;
    }

    @Override
    public double getCost() {
        return 0;
    }

    public void setDay(int day) {
        String dayToString = "";
        switch (day)
        {
            case 1:
                dayToString = "Sunday";
                break;
            case 2:
                dayToString = "Monday";
                break;
            case 3:
                dayToString = "Tuesday";
                break;
            case 4:
                dayToString = "Wednesday";
                break;
            case 5:
                dayToString = "Thursday";
                break;
            case 6:
                dayToString = "Friday";
                break;
            case 7:
                dayToString = "Saturday";
                break;
        }
        this.day = dayToString;
    }

    public void setTime(int time) {
        if(String.valueOf(time).length() == 1)
            this.time ="0"+ String.valueOf(time) + ":00";
        else
            this.time =String.valueOf(time) + ":00";
    }

    public String getDay() {
        return day;
    }

    public String getTime() {
        return time;
    }

    public void setCurrentUser(String currentUser) {
        this.currentUser = currentUser;
    }

    public String getCurrentUser() {
        return currentUser;
    }
}

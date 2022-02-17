package Class;

public class AppointmentDayTime {
    private int day;
    private int time;

    public AppointmentDayTime(int day , int time)
    {
        this.day = day;
        this.time = time;
    }
    public int getTime() {
        return time;
    }

    public int getDay() {
        return day;
    }
}

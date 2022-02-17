package Class;

import DataBase.DBController;
import Decorator.*;

import java.util.Vector;

import static Decorator.Prices.*;

public class Program {
    private boolean manager = false;
    private int openTime = 8;
    private int closeTime = 22;
    private boolean gotFromDB = false;
    private boolean gotPrices = false;
    Vector<Appointment> appointments = new Vector<Appointment>();
    Vector<AppointmentDayTime> apptDayTime = new Vector<AppointmentDayTime>();
    private static Program INSTANCE = null;
    DBController dataB = DBController.getInstance();
    private String currentUser =""; //To keep track who is the current user

    public void setCurrentUser(String user)
    {
        currentUser = user;
    }

    private Program()
    {

    }

    public static Program getInstance()
    {
        if(INSTANCE == null)
            INSTANCE = new Program();
        return INSTANCE;
    }

    public void setStartEndTimes(int start , int end)
    {
        setCloseTime(end);
        setOpenTime(start);
        dataB.updateStartEndTimes(start , end);
    }
    public void getPricesFromDB() //also does update start and end time
    {
        int[] startTimeEndTime = dataB.getStartEndTime();
        setOpenTime(startTimeEndTime[0]);
        setCloseTime(startTimeEndTime[1]);

        if(gotPrices && manager == false) return;
        double[] prices = dataB.getPrices();
        haircutPrice = prices[0];
        hairColorPrice = prices[1];
        hairDesignPrice = prices[2];
        manApPrice = prices[3];
        womanApPrice = prices[4];
    }

    public void getPrices(double hairCut , double hairColor , double manPrice , double womanPrice , double hairDesign)
    {
        haircutPrice = hairCut;
        hairColorPrice = hairColor;
        manApPrice = manPrice;
        womanApPrice = womanPrice;
        hairDesignPrice = hairDesign;
        System.out.println(hairCut + hairColor + manPrice + womanPrice + hairDesign);
        dataB.setPrices(hairCut,hairColor,manPrice,womanPrice,hairDesign);
    }

    public double calculateProfit()
    {
        double profit = 0;
        for(int i = 0 ; i < appointments.size() ; i++)
        {
            profit = profit + appointments.elementAt(i).getCost();
        }
        return profit;
    }

    public void addAppointment(boolean hairCut , boolean hairColor , boolean hairStyle, int day , int time , boolean addToDB)
    {
        String userOfAppt = "";
        if(addToDB)
            dataB.addAppointment(currentUser , hairCut , hairColor , hairStyle , day , time);
        if(addToDB == false && currentUser.equals(""))
        {
            userOfAppt = dataB.getUserAt(day,time);
        }
        //working with decorator
        Appointment newAppointment;
        PlainAppointment currentAppointment = new PlainAppointment();
        if(addToDB == false && currentUser.equals(""))
            currentAppointment.setCurrentUser(userOfAppt);
        else
            currentAppointment.setCurrentUser(currentUser);
        currentAppointment.setDay(day);
        currentAppointment.setTime(time);
        int gender = 0;
        if(addToDB == false && currentUser.equals(""))
            gender = dataB.getGender(userOfAppt);
        else
            gender = dataB.getGender(currentUser);
        if(gender == 1) {
            newAppointment = new Man(currentAppointment);
        }
        else
        {
            newAppointment = new Woman(currentAppointment);
        }
        if(hairStyle) newAppointment = new HairDesign(newAppointment);
        if(hairColor) newAppointment = new HairColor(newAppointment);
        if(hairCut) newAppointment = new Haircut(newAppointment);
      // System.out.println(newAppointment.getType());
        appointments.add(newAppointment);
        AppointmentDayTime newDTA = new AppointmentDayTime(day , time);
        apptDayTime.add(newDTA);
    }

    public void getAllAppointmentFromDB()
    {
        if(gotFromDB) return;
        gotFromDB = true;
        Vector<int[]> newAppnts;
        int[] currentAppt;
        newAppnts = dataB.getAppointments(currentUser);
        for(int i = 0 ; i < newAppnts.size() ; i++)
        {
            currentAppt = newAppnts.elementAt(i);
            addAppointment((1 == currentAppt[2]) , (1 == currentAppt[4]) , (1 == currentAppt[3]) , currentAppt[0] , currentAppt[1] , false);
        }
    }

    public Vector<String> getAppointmentsForView()
    {
        Vector<String> appsToStr = new Vector<String>();
        for(int i = 0 ; i < appointments.size() ; i++)
        {
            appsToStr.add(appointments.elementAt(i).getType() + " \n| Cost: " + appointments.elementAt(i).getCost());
        }
        return appsToStr;
    }
    public int[] getAvailableTimeForDay(int day)
    {
        return dataB.getFreeAppoitmentsForDay(day + 1 , openTime , closeTime);
    }

    public void removeAppointmentAt(int i)
    {
        AppointmentDayTime aptToRemove = apptDayTime.elementAt(i);
        dataB.removeAppointment(aptToRemove.getDay() , aptToRemove.getTime());
        apptDayTime.removeElementAt(i);
        appointments.removeElementAt(i);
    }
    //////////////////////////////////////////////////////////////////////////GET APPOINTMENTS FROM DB

    //////////////////////////////////////////////////////////////////////////GET SET FOR OPEN/CLOSE TIME
    public int getOpenTime() {
        return openTime;
    }
    public int getCloseTime(){
        return closeTime;
    }

    public void setOpenTime(int openTime) {
        this.openTime = openTime;
    }

    public void setCloseTime(int closeTime) {
        this.closeTime = closeTime;
    }
    //////////////////////////////////////////////////////////////////////////

    public void setManager() {
        this.manager = true;
    }
}

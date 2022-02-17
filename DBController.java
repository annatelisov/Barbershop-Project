package DataBase;
import java.sql.*;
import java.util.Vector;
import Class.Program;
public class DBController {
    private static DBController INSTANCE = null;
    private Connection connection;
    private Statement statement;
    private ResultSet resultSet;

    private DBController() {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/barbershop", "root", "A311016o");
            statement = connection.createStatement();
            System.out.println("DB LOADED");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static DBController getInstance()//singleton object
    {
        if(INSTANCE == null)
            INSTANCE = new DBController();
        return INSTANCE;
    }
    public String quot(String name) {//String for adding quotes
        return "\"" + name + "\"";
    }
    //------------------------------------------------------------------------
    //USER DATABASE METHODS
    //GENDER 1 - man , 2 - woman , there are only two at the moment!.
    public boolean registerUser(String fName , String lName , int gender , String phone , String username , String password)
    {
        boolean done = true;
        if(phone.length() != 10) return false;
        try{
            statement.executeUpdate("INSERT INTO customers VALUES (" + quot(username) + ","
                    + quot(fName) + ","
                    + quot(lName) + ","
                    + gender + ","
                    + quot(phone) + ","
                    + quot(password) + ")");

        }catch (Exception e)
        {
            e.printStackTrace();
            done = false;
        }
        return done;
    }
    public boolean checkLoginData(String user , String password)
    {
        int found = 0;
        try {
            resultSet = statement.executeQuery("Select username from customers where username = " + quot(user)
                                                 + " and password = " + quot(password));
            while (resultSet.next()){//we check how many results we got if we got at least one (only can be one) then the password matches
                found++;
            }
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        System.out.println(user + " |:| " + password);
        if(found > 0) return true;
        return false;
    }
    //------------------------------------------------------------------------
    //------------------------------------------------------------------------
    //------------------------------------------------------------------------
    //MANAGER DATABASE METHODS

    //Check the password that was given to see if the user is a manager
    public boolean checkManager(String password)
    {
        String pass = "";
        try {
            resultSet = statement.executeQuery("SELECT * FROM manager");
            while (resultSet.next() == true)
            {
                pass = resultSet.getString("password");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        if(password.equals(pass)) return true;
        return false;
    }

    public void setPrices(double hairCut , double hairColor , double manPrice , double womanPrice , double hairDesign)
    {
        try
        {
            statement.executeUpdate("DELETE FROM prices");
            statement.executeUpdate("INSERT INTO prices VALUES (1," + hairCut + "," + hairColor + "," + hairDesign + ","
            + manPrice + "," + womanPrice + ")");
        }catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    //This function gets all the prices from the prices table which has at all time only one row.
    public double[] getPrices()
    {
        double prices[];
        prices = new double[5];
        try {
            resultSet = statement.executeQuery("select * from prices where id = 1");
            while (resultSet.next())
            {
                prices[0] = resultSet.getDouble("haircut");
                prices[1] = resultSet.getDouble("hairdye");
                prices[2] = resultSet.getDouble("hairstyle");
                prices[3] = resultSet.getDouble("men");
                prices[4] = resultSet.getDouble("women");
            }
        }catch(Exception e)
        {
            e.printStackTrace();
        }
        return prices;
    }

    //------------------------------------------------------------------------
    //------------------------------------------------------------------------
    //------------------------------------------------------------------------
    //APPOINTMENT DATABASE METHODS

    public Vector<int[]> getAppointments(String user)
    {
        Vector<int[]> appointments = new Vector<int[]>();

        try {
            //if we got "" as input then we return all of the appointments in the DB
            if(user.equals(""))
                resultSet = statement.executeQuery("SELECT * FROM appointment");
            else
                resultSet = statement.executeQuery("SELECT * FROM appointment WHERE username = " + quot(user));
            while(resultSet.next())
            {
                int[] arr = new int[5];
                arr[0] = resultSet.getInt("day");
                arr[1] = resultSet.getInt("time");
                arr[2] = resultSet.getInt("haircut");
                arr[3] = resultSet.getInt("hairdesign");
                arr[4] = resultSet.getInt("haircolor");
                appointments.add(arr);
            }

        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return appointments;
    }

    public void updateStartEndTimes(int start, int end)
    {
        try{
            statement.executeUpdate("UPDATE manager SET startTime = "+ start + " , endTime = " + end);
        }catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    public int[] getStartEndTime()
    {
        int[] toRet = new int[2];
        try{
            resultSet = statement.executeQuery("SELECT startTime,endTime From manager");
            while (resultSet.next())
            {
                toRet[0] = resultSet.getInt("startTime");
                toRet[1] = resultSet.getInt("endTime");
            }
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return toRet;
    }

    //this function returns all the times that can get a new appointment
    //depending on close and open times , and free hours
    public int[] getFreeAppoitmentsForDay(int day , int openTime , int closeTime)
    {
        Vector<Integer> time = new Vector<Integer>();
        try
        {
            int instances ;
           for(int j = openTime ; j < closeTime ; j++) {
               instances = 0;
               resultSet = statement.executeQuery("SELECT time FROM appointment WHERE day = " + day + " AND time = " + j);
               while (resultSet.next()) {
                   instances++;
               }
              // System.out.println("instances: " + instances + " Time: " + j + " Day: " + day);
               if (instances == 0) {
                   time.add(j);
               }
           }
        }catch(Exception e)
        {
            e.printStackTrace();
        }
        int arr[] = new int[time.size()];
        for(int i = 0; i < time.size() ; i++)
        {
            arr[i] = time.elementAt(i);
        }
        return arr;
    }

    public void addAppointment(String currentUser , boolean hairCut , boolean hairColor , boolean hairStyle, int day , int time)
    {
        int cutInt = hairCut ? 1 : 0;
        int colorInt = hairColor ? 1 : 0;
        int styleInt = hairStyle ? 1 : 0;
        try
        {
            statement.executeUpdate("INSERT INTO appointment VALUES (" + day + "," + time + "," + cutInt + "," + styleInt + "," +
                    colorInt + "," + quot(currentUser) + ")");
        }catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    public void removeAppointment(int day , int time)
    {
        try{
            statement.executeUpdate("DELETE FROM appointment WHERE day = " + day + " AND time = " + time);
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    //------------------------------------------------------------------------
    //-----------------------------CUSTOMERS----------------------------------
    //------------------------------------------------------------------------
    public int getGender(String username) //1- male , 2 - female
    {
        int gender = 0;
        try {
            resultSet = statement.executeQuery("SELECT gender FROM customers WHERE username = " + quot(username));
            while (resultSet.next())
            {
                gender = resultSet.getInt("gender");
            }
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return gender;
    }

    public String getUserAt(int day , int time)
    {
        String currentUser = "";
        try{
            resultSet = statement.executeQuery("Select username from appointment where day = "+ day + " and time = " + time);
            while (resultSet.next())
            {
                currentUser = resultSet.getString("username");
            }
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return currentUser;
    }
}

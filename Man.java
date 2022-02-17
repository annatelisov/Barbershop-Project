package Decorator;
import static Decorator.Prices.*;

public class Man extends AppointmentDecorator{
    public Man(Appointment newAppointment)
    {
        super(newAppointment);
    }

    @Override
    public String getType() {
        return tempAppointment.getType() + " , gender: man";
    }

    @Override
    public double getCost() {
        return super.getCost() + manApPrice;
    }
}


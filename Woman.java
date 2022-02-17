package Decorator;
import static Decorator.Prices.*;

public class Woman extends AppointmentDecorator{
    public Woman(Appointment newAppointment)
    {
        super(newAppointment);
    }

    @Override
    public String getType() {
        return tempAppointment.getType() + " , gender: woman";
    }

    @Override
    public double getCost() {
        return super.getCost() + womanApPrice;
    }
}


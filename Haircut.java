package Decorator;
import static Decorator.Prices.*;

public class Haircut extends AppointmentDecorator{
    public Haircut(Appointment newAppointment)
    {
        super(newAppointment);
    }

    @Override
    public String getType() {
        return tempAppointment.getType() + " , haircut";
    }

    @Override
    public double getCost() {
        return super.getCost() + haircutPrice;
    }
}

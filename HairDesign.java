package Decorator;
import static Decorator.Prices.*;

public class HairDesign extends AppointmentDecorator{
    public HairDesign(Appointment newAppointment)
    {
        super(newAppointment);
    }

    @Override
    public String getType() {
        return tempAppointment.getType() + " , hair design";
    }

    @Override
    public double getCost() {
        return super.getCost() + hairDesignPrice;
    }
}


package Decorator;

import static Decorator.Prices.*;

public class HairColor extends AppointmentDecorator{
    public HairColor(Appointment newAppointment)
    {
        super(newAppointment);
    }

    @Override
    public String getType() {
        return tempAppointment.getType() + " , hair dye";
    }

    @Override
    public double getCost() {
        return super.getCost() + hairDesignPrice;
    }
}


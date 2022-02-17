package Decorator;

public abstract class AppointmentDecorator implements Appointment{
    protected Appointment tempAppointment;

    AppointmentDecorator(Appointment newAppointment)
    {
        tempAppointment = newAppointment;
    }

    @Override
    public double getCost() {
        return tempAppointment.getCost();
    }

    @Override
    public String getType() {
        return tempAppointment.getType();
    }
}

package net.thumbtack.school.hospital.model;

public class DoctorAppointment {
    private String appointment;
    private String explanation;

    public DoctorAppointment(String appointment, String explanation) {
        setAppointment(appointment);
        setExplanation(explanation);
    }

    public String getAppointment() {
        return appointment;
    }

    public void setAppointment(String appointment) {
        this.appointment = appointment;
    }

    public String getExplanation() {
        return explanation;
    }

    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }
}

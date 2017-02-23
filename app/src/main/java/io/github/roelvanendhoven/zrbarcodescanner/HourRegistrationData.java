package io.github.roelvanendhoven.zrbarcodescanner;

/**
 * Created by roelvanendhoven on 2/23/17.
 */

public class HourRegistrationData {

    private String workPermit, employee;
    private int timeWorked;

    public HourRegistrationData() {
        this.workPermit = "";
        this.employee = "";
    }

    public String getWorkPermit() {
        return workPermit;
    }

    public void setWorkPermit(String workPermit) {
        this.workPermit = workPermit;
    }

    public String getEmployee() {
        return employee;
    }

    public void setEmployee(String employee) {
        this.employee = employee;
    }

    public int getTimeWorked() {
        return timeWorked;
    }

    public void setTimeWorked(int timeWorked) {
        this.timeWorked = timeWorked;
    }

    public void workTime(int timeWorked){
        this.timeWorked += timeWorked;
    }
}

package com.SunCycle.SunCycle.dto;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class MarketRequestDTO {

    private String recyclingMethod = "";
    private String retirementDate = "";
    private String city = "";
    private String state = "";

    private Map<String, Double> breakdown;

    // constructors
    public MarketRequestDTO() {
        this.breakdown = new HashMap<String, Double>();
    }

    public MarketRequestDTO(String recyclingMethod, String retirementDate, String city, String state, Map<String, Double> breakdown) {
        this.recyclingMethod = recyclingMethod;
        this.retirementDate = retirementDate;
        this.city = city;
        this.state = state;
        this.breakdown = breakdown;
    }

    // getters and setters
    public String getRecyclingMethod() {
        return recyclingMethod;
    }

    public void setRecyclingMethod(String recyclingMethod) {
        this.recyclingMethod = recyclingMethod;
    }

    public Date getRetirementDate() {
        try { return stringToDate(retirementDate); } catch (ParseException e) { return null; }
    }

    public void setRetirementDate(String retirementDate) {
        this.retirementDate = retirementDate;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Map<String, Double> getBreakdown() {
        return breakdown;
    }

    public void setBreakdown(Map<String, Double> breakdown) {
        this.breakdown = breakdown;
    }

    public double getPolymers() {
        if (breakdown.get("polymers") == null) {
            return 0.0;
        }
        return breakdown.get("polymers");
    }

    public void setPolymers(double polymers) {
        breakdown.put("polymers", polymers);
    }

    public double getSilicon() {
        if (breakdown.get("silicon") == null) {
            return 0.0;
        }
        return breakdown.get("silicon");
    }

    public void setSilicon(double silicon) {
        breakdown.put("silicon", silicon);
    }

    public double getCopper() {
        if (breakdown.get("copper") == null) {
            return 0.0;
        }
        return breakdown.get("copper");
    }

    public void setCopper(double copper) {
        breakdown.put("copper", copper);
    }

    public double getGlass() {
        if (breakdown.get("glass") == null) {
            return 0.0;
        }
        return breakdown.get("glass");
    }

    public void setGlass(double glass) {
        breakdown.put("glass", glass);
    }

    public double getSilver() {
        if (breakdown.get("silver") == null) {
            return 0.0;
        }
        return breakdown.get("silver");
    }

    public void setSilver(double silver) {
        breakdown.put("silver", silver);
    }

    public double getAluminium() {
        if (breakdown.get("silver") == null) {
            return 0.0;
        }
        return breakdown.get("aluminium");
    }

    public void setAluminium(double aluminium) {
        breakdown.put("aluminium", aluminium);
    }

    private Date stringToDate(String dateStr) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        return formatter.parse(dateStr);
    }

}

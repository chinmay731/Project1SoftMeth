package rosterpackage;

public enum Major {
    BAIT("33:136", "RBS"),
    CS("01:198", "SAS"),
    MATH("01:640", "SAS"),
    ITI("04:547", "SC&I"),
    EE("14:332", "SOE");

    private final String schoolNames;
    private final String majorCode;

    private Major(String majorCode, String schoolNames) {
        this.majorCode = majorCode;
        this.schoolNames = schoolNames;
    }

    public String getMajorCode() {
        return this.majorCode;
    }

    public String getSchoolNames() {
        return this.schoolNames;
    }

    public String getMajorName() {
        if (this.majorCode.equalsIgnoreCase("01:198")) {
            return "CS";
        } else if (this.majorCode.equalsIgnoreCase("01:640")) {
            return "MATH";
        } else if (this.majorCode.equalsIgnoreCase("14:332")) {
            return "EE";
        } else if (this.majorCode.equalsIgnoreCase("04:547")) {
            return "ITI";
        } else {
            return this.majorCode.equalsIgnoreCase("33:136") ? "BAIT" : null;
        }
    }
}

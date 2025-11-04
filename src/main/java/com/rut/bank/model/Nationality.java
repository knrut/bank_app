package com.rut.bank.model;

public enum Nationality {

    // --- Unia Europejska (UE) ---
    AUSTRIA("AT", "Austria"),
    BELGIUM("BE", "Belgium"),
    BULGARIA("BG", "Bulgaria"),
    CROATIA("HR", "Croatia"),
    CYPRUS("CY", "Cyprus"),
    CZECH_REPUBLIC("CZ", "Czech Republic"),
    DENMARK("DK", "Denmark"),
    ESTONIA("EE", "Estonia"),
    FINLAND("FI", "Finland"),
    FRANCE("FR", "France"),
    GERMANY("DE", "Germany"),
    GREECE("GR", "Greece"),
    HUNGARY("HU", "Hungary"),
    IRELAND("IE", "Ireland"),
    ITALY("IT", "Italy"),
    LATVIA("LV", "Latvia"),
    LITHUANIA("LT", "Lithuania"),
    LUXEMBOURG("LU", "Luxembourg"),
    MALTA("MT", "Malta"),
    NETHERLANDS("NL", "Netherlands"),
    POLAND("PL", "Poland"),
    PORTUGAL("PT", "Portugal"),
    ROMANIA("RO", "Romania"),
    SLOVAKIA("SK", "Slovakia"),
    SLOVENIA("SI", "Slovenia"),
    SPAIN("ES", "Spain"),
    SWEDEN("SE", "Sweden"),

    // --- Dodatkowe kraje spoza UE ---
    SWITZERLAND("CH", "Switzerland"),
    UNITED_KINGDOM("UK", "United Kingdom"),
    NORWAY("NO", "Norway"),
    UNITED_STATES("US", "United States of America");

    private final String countryCode;
    private final String displayName;

    Nationality(String countryCode, String displayName) {
        this.countryCode = countryCode;
        this.displayName = displayName;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public String getDisplayName() {
        return displayName;
    }

    @Override
    public String toString() {
        return displayName;
    }
}

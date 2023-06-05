package de.world;

public abstract class AbstractMaterial {

    private final Properties properties;

    public AbstractMaterial(Properties properties) {
        this.properties = properties;
    }

    public double getLinearExpansionCoefficient() {
        return this.properties.alpha;
    }

    public double getVolumeExpansionCoefficient() {
        return this.properties.gamma;
    }

    public double getSpecificHeatCapacity() {
        return this.properties.c;
    }

    public double getSpecificElectricalResistor() {
        return this.properties.p;
    }

    public double getSpecificMeltingEnergy() {
        return this.properties.wS;
    }

    public double getSpecificBoilingEnergy() {
        return this.properties.wV;
    }

    public double getSpecificMeltingPoint() {
        return this.properties.meltingPoint;
    }

    public double getSpecificBoilingPoint() {
        return this.properties.boilingPoint;
    }

    public static class Properties {
        public static final double c0 = 2.99792458e8; // (m/s) The Speed of Light in the Vacuum
        public static final double g = 9.81; // (m/s²) For a latitude of 45° north, the following applies approximately on earth: g=9.81m/s².
        public static final double e = 1.602177e-19; // (C) Elementarladung

        private double alpha; // (1/°C) Längenausdehnungskoeffizient
        private double gamma; // (1/°C) Volumenausdehnungskoeffizient bei 20°C
        private double c; // (kJ/(kg * °C)) Spezifische Wärmekapazität bei 20°C
        private double p; // (Ohm * mm²/m) Spezifische Widerstand
        private double wS; // (kJ/kg) Spezifische Schmelzenergie
        private double wV; // (kJ/kg) Spezifische Verdampfungsenergie
        private double meltingPoint; // (°C) Schmelztemperatur
        private double boilingPoint; // (°C) Siedetemperatur

        public Properties() {
        }

        public Properties setLinearExpansionCoefficient(double alpha) {
            this.alpha = alpha;
            return this;
        }

        public Properties setVolumeExpansionCoefficient(double gamma) {
            this.gamma = gamma;
            return this;
        }

        public Properties setSpecificHeatCapacity(double heatCapacity) {
            this.c = heatCapacity;
            return this;
        }

        public Properties setSpecificElectricalResistor(double resist) {
            this.p = resist;
            return this;
        }

        public Properties setSpecificMeltingEnergy(double energy) {
            this.wS = energy;
            return this;
        }

        public Properties setSpecificBoilingEnergy(double energy) {
            this.wV = energy;
            return this;
        }

        public Properties setSpecificMeltingPoint(double temperature) {
            this.meltingPoint = temperature;
            return this;
        }

        public Properties setSpecificBoilingPoint(double temperature) {
            this.boilingPoint = temperature;
            return this;
        }
    }
}

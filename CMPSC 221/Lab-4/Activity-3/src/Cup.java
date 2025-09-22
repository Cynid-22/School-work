class Cup {
    protected String material;
    protected boolean isMicrowavable, isClear, isHotDrink;

    public void substance() {
        System.out.println("Material: "+ material);
    }

    public void drinkHotTea() {
        if (isHotDrink) {
            System.out.println("Drinking hot tea from this cup.");
        } else {
            System.out.println("Cannot drink hot tea from this cup!");
        }
    }

    public void showTransparency() {
        if (isClear) {
            System.out.println("You can see through the cup.");
        } else {
            System.out.println("This cup is not transparent.");
        }
    }

    public void heatInMicrowave() {
        if (isMicrowavable) {
            System.out.println("Heating cup in microwave...");
        } else {
            System.out.println("This cup cannot be heated in the microwave.");
        }
    }
}

class PaperCup extends Cup {
    public PaperCup() {
        material = "Paper";
        isMicrowavable = true;
        isClear = false;
        isHotDrink = true;
    }
}

class GlassCup extends Cup {
    public GlassCup() {
        material = "Glass";
        isMicrowavable = true;
        isClear = true;
        isHotDrink = false;
    }
}

class CeramicCup extends Cup {
    public CeramicCup() {
        material = "Ceramic";
        isMicrowavable = false;
        isClear = false;
        isHotDrink = true;
    }
}

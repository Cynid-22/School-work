public class tempClasification {
    public String weather(int temp) {
        if (temp < 0)
            return "FREEZING";
        else if (temp <= 15)
            return "COLD";
        else if (temp <= 30)
            return "WARM";
        else if (temp <= 40)
            return "HOT";
        else
            return "VERY HOT";
    }
}
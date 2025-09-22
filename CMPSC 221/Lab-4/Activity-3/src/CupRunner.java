public class CupRunner {
    public static void main(String[] args) {

        PaperCup paperCup = new PaperCup();
        paperCup.substance();
        paperCup.drinkHotTea();
        paperCup.showTransparency();
        paperCup.heatInMicrowave();

        System.out.println();

        GlassCup glassCup = new GlassCup();
        glassCup.substance();
        glassCup.drinkHotTea();
        glassCup.showTransparency();
        glassCup.heatInMicrowave();

        System.out.println();

        CeramicCup ceramicCup = new CeramicCup();
        ceramicCup.substance();
        ceramicCup.drinkHotTea();
        ceramicCup.showTransparency();
        ceramicCup.heatInMicrowave();
    }
}

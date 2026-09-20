package lw01.prelab;

public class ColourPrint extends PrintJob {
    private static final int FIRST_PAGES_LIMIT = 10;
    private static final int RATE_FIRST_PAGES = 1500;
    private static final int RATE_EXTRA_PAGES = 1000;
    private static final int SETUP_FEE = 2000;

    public ColourPrint(String id, int pages) {
        super(id, pages);
    }

    @Override
    public String label() {
        return "Colour";
    }

    @Override
    public int calculateCharge() {
        int pages = getPages();
        int firstPages = Math.min(pages, FIRST_PAGES_LIMIT);
        int extraPages = Math.max(pages - FIRST_PAGES_LIMIT, 0);
        return firstPages * RATE_FIRST_PAGES + extraPages * RATE_EXTRA_PAGES + SETUP_FEE;
    }
}
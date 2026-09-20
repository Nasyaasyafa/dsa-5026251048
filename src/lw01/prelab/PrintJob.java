package lw01.prelab;

public abstract class PrintJob implements Chargeable {
    private final String id;
    private final int pages;

    public PrintJob(String id, int pages) {
        if (pages <= 0) {
            throw new IllegalArgumentException("Pages must be positive");
        }
        this.id = id;
        this.pages = pages;
    }

    public String getId() {
        return id;
    }

    public int getPages() {
        return pages;
    }

    public String label() {
        return "Print";
    }

    @Override
    public abstract int calculateCharge();

    public int calculateCharge(int copies) {
        if (copies <= 0) {
            throw new IllegalArgumentException("Copies must be positive");
        }
        return copies * calculateCharge();
    }

    public final String summary() {
        return id + " | " + label() + " | " + calculateCharge();
    }
}
import entities.BookEntity;

public class Main {
    public static void main(String[] args) {
        DbWorker dbWorker = new DbWorker();
        for (BookEntity book : dbWorker.getAllBooks()) {
            System.out.println(book);
        }
    }
}

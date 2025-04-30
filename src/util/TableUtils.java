package util;

import java.util.List;

import Model.TableConvertible;
// sao không lên
public class TableUtils {
    public static void printTable(List<? extends TableConvertible> objects, String[] headers) {
        if (objects == null || objects.isEmpty()) {
            System.out.println("Không có dữ liệu để hiển thị.");
            return;
        }

        int columnCount = headers.length;
        int[] colWidths = new int[columnCount];

        for (int i = 0; i < columnCount; i++) {
            colWidths[i] = headers[i].length();
        }

        for (TableConvertible obj : objects) {
            String[] row = obj.toRow();
            for (int i = 0; i < columnCount; i++) {
                if (row[i] != null)
                    colWidths[i] = Math.max(colWidths[i], row[i].length());
            }
        }

        printLine(colWidths, '╔', '╦', '╗');
        printRow(headers, colWidths);
        printLine(colWidths, '╠', '╬', '╣');

        for (TableConvertible obj : objects) {
            printRow(obj.toRow(), colWidths);
        }

        printLine(colWidths, '╚', '╩', '╝');
    }

    private static void printLine(int[] colWidths, char left, char mid, char right) {
        System.out.print(left);
        for (int i = 0; i < colWidths.length; i++) {
            System.out.print("═".repeat(colWidths[i] + 2));
            if (i < colWidths.length - 1) {
                System.out.print(mid);
            }
        }
        System.out.println(right);
    }

    private static void printRow(String[] row, int[] colWidths) {
        System.out.print("║");
        for (int i = 0; i < colWidths.length; i++) {
            String cell = row[i] != null ? row[i] : "";
            System.out.printf(" %-" + colWidths[i] + "s ║", cell);
        }
        System.out.println();
    }
}
package ru.alexeylisyutenko.cormen.chapter8.columnsort;

public class ColumnSort {

    public static void sort(int[] array, int r, int s, boolean printSteps) {
        // Create mesh.
        DefaultMesh mesh = new DefaultMesh(array, r, s);
        if (printSteps) {
            System.out.println("Original mesh: ");
            mesh.printAsMatrix();
            System.out.println();
        }

        // Step 1. Sort each column.
        sortAllColumns(mesh);
        if (printSteps) {
            System.out.println("Step 1. Sort each column.");
            mesh.printAsMatrix();
            System.out.println();
        }

        // Step 2. Transpose the array.
        mesh.setState(MeshState.TRANSPOSED);
        if (printSteps) {
            System.out.println("Step 2. Transpose the array.");
            mesh.printAsMatrix();
            System.out.println();
        }

        // Step 3. Sort each column.
        sortAllColumns(mesh);
        if (printSteps) {
            System.out.println("Step 3. Sort each column.");
            mesh.printAsMatrix();
            System.out.println();
        }

        // Step 4. Perform inverse of transpose.
        mesh.setState(MeshState.NORMAL);
        if (printSteps) {
            System.out.println("Step 4. Perform inverse of transpose.");
            mesh.printAsMatrix();
            System.out.println();
        }

        // Step 5. Sort each column.
        sortAllColumns(mesh);
        if (printSteps) {
            System.out.println("Step 5. Sort each column.");
            mesh.printAsMatrix();
            System.out.println();
        }

        // Step 6. Shift the mesh.
        mesh.setState(MeshState.SHIFTED);
        if (printSteps) {
            System.out.println("Step 6. Shift the mesh.");
            mesh.printAsMatrix();
            System.out.println();
        }

        // Step 7. Sort each column.
        sortAllColumns(mesh);
        if (printSteps) {
            System.out.println("Step 7. Sort each column.");
            mesh.printAsMatrix();
            System.out.println();
        }

        // Step 8. Shift the mesh back.
        mesh.setState(MeshState.NORMAL);
        if (printSteps) {
            System.out.println("Step 7. Shift the mesh back.");
            mesh.printAsMatrix();
            System.out.println();
        }
    }

    private static void sortAllColumns(Mesh mesh) {
        for (int i = 0; i < mesh.getColumns(); i++) {
            sortColumn(mesh, i);
        }
    }

    private static void sortColumn(Mesh mesh, int column) {
        for (int i = 1; i < mesh.getRows(); i++) {
            int current = mesh.getCellValue(i, column);
            int j = i - 1;
            while (j >= 0 && current < mesh.getCellValue(j, column)) {
                mesh.setCellValue(j + 1, column, mesh.getCellValue(j, column));
                j--;
            }
            mesh.setCellValue(j + 1, column, current);
        }
    }


}

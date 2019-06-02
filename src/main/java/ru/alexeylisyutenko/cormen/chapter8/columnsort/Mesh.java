package ru.alexeylisyutenko.cormen.chapter8.columnsort;

public interface Mesh {

     MeshState getState();

     void setState(MeshState state);

     int getCellValue(int row, int column);

     void setCellValue(int row, int column, int value);

     void printAsMatrix();

     int[] getData();

     int getRows();

     int getColumns();

}

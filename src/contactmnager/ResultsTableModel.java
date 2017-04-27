/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package contactmnager;
import javax.swing.table.*;
import java.sql.*;
import java.util.*;

/**
 *
 * @author geoffreytrenard
 */
public class ResultsTableModel extends DefaultTableModel {
    public ResultsTableModel (ResultSet rs) throws Exception{
        super();
        ResultSetMetaData rsmd = rs.getMetaData();
        int colCount = rsmd.getColumnCount();
        Vector columnNames = new Vector(colCount);
        for(int i=1; i<=colCount; i++){
            columnNames.addElement(rsmd.getColumnName(i));
        }
        Vector tableData = new Vector();
        Vector rowData;
        while(rs.next()){
            rowData = new Vector(colCount);
            for(int i=1; i<=colCount; i++){
                rowData.addElement(rs.getString(i));
            }
            tableData.addElement(rowData);
        }
        setDataVector(tableData, columnNames);
    }
}

package com.bloomberg.fx.deals.reader;

import com.bloomberg.fx.deals.model.Deal;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;


public class DataReader {
    public static String TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
    static String SHEET = "DealsManifest";

    public static boolean checkExcelFormat(MultipartFile file){
        if(!TYPE.equals(file.getContentType())){
            return false;
        }
        return true;
    }

    public static List<Deal> readExcelFile(InputStream is){
        try{
            Workbook workbook = new XSSFWorkbook(is);
            Sheet sheet = workbook.getSheet(SHEET);
            Iterator<Row> rows = sheet.iterator();
            List<Deal> deals = new ArrayList<>();
            int rowNumber = 0;
            while (rows.hasNext()) {
                Row currentRow = rows.next();
                // skip header
                if (rowNumber == 0) {
                    rowNumber++;
                    continue;
                }
                Iterator<Cell> cellsInRow = currentRow.iterator();
                Deal deal = new Deal();
                int cellIdx = 0;
                while (cellsInRow.hasNext()) {
                    Cell currentCell = cellsInRow.next();
                    switch (cellIdx) {
                        case 0:
                            deal.setDeal_id(currentCell.getStringCellValue());
                            break;
                        case 1:
                            deal.setFrom_currency(currentCell.getStringCellValue());
                            break;
                        case 2:
                            deal.setTo_currency(currentCell.getStringCellValue());
                            break;
                        case 3:
                            deal.setAmount((float)currentCell.getNumericCellValue());
                            break;
                        case 4:
                            String dealDateStr = currentCell.getStringCellValue();
                            SimpleDateFormat datetimeFormatter = new SimpleDateFormat(
                                    "dd-MM-yyyy HH:mm:ss");
                            Date dealDate = datetimeFormatter.parse(dealDateStr);
                            Timestamp dealDateDb = new Timestamp(dealDate.getTime());
                            deal.setDeal_date(dealDateDb);
                            break;
                        default:
                            break;
                    }
                    cellIdx++;
                }
                deals.add(deal);
            }
            workbook.close();
            return deals;
        }catch(IOException ioe){
            throw new RuntimeException("failed to read Excel file: " + ioe.getMessage());
        }catch(ParseException pe){
            throw new RuntimeException("failed to parse Date: " + pe.getMessage());
        }
    }
}

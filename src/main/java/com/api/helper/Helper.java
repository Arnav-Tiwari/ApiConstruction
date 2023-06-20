package com.api.helper;

import com.api.entity.Product;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Helper {


    //check that file is of excel type or not
    public static boolean checkExcelFormat(MultipartFile file) {

        String contentType = file.getContentType();

        if (contentType.equals("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")) {
            return true;
        } else {
            return false;
        }
//        return contentType.equals("text/csv");
    }


    //convert excel to list of products
    public static List<Product> convertExcelToListOfProduct(InputStream is){
        List<Product> list = new ArrayList<>();
        System.out.println("INPUT STREAM IS THIS "+is);
        try {


            XSSFWorkbook workbook = new XSSFWorkbook(is);

            XSSFSheet sheet = workbook.getSheet("data");

            System.out.println("SHEET IS SOMEHTING "+sheet);
            int rowNumber = 0;
            Iterator<Row> iterator = sheet.iterator();

            while (iterator.hasNext()) {
                Row row = iterator.next();

                if (rowNumber == 0) {
                    rowNumber++;
                    continue;
                }

                Iterator<Cell> cells = row.iterator();

                int cid = 0;

                Product p = new Product();

                while (cells.hasNext()) {
                    Cell cell = cells.next();

                    switch (cid) {
                        case 0:
                            p.setProductId((int) cell.getNumericCellValue());
                            break;
                        case 1:
                            p.setDate(cell.getStringCellValue());
                            break;
                        case 2:
                            p.setUserName(cell.getStringCellValue());
                            break;
                        case 3:
                            p.setDepartment(cell.getStringCellValue());
                            break;
                        case 4:
                            p.setSoftware(cell.getStringCellValue());
                            break;
                        case 5:
                            p.setSeats((int)cell.getNumericCellValue());
                            break;
                        case 6:
                            p.setAmount((int)cell.getNumericCellValue());
                        default:
                            break;
                    }
                    cid++;

                }

                list.add(p);
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;

    }

    public static List<Product> processExcelFile(MultipartFile file) throws IOException, InvalidFormatException {
        List<Product> list = new ArrayList<>();
        try (InputStream inputStream = file.getInputStream()) {
            Workbook workbook = WorkbookFactory.create(inputStream);
            Sheet sheet = workbook.getSheetAt(0);
            int row_index=0;
            for (Row row : sheet) {
                Product p = new Product();
                if(row_index==0){
                    row_index++;
                    continue;
                }
                p.setProductId((int) row.getCell(0).getNumericCellValue());
                p.setDate(row.getCell(1).getStringCellValue());
                p.setUserName(row.getCell(2).getStringCellValue());
                p.setDepartment(row.getCell(3).getStringCellValue());
                p.setSoftware(row.getCell(4).getStringCellValue());
                p.setSeats((int)row.getCell(5).getNumericCellValue());
                p.setAmount((int)row.getCell(6).getNumericCellValue());
                // Set other fields accordingly

                list.add(p);
            }

            workbook.close();
            return list;
        }
    }
}


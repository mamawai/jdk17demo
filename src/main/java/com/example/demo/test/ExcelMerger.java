package com.example.demo.test;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

public class ExcelMerger {

    public static void main(String[] args) throws IOException {
        try {
            String inputFile = "C:\\Users\\mamingyang\\Desktop\\PSS备用系统建设项目过程资产文件检查结果_2024demo.xlsx";
            String outputFile = "C:\\Users\\mamingyang\\Desktop\\file.xlsx";

            FileInputStream fis = new FileInputStream(inputFile);
            Workbook workbook = new XSSFWorkbook(fis);
            Sheet sheet = workbook.getSheet("检查结果");

            // Read the data
            Map<String, List<String>> dataMap = new HashMap<>();
            int rows = sheet.getPhysicalNumberOfRows();
            for (int i = 1; i < rows; i++) {
                Row row = sheet.getRow(i);
                if (row == null) continue;

                Cell linkCell = row.getCell(1);
                Cell resultCell = row.getCell(3);
                if (linkCell == null || resultCell == null) continue;

                String link = linkCell.getStringCellValue().trim();
                String result = resultCell.getStringCellValue().trim();
                if (result.equals("检查通过！")) {
                    continue;
                }
                dataMap.putIfAbsent(link, new ArrayList<>());
                dataMap.get(link).add(result);
            }

            // Create a new workbook and sheet
            Workbook newWorkbook = new XSSFWorkbook();
            Sheet newSheet = newWorkbook.createSheet("检查结果");

            // Write the header
            Row header = newSheet.createRow(0);
            header.createCell(0).setCellValue("文档链接");
            header.createCell(1).setCellValue("检查结果");

            // Write the merged data
            int rowIndex = 1;
            for (Map.Entry<String, List<String>> entry : dataMap.entrySet()) {
                Row newRow = newSheet.createRow(rowIndex++);
                newRow.createCell(0).setCellValue(entry.getKey());
                int i = 1;
                for (String value : entry.getValue()) {
                    newRow.createCell(i++).setCellValue(value);
                }
            }

            // Write the new workbook to the file system
            FileOutputStream fos = new FileOutputStream(outputFile);
            newWorkbook.write(fos);
            fos.close();

            // Close the workbooks
            fis.close();
            workbook.close();
            newWorkbook.close();
        } catch (IOException e) {
            System.out.println("有异常");
            e.printStackTrace();
        }
    }
}

package com.example.demo.test;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.FileInputStream;
import java.io.IOException;

public class calculateRatio {
    public static void main(String[] args) throws IOException {
        try {
            // 文件路径
            String inputFile = "C:\\Users\\mamingyang\\Desktop\\SONAR详情信息_预订系统开发部_LPRS V4 (2).xlsx";

            FileInputStream fis = new FileInputStream(inputFile);
            Workbook workbook = new HSSFWorkbook(fis);
            Sheet sheet = workbook.getSheet("sheet1");

            long totalLength = 0;
            double percentSum = 0.0;
            double totalPercent = 0.0;

            int rows = sheet.getPhysicalNumberOfRows();
            for (int i = 1; i < rows; i++) {
                Row row = sheet.getRow(i);
                if (row == null) continue;

                Cell lengthCell = row.getCell(8);
                Cell percentCell = row.getCell(17);
                if (lengthCell == null || percentCell == null) continue;

                int perLength = (int) lengthCell.getNumericCellValue();
                double percentValue = percentCell.getNumericCellValue();

                totalLength += perLength;
                totalPercent += percentValue;
                double per = perLength * percentValue;
                percentSum += per;
            }
            System.out.println("总长度：" + totalLength + "，sonar总覆盖行数：" + percentSum);
            System.out.println("平均覆盖率：" + (percentSum / totalLength) * 100 + "%");
            System.out.println("平均覆盖率只计算百分比：" + totalPercent / (rows - 1) * 100 + "%");
            // Close the workbooks
            fis.close();
            workbook.close();
        } catch (Exception e) {
            System.out.println("有异常");
            e.printStackTrace();
        }
    }
}

package cn.jeefast.system.util;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.RichTextString;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

public class ExcelUtil {


    /**
     * read.xlsx content
     * @param file
     * @return
     * @throws
     */
    public static List<ArrayList<String>> readXlsx (MultipartFile file) {
        List<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
        InputStream input = null;
        XSSFWorkbook wb = null;
        try {
            input = file.getInputStream();
            wb = new XSSFWorkbook(input);
            ArrayList<String> rowList = null;
            int totoalRows = 0;//Total number of rows
            int totalCells = 0;//Total number of columns
            for (int sheetIndex = 0 ; sheetIndex < wb.getNumberOfSheets(); sheetIndex++) {
                XSSFSheet xssfSheet = wb.getSheetAt(sheetIndex);

                if (xssfSheet == null) {
                    continue;
                }
                totoalRows = xssfSheet.getLastRowNum();
                //readrow
                for (int rowIndex = 0; rowIndex <= totoalRows; rowIndex++) {
                    XSSFRow xssfRow = xssfSheet.getRow(rowIndex);

                    if (xssfRow == null) {
                        continue;
                    }
                    rowList = new ArrayList<String>();
                    totalCells = xssfRow.getLastCellNum();

                    //read列
                    for (int cellIndex = 0; cellIndex < totalCells; cellIndex++) {
                        XSSFCell xssfCell = xssfRow.getCell(cellIndex);
                        if (xssfCell == null) {
                            rowList.add("");
                        } else {
                            xssfCell.setCellType(CellType.STRING);
                            rowList.add(String.valueOf(xssfCell.getStringCellValue()));
                        }
                    }

                    list.add(rowList);

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                if ( wb != null) {
                    wb.close();
                }
                if (input != null) {
                    input.close();
                }
            } catch (Exception e) {
            }
        }

        return list;
    }

    /**
     * read .xlscontent
     * @param file
     * @return
     * @throws
     */
    public static List<ArrayList<String>> readXls (MultipartFile file)  {
        List<ArrayList<String>> list = new ArrayList<ArrayList<String>>();

        InputStream input = null;
        HSSFWorkbook wb = null;

        try {
            input = file.getInputStream();
            wb = new HSSFWorkbook(input);

            ArrayList<String> rowList = null;
            int totoalRows = 0;//Total number of rows
            int totalCells = 0;//Total number of columns
            for (int sheetIndex = 0 ; sheetIndex < wb.getNumberOfSheets(); sheetIndex++) {
                HSSFSheet hssfSheet = wb.getSheetAt(sheetIndex);

                if (hssfSheet == null) {
                    continue;
                }

                totoalRows = hssfSheet.getLastRowNum();
                //readrow
                for (int rowIndex = 0; rowIndex <= totoalRows; rowIndex++) {
                    HSSFRow hssfRow = hssfSheet.getRow(rowIndex);

                    if (hssfRow == null) {
                        continue;
                    }
                    rowList = new ArrayList<String>();
                    totalCells = hssfRow.getLastCellNum();

                    //read列
                    for (int cellIndex = 0; cellIndex < totalCells; cellIndex++) {
                        HSSFCell hssfCell = hssfRow.getCell(cellIndex);
                        if (hssfCell == null) {
                            rowList.add("");
                        } else {
                            hssfCell.setCellType(CellType.STRING);
                            rowList.add(String.valueOf(hssfCell.getStringCellValue()));
                        }
                    }

                    list.add(rowList);

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                if ( wb != null) {
                    wb.close();
                }
                if (input != null) {
                    input.close();
                }
            } catch (Exception e) {
            }
        }
        return list;
    }



    /**
     * getfiletype
     * @param path
     * @return
     */
    public static String getPostfix (String path) {
        if (StringUtils.isBlank(path) || !path.contains(".")) {
            return null;
        }
        return path.substring(path.lastIndexOf(".") + 1, path.length()).trim();
    }
    
    
    public static Workbook getExcelWorkBook(String[] headers, List<Map<String, Object>>list, boolean useXSSF, String[] includeAttr) {
		
		Workbook workbook = useXSSF ? new XSSFWorkbook() : new HSSFWorkbook();
        Sheet sheet = workbook.createSheet("Sheet1");
        createHeader(headers, sheet, useXSSF);
        CellStyle cellStyle = workbook.createCellStyle();
        // determine
        for (int i = 0; i < list.size(); i++) {
        	Map<String, Object> field = list.get(i);
            Row dataRow = sheet.createRow(i + 1);
            
            for (int j = 0, k = 0; j < includeAttr.length - 1; j++) {
            	Cell dataCell = dataRow.createCell(j - k);
            	String key = includeAttr[j];
            	setCellValuetwo(workbook, dataCell, field.get(key), cellStyle);
            }
            
        }
        
		return workbook;
	}
    
    private static void createHeader(String[] headers, Sheet sheet, boolean b) {
		Row headerRow = sheet.createRow(0);
        for (int i = 0; i < headers.length; i++) {
            Cell headerRowCell = headerRow.createCell(i);
            RichTextString text = b ? new XSSFRichTextString(headers[i]) : new HSSFRichTextString(headers[i]);
            headerRowCell.setCellValue(text);
        }
	}
    

    /**
     * @param workbook
     * @param dataCell
     * @param value
     */
    private static void setCellValuetwo(Workbook workbook, Cell dataCell, Object value,CellStyle cellStyle) {
    	if (value == null) {
    		 dataCell.setCellStyle(cellStyle);
    		 dataCell.setCellValue("");
    	 } else {
    		 dataCell.setCellStyle(cellStyle);
    		 dataCell.setCellValue(value.toString());
    	 }
    }
   
}

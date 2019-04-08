package io.nakong.common.excel;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellReference;
import org.jxls.reader.BeanCellMapping;
import org.jxls.reader.ReaderConfig;
import org.jxls.reader.SimpleBlockReaderImpl;
import org.jxls.reader.XLSDataReadException;
import org.jxls.reader.XLSReadMessage;
import org.jxls.reader.XLSRowCursor;

/**
 * @author Leonid Vysochyn
 */
public class MySimpleBlockReaderImpl extends SimpleBlockReaderImpl {
	protected final Log log = LogFactory.getLog(getClass());
    XLSReadStatus readStatus = new XLSReadStatus();

	public MySimpleBlockReaderImpl() {
	}

	public MySimpleBlockReaderImpl(int startRow, int endRow, List beanCellMappings) {
		super(startRow, endRow, beanCellMappings);
	}

	public MySimpleBlockReaderImpl(int startRow, int endRow) {
		super(startRow, endRow);
	}

	public XLSReadStatus read(XLSRowCursor cursor, Map beans) {
		readStatus.clear();
		final int currentRowNum = cursor.getCurrentRowNum();
		final int rowShift = currentRowNum - getStartRow();
		BeanCellMapping mapping;
		ReaderConfig.getInstance().setUseDefaultValuesForPrimitiveTypes( true );
		for (Iterator iterator = getMappings().iterator(); iterator.hasNext();) {
			mapping = (BeanCellMapping) iterator.next();
			try {
				String dataString = readCellString(cursor.getSheet(), mapping.getRow() + rowShift, mapping.getCol());
				mapping.populateBean(dataString, beans);
				
			} catch (Exception e) {
				String message = "Can't read cell " + getCellName(mapping, rowShift) + " on " + cursor.getSheetName()
						+ " spreadsheet";
				readStatus.addMessage(new XLSReadMessage(message, e));
				if (ReaderConfig.getInstance().isSkipErrors()) {
					if (log.isWarnEnabled()) {
						log.warn(message);
					}
				} else {
					readStatus.setStatusOK(false);
					throw new XLSDataReadException(getCellName(mapping, rowShift), "Can't read cell "
							+ getCellName(mapping, rowShift) + " on " + cursor.getSheetName() + " spreadsheet",
							readStatus, e);
				}
			}
		}
		cursor.setCurrentRowNum(getEndRow() + rowShift);
		return readStatus;
	}

	public String readCellString(Sheet sheet, int rowNum, short cellNum) {
		Cell cell = getCell(sheet, rowNum, cellNum);
		return getCellString(cell);
	}

	public String getCellString(Cell cell) {
		String dataString = null;
		if (cell != null) {
			switch (cell.getCellType()) {
			case Cell.CELL_TYPE_STRING:
				dataString = cell.getRichStringCellValue().getString();
				break;
			case Cell.CELL_TYPE_NUMERIC:
				
				if (HSSFDateUtil.isCellDateFormatted(cell)) {
					Date d = cell.getDateCellValue();
					DateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
					dataString = formater.format(d);
				} else {
					dataString = readNumericCell(cell);
				}				
				break;
			case Cell.CELL_TYPE_BOOLEAN:
				dataString = Boolean.toString(cell.getBooleanCellValue());
				break;
			case Cell.CELL_TYPE_BLANK:
				break;
			case Cell.CELL_TYPE_ERROR:
				break;
			case Cell.CELL_TYPE_FORMULA:
				// attempt to read formula cell as numeric cell
				try {
					dataString = readNumericCell(cell);
				} catch (Exception e1) {
					log.info("Failed to read formula cell as numeric. Next to try as string. Cell=" + cell.toString());
					try {
						dataString = cell.getRichStringCellValue().getString();
						log.info("Successfully read formula cell as string. Value=" + dataString);
					} catch (Exception e2) {
						log.warn("Failed to read formula cell as numeric or string. Cell=" + cell.toString());
					}
				}

				break;
			default:
				break;
			}
		}
		return dataString;
	}

	public String readNumericCell(Cell cell) {
		double value;
		String dataString = null;
		value = cell.getNumericCellValue();
		double sVal = value % 1.0;
		if (sVal == 0) {
			dataString = new DecimalFormat("0").format(value);
			// dataString = Integer.toString((int) value);
		} else {
			dataString = new DecimalFormat("0.00").format(value);
			// dataString = Double.toString(cell.getNumericCellValue());
		}
		return dataString;
	}

	public String getCellName(BeanCellMapping mapping, int rowShift) {
		CellReference currentCellRef = new CellReference(mapping.getRow() + rowShift, mapping.getCol(), false, false);
		return currentCellRef.formatAsString();
	}

	public Cell getCell(Sheet sheet, int rowNum, int cellNum) {
		Row row = sheet.getRow(rowNum);
		if (row == null) {
			return null;
		}
		return row.getCell(cellNum);
	}

}
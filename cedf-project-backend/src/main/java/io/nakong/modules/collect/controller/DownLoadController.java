package io.nakong.modules.collect.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.Request;
import javax.xml.ws.Response;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("collect/download")
public class DownLoadController {

	
	
  @RequestMapping("/downloadAirCost")
  public ResponseEntity<FileSystemResource> downloadTemplate(Request request ,Response response) {
  	
  	String xmlConfig = "excel/aircost-example.xls";
  	File file = null;
		try {
			file = new ClassPathResource(xmlConfig).getFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
      if (file == null) {
          return null;
      }
      HttpHeaders headers = new HttpHeaders();
      headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
      try {
			headers.add("Content-Disposition", "attachment; filename=" + java.net.URLEncoder.encode("空气成本.xls", "UTF-8"));
		} catch (UnsupportedEncodingException e) {
			headers.add("Content-Disposition", "attachment; filename=空气成本.xls");
		}
      headers.add("Pragma", "no-cache");
      headers.add("Expires", "0");
      
      
      
      return ResponseEntity
              .ok()
              .headers(headers)
              .contentLength(file.length())
              .contentType(MediaType.parseMediaType("application/octet-stream"))
              .body(new FileSystemResource(file));
      
    
  }
  
  
  @RequestMapping("/downloadTemplate")
//@RequiresPermissions("project:summary:downloadTemplate")
public ResponseEntity<FileSystemResource> downloadTemplate(String type) {
	  
	 String xmlConfig = "";
	 String excelName = "";// *.xls
	  
	 switch(type) {
	 case "1":
		 xmlConfig = "excel/aircost-example.xls";
		 excelName = "空气成本.xls";
		 break;
	 case "2":
		 xmlConfig = "excel/press-example.xls";
		 excelName = "压力曲线.xls";
		 break;
	 case "3":
		 xmlConfig = "excel/pipe-example.xls";
		 excelName = "管道流量.xls";
		 break;
	 case "4":
		 xmlConfig = "excel/power-example.xls";
		 excelName = "耗电量.xls";
		 break;
	 case "5":
		 xmlConfig =  "excel/yaliloudian-example.xls";
		 excelName = "压力露点.xls";
		 break;
	 case "6":
		 xmlConfig = "excel/temp-example.xls";
		 excelName = "温度曲线.xls";
		 break;
	 }
	
	 
	File file = null;
	try {
		file = new ClassPathResource(xmlConfig).getFile();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    if (file == null) {
        return null;
    }
    HttpHeaders headers = new HttpHeaders();
    headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
    try {
		headers.add("Content-Disposition", "attachment; filename=" + java.net.URLEncoder.encode(excelName, "UTF-8"));
	} catch (UnsupportedEncodingException e) {
		headers.add("Content-Disposition", "attachment; filename="+excelName);
	}
    headers.add("Pragma", "no-cache");
    headers.add("Expires", "0");
    return ResponseEntity
            .ok()
            .headers(headers)
            .contentLength(file.length())
            .contentType(MediaType.parseMediaType("application/octet-stream"))
            .body(new FileSystemResource(file));
    }
    
}

package com.duan.excel;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;

import javax.servlet.http.HttpServletResponse;

import com.alibaba.excel.EasyExcel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author duanwj
 */
public class IndexController {

  @GetMapping("/template")
  public void template (HttpServletResponse response) throws IOException {
    response.setContentType("application/vnd.ms-excel");
    response.setCharacterEncoding("utf-8");
    String fileName = URLEncoder.encode("用户上传模版", "UTF-8");
    response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xlsx");
    EasyExcel.write(response.getOutputStream(), UserDTO.class).sheet("用户上传模版").doWrite(new ArrayList());
  }

  @PostMapping("upload")
  @ResponseBody
  public String upload (MultipartFile file) throws IOException {
    EasyExcel.read(file.getInputStream(), UserDTO.class, new UserReadListener()).sheet().doRead();
    return "success";
  }
}

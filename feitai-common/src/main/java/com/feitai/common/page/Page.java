package com.feitai.common.page;

/** 
 * 封装分页数据 
 */  
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Joiner;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
  
public class Page {  
  
  private static final Logger logger = LoggerFactory.getLogger(Page.class);  
  private static ObjectMapper mapper = new ObjectMapper();  
  
  public static String DEFAULT_PAGESIZE = "10";  
  private int pageNo;          //当前页码  
  private int pageSize;        //每页行数  
  private int totalRecord;      //总记录数  
  private int totalPage;        //总页数  
  private Map<String, String> params;  //查询条件  
  private Map<String, List<String>> paramLists;  //数组查询条件  
  private String searchUrl;      //Url地址  
  private String pageNoDisp;       //可以显示的页号(分隔符"|"，总页数变更时更新)  
  
  private Page() {  
    pageNo = 1;  
    pageSize = Integer.valueOf(DEFAULT_PAGESIZE);  
    totalRecord = 0;  
    totalPage = 0;  
    params = Maps.newHashMap();  
    paramLists = Maps.newHashMap();  
    searchUrl = "";  
    pageNoDisp = "";  
  }  
     
  public static Page newBuilder(int pageNo, int pageSize, String url){  
    Page page = new Page();  
    page.setPageNo(pageNo);  
    page.setPageSize(pageSize);  
    page.setSearchUrl(url);  
    return page;  
  }  
  
  /** 
   * 从 HttpServletRequest 抽取请求入参来构造Page对象 
   */  
  public static Page newBuilder2(int pageSize, HttpServletRequest request){  
    Page page = new Page();  
    String uri = request.getRequestURI();  
    int pot= uri.lastIndexOf("/");  
    // 从uri中抽取右侧"/"后字符串作为的acton名 例子中对应的是 "users"  
    page.setSearchUrl(uri.substring(pot+1));  
    // 这里是核心代码：遍历 request.getParameterMap() 提取请求参数,要注意数组的情况  
    for (Object key : request.getParameterMap().keySet()){  
      String[] args = request.getParameterValues(key.toString());  
      if (args.length>1){  
        page.getParamLists().put(key.toString(), convertParamArr(args,request));  
      }else{  
        page.getParams().put(key.toString(), convertIsoToUtf8(request.getParameter(key.toString()),request));  
      }  
    }  
    page.setPageSize(pageSize);  // 这里是每页行数  
    if ( page.getParams().get("pageNo") == null ){  
      page.getParams().put("pageNo", "1");    // 当前页 原来缺省值设定改到了这里  
    }else{  
      page.setPageNo( Integer.parseInt(page.getParams().get("pageNo")));  
      // 点击分页标签时的请求 编辑totalRecord 项目（避免重复查询总记录数）  
      page.setTotalRecord(Integer.parseInt(page.getParams().get("totalRecord")));  
    }  
    return page;  
  }  
  
  /** 
   * GET请求时，单个入参的转码处理 
   */  
  private static String convertIsoToUtf8(String strIn, HttpServletRequest request) {  
    if (strIn == null || !request.getMethod().equalsIgnoreCase("get")) {  
      return strIn;  
    }  
    try {  
      String result = new String(strIn.getBytes("iso-8859-1"), "utf-8");  
      return result;  
    } catch (UnsupportedEncodingException e) {  
      return strIn;  
    }  
  }  
    
  /** 
   * GET请求时，数组型入参的转码处理 
   */  
  private static List<String> convertParamArr(String[] param,  
      HttpServletRequest request) {  
    List<String> list = Lists.newArrayList();  
    if (param != null) {  
      for (String p : param) {  
        String convertP = convertIsoToUtf8(p, request);  
        if (!list.contains(convertP)) {  
          list.add(convertP);  
        }  
      }  
    }  
    return list;  
  }  
    
  /** 
   * 查询条件转JSON 
   */  
  public String getParaJson(){  
    Map<String, Object> map = Maps.newHashMap();  
    for (String key : params.keySet()){  
      if ( params.get(key) != null  ){  
        map.put(key, params.get(key));  
      }  
    }  
    String json="";  
    try {  
      json = mapper.writeValueAsString(map);  
    } catch (Exception e) {  
      logger.error("转换JSON失败", params, e);  
    }  
    return json;  
  }  
  
  /** 
   * 数组查询条件转JSON 
   */  
  public String getParaListJson(){  
    Map<String, Object> map = Maps.newHashMap();  
    for (String key : paramLists.keySet()){  
      List<String> lists = paramLists.get(key);  
      if ( lists != null && lists.size()>0 ){  
        map.put(key, lists);  
      }  
    }  
    String json="";  
    try {  
      json = mapper.writeValueAsString(map);  
    } catch (Exception e) {  
      logger.error("转换JSON失败", params, e);  
    }  
    return json;  
  }  
  
  /** 
   * 总件数变化时，更新总页数并计算显示样式 
   */  
  private void refreshPage(){  
    //总页数计算  
    totalPage = totalRecord%pageSize==0 ? totalRecord/pageSize : (totalRecord/pageSize + 1);  
    //防止超出最末页（浏览途中数据被删除的情况）  
    if ( pageNo > totalPage && totalPage!=0){  
        pageNo = totalPage;  
    }  
    pageNoDisp = computeDisplayStyleAndPage();  
  }  
    
  /** 
   * 计算页号显示样式 
   *  这里实现以下的分页样式("[]"代表当前页号)，可根据项目需求调整 
   *   [1],2,3,4,5,6,7,8..12,13 
   *   1,2..5,6,[7],8,9..12,13 
   *   1,2..6,7,8,9,10,11,12,[13] 
   */  
  private String computeDisplayStyleAndPage(){  
    List<Integer> pageDisplays = Lists.newArrayList();  
    if ( totalPage <= 11 ){  
      for (int i=1; i<=totalPage; i++){  
        pageDisplays.add(i);  
      }  
    }else if ( pageNo < 7 ){  
      for (int i=1; i<=8; i++){  
        pageDisplays.add(i);  
      }  
      pageDisplays.add(0);// 0 表示 省略部分(下同)  
      pageDisplays.add(totalPage-1);         
      pageDisplays.add(totalPage);  
    }else if ( pageNo> totalPage-6 ){  
      pageDisplays.add(1);  
      pageDisplays.add(2);  
      pageDisplays.add(0);  
      for (int i=totalPage-7; i<=totalPage; i++){  
        pageDisplays.add(i);  
      }         
    }else{  
      pageDisplays.add(1);  
      pageDisplays.add(2);  
      pageDisplays.add(0);  
      for (int i=pageNo-2; i<=pageNo+2; i++){  
        pageDisplays.add(i);  
      }  
      pageDisplays.add(0);  
      pageDisplays.add(totalPage-1);  
      pageDisplays.add(totalPage);  
    }  
    return Joiner.on("|").join(pageDisplays.toArray());  
  }  
   
  public int getPageNo() {  
     return pageNo;  
  }  
   
  public void setPageNo(int pageNo) {  
     this.pageNo = pageNo;  
  }  
   
  public int getPageSize() {  
     return pageSize;  
  }  
   
  public void setPageSize(int pageSize) {  
     this.pageSize = pageSize;  
  }  
   
  public int getTotalRecord() {  
     return totalRecord;  
  }  
   
  public void setTotalRecord(int totalRecord) {  
    this.totalRecord = totalRecord;  
    refreshPage();       
  }  
  
  public int getTotalPage() {  
     return totalPage;  
  }  
   
  public void setTotalPage(int totalPage) {  
     this.totalPage = totalPage;  
  }  
   
  public Map<String, String> getParams() {  
     return params;  
  }  
     
  public void setParams(Map<String, String> params) {  
     this.params = params;  
  }  
    
  public Map<String, List<String>> getParamLists() {  
    return paramLists;  
  }  
  
  public void setParamLists(Map<String, List<String>> paramLists) {  
    this.paramLists = paramLists;  
  }  
  public String getSearchUrl() {  
    return searchUrl;  
  }  
  public void setSearchUrl(String searchUrl) {  
    this.searchUrl = searchUrl;  
  }  
  public String getPageNoDisp() {  
    return pageNoDisp;  
  }  
  public void setPageNoDisp(String pageNoDisp) {  
    this.pageNoDisp = pageNoDisp;  
  }  
}
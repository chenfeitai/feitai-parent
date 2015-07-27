
/*
 * @(#)peajax.js	2.0 2008-5-8
 *
 * Author: Larry Dai
 *
 * Copyright 2005-2009 Client Service International, Inc. All rights reserved.
 * CSII PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */


var _fld_ = window.ActiveXObject ? new ActiveXObject("Microsoft.XMLHTTP") : new XMLHttpRequest();
	
	
var PE_INVALID_STATUS = "服务器无效响应。状态码: ";
var PE_INVALID_CONTENT = "服务器无响应";
var PE_INVALID_TIMEOUT = "请求超时。可能是网络出现问题或其他问题。不能确定系统是否已处理刚才提交的请求。";

var PE_STATE_LOADING = "装载中...";
var PE_STATE_LOADED = "正在发送...";
var PE_STATE_INTERACTIVE = "正在接收...";

var PE_DIGITAL_SIGN_ERROR = "请先下载安全控件，再从新做此交易。";
var PE_SECURITY_INPUT_ERROR = "输入控件错误：";

var PE_TIMEOUT= 300000

	
	
 function PEGetFormData(_yield, _stack_)
 {
  var __fld2_="";
  
  if(_yield!=null)
  { 
	  for (var i = 0; i < _yield.elements.length; i++) {
	  
	     var __fld__ = _yield.elements[i];
	    
		
		 if(__fld__.type=="hidden" || __fld__.type=="password" ||
	   	  __fld__.type=="text" || __fld__.type=="textarea"  )
	    { 
	       if(__fld2_.length>0)
		     __fld2_+="&";

	       __fld2_ += __fld__.name + "=" + encodeURIComponent(__fld__.value);
		   
	    }
	    else if( __fld__.type=="select-one"  )
	    { 
          if(__fld__.options.length>0)
          {
	       if(__fld2_.length>0)
		     __fld2_+="&";

          
          __fld2_ += __fld__.name + "=" + encodeURIComponent(__fld__.options[__fld__.selectedIndex].value);
          }
          
	    }	
	    else if( __fld__.type=="radio" ||  __fld__.type=="checkbox" )
	    { 
			if(__fld__.checked)
			{
	         if(__fld2_.length>0)
		       __fld2_+="&";

	           __fld2_ += __fld__.name + "=" + encodeURIComponent(__fld__.value);
			}
	    }
		else if(__fld__.type=="select-multiple")
	    {
          if(__fld__.options.length>0)
          {
		     for(var j=0;j<__fld__.options.length;j++)
		     {
		     	if(__fld__.options[j].selected)
				{
		       		if(__fld2_.length>0)
		    		 __fld2_+="&";

	
			       __fld2_ += __fld__.name + "=" + encodeURIComponent(__fld__.options[j].value);
				}
			 }
		   }
	    }
	   
	   }
   
   }
   
  if(_stack_!=null)
  {
	  if(_stack_.type!="")
	  {
	    if(__fld2_.length>0)
		     __fld2_+="&";

	  	__fld2_ += _stack_.name + "=" + encodeURIComponent(_stack_.value.valueOf());
	  }
  }

   return __fld2_;
  }
  
  
 function PEGetPostData(myarray)
 {  
	var postData = "";
	
	if(myarray==null)
	  return postData;
	  
	for (var i = 0; i < myarray.length; i++)
	{
		var __fld__ = myarray[i];
		postData += i==0 ? "" : "&";
		postData += __fld__.name + "=" + encodeURIComponent(__fld__.value==null? "":__fld__.value);
	}
	
	return postData;
	
 }
 
 function PEGetItemData(__fld__)
 {
 	var postData= __fld__.name + "=" + encodeURIComponent( __fld__.value==null? "": __fld__.value);
 
 	return postData;
 }
  
 function PEGetElement(id)
 {
	return  window.document.getElementById(id);
 }
	
	
/**
 * post to server
 *
 * @author Larry Dai
 * @since 2006.3.14
 * @version 1.0
 */
 
function _w1m_(_push_, _yield, _stack_, messageId, _pop_, callback, timeout)
{
	        
    if(_stack_!=null)
    {
       _stack_.disabled = true;
    }

	if(_fld_!=null)
	{
	    var _push_Var;
	    if(_push_==null)
	    {
	      if(_yield==null)
	      {
	        alert("form is null and url is null are forbidden!!!");

            if(_stack_!=null)            
               _stack_.disabled = false;
               
	        return;
	      }
	      _push_Var = _yield.action;
	    }
	    else
	     _push_Var = _push_;
	    
		var postData = PEGetFormData(_yield, _stack_);
		  
	    var OOP_ =new _form_(1,null,messageId, _stack_,_push_Var, postData,_pop_,callback,timeout);		  
		_lyd3_(OOP_);
	}
	else
	{ 
	   alert("cannot allocate xmlhttp");
	}
} 

//@Deprecated 
function post2Server(_push_,messageId, _yield, _stack_, callback, timeout)
{
    _w1m_(_push_, _yield, _stack_, messageId, false, callback, timeout);
}


function post2SRV(_push_, _yield, _stack_ , messageId, callback, timeout)
{
    _w1m_(_push_, _yield, _stack_, messageId, false, callback, timeout);
}

function postData2SRV(_push_, postData , messageId, callback, timeout)
{
	if(_fld_!=null)
	{			  
	    var OOP_ = new _form_(1,null,messageId, null, _push_, postData,false,callback,timeout);		  
			  
		_lyd3_(OOP_);
	}
	else
	{ 
	   alert("cannot allocate xmlhttp");
	}	
	
}



function post2SRVWithCallback(_push_,_yield, _stack_, callback,timeout)
{
	
	if(callback==null)
	   alert("callback is mandatory");

   if(_stack_!=null)
    {
       _stack_.disabled = true;
    }

	if(_fld_!=null)
	{
	    var _push_Var;
	    if(_push_==null)
	    {
	      if(_yield==null)
	      {
	        alert("form is null and url is null are forbidden!!!");

            if(_stack_!=null)            
               _stack_.disabled = false;
               
	        return;
	      }
	      _push_Var = _yield.action;
	    }
	    else
	     _push_Var = _push_;
	    
		var postData = PEGetFormData(_yield, _stack_);
		  
	    var OOP_ =new _form_(2,null,null, _stack_,_push_Var, postData,false ,callback,timeout);	
	    	  
		_lyd3_(OOP_);
	}
	else
	{ 
	   alert("cannot allocate xmlhttp");
	}  
	    
}

function postData2SRVWithCallback(_push_, postData, callback, timeout)
{

  	if(_fld_!=null)
	{	  
	    var OOP_ =new _form_(2,null,null, null,_push_, postData,false,callback,timeout);		    	  
		_lyd3_(OOP_);
	}
	else
	{ 
	   alert("cannot allocate xmlhttp");
	}  

}

function postItem2SRVWithCallback(_push_, __fld__, callback, timeout)
{
  postData2SRVWithCallback(_push_, PEGetItemData(__fld__), callback, timeout); 
}

function postArray2SRVWithCallback(_push_, myarray, callback, timeout)
{
  postData2SRVWithCallback(_push_, PEGetPostData(myarray), callback, timeout); 
}




function post2SRVNoFoward(_push_, _yield, _stack_, id, messageId,callback, timeout)
{
 
   if(_stack_!=null)
    {
       _stack_.disabled = true;
    }

	if(_fld_!=null)
	{
	    var _push_Var;
	    if(_push_==null)
	    {
	      if(_yield==null)
	      {
	        alert("form is null and url is null are forbidden!!!");

            if(_stack_!=null)            
               _stack_.disabled = false;
               
	        return;
	      }
	      _push_Var = _yield.action;
	    }
	    else
	     _push_Var = _push_;
	    
		var postData = PEGetFormData(_yield, _stack_);
		  
	    var OOP_ =new _form_(0,id,messageId, _stack_,_push_Var, postData,false,callback,timeout);	
	    	  
		_lyd3_(OOP_);
	}
	else
	{ 
	   alert("cannot allocate xmlhttp");
	}  
	    
}

function postData2SRVNoFoward(_push_, postData , id, messageId,callback, timeout)
{
	if(_fld_!=null)
	{	   	  
	    var OOP_ =new _form_(0, id, messageId, null, _push_, postData, false, callback, timeout);	
	    	  
		_lyd3_(OOP_);
	}
	else
	{ 
	   alert("cannot allocate xmlhttp");
	}  	    
}


function _w2m_(_push_,id, messageId, postData, _pop_,callback, timeout)
{

  var OOP_ =new _form_(0,id, messageId, null,_push_, postData,_pop_, callback,timeout);
  
    _lyd3_(OOP_);
	    
}

function PEFreshContent( _push_,__fld__, id, messageId,callback, timeout)
{
  if(messageId!=null)
	   _w2m_(_push_,id, messageId, PEGetItemData(__fld__) , false,callback, timeout);
  else
   	   _w2m_(_push_,id, id, PEGetItemData(__fld__) , false,callback, timeout);	   	 
}

function PELoadContent(_push_, myarray, id, messageId, callback, timeout )
{
 if(messageId!=null)
   _w2m_(_push_,id, messageId, PEGetPostData(myarray) , false, callback, timeout);
 else
   _w2m_(_push_,id, id, PEGetPostData(myarray) , false, callback, timeout );
}

function loadDCToken(tokenId, timeout)
{
	postData2SRVWithCallback("GenToken.do", null, 
	
	 function(success, message){
        if (success) {
        
        if(tokenId==null)
          PEGetElement("_tokenName").value=message;
        else
          PEGetElement(tokenId).value=message;
        
		}
		else
		{
			alert(message);
		}
	 }
	
	, timeout
	
	);
}


var _O_ = new Array();
var _O_o = 0;

function _csii_()
{
  this._yield_ = false;
  this._o_O = null;
  this._o_OEnc = null;
}

function _ld3_(_O_o)
{
   var POoOP__ =  _O_[_O_o%100];
   if(POoOP__._yield_)
   {
      var _form = _w0m_();
	  if(_form!=null)
	     _form.style.visibility = "hidden";
   
     _O_[_O_o%100]= null;
     
     if(POoOP__._o_O!=null)
     {
     

       window.document.open();
       
       window.document.write("<!-- PEAjax Copyright 2006 Client Service International, Inc. All rights reserved. -->");

       if(POoOP__._o_OEnc!=null)
       {

	       if(POoOP__._o_OEnc=="UTF-8" || POoOP__._o_OEnc=="utf-8" )
	       {
	          window.document.write(POoOP__._o_O);
	       }
	       else
	       {
       	       window.document.charset=POoOP__._o_OEnc;
		       var pos = POoOP__._o_O.indexOf("<title>");
		       if(pos<0)
		       {
	    	       pos = POoOP__._o_O.indexOf("<script");
	    	       if(pos<0)
	    	       {
	     	         pos = POoOP__._o_O.indexOf("</head>");
	    	       }
		       }
		       if(pos<0)
		           window.document.write(POoOP__._o_O);
		       else
		       {
		          window.document.write(POoOP__._o_O.substring(0,pos));
		          window.document.write("<script language=\"javascript\">window.document.charset=\""+POoOP__._o_OEnc+"\";</script>");
	   	          window.document.write(POoOP__._o_O.substring(pos));
		       }
	       }
	   }
	   else
	   {
           window.document.write(POoOP__._o_O);
	   }
	   window.document.close();
	   
	   
     }  

   }
   else
     window.setTimeout("_ld3_("+_O_o+")", 10);
}


/**
 * helper class for generating url
 *
 * @author Larry Dai
 * @since 2006.3.14
 * @version 1.0
 */
function Pair(name, value)
{
	this.name = name;
	this.value = value == "-1" ? "" : value;
}

function PEGetPairArray(vValue, vParaArray)
{

  if((vValue=='') || (vValue==null)){
       return null;
  }
  var vType = vValue.split("/");
  
  if(vType.length!=vParaArray.length){
      alert("parameter not mattched, required length: "+
	        vParaArray.length+" actual length: "+vType.length);
  }
  var oparams = new Array(vParaArray.length);
  for(var i=0;i<vParaArray.length;i++){
		 oparams[i]=new Pair(vParaArray[i],vType[i]);
  }
  return oparams;

}


function PEGetPairFromArray(oparams, name)
{
  if(oparams==null)
    return null;
	
  for(var i=0;i<oparams.length;i++){
		var item =  oparams[i];
		if(item.name==name)
		{
			return item;
		}
  }
  return null;
}

function PESetHiddenField(form0, oparams, names )
{
  if(names==null)
  {
		names =  new Array(oparams.length);
		for(var i=0; i < oparams.length; i++)
		{
		  var pair = oparams[i];
		  names[i]  = pair.name;
		} 
  }
  
  for(var j=0; j< names.length; j ++)
  {
		  var name = names[j];
	
		  var found= false;
		  for (var k = 0; k< form0.elements.length;k++)
		  {	  
			 var item = form0.elements[k];
			 if(item.name==name)
			 { 
				   found = true;
				   var pair = PEGetPairFromArray(oparams, name);
				   if(pair!=null)
				   {
					 item.value = pair.value;
					 break;
				   }
				   else
				   {
					 alert("CANNOT FIND "+name+" IN INPUT ARRAY");
					 break;
				   }		   
			 }	
				
		   }
		   
		   if(!found)
		   {
			   alert("CANNOT FIND "+name+" IN FORM");
		   }
  }
		   
}

/**
 * _form_
 *
 * if type==2, id= callback funtion
 * @author Larry Dai
 * @since 2006.3.14
 * @version 1.0
 */

function _form_(type, id, messageId, _stack_,_push_, postData,_pop_, callback, timeout)
{
  this.type = type;
  this.id = id;
  this.messageId = messageId;  
  this._stack_  = _stack_;
  this._push_ = _push_;
  this.postData = postData;
  this._pop_ = _pop_; 
  this.callback = callback;
  if(timeout==null)
	  this.timeout = PE_TIMEOUT;
  else
	  this.timeout = timeout; 
  this.cancel=false; //reserved for future 
  
}


var _m_ = new _w_();

function _w_()
{
  this.POOP__ = new Array(); 

}

function csii3_(type, data)
{
 this.O_220_= null;
 this.type = type;
 this.data = data;

}

var _fld__s = new Array();

var O_201 = new Array();
  
var lockCount=0;
 
var O_220= 0;


function O_0(O_220_)
{

   if(O_201[O_220%100]== O_220_)
   {
      var _fld__ =  _fld__s[O_220_%100];  
      _fld__s[O_220_%100] = null;
      if(_fld__.type==0 )
      {
      	_m_._fldOO_(O_220_, _fld__.data);
      }
      else if(_fld__.type==1)
      {
       _m_.o_o__O_O_(O_220_);
      }
     
   }
   else
	window.setTimeout("O_0("+O_220_+")",5);    

}

function _lyd3_(OOP_) {

    var _fld__ = new csii3_(0, OOP_);
    OOP__(_fld__);
}

function __lyd3_() {

    var _fld__ = new csii3_(1);
    OOP__(_fld__);
}


function OOP__(_fld__) {

    var O_10 = lockCount++; 
    var pos = O_10%100 ;
    
    O_201[pos]= O_10;
    
	{
	    _fld__.O_220_ = O_10;
	    
	    _fld__s[O_10%100] = _fld__; 
	    
		window.setTimeout("O_0("+O_10+")",5);    
	}
}



function O_20(O_10) {

  if(O_201[O_220%100]==O_10)
  {
        O_220++;
  }
 
}


_w_.prototype._fldOO_ = function ( O_220_, OOP_ ) {
     
  var _form = _w0m_();
  if(_form!=null)
	   _form.style.visibility = "visible";
	   

	      
  if(!OOP_._pop_)
  {

	
	  var pos =  this.POOP__.length++;
	  this.POOP__[pos] = OOP_;
	
	  if(pos==0)
	  {
	      o_o_O_O(this.POOP__[0]);
	  }   
	  
	 
  }
  else
  {
 
    o_o_O_O(OOP_);      
    PEProcess_form_(OOP_);

    if(_form!=null)
	   _form.style.visibility = "hidden";
  }
  
  O_20(O_220_);
   
} 




_w_.prototype.o_o__O_O_ = function (O_220_) {


  for(var i=1;i< this.POOP__.length;i++)
  {
      this.POOP__[i-1] = this.POOP__[i];
  }
  this.POOP__.length--;
  
  if(this.POOP__.length>0)
  { 
      o_o_O_O(this.POOP__[0]);
  }
  else
  {
   var _form = _w0m_();
	if(_form!=null)
	   _form.style.visibility = "hidden";
  }
  
  O_20(O_220_);  
} 


_w_.prototype._OOP_ = function () {

   return this.POOP__[0];
 
}


function PEProcess_form_(OOP_)
{

   var messageId= OOP_.messageId;
   var id= OOP_.id;
      
   var _stack_ = OOP_._stack_;

   var PoOOP__;
   
  
   if(!OOP_.cancel && OOP_.timeout>0)
   {
     clearTimeout(OOP_.tVal);
   }
   
   if(OOP_.cancel)
   {
      PoOOP__ = "<PEAjaxError>"+PE_INVALID_TIMEOUT+"</PEAjaxError>";   
   }
   else if (_fld_.status != 200) {

	                                       
	   PoOOP__ = "<PEAjaxError>"+PE_INVALID_STATUS +_fld_.status+"</PEAjaxError>";     
   }
   else
       PoOOP__ = _fld_.responseText;    
 	   
		
	   if(OOP_.type==0 || OOP_.type==2)
	   {      
	
	   	   if(_stack_!=null)           
	           _stack_.disabled = false;   
	   
		    if(PoOOP__==null)
		   {
//		   	   if(_stack_!=null)           
//		           _stack_.disabled = false;   
		                                       
		       PoOOP__ = "<PEAjaxError>"+PE_INVALID_CONTENT+"</PEAjaxError>" ;
		   }
		
			 var b = PoOOP__.indexOf("<PEAjaxError>");
			 if(b>=0)
			 {
					b+=13;
					var e = PoOOP__.indexOf("</PEAjaxError>",b);		
				    PoOOP__ = PoOOP__.substring(b,e);
				    
				    if(OOP_.type==0)
				    {
				       if(messageId!=null)
				       {
							var  messageIdVar=  window.document.getElementById(messageId);
							if(messageIdVar!=null)
								 messageIdVar.innerHTML = PoOOP__;       
							else
							{
								   alert("cannot find span with id:"+messageId);
							}
					    }
						
						if(OOP_.callback!=null)
							OOP_.callback(false, PoOOP__);
					}
					else
					{
						if(OOP_.callback!=null)
					     OOP_.callback(false, PoOOP__);
					    else
					     alert("cannot find a valid callback function for: "+OOP_._push_);
					}
					return true;				    
			  }
			  else
			  {	    
				  if(OOP_.type==0) 
				  {
					  var  idVar=  window.document.getElementById(OOP_.id);
					  if(idVar!=null)
						 idVar.innerHTML = PoOOP__;       
					  else
					  {
						   alert("cannot find span with id:"+id);
					  }
					  
					  if(OOP_.callback!=null)
							OOP_.callback(true, PoOOP__);
					   
				   }
				   else
				   {
				    //  alert("PoOOP__:"+PoOOP__);
				     // alert("callback:"+OOP_.callback);
					   OOP_.callback(true, PoOOP__);
				   }
				  return true;
			  }
	   }
	   else  if(OOP_.type ==1)
	   {
	
	   	   if(_stack_!=null)           
	           _stack_.disabled = false;   
	   
		    if(PoOOP__==null|| PoOOP__.length==0)
		   {
//		   	   if(_stack_!=null)           
//		           _stack_.disabled = false;   
		                                       
		       PoOOP__ = "<PEAjaxError>"+PE_INVALID_CONTENT+"</PEAjaxError>" ;
		   }
  
			 var b = PoOOP__.indexOf("<PEAjaxError>");
			 if(b>=0)
			 {
					b+=13;
					var e = PoOOP__.indexOf("</PEAjaxError>",b);
					
				    PoOOP__ = PoOOP__.substring(b,e);
					   
		       //	   if(_stack_!=null)           
		         //   _stack_.disabled = false;   
				
				    
				    if(messageId==null)
				    {
				   	 if(OOP_.callback==null)
				    	  alert(PoOOP__);
				    }
				    else
				    {
				      var messageIdVar = window.document.getElementById(messageId);
				      if(messageIdVar!=null)
					  		messageIdVar.innerHTML = PoOOP__;
					  else
					  {
					        alert("can't find span with id:"+messageId);
					        alert(PoOOP__);
					  }
					}
					
					
					if(OOP_.callback!=null)
							OOP_.callback(false, PoOOP__);
					
					if(!OOP_._pop_)
					{
						var POoOP__ = _O_[OOP_._O_o%100];
						POoOP__._yield_ = true;
					}
				    return true;	  
				}
				else
				{
					 var enc=null;
					 
					 var pos = PoOOP__.indexOf("charset=");
					 if(pos>0)
					 {
					   pos+=8;
					   var pos2 = PoOOP__.indexOf("\"",pos);
					   if(pos2>0)
					   {
					      enc = PoOOP__.substring(pos,pos2);
					      var pos3 = enc.indexOf(" ");
					      if(pos3>0)
					      {
					        enc  = enc.substring(0,pos3);
					      }
					      if(enc=="gb2312")
					       enc ="GBK";
					   }
					 }
 					
					if(!OOP_._pop_)
					{
						var POoOP__ = _O_[OOP_._O_o%100];
						POoOP__._o_O = PoOOP__;
						POoOP__._o_OEnc = enc;
						POoOP__._yield_ = true;
					}
					else
					{
					  
					  window.document.open();
					  window.document.write(PoOOP__);
					  if(enc!=null)
					    window.document.charset = enc;
					  window.document.close();
					}
 					
					
					return false;
		
				}
			
				
	  }
}


function o_o_O()
{

 // alert("state: "+_fld_.readyState );
 
  
  if (_fld_.readyState == 4 || _m_._OOP_().cancel)
  {
  
     
     window.clearTimeout(blinkTimeoutId);
      
     window.status="";
     hiddenAjaxState();
     
      
      var OOP_ = _m_._OOP_();
		
	 if(PEProcess_form_(OOP_))
        __lyd3_();
	
	
  }
  else
  {  
     if(_fld_.readyState==1)
     {
       blinkTimeoutId = window.setTimeout("PEBlink()",300);
       window.status = PE_STATE_LOADING;
     }
     else if(_fld_.readyState==2)
     {
       window.status = PE_STATE_LOADED;
     }
     else if(_fld_.readyState==3)
     {
       window.status = PE_STATE_INTERACTIVE;
     }
  
  }
  
  
}

function _w0m_()
{

   var _form = window.document.getElementById("PEAjaxState");  
   if(_form!=null)
   {   
     return _form;
   }
   else
   {
	 if(parent!=null&& parent.frames!=null )
	 {
	    for(var i=0;i< parent.frames.length;i++)
		{
		  var _form = parent.frames[i].document.getElementById("PEAjaxState");  
		   if(_form!=null)
		   {   
		     return _form;
		   }		   
		 
		}
	 }
	 return null;
   }

}

var blinkTimeoutId;

function PEBlink()
{

   var _form = _w0m_();
   if(_form!=null)
   {   
      if(_form.style.visibility == "visible") 
  	           _form.style.visibility = "hidden";
      else
  	           _form.style.visibility = "visible";  	       
   }
   
   blinkTimeoutId = window.setTimeout("PEBlink()",300);
   
}
function hiddenAjaxState()
{

   var _form = _w0m_();
   if(_form!=null)
   {   
      if(_form.style.visibility == "visible") 
  	           _form.style.visibility = "hidden";
        
   }
   
}


function o_o_O_O(OOP_)
{


	if(_fld_)
	{
	
	  if(OOP_.messageId!=null)
	  {
	      var messageIdVar = window.document.getElementById(OOP_.messageId);
	      if(messageIdVar!=null)
		  		messageIdVar.innerHTML = "";
	  }
	  
	  if(OOP_.id!=null)
	  {
	      var idVar = window.document.getElementById(OOP_.id);
	      if(idVar!=null)
		  		idVar.innerHTML = "";
	  }  
	
	  if(OOP_._pop_)
	  {
	  		_fld_.open("POST", OOP_._push_, false);
	  }
	  else
	  {
	    if(OOP_.type==1)
	    {
	        var POoOP__ = new _csii_();
	        var pgId = _O_o++;
	        OOP_._O_o = pgId;
	        _O_[pgId%100] = POoOP__;
	        
			window.setTimeout("_ld3_("+pgId+")",10);
		}
			  
		_fld_.open("POST", OOP_._push_, true);

	    _fld_.onreadystatechange= o_o_O;	
	  }
				
	 //   alert("post: "+OOP_._push_+" postData:"+OOP_.postData);
	 
	    if ( OOP_.timeout > 0 )
			 OOP_.tVal = setTimeout(function(){
										
					    OOP_.cancel = true;	
					    _fld_.abort();
					  
					  
					
				}, OOP_.timeout);	 
	    
		if(OOP_.postData!=null && OOP_.postData.length>0)
		{   
			_fld_.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
			_fld_.setRequestHeader("PE-AJAX", "true");
			_fld_.setRequestHeader("PE-ENCODING", "UTF-8");							
			_fld_.send(OOP_.postData);
		}
		else
		{
			_fld_.setRequestHeader("PE-AJAX", "true");		
			_fld_.send("");
		}
		
	}
	else
	{
       alert("can't allocate XMLHTTP");
	}
}


function PESign(data2sign) 
{ 

  try { 
        document.forms[0].CSIISignature.value=data2sign;        
        iesign.SignData= data2sign; 
        
        document.forms[0].CSIISignature.value=iesign.SignData; 
        
        if(data2sign==document.forms[0].CSIISignature.value){ 
                alert(PE_DIGITAL_SIGN_ERROR); 
                return false; 
        } 
        if(document.forms[0].CSIISignature.value=='E:0006'){ 
               return false; 
        } 
        return true; 
  } 
  catch(e) 
  { 
    alert("PESign:"+e); 
        return false; 
  } 
} 



function powerConfig(args) {
	var defaults = { "width":150, "height":22, "maxLength":12, "minLength":6, "maskChar":"#", "backColor":"#ECE9D8", "textColor":"#FF0000", "borderColor":"#7F9DB9", "accepts":"\\w{0,}" };
	for (var p in args)
		if (args[p] != null) defaults[p] = args[p];
	return defaults;
};

function PEWriteObject(codeBase, clsId, oid, cfg) {

   
	var htmlContent= '<object id="' + oid + '" codebase="'+codeBase+'" classid="' + clsId
		+ '" width="' + cfg.width + '" height="' + cfg.height
		+ '" style="width:' + cfg.width + 'px;height:' + cfg.height + 'px">';
	for (var name in cfg)
		htmlContent+='<param name="' + name + '" value="' + cfg[name] + '">';
	htmlContent+='</object>';
	
	return htmlContent;
};

function PEWriteUtilityObject(codeBase, clsId, oid) {
	if (!oid || typeof(oid) != "string") {
		alert("writeCommitObj Failed: oid is required!");
		return "";
	} else {
		return PEWriteObject(codeBase, "clsid:"+clsId, oid,  {"width":0,"height":0,"frameName":"mainFrame"});
	}
};

function PEWriteEditObject(codeBase, clsId, oid, cfg) {
	if (!oid || typeof(oid) != "string") {
		alert("writeEditObj Failed: oid is required!");
		return "";
	} else {
		return PEWriteObject(codeBase, "clsid:"+clsId, oid,  powerConfig(cfg));
	}
};

function PEWritePassObject(codeBase, clsId, oid, cfg  ) {
	if (!oid || typeof(oid) != "string") {
		alert("writePassObj Failed: oid is required!");
		return "";
	} else {
		return PEWriteObject(codeBase, "clsid:"+clsId, oid,  powerConfig(cfg));
	}
};

function PEGetOCXParamValue(oid, key) {
	var nodes = document.getElementById(oid).childNodes;
	for (var i = 0; i < nodes.length; i++)
		if (nodes[i].nodeName.toLowerCase() == "param")
			if (nodes[i].getAttribute("name").toLowerCase() == key.toLowerCase())
				return nodes[i].getAttribute("value");
	return null;
}


//get transaction password.
function getSecurityPassword(id, ts, spanId, errMsg) {
   
    try {
		var powerpass = document.getElementById(id);	
		powerpass.setTimestamp(ts);
		var nresult = powerpass.verify();
		if(nresult<0)
		{
			   PEGetElement(spanId).innerHTML = errMsg;
			   return null;
		}		
		value = powerpass.getPinValue();			
		if(value=="")
		{
			PEGetElement(spanId).innerHTML= PE_SECURITY_INPUT_ERROR+powerpass.lastError(); 
			return null;
		}
		else
		{
			return value;
		}		
	}catch(e)
	{
			PEGetElement(spanId).innerHTML= PE_SECURITY_INPUT_ERROR +e; 
	}
	return null;

}

//get ibs sign on password
function getIBSPassword(id, ts, spanId, errMsg) {
   
    try {
		var powerpass = document.getElementById(id);	
		powerpass.setTimestamp(ts);
		var nresult = powerpass.verify();
		if(nresult<0)
		{
			   PEGetElement(spanId).innerHTML = errMsg;
			   return null;
		}		
		value = powerpass.getValue();			
		if(value=="")
		{
			PEGetElement(spanId).innerHTML= PE_SECURITY_INPUT_ERROR+powerpass.lastError(); 
			return null;
		}
		else
		{
			return value;
		}		
	}catch(e)
	{
			PEGetElement(spanId).innerHTML= PE_SECURITY_INPUT_ERROR +e; 
	}
	return null;

}

function PEWriteSpan(spanId, content) {
	  var span = window.document.getElementById(spanId);
	  if(span!=null)
	  {
	  	span.innerHTML = content;
	  }
	  else
	  {
	  	alert("can not find span:"+spanId+" for: "+content);
	  }
}

function PEAppendSpan(spanId, content) {
	  var span = window.document.getElementById(spanId);
	  if(span!=null)
	  {
	  	span.innerHTML+= content;
	  }
	  else
	  {
	  	alert("can not find span:"+spanId+" for: "+content);
	  }
}

function getPinComplexDegree(id) {
   
    try {
		var powerpass = document.getElementById(id);	
		return	powerpass.getComplexDegree();
	}catch(e)
	{
		
	}
	return "W";
}
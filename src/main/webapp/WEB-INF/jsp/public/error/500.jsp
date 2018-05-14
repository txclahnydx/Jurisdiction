<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>500</title>
<style>
	body{ background:#f4f3ef;font-family: -apple-system,BlinkMacSystemFont,Segoe UI,Roboto,Helvetica Neue,Helvetica,PingFang SC,Hiragino Sans GB,Microsoft YaHei,SimSun,sans-serif; color: #576b7e;}
	.prompt_main{ background:#f4f3ef;}
	.prompt_main .container{ background:#f4f3ef; margin:0 auto; width:900px;height:446px;}
	.prompt404, .prompt500, .promptNoAuthority{ height:auto; overflow:hidden;}
	.prompt_main .container .other{margin:0 auto; width:310px;}
	.prompt_main .container .other .prompt_title{color:#6da8b4; font-size:24px; margin-top:20px;}
	.prompt_main .container .other .prompt_btn{ margin-top:40px;}
	.prompt_main .container .other .prompt_btn a{ padding:10px 20px; background:#eae9e6; border:1px solid #dad9d5; border-radius:10px; text-decoration:none; font-size:18px; color:#77b3bf;} 
	.prompt_main .container .other .prompt_btn a.first{ background:#66a0ab; border-color:#568f99; color:#fff; margin-right:50px;}
	.prompt_main .container .other .prompt_tel{ margin-top:40px; width:100%; text-align:center;}
	.prompt_main .container .promptNoAuthority{ width:100%;text-align:center;}
</style>
</head>
<body>
     <div class="prompt_main">
     	<div class="container">
            <div	 class="prompt500">
            	<div class="img"><img src="${path }/resources/static/images/common/500.jpg"></div>
          		<div class="other">
                <div class="prompt_title">您还可以：</div>
                <div class="prompt_btn">
                    <a href="#" class="first">访问首页</a>
                    <a href="#">返回上一步</a>
                </div>
                <div class="prompt_tel">免费客服热线：027-87531686</div>
               </div>
            </div>
        </div>
     </div>
</body>
</html>
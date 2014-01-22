// 导航栏配置文件
var outlookbar=new outlook();
var t;
t=outlookbar.addtitle('问题管理','问题管理',1);
outlookbar.additem('问题列表',t,'question/view.action');

t=outlookbar.addtitle('专题管理','专题管理',1);
outlookbar.additem('添加新专题',t,'topic/add.jsp');
outlookbar.additem('专题列表',t,'topic/listTopic.action');

t=outlookbar.addtitle('专家管理','专家管理',1);
outlookbar.additem('添加专家',t,'expert/addList.action?id=0');
/*outlookbar.additem('添加专家_2',t,'expert/NewExpert.action');*/
outlookbar.additem('专家列表',t,'expert/list.action?id=1');

t=outlookbar.addtitle('关键字管理','关键字管理',1);
outlookbar.additem('添加关键字',t,'keyword/addKeyWords.jsp');
outlookbar.additem('关键字列表',t,'keyword/list.action');

t=outlookbar.addtitle('审核管理','审核管理',1);
outlookbar.additem('问题审核',t,'audit/viewQuestionHis.action?status=4,5');
outlookbar.additem('回答审核',t,'audit/viewReplyHis.action?status=4,5');
outlookbar.additem('追问审核',t,'audit/viewProbingHis.action?status=4,5');
outlookbar.additem('审核设置',t,'audit/showAudit.action');

t=outlookbar.addtitle('领域管理','领域管理',1);
outlookbar.additem('添加领域',t,'domain/new.action');
outlookbar.additem('领域列表',t,'domain/view.action');

t=outlookbar.addtitle('系统设置','系统设置',1);
outlookbar.additem('友情链接',t,'link/viewLink.action?status=0,1,2');
outlookbar.additem('参数设置',t,'sysdata/listSysdata.action');

t=outlookbar.addtitle('用户管理','用户管理',1);
outlookbar.additem('用戶列表',t,'http://passport.gy3nong.com/m/user/index.action');

t=outlookbar.addtitle('表单导入','表单导入',1);
outlookbar.additem('导入',t,'');
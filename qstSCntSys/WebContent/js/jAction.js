var dlg='#commonDlg';
var buttons='#btnSave';
var baseurl='http://localhost:8080/qstSCntSys';
//var baseurl='http://106.14.14.121:8080/qstSCntSys';
var msginfo={'err':'系统报错请联系管理员','loaderr':'系统加载失败,该信息可能已被删除！'};
var productItems=0;
var productTRCount=0;
var orderProducts={"deleteProducts":[]}
//new dialog
function newOrderDialog(callback)
{
	productItems=0;
	productTRCount=0;
	//orderProducts.productCount=0;

	var args={'url':'NewOrder.xml','width':'850','height':'450','action':'product','title':'新增订单信息'};
	openDialog(args,newOrder,callback,initOrder);
}
function newProductDialog(callback)
{
	var args={'url':'NewProduct.xml','width':'500','height':'400','action':'product','title':'新建产品信息'};
	openDialog(args,newProduct,callback);
}
function newUserDialog(callback)
{
	var args={'url':'NewUser.xml','width':'400','height':'400','action':'user','title':'新建用户信息'};
	openDialog(args,newUser,callback);
}
function newDepartmentDialog(callback)
{
	var args={'url':'NewSalesDepartment.xml','width':'400','height':'400','action':'department','title':'新建销售部信息'};
	openDialog(args,newDepartment,callback);
}
function newCustomerDialog(callback)
{
	var args={'url':'NewCustomer.xml','width':'400','height':'400','action':'customer','title':'新建客户信息'};
	openDialog(args,newCustomer,callback);
}
function newEmployeeDialog(callback)
{
	var args={'url':'NewEmployee.xml','width':'400','height':'450','action':'employee','title':'新建员工信息'};
	openDialog(args,newEmployee,callback);
}
function newExpenseDialog(callback)
{
	var args={'url':'NewExpense.xml','width':'400','height':'400','action':'expense','title':'新增费用申请'};
	openDialog(args,newExpense,callback);
}
function newReceiptDialog(callback)
{
	var args={'url':'NewReceipt.xml','width':'400','height':'400','action':'receipt','title':'新增收款'};
	openDialog(args,newReceipt,callback,initReceipt);
}
function newMonthReportDialog(callback)
{
	var args={'url':'NewMonthReport.xml','width':'450','height':'450','action':'monthReport','title':'新增月报其他信息'};
	openDialog(args,newMonthReport,callback,initMonthReport);
}


//set model view
function setOrderModel(json)
{	
	if(json.length!=0)
    {
		$('#salesDept').combotree({
			onLoadSuccess:function(node,data){  
		    	$('#salesDept').combotree('setValue',json.salesDepartmentID);
		    },
			onSelect:function(node) {				
				$("#cusN").combogrid({
					required:true,
					panelWidth: 281,
					idField: 'id',
					textField: 'customerName',
					url: '/qstSCntSys/order/selectCustomerInfo.do?salesDepartmentID='+node.id,
					method: 'post',
					columns: [[
						{field:'customerName',title:'客户姓名',width:60},
						{field:'customerPhone',title:'客户电话',width:60},
						{field:'customerAddress',title:'顾客住址',width:159}
					]],
					rownumbers:false,
					singleSelect:true,
					pagination:true,
					pageSize:5,				
					pageList: [5],       //可以设置每页记录条数的列表
					fitColumns: true,
					onSelect:function(){
						$("#cusP").textbox("setValue",$("#cusN").combogrid('grid').datagrid('getSelected').customerPhone);
						$("#cusaddr").textbox("setValue",$("#cusN").combogrid('grid').datagrid('getSelected').customerAddress);				
					},
					keyHandler:{
						query:function(q)
						{
							$('#cusN').combogrid("grid").datagrid("reload",{'customerName':q});
							$('#cusN').combogrid("setValue",q);
						}
					}
				});
				$("#cusN").combogrid('grid').datagrid('getPager').pagination({showPageList:false,displayMsg:''});
			}
		})
	
		$("#cusN").combogrid({
			required:true,
			panelWidth: 281,
			idField: 'id',
			textField: 'customerName',
			url: '/qstSCntSys/order/selectCustomerInfo.do?salesDepartmentID='+json.salesDepartmentID,
			method: 'post',
			columns: [[
				{field:'customerName',title:'客户姓名',width:60},
				{field:'customerPhone',title:'客户电话',width:60},
				{field:'customerAddress',title:'顾客住址',width:159}
			]],
			rownumbers:false,
			singleSelect:true,
			pagination:true,
			pageSize:5,				
			pageList: [5],       //可以设置每页记录条数的列表
			fitColumns: true,
			onSelect:function(){
				$("#cusP").textbox("setValue",$("#cusN").combogrid('grid').datagrid('getSelected').customerPhone);
				$("#cusaddr").textbox("setValue",$("#cusN").combogrid('grid').datagrid('getSelected').customerAddress);				
			},
			keyHandler:{
				query:function(q)
				{
					$('#cusN').combogrid("grid").datagrid("reload",{'customerName':q});
					$('#cusN').combogrid("setValue",q);
				}
			}
		});
		$("#cusN").combogrid('grid').datagrid('getPager').pagination({showPageList:false,displayMsg:''});
			
		
		
		$('#orid').textbox('setValue',json.orderCode);
		$('#vt').combobox('select',json.customerType);
		$('#cusP').textbox('setValue',json.customerPhone);
		$('#cusN').combogrid('setValue',json.customerName);
		$('#cusaddr').textbox('setValue',json.customerAddress);
		$('#odate').datebox('setValue',json.orderDate);
		$('#amount').numberbox('setValue',json.orderAmount);
		$('#hd').textbox('setValue',json.healthMember);
		$('#cs').textbox('setValue',json.customerSign);
		var products=json.products;
		$("#orderProductId_0").textbox('setValue',products[0].id);
		$("#proN_0").combobox('setValue',products[0].productID);
		$("#odate_0").numberspinner('setValue',products[0].amount);
		//$("#unitprice_0").textbox('setValue',products[0].price);
		$("#unitprice_0").numberbox({
            prefix:'￥',
            value:products[0].price
        });
		
		//orderProducts.updateProducts.push(products[0]);
		//orderProducts.productCount=products.length-1;
		for(var i=1;i<products.length;i++)
		{
			addProduct();
			$("#orderProductId_"+i).textbox('setValue',products[i].id);
			$("#proN_"+i).combobox('setValue',products[i].productID);
			$("#odate_"+i).numberspinner('setValue',products[i].amount);
			//$("#unitprice_"+i).textbox('setValue',products[i].price)
			$("#unitprice_"+i).numberbox({
	            prefix:'￥',
	            value:products[i].price
	        });
			//orderProducts.updateProducts.push(products[i]);
		}
	}
	else
	{
		$('#msginfo2').html(msginfo.loaderr);
	}
	
}
function setProductModel(json)
{
	if(json.length!=0)
    {
		$('#productN').textbox('setValue',json.productName);
		$('#productU').textbox('setValue',json.productUnit);
		$('#productP').numberbox('setValue',json.productPrice);
	}
	else
	{
		$('#msginfo').html(msginfo.loaderr);
	}
}
function setDepartmentModel(json)
{
	if(json.length!=0)
    {
		$('#marketName').textbox('setValue',json.salesDepartmentName);
		$('#marketPhone').textbox('setValue',json.salesDepartmentPhone);
		$('#marketAddress').textbox('setValue',json.salesDepartmentAddress);
		$('#parentMarketName').combobox('select',json.parentID);
	}
	else
	{
		$('#msginfo').html(msginfo.loaderr);
	}
}
function setCustomerModel(json)
{
	if(json.length!=0)
    {
		$('#salesDept').combotree({
		    onLoadSuccess:function(node,data){  
		    	$('#salesDept').combotree('setValue',json.salesDepartmentID);
		    }}); 
		$('#customerN').textbox('setValue',json.customerName);
		$('#customerP').textbox('setValue',json.customerPhone);
		$('#customerA').textbox('setValue',json.customerAddress);
	}
	else
	{
		$('#msginfo').html(msginfo.loaderr);
	}
}
function setEmployeeModel(json)
{
	if(json.length!=0)
    {
		$('#salesDept').combotree({
		    onLoadSuccess:function(node,data){  
		    	$('#salesDept').combotree('setValue',json.salesDepartmentID);
		    }}); 
		$('#employeeN').textbox('setValue',json.employeeName);
		$('#employeeS').combobox('select',json.sex);
		$('#employeeP').textbox('setValue',json.employeePhone);
		$('#employeeA').textbox('setValue',json.employeeAddress);
		$('#employeeE').textbox('setValue',json.employeeEmail);
	}
	else
	{
		$('#msginfo').html(msginfo.loaderr);
	}
}
function setExpenseModel(json)
{
	if(json.length!=0)
    {

		$('#salesDept').combotree({
		    onLoadSuccess:function(node,data){  
		    	$('#salesDept').combotree('setValue',json.salesDepartmentID);
		    }}); 
	    $('#expenseN').combobox('setValue',json.expenseItemID);
		$('#expenseA').numberbox('setValue',json.expenseAmount);
		$('#expenseD').datebox("setValue",json.expenseDate);
	}
	else
	{
		$('#msginfo').html(msginfo.loaderr);
	}
}
function setReceiptModel(json)
{
	//parent.$.messager.alert("消息提示","1 "+json.result);
	if(json.length!=0)
    {
		$('#salesDept').combotree({
		    onLoadSuccess:function(node,data){  
		    	$('#salesDept').combotree('setValue',json.salesDepartmentID);
		    },
		    onSelect:function(node) {	
				$('#orderN').combogrid({
					required:true,
					panelWidth: 270,
					idField: 'id',
					textField: 'orderCode',
					url: '/qstSCntSys/receipt/selectOrderCode.do?salesDepartmentID='+node.id,
					method: 'post',
					columns: [[
					    {field:'id',title:'订单编号',hidden:true},
						{field:'orderCode',title:'订单编号',width:80},
						{field:'customerName',title:'客户姓名',width:80},
						{field:'customerPhone',title:'客户电话',width:130}
					]],
					rownumbers:false,
					singleSelect:true,
					pagination:true,
					pageSize:5,				
					pageList: [5],
					fitColumns: true,
					keyHandler:{
						query:function(q)
						{
							$('#orderN').combogrid("grid").datagrid("reload",{'orderCode':q});
							$('#orderN').combogrid("setValue",q);
						}
					}
					});
				$("#orderN").combogrid('grid').datagrid('getPager').pagination({showPageList:false,displayMsg:''});
		
			}
		}); 

		
		$('#orderN').combogrid({
			required:true,
			panelWidth: 270,
			idField: 'id',
			textField: 'orderCode',
			url: '/qstSCntSys/receipt/selectOrderCode.do?salesDepartmentID='+json.salesDepartmentID,
			method: 'post',
			columns: [[
			    {field:'id',title:'订单编号',hidden:true},
				{field:'orderCode',title:'订单编号',width:80},
				{field:'customerName',title:'客户姓名',width:80},
				{field:'customerPhone',title:'客户电话',width:130}
			]],
			rownumbers:false,
			singleSelect:true,
			pagination:true,
			pageSize:5,				
			pageList: [5],
			fitColumns: true,
			keyHandler:{
				query:function(q)
				{
					$('#orderN').combogrid("grid").datagrid("reload",{'orderCode':q});
					$('#orderN').combogrid("setValue",q);
				}
			}
			});
		$("#orderN").combogrid('grid').datagrid('getPager').pagination({showPageList:false,displayMsg:''});

	
		
		$('#receiptA').numberbox('setValue',json.receiptAmount);
		$('#receiptM').textbox('setValue',json.receiptMember);
		$('#receiptR').textbox('setValue',json.remark);
		$('#orderN').combogrid('setValue',json.orderCode);
		//alert(json.orderCode);
		//$('#orderN').combogrid('setValue',{id:json.orderID,name:json.orderCode});
		$('#receiptD').datebox('setValue',json.receiptDate);

	}
	else
	{
		$('#msginfo').html(msginfo.loaderr);
	}
}
function setMonthReportModel(json)
{
	initMonthReport();
	if(json.length!=0)
    {
		$('#salesDept').combotree({
		    onLoadSuccess:function(node,data){  
		    	$('#salesDept').combotree('setValue',json.salesDepartmentID);
		    }}); 
		$('#manageC').numberbox('setValue',json.manageCost);
		$('#infoD').datebox('setValue',json.infoDate);
		$('#earlyN').numberbox('setValue',json.earlyNumber);
		$('#finalN').numberbox('setValue',json.finalNumber);
		$('#singleExcessA').numberbox('setValue',json.singleExcessAmount);
		$('#overallExcessA').numberbox('setValue',json.overallExcessAmount);
    }
	else
	{
		$('#msginfo').html(msginfo.loaderr);
	}
}
function setUserModel(json)
{
	var length=0;  
	for(var ever in json) {  
	    length++;  
	}
	//parent.$.messager.alert("消息提示",length);
	if(length!=0)
    {
		$('#userN').textbox('setValue',json.userName);
		$('#userP').textbox('setValue',json.userPhone);
		$('#userE').textbox('setValue',json.userEmail);
		$('#userPwd').textbox('setValue',json.pwd);
    }
	else
	{
		$('#msginfo').html(msginfo.loaderr);
	}
}

//update dialog
function updateOrderDialog(id,callback)
{
	productItems=0;
	productTRCount=0;
	//orderProducts.productCount=0;
	//orderProducts.updateProducts=[];
	orderProducts.deleteProducts=[];
	var args={'actionUrl':'/qstSCntSys/order/selectByID.do','url':'NewOrder.xml','width':'850','height':'450','action':'product','title':'修改订单信息'};
	openUpdateDialog(args,updateOrder,callback,id,setOrderModel)
}
function updateProductDialog(id,callback)
{
	var args={'actionUrl':'/qstSCntSys/product/selectByID.do','url':'NewProduct.xml','width':'400','height':'300','action':'product','title':'修改产品信息'};
	openUpdateDialog(args,updateProduct,callback,id,setProductModel)
}

function updateUserDialog(id,callback)
{
	var args={'actionUrl':'/qstSCntSys/user/selectByID.do','url':'UpdateUser.xml','width':'500','height':'400','action':'user','title':'修改用户信息'};
	openUpdateDialog(args,updateUser,callback,id,setUserModel);
}
function updateDepartmentDialog(id,callback)
{
	var args={'actionUrl':'/qstSCntSys/salesDepartment/selectByID.do','url':'NewSalesDepartment.xml','width':'400','height':'300','action':'department','title':'修改销售部信息'};
	openUpdateDialog(args,updateDepartment,callback,id,setDepartmentModel);
}
function updateCustomerDialog(id,callback)
{
	var args={'actionUrl':'/qstSCntSys/customer/selectByID.do','url':'NewCustomer.xml','width':'400','height':'300','action':'customer','title':'修改客户信息'};
	openUpdateDialog(args,updateCustomer,callback,id,setCustomerModel);
}
function updateEmployeeDialog(id,callback)
{
	var args={'actionUrl':'/qstSCntSys/employee/selectByID.do','url':'NewEmployee.xml','width':'400','height':'450','action':'employee','title':'修改员工信息'};
	openUpdateDialog(args,updateEmployee,callback,id,setEmployeeModel);
}
function updateExpenseDialog(id,callback)
{
	var args={'actionUrl':'/qstSCntSys/cost/selectByID.do','url':'NewExpense.xml','width':'400','height':'350','action':'expense','title':'修改费用申请'};
	openUpdateDialog(args,updateExpense,callback,id,setExpenseModel);
}
function updateReceiptDialog(id,callback)
{
	var args={'actionUrl':'/qstSCntSys/receipt/selectByID.do','url':'NewReceipt.xml','width':'400','height':'400','action':'receipt','title':'修改收款'};
	openUpdateDialog(args,updateReceipt,callback,id,setReceiptModel);
}
function updateMonthReportDialog(id,callback)
{
	var args={'actionUrl':'/qstSCntSys/otherInfo/selectByID.do','url':'NewMonthReport.xml','width':'450','height':'450','action':'monthReport','title':'修改月报其他信息'};
	openUpdateDialog(args,updateMonthReport,callback,id,setMonthReportModel);
}

function getDataModel(url,id,callback)
{
	$.ajax({
		type: "get",
		url : url,
		data:{id:id},
		dataType:"json",
		error: function(){
			parent.$.messager.alert("消息提示",msginfo.err,"info");
		},
		success: function(json){
			callback(json);
		}
	});
}
function closeDialog()
{
	$('#commonDlg').dialog('close');
}
function addCustomer()
{
	var args={'url':'NewCustomer.xml','width':'400','height':'300','action':'customer','title':'新建客户信息'};
	$("#cusDlg").dialog(
	{
		title:args.title,
		height:args.height,
		width:args.width,
		cache:false,
		href:args.url,
		closed:false,
		modal:true,
		onLoad:function(){
			$("#btnSave2").bind('click',function(){
				if($("#customerN").textbox('isValid')==false)
				{
					$('#msginfo').html("客户姓名为必输项！");
					return;
				}
				if($("#customerP").textbox('isValid')==false)
				{
					$('#msginfo').html("客户电话为必输项！");
					return;
				}
				var json={
						"customerPhone":$('#customerP').val(),
						"customerName":$('#customerN').val(),
						"customerAddress":$('#customerA').val()
						};
				
				$.ajax({
					type:"post",
					url :"/qstSCntSys/customer/addCustomerInfo.do",
					data:JSON.stringify(json),
					dataType:"json",
					contentType:"application/json;charset=utf-8",
					error: function(){
						parent.$.messager.alert("消息提示",msginfo.err,"info");
					},
					success: function(json){
							if(json.result=="Success")
				    		{
				    			parent.$.messager.alert("消息提示","新增成功！","info");
				    			parent.$('#cusDlg').dialog('close');;
				    		}
				    		else if(json.result=="Failed")
				    		{
				    			parent.$.messager.alert("消息提示","新增失败！","info");
				    		}
				    		else
				    		{
				    			parent.$.messager.alert("消息提示","客户信息已存在！","info");
				    		}
					}
				});
			});
		},
		onClose:function(){
			$("#btnSave2").unbind('click');
		}
	});
}
function initOrder()
{
	$('#salesDept').combotree({
		onSelect:function(node) {
			
			$("#cusN").combogrid({
				required:true,
				panelWidth: 281,
				idField: 'id',
				textField: 'customerName',
				url: '/qstSCntSys/order/selectCustomerInfo.do?salesDepartmentID='+node.id,
				method: 'post',
				columns: [[
					{field:'customerName',title:'客户姓名',width:60},
					{field:'customerPhone',title:'客户电话',width:60},
					{field:'customerAddress',title:'顾客住址',width:159}
				]],
				rownumbers:false,
				singleSelect:true,
				pagination:true,
				pageSize:5,				
				pageList: [5],       //可以设置每页记录条数的列表
				fitColumns: true,
				onSelect:function(){
					$("#cusP").textbox("setValue",$("#cusN").combogrid('grid').datagrid('getSelected').customerPhone);
					$("#cusaddr").textbox("setValue",$("#cusN").combogrid('grid').datagrid('getSelected').customerAddress);				
				},
				keyHandler:{
					query:function(q)
					{
						$('#cusN').combogrid("grid").datagrid("reload",{'customerName':q});
						$('#cusN').combogrid("setValue",q);
					}
				}
			});
			$("#cusN").combogrid('grid').datagrid('getPager').pagination({showPageList:false,displayMsg:''});
		}
	})
}
function initReceipt()
{
	$('#salesDept').combotree({
		onSelect:function(node) {	
			$('#orderN').combogrid({
				required:true,
				panelWidth: 270,
				idField: 'id',
				textField: 'orderCode',
				url: '/qstSCntSys/receipt/selectOrderCode.do?salesDepartmentID='+node.id,
				method: 'post',
				columns: [[
				    {field:'id',title:'订单编号',hidden:true},
					{field:'orderCode',title:'订单编号',width:80},
					{field:'customerName',title:'客户姓名',width:80},
					{field:'customerPhone',title:'客户电话',width:130}
				]],
				rownumbers:false,
				singleSelect:true,
				pagination:true,
				pageSize:5,				
				pageList: [5],
				fitColumns: true,
				keyHandler:{
					query:function(q)
					{
						$('#orderN').combogrid("grid").datagrid("reload",{'orderCode':q});
						$('#orderN').combogrid("setValue",q);
					}
				}
				});
			$("#orderN").combogrid('grid').datagrid('getPager').pagination({showPageList:false,displayMsg:''});
	
		}
	})
}



function initMonthReport()
{
	dateformate("#infoD")
}
function openDialog(args,action,callback,initView)
{
		$(dlg).dialog(
		{
			title:args.title,
			height:args.height,
			width:args.width,
			cache:false,
			href:args.url,
			closed:false,
			modal:true,
			onLoad:function(){
				if(initView!=null)
				{initView();}
				$(buttons).bind('click',function(){action(callback);});
			},
			onClose:function(){
				$(buttons).unbind('click');
			}
	    });
}


function openUpdateDialog(args,action,callback,id,setModel)
{
	//parent.$.messager.alert("消息提示",args);
	//parent.$.messager.alert("消息提示",id);
		$(dlg).dialog(
		{
			title:args.title,
			height:args.height,
			width:args.width,
			cache:false,
			href:args.url,
			closed:false,
			modal:true,
			onLoad:function(){
				getDataModel(args.actionUrl,id,setModel);
				$(buttons).bind('click',function(){action(callback,id);});
			},
			onClose:function(){
				$(buttons).unbind('click');
			}
	    });
}
//new operation
function newOrder(callback)
{
	if($("#salesDept").combotree('isValid')==false)
	{
		$('#msginfo2').html("销售部门为必选项！");
		return;
	}
	if($("#orid").textbox('isValid')==false)
	{
		$('#msginfo2').html("订单编号为必输项！");
		return;
	}
	if($("#cusN").combogrid('isValid')==false)
	{
		$('#msginfo2').html("客户姓名为必选项！");
		return;
	}
	if($("#amount").numberbox('isValid')==false)
	{
		$('#msginfo2').html("订单金额为必输项！");
		return;
	}
	if($("#odate").datebox('isValid')==false)
	{
		$('#msginfo2').html("订单时间为必选项！");
		return;
	}
	
	var res;
	for(var i=0;i<=productItems;i++)
	{
		//if(document.getElementById("plist_"+i)!=null){
		if($("#plist_"+i).length>0){
			//parent.$.messager.alert("消息提示",$("#proN_"+i));
			//parent.$.messager.alert("消息提示",JSON.stringify($("#plist_2")));
			if($("#proN_"+i).combobox('isValid')==false)
			{
				res=true;
				$('#msginfo2').html("产品名称为必选项！");
				break;
			}
			if($("#unitprice_"+i).numberbox('isValid')==false)
			{
				$('#msginfo2').html("产品单价为必输项！");
				res=true;
				break;
			}
		}
		
	}
	if(res){return;}
	if($("#hd").textbox('isValid')==false)
	{
		$('#msginfo2').html("健康代表为必输项！");
		return;
	}
//	if($("#cs").textbox('isValid')==false)
//	{
//		$('#msginfo2').html("顾客签收为必输项！");
//		return;
//	}
	var json={
			"salesDepartmentID":$('#salesDept').combotree("getValue"),
			"orderCode":$('#orid').val(),
			"customerType":$('#vt').combobox('getValue'),
			"customerPhone":$('#cusP').val(),
			"customerName":$('#cusN').combogrid('getText'),
			"customerAddress":$('#cusaddr').val(),
			"orderDate":$('#odate').datebox("getValue"),
			"orderAmount":$("#amount").val(),
			"healthMember":$('#hd').val(),
			"customerSign":$('#cs').val(),
			"products":[]
			};
	for(var i=0;i<=productItems;i++)
	{
		if($("#plist_"+i).length>0){
			var product=new Object();
			product.productID=$("#proN_"+i).combobox('getValue');
			product.amount=$("#odate_"+i).numberspinner('getValue');
			product.price=$("#unitprice_"+i).numberbox('getValue');
			json.products.push(product);			
		}
	}
    //parent.$.messager.alert("消息提示",JSON.stringify(json));
	setDataModel("/qstSCntSys/order/insertOrderAndOProductInfo.do",json,callback);
}
function newProduct(callback)
{
	if($("#productN").textbox('isValid')==false)
	{
		$('#msginfo').html("产品名称为必输项！");
		return;
	}
	if($("#productU").textbox('isValid')==false)
	{
		$('#msginfo').html("产品单位为必输项！");
		return;
	}
	if($("#productP").numberbox('isValid')==false)
	{
		$('#msginfo').html("产品单价为必输项！");
		return;
	}
	var json={
			"productName":$('#productN').val(),
			"productUnit":$('#productU').val(),
			"productPrice":$('#productP').numberbox('getValue')
			};
	setDataModel("/qstSCntSys/product/addProductInfo.do",json,callback);
}
function newUser(callback)
{
	if($("#userN").textbox('isValid')==false)
	{
		$('#msginfo').html("用户姓名为必输项！");
		return;
	}
	if($("#userP").textbox('isValid')==false)
	{
		$('#msginfo').html("用户联系电话为必输项！");
		return;
	}
	if($('#userE').val()!=null&&$("#userE").textbox('isValid')==false)
	{
		$('#msginfo').html("用户电子邮件格式不符！");
		return;
	}
	var json={
			"userName":$('#userN').val(),
			"userPhone":$('#userP').val(),
			"userEmail":$('#userE').val(),
			};
	setDataModel("/qstSCntSys/user/addUserInfo.do",json,callback);
}
function newDepartment(callback)
{
	if($("#marketName").textbox('isValid')==false)
	{
		$('#msginfo').html("销售部名称为必输项！");
		return;
	}
	if($("#marketPhone").textbox('isValid')==false)
	{
		$('#msginfo').html("销售部电话为必输项！");
		return;
	}
	if($("#marketAddress").textbox('isValid')==false)
	{
		$('#msginfo').html("销售部地址为必输项！");
		return;
	}
	var json={
			"salesDepartmentName":$('#marketName').val(),
			"salesDepartmentAddress":$('#marketPhone').val(),
			"salesDepartmentPhone":$('#marketAddress').val(),
			"parentID":$('#parentMarketName').combobox('getValue')
			};
	setDataModel("/qstSCntSys/salesDepartment/addSalesDepartmentInfo.do",json,callback);
}
function newCustomer(callback)
{
	if($("#salesDept").combotree('isValid')==false)
	{
		$('#msginfo2').html("销售部门为必选项！");
		return;
	}
	if($("#customerN").textbox('isValid')==false)
	{
		$('#msginfo').html("客户姓名为必输项！");
		return;
	}
	if($("#customerP").textbox('isValid')==false)
	{
		$('#msginfo').html("客户电话为必输项！");
		return;
	}
	var json={
			"salesDepartmentID":$('#salesDept').combotree("getValue"),
			"customerPhone":$('#customerP').val(),
			"customerName":$('#customerN').val(),
			"customerAddress":$('#customerA').val()
			};
	setDataModel("/qstSCntSys/customer/addCustomerInfo.do",json,callback);
}
function newEmployee(callback)
{
	if($("#salesDept").combotree('isValid')==false)
	{
		$('#msginfo2').html("销售部门为必选项！");
		return;
	}
	if($("#employeeN").textbox('isValid')==false)
	{
		$('#msginfo').html("员工姓名为必输项！");
		return;
	}
	if($("#employeeP").textbox('isValid')==false)
	{
		$('#msginfo').html("员工电话为必输项！");
		return;
	}
	if($('#employeeE').val()!=null&&$("#employeeE").textbox('isValid')==false)
	{
		$('#msginfo').html("用户电子邮件格式不符！");
		return;
	}
	var json={
			"salesDepartmentID":$('#salesDept').combotree("getValue"),
			"employeeName":$('#employeeN').val(),
			"sex":$('#employeeS').combobox('getValue'),
			"employeePhone":$('#employeeP').val(),
			"employeeAddress":$('#employeeA').val(),
			"employeeEmail":$('#employeeE').val(),
			"role":$('#role').combobox('getValue')
			};
	setDataModel("/qstSCntSys/employee/addEmployeeInfo.do",json,callback);
}
function newExpense(callback)
{
	if($("#salesDept").combotree('isValid')==false)
	{
		$('#msginfo2').html("销售部门为必选项！");
		return;
	}
	if($("#expenseA").numberbox('isValid')==false)
	{
		$('#msginfo').html("费用项目为必选项！");
		return;
	}
	if($("#expenseD").datebox('isValid')==false)
	{
		$('#msginfo').html("费用时间为必选项！");
		return;
	}
	var json={
			"salesDepartmentID":$('#salesDept').combotree("getValue"),
			"expenseItemID":$('#expenseN').combobox('getValue'),
			"expenseAmount":$('#expenseA').numberbox('getValue'),
			"expenseDate":$('#expenseD').datebox("getValue")
			};
	setDataModel("/qstSCntSys/cost/addCostInfo.do",json,callback);
}
function newReceipt(callback)
{
	if($("#salesDept").combotree('isValid')==false)
	{
		$('#msginfo').html("销售部门为必选项！");
		return;
	}
	if($("#orderN").combogrid('isValid')==false)
	{
		$('#msginfo').html("订单编号为必选项！");
		return;
	}
	if($("#receiptA").numberbox('isValid')==false)
	{
		$('#msginfo').html("收款金额为必输项！");
		return;
	}
	if($("#receiptD").datebox('isValid')==false)
	{
		$('#msginfo').html("收款时间为必选项！");
		return;
	}
	if($("#receiptM").textbox('isValid')==false)
	{
		$('#msginfo').html("收款人为必输项！");
		return;
	}
	var json={
			"salesDepartmentID":$('#salesDept').combotree("getValue"),
			"orderID":$('#orderN').combobox("getValue"),
			"receiptDate":$('#receiptD').datebox("getValue"),
			"receiptAmount":$('#receiptA').numberbox("getValue"),
			"receiptMember":$('#receiptM').val(),
			"remark":$('#receiptR').val()
			};
	setDataModel("/qstSCntSys/receipt/addReceiptInfo.do",json,callback);
}
function newMonthReport(callback)
{
	if($("#salesDept").combotree('isValid')==false)
	{
		$('#msginfo').html("销售部门为必选项！");
		return;
	}
	var manageCost=0;
	var earlyNumber=0;
	var finalNumber=0;
	var singleExcessAmount=0;
	var overallExcessAmount=0;
	if($('#manageC').numberbox('getValue')=="")
	{
		manageCost=0;
	}
	else
	{
		manageCost=$('#manageC').numberbox('getValue');
	}
	
	if($('#earlyN').numberbox('getValue')=="")
	{
		earlyNumber=0;
	}
	else
	{
		earlyNumber=$('#earlyN').numberbox('getValue');
	}
	
	if($('#finalN').numberbox('getValue')=="")
	{
		finalNumber=0;
	}
	else
	{
		finalNumber=$('#finalN').numberbox('getValue');
	}
	
	if($('#singleExcessA').numberbox('getValue')=="")
	{
		singleExcessAmount=0;
	}
	else
	{
		singleExcessAmount=$('#singleExcessA').numberbox('getValue');
	}
	
	if($('#overallExcessA').numberbox('getValue')=="")
	{
		overallExcessAmount=0;
	}
	else
	{
		overallExcessAmount=$('#overallExcessA').numberbox('getValue');
	}
	var json={
			"salesDepartmentID":$('#salesDept').combotree("getValue"),
			"manageCost":manageCost,
			"earlyNumber":earlyNumber,
			"finalNumber":finalNumber,
			"singleExcessAmount":singleExcessAmount,
			"overallExcessAmount":overallExcessAmount,
			"infoDate":$('#infoD').datebox("getValue")
			};
	setDataModel("/qstSCntSys/otherInfo/addEveryMonthOtherInfo.do",json,callback);
}




//update
function setDataModel(url,json,callback)
{
	//parent.$.messager.alert("消息提示",json);
	//parent.$.messager.alert("消息提示",JSON.stringify(json));
	$.ajax({
		type:"post",
		url :url,
		data:JSON.stringify(json),
		dataType:"json",
		contentType:"application/json;charset=utf-8",
		error: function(){
			parent.$.messager.alert("消息提示",msginfo.err,"info");
		},
		success: function(json){
				callback(json.result);
		}
	});
}
//update operation
function updateOrder(callback,id)
{
	if($("#salesDept").combotree('isValid')==false)
	{
		$('#msginfo2').html("销售部门为必选项！");
		return;
	}
	if($("#orid").textbox('isValid')==false)
	{
		$('#msginfo2').html("订单编号为必输项！");
		return;
	}
	if($("#cusN").combogrid('isValid')==false)
	{
		$('#msginfo2').html("客户姓名为必选项！");
		return;
	}
	if($("#amount").numberbox('isValid')==false)
	{
		$('#msginfo2').html("订单金额为必输项！");
		return;
	}
	if($("#odate").datebox('isValid')==false)
	{
		$('#msginfo2').html("订单时间为必选项！");
		return;
	}	
//	var res;
//	for(var i=0;i<=productItems;i++)
//	{
//		
//		if($("#proN_"+i).combobox('isValid')==false)
//		{
//			res=true;
//			$('#msginfo2').html("产品名称为必选项！");
//			break;
//		}
//		if($("#unitprice_"+i).numberbox('isValid')==false)
//		{
//			$('#msginfo2').html("产品单价为必输项！");
//			res=true;
//			break;
//		}
//	}
//	if(res){return;}
	var res;
	for(var i=0;i<=productItems;i++)
	{
		//if(document.getElementById("plist_"+i)!=null){
		if($("#plist_"+i).length>0){
			//parent.$.messager.alert("消息提示",$("#proN_"+i));
			//parent.$.messager.alert("消息提示",JSON.stringify($("#plist_2")));
			if($("#proN_"+i).combobox('isValid')==false)
			{
				res=true;
				$('#msginfo2').html("产品名称为必选项！");
				break;
			}
			if($("#unitprice_"+i).numberbox('isValid')==false)
			{
				$('#msginfo2').html("产品单价为必输项！");
				res=true;
				break;
			}
		}
		
	}
	if(res){return;}
	if($("#hd").textbox('isValid')==false)
	{
		$('#msginfo2').html("健康代表为必输项！");
		return;
	}
//	if($("#cs").textbox('isValid')==false)
//	{
//		$('#msginfo2').html("顾客签收为必输项！");
//		return;
//	}
	var json={
			"id":id,
			"salesDepartmentID":$('#salesDept').combotree("getValue"),
			"orderCode":$('#orid').val(),
			"customerType":$('#vt').combobox('getValue'),
			"customerPhone":$('#cusP').val(),
			"customerName":$('#cusN').combobox('getText'),
			"customerAddress":$('#cusaddr').val(),
			"orderDate":$('#odate').datebox("getValue"),
			"orderAmount":$("#amount").val(),
			"healthMember":$('#hd').val(),
			"customerSign":$('#cs').val(),
			"updateProducts":[],"deleteProducts":[],"insertProducts":[]
			};
	
	for(var i=0;i<=productItems;i++)
	{
		if($("#plist_"+i).length>0){
			
			var opID=$("#orderProductId_"+i).val();
			//parent.$.messager.alert("消息提示",opID);
			if(opID!=""){//要修改的产品信息
				//parent.$.messager.alert("消息提示",1);
				var product=new Object();
				product.id=$("#orderProductId_"+i).val();
				product.productID=$("#proN_"+i).combobox('getValue');
				product.amount=$("#odate_"+i).numberspinner('getValue');
				product.price=$("#unitprice_"+i).numberbox('getValue');
				json.updateProducts.push(product);
			}
			else{//新增的产品信息
				var product=new Object();
				product.productID=$("#proN_"+i).combobox('getValue');
				product.amount=$("#odate_"+i).numberspinner('getValue');
				product.price=$("#unitprice_"+i).numberbox('getValue');
				json.insertProducts.push(product);
			}			
		}
	}
	
//	for(var i=0;i<=orderProducts.productCount;i++)
//	{
//		var product=new Object();
//		product.id=orderProducts.updateProducts[i].id;
//		product.productID=$("#proN_"+i).combobox('getValue');
//		product.amount=$("#odate_"+i).numberspinner('getValue');
//		product.price=$("#unitprice_"+i).numberbox('getValue');
//		json.updateProducts.push(product);
//	}
//	for(var i=orderProducts.productCount+1;i<=productItems;i++)
//	{
//		var product=new Object();
//		product.productID=$("#proN_"+i).combobox('getValue');
//		product.amount=$("#odate_"+i).numberspinner('getValue');
//		product.price=$("#unitprice_"+i).numberbox('getValue');
//		json.insertProducts.push(product);
//		 parent.$.messager.alert("消息提示",JSON.stringify(product));
//	}
	
	for(var i=0;i<orderProducts.deleteProducts.length;i++)//要删除的产品信息
	{
		var product=new Object();
		product.id=orderProducts.deleteProducts[i].id
		json.deleteProducts.push(product);
	}
    //parent.$.messager.alert("消息提示",JSON.stringify(json));
	setDataModel("/qstSCntSys/order/updateOrderAndOProductInfo.do",json,callback);
}
function updateProduct(callback,id)
{
	if($("#productN").textbox('isValid')==false)
	{
		$('#msginfo').html("产品名称为必输项！");
		return;
	}
	if($("#productU").textbox('isValid')==false)
	{
		$('#msginfo').html("产品单位为必输项！");
		return;
	}
	if($("#productP").numberbox('isValid')==false)
	{
		$('#msginfo').html("产品单价为必输项！");
		return;
	}
	var json={
			"id":id,
			"productName":$('#productN').val(),
			"productUnit":$('#productU').val(),
			"productPrice":$('#productP').numberbox('getValue')
			};
	setDataModel("/qstSCntSys/product/updateProductInfo.do",json,callback);
}
function updateUser(callback,id)
{
	if($("#userN").textbox('isValid')==false)
	{
		$('#msginfo').html("用户姓名为必输项！");
		return;
	}
	if($("#userPwd").textbox('isValid')==false)
	{
		$('#msginfo').html("用户密码为必输项！");
		return;
	}
	if($("#userP").textbox('isValid')==false)
	{
		$('#msginfo').html("用户联系电话为必输项！");
		return;
	}
	if($('#userE').val()!=null&&$("#userE").textbox('isValid')==false)
	{
		$('#msginfo').html("用户电子邮件格式不符！");
		return;
	}
	
	var json={
			"id":id,
			"userName":$('#userN').val(),
			"userPhone":$('#userP').val(),
			"userEmail":$('#userE').val(),
			"pwd":$('#userPwd').val()
			};
	setDataModel("/qstSCntSys/user/updateUserInfo.do",json,callback);
}
function updateDepartment(callback,id)
{
	if($("#marketName").textbox('isValid')==false)
	{
		$('#msginfo').html("销售部名称为必输项！");
		return;
	}
	if($("#marketPhone").textbox('isValid')==false)
	{
		$('#msginfo').html("销售部电话为必输项！");
		return;
	}
	if($("#marketAddress").textbox('isValid')==false)
	{
		$('#msginfo').html("销售部地址为必输项！");
		return;
	}
	var json={
			"id":id,
			"salesDepartmentName":$('#marketName').val(),
			"salesDepartmentAddress":$('#marketPhone').val(),
			"salesDepartmentPhone":$('#marketAddress').val(),
			"parentID":$('#parentMarketName').combobox('getValue')
			};
	setDataModel("/qstSCntSys/salesDepartment/updateSalesDepartmentInfo.do",json,callback);
}
function updateCustomer(callback,id)
{
	if($("#salesDept").combotree('isValid')==false)
	{
		$('#msginfo2').html("销售部门为必选项！");
		return;
	}
	if($("#customerN").textbox('isValid')==false)
	{
		$('#msginfo').html("客户姓名为必输项！");
		return;
	}
	if($("#customerP").textbox('isValid')==false)
	{
		$('#msginfo').html("客户电话为必输项！");
		return;
	}
	var json={
			"id":id,
			"salesDepartmentID":$('#salesDept').combotree("getValue"),
			"customerPhone":$('#customerP').val(),
			"customerName":$('#customerN').val(),
			"customerAddress":$('#customerA').val()
			};
	setDataModel("/qstSCntSys/customer/updateCustomerInfo.do",json,callback);
}
function updateEmployee(callback,id)
{
	if($("#salesDept").combotree('isValid')==false)
	{
		$('#msginfo2').html("销售部门为必选项！");
		return;
	}
	if($("#employeeN").textbox('isValid')==false)
	{
		$('#msginfo').html("员工姓名为必输项！");
		return;
	}
	if($("#employeeP").textbox('isValid')==false)
	{
		$('#msginfo').html("员工电话为必输项！");
		return;
	}
	if($('#employeeE').val()!=null&&$("#employeeE").textbox('isValid')==false)
	{
		$('#msginfo').html("用户电子邮件格式不符！");
		return;
	}
	var json={
			"id":id,
			"salesDepartmentID":$('#salesDept').combotree("getValue"),
			"employeeName":$('#employeeN').val(),
			"sex":$('#employeeS').combobox('getValue'),
			"employeePhone":$('#employeeP').val(),
			"employeeAddress":$('#employeeA').val(),
			"employeeEmail":$('#employeeE').val(),
			"role":$('#role').combobox('getValue')
			};
	setDataModel("/qstSCntSys/employee/updateEmployeeInfo.do",json,callback);
}
function updateExpense(callback,id)
{
	if($("#salesDept").combotree('isValid')==false)
	{
		$('#msginfo2').html("销售部门为必选项！");
		return;
	}
	if($("#expenseA").numberbox('isValid')==false)
	{
		$('#msginfo').html("费用项目为必选项！！");
		return;
	}
	if($("#expenseD").datebox('isValid')==false)
	{
		$('#msginfo').html("费用时间为必选项！");
		return;
	}
	var json={
			"id":id,
			"salesDepartmentID":$('#salesDept').combotree("getValue"),
			"expenseItemID":$('#expenseN').combobox('getValue'),
			"expenseAmount":$('#expenseA').numberbox('getValue'),
			"expenseDate":$('#expenseD').datebox("getValue")
			};
	setDataModel("/qstSCntSys/cost/updateCostInfo.do",json,callback);
}
function updateReceipt(callback,id)
{
	if($("#salesDept").combotree('isValid')==false)
	{
		$('#msginfo').html("销售部门为必选项！");
		return;
	}
	if($("#orderN").combogrid('isValid')==false)
	{
		$('#msginfo').html("订单编号为必选项！");
		return;
	}
	if($("#receiptA").numberbox('isValid')==false)
	{
		$('#msginfo').html("收款金额为必输项！");
		return;
	}
	if($("#receiptD").datebox('isValid')==false)
	{
		$('#msginfo').html("收款时间为必选项！");
		return;
	}
	if($("#receiptM").textbox('isValid')==false)
	{
		$('#msginfo').html("收款人为必输项！");
		return;
	}
	var json={
			"id":id,
			"salesDepartmentID":$('#salesDept').combotree("getValue"),
			"orderID":$('#orderN').combobox("getValue"),
			"receiptDate":$('#receiptD').datebox("getValue"),
			"receiptAmount":$('#receiptA').numberbox('getValue'),
			"receiptMember":$('#receiptM').val(),
			"remark":$('#receiptR').val()
			};
	setDataModel("/qstSCntSys/receipt/updateReceiptInfo.do",json,callback);
}
function updateMonthReport(callback,id)
{
	if($("#salesDept").combotree('isValid')==false)
	{
		$('#msginfo').html("销售部门为必选项！");
		return;
	}
	var manageCost=0;
	var earlyNumber=0;
	var finalNumber=0;
	var singleExcessAmount=0;
	var overallExcessAmount=0;
	if($('#manageC').numberbox('getValue')=="")
	{
		manageCost=0;
	}
	else
	{
		manageCost=$('#manageC').numberbox('getValue');
	}
	
	if($('#earlyN').numberbox('getValue')=="")
	{
		earlyNumber=0;
	}
	else
	{
		earlyNumber=$('#earlyN').numberbox('getValue');
	}
	
	if($('#finalN').numberbox('getValue')=="")
	{
		finalNumber=0;
	}
	else
	{
		finalNumber=$('#finalN').numberbox('getValue');
	}
	
	if($('#singleExcessA').numberbox('getValue')=="")
	{
		singleExcessAmount=0;
	}
	else
	{
		singleExcessAmount=$('#singleExcessA').numberbox('getValue');
	}
	
	if($('#overallExcessA').numberbox('getValue')=="")
	{
		overallExcessAmount=0;
	}
	else
	{
		overallExcessAmount=$('#overallExcessA').numberbox('getValue');
	}
	var json={
			"id":id,
			"salesDepartmentID":$('#salesDept').combotree("getValue"),
			"manageCost":manageCost,
			"earlyNumber":earlyNumber,
			"finalNumber":finalNumber,
			"singleExcessAmount":singleExcessAmount,
			"overallExcessAmount":overallExcessAmount,
			"infoDate":$('#infoD').datebox("getValue")
			};
	
	setDataModel("/qstSCntSys/otherInfo/updateEveryMonthOtherInfo.do",json,callback);
}
function addProduct()
{
	productItems++;
	productTRCount++;
	var tr='<tr id="plist_'+productItems+'">';
	tr+='<td style="display: none;"><input id="orderProductId_'+productItems+'" class="easyui-textbox"></input></td>';
	tr+='<td>产品名称:</td>';
	tr+='<td><input class="easyui-combobox" id="proN_'+productItems+'" data-options="required:true,editable:false,url:\'/qstSCntSys/order/selectProduct.do\',method:\'get\',valueField:\'id\',textField:\'productName\',panelHeight:\'auto\', onSelect: function(rec){$(\'#unitprice_'+productItems+'\').numberbox({prefix:\'￥\',value:rec.productPrice});}"></input></td>';
	tr+='<td>单价:</td>';
	tr+='<td><input id="unitprice_'+productItems+'" class="easyui-numberbox" data-options="required:true,precision:2,groupSeparator:\',\',decimalSeparator:\'.\',prefix:\'￥\'"></input> </td>';
	tr+='<td><span> 数量: </span></td>';
	tr+='<td><input id="odate_'+productItems+'" value="1" class="easyui-numberspinner" data-options="min:0"></input>';
	tr+=' <a id="btn_add_'+productItems+'" href="#" class="easyui-linkbutton" plain="true" onclick="addProduct()">添加</a>';
    tr+=' <a id="btn_delete_'+productItems+'"  href="#" class="easyui-linkbutton" plain="true" onclick="deleteProduct(plist_'+productItems+','+productItems+')">删除</a></td>';
    tr+='</tr>';
    //$('#btn_delete_'+(productItems-1)).show();//显示
    //$('#btn_add_'+(productItems-1)).hide();//隐藏
    $('#plist_'+(productItems-1)).after(tr);
	$.parser.parse($('#plist_'+productItems));
	//$('#btn_delete_'+productItems).show();//显示
}
function deleteProduct(trID,pItems)
{
	if(productTRCount>0){	
		var opID=$("#orderProductId_"+pItems).val();
		//parent.$.messager.alert("消息提示",opID);
		if(opID!=""){
			//parent.$.messager.alert("消息提示",1);
			var product=new Object();
			product.id=$("#orderProductId_"+pItems).val();
			orderProducts.deleteProducts.push(product);
		}
		//parent.$.messager.alert("消息提示",JSON.stringify(orderProducts));
		
	    $(trID).remove();
	    productTRCount--;
	}
}

function dateformate(id) {
	   $(id).datebox({
	       //显示日趋选择对象后再触发弹出月份层的事件，初始化时没有生成月份层
	       onShowPanel: function () {
	          //触发click事件弹出月份层
	          span.trigger('click'); 
	          if (!tds)
	            //延时触发获取月份对象，因为上面的事件触发和对象生成有时间间隔
	            setTimeout(function() { 
	                tds = p.find('div.calendar-menu-month-inner td');
	                tds.click(function(e) {
	                   //禁止冒泡执行easyui给月份绑定的事件
	                   e.stopPropagation(); 
	                   //得到年份
	                   var year = /\d{4}/.exec(span.html())[0] ,
	                   //月份
	                   //之前是这样的month = parseInt($(this).attr('abbr'), 10) + 1; 
	                   month = parseInt($(this).attr('abbr'), 10);  

	         //隐藏日期对象                     
	         $(id).datebox('hidePanel') 
	           //设置日期的值
	           .datebox('setValue', year + '-' + month); 
	                        });
	                    }, 0);
	            },
	            //配置parser，返回选择的日期
	            parser: function (s) {
	                if (!s) return new Date();
	                var arr = s.split('-');
	                return new Date(parseInt(arr[0], 10), parseInt(arr[1], 10) - 1, 1);
	            },
	            //配置formatter，只返回年月 之前是这样的d.getFullYear() + '-' +(d.getMonth()); 
	            formatter: function (d) { 
	                var currentMonth = (d.getMonth()+1);
	                var currentMonthStr = currentMonth < 10 ? ('0' + currentMonth) : (currentMonth + '');
	                return d.getFullYear() + '-' + currentMonthStr; 
	            }
	        });

	        //日期选择对象
	        var p = $(id).datebox('panel'), 
	        //日期选择对象中月份
	        tds = false, 
	        //显示月份层的触发控件
	        span = p.find('span.calendar-text'); 
	        var curr_time = new Date();

	        //设置前当月
	        $(id).datebox("setValue", myformatter(curr_time));
	}
function myformatter(date) {
    //获取年份
    var y = date.getFullYear();
    //获取月份
    var m = date.getMonth() + 1;
    return y + '-' + m;
}
function dformatter(date){
	var y = date.getFullYear();
	var m = date.getMonth()+1;
	var d = date.getDate();
	return y+'-'+(m<10?('0'+m):m)+'-'+(d<10?('0'+d):d);
}
function dparser(s){
	if (!s) return new Date();
	var ss = (s.split('-'));
	var y = parseInt(ss[0],10);
	var m = parseInt(ss[1],10);
	var d = parseInt(ss[2],10);
	if (!isNaN(y) && !isNaN(m) && !isNaN(d)){
		return new Date(y,m-1,d);
	} else {
		return new Date();
	}
}




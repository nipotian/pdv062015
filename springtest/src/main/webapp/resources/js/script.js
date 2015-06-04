
function ycdialog(fid){
	var dialog = $('<div title="Confirm">Are you sure?</div>').dialog({
		autoOpen: false,
        modal: true,
    buttons: {
    	"Cancel":  function() {
            dialog.dialog('close');
        },
        "Yes": function() {
        	dialog.dialog('close');
        	document.getElementById(fid).submit();
        	}
    }
});
	dialog.dialog('open');
}

function testt(){
	return confirm("Delete?");
}
function valid(){
	var id=document.getElementById("ipid").value.trim();
	if(!isNaN(id) && id>=100000000 && id<=999999999){
		document.getElementById("ider").innerHTML="";
		document.getElementById("ipid").value=id;
		return true;
	}
	else if(id==""){
		document.getElementById("ider").innerHTML="không được để trống";
		return false;
	}
	else{
		document.getElementById("ider").innerHTML="id phải là số có 9 chữ số";
		return false;
	}
	
}
function validname(ipname,nameer,ml,mxl){
	var name=document.getElementById(ipname).value.trim();
	if(name.length>=ml && name.length<=mxl){
		var regex=/^[a-zA-Z]+$/;
		if (!regex.test(name)){
			document.getElementById(nameer).innerHTML="ten chi gom cac chu cai a-z, A-Z";
			return false;
		}
		document.getElementById(nameer).innerHTML="";
		document.getElementById(ipname).value=name;
		return true;
	}
	else if(name==""){
		document.getElementById(nameer).innerHTML="không được để trống";
		return false;
	}
	else{
		document.getElementById(nameer).innerHTML="tên phải có độ dài từ "+ ml + " đến "+ mxl + " ký tự";
		return false;
	}
}
function validfname(){
	return validname("ipfname","fnameer",3,15);
}
function validlname(){
	return validname("iplname","lnameer",2,15);
}
function validbday(){
	var date=document.getElementById("ipbirthday").value.trim();
	if(date.length>0){
		var rg1 = /^(0?[1-9]|[12]\d|30)\/(0?[13-9]|1[0-2])\/((19|20)\d\d)$/;
		var rg2 = /^(31)\/(0?[13578]|1[02])\/((19|20)\d\d)$/;
		var rg3 = /^(0?[1-9]|1\d|2[0-8])\/(02|2)\/((19|20)\d\d)$/;
		var rg4 = /^(29)\/(02|2)\/(((19|20)(0[48]|[13579][26]|[2468][480]))|2000)$/;
		if (rg1.test(date) || rg2.test(date) || rg3.test(date) || rg4.test(date)){
			cyear=new Date().getFullYear();
			iyear=date.substr(date.length-4,4);
			if(cyear-iyear<18){
				document.getElementById("birthdayer").innerHTML="chưa đủ 18 tuổi";
				return false;
			}
			document.getElementById("birthdayer").innerHTML="";
			document.getElementById("ipbirthday").value=date;
			return true;
			
		}
		document.getElementById("birthdayer").innerHTML="dd/MM/yyyy";
		return false;
	}
	else {
		document.getElementById("birthdayer").innerHTML="";
		return true;
	}
}
function chkeform(){
	chkfname=validfname();
	chklname=validlname();
	chkbday=validbday();
	return chkfname && chklname && chkbday;
}
function chkforms(){
	chkid=valid();
	chkef=chkeform();
	return chkid && chkef;
}
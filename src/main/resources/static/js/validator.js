jQuery(function($){
	$(".form-horizontal_hoge").validate({
		  rules : {
				 last_name: {
					required: true
				 },
				 first_name: {
					required: true
				 },
				 email: {
					 required: true,
					 email: true
				 },
				 reemail: {
					 required: true,
					 email: true,
					 equalTo: "#email"
				 },	
				 tel: {
					 number: true
				 },	
				 zip: {
					 number: true
				 },
				 content: {
					 maxlength : 1000
				 },	
				 "CheckboxGroup1[]": {
					 required: true
				 }	
		  },
		  messages: {
				 last_name:{
						required: "必須項目です。入力をお願いします。"
				 },
				 first_name:{
						required: "必須項目です。入力をお願いします。"
				 },
				 email:{
						required: "必須項目です。入力をお願いします。",
						email: "Eメールの形式で入力して下さい。"
				 },
				 reemail:{
						required: "必須項目です。入力をお願いします。",
						email: "Eメールの形式で入力して下さい。",
						equalTo: "入力した値が一致しません。"

				 },
				 tel:{
						number: "数字のみ入力出来ます。"
				 },
				 zip:{
						number: "数字のみ入力出来ます。"
				 },
				 content: {
					 maxlength : "最大文字数1000を超えています。文章を短くして下さい。"
				 },	
				 "CheckboxGroup1[]" :{
						required: "必須項目です。選択して下さい。"
				 }
		  },
		  errorPlacement: function(error, element) {
						if(element.attr("name")=="CheckboxGroup1[]")
						{
							error.insertAfter("#CheckboxGroup1_error");	
						}
						else{
							error.insertAfter(element);	
						}
			  
		  }
	});
});
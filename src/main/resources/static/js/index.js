$(function(){
  //人数入力後
  $('#number-of-people').on("change", function(){
	$('.inputYenArea').css('display','');
	$('.postInfo').css('display','');
    var peopleVal = $('#number-of-people').val(); //人数を取得
    $('#template-show').find('.person').remove(); // 初期化
    $('#template-show').find('.template-item').remove(); // 初期化
    $('#template-show').find('.template-item-add').remove(); // 初期化
    for (var i = 0; i < peopleVal; i++) {
      var template = $('.template-item').html().replace(/_number_/g, i).replace(/_col_/g, 0); //テンプレートを変数に格納して、[_number_]の部分をreplaceで順になるように置き換え
      $('#template-show').append(template); //appendで#template-showの中にtemplateを追加
    }
  });
  //行追加押下後
  $('#template-show').on('click', '.addCol', function() {
	var $person = $(this).parents('.person');
	var $personRow = $(this).parents('.personRow');
	var $button = $(this);
    var col = $person.find('.yen:last').data('col');
    var number = $person.find('.yen:last').attr('data-number');
    var template = $('.template-item-add').html().replace(/_number_/g, number).replace(/_col_/g, col+1); //テンプレートを変数に格納して、[_number_]の部分をreplaceで順になるように置き換え
    //$personRow.after(template);
    $person.append(template);
    //buttonの位置を最後尾に
    $button.remove();
    $personRow.next().append($button);
  });
  
  //計算押下後
  $('.postInfo').on('click', function() {
	  initialize();
	  if(!validation()) {
		  $('#messageArea').text("必須項目を入力してください。")
		  return;
	  }
	  dispLoading("計算中...");
	  var $form = $('#postForm');
	  $.ajax({
		  url:'/result',
		  type:'POST',
		  data:$form.serialize(),
	  })
	  .done( (data) => {
		  var lineURL = 'http://line.me/R/msg/text/?';
		  var warikanURL = location.protocol + '//' + location.host;
		  var lineMsg = '';
		  var br = '%0D%0A';
		  console.log(data);
		  if(data.payByPersonList.length===0) {
			  var noPayMsg = '同じ金額を支払っているので、精算は不要です。';
			  lineMsg += noPayMsg;
			  $('.result').append("<div><span>" + noPayMsg + "</span></div>")
			  
		  } else {
			  $.each(data.payByPersonList,function(index,val) {
				  $('.result').append("<div class=\"resultPerson\">")
				  $('.result').append("<div><span class=\"resultRow\">"+val.name+"さん</span> <span>は</span></div>")
				  lineMsg += val.name+"さん は" + br;
				  $.each(val.yenByTargetName,function(key,val2) {
	    			  $('.result').append("<div><span class=\"resultRow\">"+key+"さん</span><span> から </span><span class=\"resultRow\">"+val2.toLocaleString()+"円</span><span> を</span></div>")
					  lineMsg += key+"さん から "+val2.toLocaleString()+"円 を" + br;
				  })
				  $('.result').append("<span>貰ってください</span>")
				  $('.result').append("</div>")
				  lineMsg += "貰ってください"+ br;
			  })
		  }
		  lineMsg += "【】" +warikanURL;
		  $('#lineAPI').attr('href', lineURL + lineMsg);
	      $('#modalAreaResult').fadeIn();

	  })
	  .fail( (data) => {
		  console.log(data);
	  })
	  .always( (data) => {
		  removeLoading();
	  });
  });

  //一時保存押下後
  $('.postTmpInfo').on('click', function() {
	  $('#tmpSaveMessageArea').text('');
	  dispLoading("一時保存中...");
	  var $form = $('#postForm');
	  $.ajax({
		  url:'/tmpsave',
		  type:'POST',
		  data:$form.serialize(),
	  })
	  .done( (data) => {
		  if(data.flg) {
			  $('#tmpSaveMessageArea').html('<span>一時保存しました。</span>');
		  } else {
			  $('#tmpSaveMessageArea').html('<span>一時保存に失敗しました。<br />'+data.msg+'</span>');
		  }
	  })
	  .fail( (data) => {
		  console.log(data);
	  })
	  .always( (data) => {
		  removeLoading();
	  });
  });

  //新規登録押下後
  $('#signUpButton').click(function(){
	  $('#signUpMessage').text('');
	  var $form = $('#signUpForm');
	  if(!validationSignUp()) {
		  $('#signUpMessage').text("必須項目を入力してください。")
		  return;
	  }
	  $.ajax({
		  url:'/signupCheck',
		  type:'POST',
		  data:$form.serialize(),
	  })
	  .done( (data) => {
		  console.log(data);
		  if(data.flg) {
			  $('#signUpForm').submit();
		  } else {
			  $('#signUpMessage').text('すでに使用されているIDです。');
		  }
	  })
	  .fail( (data) => {
		  console.log(data);
	  })
	  .always( (data) => {
		  
	  });
  });
  
  //ログイン押下後
  $('#loginButton').click(function(){
	  $('#loginMessage').text('');
	  if(!validationLogin()) {
		  $('#loginMessage').text("必須項目を入力してください。")
		  return;
	  }
	  var $form = $('#loginForm');
	  $.ajax({
		  url:'/loginCheck',
		  type:'POST',
		  data:$form.serialize(),
	  })
	  .done( (data) => {
		  console.log(data);
		  if(data.flg) {
			  $('#loginForm').submit();
		  } else {
			  $('#loginMessage').text('ログインに失敗しました。');
		  }
	  })
	  .fail( (data) => {
		  console.log(data);
	  })
	  .always( (data) => {
		  
	  });
  });
  
  //計算時のバリデーションチェック
  function validation() {
	  var flg = true;
	  $('form .peopleName').each(function(i, e) {
		  if(this.value === "") {
			  flg = false;
			  e.style.border = "solid 1px red";
		  }
	  })
	  $('form .yen').each(function(i, e) {
		  if(this.value === "") {
			  flg = false;
			  e.style.border = "solid 1px red";
		  }
	  })
	  return flg;
  }
  //新規登録時のバリデーションチェック
  function validationSignUp() {
	  var flg = true;
	  $('#signUpForm input').each(function(i, e) {
		  if(this.value === "") {
			  flg = false;
			  e.style.border = "solid 1px red";
		  }
	  })
	  return flg;
  }
  //ログイン時のバリデーションチェック
  function validationLogin() {
	  var flg = true;
	  $('#loginForm input').each(function(i, e) {
		  if(this.value === "") {
			  flg = false;
			  e.style.border = "solid 1px red";
		  }
	  })
	  return flg;
  }

  //計算押下時、CSSをリセット
  function initialize() {
	  $('#messageArea').text("")
	  $('#tmpSaveMessageArea').text("");
	  $('.result').text("")
	  $('form .peopleName').each(function(i, e) {
		  e.style.border = "solid 1px #ced4da";
	  })
	  $('form .yen').each(function(i, e) {
		  e.style.border = "solid 1px #ced4da";
	  })
  }
  
  //モーダル開閉
  $('#openModal').click(function(){
      $('#modalArea').fadeIn();
  });
  $('#closeModal , #modalBg').click(function(){
    $('#modalArea').fadeOut();
  });
  $('#openModalLogin').click(function(){
      $('#modalAreaLogin').fadeIn();
  });
  $('#closeModalLogin , #modalBgLogin').click(function(){
    $('#modalAreaLogin').fadeOut();
  });
  $('#openModalResult').click(function(){
      $('#modalAreaResult').fadeIn();
  });
  $('#closeModalResult , #modalBgResult').click(function(){
    $('#modalAreaResult').fadeOut();
  });
  $('.modalBgResult').click(function(){
	    $('#modalAreaResult').fadeOut();
	  });
  
  /* ------------------------------
  Loading イメージ表示関数
  引数： msg 画面に表示する文言
  ------------------------------ */
 function dispLoading(msg){
   // 引数なし（メッセージなし）を許容
   if( msg == undefined ){
     msg = "";
   }
   // 画面表示メッセージ
   var dispMsg = "<div class='loadingMsg'>" + msg + "</div>";
   // ローディング画像が表示されていない場合のみ出力
   if($("#loading").length == 0){
     $("body").append("<div id='loading'>" + dispMsg + "</div>");
   }
 }
  
 /* ------------------------------
  Loading イメージ削除関数
  ------------------------------ */
 function removeLoading(){
   $("#loading").remove();
 }
  
});
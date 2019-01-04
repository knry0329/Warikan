$(function(){
  $('#number-of-people').on("change", function(){
	$('.inputYenArea').css('display','');
	$('.postInfo').css('display','');
    var peopleVal = $('#number-of-people').val(); //人数を取得
    $('#template-show').find('.template-item').remove(); // 初期化
    $('#template-show').find('.template-item-add').remove(); // 初期化
    for (var i = 0; i < peopleVal; i++) {
      var template = $('.template-item').html().replace(/_number_/g, i).replace(/_col_/g, 0); //テンプレートを変数に格納して、[_number_]の部分をreplaceで順になるように置き換え
      $('#template-show').append(template); //appendで#template-showの中にtemplateを追加
    }
  });

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
  
  $('.postInfo').on('click', function() {
	  initialize();
	  if(!validation()) {
		  $('#messageArea').text("必須項目を入力してください。")
		  return;
	  }
	  var $form = $('#postForm');
	  $.ajax({
		  url:'/result',
		  type:'POST',
		  data:$form.serialize(),
	  })
	  .done( (data) => {
		  console.log(data);
		  if(data.payByPersonList.length===0) {
			  $('.result').append("<div><span>同じ金額を支払っているので、精算は不要です。</span></div>")
		  } else {
			  $.each(data.payByPersonList,function(index,val) {
				  $('.result').append("<div><span>"+val.name+"さんは</span></div>")
				  $.each(val.yenByTargetName,function(key,val2) {
	    			  $('.result').append("<div><span>"+key+"さんから"+val2.toLocaleString()+"円を</span></div>")
				  })
				  $('.result').append("<span>貰ってください</span>")
			  })
		  }
	  })
	  .fail( (data) => {
		  console.log(data);
	  })
	  .always( (data) => {
		  
	  });
  });
  
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
  function initialize() {
	  $('#messageArea').text("")
	  $('.result').text("")
	  $('form .peopleName').each(function(i, e) {
		  e.style.border = "solid 1px #ced4da";
	  })
	  $('form .yen').each(function(i, e) {
		  e.style.border = "solid 1px #ced4da";
	  })
  }
  
});
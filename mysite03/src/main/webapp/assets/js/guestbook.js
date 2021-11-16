/**
 *
 */

/* guestbook application based on jQuery */
/*
		homework ex1: 리스트(list)
		- no 기준의 리스트를 부분적(3개씩) 가져와서 리스트 렌더링(append)
		- 버튼 이벤트 구현 -> 스크롤 이벤트 바꾼다.
		- no 기준으로 동적 쿼리를 레포지토리에 구현한다.
		- 렌더링 참고: /ch08/test/gb/ex1
		homework ex2: 메세지 등록(add)
		- validation
		- message 기반 dialog plugin 사용법
		- form submit 막기
		- 데이터 하나를 렌더링(prepend)
		- 참고: /ch08/test/gb/ex2
		homework ex3: 메세지 삭제(delete)
		- a tag 기본동작 막기
		- live event
		- form 기반 dialog plugin 사용법
		- 응답에 대해 해당 li 삭제
		- 비밀번호가 틀린 경우(삭제실패, no=0) 사용자한테 알려주는 UI
		- 삭제가 성공한 경우(no > 0), data-no=10 인 li element를 삭제
		- 참고: /ch08/test/gb/ex1,ex2,ex3
	*/
/////////////////////////////////////////////////////////////////////

var messageBox = function (title, message, callback) {
  $("#dialog-message").attr("title", title);
  $("#dialog-message p").text(message);
  $("#dialog-message").dialog({
    modal: true,
    buttons: {
      확인: function () {
        $(this).dialog("close");
      },
    },
    close: callback,
  });
};

var fetch = function() {
	var url = '${pageContext.request.contextPath }/api/guestbook/list' + (startNo ? ('?sn=' + startNo) : '');
	$.ajax({
		url: url,
		dataType: 'json',
		type: 'get',
		success: function(response) {
			console.log(response);
			var html = listEJS.render(response);
			$("#list-guestbook").append(html);
			startNo = $('#list-guestbook li').last().data('no') || 0;
			console.log(startNo);
		}
	});
};

$(function () {
  var page = 0;

  // 첫번째 리스트 가져오기

  $(function () {
    // 최초 데이터 가져오기
    fetch();
    //스크롤이 최하단 으로 내려가면 리스트를 조회하고 page를 증가시킨다.
    //만약 스크롤
    $(window).scroll(function () {
      if ($(window).scrollTop() >= $(document).height() - $(window).height()) {
        page = page + 3;
        fetch();
      }
    });
  });

  // form validation
  $("#add-form").submit(function (event) {
    event.preventDefault();

    vo = {};
    // 이름
    vo.name = $("#input-name").val();
    if (!vo.name) {
      messageBox("새글 작성", "이름은 반드시 입력해야 합니다.", function () {
        $("#input-name").focus();
      });
      return;
    }

    // 비밀번호
    vo.password = $("#input-password").val();
    // 내용
    vo.message = $("#tx-content").val();

    //데이터 등록
    $.ajax({
      url: "${pageContext.request.contextPath }/guestbook/api/add",
      dataType: "json",
      type: "post",
      contentType: "application/json",
      data: JSON.stringify(vo),
      success: function (response) {
        var vo = response.data;

        var html = listItemEJS.render(response.data);
        $("#list-guestbook").prepend(html);
      },
    });
  });
  //live event : 존재하지 않는 element의 이벤트 핸들러를 미리 등록한다.
  // delegation(위임하다) -> document
  $(document).on("click", "#list-guestbook li a", function (event) {
    event.preventDefault();
    let no = $(this).data("no");
    $("#hidden-no").val(no);

    deleteDialog.dialog("open");
  });
  // 삭제 다이알로그 객체 만들기
  var dialogDelete = $("#dialog-delete-form").dialog({
    autoOpen: false,
    width: 300,
    height: 220,
    modal: true,
    buttons: {
      삭제: function () {
        // ajax 삭제....
        const no = $("#hidden-no").val();
        const password = $("#password-delete").val();
        $.ajax({
          url: "${pageContext.request.contextPath }/guestbook/api/delete/" + no,
          dataType: "json",
          type: "post",
          data: "password=" + password,
          success: function (response) {
            //비밀번호가 틀린경우
            //==data값이 -1 일 떄
            if (response.data == -1) {
              $(".validataTips.error").show();
              return;
            }

            $("list-guestbook li[data-no=" + response.data + "]").remove();
            deleteDialog.dialog("close");
          },
        });
      },
      취소: function () {
        $(this).dialog("close");
      },
    },
    close: function () {
      //1. password 비우기
      //2. no 비우기
      //3. error message 숨기기.
      $(".validateTips.error").hide();
      $("#hidden-no").val("");
      $("password-delete").val("");
    },
  });
});

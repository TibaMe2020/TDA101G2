let pathName = window.location.pathname;
const projectUrl = "http://" + window.location.host + pathName.substring(0, pathName.indexOf('/', 1));
//linepay

function linepayReq() {
    let total = $("input.bookingTotal").attr("data-price");
   
    var url = 'https://cors-anywhere.herokuapp.com/https://sandbox-api-pay.line.me/v2/payments/request'
    let data = {
        "productName": "petbox",
        "productImageUrl": "https://via.placeholder.com/84x84",
        "amount": total,
        "currency": "TWD",
        "confirmUrl": projectUrl + "/front-end/store/catchLinePay.jsp",
        "orderId": uuidv4()
    };
    $.ajax({
        url: url,
        dataType: 'json',
        data: JSON.stringify(data),
        type: 'POST',
        dataType: "json",
        headers: {
            // 'Access-Control-Allow-Origin': '*',
            // 'Access-Control-Allow-Methods': 'POST',
            // 'Access-Control-Allow-Headers': 'x-requested-with,content-type',
            'Content-Type': 'application/json',
            'X-LINE-ChannelId': '1654393823',
            'X-LINE-ChannelSecret': '621b6fda656e715f4d734a02d53cfe36'
        },
        error: function (xhr) {         // request 發生錯誤的話執行
            console.log(xhr.responseText);
        },
        success: function (data) {
            console.log(data);
            console.log(data.info.paymentUrl.web);
            // $('#linepay').attr("href", data.info.paymentUrl.web)
            sessionStorage.setItem('amount', amount)
            let lineHtml = `<iframe src=${data.info.paymentUrl.web} style="height:650px;"title="LinePay"></iframe>`
            $('#linePayModal').find("div.modal-content").html(lineHtml);

        }
    });
}

// 選擇門市
$('#Selectbtn').on('click', function () {
    $(document).ready(function () {
        $("#myModal").modal('show');
    });
});


// 信用卡動畫

$('.input-cart-number').on('keyup change', function () {
    $t = $(this);

    if ($t.val().length > 3) {
        $t.next().focus();
    }

    var card_number = '';
    $('.input-cart-number').each(function () {
        card_number += $(this).val() + ' ';
        if ($(this).val().length == 4) {
            $(this).next().focus();
        }
    })

    $('.credit-card-box .number').html(card_number);
});

$('#card-holder').on('keyup change', function () {
    $t = $(this);
    $('.credit-card-box .card-holder div').html($t.val());
});

$('#card-holder').on('keyup change', function () {
    $t = $(this);
    $('.credit-card-box .card-holder div').html($t.val());
});

$('#expire-month, #expire-year').change(function () {
    m = $('#expire-month option').index($('#expire-month option:selected'));
    m = (m < 10) ? '0' + m : m;
    y = $('#expire-year').val().substr(2, 2);
    $('.card-expiration-date div').html(m + '/' + y);
})

$('#card-ccv').on('focus', function () {
    $('.credit-card-box').addClass('hover');
}).on('blur', function () {
    $('.credit-card-box').removeClass('hover');
}).on('keyup change', function () {
    $('.ccv div').html($(this).val());
});

setTimeout(function () {
    $('#card-ccv').focus().delay(1000).queue(function () {
        $(this).blur().dequeue();
    });
}, 500);


// 信用卡輸入
$('#animatcredit').on('click', function () {
    console.log(123)
    $(document).ready(function () {
        $("#creditanimation").modal('show');
    });
});
var host = window.location.host;
var path = window.location.pathname;
var webCtx = path.substring(0, path.indexOf('/', 1));
var url="http://" + host + webCtx;
// 送出訂單
$('#Final_order').on('click', function () {
	let checked = $('input[name="payment"]:checked').val();
    const swalWithBootstrapButtons = Swal.mixin({
        customClass: {
            confirmButton: 'btn btn-success',
            cancelButton: 'btn btn-danger'
        },
        buttonsStyling: false
    })

    swalWithBootstrapButtons.fire({
        title: '確定送出訂單',
        icon: 'warning',
        showCancelButton: true,
        confirmButtonText: '送出訂單',
        cancelButtonText: '返回商城首頁', 
        reverseButtons: true
    }).then((result) => {
    	if (result.value) {
    		
        	SetOrder();
	    	  Swal.fire({
	    	      position: 'center',
	    	      icon: 'success',
	    	      title: '購買成功',
	    	      showConfirmButton: false,
	    	      timer: 1500
	    	  });
	    	  setTimeout(function(){
	    		 window.location.href = url+'/front-end/member/shoppingList.jsp'
	    		  
	    	  }, 1000);
    	  } else if (
    	    /* Read more about handling dismissals below */
    	    result.dismiss === Swal.DismissReason.cancel
    	  ) {
    		  window.location.href=`ProductHomepage.jsp`
//    	    swalWithBootstrapButtons.fire(
//    	      'Cancelled',
//    	      'Your imaginary file is safe :)',
//    	      'error'
//    	    )
    	  }

    })
		
});



//直接購買

$("confirmButtonText").on("click", function () {
  Swal.fire({
      position: 'center',
      icon: 'success',
      title: '購買成功',
      showConfirmButton: false,
      timer: 1500
  });
});


var host = window.location.host;
var path = window.location.pathname;
var webCtx = path.substring(0, path.indexOf('/', 1));
var url="http://" + host + webCtx;

//把資料打到資料庫裡面

function SetOrder(){
//  let product_Object = {
//	member_id: ${memberVO.member_id},
//	product_order_state: "1",
//  payment: "1",
//	location: "雲林縣二崙鄉",
//	order_details: [{
//		product_version_id:product_version_id,
//		quantity: count
//	},{
//		product_version_id:product_version_id,
//		quantity: count
//	}]
//}
//	console.log('cartlist ajax', JSON.parse(localStorage.getItem("CartList")).CartList_Array)
	let cartObjects = JSON.parse(localStorage.getItem("CartList")).CartList_Array;
	let orderMaster = {};
	orderMaster.member_id = cartObjects[0].member_id;
	orderMaster.product_order_state = "1";
	orderMaster.payment = "1";
	orderMaster.location = "雲林縣二崙鄉";
	orderMaster.detail_list = [];
	console.log(cartObjects);
	for (c of cartObjects) {
		let order_detail = {
			product_version_id: c.product_version_id,
			quantity: c.product_count,
			//D新增一個product_id(惟揚)
			product_id: c.product_id
		}
		orderMaster.detail_list.push(order_detail);
	}
	
	 $.ajax({
    	 url:  url +'/Order_master', // 資料請求的網址
        type: "POST", // GET | POST | PUT | DELETE | PATCH

         data: {
			action: "setOrderMaster",
          	json:JSON.stringify(orderMaster)
         },
        success: function (response) { // request 成功取得回應後執行
        	console.log('success: ' + response);
        	localStorage.clear();
        },
        error: function (xhr) { // request 發生錯誤的話執行
            console.log('error: ', xhr);
        }
    });
}


// 抓取local修改完的資料
$(document).ready(function () {
    if (localStorage.getItem("CartList") !== null) {

        let CartList = JSON.parse(localStorage.getItem("CartList"));
        let counter = 0;
        let totalSum= 0;
        CartList.CartList_Array.forEach(function (Item, index) {
            console.log(index);
            let foo =Item.product_price*Item.product_count;
           
            totalSum += foo;
           
            $("#CartBody").append(
                `<tr class="tr" id="${"A"+ counter }">
                        <th scope="row">
                            <div class="photo">
                                <img src = "${Item.product_image}"></img>
                            </div>
                        </th>
                        <td>
                            <div class="name" style="margin-top:-20px">
                                <p>${Item.product_name}</p>
                                <p style="margin-top:0px;font-size:19px;color:red;">${Item.version_name}</p>
                            </div>
                        </td>
                        <td>
                            <div class="price">
                               <p>$
                                    <span class="price" id="pricesum" >${Item.product_price}</span>
                                </p>
                            </div>
                        </td>
                        <td id="AAA">
                            <div class="container">
                                <div id="box">
                                   <span>${Item.product_count}</span>
                                </div>
                            </div>
                        </td>

                        <td>
                            <div class="total">
                                <p>$
                                    <span class="total" id="totalsum">${foo}</span>
                                </p>
                            </div>
                        </td>
                    </tr>
                    `
            );
            counter = counter + 1;
        });
$("#totalSum").append(
		totalSum

);
    }
});


//確定
$("#ok").on("click", function () {
	  Swal.fire({
	      position: 'center',
	      icon: 'success',
	      title: '信用卡輸入成功',
	      showConfirmButton: false,
	      timer: 1500
	  });
	});

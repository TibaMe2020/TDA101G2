//  數量選擇器

(function () {
    'use strict';

    function ctrls() {
        var _this = this;

        this.counter = 1;
        this.els = {
            decrement: document.querySelector('.ctrl__button--decrement'),
            counter: {
                container: document.querySelector('.ctrl__counter'),
                num: document.querySelector('.ctrl__counter-num'),
                input: document.querySelector('.ctrl__counter-input')
            },
            increment: document.querySelector('.ctrl__button--increment')
        };

        this.decrement = function () {
            var counter = _this.getCounter();
            var nextCounter = (_this.counter > 1) ? --counter : counter;
            _this.setCounter(nextCounter);
        };

        this.increment = function () {
            var counter = _this.getCounter();
            var nextCounter = (counter < 99) ? ++counter : counter;
            _this.setCounter(nextCounter);
        };

        this.getCounter = function () {
            return _this.counter;
        };

        this.setCounter = function (nextCounter) {
            _this.counter = nextCounter;
        };

        this.debounce = function (callback) {
            setTimeout(callback, 100);
        };

        this.render = function (hideClassName, visibleClassName) {
            _this.els.counter.num.classList.add(hideClassName);

            setTimeout(function () {
                _this.els.counter.num.innerText = _this.getCounter();
                _this.els.counter.input.value = _this.getCounter();
                _this.els.counter.num.classList.add(visibleClassName);
            }, 100);

            setTimeout(function () {
                _this.els.counter.num.classList.remove(hideClassName);
                _this.els.counter.num.classList.remove(visibleClassName);
            }, 200);
        };

        this.ready = function () {
            _this.els.decrement.addEventListener('click', function () {
                _this.debounce(function () {
                    _this.decrement();
                    _this.render('is-decrement-hide', 'is-decrement-visible');
                });
            });

            _this.els.increment.addEventListener('click', function () {
                _this.debounce(function () {
                    _this.increment();
                    _this.render('is-increment-hide', 'is-increment-visible');
                });
            });

            _this.els.counter.input.addEventListener('input', function (e) {
                var parseValue = parseInt(e.target.value);
                if (!isNaN(parseValue) && parseValue >= 0) {
                    _this.setCounter(parseValue);
                    _this.render();
                }
            });

            _this.els.counter.input.addEventListener('focus', function (e) {
                _this.els.counter.container.classList.add('is-input');
            });

            _this.els.counter.input.addEventListener('blur', function (e) {
                _this.els.counter.container.classList.remove('is-input');
                _this.render();
            });
        };
    };

    // init
    var controls = new ctrls();
    document.addEventListener('DOMContentLoaded', controls.ready);
})();


// 商品圖片選擇
$("#product_photo2_1").find("img").on("click", function () {
    let src = $(this).attr("src");
    $("#product_photo1").children("img").attr("src", src);

});


//購物車

$("#shoppingcart").on("click", function () {
	 	let count = parseInt($("#account").val());
	    let product_id = $("#product_name").attr("data-product_ID");
	    let product_version_id = $(".btn_version.-on").attr("data-version-id");
	    let version_name = $(".btn_version.-on").html();
	    let name = $("#product_name").html();
	    let image = $("#FirstProductImage").attr("src");
	    let price = parseInt($("#FirstProductPrice").html());
	    let member_id = $('#buy').attr("data-memberId");
	    console.log(member_id);
	    
	    let product_Object = {
	    	
	    	member_id: member_id,	
	    	product_id:product_id,
	    	product_version_id:product_version_id,
	    	version_name:version_name,
	        product_image: image,
	        product_name: name,    
	        product_price: price,
	        product_count: count,
	    }
	    

	    if (localStorage.getItem("CartList") !== null) {

	        let CartList = JSON.parse(localStorage.getItem("CartList"));
	        let IsProductSame = false;
	        CartList.CartList_Array.forEach(function (Item, index) {

	            if (Item.product_version_id == product_Object.product_version_id) {
	                Item.product_count = parseInt(count) + parseInt(Item.product_count)
	                IsProductSame = true;

	            }
	        })

	        if (IsProductSame != true) {
	            CartList.CartList_Array.push(product_Object);
	        }

	        localStorage.setItem("CartList", JSON.stringify(CartList));

	    } else {

	        let Array = [];
	        Array.push(product_Object);
	        let json = JSON.stringify({
	            CartList_Array: Array
	        })
	        localStorage.setItem("CartList", json);
	    }

    Swal.fire({
        position: 'center',
        icon: 'success',
        title: '已經加入購物車',
        showConfirmButton: false,
        timer: 1500
    });

});


//跳轉頁面
$("#buy").on("click", function () {
//	e.preventDefault();
	
    let count = $("#account").val();
    let product_id = $("#product_name").attr("data-product_ID");
    let product_version_id = $(".btn_version.-on").attr("data-version-id");
    let version_name = $(".btn_version.-on").html();
    let name = $("#product_name").html();
    let image = $("#FirstProductImage").attr("src");
    let price = parseInt($("#FirstProductPrice").html());
    let member_id = $('#buy').attr("data-memberId");
    console.log(member_id);
    
    let product_Object = {
    	
    	member_id: member_id,	
    	product_id:product_id,
    	product_version_id:product_version_id,
    	version_name:version_name,
        product_image: image,
        product_name: name,    
        product_price: price,
        product_count: count,
    }
    

    if (localStorage.getItem("CartList") !== null) {

        let CartList = JSON.parse(localStorage.getItem("CartList"));
        let IsProductSame = false;
        CartList.CartList_Array.forEach(function (Item, index) {

            if (Item.product_version_id == product_Object.product_version_id) {
                Item.product_count = parseInt(count) + parseInt(Item.product_count)
                IsProductSame = true;

            }
        })

        if (IsProductSame != true) {
            CartList.CartList_Array.push(product_Object);
        }

        localStorage.setItem("CartList", JSON.stringify(CartList));

    } else {

        let Array = [];
        Array.push(product_Object);
        let json = JSON.stringify({
            CartList_Array: Array
        })
        localStorage.setItem("CartList", json);
    }

});









$(document).on("click", ".version1", function () {
    $(".version1").removeClass("-on");
    $(this).toggleClass("-on");
});


// 動態產生
$(".btn_version").on("click", function () {
    let version_price = $(this).attr("data-price");
//    alert(version_price);
    $('#FirstProductPrice').text(version_price);
})



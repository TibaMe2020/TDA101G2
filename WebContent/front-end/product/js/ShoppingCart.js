// 商品刪除
$(document).on("click", ".fa-trash", function () {
    const swalWithBootstrapButtons = Swal.mixin({
        customClass: {
            confirmButton: 'btn btn-success',
            cancelButton: 'btn btn-danger'
        },
        buttonsStyling: false
    })

    swalWithBootstrapButtons.fire({
        title: '確定刪除嗎??',
        text: "你確定不保留商品!",
        icon: 'warning',
        showCancelButton: true,
        confirmButtonText: '確定刪除!',
        cancelButtonText: '取消!',
        reverseButtons: true
    }).then((result) => {
        if (result.value) {
            swalWithBootstrapButtons.fire(
                '刪除',
                '你的商品已經被刪除',
                'success'
            )
            $(this).closest("tr").remove();
            let deleteBefore = getCartList ();
            let getversion_id = $(this).attr("data-version-id");
            let bar = "";
            
            $(deleteBefore).each(function(index, item){
            	if(this.product_version_id == getversion_id){
            		bar = index;
            	}
            });
            
            deleteBefore.splice(bar, 1);
            console.log(deleteBefore);
            setCartList(deleteBefore);
            
            let CartList = JSON.parse(localStorage.getItem("CartList"));
            console.log(CartList);
            
            let foo = [];
            $(CartList.CartList_Array).each(function(index, item){

            });
			if(deleteBefore.length==0){
				$( "#buy" ).addClass( "disabled" );
				$("#CartBody").append(
				    	`
				    	       <tr class="tr" >
                        <th scope="row" >
                            
                        </th>
                        <td>
                           
                        </td>
                        <td>
                            <div class="price">                           
                                <img src="/TDA101G2/resources/images/ProductImage/empty/shopping_cart_empty.jpg" style="width: 400px;">
                            </div>
                        </td>
                        <td id="AAA">
                            
                        </td>

                        <td>
                           
                        </td>

                        <td>
                           
                        </td>
                    < /tr>
				    	`		
				    	
				    	)
			}
        } else if (
            /* Read more about handling dismissals below */
            result.dismiss === Swal.DismissReason.cancel
        ) {
            swalWithBootstrapButtons.fire(
                '取消',
                '你的商品還在',
                'error'
            )
        }
    })

});;


// 數量

$(document).on("click", ".next", function () {
    // 數量
    let foo = $(this).closest(".container").find("#box span").html();
    foo = parseInt(foo);
    let price = $(this).closest('tr.tr').find('span.price').html();
// console.log(price)
    $(this).closest(".container").find("#box span").html(foo + 1);
    let count= foo + 1
    console.log(count)
    $(this).closest('tr.tr').find('span.total').html(price * (foo + 1));

    if (foo >= 99) {
        foo = 0;
    }
    
    
    let pricebefore =getCartList ();
    let getversion_id = $(this).attr("data-version-id");
    let bar = "";
    $(pricebefore).each(function(index, item){
    	if(this.product_version_id == getversion_id){
    		bar = index;
    		item.product_count = parseInt(item.product_count) + 1;   	
    	}
    });
    total();
    setCartList(pricebefore);
    
    

});

$(document).on("click", ".prev", function () {
    let foo = $(this).closest(".container").find("#box span").html();
    let price = $(this).closest('tr.tr').find('span.price').html();
    foo = parseInt(foo);
    if (foo < 2) {
        foo = 2;
    }
    $(this).closest(".container").find("#box span").html(foo - 1);
    $(this).closest('tr.tr').find('span.total').html(price * (foo - 1));
    
    let pricebefore =getCartList ();
    let getversion_id = $(this).attr("data-version-id");
    let bar = "";
    $(pricebefore).each(function(index, item){
    	if(this.product_version_id == getversion_id){
    		bar = index;
    		item.product_count = parseInt(item.product_count) - 1;   	
    	}
    });
    total();
    setCartList(pricebefore);
    
});






// 送出訂單
$('#select_order').on('click', function () {
    Swal.fire({
        position: 'center',
        icon: 'success',
        title: '訂單已完成',
        showConfirmButton: false,
        timer: 1500
    })
});

var host = window.location.host;
var path = window.location.pathname;
var webCtx = path.substring(0, path.indexOf('/', 1));
var url="http://" + host + webCtx;


// 顯示出商品介面
$(document).ready(function () {
    if (localStorage.getItem("CartList") !== null && getCartList().length !== 0) {    	
        let CartList = JSON.parse(localStorage.getItem("CartList"));
        let counter = 0;

        CartList.CartList_Array.forEach(function (Item, index) {

        
            $("#CartBody").append(

                `<tr class="tr" id="${"A"+ counter }">
                        <th scope="row" >
                            <div class="photo" style="padding-top: 25px;">
                               <img src = "${Item.product_image}"></img>
                            </div>
                        </th>
                        <td>
                            <div class="name">
                                <p>${Item.product_name}</p>
                               
                                <p style="margin-top:0px">${Item.version_name}</p>
                               
                            </div>
                        </td>
                        <td>
                            <div class="price" >
                                <p>$
                                    <span class="price" id="pricesum" >${Item.product_price}</span>
                                </p>
                            </div>
                        </td>
                        <td id="AAA">
                            <div class="container" id="account">
                                <span class="next" data-version-id="${Item.product_version_id}"></span>
                                <span class="prev" data-version-id="${Item.product_version_id}"></span>
                                <div id="box">
                                    <span>${Item.product_count}</span>
                                </div>
                            </div>
                        </td>

                        <td>
                            <div class="total" >
                                <p>$
                                    <span class="total" id="totalsum">${Item.product_count * Item.product_price}</span>
                                </p>
                            </div>
                        </td>

                        <td>
                            <i class="fa fa-trash" id="trash" data-version-id="${Item.product_version_id}">

                            </i>
                        </td>
                    < /tr>
                    `

            );
            counter = counter + 1;
        });

    } else {
    	
    	$( "#buy" ).addClass( "disabled" );
    	$("#CartBody").append(
    	`
                        <tr class="tr" >
                        <th scope="row" >
                            
                        </th>
                        <td>
                           
                        </td>
                        <td>
                            <div class="price">                           
                                <img src="/TDA101G2/resources/images/ProductImage/empty/shopping_cart_empty.jpg" style="width: 400px;">
                            </div>
                        </td>
                        <td id="AAA">
                            
                        </td>

                        <td>
                           
                        </td>

                        <td>
                           
                        </td>
                    < /tr>

    	`		
    	
    	)
    }
    	
 
});




// 小計

function total() {
    let foo = $(this).closest(".container").find("#box span").html();
    foo = parseInt(foo);
    let price = $(this).closest('tr.tr').find('span.price').html();
    $(this).closest('tr.tr').find('span.total').html(price * foo);
}
// 取localstorage的值轉換
function getCartList () {
	let jsonStr = localStorage.getItem("CartList");
	let obj = JSON.parse(jsonStr);
	let cartList = obj.CartList_Array;
	return cartList; 
}
// 把localstorage的值丟回去
function setCartList(cartList) {
	let obj = {
		"CartList_Array" : cartList
	};
	localStorage.setItem("CartList", JSON.stringify(obj));
}

// 刪除單筆再存入localstorage
$(document).on("click","#trash",function(){
	let deleteBefore= getCartList ();
	
})
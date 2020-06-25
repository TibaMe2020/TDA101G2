//succes
function AjaxSucces(response) {
    console.log('success', response);

    let length = response.length / 4;
    let fatherNode = $("#newProduct");
    let children = '';
    fatherNode.html('');
    let i = 0;
    let endIndex = 4;
    for (let start = 0; start <= length; start++) {
        let spliceData = response.slice(start * 4, endIndex)
        endIndex = endIndex + 4

        let children = spliceData.map(p => `
                <div class="col-sm-12 col-md-3">
                    <div class="card" style="width:100%">
                        <img class="card-img-top" src="http://${host}${webCtx}/Product_Image?product_id=${p.product_id}&image=1" alt="Card image"
                            style="width:100%">
                        <div class="card-body">
                            <h4 class="card-title">${p.name}</h4>
                                    <span class="star"><i class="fas fa-star " style=" ${p.score >=1 ? " color: #EF8216 " : ""} "></i></span>
                                    <span class="star"><i class="fas fa-star " style=" ${p.score >=2 ? " color: #EF8216 " : ""} "></i></span>
                                    <span class="star"><i class="fas fa-star " style=" ${p.score >=3 ? " color: #EF8216 " : ""} "></i></span>
                                    <span class="star"><i class="fas fa-star " style=" ${p.score >=4 ? " color: #EF8216 " : ""} "></i></span>
                                    <span class="star"><i class="fas fa-star " style=" ${p.score >=5 ? " color: #EF8216 " : ""} "></i></span>
                            <p class="card-text"> $${p.price}</p>
                            <a href="http://${host}${webCtx}/front-end/product/Product.jsp?product_id=${p.product_id}" class="btn btn-primary stretched-link"> 購 買 </a>
                        </div>
                    </div>
                </div>
                `).join('')

        children = `<div class="row">${children}</div>`
        fatherNode.append(children)
    }
}
var host = window.location.host;
var path = window.location.pathname;
var webCtx = path.substring(0, path.indexOf('/', 1));
var url="http://" + host + webCtx;

//最新日期

$("#NewDate").on("click", function () {

    // console.log(this.id)
    $.ajax({
    	
    	url:  url +`/Product?action=NewDate`, // 資料請求的網址
        type: "GET", // GET | POST | PUT | DELETE | PATCH
        dataType: 'text json', // 預期會接收到回傳資料的格式： json | xml | html

        success: function (response) { // request 成功取得回應後執行
            AjaxSucces(response);

        },
        error: function (xhr) { // request 發生錯誤的話執行
            console.log('error', xhr);
            // console.log(xhr);
        },
        complete: function (xhr) { // request 完成之後執行(在 success / error 事件之後執行)
            console.log('complete', xhr);
            console.log(xhr);
        }
    });
});

// 價錢最高

$("#HighPrice").on("click", function () {

    // console.log(this.id)
    $.ajax({
    	 url:  url +`/Product?action=HighPrice`, // 資料請求的網址
        type: "GET", // GET | POST | PUT | DELETE | PATCH
        dataType: 'text json', // 預期會接收到回傳資料的格式： json | xml | html
        // data: {
        //     "action": "highprice",
        // },
        success: function (response) { // request 成功取得回應後執行
            AjaxSucces(response);
        },
        error: function (xhr) { // request 發生錯誤的話執行
            console.log('error', xhr);
            console.log(xhr);
        },
        complete: function (xhr) { // request 完成之後執行(在 success / error 事件之後執行)
            console.log('complete', xhr);
            console.log(xhr);
        }
    });
});

// 價錢最低

$("#LowPrice").on("click", function () {

    // console.log(this.id)
    $.ajax({
    	 url:  url +`/Product?action=LowPrice`, // 資料請求的網址
        type: "GET", // GET | POST | PUT | DELETE | PATCH
        dataType: 'text json', // 預期會接收到回傳資料的格式： json | xml | html
        // data: {
        //     "action": "highprice",
        // },
        success: function (response) { // request 成功取得回應後執行
            AjaxSucces(response);
        },
        error: function (xhr) { // request 發生錯誤的話執行
            console.log('error', xhr);
            console.log(xhr);
        },
        complete: function (xhr) { // request 完成之後執行(在 success / error 事件之後執行)
            console.log('complete', xhr);
            console.log(xhr);
        }
    });
});

// 最高評分

$("#HighScore").on("click", function () {

    // console.log(this.id)
    $.ajax({
        url:  url +`/Product?action=HighScore`, // 資料請求的網址
        type: "GET", // GET | POST | PUT | DELETE | PATCH
        dataType: 'text json', // 預期會接收到回傳資料的格式： json | xml | html
        // data: {
        //     "action": "highprice",
        // },
        success: function (response) { // request 成功取得回應後執行
            AjaxSucces(response);

        },
        error: function (xhr) { // request 發生錯誤的話執行
            console.log('error', xhr);
            console.log(xhr);
        },
        complete: function (xhr) { // request 完成之後執行(在 success / error 事件之後執行)
            console.log('complete', xhr);
            console.log(xhr);
        }
    });
});


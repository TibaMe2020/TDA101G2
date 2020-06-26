let path = window.location.pathname;
// console.log(window.location.host)
// console.log(path)
// console.log(path.substring(0,path.indexOf('/',1)))
const projectUrl = "http://" + window.location.host + path.substring(0, path.indexOf('/', 1))
//const projectUrl = "http://localhost:8081/TDA101G2";
// console.log(projectUrl)

window.onload = (event) => {
    let githubURL = new URL(window.location.href);
    let params = githubURL.searchParams;
    // console.log(githubURL.searchParams.toString());
    for (let pair of params.entries()) {
        // console.log(`key: ${pair[0]}, value: ${pair[1]}`)

        let text = $("a.store-dropdown")
        //        console.log(text); 
        switch (pair[1]) {
            case 'restaurant':
                text.text("寵物餐廳");
                $("#storeType1").removeClass("d-none");
                $("#storeType2").addClass("d-none");
                $("#storeType2-2").addClass("d-none");
                break;
            case 'hostel':
                text.text("寵物旅館");
                $("input.input-date-normal").closest("div").removeClass("d-none");
                $("input.input-date-checkout").closest("div").removeClass("d-none");
                $("input.input-date-gromming").closest("div").addClass("d-none");
                $("#storeType1").addClass("d-none");
                $("#storeType2").removeClass("d-none");
                $("#storeType2-2").addClass("d-none");
                $("#totalonly").addClass("d-none");
                $("#hostelTotal").removeClass("d-none");
                break;
            case 'grooming':
                text.text("寵物美容");
                $("input.input-date-normal").closest("div").addClass("d-none");
                $("input.input-date-checkout").closest("div").addClass("d-none");
                $("input.input-date-gromming").closest("div").removeClass("d-none");
                $("#storeType1").addClass("d-none");
                $("#storeType2").removeClass("d-none");
                $("#storeType2-2").addClass("d-none");
                $("#totalonly").removeClass("d-none");
                $("#hostelTotal").addClass("d-none");
                break;
            case 'school':
                text.text("寵物學校");
                $("input.input-date-normal").closest("div").removeClass("d-none");
                $("input.input-date-checkout").closest("div").addClass("d-none");
                $("input.input-date-gromming").closest("div").addClass("d-none");
                $("#storeType1").addClass("d-none");
                $("#storeType2").removeClass("d-none");
                $("#storeType2-2").addClass("d-none");
                $("#totalonly").removeClass("d-none");
                $("#hostelTotal").addClass("d-none");
                break;
            case 'hospital':
                text.text("寵物醫院");
                $("#storeType1").removeClass("d-none");
                $("#storeType2").addClass("d-none");
                $("#storeType2-2").addClass("d-none");
                break;
        }

        $.ajax({
            url: projectUrl + "/Store_frontController",
            // url: "http://localhost:8081/TDA101G2/Store_frontController",
            type: "GET",                  // GET | POST | PUT | DELETE | PATCH
            data: {
                "action": "store_type",
                "type": `${pair[1]}`
            },
            dataType: "json",             // 預期會接收到回傳資料的格式： json | xml | html
            beforeSend: function () {       // 在 request 發送之前執行
            },
            statusCode: {                 // 狀態碼
                200: function (res) {
                    // console.log("200")
                },
                404: function (res) {
                    console.log("400")
                },
                500: function (res) {
                    console.log("500")
                }
            },
            error: function (xhr) {         // request 發生錯誤的話執行
                console.log(xhr.responseText);
            },

            success: function (data) {
                // console.log(data);
                // $("#storelist_card").empty();
                let cards_html = '';
                if (data.length != 0) {

                    $.each(data, function (index, item) {
                        let src = 'https://via.placeholder.com/268x150';
                        if (item.store_image1 != null) {
                            let bytes = new Uint8Array(item.store_image1.length);
                            for (let i = 0; i < bytes.length; i++) {
                                bytes[i] = item.store_image1[i];
                            }
                            let blob = new Blob([bytes], { type: 'image/png' });
                            src = URL.createObjectURL(blob);
                        }
                        cards_html += `
                          <div class="col-12 col-sm-6 col-md-3 d-flex justify-content-center cardview">
                          <div class="card" style="width: 18rem;" onclick="pageMiddle(this)" name="${item.store_id}">
                            <img src="${src}" class="card-img-top" alt="..."></img>
                                  <div class="card-body">
                                      <h5 class="card-title">${item.store_name}</h5>
                                      <p class="card-text">${item.store_introduction}</p>
                                      <div class="d-flex justify-content-end"> 
                                          <a href="#" class="btn btn-primary" onclick="pageBooking(this)" name="${item.store_id}">預約</a>
                                      </div>
                                  </div>
                              </div>
                          </div>`;
                    });
                }
                $("#storelist_card").prepend(cards_html);
                // 連動頁籤 - 第一筆
                middlePage(data[0].store_id);
                bookingPage(data[0].store_id);
            }
        });
    }
};
// 卡片->介紹頁籤
function pageMiddle(e) {
    let githubURL = new URL(window.location.href);
    let params = githubURL.searchParams;
    for (let pair of params.entries()) {
        if (pair[1] == 'restaurant' || pair[1] == 'hospital') {
            $("#storeType2").addClass("d-none");
            $("#storeType2-2").addClass("d-none");
        } else {
            $("#storeType2").removeClass("d-none");
            $("#storeType2-2").addClass("d-none");
        }
    }
    $("#pills-profile-tab").tab('show');
    let id = $(e).attr("name");
    middlePage(id);
    bookingPage(id);
    $("input.bookingTotal").val('0');
}
// 預約按鈕->預約頁籤
function pageBooking(e) {
    let githubURL = new URL(window.location.href);
    let params = githubURL.searchParams;
    for (let pair of params.entries()) {
        if (pair[1] == 'restaurant' || pair[1] == 'hospital') {
            $("#storeType2").addClass("d-none");
            $("#storeType2-2").addClass("d-none");
        } else {
            $("#storeType2").removeClass("d-none");
            $("#storeType2-2").addClass("d-none");
        }
    }
    $("#pills-contact-tab").tab('show');
    // $("#pills-contact-tab").toggleClass('show');
    event.stopPropagation();
    // 連動介紹頁籤
    let id = $(e).attr("name");
    middlePage(id);
    bookingPage(id);
    $("input.bookingTotal").val('0');
}
// 介紹頁籤
// let selectedWeekday;
// let selectedDate;
function middlePage(store_id) {
    let selectedWeekday = [];
    let selectedDate = [];
    $('input.f_date1').datetimepicker('destroy')
    $('input.f_date2').datetimepicker('destroy')

    $.ajax({
        url: projectUrl + "/Store_frontController",
        // url: "http://localhost:8081/TDA101G2/Store_frontController",
        type: "GET",                  // GET | POST | PUT | DELETE | PATCH
        data: {
            "action": "getStoreVO",
            "storeId": store_id
        },
        dataType: "json",             // 預期會接收到回傳資料的格式： json | xml | html
        beforeSend: function () {       // 在 request 發送之前執行
        },
        statusCode: {                 // 狀態碼
            200: function (res) {
                // console.log("200")
            },
            404: function (res) {
                console.log("400")
            },
            500: function (res) {
                console.log("500")
            }
        },
        error: function (xhr) {         // request 發生錯誤的話執行
            console.log(xhr.responseText);
        },

        success: function (data) {
            // console.log(data)
            let st = JSON.parse(data[0]);
            if (data.length != 0) {
                $("#showStore_introduction").html(`<a style="font-weight:bold;">${st.store_name}</a><br>${st.store_introduction}`);
                let imgArray = [st.store_image1, st.store_image2, st.store_image3]
                for (i = 0; i < 3; i++) {
                    let src = 'https://via.placeholder.com/1200x800';
                    if (imgArray[i] != null) {
                        let bytes = new Uint8Array(imgArray[i].length);
                        for (let x = 0; x < bytes.length; x++) {
                            bytes[x] = imgArray[i][x];
                        }
                        let blob = new Blob([bytes], { type: 'image/png' });
                        src = URL.createObjectURL(blob);
                    }
                    $("#showStore_image" + (i + 1)).attr("src", src);
                };
                // 預約頁籤 - 店家條
                $("#stline-name").text(st.store_name);
                $("#stline-name").attr("data-store_id", st.store_id)
                $("#stline-content").text(st.store_introduction);
                $("#stline-clicks").text(st.store_clicks);

                st.store_firstbreak == 0 ? st.store_firstbreak = null : st.store_firstbreak;
                st.store_secondbreak == 0 ? st.store_secondbreak = null : st.store_secondbreak;
                st.store_firstbreak == 7 ? st.store_firstbreak = 0 : st.store_firstbreak;
                st.store_secondbreak == 7 ? st.store_secondbreak = 0 : st.store_secondbreak;
                if (st.store_firstbreak == st.store_secondbreak) {
                    st.store_secondbreak = null;
                }
                if (st.store_firstbreak != null && st.store_secondbreak != null) {
                    selectedWeekday = [st.store_firstbreak, st.store_secondbreak];
                } else if (st.store_firstbreak == null && st.store_secondbreak != null) {
                    selectedWeekday = [st.store_secondbreak];
                } else if (st.store_firstbreak != null && st.store_secondbreak == null) {
                    selectedWeekday = [st.store_firstbreak];
                } else {
                    selectedWeekday = [];
                }
                // 回填公休日
                let clo = JSON.parse(data[1]);
                for (i of clo) {
                    selectedDate.push(i.store_closed_day);
                }
                console.log(selectedWeekday);
                console.log(selectedDate);

                $.datetimepicker.setLocale('zh');
                $('input.f_date1').datetimepicker({
                    theme: '',              //theme: 'dark',
                    timepicker: true,       //timepicker:true,(有時分秒)
                    step: 60,                //step: 60 (這是timepicker的預設間隔60分鐘)
                    format: 'Y-m-d H:i',         //format:'Y-m-d H:i:s','Y-m-d',
                    // value: '<%=hiredate%>', // value:   new Date(),
                    // disabledDates: ['2020/06/08', '2020/06/09', '2020/06/10'], // 去除特定不含
                    disabledDates: selectedDate,
                    disabledWeekDays: selectedWeekday,
                    // startDate: '2020/06/10',  // 起始日
                    minDate: '-1970-01-01', // 去除今日(不含)之前
                    // maxDate: '+2030-01-01'  // 去除今日(不含)之後
                });

                let choosedate = "2020-7-1"
                // $.datetimepicker.setLocale('zh');
                $('input.f_date2').datetimepicker({
                    theme: '',              //theme: 'dark',
                    timepicker: false,       //timepicker:true,(有時分秒)
                    step: 60,                //step: 60 (這是timepicker的預設間隔60分鐘)
                    format: 'Y-m-d',         //format:'Y-m-d H:i:s','Y-m-d',
                    // value: '<%=hiredate%>', // value:   new Date(),
                    // disabledDates: ['2020/06/08', '2020/06/09', '2020/06/10'], // 去除特定不含
                    disabledDates: selectedDate,
                    disabledWeekDays: selectedWeekday,
                    // startDate: '2020/06/10',  // 起始日
                    minDate: '-1970-01-01', // 去除今日(不含)之前
                    // maxDate: '+2030-01-01'  // 去除今日(不含)之後
                });
                // console.log("公休日=" + selectedWeekday);
            }
        }
    });
}

// 預約頁籤
function bookingPage(store_id) {
    $.ajax({
        url: projectUrl + "/ServiceController_Ajax",
        // url: "http://localhost:8081/TDA101G2/ServiceController_Ajax",
        type: "GET",                  // GET | POST | PUT | DELETE | PATCH
        data: {
            "action": "getSerivceList",
            "storeId": store_id
        },
        dataType: "json",             // 預期會接收到回傳資料的格式： json | xml | html
        beforeSend: function () {       // 在 request 發送之前執行
        },
        statusCode: {                 // 狀態碼
            200: function (res) {
                // console.log("200")
            },
            404: function (res) {
                console.log("400")
            },
            500: function (res) {
                console.log("500")
            }
        },
        error: function (xhr) {         // request 發生錯誤的話執行
            console.log(xhr.responseText);
        },

        success: function (data) {
            // console.log(data);
            $("div.servicelist").empty();
            let td_html = "";
            if (data.length != 0) {
                let stable =
                    "<div class='table-responsive'>"
                    + "<table class='table'>"
                    + "<thead>"
                    + "<tr>"
                    + "<th scope='col' ></th>"
                    + "<th scope='col' style='min-width: 150px;'>服務項目</th>"
                    + "<th scope='col' style='text-align:right'>價錢</th>"
                    + "<th scope='col' >數量(1-999)</th>"
                    + "</tr>"
                    + "</thead>"
                    + "<tbody class='service_list'></tbody>"
                    + "</table>"
                    + "</div>";
                $("div.servicelist").prepend(stable);
                $.each(data, function (index, item) {
                    td_html +=
                        "<tr>"
                        + "<th scope='row' data-service_id=" + item.service_id + " class='getServiceId'>" + index + "</th>"
                        + "<td value=" + item.service_detail + ">" + item.service_detail + "</td>"
                        + "<td value=" + item.service_price + " style='text-align:right'>" + thousandComma(item.service_price) + "</td>"
                        + "<td>"
                        + "<input type='number' value='0' min='0' max='999' step='1' data-price=" + item.service_price + "></input>"
                        // +"<input type='text' size='20' name='pets'></input>"
                        // +"<input type='hidden' name='service_id' value="+item.service_id+"></input>"
                        + "</td>"
                        + "</tr>";
                });
                $("tbody.service_list").after(td_html);
            }
            $("div.table-responsive input[type='number']").inputSpinner();
            total();
            // $("div.table-responsive input[type^='number']").change(function () {
            //     console.log(123)
            //     dateCounter()
            // })
        }
    });
}
$("input.num-spinner").inputSpinner();

function total() {
    $("div.table-responsive input[type^='number']").change(function () {
        var sum = 0;
        $("div.table-responsive input[type^='number']").each(function () {
            var price = $(this).data("price");
            var count = $(this).val();

            sum += price * count;

            // document.getElementById("add").value = sum;
        });
        $("input.bookingTotal").val("$ " + thousandComma(sum));
        $("input.bookingTotal").attr("data-price", sum);
    });
}
// 金錢符號 每三位+,
var thousandComma = function (number) {
    var num = number.toString();
    var pattern = /(-?\d+)(\d{3})/;

    while (pattern.test(num)) {
        num = num.replace(pattern, "$1,$2");

    }
    return num;

}
// 計算天數
function dateCounter() {
    $("input[class*='input-date']").change(function () {
        // $("input.input-date-checkout").change(function () {
        var date1 = $("input.input-date-normal").val();
        var date2 = $("input.input-date-checkout").val();
        let count = days_between(new Date(date1), new Date(date2));
        let price = $("input.bookingTotal").attr("data-price")

        $("input.dateCount").val(count)
        // $("input.getTotal").val("$ " + thousandComma(count * price));
        $("#getTotal").val("$ " + thousandComma(count * price));
    })
}
dateCounter()
// 回填初始日
$("#checkinDate").change(function () {
    let day = new Date($(this).val())
    let nextday = day.setDate(day.getDate() + 1)
    $("#checkoutDate").datetimepicker({
        minDate: nextday,
        startDate: nextday
    })
})

function days_between(date1, date2) {

    // The number of milliseconds in one day
    var ONE_DAY = 1000 * 60 * 60 * 24

    // Convert both dates to milliseconds
    var date1_ms = date1.getTime()
    var date2_ms = date2.getTime()

    // Calculate the difference in milliseconds
    var difference_ms = Math.abs(date1_ms - date2_ms)

    // Convert back to days and return
    return Math.round(difference_ms / ONE_DAY)

}

// 預約 上一步.下一步
$("button.nextstep").on("click", function () {
    $("#storeType2").addClass("d-none");
    $("#storeType2-2").removeClass("d-none");
    dateCounter()
    $("input[class*='input-date']").change()
})
$("button.previous-step").on("click", function () {
    $("#storeType2").removeClass("d-none");
    $("#storeType2-2").addClass("d-none");
    dateCounter()
    $("input[class*='input-date']").change()
})

// 介紹頁籤 - 預約按鈕
$("button.booking").on("click", function () {
    $("#pills-contact-tab").tab('show');
})

// 大吳老師- 日期套件
// $.datetimepicker.setLocale('zh');
// $('input.f_date1').datetimepicker({
//     theme: '',              //theme: 'dark',
//     timepicker: true,       //timepicker:true,(有時分秒)
//     step: 60,                //step: 60 (這是timepicker的預設間隔60分鐘)
//     format: 'Y-m-d H:i',         //format:'Y-m-d H:i:s','Y-m-d',
//     // value: '<%=hiredate%>', // value:   new Date(),
//     // disabledDates: ['2020/06/08', '2020/06/09', '2020/06/10'], // 去除特定不含
//     disabledDates: selectedDate,
//     disabledWeekDays: selectedWeekday,
//     // startDate: '2020/06/10',  // 起始日
//     minDate: '-1970-01-01', // 去除今日(不含)之前
//     // maxDate: '+2030-01-01'  // 去除今日(不含)之後
// });
// $.datetimepicker.setLocale('zh');
// $('input.f_date2').datetimepicker({
//     theme: '',              //theme: 'dark',
//     timepicker: false,       //timepicker:true,(有時分秒)
//     step: 60,                //step: 60 (這是timepicker的預設間隔60分鐘)
//     format: 'Y-m-d',         //format:'Y-m-d H:i:s','Y-m-d',
//     // value: '<%=hiredate%>', // value:   new Date(),
//     // disabledDates: ['2020/06/08', '2020/06/09', '2020/06/10'], // 去除特定不含
//     disabledDates: selectedDate,
//     disabledWeekDays: selectedWeekday,
//     // startDate: '2020/06/10',  // 起始日
//     minDate: '-1970-01-01', // 去除今日(不含)之前
//     // maxDate: '+2030-01-01'  // 去除今日(不含)之後
// });

// 確認預約按鈕

$("button.btn_confirm").on("click", function () {
    let checkmail = false;
    let checkphone = false;
    let checkdate = false;
    let current = $(this).closest("div.reservation");

    // 檢查eamil格式
    var emailReg = /^([a-z0-9_\.-]+)@([\da-z\.-]+)\.([a-z\.]{2,6})$/
    let email = $(this).closest("div.reservation").find("input[type='email']");
    let eamilval = email.val();
    if (!emailReg.test(eamilval)) {
        email.removeClass("is-valid")
        email.addClass("is-invalid")
        checkmail = false;
    } else {
        email.removeClass("is-invalid")
        email.addClass("is-valid")
        checkmail = true;
    }

    var phoneReg = /^[0-9]{10}$/
    let phone = current.find("input[type='phone-number']");
    let phoneval = phone.val();
    if (!phoneReg.test(phoneval)) {
        phone.removeClass("is-valid")
        phone.addClass("is-invalid")
        checkphone = false;
    } else {
        phone.removeClass("is-invalid")
        phone.addClass("is-valid")
        checkphone = true;
    }
    let storeId = $("#stline-name").data("store_id");
    let name = current.find("input.input-name");
    let nameval = name.val();
    let date = null;
    let dateval = null;
    if ($("a.store-dropdown").html().search("寵物美容") != -1) {
        date = current.find("input.input-date-gromming");
        dateval = date.val();
    } else {
        date = current.find("input.input-date");
        dateval = date.val();
    }
    let date2 = current.find("input.input-date-checkout");
    let date2val = date2.val();
    let person = current.find("input.input-persons");
    let personval = person.val();
    let note = current.find("textarea.input-note");
    let noteval = note.val();
    // console.log(storeId);
    // console.log(nameval);
    // console.log(eamilval);
    console.log(dateval);
    // console.log(personval);
    // console.log(phoneval);
    // console.log(noteval);

    let array = [];
    $("#storeType2").find("th.getServiceId").each(function (index, item) {
        let serviceId = $(item).data("service_id");
        let pets = $(item).siblings("td").find("input").val();
        let obj = { service_id: serviceId, order_detail_pets: pets }
        array.push(obj);
    });

    let obj = { store_id: storeId, member_id: "", store_order_name: nameval, store_order_email: eamilval, store_order_phone_num: phoneval, store_order_persons: personval, store_order_note: noteval, store_order_date_time: dateval, store_order_end_date: date2val, detail_list: array }
    var myJson = JSON.stringify(obj);
    // console.log(JSON.parse(myJson));

    if (checkmail == true && checkphone == true) {
        $.ajax({
            url: projectUrl + "/Store_frontController",
            // url: "http://localhost:8081/TDA101G2/Store_frontController",
            type: "POST",                  // GET | POST | PUT | DELETE | PATCH
            data: {
                "action": "Booking",
                data: myJson
            },
            dataType: "text",             // 預期會接收到回傳資料的格式： json | xml | html
            beforeSend: function () {       // 在 request 發送之前執行
            },
            statusCode: {                 // 狀態碼
                200: function (res) {
                    // console.log("200")
                },
                404: function (res) {
                    console.log("400")
                },
                500: function (res) {
                    console.log("500")
                }
            },
            error: function (xhr) {         // request 發生錯誤的話執行
                console.log(xhr.responseText);
            },

            success: function (data) {
                let reult = data.toString()
                let text = reult.substr(data.indexOf("=") + 1);
                if (data.search("預約成功") != -1) {
                    $("#staticBackdropLabel").text("預約成功")
                    $("#staticBackdrop").find("div.modal-body").text("訂單編號： " + text);
                    $('#staticBackdrop').modal('show');
                } else if (data.search("預約失敗") != -1) {
                    $("#staticBackdropLabel").text("預約失敗")
                    $("#staticBackdrop").find("div.modal-body").text(text);
                    $('#staticBackdrop').modal('show');
                } else {
                    console.log(data);
                }
            }
        });
    }
})
$("input[name='payment']").change(function () {
    if ($(this).val() == "linepay") {
        linepayReq()
        $("#linepay").removeClass("d-none")
    } else {
        $("#linepay").addClass("d-none")
    }
})

function linepayReq() {
    var url = 'https://sandbox-api-pay.line.me/v2/payments/request';
    $.ajax({
        url: url,
        dataType: 'json',
        data: {
            "productName": "test",
            "productImageUrl": "https://via.placeholder.com/84x84",
            "amount": 1,
            "currency": "TWD",
            "confirmUrl": "www.google.com",
            "orderId": "P0001"
        },
        type: 'POST',
        dataType: "json",
        headers: {
            // 'Access-Control-Allow-Origin': '*',
            // 'Access-Control-Allow-Methods': 'POST',
            // 'Access-Control-Allow-Headers': 'x-requested-with,content-type',
            'Content-Type': 'application/json;charset=UTF-8',
            'X-LINE-ChannelId': '1654393823',
            'X-LINE-ChannelSecret': '621b6fda656e715f4d734a02d53cfe36'
        },
        beforeSend: function (xhr) {       // 在 request 發送之前執行
            // xhr.setRequestHeader("Access-Control-Allow-Origin", "*")
        },
        statusCode: {                 // 狀態碼
            200: function (res) {
                console.log("200")
            },
            404: function (res) {
                console.log("400")
            },
            500: function (res) {
                console.log("500")
            }
        },
        error: function (xhr) {         // request 發生錯誤的話執行
            console.log(xhr.responseText);
        },
        success: function (data) {
            console.log(data);
        }
    });
}
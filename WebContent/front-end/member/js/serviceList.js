//判斷是否為正整數
function isPositiveInteger(str) {
  var n = Math.floor(Number(str));
  return n !== Infinity && String(n) === str && n >= 0;
}

//上傳圖片設定 ========================================================================================

FilePond.registerPlugin(
  FilePondPluginImagePreview,
  FilePondPluginImageResize,
  FilePondPluginImageTransform,
  FilePondPluginFileValidateType,
  FilePondPluginFileEncode
)

let base64 = {};

const inputElement = document.querySelector('input[id="store_image_update"]');
const inputElement2 = document.querySelector('input[id="store_image_update2"]');
let firstTimeUpdate = false;
let imageobj = {
  labelIdle: '上傳圖片',
  // allowFileEncode: true,
  allowImagePreview: false,
  acceptedFileTypes: ['image/*'],
  // imageResizeTargetWidth: 270,
  imageResizeMode: 'contain',
  maxFiles: 3,
  name: 'store_image',
  onpreparefile: (file, output) => {
    firstTimeUpdate = true;
    const holder = $('div#new-store-image-update');
    let url = URL.createObjectURL(output);
    if (firstTimeUpdate === false) $(holder).html('');

    $(holder).append(`
      <div class="store_image_holder d-flex justify-content-center shadow-sm p-3 mb-5 bg-white rounded" id="${file.id}">
        <img src="${url}">
      </div>
      `);
    base64[file.id] = file.getFileEncodeBase64String();
  },
  onremovefile: (error, file) => {
    const img = $(`#${file.id}`);
    $(img).remove();
    delete base64[file.id];
  }
}
let imageobj2 = {
  labelIdle: '上傳圖片',
  // allowFileEncode: true,
  allowImagePreview: false,
  acceptedFileTypes: ['image/*'],
  // imageResizeTargetWidth: 270,
  imageResizeMode: 'contain',
  maxFiles: 3,
  name: 'store_image',
  onpreparefile: (file, output) => {
    firstTimeUpdate = true;
    const holder = $('div#new-store-image-update2');
    let url = URL.createObjectURL(output);
    if (firstTimeUpdate === false) $(holder).html('');

    $(holder).append(`
      <div class="store_image_holder d-flex justify-content-center shadow-sm p-3 mb-5 bg-white rounded" id="${file.id}">
        <img src="${url}">
      </div>
      `);
    base64[file.id] = file.getFileEncodeBase64String();
  },
  onremovefile: (error, file) => {
    const img = $(`#${file.id}`);
    $(img).remove();
    delete base64[file.id];
  }
}
const pond = FilePond.create(inputElement, imageobj);
const pond2 = FilePond.create(inputElement2, imageobj2);



//修改上傳圖片預設文字，及預覽圖片
// $(window).on("load", function () {
//   //修改上傳圖片預設文字
//   //此標籤為實際上傳圖片之input
//   $("input[name='description']").on("change", function () {
//     for (let i = 0; i < this.files.length; i++) {
//       let reader = new FileReader();
//       reader.readAsDataURL(this.files[i]);
//       reader.addEventListener("load", function () {
//         // console.log(reader.result);

//         //此處處理圖片，可以把圖片轉成blob
//         // 之後透過ajax把圖片傳到後端處理
//       });
//     }
//   })
// });

//刪除某項商品=======================================================================
$('#product-table').on("click", "a.product-remove", function () {
  //這邊透過ajax到後端進行移除
  let self = this;
  $(remove).off('click').on("click", function () {
    $(self).closest('tr').remove();
    $(this).closest('.modal-content').find('button.close').click();
  })
})

//新增商品內容=======================================================================

//新增商品
$('#add-product-btn').on("click", function () {
  //正式上線
  // $('#add-product-form').on("submit", function (e) {
  //   let self = this;
  //   e.preventDefault();

  let modal = $(this).closest('.modal-content');
  let name = $('#product_name').val().trim();
  let type = $('#product_type').val().trim();
  // let price = $('#product_price').val().trim();
  let form = $(this).closest('form');
  let trs = $(modal).find('tbody tr');
  //判斷不能為空值
  // if (name !== "" && type !== "") {

  $.each(trs, function (index, item) {
    console.log(item);
    $(item).attr('id', `new-product-version${index + 1}`);
    let version = $(item).find('td').eq(0).text();
    let quantity = $(item).find('td').eq(1).text();
    let price = $(item).find('td').eq(2).text();
    $(form).append(`
      <input type='hidden' name'product_version${index + 1}' value='${version}'>
      <input type='hidden' name'quantity${index + 1}' value='${quantity}'>
      <input type='hidden' name'price${index + 1}' value='${price}'>
    `);
  })

  //處理input 上傳資料(使用ajax才會用到)
  // let file = pond.getFile(description_id).file;
  // let reader = new FileReader();
  // reader.readAsArrayBuffer(file);
  // reader.onloadend = function () {
  //   console.log(reader.result);
  //   obj.description = reader.result;
  // };

  //送出請求
  // ...
  //正式上線，等到完成以上動作再送出請求
  // self.submit();
  // }
})

//新增商品規格(新增商品頁面上)
$('#add-product-version').on("click", function () {
  let name = $('#product_version').val().trim();
  let quantity = $('#quantity').val().trim();
  let price = $('#price').val().trim();


  if (name !== "" && quantity !== "" && price !== "" &&
    isPositiveInteger(quantity) && isPositiveInteger(price)) {
    $('#product-version-table').append(`
    <tr>
      <td>${name}</td>
      <td>${quantity}</td>
      <td>${price}</td>
      <td>
        <a href="#" data-toggle="modal" class="product-remove">
          <i class="fas fa-trash remove-product-version"></i>
        </a>
      </td>
    </tr>`);
    $('#product_version').val("");
    $('#quantity').val("");
    $('#price').val("");
  }
})

//移除商品規格
$('#product-version-table').on("click", "a.product-remove", function () {
  $(this).closest('tr').remove();
})



//取消新增商品
$('#cancel-product').on("click", function () {
  let modal = $(this).closest('.modal-content');
  if (confirm("確定要取消新增此項商品? 此次填寫的內容將會遺失")) {
    $(modal).find('input').val('');
    $(modal).find('select option').first().prop("selected", true);
    $(modal).find('tbody').html('');
    pond.removeFiles();
    pond1.removeFiles();
    $(modal).find('button.close').click();
  }
})


//更新商品內容=======================================================================

//更改商品資訊
$('#update-product-btn').on("click", function () {
  //正式上線
  // $('#update-product-form').on("submit", function (e) {
  //   let self = this;
  //   e.preventDefault();

  let modal = $(this).closest('.modal-content');
  let name = $('#product_name_update').val().trim();
  let type = $('#product_type_update').val().trim();

  let form = $(this).closest('form');
  //判斷不能為空值
  // if (name !== "" && type !== "" && price !== "" && isPositiveInteger(price)) {

  let trs = $(modal).find('tbody tr');

  $.each(trs, function (index, item) {
    console.log(item);
    $(item).attr('id', `update-product-version${index + 1}`);
    let version = $(item).find('td').eq(0).text();
    let quantity = $(item).find('td').eq(1).text();
    let price = $(item).find('td').eq(2).text();
    $(form).append(`
      <input type='hidden' name'product_version${index + 1}' value='${version}'>
      <input type='hidden' name'quantity${index + 1}' value='${quantity}'>
      <input type='hidden' name'price${index + 1}' value='${price}'>
    `);
  })
  //送出請求()
  //關閉modal(只有ajax會用到)
  // $(modal).find('button.close').click();
  //正式上線，等到完成以上動作再送出請求
  // self.submit();
  // }
})


//新增商品規格(更新商品頁面上)
$('#add-product-version-update').on("click", function () {
  let name = $('#product_version_update').val().trim();
  let quantity = $('#quantity_update').val().trim();
  let price = $('#price_update').val().trim();
  if (name !== "" && quantity !== "" && price !== "" &&
    isPositiveInteger(quantity) && isPositiveInteger(price)) {
    $('#product-version-table-update').append(`
      <tr>
        <td>${name}</td>
        <td>${quantity}</td>
        <td>${price}</td>
        <td>
          <a href="#" data-toggle="modal" class="product-remove">
            <i class="fas fa-trash remove-product-version"></i>
          </a>
        </td>
      </tr>`);

    $('#product_version_update').val("");
    $('#quantity_update').val("");
    $('#price_update').val("");
  }
})

//移除商品規格
$('#product-version-table-update').on("click", "a.product-remove", function () {
  $(this).closest('tr').remove();
})

//取消更新商品
$('#cancel-update').on("click", function () {
  let modal = $(this).closest('.modal-content');
  console.log(modal);
  if (confirm("確定要取消新增此項商品?" + "\n" + "此次填寫的內容將會遺失回到上次更新的狀態")) {
    //此處回填資料庫的資料

    $(modal).find('button.close').click();
  }
})






// 宇宏覆寫 =============================================================================
let path = window.location.pathname;
const projectUrl = "http://" + window.location.host + path.substring(0, path.indexOf('/', 1))
// const projectUrl = "http://localhost:8081/TDA101G2";

$("input[type^='number']").inputSpinner();

$("#table-title-text").on('click', function () {
  $("i.add-product").addClass("d-none");
})
$("#pills-profile-tab").on('click', function () {
  $("i.add-product").removeClass("d-none");
})

let selectedDate = [];
// 日期選擇器
var myDatepicker = $('.datepicker-here').datepicker().data('datepicker');
var myDatepicker2 = $('#store_closed_update2').datepicker().data('datepicker');
// $.fn.datepicker.language['en'] = {
//   days: ['Sunday', 'Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday', 'Saturday'],
//   daysShort: ['Sun', 'Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat'],
//   daysMin: ['日', '一', '二', '三', '四', '五', '六'],
//   months: ['1月', '1月', '3月', '4月', '5月', '6月', '7月', '8月', '9月', '10月', '11月', '12月'],
//   monthsShort: ['一月', '二月', '三月', '四月', '五月', '六月', '七月', '八月', '九月', '十月', '十一月', '十二月'],
//   today: 'Today',
//   clear: 'Clear',
//   dateFormat: 'yyyy/mm/dd',
//   timeFormat: 'hh:ii aa'
// };
$('.datepicker-here').datepicker({
  multipleDates: true,
  multipleDatesSeparator: ",",
  firstDay: 0
  // clearButton: true
})
$('#store_closed_update2').datepicker({
  multipleDates: true,
  multipleDatesSeparator: ",",
  firstDay: 0
  // clearButton: true
})

// 公休日 連動多選日
$("div.storeBreak").change(function () {
  myDatepicker.clear();
  let date1 = $("select.storeFirstbreak").val();
  let date2 = $("select.storeSecondbreak").val();
  if (date1 == 7) {
    date1 = 0
  } else if (date1 == 0) {
    date1 = 7
  }
  if (date2 == 7) {
    date2 = 0
  } else if (date2 == 0) {
    date2 = 7
  }

  // 多選日期 -> 整周不顯示
  disabledDays = [parseInt(date1), parseInt(date2)];
  $('.datepicker-here').datepicker({
    onRenderCell: function (date, cellType) {
      if (cellType == 'day') {
        var day = date.getDay(),
          isDisabled = disabledDays.indexOf(day) != -1;
        return {
          disabled: isDisabled
        }
      }
    }
  })
})
// 公休日 連動多選日2
$("div.storeBreak2").change(function () {
  myDatepicker2.clear();
  let date3 = $("select.storeFirstbreak2").val();
  let date4 = $("select.storeSecondbreak2").val();
  if (date3 == 7) {
    date3 = 0
  } else if (date3 == 0) {
    date3 = 7
  }
  if (date4 == 7) {
    date4 = 0
  } else if (date4 == 0) {
    date4 = 7
  }

  // 多選日期 -> 整周不顯示2
  disabledDays2 = [parseInt(date3), parseInt(date4)];
  $('#store_closed_update2').datepicker({
    onRenderCell: function (date, cellType) {
      if (cellType == 'day') {
        var day = date.getDay(),
          isDisabled = disabledDays2.indexOf(day) != -1;
        return {
          disabled: isDisabled
        }
      }
    }
  })
})
// 新增 店家 按鈕
$("#new-store").on('click', function () {
  let member_id_val = $("#inputMemberId").val();
  let store_class_val = $("#store_type_update").val();
  let store_name_val = $("#store_name_update").val();
  let store_adress_val = $("#store_adress_update").val();
  let store_phone_number_val = $("#store_phone_update").val();
  let store_introduction_val = $("#store_introduction_update").val();
  let store_firstbreak_val = $("#store_firstbreak_update").val();
  let store_secondbreak_val = $("#store_secondbreak_update").val();
  let store_maxcapacity_val = $("#store_maxcapacity_update").val();
  let closed_array = $("#store_closed_update").val();
  var store_closed_val = closed_array.split(",");
  // console.log(store_class_val)
  // console.log(store_name_val)
  // console.log(store_adress_val)
  // console.log(store_phone_number_val)
  // console.log(store_introduction_val)
  // console.log(store_firstbreak_val)
  // console.log(store_secondbreak_val)
  // console.log(store_maxcapacity_val)
  // console.log(store_closed_val)
  // console.log(store_image1_val)

  let checkcalss = false;
  let checkname = false;
  let checkcadress = false;
  let checkphone = false;

  if (store_class_val == "") {
    $("#store_type_update").addClass("is-invalid")
    checkcalss = false;
  } else {
    $("#store_type_update").removeClass("is-invalid")
    checkcalss = true;
  }
  if (store_name_val == "") {
    $("#store_name_update").addClass("is-invalid")
    checkname = false;
  } else {
    $("#store_name_update").removeClass("is-invalid")
    checkname = true;
  }
  if (store_adress_val == "") {
    $("#store_adress_update").addClass("is-invalid")
    checkcadress = false;
  } else {
    $("#store_adress_update").removeClass("is-invalid")
    checkcadress = true;
  }
  if (store_phone_number_val == "") {
    $("#store_phone_update").addClass("is-invalid")
    checkphone = false;
  } else {
    $("#store_phone_update").removeClass("is-invalid")
    checkphone = true;
  }

  if (store_maxcapacity_val == "") {
    store_maxcapacity_val = null;
  }
  if (store_firstbreak_val == "") {
    store_firstbreak_val = null;
  }
  if (store_secondbreak_val == "") {
    store_secondbreak_val = null;
  }
  let store_closed = [];
  $.each(store_closed_val, function (index, item) {
    if (store_closed_val != "") {
      store_closed.push(
        { store_closed_day: item }
      )
    } else {
      store_closed = null;
    }
  });
  // console.log(store_closed)
  let keys = Object.keys(base64);
  let obj = {
    member_id: member_id_val,
    store_class: store_class_val,
    store_name: store_name_val,
    store_adress: store_adress_val,
    store_phone_number: store_phone_number_val,
    store_introduction: store_introduction_val,
    store_firstbreak: store_firstbreak_val,
    store_secondbreak: store_secondbreak_val,
    store_maxcapacity: store_maxcapacity_val,
    store_closed: store_closed,
    store_image1: base64[keys[0]],
    store_image2: base64[keys[1]],
    store_image3: base64[keys[2]]
  };


  // let obj = {
  //   store_adress: "臺北市萬華區平菁路四段201巷204弄",
  //   store_class: "旅館",
  //   store_closed: [
  //     { store_closed_day: "2020-06-12" },
  //     { store_closed_day: "2020-06-19" },
  //     { store_closed_day: "2020-06-26" }
  //   ],
  //   store_firstbreak: "3",
  //   store_introduction: "安安",
  //   store_maxcapacity: "3",
  //   store_name: "王小明",
  //   store_phone_number: "123",
  //   store_secondbreak: "2"
  // };

  var myJson = JSON.stringify(obj);
  console.log(JSON.parse(myJson));

  //   for (i of base64) console.log(i.length);
  if (checkphone == true && checkcalss == true && checkname == true && checkcadress == true) {
    $.ajax({
      url: projectUrl + "/Store_frontController",
      // url: "http://localhost:8081/TDA101G2/Store_frontController",
      type: "POST",                  // GET | POST | PUT | DELETE | PATCH
      data: {
        "action": "newStore",
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
        // console.log(xhr.responseText);
      },

      success: function (data) {
        console.log(data)
        if (data.search("新增成功") != -1) {
          $("#staticBackdrop").find("div.modal-body").text(data)
          $('#staticBackdrop').modal('show');
        } else {
          $("#staticBackdrop").find("div.modal-body").text("新增失敗");
          $('#staticBackdrop').modal('show');
        }
      }
    });
    $("#btn_newstore_reload").click(function () {
      location.reload();
    })
  }
})
// 修改 店家 按鈕
$("#update-store").on('click', function () {
  let store_id_val = $("#update_store").attr("data-store_id");
  let member_id_val = $("#inputMemberId").val();
  let store_class_val = $("#store_type_update2").val();
  let store_name_val = $("#store_name_update2").val();
  let store_adress_val = $("#store_adress_update2").val();
  let store_phone_number_val = $("#store_phone_update2").val();
  let store_introduction_val = $("#store_introduction_update2").val();
  let store_firstbreak_val = $("#store_firstbreak_update2").val();
  let store_secondbreak_val = $("#store_secondbreak_update2").val();
  let store_maxcapacity_val = $("#store_maxcapacity_update2").val();
  let closed_array = $("#store_closed_update2").val();
  var store_closed_val = closed_array.split(",");
  console.log(store_id_val)
  console.log(member_id_val)
  // console.log(store_class_val)
  // console.log(store_name_val)
  // console.log(store_adress_val)
  // console.log(store_phone_number_val)
  // console.log(store_introduction_val)
  // console.log(store_firstbreak_val)
  // console.log(store_secondbreak_val)
  // console.log(store_maxcapacity_val)
  // console.log(store_closed_val)

  let checkcalss = false;
  let checkname = false;
  let checkcadress = false;
  let checkphone = false;

  if (store_class_val == "") {
    $("#store_type_update").addClass("is-invalid")
    checkcalss = false;
  } else {
    $("#store_type_update").removeClass("is-invalid")
    checkcalss = true;
  }
  if (store_name_val == "") {
    $("#store_name_update").addClass("is-invalid")
    checkname = false;
  } else {
    $("#store_name_update").removeClass("is-invalid")
    checkname = true;
  }
  if (store_adress_val == "") {
    $("#store_adress_update").addClass("is-invalid")
    checkcadress = false;
  } else {
    $("#store_adress_update").removeClass("is-invalid")
    checkcadress = true;
  }
  if (store_phone_number_val == "") {
    $("#store_phone_update").addClass("is-invalid")
    checkphone = false;
  } else {
    $("#store_phone_update").removeClass("is-invalid")
    checkphone = true;
  }

  if (store_maxcapacity_val == "") {
    store_maxcapacity_val = null;
  }
  if (store_firstbreak_val == "") {
    store_firstbreak_val = null;
  }
  if (store_secondbreak_val == "") {
    store_secondbreak_val = null;
  }
  let store_closed = [];
  $.each(store_closed_val, function (index, item) {
    if (store_closed_val != "") {
      store_closed.push(
        { store_closed_day: item }
      )
    } else {
      store_closed = null;
    }
  });
  // console.log(store_closed)
  let keys = Object.keys(base64);
  let obj = {
    store_id: store_id_val,
    member_id: member_id_val,
    store_class: store_class_val,
    store_name: store_name_val,
    store_adress: store_adress_val,
    store_phone_number: store_phone_number_val,
    store_introduction: store_introduction_val,
    store_firstbreak: store_firstbreak_val,
    store_secondbreak: store_secondbreak_val,
    store_maxcapacity: store_maxcapacity_val,
    store_closed: store_closed,
    store_image1: base64[keys[0]],
    store_image2: base64[keys[1]],
    store_image3: base64[keys[2]]
  };

  var myJson = JSON.stringify(obj);
  console.log(JSON.parse(myJson));

  //   for (i of base64) console.log(i.length);
  if (checkphone == true && checkcalss == true && checkname == true && checkcadress == true) {
    $.ajax({
      url: projectUrl + "/Store_frontController",
      // url: "http://localhost:8081/TDA101G2/Store_frontController",
      type: "POST",                  // GET | POST | PUT | DELETE | PATCH
      data: {
        "action": "updateStore",
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
        // console.log(xhr.responseText);
      },

      success: function (data) {
        console.log(data)
        if (data.search("修改成功") != -1) {
          $("#staticBackdrop").find("div.modal-body").text(data)
          $('#staticBackdrop').modal('show');
        } else {
          $("#staticBackdrop").find("div.modal-body").text("修改失敗");
          $('#staticBackdrop').modal('show');
        }
      }
    });
  }
})

// 服務列表顯示/隱藏

$("[id^='store_type_update']").change(function () {
  let sotre_type = $(this).val();
  if (sotre_type == '旅館' || sotre_type == '美容' || sotre_type == '學校') {
    $("#pills-profile-tab").removeClass("d-none")
  } else {
    $("#pills-profile-tab").addClass("d-none")
  }
})

// 暫 - 串membery 資料
let member_id;
$("#inputMemberId").change(function () {
  console.log($(this).val())
  showServiceList($(this).val())
  member_id = $(this).val()
  showStoreData(member_id);
  $("div.update_store").addClass("d-none");
  $("div.input_store").removeClass("d-none");
  selectedDate = [];
  myDatepicker2.clear();
  // 清空圖片
  $("li.filepond--item").find("button.filepond--action-remove-item").click()
})

if ($("#inputMemberId").val() != null) {
  $("#inputMemberId").change();
}
let storeid;
function showStoreData(member_id) {

  $.ajax({
    url: projectUrl + "/Store_frontController",
    // url: "http://localhost:8081/TDA101G2/Store_frontController",
    type: "GET",                  // GET | POST | PUT | DELETE | PATCH
    data: {
      "action": "getStoreData",
      memberId: member_id
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
      // console.log(xhr.responseText);
    },

    success: function (data) {
      console.log(data)
      let st = JSON.parse(data[0]);
      storeid = st.store_id
      if (data != null) {
        $("div.update_store").removeClass("d-none");
        $("div.input_store").addClass("d-none");
        $("#update_store").attr("data-store_id", st.store_id)
        $("#store_name_update2").val(st.store_name)
        $("#store_type_update2").val(st.store_class)
        $("#store_maxcapacity_update2").val(st.store_maxcapacity)
        $("#store_adress_update2").val(st.store_adress)
        $("#store_phone_update2").val(st.store_phone_number)
        $("#store_introduction_update2").val(st.store_introduction)
        let date1 = $("#store_firstbreak_update2").val(st.store_firstbreak)
        let date2 = $("#store_secondbreak_update2").val(st.store_secondbreak)
        disabledDays2 = [parseInt(date1.val()), parseInt(date2.val())];
        // 多選日期 -> 整周不顯示2
        $('#store_closed_update2').datepicker({
          onRenderCell: function (date, cellType) {
            if (cellType == 'day') {
              var day = date.getDay(),
                isDisabled = disabledDays2.indexOf(day) != -1;
              return {
                disabled: isDisabled
              }
            }
          }
        })

        let clo = JSON.parse(data[1]);

        for (i of clo) {
          console.log(i.store_closed_day)
          selectedDate.push(new Date(i.store_closed_day));
        }
        myDatepicker2.selectDate(selectedDate);

        if (st.store_class == '旅館' || st.store_class == '美容' || st.store_class == '學校') {
          $("#pills-profile-tab").removeClass("d-none")
        } else {
          $("#pills-profile-tab").addClass("d-none")
        }
      }

    }
  })
}

function showServiceList(member_id) {
  $.ajax({
    url: projectUrl + "/Store_frontController",
    // url: "http://localhost:8081/TDA101G2/Store_frontController",
    type: "POST",                  // GET | POST | PUT | DELETE | PATCH
    data: {
      "action": "getListByMember",
      memberId: member_id
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
      // console.log(xhr.responseText);
    },

    success: function (data) {
      $("#service-table").empty();
      let tr_html = "";
      $.each(data, function (index, item) {
        tr_html +=
          `<tr class="service_id" data-service_id="${item.service_id}" data-store_id="${item.store_id}">
            <td class="service_detail">${item.service_detail}</td>
            <td class="service_price">${item.service_price}</td>
            <td class="service_limit">${item.service_limit}</td>
            <td>
              <div class="d-flex justify-content-center">
                <label class="switch d-flex align-self-center">
                  <input type="checkbox" name="service_state" ${(item.service_state == 1) ? 'checked' : ''}>
                  <span class="slider round" data-service_id="${item.service_id}" data-state="${item.service_state}"></span>
                </label>
              </div>
            </td>
            <td>
              <div class="d-flex justify-content-center">
                <a href="#update-product" data-toggle="modal" class="product-update">
                  <i class="fas fa-pen"></i>
                </a>
              </div>
            </td>
            <td>
              <a href="#remove-product" data-toggle="modal" class="product-remove">
                <i class="fas fa-trash"></i>
              </a>
            </td>
          </tr>`;
      })
      $("#service-table").append(tr_html);
    }
  });
}

// 新增服務
$("#add-service-btn").on("click", function () {
  let name = $(this).closest("#add-product").find("#service_name").val().trim()
  let price = $(this).closest("#add-product").find("#price").val().trim()
  let limit = $(this).closest("#add-product").find("#quantity").val().trim()
  if (name == "") {
    $(this).closest("#add-product").find("#service_name").addClass("is-invalid")
  } else {
    $(this).closest("#add-product").find("#service_name").removeClass("is-invalid")
  }
  if (price == "" || price == 0) {
    $(this).closest("#add-product").find("#price").addClass("is-invalid")
  } else {
    $(this).closest("#add-product").find("#price").removeClass("is-invalid")
  }
  if (limit == "" || limit == 0) {
    $(this).closest("#add-product").find("#quantity").addClass("is-invalid")
  } else {
    $(this).closest("#add-product").find("#quantity").removeClass("is-invalid")
  }
  if (name != "" && price != "" && price != 0 && limit != 0 && limit != "") {

    let serviceObj = { store_id: storeid, service_detail: name, service_price: price, service_limit: limit, service_state: 1 }
    var service = JSON.stringify(serviceObj);
    console.log(service)
    $.ajax({
      url: projectUrl + "/Store_frontController",
      // url: "http://localhost:8081/TDA101G2/Store_frontController",
      type: "POST",                  // GET | POST | PUT | DELETE | PATCH
      data: {
        "action": "newService",
        service_vo: service
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
        // console.log(xhr.responseText);
      },

      success: function (data) {
        console.log(data)
        let reult = data.toString()
        let text = reult.substr(data.indexOf("=") + 1);
        let tr_html =
          `<tr class="service_id newService" data-service_id="${text}" data-store_id="${storeid}">
            <td class="service_detail">${name}</td>
            <td class="service_price">${price}</td>
            <td class="service_limit">${limit}</td>
            <td>
              <div class="d-flex justify-content-center">
              <label class="switch d-flex align-self-center">
                  <input type="checkbox" name="service_state" checked>
                  <span class="slider round" data-service_id="${text}" data-state="1"></span>
                </label>
              </div>
            </td>
            <td>
              <div class="d-flex justify-content-center">
                <a href="#update-product" data-toggle="modal" class="product-update">
                  <i class="fas fa-pen"></i>
                </a>
              </div>
            </td>
            <td>
              <a href="#remove-product" data-toggle="modal" class="product-remove">
                <i class="fas fa-trash"></i>
              </a>
            </td>
          </tr>`;
        $("#service-table").append(tr_html);
      }
    })
    $("#add-product").modal('hide')
    $("#service_name").val("")
    $("#price").val("")
    $("#quantity").val("")
  }
})

// 修改服務-1
let serviceid;

let nameobj;
let priceobj;
let limitobj;
$('#service-table').on('click', 'a.product-update', function () {
  serviceid = $(this).closest("tr.service_id").data("service_id")
  storeid = $(this).closest("tr.service_id").data("store_id")
  nameobj = $(this).closest("tr.service_id").find("td.service_detail")
  let name = nameobj.html()
  priceobj = $(this).closest("tr.service_id").find("td.service_price")
  let price = priceobj.html()
  limitobj = $(this).closest("tr.service_id").find("td.service_limit")
  let limit = limitobj.html()
  console.log(storeid)
  $("#update-product").find("#update-service-btn").attr("data-store_id", storeid)
  $("#update-product").find("#service_name").val(name)
  $("#update-product").find("#price").val(price)
  $("#update-product").find("#quantity").val(limit)
})
// 修改服務-2
$("#update-service-btn").on('click', function () {
  let newname = $(this).closest("#update-product").find("#service_name").val()
  let newprice = $(this).closest("#update-product").find("#price").val()
  let newlimit = $(this).closest("#update-product").find("#quantity").val()
  let storeID = $(this).data("store_id")
  console.log(serviceid)
  console.log(storeID)
  let serviceObj = { service_id: serviceid, store_id: storeID, service_detail: newname, service_price: newprice, service_limit: newlimit }
  var service = JSON.stringify(serviceObj);
  $.ajax({
    url: projectUrl + "/Store_frontController",
    // url: "http://localhost:8081/TDA101G2/Store_frontController",
    type: "POST",                  // GET | POST | PUT | DELETE | PATCH
    data: {
      "action": "updateService",
      service_vo: service
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
      // console.log(xhr.responseText);
    },

    success: function (data) {
      console.log(data)
    }
  })

  nameobj.text(newname)
  priceobj.text(newprice)
  limitobj.text(newlimit)
  $("#update-product").modal('hide')
})

// 刪除服務
let remove_tr;
$('#service-table').on('click', 'a.product-remove', function () {
  serviceid = $(this).closest("tr.service_id").data("service_id")
  remove_tr = $(this).closest("tr.service_id")
})

$("#remove").on('click', function () {
  $.ajax({
    url: projectUrl + "/Store_frontController",
    // url: "http://localhost:8081/TDA101G2/Store_frontController",
    type: "POST",                  // GET | POST | PUT | DELETE | PATCH
    data: {
      "action": "deleteService",
      serviceId: serviceid
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
      // console.log(xhr.responseText);
    },

    success: function (data) {
      console.log(data)
    }
  })
  remove_tr.remove();
  $("#remove-product").modal('hide')
})

// 修改服務狀態
$('input[name="service_state"]').on("click", function () {
  console.log(111)
})
$('input[name="service_state"]').change(function () {
  console.log(222)
})
// $(function () {
$('#service-table').on('click', '.slider', function () {
  let self = this;
  console.log(self)
  let state = $(self).attr("data-state")
  state == 1 ? $(self).attr("data-state", 0) : $(self).attr("data-state", 1)
  $.ajax({
    url: projectUrl + "/Store_frontController",
    type: "post",
    data: {
      action: "update_state",
      service_id: $(self).attr("data-service_id"),
      service_state: $(self).attr("data-state")
    },
    dataType: "text",
    timeout: 0,
    headers: {
    },
    error: function (xhr) {
      console.log(xhr);
    },
    success: function (data) {
      if (data == "success") alert("狀態更新成功");
      console.log(data);
    }
  });
})
// })
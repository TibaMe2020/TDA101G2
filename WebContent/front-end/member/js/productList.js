判斷是否為正整數
function isPositiveInteger(str) {
  var n = Math.floor(Number(str));
  return n !== Infinity && String(n) === str && n >= 0;
}
let blob;
let base64;

function getImageBlob(url) {
  return $.ajax({
    url: url,
    cache: false,
    xhrFields: {
      responseType: 'blob'
    },
    success: function (data) {
      console.log(data);
    },
    error: function (xhr) {
      console.log(xhr);
    }
  })
}

function getDataUrl(url) {
  var canvas = document.createElement('canvas');
  var ctx = canvas.getContext('2d');

  canvas.width = img.width;
  canvas.height = img.height;
  ctx.drawImage(img, 0, 0);
  return canvas.toDataURL();
}

function toDataURL(url) {
  return $.ajax({
    url: url,
    cache: false,
    xhrFields: {
      responseType: 'blob'
    },
    success: function (data) {
      let reader = new FileReader();
      reader.readAsDataURL(data);
      reader.onloadend = function () {
        base64 = reader.result;
      }
    },
    error: function (xhr) {}
  })
  // var xhr = new XMLHttpRequest();
  // xhr.onload = function () {
  //   var reader = new FileReader();
  //   reader.onloadend = function () {
  //     callback(reader.result);
  //   }
  //   reader.readAsDataURL(xhr.response);
  // };
  // xhr.open('GET', url);
  // xhr.responseType = 'blob';
  // xhr.send();
}

//上傳圖片設定 ========================================================================================

FilePond.registerPlugin(
  FilePondPluginImagePreview,
  FilePondPluginImageResize,
  FilePondPluginImageTransform,
  FilePondPluginFileValidateType,
  FilePondPluginFileEncode
)





var requestUrls = [
  'http://localhost:8081/Petbox060802/member/profileImage?member_id=MB00001',
  'http://localhost:8081/Petbox060802/member/profileImage?member_id=MB00001',
  'http://localhost:8081/Petbox060802/member/profileImage?member_id=MB00001',
  'http://localhost:8081/Petbox060802/member/profileImage?member_id=MB00001',
  'http://localhost:8081/Petbox060802/member/profileImage?member_id=MB00001',
  'http://localhost:8081/Petbox060802/member/profileImage?member_id=MB00001',
  'http://localhost:8081/Petbox060802/member/profileImage?member_id=MB00001',
  'http://localhost:8081/Petbox060802/member/profileImage?member_id=MB00001',
  'http://localhost:8081/Petbox060802/member/profileImage?member_id=MB00001',
  'http://localhost:8081/Petbox060802/member/profileImage?member_id=MB00001'
];

$(document).ready(function () {
  getImageBlob('http://localhost:8081/Petbox061701/member/profileImage?member_id=MB00001')
    .done(function (blob) {
      const inputElement = document.querySelector('input[id="description"]');
      const pond = FilePond.create(inputElement, {
        allowFileEncode: true,
        labelIdle: '上傳圖片',
        allowImagePreview: false,
        acceptedFileTypes: ['image/*'],
        imageResizeTargetWidth: 290,
        imageResizeMode: 'contain',
        maxFiles: 1,
        name: 'description',
        onpreparefile: (file, output) => {
          // console.log("this is file id: " + file.id);
          const holder = $('div#new-description');
          let url = URL.createObjectURL(output);
          description_id = file.id;
          // 以下程式碼會把file轉成base64字串
          console.log(file.getFileEncodeBase64String());
          console.log(file.getFileEncodeDataURL().length);
          $(holder).append(`
            <div class="description_holder d-flex justify-content-center shadow-sm p-3 mb-5 bg-white rounded" id="${file.id}">
              <img src="${url}">
            </div>`);
        },
        onremovefile: (error, file) => {
          // console.log("this is file id: " + file.id);
          const img = $(`div#${file.id}`);
          $(img).remove();
        },
        files: [{
          source: blob,
          options: {
            type: 'local'
          }
        }]
      });






    });






  // let ajax1 = getImageBlob('http://localhost:8081/Petbox060802/member/profileImage?member_id=MB00001');
  // let ajax2 = getImageBlob('http://localhost:8081/Petbox060802/member/profileImage?member_id=MB00002');
  // let ajax3 = getImageBlob('http://localhost:8081/Petbox060802/member/profileImage?member_id=MB00003');
  // let ajax4 = getImageBlob('http://localhost:8081/Petbox060802/member/profileImage?member_id=MB00004');
  // let ajax5 = getImageBlob('http://localhost:8081/Petbox060802/member/profileImage?member_id=MB00005');
  // let ajax6 = getImageBlob('http://localhost:8081/Petbox060802/member/profileImage?member_id=MB00006');
  // let ajax7 = getImageBlob('http://localhost:8081/Petbox060802/member/profileImage?member_id=MB00007');
  // let ajax8 = getImageBlob('http://localhost:8081/Petbox060802/member/profileImage?member_id=MB00008');
  // let ajax9 = getImageBlob('http://localhost:8081/Petbox060802/member/profileImage?member_id=MB00009');
  // let ajax0 = getImageBlob('http://localhost:8081/Petbox060802/member/profileImage?member_id=MB00010');

  // $.when(ajax0, ajax1, ajax2, ajax3, ajax4, ajax5, ajax6, ajax7, ajax8, ajax9).done(function (a0, a1, a2, a3, a4, a5, a6, a7, a8, a9) {

  //   console.log(a0[0]);
  //   console.log(a0[1]);
  //   console.log(a0[2]);
  //   console.log(a0[3]);
  //   console.log(a0[4]);
  //   console.log(a0[5]);
  //   console.log(a0[6]);
  //   console.log(a0[7]);
  //   console.log(a0[8]);
  //   console.log(a0[9]);
  // })
});

let description_id;
let firstTimeUpdate = false;

$(window).on('load', function () {







  const inputElement1 = document.querySelector('input[id="product_image"]');
  const pond1 = FilePond.create(inputElement1, {
    labelIdle: '上傳圖片',
    allowImagePreview: false,
    acceptedFileTypes: ['image/*'],
    imageResizeTargetWidth: 270,
    imageResizeMode: 'contain',
    maxFiles: 4,
    name: 'product_image',
    onpreparefile: (file, output) => {
      console.log("this is file id: " + file.id);
      const holder = $('div#new-product-image');
      let url = URL.createObjectURL(output);
      $(holder).append(`
    <div class="product_image_holder d-flex justify-content-center shadow-sm p-3 mb-5 bg-white rounded" id="${file.id}">
      <img src="${url}">
    </div>
    `);
    },
    onremovefile: (error, file) => {
      console.log("this is file id: " + file.id);
      const img = $(`div#${file.id}`);
      $(img).remove();
    },
    files: [{
      source: blob,
      options: {
        type: 'local'
      }
    }]
  });

  const inputElement2 = document.querySelector('input[id="description_update"]');

  const pond2 = FilePond.create(inputElement2, {
    labelIdle: '上傳圖片',
    allowImagePreview: false,
    acceptedFileTypes: ['image/*'],
    imageResizeTargetWidth: 290,
    imageResizeMode: 'contain',
    maxFiles: 1,
    name: 'description',
    onpreparefile: (file, output) => {
      const holder = $('div#new-description-update');
      let url = URL.createObjectURL(output);
      $(holder).append(`
    <div class="product_image_holder d-flex justify-content-center shadow-sm p-3 mb-5 bg-white rounded" id="${file.id}">
      <img src="${url}">
    </div>
    `);
    },
    onremovefile: (error, file) => {
      const img = $(`#${file.id}`);
      $(img).remove();
    }
  });

  const inputElement3 = document.querySelector('input[id="product_image_update"]');

  const pond3 = FilePond.create(inputElement3, {
    labelIdle: '上傳圖片',
    allowImagePreview: false,
    acceptedFileTypes: ['image/*'],
    imageResizeTargetWidth: 270,
    imageResizeMode: 'contain',
    maxFiles: 4,
    name: 'product_image',
    onpreparefile: (file, output) => {
      firstTimeUpdate = true;
      const holder = $('div#new-product-image-update');
      let url = URL.createObjectURL(output);
      if (firstTimeUpdate === false) $(holder).html('');

      $(holder).append(`
    <div class="product_image_holder d-flex justify-content-center shadow-sm p-3 mb-5 bg-white rounded" id="${file.id}">
      <img src="${url}">
    </div>
    `);
    },
    onremovefile: (error, file) => {
      const img = $(`#${file.id}`);
      $(img).remove();
    }
  });

})



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
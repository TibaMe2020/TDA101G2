$('#product_score').on("click", function () {
  $.ajax({
    url: "https://...",
    type: "POST",
    data: {
      product_id = $('')

    },
    dataType: "json",
    timeout: 0,
    beforeSend: function () {},
    headers: {},
    statusCode: {
      200: function (res) {},
      404: function (res) {},
      500: function (res) {}
    },
    success: function (data) {
      console.log(data);
    },
    error: function (xhr) {
      console.log(xhr);
    },
    complete: function (xhr) {
      console.log(xhr);
    }
  });
})
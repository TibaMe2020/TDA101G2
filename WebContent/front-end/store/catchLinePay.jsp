<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <style>
        body {
            background-image: url('<%=request.getContextPath()%>/resources/images/linepayFinish.jpg');
            background-repeat: no-repeat;
            background-size: 800px 650px;
        }
    </style>
</head>

<body>
    <div style="text-align: center;">
        <h1 id="payment" style="text-align: center;"></h1>
    </div>
    <script src="https://code.jquery.com/jquery-3.5.1.min.js"
        integrity="sha256-9/aliU8dGd2tb6OSsuzixeV4y/faTqgFtohetphbbj0=" crossorigin="anonymous"></script>
    <script>
        function getTransactionId() {
            let transactionId = window.location.href.substr(window.location.href.indexOf("=") + 1)
            return transactionId;
        }
        window.onload = (event) => {
            let data =
            {
                "amount": sessionStorage.getItem("amount"),
                "currency": "TWD"
            }

            $.ajax({
                url: 'https://cors-anywhere.herokuapp.com/https://sandbox-api-pay.line.me/v2/payments/' + getTransactionId() + "/confirm",
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
                    // console.log(data.info.paymentUrl.web);
                    // $('#linepay').attr("href", data.info.paymentUrl.web)
                    sessionStorage.removeItem("amount");
                    $("#payment").text("付款成功");
                }
            });
        }
    </script>
</body>

</html>
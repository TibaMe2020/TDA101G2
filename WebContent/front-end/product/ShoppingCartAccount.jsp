<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>結帳</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.13.0/css/all.min.css" />
<link rel="stylesheet"
	href="https://fonts.googleapis.com/css2?family=Noto+Serif+TC&display=swap">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/front-end/member/css/headerNfooter.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/front-end/product/css/ShoppingCartAccount.css">
<style type="text/css">
#map {
  height: 500px;
  width: 760px;
}

#description {
  font-family: Roboto;
  font-size: 15px;
  font-weight: 300;
}

#infowindow-content .title {
  font-weight: bold;
}

#infowindow-content {
  display: none;
}

#map #infowindow-content {
  display: inline;
}

.pac-card {
  margin: 10px 10px 0 0;
  border-radius: 2px 0 0 2px;
  box-sizing: border-box;
  -moz-box-sizing: border-box;
  outline: none;
  box-shadow: 0 2px 6px rgba(0, 0, 0, 0.3);
  background-color: #fff;
  font-family: Roboto;
}

#pac-container {
  padding-bottom: 12px;
  margin-right: 12px;
}

.pac-controls {
  display: inline-block;
  padding: 5px 11px;
}

.pac-controls label {
  font-family: Roboto;
  font-size: 13px;
  font-weight: 300;
}

#pac-input {
margin-top:10px;
  background-color: #fff;
  font-family: Roboto;
  font-size: 30px;
  font-weight: 300;
  margin-left: 0px;
  padding: 0 11px 0 13px;
  text-overflow: ellipsis;
  width: 200px;
}

#pac-input:focus {
  border-color: #4d90fe;
}

#title {
  color: #fff;
  background-color: #4d90fe;
  font-size: 25px;
  font-weight: 500;
  padding: 6px 12px;
}
#target {
  width: 345px;
}    
</style>
</head>

<body>
	<!-- 購物車、結帳、全部訂單 -->

	<%@ include file="/front-end/member/header.jsp"%>
	<!-- 商品欄位 -->
	<div class="container">
		<div class="row" id="Row_Name">
			<table class="table">
				<thead style="background-color: orange;">
					<tr>

						<th scope="col">
							<h3 style="margin: auto;">商品圖片</h3>
						</th>
						<th scope="col">
							<h3 style="margin: auto;">商品名稱</h3>
						</th>
						<th scope="col">
							<h3 style="margin: auto;">單價</h3>
						</th>
						<th scope="col">
							<h3 style="margin: auto;">數量</h3>
						</th>
						<th scope="col">
							<h3 style="margin: auto;">小計</h3>
						</th>
					</tr>
				</thead>
				<tbody id="CartBody">
					<!-- 放動態生成的商品明細 -->

				</tbody>
			</table>
		</div>
	</div>
	<br>

	<!-- 運費活動 -->
	<div class="container">
		<div class="row" id="Activity" style="border: 3px solid black">
			<div class="col-2">
				<i class="fa fa-truck" id="truck"
					style="padding-top: 20px; padding-left: 20px; font-size: 50px;"></i>
			</div>
			<div class="col-3 text-left" style="margin-top: 5px;">
				<h4>運費活動:</h4>
				<p>
					全家、萊爾富取貨付款 $20 <br> 7-11取貨付款 $10
				</p>
			</div>

			<!--門市訊息 -->

			<div style="margin-top: 5px;">
				<div class="row">
					<div class="col-sm-12 col-md-8">
						<h4>門市資訊:</h4>
						  <select name="item">
						    <option value="1">a</option>
						    <option value="2">b</option>
						    <option value="3">c</option>
						  </select>						 
						</form>
					</div>
					<div class="col-sm-12 col-md-4">
						<button type="button" class="btn btn-secondary" id="Selectbtn">選擇門市</button>
					</div>
				</div>
			</div>

			<!--選擇門市資訊 -->
			<div class="bs-example">
				<div id="myModal" class="modal fade" tabindex="-1">
					<div class="modal-dialog modal-lg">
						<div class="modal-content">
							<div class="modal-body">
								<div class="d-flex justify-content-around align-items-center">
									<input id="pac-input" class="controls" type="text" placeholder="請輸入門市">
    								<div id="map"></div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<br>
	<div class="container">
		<div class="row" id="credit" style="border: 3px solid black">
			<div class="col-sm-12 col-md-4">
				<form style="margin: 5px 0 0 100px; text-align: left;">
					<h3>付款方式：</h3>
					<input type="radio" name="payment" value=" 信用卡" checked>信用卡
					<i class="fa fa-credit-card" style="font-size: 20px;"
						id="animatcredit"></i> <br> <input type="radio"
						name="payment" value="PayPal">一般付款
				</form>
			</div>

			<div class="col-sm-12 col-md-4"
				style="text-align: center; margin-top: 5px; font-size: 30px;">
				<p>訂單總金額:</p>
				<div class="row" style="margin-top: -25px; margin-left: 130px;"
					id="monyicon">
					$
					<p class="allTotal" id="totalSum" style="color: red"></p>
				</div>
			</div>

			<div class="col-sm-12 col-md-4">
				<div class="row">
					<a
						href="<%=request.getContextPath()%>/front-end/product/ShoppingCart.jsp">
						<button type="button" class="btn btn-secondary"
							id="BackToShoppingCartS">回到購物車</button>
					</a> <input class="btn btn-secondary" style="margin-left: 80px;"
						id="Final_order" type="submit" value="送出訂單">


				</div>
			</div>
		</div>
	</div>

	<!-- 信用卡動畫 -->
	<div class="modal fade" id="creditanimation" tabindex="-1"
		role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<div class="checkout">
						<div class="credit-card-box">
							<div class="flip">
								<div class="front">
									<div class="chip"></div>
									<div class="logo">
										<svg version="1.1" id="visa"
											xmlns="http://www.w3.org/2000/svg"
											xmlns:xlink="http://www.w3.org/1999/xlink" x="0px" y="0px"
											width="47.834px" height="47.834px"
											viewBox="0 0 47.834 47.834"
											style="enable-background: new 0 0 47.834 47.834;">
                                            <g>
                                                <g>
                                                    <path
												d="M44.688,16.814h-3.004c-0.933,0-1.627,0.254-2.037,1.184l-5.773,13.074h4.083c0,0,0.666-1.758,0.817-2.143
                         c0.447,0,4.414,0.006,4.979,0.006c0.116,0.498,0.474,2.137,0.474,2.137h3.607L44.688,16.814z M39.893,26.01
                         c0.32-0.819,1.549-3.987,1.549-3.987c-0.021,0.039,0.317-0.825,0.518-1.362l0.262,1.23c0,0,0.745,3.406,0.901,4.119H39.893z
                         M34.146,26.404c-0.028,2.963-2.684,4.875-6.771,4.875c-1.743-0.018-3.422-0.361-4.332-0.76l0.547-3.193l0.501,0.228
                         c1.277,0.532,2.104,0.747,3.661,0.747c1.117,0,2.313-0.438,2.325-1.393c0.007-0.625-0.501-1.07-2.016-1.77
                         c-1.476-0.683-3.43-1.827-3.405-3.876c0.021-2.773,2.729-4.708,6.571-4.708c1.506,0,2.713,0.31,3.483,0.599l-0.526,3.092
                         l-0.351-0.165c-0.716-0.288-1.638-0.566-2.91-0.546c-1.522,0-2.228,0.634-2.228,1.227c-0.008,0.668,0.824,1.108,2.184,1.77
                         C33.126,23.546,34.163,24.783,34.146,26.404z M0,16.962l0.05-0.286h6.028c0.813,0.031,1.468,0.29,1.694,1.159l1.311,6.304
                         C7.795,20.842,4.691,18.099,0,16.962z M17.581,16.812l-6.123,14.239l-4.114,0.007L3.862,19.161
                         c2.503,1.602,4.635,4.144,5.386,5.914l0.406,1.469l3.808-9.729L17.581,16.812L17.581,16.812z M19.153,16.8h3.89L20.61,31.066
                         h-3.888L19.153,16.8z"></path>
                                                </g>
                                            </g>
                                        </svg>
									</div>
									<div class="number"></div>
									<div class="card-holder">
										<label>Card holder</label>
										<div></div>
									</div>
									<div class="card-expiration-date">
										<label>Expires</label>
										<div></div>
									</div>
								</div>
								<div class="back">
									<div class="strip"></div>
									<div class="logo">
										<svg version="1.1" id="visa"
											xmlns="http://www.w3.org/2000/svg"
											xmlns:xlink="http://www.w3.org/1999/xlink" x="0px" y="0px"
											width="47.834px" height="47.834px"
											viewBox="0 0 47.834 47.834"
											style="enable-background: new 0 0 47.834 47.834;">
                                            <g>
                                                <g>
                                                    <path
												d="M44.688,16.814h-3.004c-0.933,0-1.627,0.254-2.037,1.184l-5.773,13.074h4.083c0,0,0.666-1.758,0.817-2.143
                         c0.447,0,4.414,0.006,4.979,0.006c0.116,0.498,0.474,2.137,0.474,2.137h3.607L44.688,16.814z M39.893,26.01
                         c0.32-0.819,1.549-3.987,1.549-3.987c-0.021,0.039,0.317-0.825,0.518-1.362l0.262,1.23c0,0,0.745,3.406,0.901,4.119H39.893z
                         M34.146,26.404c-0.028,2.963-2.684,4.875-6.771,4.875c-1.743-0.018-3.422-0.361-4.332-0.76l0.547-3.193l0.501,0.228
                         c1.277,0.532,2.104,0.747,3.661,0.747c1.117,0,2.313-0.438,2.325-1.393c0.007-0.625-0.501-1.07-2.016-1.77
                         c-1.476-0.683-3.43-1.827-3.405-3.876c0.021-2.773,2.729-4.708,6.571-4.708c1.506,0,2.713,0.31,3.483,0.599l-0.526,3.092
                         l-0.351-0.165c-0.716-0.288-1.638-0.566-2.91-0.546c-1.522,0-2.228,0.634-2.228,1.227c-0.008,0.668,0.824,1.108,2.184,1.77
                         C33.126,23.546,34.163,24.783,34.146,26.404z M0,16.962l0.05-0.286h6.028c0.813,0.031,1.468,0.29,1.694,1.159l1.311,6.304
                         C7.795,20.842,4.691,18.099,0,16.962z M17.581,16.812l-6.123,14.239l-4.114,0.007L3.862,19.161
                         c2.503,1.602,4.635,4.144,5.386,5.914l0.406,1.469l3.808-9.729L17.581,16.812L17.581,16.812z M19.153,16.8h3.89L20.61,31.066
                         h-3.888L19.153,16.8z"></path>
                                                </g>
                                            </g>
                                        </svg>

									</div>
									<div class="ccv">
										<label>CCV</label>
										<div></div>
									</div>
								</div>
							</div>
						</div>
						<form class="form" autocomplete="off" novalidate="">
							<fieldset>
								<label for="card-number">Card Number</label> <input type="num"
									id="card-number" class="input-cart-number" maxlength="4">
								<input type="num" id="card-number-1" class="input-cart-number"
									maxlength="4"> <input type="num" id="card-number-2"
									class="input-cart-number" maxlength="4"> <input
									type="num" id="card-number-3" class="input-cart-number"
									maxlength="4">
							</fieldset>
							<fieldset>
								<label for="card-holder">Card holder</label> <input type="text"
									id="card-holder">
							</fieldset>
							<fieldset class="card-expire">
								<label for="expire-month">Expire date</label>
								<div class="select">
									<select id="expire-month">
										<option></option>
										<option>01</option>
										<option>02</option>
										<option>03</option>
										<option>04</option>
										<option>05</option>
										<option>06</option>
										<option>07</option>
										<option>08</option>
										<option>09</option>
										<option>10</option>
										<option>11</option>
										<option>12</option>
									</select>
								</div>
								<div class="select">
									<select id="expire-year">
										<option></option>
										<option>2016</option>
										<option>2017</option>
										<option>2018</option>
										<option>2019</option>
										<option>2020</option>
										<option>2021</option>
										<option>2022</option>
										<option>2023</option>
										<option>2024</option>
										<option>2025</option>
									</select>
								</div>
							</fieldset>
							<fieldset class="fieldset-ccv">
								<label for="card-ccv">CCV</label> <input type="text"
									id="card-ccv" maxlength="3">
							</fieldset>
						</form>
					</div>
				</div>

				<!-- 信用卡確定跟取消 -->
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary"
						data-dismiss="modal" style="margin: auto;">取消</button>
					<button type="button" class="btn btn-primary" data-dismiss="modal"
						style="margin: auto;" id="ok">確定</button>
				</div>
			</div>
		</div>
	</div>
	<br>


	<%@ include file="/front-end/member/footer.jsp"%>
	<script src="https://cdn.jsdelivr.net/npm/sweetalert2@9"></script>
	<script
        src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBLtndsZXO550KJIZSzmj1a5Yt5Ot0j7sw&libraries=places&callback=initAutocomplete"
        async defer></script>
	<script
		src="<%=request.getContextPath()%>/front-end/product/js/ShoppingCartAccount.js"></script>
	<script>
	function initAutocomplete() {
        var map = new google.maps.Map(document.getElementById('map'), {
            center: {
            	//北商經緯度
            	lat:25.042084,
            	lng:121.525621
            },
            zoom: 15,
            mapTypeId: 'roadmap'
        });

        // Create the search box and link it to the UI element.
        var input = document.getElementById('pac-input');
        var searchBox = new google.maps.places.SearchBox(input);
        map.controls[google.maps.ControlPosition.TOP_LEFT].push(input);

        // Bias the SearchBox results towards current map's viewport.
        map.addListener('bounds_changed', function () {
            searchBox.setBounds(map.getBounds());
        });

        var markers = [];
        // Listen for the event fired when the user selects a prediction and retrieve
        // more details for that place.
        searchBox.addListener('places_changed', function () {
            var places = searchBox.getPlaces();

            if (places.length == 0) {
                return;
            }

            // Clear out the old markers.
            markers.forEach(function (marker) {
                marker.setMap(null);
            });
            markers = [];

            // For each place, get the icon, name and location.
            var bounds = new google.maps.LatLngBounds();
            places.forEach(function (place) {
                console.log(place)
                if (!place.geometry) {
                    console.log("Returned place contains no geometry");
                    return;
                }
                var icon = {
                    url: place.icon,
                    size: new google.maps.Size(71, 71),
                    origin: new google.maps.Point(0, 0),
                    anchor: new google.maps.Point(17, 34),
                    scaledSize: new google.maps.Size(25, 25)
                };

                // Create a marker for each place.
                markers.push(new google.maps.Marker({
                    map: map,
                    icon: icon,
                    title: place.name,
                    position: place.geometry.location
                }));

                if (place.geometry.viewport) {
                    // Only geocodes have viewport.
                    bounds.union(place.geometry.viewport);
                } else {
                    bounds.extend(place.geometry.location);
                }
            });
            map.fitBounds(bounds);
        });
    }
	</script>
</body>

</html>